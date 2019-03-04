<#assign
    page={"path":"./"},
    licensesInfo={"author":"叶不空"
    ,"authorUrl":"https://yebukong.com"
    ,"articleBaseUrl":"https://yebukong.com/article/"}
/>
<!DOCTYPE html>
<html>
	<head>
		<title>关于 | Mine &amp; Nook</title>
		<!-- meta -->
		<meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />

	    <!-- css -->
		<link rel="stylesheet" href="lib/bootstrap/3.3.2/bootstrap.min.css">
		<!--<link href="https://unpkg.com/ionicons@4.4.1/dist/css/ionicons.min.css" rel="stylesheet">-->
		<link href="https://cdn.staticfile.org/ionicons/4.4.1/css/ionicons.min.css" rel="stylesheet">
		<#--<link rel="stylesheet" href="css/pace.css">-->
	    <link rel="stylesheet" href="css/custom.css">

	    <!-- js -->
	    <script src="js/cute-click.js"></script>
	    <script src="js/cute-title.js"></script>
	    <script src="js/jquery-2.1.3.min.js"></script>
	    <script src="lib/bootstrap/3.3.2/bootstrap.min.js"></script>
	    <#--<script src="js/pace.min.js"></script>-->
	    <script src="js/modernizr.custom.js"></script>
	</head>
	<body id='about'>
        <div class="container" >
             <#include "./inc/header.ftl"/>
        </div>
		<div class="content-body">
			<div class="container">
				<div class="row">
					<main class="col-md-12">
						<h1 class="page-title" >About</h1>
						<article class="post">
							<div class="entry-content clearfix">
								<figure class="img-responsive-center">
									<img class="img-responsive" src="img/me-half.jpg" alt="Developer Image">
								</figure>
								<div class="row">
									<div class="col-md-7">
										<p><strong>关于我</strong></p>
										<p>
											javaer，会点前端<br>
											典型码农
										</p>
										<p class="about-copyright">
										<strong>版权申明</strong><br>
										本站文章除特别注明外，皆为原创。<br>
										本站文章均可转载，但需保留本站相应的文章链接。<br>
										本站内容若有侵犯您的权益，请立即<a href="mailto:yebukong@qq.com;yebukong@live.com?subject=请描述你的问题" style="text-decoration:underline ">联系我</a>删除。
										</p>
									</div>
									<div class="col-md-5">
										<p><strong>关于本站</strong></p>
										<p>
											服务器：OpenResty+Tomcat<br>
											数据库：MySQL<br>
											后端：SpringBoot+FreeMarker<br>
											文章由FreeMarker配合Editor.md完成静态化<br>
											博客持续优化中： <a target="_blank" href="https://github.com/yebukong/nookblog" title="GitHub"><u >Github</u></a> | <a target="_blank" href="https://gitee.com/yebukong/nookblog"  title="码云"><u>Gitee</u></a>
										</p>
									</div>
								</div>
								<div class="height-40px"></div>
								<h3 class="title text-center" id = 'chakhsu' >留言板</h3>
                                <div id="gitalk-container"><h5 class=" text-center">（°ο°）评论插件未能完成加载！</h5></div>
								<div class="height-40px"></div>
								<h3 class="title text-center">找到我</h3>
								<ul class="social">
									<li class="link-15px"><a href="mailto:yebukong@qq.com;yebukong@live.com?body=小老弟，找我有啥事？"><span class="ion-ios-mail"></span></a></li>
									<li class="link-20px"><a href="https://github.com/yebukong" target="_blank"><span class="ion-logo-octocat"></span></a></li>
									<li class="link-15px"><a href="https://gitee.com/yebukong" target="_blank"><span class="ion-ios-planet"></span></a></li>
								</ul>
							</div>
								
						</article>
					</main>
				</div>
			</div>
		</div>
        <!--底部-->
        <#include "./inc/footer.ftl"/>
        <!-- Mobile Menu -->
        <#include "./inc/mobile-menu.ftl"/>
		<script src="js/script.js"></script>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/gitalk@1/dist/gitalk.css">
 		<script src="https://cdn.jsdelivr.net/npm/gitalk@1/dist/gitalk.min.js"></script>
 		<script type="text/javascript">
 			$(function(){
                chakhsu(document.getElementById('chakhsu'));
 				var gitalk = new Gitalk({ // gitalk的主要参数 
                    clientID: '${GitalkConfig["clientID"]}',
                    clientSecret: '${GitalkConfig["clientSecret"]}',
                    repo: '${GitalkConfig["repo"]}',
                    owner: '${GitalkConfig["owner"]}',
                    admin: '${GitalkConfig["admin"]}'.split(","),
		        	id:'about-me',
					body: '${licensesInfo["authorUrl"]}/about-me.html'
				});
		        gitalk.render('gitalk-container');
 			});
 		</script>
        <script type="text/javascript">
            var chakhsu = function(r) {
                function t() {  return b[Math.floor(Math.random() * b.length)] }

                function e() {  return String.fromCharCode(94 * Math.random() + 33) }

                function n(r) {
                    for(var n = document.createDocumentFragment(), i = 0; r > i; i++) {
                        var l = document.createElement("span");
                        l.textContent = e(), l.style.color = t(), n.appendChild(l)
                    }
                    return n
                }

                function i() {
                    var t = o[c.skillI];
                    c.step ? c.step-- : (c.step = g, c.prefixP < l.length ? (c.prefixP >= 0 && (c.text += l[c.prefixP]), c.prefixP++) : "forward" === c.direction ? c.skillP < t.length ? (c.text += t[c.skillP], c.skillP++) : c.delay ? c.delay-- : (c.direction = "backward", c.delay = a) : c.skillP > 0 ? (c.text = c.text.slice(0, -1), c.skillP--) : (c.skillI = (c.skillI + 1) % o.length, c.direction = "forward")), r.textContent = c.text, r.appendChild(n(c.prefixP < l.length ? Math.min(s, s + c.prefixP) : Math.min(s, t.length - c.skillP))), setTimeout(i, d)
                }
                var l = "",
					o = ["留言板:-)","吐槽一下吧"].map(function(r) {  return r + ""  }),
					a = 2, g = 1, s = 5, d = 75,
					b = ["rgb(110,64,170)", "rgb(150,61,179)", "rgb(191,60,175)", "rgb(228,65,157)", "rgb(254,75,131)", "rgb(255,94,99)", "rgb(255,120,71)", "rgb(251,150,51)", "rgb(226,183,47)", "rgb(198,214,60)", "rgb(175,240,91)", "rgb(127,246,88)", "rgb(82,246,103)", "rgb(48,239,130)", "rgb(29,223,163)", "rgb(26,199,194)", "rgb(35,171,216)", "rgb(54,140,225)", "rgb(76,110,219)", "rgb(96,84,200)"],
					c = {
						text: "",
						prefixP: -s,
						skillI: 0,
						skillP: 0,
						direction: "forward",
						delay: a,
						step: g
					};
                i()
            };
        </script>
	</body>
</html>
