<#assign
page={"path":"../"},
licensesInfo={"author":"叶不空"
,"authorUrl":"https://yebukong.com"
,"articleBaseUrl":"https://yebukong.com/article/"}
/>
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <title>${article.title} | Mine &amp; Blog</title>
    <!-- meta -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="叶不空,yebukong">
    <meta name="copyright" content="叶不空,yebukong">
    <meta Name="Keywords" Content="${articleTypeName},<#list articleTags as tag>${tag},</#list>">
    <meta Name="description" Content="${article.brief}">
    <link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon"/>
    <!-- css -->
    <link rel="stylesheet" href="../lib/bootstrap/3.3.2/bootstrap.min.css">
    <!--<link href="https://unpkg.com/ionicons@4.4.1/dist/css/ionicons.min.css" rel="stylesheet">-->
    <link href="https://cdn.staticfile.org/ionicons/4.4.1/css/ionicons.min.css" rel="stylesheet">
    <!--<link rel="stylesheet" href="css/ionicons.min.css">-->
    <#--<link rel="stylesheet" href="../css/pace.css">-->
    <link rel="stylesheet" href="../css/custom.css">
    <!--md-->
    <link rel="stylesheet" href="../lib/editor-md/css/editormd.preview.css"/>
    <!-- js -->
    <script src="../js/cute-title.js"></script>
    <script src="../js/jquery-2.1.3.min.js"></script>
    <script src="../lib/bootstrap/3.3.2/bootstrap.min.js"></script>
    <#--<script src="../js/pace.min.js"></script>-->
    <script src="../js/modernizr.custom.js"></script>
    <!--回到顶部-->
    <link rel="stylesheet" href="../lib/to-top/to-top.css" media="all">
    <script src="../lib/to-top/to-top.js"></script>
</head>

<body id="single">
<div class="container"><!--头部-->
            <#include "../inc/header.ftl"/>
</div>

<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-9">
                <article class="post post-1">
                    <header class="entry-header">
                        <h1 class="entry-title">${article.title}</h1>
                        <div class="entry-meta">
                            <span class="post-date"><a href="#">${article.created?string('yyyy/MM/dd')}</a></span>
                            <span class="post-category"><a href="#">${articleTypeName}</a></span>
                            <span class="post-category"><a href="#">${article.address}</a></span>
                            <span class="comments-link"><a href="#">~${article.wordCount}字</a></span>
                        </div>
                    </header>
                    <div class="entry-content clearfix">
                        <div id="test-editormd-view">
					               <textarea style="display:none;" id="editormd-view-textarea"></textarea>
                        </div>
                        <pre id="editormd-view-code">
${article.content}

------------
> 本文由 [${licensesInfo.author}](${licensesInfo.authorUrl} "${licensesInfo.author}") 创作，采用 [知识共享署名 4.0 国际许可协议](https://creativecommons.org/licenses/by/4.0/ "知识共享署名 4.0 国际许可协议")进行许可,转载请附上链接！
> 本文链接: [${licensesInfo.articleBaseUrl}${article.id}.html](${licensesInfo.articleBaseUrl}${article.id}.html "${article.title}")
                        </pre>
                    </div>
                </article>
            </main>
            <aside class="col-md-3">
                <div class="widget ">
                    <h3 class="widget-title">目录</h3>
                    <div id="sidebar">
                        <div class="markdown-body editormd-preview-container" id="custom-toc-container">
                            #custom-toc-container
                        </div>
                    </div>
                </div>
                <div class="widget widget-category">
                    <h3 class="widget-title">标签</h3>
                    <ul>
                             <#list articleTags as tagname>
                                 <li>
                                     <a href="../unfinish.html">${tagname}</a>
                                 </li>
                             </#list>
                    </ul>
                </div>
            </aside>
        </div>
        <div class="row">
            <main class="col-md-9">
                <div id="gitalk-container"><h5 class="text-center">（°ο°）评论插件未能完成加载！</h5></div>
            </main>
        </div>
    </div>
</div>
<!--底部-->
<#include "../inc/footer.ftl"/>
<!-- Mobile Menu -->
<#include "../inc/mobile-menu.ftl"/>
<script src="../js/script.js"></script>
<script src="../lib/editor-md/lib/marked.min.js"></script>
<script src="../lib/editor-md/lib/prettify.min.js"></script>
<script src="../lib/editor-md/lib/raphael.min.js"></script>
<script src="../lib/editor-md/lib/underscore.min.js"></script>
<script src="../lib/editor-md/lib/sequence-diagram.min.js"></script>
<script src="../lib/editor-md/lib/flowchart.min.js"></script>
<script src="../lib/editor-md/lib/jquery.flowchart.min.js"></script>
<script src="../lib/editor-md/editormd.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/gitalk@1/dist/gitalk.css">
<script src="https://cdn.jsdelivr.net/npm/gitalk@1/dist/gitalk.min.js"></script>
<script type="text/javascript">
    $(function () {
        var codeObj = $('#editormd-view-code');
        codeObj.hide();
        var textObj = $('#editormd-view-textarea');
        textObj.val(codeObj.text());
        //重定义emojis地址
        editormd.emoji = {
            path: "../lib/editor-md/plugins/emoji-dialog/emojis/",
            ext: ".png"
        };
        var testEditormdView = editormd.markdownToHTML("test-editormd-view", {
            //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
            htmlDecode       : "style,script,iframe|on*",   // you can filter tags decode
            previewCodeHighlight : true,
            toc: true,
            tocm: true,    // Using [TOCM]
            tocContainer: "#custom-toc-container", // 自定义 ToC 容器层
            //gfm             : false,
            //tocDropdown     : true,
            markdownSourceCode: false, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
            emoji: true,
            taskList: true,
            tex: true,  // 默认不解析
            flowChart: true,  // 默认不解析
            sequenceDiagram: true,  // 默认不解析
        });
        // var  metaDesc = document.getElementsByTagName('meta')['description']['content'];
        // if(!metaDesc){ metaDesc = ""; }
        var gitalk = new Gitalk({ // gitalk的主要参数
                    clientID: '${GitalkConfig["clientID"]}',
                    clientSecret: '${GitalkConfig["clientSecret"]}',
                    repo: '${GitalkConfig["repo"]}',
                    owner: '${GitalkConfig["owner"]}',
                    admin: '${GitalkConfig["admin"]}'.split(","),
                    id: '${article.id}',
                    body: '${licensesInfo.articleBaseUrl}${article.id}.html'
                }
        );
        gitalk.render('gitalk-container');
    });
</script>
</body>
</html>
