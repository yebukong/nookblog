<script>
    /*防止爬虫收录，心累...*/
    function _jumpBeianPage(obj) {
        var base64url = obj.getAttribute('base64url');
        window.open(window.atob(base64url));
    }
</script>
<footer id="site-footer">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="beian">
                    <span onclick="_jumpBeianPage(this)" base64url="aHR0cDovL3d3dy5taWl0YmVpYW4uZ292LmNu" style="cursor:pointer" title="我可没干坏事啊o_o?">京ICP备18052530号</span>
                    <span> | </span>
                    <span onclick="_jumpBeianPage(this)" base64url="aHR0cDovL3d3dy5iZWlhbi5nb3YuY24vcG9ydGFsL3JlY29yZFF1ZXJ5" style="cursor:pointer" title="有快递-_-?">晋公网安备 14010802080176号</span>
                </div>
                <div class="copyright">
                    <span class="upyun">
                        <a href="https://www.upyun.com/" target="_blank" ><img src="${page["path"]!""}img/upyun_logo2.png" style="height:28px;"></a>
                    </span>
                    <span class="info">
                        <a href="https://yebukong.com" target="_blank" title="叶不空's 博客站点">叶不空</a>
                        <span>&copy;${.now?string('yyyy')} Mine &amp; Nook</span>
					</span>
                </div>
            </div>
        </div>
    </div>
</footer>