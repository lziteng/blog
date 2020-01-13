<%--
    一般用于首页侧边栏：
    包括 关于本站，网站概况，热评文章，所有标签，随机文章 等小工具

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--博客主体-右侧侧边栏 start--%>
<div id="sidebar" class="widget-area all-sidebar"
     style="position: relative; overflow: visible; box-sizing: border-box; min-height: 1px;">

    <%--关于本站 start--%>
    <aside class="widget about">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>关于本站
        </h3>
        <div id="feed_widget">
            <div class="feed-about">
                <div class="about-main">
                    <div class="about-img">
                        <img src="${options.optionAboutsiteAvatar}"
                             alt="QR Code">
                    </div>
                    <div class="about-name">${options.optionAboutsiteTitle}</div>
                    <div class="about-the">
                        ${options.optionAboutsiteContent}
                    </div>
                </div>
                <div class="clear"></div>
                <ul>
                    <li class="weixin">
                        <a title="微信" id="weixin_btn" rel="external nofollow">
                            <i class="fa fa-weixin"> </i>
                            <div id="weixin_code" class="hide">
                                <img src="${options.optionAboutsiteWechat}" alt="">
                            </div>
                        </a>
                    </li>
                    <li class="tqq">
                        <a target="blank" rel="external nofollow"
                           href="http://wpa.qq.com/msgrd?V=3&amp;uin=${options.optionAboutsiteQq}&amp;Site=QQ&amp;Menu=yes"
                           title="QQ在线">
                            <i class="fa fa-qq"></i>
                        </a>
                    </li>
                    <li class="tsina">
                        <a title=""
                           href="http://weibo.com/${options.optionAboutsiteWeibo}"
                           target="_blank" rel="external nofollow">
                            <i class="fa fa-weibo"></i>
                        </a>
                    </li>
                    <li class="feed">
                        <a title="" href="https://github.com/${options.optionAboutsiteGithub}" target="_blank"
                           rel="external nofollow">
                            <i class="fa fa-github"></i>
                        </a>
                    </li>
                </ul>
                <div class="about-inf">
                    <span class="about-pn">文章 ${siteBasicData.articleCount} </span>
                    <span class="about-cn">评论 ${siteBasicData.commentCount} </span>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </aside>
    <%--关于本站 start--%>

    <%--网站概况 start--%>
    <aside id="php_text-22" class="widget php_text">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>网站概况
        </h3>
        <div class="textwidget widget-text">
            <ul class="site-profile">
                <li><i class="fa fa-file-o"></i> 文章总数：${siteBasicData.articleCount} 篇</li>
                <li><i class="fa fa-commenting-o"></i> 评论数量：${siteBasicData.commentCount}条</li>
                <li><i class="fa fa-folder-o"></i> 分类数量：${siteBasicData.categoryCount} 个</li>
                <li><i class="fa fa-tags"></i> 标签总数：${siteBasicData.tagCount} 个</li>
                <li><i class="fa fa-link"></i> 链接数量：${siteBasicData.linkCount} 个</li>
                <li><i class="fa fa-eye"></i> 浏览总量：${siteBasicData.viewCount}次</li>
                <li><i class="fa fa-pencil-square-o"></i> 最后更新：
                    <span style="color:#2F889A">
                        <fmt:formatDate value="${siteBasicData.articleLastUpdateTime}"
                                        pattern="yyyy年MM月dd日"/>
                     </span>
                </li>
            </ul>
        </div>
        <div class="clear"></div>
    </aside>
    <%--网站概况 end--%>

    <%--所有标签 start--%>
    <aside class="widget">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>所有标签
        </h3>
        <div class="tagcloud">
            <c:forEach items="${tagList}" var="tag">
                <a href="/tag/${tag.tagId}"
                   class="tag-link-129 tag-link-position-1"
                   style="font-size: 14px;">
                        ${tag.tagName}
                </a>
            </c:forEach>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </aside>
    <%--所有标签 end--%>


    <%--热评文章 start--%>
    <aside class="widget hot_comment">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>热评文章
        </h3>
        <div id="hot_comment_widget">
            <ul>
                <c:forEach items="${mostCommentArticleList}" var="m">
                    <li>
                        <a href="/article/${m.articleId}" rel="bookmark" title=" (${m.articleCommentCount}条评论)">
                                ${m.articleTitle}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="clear"></div>
    </aside>
    <%--热评文章 end--%>
</div>

<%--博客主体-右侧侧边栏 end--%>
