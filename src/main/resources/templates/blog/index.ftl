<#assign
page={"path":"./"}
/>
<!DOCTYPE html>
<html>
<head>
    <title>Mine &amp; Nook</title>
    <!-- meta -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="叶不空,yebukong">
    <meta name="copyright" content="叶不空,yebukong">
    <meta name="Keywords" Content="叶不空,yebukong,Mine&Nook,MineNook,MineBlog,NookBlog">
    <meta name="description" Content="欢迎来到叶不空's博客站点[yebukong.com]，这里是我记录技术和生活点滴的地方─=≡Σ((( つ•̀ω•́)つ">

    <link rel="shortcut icon" href="./img/happy.ico" type="image/x-icon"/>
    <link rel="canonical" href="https://yebukong.com/">
    <!-- css -->
    <link rel="stylesheet" href="lib/bootstrap/3.3.2/bootstrap.min.css">

    <#--<link rel="stylesheet" href="css/pace.css">-->
    <link rel="stylesheet" href="css/custom.css">

    <!--<link rel="stylesheet" href="css/ionicons.min.css">-->
    <!--<link href="https://unpkg.com/ionicons@4.4.1/dist/css/ionicons.min.css" rel="stylesheet">-->
    <link href="https://cdn.staticfile.org/ionicons/4.4.1/css/ionicons.min.css" rel="stylesheet">
    <!--https://www.jq22.com/jquery/font-awesome.4.6.0.css-->
    <link rel="stylesheet" type="text/css"
          href="https://cdn.staticfile.org/font-awesome/4.6.0/css/font-awesome.min.css">
    <!-- js -->
    <script src="js/cute-title.js"></script>
    <script src="js/jquery-2.1.3.min.js"></script>
    <script src="lib/bootstrap/3.3.2/bootstrap.min.js"></script>
    <#--<script src="js/pace.min.js"></script>-->
    <script src="js/modernizr.custom.js"></script>
    <!--控制台Log-->
    <script src="js/Mine&Nook-log.js"></script>
    <!--回到顶部-->
    <link rel="stylesheet" href="lib/to-top/to-top.css" media="all">
    <script src="lib/to-top/to-top.js"></script>
</head>
<body>
<div class="container">
     <#include "./inc/header.ftl"/>
</div>
<div class="copyrights"></div>

<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-8">
                <div class="static">
                    <#list articles as article>
                        <article class="post post-${article.id}">
                            <header class="entry-header">
                                <h1 class="entry-title">
                                    <a href="./article/${article.id}.html">${article.title!""}</a>
                                </h1>
                                <div class="entry-meta">
                                <span class="post-date"><a href="#">
                                    <time class="entry-date"
                                          datetime="2012-11-09T23:15:57+00:00">${article.created?string('yyyy/MM/dd')}</time>
                                </a></span>
                                    <span class="post-category"><a href="#">
                                    <#list articleTypes as type><#if article.type=type.itemNo>
                                        ${type.itemName}
                                    </#if></#list>
                                    </a></span>
                                    <span class="post-param"><a href="#">${article.address!""}</a></span>
                                    <span class="comments-link"><a href="#">~${article.wordCount!""}字</a></span>
                                </div>
                            </header>
                            <div class="entry-content clearfix">
                                <p>${article.brief!""}</p>
                                <div class="read-more cl-effect-14">
                                    <a href="./article/${article.id}.html" class="more-link">继续阅读 <span
                                            class="meta-nav">→</span></a>
                                </div>
                            </div>
                        </article>
                    </#list>
                </div>
                <div id="flowArticles">
                </div>
            </main>
            <aside class="col-md-4">
                <div class="widget">
                    <h3 class="widget-title">
                        <a href="https://www.jinrishici.com" target="_blank" title="前往今日诗词">今日诗词</a>
                        <span class="jinrishici-reload" title="换一换">[换一换]</span>
                    </h3>
                    <div class="jinrishici-msg">获取中...</div>
                    <div class="jinrishici-origin">
                        <h5 class="jinrishici-title"></h5>
                        <h6 class="jinrishici-author"></h6>
                        <div class="jinrishici-origin-content"></div>
                    </div>
                </div>
                <div class="widget widget-archives">
                    <a href="#"><h3 class="widget-title">分类</h3></a>
                    <ul>
                    <#assign articleTypeMap = ""> <#-- 创建变量 文章类型 -->
                    <#list articleTypes as type>
                        <#assign articleTypeMap = articleTypeMap + type.itemNo +":'"+type.itemName+"'">
                        <#if type_has_next>
                            <#assign articleTypeMap = articleTypeMap + ",">
                        </#if>
                        <li>
                            <a href="./unfinish.html">${type.itemName}</a>
                        </li>
                    </#list>
                    </ul>
                </div>

                <div class="widget widget-category">
                    <a href="#"><h3 class="widget-title">标签</h3></a>
                    <ul>
                    <#list articleTags as tag>
                        <li>
                            <a href="./unfinish.html">${tag.itemName}</a>
                        </li>
                    </#list>
                    </ul>
                </div>
            </aside>
        </div>
    </div>
