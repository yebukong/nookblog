<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mine &amp; Nook - CMS</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="./lib/jq/v3.2.1/jquery.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js" charset="utf-8"></script>

</head>

<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a href="./index">Mine &amp; Nook - CMS</a></div>
    <ul class="layui-nav left fast-add x-layui-nav x-un-select" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;"><i class="iconfont">&#xe696;</i></a>
            <dl class="layui-nav-child">
                <dd><a id='reloadCmsCache'><i class="layui-icon layui-icon-senior"></i>更新CMS缓存</a></dd>
                <dd><a id='reloadFrontCache'><i class="layui-icon layui-icon-release"></i>更新前台缓存</a></dd>
            </dl>
        </li>
    </ul>
    <ul class="layui-nav right x-layui-nav x-un-select" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">管理小空空</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <#--<dd><a onclick="x_admin_show('个人信息','http://www.baidu.com')">个人信息</a></dd>-->
                <#--<dd><a onclick="x_admin_show('切换帐号','http://www.baidu.com')">切换帐号</a></dd>-->
                <dd><a onclick="x_admin_full('新增文章','article-add.html')">新增文章</a></dd>
                <dd><a href="logout">退出</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index"><a href="https://yebukong.com/" target="_blank"><i title="前台"
                                                                                               class="layui-icon layui-icon-release"></i></a>
        </li>
    </ul>

</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav x-un-select">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a _href="article-list.html"><i class="iconfont">&#xe705;</i><cite>文章</cite></a>
            </li>
            <li>
                <a _href="code-list.html"><i class="iconfont">&#xe842;</i><cite>代码项</cite></a>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b4;</i>
                    <cite>Other</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="jobs-list.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>Task</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="unicode.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>IconFont图标</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="layuicode.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>Layui图标</cite>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content" id="pageContent">
    <div class="layui-tab tab " lay-filter="xbs_tab" lay-allowclose="false">
        <ul id='xbs_tab' class="layui-tab-title x-un-select">
            <li class="home x-home layui-this">
                <!--其他按钮-->
                <span class="x-home-menubar">
            		<span><i id='btn-turn-side' title="切换侧边栏" class="layui-icon layui-icon-spread-left"></i></span>
                    <!--<span><i id='btn-reload-curtab' title="切换" class="layui-icon layui-icon-refresh"></i></span>-->
            	</span>
                <i class="layui-icon">&#xe68e;</i>
            </li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='./welcome' frameborder="0" scrolling="yes" class="x-iframe" id='x-iframe'></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright"><a href="http://www.yebukong.com" target="_blank" title="叶不空's 博客站点">叶不空</a> ©2018 Mine &amp;
        Nook - CMS
    </div>
</div>
<!-- 底部结束 -->
<script>
    layui.config({
        base: './lib/'
    }).extend({ //设定模块别名
        tabrightmenu: 'tabrightmenu/tabrightmenu'
    });
    layui.use(['element', 'tabrightmenu'], function () {
        var element = layui.element;
        var tabrightmenu = layui.tabrightmenu;
        tabrightmenu.render({
            container: '#pageContent'
            , filter: 'xbs_tab'
            , isClickMidCloseTab: true
            , navArr: [
                {eventName: 'refresh', title: '刷新当前标签页', icon: 'layui-icon-refresh'},
                //	{eventName: 'line'},// 创建分割线
                {eventName: 'closethis', title: '关闭当前标签页', icon: 'layui-icon-close'},
                {eventName: 'closeothers', title: '关闭其它标签页', icon: 'layui-icon-unlink'},
                {eventName: 'closeall', title: '关闭所有标签页', icon: 'layui-icon-delete'},
            ]
        });
    });
</script>
<script>
    /*阻止嵌入其他页面*/
    if (window != top) top.location.href = location.href;
</script>
</body>
</html>