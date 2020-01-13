package com.lzt.ssm.blog.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.github.pagehelper.PageInfo;
import com.lzt.ssm.blog.entity.*;
import com.lzt.ssm.blog.enums.ArticleStatus;
import com.lzt.ssm.blog.service.*;
import com.lzt.ssm.blog.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author lzt
 * @date 2020/1/13 16:19
 */
@Controller
@RequestMapping("/admin/comment")
public class BackCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("")
    public ModelAndView index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize, ModelAndView mv) {
        PageInfo<Comment> pageInfo = commentService.listCommentByPage(pageIndex, pageSize);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("pageUrlPrefix", "/admin/comment?pageIndex");

        mv.setViewName("Admin/Comment/index");
        return mv;
    }

    @RequestMapping("/edit/{commentId}")
    public ModelAndView editView(@PathVariable("commentId") Integer commentId, ModelAndView mv) {
        Comment comment = commentService.getEntityById(commentId);
        mv.addObject("comment", comment);

        mv.setViewName("Admin/Comment/edit");
        return mv;
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(Comment comment) {
        commentService.updateEntity(comment);
        return "redirect:/admin/comment";
    }

    @RequestMapping("/reply/{commentId}")
    public ModelAndView replyView(@PathVariable("commentId") Integer commentId, ModelAndView mv) {
        Comment comment = commentService.getEntityById(commentId);
        mv.addObject("comment", comment);

        mv.setViewName("Admin/Comment/reply");
        return mv;
    }

    @RequestMapping(value = "/replySubmit", method = RequestMethod.POST)
    public String replySubmit(Comment comment, HttpServletRequest request) {
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIp(request));

        //过滤字符，防止XSS攻击
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));
        comment.setCommentAuthorName(HtmlUtil.escape(comment.getCommentAuthorName()));
        comment.setCommentAuthorEmail(HtmlUtil.escape(comment.getCommentAuthorEmail()));
        comment.setCommentAuthorUrl(HtmlUtil.escape(comment.getCommentAuthorUrl()));

        User user = (User) request.getSession().getAttribute("user");
        comment.setCommentAuthorAvatar(user.getUserAvatar());
        Article article =
                articleService.getEntityByStatusAndId(ArticleStatus.PUBLISH.getValue(), comment.getCommentArticleId());
        //当前用户是作者时
        if (article.getArticleUserId().equals(user.getUserId())) {
            comment.setCommentRole(1);
        } else {
            comment.setCommentRole(0);
        }

        commentService.insertEntity(comment);
        articleService.updateCommentCount(comment.getCommentArticleId());

        return "redirect:/admin/comment";
    }

    @RequestMapping(value = "/delete/{commentId}", method = RequestMethod.POST)
    public void delete(@PathVariable("commentId") Integer commentId) {
        commentService.deleteChildComment(commentId);
        commentService.deleteEntityById(commentId);
    }
}
