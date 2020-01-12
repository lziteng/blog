package com.lzt.ssm.blog.controller.admin;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HtmlUtil;
import com.github.pagehelper.PageInfo;
import com.lzt.ssm.blog.dto.ArticleParm;
import com.lzt.ssm.blog.entity.*;
import com.lzt.ssm.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author lzt
 * @date 2020/1/10 15:16
 */
@Controller
@RequestMapping("/admin/article")
public class BackArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    /**
     * 上移
     */
    public static final String UP = "up";

    /**
     * 下移
     */
    public static final String DOWN = "down";

    /**
     * 后台文章列表显示
     *
     * @return modelAndView
     */
    @RequestMapping(value = "")
    public ModelAndView index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                              @RequestParam(required = false) String status, ModelAndView mv) {
        HashMap<String, Object> criteria = new HashMap<>(1);
        if (status == null) {
            mv.addObject("pageUrlPrefix", "/admin/article?pageIndex");
        } else {
            criteria.put("status", status);
            mv.addObject("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
        }
        PageInfo<Article> articlePageInfo = articleService.pageEntity(pageIndex, pageSize, criteria);
        mv.addObject("pageInfo", articlePageInfo);
        mv.setViewName("Admin/Article/index");
        return mv;
    }

    /**
     * 添加页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/insert")
    public ModelAndView insertView(ModelAndView mv) {
        List<Category> categoryList = categoryService.listEntity();
        mv.addObject("categoryList", categoryList);

        List<Tag> tagList = tagService.listEntity();
        mv.addObject("tagList", tagList);

        mv.setViewName("Admin/Article/insert");

        return mv;
    }

    /**
     * 提交添加
     *
     * @param session
     * @param articleParm 页面form表单转换的对象
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertSubmit(HttpSession session, ArticleParm articleParm) {
        Article article = convert(session, articleParm);
        Integer maxOrder = articleService.getMaxOrder();
        if (maxOrder == null) {
            maxOrder = 0;
        }
        article.setArticleOrder(++maxOrder);
        articleService.insertEntity(article);
        return "redirect:/admin/article";
    }


    /**
     * 编辑页面
     *
     * @param id
     * @param mv
     * @return
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView editView(@PathVariable("id") Integer id, ModelAndView mv) {
        List<Category> categoryList = categoryService.listEntity();
        mv.addObject("categoryList", categoryList);

        List<Tag> tagList = tagService.listEntity();
        mv.addObject("tagList", tagList);

        Article article = articleService.getEntityByStatusAndId(null, id);
        mv.addObject("article", article);

        mv.setViewName("Admin/Article/edit");
        return mv;
    }

    /**
     * 提交编辑
     *
     * @param articleParm 页面form表单转换的对象
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(ArticleParm articleParm) {
        Article article = convert(null, articleParm);
        articleService.updateEntityDetail(article);

        return "redirect:/admin/article";
    }

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        articleService.deleteEntityById(id);
        return "redirect:/admin/article";
    }

    /**
     * 根据页面form表单的对象，填充文章内容
     *
     * @param session
     * @param articleParm form表单
     * @return 文章
     */
    private Article convert(HttpSession session, ArticleParm articleParm) {
        Article article = new Article();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            article.setArticleUserId(user.getUserId());
        }
        article.setArticleTitle(articleParm.getArticleTitle());
        article.setArticleContent(articleParm.getArticleContent());
        article.setArticleStatus(articleParm.getArticleStatus());

        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(articleParm.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }

        //文章和分类的关联记录
        List<Category> categoryList = new ArrayList<>(2);
        if (articleParm.getArticleParentCategoryId() != null) {
            categoryList.add(new Category(articleParm.getArticleParentCategoryId()));
        }

        if (articleParm.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParm.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);

        //文章和标签的关联记录
        List<Tag> tagList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(articleParm.getArticleTagIds())) {
            for (Integer tagId : articleParm.getArticleTagIds()) {
                tagList.add(new Tag(tagId));
            }
        }
        article.setTagList(tagList);
        return article;
    }

    /**
     * 上下移动操作
     *
     * @param id        id
     * @param direction 移动方向
     */
    @RequestMapping("/move/{id}/{direction}")
    @ResponseBody
    public String move(@PathVariable("id") Integer id, @PathVariable("direction") String direction) {
        Article nowEntity = articleService.getEntityById(id);
        Integer nowOrder = nowEntity.getArticleOrder();

        //由于按排序号、id降序排序，所有将上下移动操作互换
        if (DOWN.equals(direction)) {
            Article preEntity = articleService.getPreEntityByOrder(null, nowOrder);
            nowEntity.setArticleOrder(preEntity.getArticleOrder());
            preEntity.setArticleOrder(nowOrder);
            articleService.updateEntity(nowEntity);
            articleService.updateEntity(preEntity);
        } else if (UP.equals(direction)) {
            Article nextEntity = articleService.getNextEntityByOrder(null, nowOrder);
            nowEntity.setArticleOrder(nextEntity.getArticleOrder());
            nextEntity.setArticleOrder(nowOrder);
            articleService.updateEntity(nowEntity);
            articleService.updateEntity(nextEntity);
        }

        return "success";
    }
}
