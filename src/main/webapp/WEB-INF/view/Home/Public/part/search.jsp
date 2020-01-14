<div id="search-main" style="display: block;">
    <div class="off-search wow fadeInRight animated" data-wow-delay="0.4s"></div>
    <div class="off-search-a"></div>
    <div class="search-wrap wow fadeInDown animated" data-wow-delay="0.4s">
        <div class="searchbar">
            <form method="get" id="searchform" action="https://liuyanzhao.com/">
		<span class="search-input">
			<input type="text" value="" name="s" id="s" placeholder="输入搜索内容" required="">
			<button type="submit" id="searchsubmit"><i class="be be-search"></i></button>
		</span>
            </form>
        </div>
        <div class="searchbar">
            <script>
                function g(formname) {
                    var url = "https://www.baidu.com/baidu";
                    if (formname.s[1].checked) {
                        formname.ct.value = "2097152";
                    } else {
                        formname.ct.value = "0";
                    }
                    formname.action = url;
                    return true;
                }
            </script>
            <form name="f1" onsubmit="return g(this)" target="_blank">
				<span class="search-input">
					<input name="word" class="swap_value" placeholder="输入百度站内搜索关键词">
					<input name="tn" type="hidden" value="bds">
					<input name="cl" type="hidden" value="3">
					<input name="ct" type="hidden">
					<input name="si" type="hidden" value="liuyanzhao.com">
					<button type="submit" id="searchbaidu" class="search-close"><i class="be be-baidu"></i></button>
					<input name="s" class="choose" type="radio">
					<input name="s" class="choose" type="radio" checked="">
				</span>
            </form>
        </div>

        <div class="searchbar">
            <form action="https://www.so.com/s" target="_blank" id="so360form">
				<span class="search-input">
					<input type="text" autocomplete="off" placeholder="输入360站内搜索关键词" name="q" id="so360_keyword">
					<button type="submit" id="so360_submit" class="search-close">360</button>
					<input type="hidden" name="ie" value="utf-8">
					<input type="hidden" name="src" value="zz_liuyanzhao.com">
					<input type="hidden" name="site" value="liuyanzhao.com">
					<input type="hidden" name="rg" value="1">
					<input type="hidden" name="inurl" value="">
				</span>
            </form>
        </div>

        <div class="searchbar">
            <form action="https://www.sogou.com/web" target="_blank" name="sogou_queryform">
				<span class="search-input">
					<input type="text" placeholder="输入搜狗站内搜索关键词" name="query">
					<button type="submit" id="sogou_submit" class="search-close"
                            onclick="check_insite_input(document.sogou_queryform, 1)">搜狗</button>
					<input type="hidden" name="insite" value="liuyanzhao.com">
				</span>
            </form>
        </div>
        <div class="clear"></div>

        <nav class="search-nav">
            <h4>搜索热点</h4>
            <div class="clear"></div>
            <div class="menu-%e6%90%9c%e7%b4%a2%e6%8e%a8%e8%8d%90-container">
                <ul id="menu-%e6%90%9c%e7%b4%a2%e6%8e%a8%e8%8d%90" class="search-menu">
                    <li id="menu-item-9847"
                        class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9847"><a
                            href="https://liuyanzhao.com/category/javadev/">Java 开发</a></li>
                    <li id="menu-item-9848"
                        class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9848"><a
                            href="https://liuyanzhao.com/category/javadev/core-java/java-code/">Java 源码</a></li>
                    <li id="menu-item-9849"
                        class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9849"><a
                            href="https://liuyanzhao.com/category/frameworks/orm/">ORM 框架</a></li>
                    <li id="menu-item-9850"
                        class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9850"><a
                            href="https://liuyanzhao.com/category/frameworks/spring/springboot/">SpringBoot教程</a></li>
                    <li id="menu-item-9851"
                        class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9851"><a
                            href="https://liuyanzhao.com/category/frameworks/spring/springcloud/">SpringCloud</a></li>
                    <li id="menu-item-9852"
                        class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9852"><a
                            href="https://liuyanzhao.com/category/frameworks/%e5%ae%89%e5%85%a8%e6%a1%86%e6%9e%b6/shiro/">Shiro</a>
                    </li>
                    <li id="menu-item-9853"
                        class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9853"><a
                            href="https://liuyanzhao.com/category/frameworks/%e5%ae%89%e5%85%a8%e6%a1%86%e6%9e%b6/spring-security/">Spring
                        Security</a></li>
                    <li id="menu-item-9854"
                        class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9854"><a
                            href="https://liuyanzhao.com/category/frameworks/search-engine/">搜索引擎框架</a></li>
                    <li id="menu-item-9858"
                        class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9858"><a
                            href="https://liuyanzhao.com/category/frameworks/mq/">消息中间件</a></li>
                    <li id="menu-item-9859"
                        class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9859"><a
                            href="https://liuyanzhao.com/category/frameworks/cache/">缓存技术</a></li>
                    <li id="menu-item-9855"
                        class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9855"><a
                            href="https://liuyanzhao.com/category/other-tech/web-build/wordpress/">WordPress</a></li>
                    <li id="menu-item-9856"
                        class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9856"><a
                            href="https://liuyanzhao.com/category/other-tech/web-build/server-host/">服务器与主机</a></li>
                    <li id="menu-item-9857"
                        class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-9857"><a
                            href="https://liuyanzhao.com/category/knowledge/resource-share/">资源分享</a></li>
                </ul>
            </div>
        </nav>
    </div>
    <div class="off-search-b">
        <div class="clear"></div>
    </div>
</div>