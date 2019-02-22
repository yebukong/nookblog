<#assign
page={"path":"./"}
<#--起始时间-->
helloDate = "2018/11/01"
/>
<!DOCTYPE html>
<html>
<head>
    <title>时间轴 | Mine &amp; Nook</title>

    <!-- meta -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>
    <!-- css -->
    <link rel="stylesheet" href="lib/bootstrap/3.3.2/bootstrap.min.css">
    <!--<link href="https://unpkg.com/ionicons@4.4.1/dist/css/ionicons.min.css" rel="stylesheet">-->
    <link href="https://cdn.staticfile.org/ionicons/4.4.1/css/ionicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/pace.css">
    <link rel="stylesheet" href="css/custom.css">
    <!-- 时间线 -->
    <link rel="stylesheet" href="lib/timeline/timeline.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdn.staticfile.org/font-awesome/4.6.0/css/font-awesome.min.css">
    <!-- js -->
    <script src="js/cute-title.js"></script>
    <script src="js/jquery-2.1.3.min.js"></script>
    <script src="lib/bootstrap/3.3.2/bootstrap.min.js"></script>
    <script src="js/pace.min.js"></script>
    <script src="js/modernizr.custom.js"></script>
    <!--回到顶部-->
    <link rel="stylesheet" href="lib/to-top/to-top.css" media="all">
    <script src="lib/to-top/to-top.js"></script>
</head>
<body>
<div class="container">
		 <#include "./inc/header.ftl"/>
</div>
<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-12">
                <div class=" ">
                    <div class="timeline">
							<span class="timeline-label">
								<a href="#" class="btn btn-default" title="时间轴视图">
								  <i class="fa fa-fw fa-fire"></i>
								</a>
							</span>
                        <div class="timeline-item timeline-item-left">
                            <div class="timeline-point timeline-point-default">
                                <i class="fa fa-circle"></i>
                            </div>
                            <div class="timeline-event timeline-event-default">
                                <div class="timeline-heading ">
                                    <h4>目前共计 ${count!"0"} 篇日志。 <span class="shake">(゜-゜)つロ 干杯~</span></h4>
                                </div>
                                <div class="timeline-body">
                                    <p>("▔□▔)/:报告！确认存活[<span id="since">0.0</span>]</p>
                                </div>
                                <div class="timeline-footer">
                                    <p class="text-right">${helloDate}</p>
                                </div>
                            </div>
                        </div>
                        <span class="timeline-label">
								<span class="label label-primary">${year!""}</span>
							</span>
							<#if articles?? && (articles?size > 0) >
                                <#list articles as article>
								<div class="timeline-item timeline-item-arrow-sm">
                                    <div class="timeline-point timeline-point-blank"></div>
                                    <div class="timeline-event timeline-event-default">
                                        <div class="timeline-body">
                                            <p>
                                                <a class="timeline-fz16"
                                                   href="./article/${article.id}.html">${article.title}</a>
                                            </p>
                                        </div>
                                        <div class="timeline-footer">
                                            <p class="timeline-fz12 text-right">${article.created?string('yyyy/MM/dd HH:mm:ss')}</p>
                                        </div>
                                    </div>
                                </div>
                                </#list>
                            </#if>
                        <span class="timeline-label">
								<span class="label label-primary">go</span>
							</span>
                        <span class="timeline-label xx-one">
								<div class="btn-group ">
									<#list years as iYear>
									<a href="./timeline.html?year=${iYear}" class="btn btn-default"
                                       title="${iYear}">${iYear}</a>
                                    </#list>
                                </div>
							</span>
                    </div>
                </div>
            </main>
        </div>
    </div>
</div>
<!--底部-->
	<#include "./inc/footer.ftl"/>
<!-- Mobile Menu -->
	<#include "./inc/mobile-menu.ftl"/>
<script src="js/script.js"></script>
<script>
    $(function () {
        var beginTime = new Date("${helloDate}").getTime();
        $("#since").text(_getSinceTimeStr(beginTime));
        setInterval(function () {
            $("#since").text(_getSinceTimeStr(beginTime));
        }, 1000);
    });
</script>
</body>
</html>
