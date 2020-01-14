$(function () {
    //标签随机颜色
    $(".tagcloud a").each(function (i) {
        let colorArr = new Array("c3010a", "31ac76", "ea4563", "31a6a0", "8e7daa", "4fad7b", "f99f13", "f85200", "666666");
        let random1 = Math.floor(Math.random() * 9) + 0;
        $(this).attr("style", "background:#" + colorArr[random1] + "");
    });

    $(".scroll-t").click(function() {
        $("html,body").animate({
            scrollTop: "0px"
        }, 800)
    });

    $(".scroll-b").click(function() {
        $("html,body").animate({
            scrollTop: $(".site-info").offset().top
        }, 800)
    });

    $(".m-eyecare").click(function() {
        $(".eyecare").toggleClass(function() {
            return "eyes"
        })
    });
});