package com.lzt.ssm.blog.controller.home;

import cn.hutool.http.HtmlUtil;
import com.lzt.ssm.blog.dto.JsonResult;
import com.lzt.ssm.blog.entity.*;
import com.lzt.ssm.blog.enums.ArticleStatus;
import com.lzt.ssm.blog.service.*;
import com.lzt.ssm.blog.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author lzt
 * @date 2020/1/13 11:23
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult comment(Comment comment, HttpServletRequest request) throws Exception {
        comment.setCommentCreateTime(new Date());
        comment.setCommentRole(0);
        comment.setCommentIp(MyUtils.getIp(request));

        //过滤字符，防止XSS攻击
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));
        comment.setCommentAuthorName(HtmlUtil.escape(comment.getCommentAuthorName()));
        comment.setCommentAuthorEmail(HtmlUtil.escape(comment.getCommentAuthorEmail()));
        comment.setCommentAuthorUrl(HtmlUtil.escape(comment.getCommentAuthorUrl()));

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            comment.setCommentAuthorAvatar(user.getUserAvatar());
            Article article =
                    articleService.getEntityByStatusAndId(ArticleStatus.PUBLISH.getValue(), comment.getCommentArticleId());
            //当前用户是作者时
            if (article.getArticleUserId().equals(user.getUserId())) {
                comment.setCommentRole(1);
            }
        } else {
            comment.setCommentAuthorAvatar(MyUtils.getAvatar(comment.getCommentAuthorEmail()));
        }
        commentService.insertEntity(comment);

        //更新文章的评论数
        articleService.updateCommentCount(comment.getCommentArticleId());

        return new JsonResult().ok();
    }
}
