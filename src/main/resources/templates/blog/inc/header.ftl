<header id="site-header">
    <div class="row">
        <div class="col-md-4 col-sm-5 col-xs-8">
            <div class="logo">
                <h1><a href="${page["path"]!""}index.html"><b>Mine</b> &amp; Nook</a></h1>
            </div>
        </div><!-- col-md-4 -->
        <div class="col-md-8 col-sm-7 col-xs-4">
            <nav class="main-nav" role="navigation">
                <div class="navbar-header">
                    <button type="button" id="trigger-overlay" class="navbar-toggle">
                        <span class="ion-ios-menu"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="cl-effect-11"><a href="${page["path"]!""}index.html" data-hover="首页" title="首页">首页</a></li>
                        <li class="cl-effect-11"><a href="${page["path"]!""}timeline.html" data-hover="时间轴" title="时间轴">时间轴</a></li>
                        <li class="cl-effect-11"><a href="${page["path"]!""}unfinish.html" data-hover="实验室" title="Ye's Nook">实验室</a></li>
                        <li class="cl-effect-11"><a href="${page["path"]!""}about-me.html" data-hover="关于" title="关于">关于</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </nav>
            <div id="header-search-box">
                <a id="search-menu" href="#"><span id="search-icon" class="icon ion-md-search"></span></a>
                <div id="search-form" class="search-form">
                    <form role="search" method="get" id="searchform" action="${page["path"]!""}unfinish.html">
                        <input type="search" placeholder="Search" required>
                        <button type="submit"><span class="ion-ios-search"></span></button>
                    </form>
                </div>
            </div>
        </div><!-- col-md-8 -->
    </div>
</header>