<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
    - 公告列表
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/

        .layui-table {
            margin-top: 0;
        }

        .layui-btn {
            margin: 2px 0 !important;
        }
    </style>
</rapid:override>

<rapid:override name="content">

    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
          <a href="/admin">首页</a>
          <a><cite>公告列表</cite></a>
        </span>
    </blockquote>


    <table class="layui-table">
        <colgroup>
            <col width="400">
            <col width="50">
            <col width="100">
            <col width="100">
            <col width="50">
        </colgroup>
        <thead>
        <tr>
            <th>标题</th>
            <%--<th>Order</th>--%>
            <th>排序</th>
            <th>状态</th>
            <th>操作</th>
            <%--<td>ID</td>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${noticeList}" var="c">
            <tr>
                <td>
                    <a href="/notice/${c.noticeId}" target="_blank">${c.noticeTitle}</a>
                </td>
                    <%--  <td>
                              ${c.noticeOrder}
                      </td>--%>
                <td>
                    <span id="${c.noticeId}" class="up">
<%--                        <i class="layui-icon" style="font-size: 24px; color: #1E9FFF;">&#xe62f;</i>--%>
                    </span>
                    <span id="${c.noticeId}" class="down">
<%--                        <i class="layui-icon" style="font-size: 24px; color: #1E9FFF;">&#xe601;</i>--%>
                    </span>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${c.noticeStatus == 1}">
                            显示
                        </c:when>
                        <c:otherwise>
                            <span style="color:#FF5722;">隐藏</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="/admin/notice/edit/${c.noticeId}" class="layui-btn layui-btn-mini">编辑</a>
                    <a href="/admin/notice/delete/${c.noticeId}" class="layui-btn layui-btn-danger layui-btn-mini"
                       onclick="return confirmDelete()">删除</a>
                </td>
                <%--<td>${c.noticeId}</td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <blockquote class="layui-elem-quote layui-quote-nm">
        温馨提示：
        <ul>
                <%--<li>Order的大小决定显示的顺序</li>--%>
            <li>可根据实际情况，调整公告的顺序</li>
        </ul>
    </blockquote>
</rapid:override>
<rapid:override name="footer-script">
    <script>
        /**
         * 上下排序功能
         */
        $(function () {
            $(document).on("click", "span.up", function () {
                var trParent = $(this).parent().parent();
                if (trParent.prev().length > 0)
                {
                    trParent.prev().before(trParent);
                    var id = $(this).attr("id");
                    $.get("/admin/notice/move/" + id + " ?direction=up", function () {
                    })
                }
            });

            $(document).on("click", "span.down", function () {
                var trParent = $(this).parent().parent();
                if (trParent.next().length > 0)
                {
                    trParent.next().after(trParent);
                    var id = $(this).attr("id");
                    $.get("/admin/notice/move/" + id + " ?direction=down", function () {
                    })
                }
            });
        });
    </script>
</rapid:override>

<%@ include file="../Public/framework.jsp" %>
