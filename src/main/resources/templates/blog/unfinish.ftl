<#assign
page={"path":"./"}
/>
<!DOCTYPE html>
<html>
<head>
    <title>页面施工中... | Mine &amp; Nook</title>
    <!-- meta -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>
    <!-- css -->
    <link rel="stylesheet" href="lib/bootstrap/3.3.2/bootstrap.min.css">
    <!--<link href="https://unpkg.com/ionicons@4.4.1/dist/css/ionicons.min.css" rel="stylesheet">-->
    <link href="https://cdn.staticfile.org/ionicons/4.4.1/css/ionicons.min.css" rel="stylesheet">
    <#--<link rel="stylesheet" href="css/pace.css">-->
    <link rel="stylesheet" href="css/custom.css">
    <!-- js -->
    <script src="js/cute-title.js"></script>
    <script src="js/jquery-2.1.3.min.js"></script>
    <script src="lib/bootstrap/3.3.2/bootstrap.min.js"></script>
    <#--<script src="js/pace.min.js"></script>-->
    <script src="js/modernizr.custom.js"></script>
</head>

<body>
<div class="container">
             <#include "./inc/header.ftl"/>
</div>
<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-12 unfinish-main">
                <h3 class="page-title"><span>_(:3」∠)_</span> 页面施工中...</h3>
                <article class="post">
                    <div class="entry-content clearfix">
                        <figure class="img-responsive-center ">
                            <img id='poetry' title="王维-《相思》" class="img-responsive " src="img/poetry.jpg" alt="相思">
                        </figure>
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
<script>
    window.onload = function () {
        console.group("相思-王维");
        console.log("红豆生南国,");
        console.log("春来发几枝;");
        console.log("愿君多采撷,");
        console.log("此物最相思。");
        console.groupEnd();
    };
</script>
</body>
</html>