</div>
<!--底部-->
<#include "./inc/footer.ftl"/>
<!-- Mobile Menu -->
<#include "./inc/mobile-menu.ftl"/>
<script type="text/html" id="oneArticle">
    <article class="post post-{{d.o.id}}">
        <header class="entry-header">
            <h1 class="entry-title">
                <a href="./article/{{d.o.id}}.html">{{d.o.title}}</a>
            </h1>
            <div class="entry-meta">
                <span class="post-date"><a href="#"><time class="entry-date" datetime="2012-11-09T23:15:57+00:00">{{d.o.createdFmt}}</time></a></span>
                <span class="post-category"><a href="#">{{d.archiveType[d.o.type]}}</a></span>
                <span class="post-param"><a href="#">{{d.o.address}}</a></span>
                <span class="comments-link"><a href="#">~{{d.o.wordCount}}字</a></span>
            </div>
        </header>
        <div class="entry-content clearfix">
            <p>{{d.o.brief}}</p>
            <div class="read-more cl-effect-14">
                <a href="./article/{{d.o.id}}.html" class="more-link">继续阅读 <span class="meta-nav">→</span></a>
            </div>
        </div>
    </article>
</script>
<script src="js/script.js"></script>
<script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['flow', 'laytpl', 'layer'], function () {
        $ = layui.jquery;
        var layer = layui.layer;
        var flow = layui.flow;
        var laytpl = layui.laytpl;
        _getJinrishici();//今日诗词
        $(".jinrishici-reload").click(function () {
            $(".jinrishici-msg").text("获取中...");
            _getJinrishici(true);
        });

        var archiveTypeMap = {${articleTypeMap}};
        flow.load({//加载更多文章
            elem: '#flowArticles' //流加载容器
            , isAuto: false
            , mb: 100
            , isLazyimg: true
            , done: function (page, next) { //加载下一页
                $(".layui-flow-more i.layui-icon").text("");//没有引入layui.css
                $(".layui-flow-more i.layui-icon").removeClass("layui-anim layui-anim-rotate layui-anim-loop");
                $(".layui-flow-more i.layui-icon").addClass("fa fa-circle-o-notch fa-spin fa-2x fa-fw");
                if (page < 2) {//第一页已经静态化不加载
                    next('', true);
                    return;
                }
                //layer.msg("开始加载了" + page);
                setTimeout(function () {
                    $.ajax({
                        url: "./articleBriefList",
                        type: "GET",
                        dataType: "JSON",
                        data: {current:page,size:5},
                        async: true,
                        success: function(data, status) {
                                resolveData(data,next)
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            var errmsg = '['+jqXHR.status+':'+textStatus+']请求错误!';
                            layer.msg(errmsg, { anim: 6, icon: 2});
                            next('', false);
                        }
                    });
                }, 100);
                function resolveData(data,next) {
                    var oneArticleTpl = $('#oneArticle').text();
                    if(data.code!='0'){
                        next('', false);
                    }
                    if(data.data.records==undefined || data.data.records.length == 0){
                        next('', false);
                    }
                    var lis = [];
                    for (var i = 0,len = data.data.records.length; i < len; i++) {
                        var single = data.data.records[i];
                        single["modifiedFmt"] = single.modified.substr(0,10).split('-').join("/");
                        single["createdFmt"] = single.created.substr(0,10).split('-').join("/");
                        console.log(JSON.stringify(single));
                        var targetC = laytpl(oneArticleTpl).render({
                            o: single,
                            archiveType: archiveTypeMap
                        });
                        lis.push(targetC);
                    }
                    next(lis.join(''), data.data.current<data.data.pages);
                }
            }
            , end: "没有更多了0.0"
        });
    });
</script>
</body>
</html>
