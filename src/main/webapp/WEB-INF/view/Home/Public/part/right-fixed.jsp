<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<script src="/js/gb2big5.js"></script>--%>
<ul id="scroll">
    <li><a class="scroll-t" title="返回顶部"><i class="iconfont iconarrow-up"></i></a></li>
    <li><a class="scroll-b" title="转到底部"><i class="iconfont iconarrow-down"></i></a></li>
    <li><a class="m-eyecare" title="护眼模式"><i class="iconfont iconguanyuwomenguanzhuwomen"></i></a></li>
<%--    <li><a class="scroll-search" title="搜索"><i class="be be-search"></i></a></li>--%>
    <li class="gb2-site">
        <a id="gb2big5" href="javascript:StranBody()" title="繁體"><span>繁</span></a>
    </li>
   <%-- <li class="qqonline">
        <div class="online">
            <a href="javascript:void(0)"><i class="be be-qq"></i></a>
        </div>
        <div class="qqonline-box">
            <div class="qqonline-main">
                <div class="nline-wiexin">
                    <h4>微信</h4>
                    <img title="微信" alt="微信" src="https://liuyanzhao.com/wp-content/uploads/2018/09/wechat.jpg">
                </div>
                <div class="nline-qq">
                    <h4>在线咨询</h4>
                    <a target="_blank" rel="external nofollow"
                       href="http://wpa.qq.com/msgrd?v=3&amp;uin=847064370&amp;site=qq&amp;menu=yes" title="QQ在线咨询"><i
                            class="be be-qq"></i></a>
                </div>
                <div class="clear"></div>
                <div class="nline-phone">
                    <i class="be be-favoriteoutline"></i>选择一种方式联系我们！
                </div>
            </div>
            <span class="qq-arrow"><span class="arrow arrow-y"><i class="be be-playarrow"></i></span><span
                    class="arrow arrow-x"><i class="be be-playarrow"></i></span></span>
        </div>
    </li>
    <li class="qr-site"><a href="javascript:void(0)" class="qr" title="本页二维码"><i class="be be-qr-code"></i><span
            class="qr-img"><span id="output"><img class="alignnone"
                                                  src="https://liuyanzhao.com/wp-content/uploads/2017/04/sousoulogo.png"
                                                  alt="icon"><canvas width="150" height="150"></canvas></span><span
            class="arrow arrow-z"><i class="be be-playarrow"></i></span><span class="arrow arrow-y"><i
            class="be be-playarrow"></i></span></span></a></li>--%>
    <%--<script type="text/javascript">
        $(document).ready(function () {
        if (!+[1,]) {
            present = "table";
        } else {
            present = "canvas";
        }
        $('#output').qrcode({render: present, text: window.location.href, width: "150", height: "150"});
    });</script>--%>
</ul>