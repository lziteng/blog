<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="title">
    - 页面列表
</rapid:override>
<rapid:override name="header-style">
    <style>
    </style>
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
          <a href="/admin">首页</a>
          <a><cite>页面列表</cite></a>
        </span>
    </blockquote>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>页面列表</legend>
    </fieldset>
    <form id="pageForm" method="post">
        <table class="layui-table">
            <colgroup>
                <col width="50">
                <col width="50">
                <col width="100">
                <col width="200">
                <col width="50">
                <col width="100">
            </colgroup>
            <thead>
            <tr>
                <th>id</th>
                <th>key</th>
                <th>标题</th>
                <th>内容</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageList}" var="p">
                <c:if test="${p.pageStatus!=2}">
                    <tr>
                        <td>${p.pageId}</td>
                        <td>${p.pageKey}</td>
                        <td>
                                ${p.pageTitle}
                        </td>
                        <td>
                            <a href="/${p.pageKey}"
                               target="_blank">
                                    ${fn:substring(p.pageContent, 0,10 )}
                            </a>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${p.pageStatus==1}">
                                    显示
                                </c:when>
                                <c:otherwise>
                                    <span style="color: #FF5722;">
                                        隐藏
                                    </span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="/admin/page/edit/${p.pageId}"
                               class="layui-btn layui-btn-mini">编辑</a>
                            <a href="/admin/page/delete/${p.pageId}"
                               class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </form>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>自定义页面</legend>
    </fieldset>
    <div class="layui-form">
        <table class="layui-table" style="width: 40%;">
            <colgroup>
                <col width="150">
                <col width="150">
                <col width="200">
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>别名</th>
                <th>标题</th>
                <th>内容</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageList}" var="p">
                <c:if test="${p.pageStatus==2}">
                    <tr>
                        <td>${p.pageKey}</td>
                        <td>${p.pageTitle}</td>
                        <td><a href="/${p.pageKey}" target="_blank">点击查看</a></td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <blockquote class="layui-elem-quote layui-quote-nm">
        温馨提示：
        <ul>
            <li> 1、自定义的页面，无法删除，别名已写入控制器(自己定义对应的方法和页面)</li>
            <li> 2、用法:对于手动添加的页面，通过/+key访问时(如/aboutSite)，由于在pageDetail方法中，根据pageKey为aboutSite能获取到Page对象，所以会跳转到Home/Page/page.jsp中，展示Page的内容</li>
            <li> 3、可与菜单功能搭配使用，实现访问不同的菜单(Menu对象的menuUrl需要和Page对象的pageKey保持一致)时，在同一个页面展示不同的内容(Page中维护的内容)。
                <br>
                例如:
                <br>
                先添加一个顶部菜单(menuName:博客通知、menuUrl:/blogNotice),
                <br>
                然后添加一个页面(pageTitle:博客通知、pageKey:blogNotice、pageContent:最新通知xxx)
                <br>
                访问顶部菜单/blogNotice，在pageDetail方法中，由于根据pageKey为blogNotice能获取到Page对象，所以会直接跳转到Home/Page/page.jsp中，展示Page的内容。
            </li>
        </ul>
    </blockquote>
</rapid:override>
<rapid:override name="footer-script">
    <script>


    </script>
</rapid:override>
<%@ include file="../Public/framework.jsp" %>
