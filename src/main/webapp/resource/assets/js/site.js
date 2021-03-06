!function(a, b) {
    "function" == typeof define && define.amd ? define([], b) : "object" == typeof exports ? module.exports = b() : a.Headroom = b()
}(this, function() {
    function a(a) {
        this.callback = a,
            this.ticking = !1
    }
    function b(a) {
        return a && "undefined" != typeof window && (a === window || a.nodeType)
    }
    function c(a) {
        if (arguments.length <= 0) {
            throw new Error("Missing arguments in extend function")
        }
        var d, e, f = a || {};
        for (e = 1; e < arguments.length; e++) {
            var g = arguments[e] || {};
            for (d in g) {
                "object" != typeof f[d] || b(f[d]) ? f[d] = f[d] || g[d] : f[d] = c(f[d], g[d])
            }
        }
        return f
    }
    function d(a) {
        return a === Object(a) ? a : {
            down: a,
            up: a
        }
    }
    function e(a, b) {
        b = c(b, e.options),
            this.lastKnownScrollY = 0,
            this.elem = a,
            this.tolerance = d(b.tolerance),
            this.classes = b.classes,
            this.offset = b.offset,
            this.scroller = b.scroller,
            this.initialised = !1,
            this.onPin = b.onPin,
            this.onUnpin = b.onUnpin,
            this.onTop = b.onTop,
            this.onNotTop = b.onNotTop,
            this.onBottom = b.onBottom,
            this.onNotBottom = b.onNotBottom
    }
    var f = {
        bind: !!function() {}
            .bind,
        classList: "classList"in document.documentElement,
        rAF: !!(window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame)
    };
    return window.requestAnimationFrame = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame,
        a.prototype = {
            constructor: a,
            update: function() {
                this.callback && this.callback(),
                    this.ticking = !1
            },
            requestTick: function() {
                this.ticking || (requestAnimationFrame(this.rafCallback || (this.rafCallback = this.update.bind(this))),
                    this.ticking = !0)
            },
            handleEvent: function() {
                this.requestTick()
            }
        },
        e.prototype = {
            constructor: e,
            init: function() {
                return e.cutsTheMustard ? (this.debouncer = new a(this.update.bind(this)),
                    this.elem.classList.add(this.classes.initial),
                    setTimeout(this.attachEvent.bind(this), 100),
                    this) : void 0
            },
            destroy: function() {
                var a = this.classes;
                this.initialised = !1,
                    this.elem.classList.remove(a.unpinned, a.pinned, a.top, a.notTop, a.initial),
                    this.scroller.removeEventListener("scroll", this.debouncer, !1)
            },
            attachEvent: function() {
                this.initialised || (this.lastKnownScrollY = this.getScrollY(),
                    this.initialised = !0,
                    this.scroller.addEventListener("scroll", this.debouncer, !1),
                    this.debouncer.handleEvent())
            },
            unpin: function() {
                var a = this.elem.classList
                    , b = this.classes;
                !a.contains(b.pinned) && a.contains(b.unpinned) || (a.add(b.unpinned),
                    a.remove(b.pinned),
                this.onUnpin && this.onUnpin.call(this))
            },
            pin: function() {
                var a = this.elem.classList
                    , b = this.classes;
                a.contains(b.unpinned) && (a.remove(b.unpinned),
                    a.add(b.pinned),
                this.onPin && this.onPin.call(this))
            },
            top: function() {
                var a = this.elem.classList
                    , b = this.classes;
                a.contains(b.top) || (a.add(b.top),
                    a.remove(b.notTop),
                this.onTop && this.onTop.call(this))
            },
            notTop: function() {
                var a = this.elem.classList
                    , b = this.classes;
                a.contains(b.notTop) || (a.add(b.notTop),
                    a.remove(b.top),
                this.onNotTop && this.onNotTop.call(this))
            },
            bottom: function() {
                var a = this.elem.classList
                    , b = this.classes;
                a.contains(b.bottom) || (a.add(b.bottom),
                    a.remove(b.notBottom),
                this.onBottom && this.onBottom.call(this))
            },
            notBottom: function() {
                var a = this.elem.classList
                    , b = this.classes;
                a.contains(b.notBottom) || (a.add(b.notBottom),
                    a.remove(b.bottom),
                this.onNotBottom && this.onNotBottom.call(this))
            },
            getScrollY: function() {
                return void 0 !== this.scroller.pageYOffset ? this.scroller.pageYOffset : void 0 !== this.scroller.scrollTop ? this.scroller.scrollTop : (document.documentElement || document.body.parentNode || document.body).scrollTop
            },
            getViewportHeight: function() {
                return window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
            },
            getElementPhysicalHeight: function(a) {
                return Math.max(a.offsetHeight, a.clientHeight)
            },
            getScrollerPhysicalHeight: function() {
                return this.scroller === window || this.scroller === document.body ? this.getViewportHeight() : this.getElementPhysicalHeight(this.scroller)
            },
            getDocumentHeight: function() {
                var a = document.body
                    , b = document.documentElement;
                return Math.max(a.scrollHeight, b.scrollHeight, a.offsetHeight, b.offsetHeight, a.clientHeight, b.clientHeight)
            },
            getElementHeight: function(a) {
                return Math.max(a.scrollHeight, a.offsetHeight, a.clientHeight)
            },
            getScrollerHeight: function() {
                return this.scroller === window || this.scroller === document.body ? this.getDocumentHeight() : this.getElementHeight(this.scroller)
            },
            isOutOfBounds: function(a) {
                var b = 0 > a
                    , c = a + this.getScrollerPhysicalHeight() > this.getScrollerHeight();
                return b || c
            },
            toleranceExceeded: function(a, b) {
                return Math.abs(a - this.lastKnownScrollY) >= this.tolerance[b]
            },
            shouldUnpin: function(a, b) {
                var c = a > this.lastKnownScrollY
                    , d = a >= this.offset;
                return c && d && b
            },
            shouldPin: function(a, b) {
                var c = a < this.lastKnownScrollY
                    , d = a <= this.offset;
                return c && b || d
            },
            update: function() {
                var a = this.getScrollY()
                    , b = a > this.lastKnownScrollY ? "down" : "up"
                    , c = this.toleranceExceeded(a, b);
                this.isOutOfBounds(a) || (a <= this.offset ? this.top() : this.notTop(),
                    a + this.getViewportHeight() >= this.getScrollerHeight() ? this.bottom() : this.notBottom(),
                    this.shouldUnpin(a, c) ? this.unpin() : this.shouldPin(a, c) && this.pin(),
                    this.lastKnownScrollY = a)
            }
        },
        e.options = {
            tolerance: {
                up: 0,
                down: 0
            },
            offset: 0,
            scroller: window,
            classes: {
                pinned: "headroom--pinned",
                unpinned: "headroom--unpinned",
                top: "headroom--top",
                notTop: "headroom--not-top",
                bottom: "headroom--bottom",
                notBottom: "headroom--not-bottom",
                initial: "headroom",
                use: "r883"
            }
        },
        e.cutsTheMustard = "undefined" != typeof f && f.rAF && f.bind && f.classList,
        e
});
(function($) {
    if (!$) {
        return
    }
    $.fn.headroom = function(option) {
        return this.each(function() {
            var $this = $(this)
                , data = $this.data("headroom")
                , options = typeof option === "object" && option;
            options = $.extend(true, {}, Headroom.options, options);
            if (!data) {
                data = new Headroom(this,options);
                data.init();
                $this.data("headroom", data)
            }
            if (typeof option === "string") {
                data[option]();
                if (option === "destroy") {
                    $this.removeData("headroom")
                }
            }
        })
    }
}(window.jQuery));
$(document).ready(function() {
    $("#header-main").headroom({
        "tolerance": 10,
        "offset": 89,
        "classes": {
            "initial": "sliding",
            "pinned": "slideDown",
            "unpinned": "slideUp"
        }
    });
    $("#header-main-g").headroom({
        "tolerance": 10,
        "offset": 999999999,
        "classes": {
            "initial": "sliding",
            "pinned": "slideDown",
            "unpinned": "slideUp"
        }
    });
    $("#group-tab span:first").addClass("group-current");
    $("#group-tab .tab-bd-con:gt(0)").hide();
    $("#group-tab span").mouseover(function() {
        $(this).addClass("group-current").siblings("span").removeClass("group-current");
        $("#group-tab .group-tab-bd-con:eq(" + $(this).index() + ")").show().siblings(".group-tab-bd-con").hide().addClass("group-current")
    });
    $("#layout-tab span:first").addClass("current");
    $("#layout-tab .tab-bd-con:gt(0)").hide();
    $("#layout-tab span").mouseover(function() {
        $(this).addClass("current").siblings("span").removeClass("current");
        $("#layout-tab .tab-bd-con:eq(" + $(this).index() + ")").show().siblings(".tab-bd-con").hide().addClass("current")
    });
    $("#img-tab span:first").addClass("img-current");
    $("#img-tab .tab-bd-con:gt(0)").hide();
    $("#img-tab span").mouseover(function() {
        $(this).addClass("img-current").siblings("span").removeClass("img-current");
        $("#img-tab .img-tab-bd-con:eq(" + $(this).index() + ")").show().siblings(".img-tab-bd-con").hide().addClass("img-current")
    });
    $("#login-tab span:first").addClass("login-current");
    $("#login-tab .login-tab-bd-con:gt(0)").hide();
    $("#login-tab span").click(function() {
        $(this).addClass("login-current").siblings("span").removeClass("login-current");
        $("#login-tab .login-tab-bd-con:eq(" + $(this).index() + ")").show().siblings(".login-tab-bd-con").hide().addClass("login-current")
    });
    var divTestJQ = $("#below-main");
    var divJQ = $(".sort", divTestJQ);
    var EntityList = [];
    divJQ.each(function() {
        var thisJQ = $(this);
        EntityList.push({
            Name: parseInt(thisJQ.attr("name"), 10),
            JQ: thisJQ
        })
    });
    EntityList.sort(function(a, b) {
        return a.Name - b.Name
    });
    for (var i = 0; i < EntityList.length; i++) {
        EntityList[i].JQ.appendTo(divTestJQ)
    }
    var divTestJQ = $("#main");
    var divJQ = $(".sort", divTestJQ);
    var EntityList = [];
    divJQ.each(function() {
        var thisJQ = $(this);
        EntityList.push({
            Name: parseInt(thisJQ.attr("name"), 10),
            JQ: thisJQ
        })
    });
    EntityList.sort(function(a, b) {
        return a.Name - b.Name
    });
    for (var i = 0; i < EntityList.length; i++) {
        EntityList[i].JQ.appendTo(divTestJQ)
    }
    var divTestJQ = $("#group-section");
    var divJQ = $(".sort", divTestJQ);
    var EntityList = [];
    divJQ.each(function() {
        var thisJQ = $(this);
        EntityList.push({
            Name: parseInt(thisJQ.attr("name"), 10),
            JQ: thisJQ
        })
    });
    EntityList.sort(function(a, b) {
        return a.Name - b.Name
    });
    for (var i = 0; i < EntityList.length; i++) {
        EntityList[i].JQ.appendTo(divTestJQ)
    }
    $(".nav-search").click(function() {
        $("#search-main").fadeToggle(300)
    });
    $(".scroll-search").click(function() {
        $("#search-main").fadeToggle(300)
    });
    $(".off-search, .search-close, .off-search-a, .off-search-b").click(function() {
        $("#search-main").fadeToggle(300)
    });
    $(".nav-mobile").click(function() {
        $("#mobile-nav").fadeToggle(300)
    });
    $(".off-mobile-nav, .mobile-nav-b").click(function() {
        $("#mobile-nav").fadeToggle(300)
    });
    $(".backs").click(function() {
        $(".track").slideToggle("slow");
        return false
    });
    $(".qr").mouseover(function() {
        $(this).children(".qr-img").show()
    });
    $(".qr").mouseout(function() {
        $(this).children(".qr-img").hide()
    });
    $(".qqonline").mouseover(function() {
        $(this).children(".qqonline-box").show()
    });
    $(".qqonline").mouseout(function() {
        $(this).children(".qqonline-box").hide()
    });
    $(".sorting").mouseover(function() {
        $(this).children(".order-box").show()
    });
    $(".sorting").mouseout(function() {
        $(this).children(".order-box").hide()
    });
    $(".user-box").mouseover(function() {
        $(this).children(".user-info").show()
    });
    $(".user-box").mouseout(function() {
        $(this).children(".user-info").hide()
    });
    $(".shang-p").mouseover(function() {
        $(this).children("#shang").show();
        $(".share-sd, .like, .shang-empty").css("display", "none")
    });
    $(".shang-p").mouseout(function() {
        $(this).children("#shang").hide();
        $(".share-sd, .like, .shang-empty").css("display", "block")
    });
    $(".weixin-b").mouseover(function() {
        $(this).children(".weixin-qr").show()
    });
    $(".weixin-b").mouseout(function() {
        $(this).children(".weixin-qr").hide()
    });
    $("#respond").click(function() {
        $("#comment-author-info").addClass("author-form-fill")
    });
    $(".share-sd").mouseover(function() {
        $(this).children("#share").show()
    });
    $(".share-sd").mouseout(function() {
        $(this).children("#share").hide()
    });
    $(".show-more span").click(function(e) {
        $(this).html(["<i class='be be-squareplus'></i>展开", "<i class='be be-squareminus'></i>折叠"][this.hutia ^= 1]);
        $(this.parentNode.parentNode).next().slideToggle();
        e.preventDefault()
    });
    $(".scroll-h").click(function() {
        $("html,body").animate({
            scrollTop: "0px"
        }, 800)
    });
    $(".scroll-c").click(function() {
        $("html,body").animate({
            scrollTop: $(".scroll-comments").offset().top
        }, 800)
    });
    $(".scroll-b").click(function() {
        $("html,body").animate({
            scrollTop: $(".site-info").offset().top
        }, 800)
    });
    $(".message-widget li:last, .message-page li:last, .hot_commend li:last, .search-page li:last, .my-comment li:last, .message-tab li:last").css("border", "none");
    $(".emoji").click(function() {
        $(".emoji-box").animate({
            opacity: "toggle",
            left: "50px"
        }, 1000).animate({
            left: "10px"
        }, "fast");
        return false
    });
    $("#fontsize").click(function() {
        var _this = $(this);
        var _t = $(".single-content");
        var _c = _this.attr("class");
        if (_c == "size_s") {
            _this.removeClass("size_s").addClass("size_l");
            _this.text("A+");
            _t.removeClass("fontsmall").addClass("fontlarge")
        } else {
            _this.removeClass("size_l").addClass("size_s");
            _this.text("A-");
            _t.removeClass("fontlarge").addClass("fontsmall")
        }
    });
    if (document.body.clientWidth > 1024) {
        $(function() {
            $(window).scroll(function() {
                if ($("#log-box").html() != undefined) {
                    var h = $("#title-2").offset().top;
                    if ($(this).scrollTop() > h && $(this).scrollTop() < h + 50) {
                        $("#log-box").show()
                    }
                    var h = $("#title-1").offset().top;
                    if ($(this).scrollTop() > h && $(this).scrollTop() < h + 50) {
                        $("#log-box").hide()
                    }
                }
            })
        })
    }
    $(".log-button, .log-close").click(function() {
        $("#log-box").fadeToggle(300)
    });
    if ($("#log-box").length > 0) {
        $(".log").removeClass("log-no")
    }
    $(".log-prompt").show().delay(5000).fadeOut();
    $(".load img, .single-content img,.avatar").lazyload({
        effect: "fadeIn",
        threshold: 100,
        failure_limit: 70
    });
    $(".owl-item img").lazyload({
        event: "mouseover"
    });
    $("#catalog a[href*=#],area[href*=#]").click(function() {
        if (location.pathname.replace(/^\//, "") == this.pathname.replace(/^\//, "") && location.hostname == this.hostname) {
            var $target = $(this.hash);
            $target = $target.length && $target || $("[name=" + this.hash.slice(1) + "]");
            if ($target.length) {
                var targetOffset = $target.offset().top;
                $("html,body").animate({
                    scrollTop: targetOffset
                }, 800);
                return false
            }
        }
    });
    var i = $(".slide-h .fancybox").size();
    $(".myimg").html(" " + i + " 张图片");
    var i = $(".slides-h .fancybox").size();
    $(".mimg").html(" " + i + " 张图片");
    box_width = $(".single-tag, .cover-des-box, .tagcloud").width();
    len = $(".single-tag ul li a, .cover-des-box, .tagcloud a").length - 1;
    $(".single-tag ul li a, .cover-des-box, .tagcloud a").each(function(i) {
        var let = new Array("c3010a","31ac76","ea4563","31a6a0","8e7daa","4fad7b","f99f13","f85200","666666");
        var random1 = Math.floor(Math.random() * 9) + 0;
        var num = Math.floor(Math.random() * 6 + 9);
        $(this).attr("style", "background:#" + let[random1] + "");
        if ($(this).next().length > 0) {
            last = $(this).next().position().left
        }
    });
    $(".single-content").find(".down-line:last").css({
        clear: "both"
    });
    var $li = $(".zm-tabs-nav span");
    var $ul = $(".zm-tabs-container ul");
    $li.mouseover(function() {
        var $this = $(this);
        var $t = $this.index();
        $li.removeClass();
        $this.addClass("current");
        $ul.css("display", "none");
        $ul.eq($t).css("display", "block")
    });
    $(".off-side").click(function() {
        $(this).toggleClass("on-side");
        $("#sidebar").toggleClass("sidebar");
        $("#primary").toggleClass("primary")
    });
    $(".off-side").click(function() {
        $(this).toggleClass("on-side-l");
        $("#sidebar-l").toggleClass("sidebar");
        $("#primary-l").toggleClass("primary")
    });
    $(".filter-t").click(function() {
        $(this).toggleClass("off-filter");
        $(".filter-box-main").fadeToggle(300);
        $(".filter-results-main").fadeToggle(300)
    });
    $(".filtertag a").click(function() {
        var papaDate = $(this).parent(".filtertag").attr("data");
        $(this).parent(".filtertag").find("a").css("borderColor", "");
        if (papaDate == undefined | papaDate == "" | papaDate != $(this).attr("data")) {
            $(this).css("borderColor", "#ddd").parent(".filtertag").attr("data", $(this).attr("data"))
        } else {
            if (papaDate == $(this).attr("data")) {
                $(this).css("borderColor", "").parent(".filtertag").attr("data", "")
            } else {
                $(this).css("borderColor", "").parent(".filtertag").attr("data", "")
            }
        }
        var ishttps = "https:" == document.location.protocol ? true : false;
        if (ishttps) {
            var urlNow = "https://" + window.location.host + "/?"
        } else {
            if (window.location.host == "127.0.0.1" || window.location.host == "localhost") {
                var urlNow = "http://" + window.location.host + "/wordpress/?"
            } else {
                var urlNow = "http://" + window.location.host + "/?"
            }
        }
        var url = urlNow;
        var filtersa = $("#filtersa").attr("data");
        var filtersb = $("#filtersb").attr("data");
        var filtersc = $("#filtersc").attr("data");
        var filtersd = $("#filtersd").attr("data");
        var filterse = $("#filterse").attr("data");
        if (typeof (filtersa) != "undefined") {
            if (filtersa.length > 0) {
                url += "filtersa=" + filtersa
            }
        }
        if (typeof (filtersb) != "undefined") {
            if (url.substr(-1) != "?") {
                url += "&"
            }
            if (filtersb.length > 0) {
                url += "filtersb=" + filtersb
            }
        }
        if (typeof (filtersc) != "undefined") {
            if (url.substr(-1) != "?") {
                url += "&"
            }
            if (filtersc.length > 0) {
                url += "filtersc=" + filtersc
            }
        }
        if (typeof (filtersd) != "undefined") {
            if (url.substr(-1) != "?") {
                url += "&"
            }
            if (filtersd.length > 0) {
                url += "filtersd=" + filtersd
            }
        }
        if (typeof (filterse) != "undefined") {
            if (url.substr(-1) != "?") {
                url += "&"
            }
            if (filterse.length > 0) {
                url += "filterse=" + filterse
            }
        }
        if (urlNow != url) {
            window.location.href = url
        }
    });
    $(window).scroll(function() {
        var scrollTop = $(window).scrollTop();
        var $windowHeight = $(window).innerHeight();
        scrollTop > -1 ? $(".footer-nav").fadeIn(200).css("display", "block") : $(".footer-nav").fadeOut(200)
    });
    $(".tree-menu, .tree_categories").treemenu({
        delay: 300
    }).openActive();
    $(".animateNum").running();
    $(window).bind("scroll", function() {
        var top = $(window).scrollTop();
        $(".about-inf .animateNum").each(function() {
            if ($(this).offset().top - top < $(window).height() - 200) {
                $(".about-inf .animateNum").removeClass("all-view")
            }
        })
    });
    $(".m-eyecare").click(function() {
        $(".eyecare").toggleClass(function() {
            return "eyes"
        })
    });
    $(".m-night").click(function() {
        $(".m-day").addClass("not-day")
    });
    $(".m-day").click(function() {
        $(".m-day").removeClass("not-day")
    });
    var cookieClass = getCookie("class");
    $("#page").attr("class", cookieClass);
    $(".night li a").each(function() {
        $(this).click(function() {
            var className = $(this).attr("class");
            $("#page").attr("class", className, 30);
            function SetCookie(name, value, day) {
                var exp = new Date();
                exp.setTime(exp.getTime() + day * 24 * 60 * 60 * 1000);
                document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString()
            }
            SetCookie("class", className, 30)
        })
    });
    $(".tab-title span").click(function() {
        $(this).addClass("selected").siblings().removeClass();
        $(".tab-content > ul").hide().eq($(".tab-title span").index(this)).show()
    })
});
$(function() {
    function showLayer(id) {
        var layer = $("#" + id)
            , layerwrap = layer.find("#login");
        layer.fadeIn();
        layerwrap.css({
            "margin-top": -layerwrap.outerHeight() / 2
        })
    }
    function hideLayer() {
        $(".login-overlay").fadeOut()
    }
    $(".show-layer").on("click", function() {
        var layerid = $(this).data("show-layer");
        showLayer(layerid)
    });
    $(".login-overlay").on("click", function(event) {
        if (event.target == this) {
            hideLayer()
        }
    })
});
function embedImage() {
    var URL = prompt("请输入图片 URL 地址:", "http://");
    if (URL) {
        document.getElementById("comment").value = document.getElementById("comment").value + "" + URL + ""
    }
}
(function($) {
        $.fn.textSlider = function(settings) {
            settings = jQuery.extend({
                speed: "normal",
                line: 2,
                timer: 1000
            }, settings);
            return this.each(function() {
                $.fn.textSlider.scllor($(this), settings)
            })
        }
        ;
        $.fn.textSlider.scllor = function($this, settings) {
            var ul = $("ul:eq(0)", $this);
            var timerID;
            var li = ul.children();
            var _btnUp = $(".up:eq(0)", $this);
            var _btnDown = $(".down:eq(0)", $this);
            var liHight = $(li[0]).height();
            var upHeight = 0 - settings.line * liHight;
            var scrollUp = function() {
                _btnUp.unbind("click", scrollUp);
                ul.animate({
                    marginTop: upHeight
                }, settings.speed, function() {
                    for (i = 0; i < settings.line; i++) {
                        ul.find("li:first").appendTo(ul)
                    }
                    ul.css({
                        marginTop: 0
                    });
                    _btnUp.bind("click", scrollUp)
                })
            };
            var scrollDown = function() {
                _btnDown.unbind("click", scrollDown);
                ul.css({
                    marginTop: upHeight
                });
                for (i = 0; i < settings.line; i++) {
                    ul.find("li:last").prependTo(ul)
                }
                ul.animate({
                    marginTop: 0
                }, settings.speed, function() {
                    _btnDown.bind("click", scrollDown)
                })
            };
            var autoPlay = function() {
                timerID = window.setInterval(scrollUp, settings.timer)
            };
            var autoStop = function() {
                window.clearInterval(timerID)
            };
            ul.hover(autoStop, autoPlay).mouseout();
            _btnUp.css("cursor", "pointer").click(scrollUp);
            _btnUp.hover(autoStop, autoPlay);
            _btnDown.css("cursor", "pointer").click(scrollDown);
            _btnDown.hover(autoStop, autoPlay)
        }
    }
)(jQuery);
function grin(a) {
    var d;
    a = " " + a + " ";
    if (document.getElementById("comment") && document.getElementById("comment").type == "textarea") {
        d = document.getElementById("comment")
    } else {
        return false
    }
    if (document.selection) {
        d.focus();
        sel = document.selection.createRange();
        sel.text = a;
        d.focus()
    } else {
        if (d.selectionStart || d.selectionStart == "0") {
            var c = d.selectionStart;
            var b = d.selectionEnd;
            var e = b;
            d.value = d.value.substring(0, c) + a + d.value.substring(b, d.value.length);
            e += a.length;
            d.focus();
            d.selectionStart = e;
            d.selectionEnd = e
        } else {
            d.value += a;
            d.focus()
        }
    }
}
$.fn.postLike = function() {
    if (jQuery(this).hasClass("done")) {
        alert("您已赞过啦！");
        return false
    } else {
        $(this).addClass("done");
        var d = $(this).data("id")
            , c = $(this).data("action")
            , b = jQuery(this).children(".count");
        var a = {
            action: "zm_ding",
            um_id: d,
            um_action: c
        };
        $.post(wpl_ajax_url, a, function(e) {
            jQuery(b).html(e)
        });
        return false
    }
}
;
$(document).on("click", ".dingzan", function() {
    $(this).postLike()
});
var global_Html = "";
function printme() {
    global_Html = document.body.innerHTML;
    document.body.innerHTML = document.getElementById("primary").innerHTML;
    window.print();
    window.setTimeout(function() {
        document.body.innerHTML = global_Html
    }, 500)
}
(function($) {
        $.fn.openActive = function(activeSel) {
            activeSel = activeSel || ".active";
            var c = this.attr("class");
            this.find(activeSel).each(function() {
                var el = $(this).parent();
                while (el.attr("class") !== c) {
                    if (el.prop("tagName") === "UL") {
                        el.show()
                    } else {
                        if (el.prop("tagName") === "LI") {
                            el.removeClass("tree-closed");
                            el.addClass("tree-opened")
                        }
                    }
                    el = el.parent()
                }
            });
            return this
        }
        ;
        $.fn.treemenu = function(options) {
            options = options || {};
            options.delay = options.delay || 0;
            options.openActive = options.openActive || false;
            options.activeSelector = options.activeSelector || "";
            this.addClass("treemenu");
            this.find("> li").each(function() {
                e = $(this);
                var subtree = e.find("> ul");
                var button = e.find("span").eq(0).addClass("toggler");
                if (button.length == 0) {
                    var button = $("<span>");
                    button.addClass("toggler");
                    e.prepend(button)
                } else {
                    button.addClass("toggler")
                }
                if (subtree.length > 0) {
                    subtree.hide();
                    e.addClass("tree-closed");
                    e.find(button).mouseover(function() {
                        var li = $(this).parent("li");
                        li.find("> ul").slideToggle(options.delay);
                        li.toggleClass("tree-opened");
                        li.toggleClass("tree-closed");
                        li.toggleClass(options.activeSelector)
                    });
                    $(this).find("> ul").treemenu(options)
                } else {
                    $(this).addClass("tree-empty")
                }
            });
            if (options.openActive) {
                this.openActive(options.activeSelector)
            }
            return this
        }
    }
)(jQuery);
$(document).ready(function() {
    function initMainNavigation(container) {
        var dropdownToggle = $("<button />", {
            "class": "dropdown-toggle"
        });
        container.find(".menu-item-has-children > a").after(dropdownToggle);
        container.find(".dropdown-toggle").click(function(e) {
            var _this = $(this)
                , screenReaderSpan = _this.find("");
            e.preventDefault();
            _this.toggleClass("toggled-on");
            _this.next(".menu-item-has-children, .sub-menu").toggleClass("toggled-on")
        })
    }
    initMainNavigation($(".nav-menu"))
});
(function($) {
        $.extend({
            tipsBox: function(options) {
                options = $.extend({
                    obj: null,
                    str: "+1",
                    startSize: "12px",
                    endSize: "30px",
                    interval: 600,
                    color: "red",
                    callback: function() {}
                }, options);
                $("body").append("<span class='num'>" + options.str + "</span>");
                var box = $(".num");
                var left = options.obj.offset().left + options.obj.width() / 2;
                var top = options.obj.offset().top - options.obj.height();
                box.css({
                    "position": "absolute",
                    "left": left + "px",
                    "top": top + "px",
                    "z-index": 9999,
                    "font-size": options.startSize,
                    "line-height": options.endSize,
                    "color": options.color
                });
                box.animate({
                    "font-size": options.endSize,
                    "opacity": "0",
                    "top": top - parseInt(options.endSize) + "px"
                }, options.interval, function() {
                    box.remove();
                    options.callback()
                })
            }
        })
    }
)(jQuery);
$(function() {
    $(".dingzan").click(function() {
        $.tipsBox({
            obj: $(this),
            str: "+1",
            callback: function() {}
        });
        niceIn($(this))
    })
});
(function(defaults, $, window, document, undefined) {
        $.extend({
            tabifySetup: function(options) {
                return $.extend(defaults, options)
            }
        }).fn.extend({
            tabify: function(options) {
                options = $.extend({}, defaults, options);
                return $(this).each(function() {
                    var $element, tabHTML, $tabs, $sections;
                    $element = $(this);
                    $sections = $element.children();
                    tabHTML = '<ul class="tab-nav">';
                    $sections.each(function() {
                        if ($(this).attr("title") && $(this).attr("id")) {
                            tabHTML += '<li><a href="#' + $(this).attr("id") + '">' + $(this).attr("title") + "</a></li>"
                        }
                    });
                    tabHTML += "</ul>";
                    $element.prepend(tabHTML);
                    $tabs = $element.find(".tab-nav li");
                    var activateTab = function(id) {
                        $tabs.filter(".active").removeClass("active");
                        $sections.filter(".active").removeClass("active");
                        $tabs.has('a[href="' + id + '"]').addClass("active");
                        $sections.filter(id).addClass("active")
                    };
                    $tabs.on("click", function(e) {
                        activateTab($(this).find("a").attr("href"));
                        e.preventDefault()
                    });
                    activateTab($tabs.first().find("a").attr("href"))
                })
            }
        })
    }
)({
    property: "value",
    otherProperty: "value"
}, jQuery, window, document);
$(".tab-group").tabify();
(function($) {
        $.fn.running = function() {
            function n() {
                var t = $(".animateNum");
                var n = {
                    top: $(window).scrollTop(),
                    bottom: $(window).scrollTop() + $(window).height()
                };
                t.each(function() {
                    var t = $(this).attr("data-animateTarget");
                    n.top <= $(this).offset().top + $(this).height() && n.bottom >= $(this).offset().top && !$(this).data("start") && ($(this).data("start", !0),
                        new AnimateNum({
                            obj: $(this),
                            target: t,
                            totalTime: 1000
                        }))
                })
            }
            $(window).bind("scroll", function() {
                n()
            });
            function AnimateNum(t) {
                this.obj = t.obj,
                    this.target = t.target.toString(),
                    this.totalTime = t.totalTime || 1000,
                    this.init()
            }
            AnimateNum.prototype = {
                init: function() {
                    return this.target ? (this.animation(),
                        void 0) : !1
                },
                animation: function() {
                    var t = this
                        , i = this.target.indexOf(".")
                        , e = 0;
                    i >= 0 && (e = this.target.length - i - 1);
                    var n = this.target.replace(".", "")
                        , s = this.totalTime / 10 | 0
                        , a = n / s | 0
                        , r = 0
                        , h = 0;
                    t.timer = setInterval(function() {
                        r++,
                            h += a,
                            t.obj.html(h / Math.pow(10, e)),
                        r >= s && (clearInterval(t.timer),
                            t.obj.html(t.target))
                    }, 100)
                }
            }
        }
    }
)(jQuery);
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(";");
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == " ") {
            c = c.substring(1, c.length)
        }
        if (c.indexOf(nameEQ) == 0) {
            return c.substring(nameEQ.length, c.length)
        }
    }
    return null
}
;