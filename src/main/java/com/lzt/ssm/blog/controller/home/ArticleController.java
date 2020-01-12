package com.lzt.ssm.blog.controller.home;

import com.alibaba.fastjson.JSON;
import com.lzt.ssm.blog.entity.Article;
import com.lzt.ssm.blog.entity.User;
import com.lzt.ssm.blog.enums.ArticleStatus;
import com.lzt.ssm.blog.service.ArticleService;
import com.lzt.ssm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/12 16:08
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @RequestMapping("/article/{articleId}")
    public String goDetail(@PathVariable("articleId") Integer articleId, Model model) {
        Article article = articleService.getEntityByStatusAndId(ArticleStatus.PUBLISH.getValue(), articleId);
        if (article == null) {
            return "Home/Error/404";
        }

        //作者信息
        User user = userService.getEntityById(article.getArticleUserId());
        article.setUser(user);

        model.addAttribute("article", article);

        //由于按排序号降序排序，所以上一篇和下一篇互换
        Article preArticle = articleService.getNextEntityByOrder(ArticleStatus.PUBLISH.getValue(), article.getArticleOrder());
        Article nextArticle = articleService.getPreEntityByOrder(ArticleStatus.PUBLISH.getValue(), article.getArticleOrder());
        model.addAttribute("preArticle", preArticle);
        model.addAttribute("nextArticle", nextArticle);

        //相关文章(查询文章所属分类下的文章列表)
        List<Integer> categoryIds = articleService.listCategoryIdsByArticleId(articleId);
        List<Article> relatedArticleList = articleService.listArticleByCategoryIds(categoryIds, 5);
        model.addAttribute("relatedArticleList", relatedArticleList);

        //猜你喜欢(访问量高的文章列表)
        List<Article> mostViewArticleList = articleService.listArticleByViewCount(5);
        model.addAttribute("mostViewArticleList", mostViewArticleList);

        return "Home/Page/articleDetail";
    }

    /**
     * 文章访问量数增加
     *
     * @param articleId 文章id
     * @return 最新的访问量数
     */
    @RequestMapping(value = "/article/view/{articleId}", method = RequestMethod.POST)
    @ResponseBody
    public String increaseViewCount(@PathVariable("articleId") Integer articleId) {
        Article article = articleService.getEntityByStatusAndId(ArticleStatus.PUBLISH.getValue(), articleId);
        article.setArticleViewCount(article.getArticleViewCount() + 1);
        articleService.updateEntity(article);
        return JSON.toJSONString(article.getArticleViewCount());
    }

    /**
     * 文章点赞数增加
     *
     * @param articleId 文章id
     * @return 最新的点赞数
     */
    @RequestMapping(value = "/article/like/{articleId}", method = RequestMethod.POST)
    @ResponseBody
    public String increaseLikeCount(@PathVariable("articleId") Integer articleId) {
        Article article = articleService.getEntityByStatusAndId(ArticleStatus.PUBLISH.getValue(), articleId);
        article.setArticleLikeCount(article.getArticleLikeCount() + 1);
        articleService.updateEntity(article);
        return JSON.toJSONString(article.getArticleLikeCount());
    }

}
