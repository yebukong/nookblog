/**
 * v1.0.5 写法改进-闭包
 *使用方法:将此js文件引入页面即可 ,自定义属性可以使用 MineCuteTitle.reset({...});方式修改
 *改进:叶小空
 *码云:https://git.oschina.net/yebukong
 *原作者：DIYgod
 *原作者链接：https://www.anotherhome.net/2153
 */

var MineCuteTitle = {};
(function(){
    var defaultOption = {
        changeTitle: true               /*是否改变标题*/
        ,changeFavicon: true            /*是否改变图标*/
        ,recoverTime : 2333             /*改变后恢复时间*/
        ,hiddenTitleStr : "(●_●) "       /*hidden后显示title*/
        ,focusTitleStr : "(╯▽╰) "     /*激活后显示title*/
        /*hidden后显示图标路径及类型*/
        ,hiddenFaviconHref : "data:image/x-icon;base64,AAABAAEAICAAAAEAIACoEAAAFgAAACgAAAAgAAAAQAAAAAEAIAAAAAAAABAAAMIeAADCHgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOwf8ADsH/AA7B/wAOwf8ADsH/AA7B/wAOwf8ADsH/AA7B/wAOwf8ADsH/AA7B/wAOwf8ADsH/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOwf8ADsH/AA7B/wAOwf8ADsH/Dw7B/zUOwf9eDsH/fA7B/4gOwf+IDsH/fQ7B/18Owf82DsH/EA7B/wAOwf8ADsH/AA7B/wADJjIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOwf8ADsH/AA7B/wAOwf8ADsH/Jw7B/3wOwf/HDsH/7w7B//4Owf//DsH//w7B//8Owf//DsH//w7B//AOwf/JDsH/fw7B/yoOwf8ADsH/AA7B/wAOwf8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADsH/AA7B/wAOwf8ADsH/Ew7B/3oOwf/fDsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH/4g7B/38Owf8WDsH/AA7B/wAOwf8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA7B/wAOwf8ADsH/AA7B/y8Owf+6DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B/78Owf8zDsH/AA7B/wAOwf8AAAAAAAAAAAAAAAAAAAAAAAAAAAAOwf8ADsH/AA7B/wAOwf87DsH/1Q7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B/9kOwf9ADsH/AA7B/wAOwf8AAAAAAAAAAAAAAAAAAAAAAA7B/wAOwf8ADsH/Lg7B/9UOwf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B/9kOwf8zDsH/AA7B/wADJTAAAAAAAAAAAAAOwf8ADsH/AA7B/xIOwf+5DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsL//w7C//8Owv//DsL//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B/78Owf8WDsH/AA7B/wAAAAAAAAAAAA7B/wAOwf8ADsH/dw7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7C//8Ss+v/IXaU/yJ1kf8Ssen/DsL//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B/38Owf8ADsH/AAAAAAAOwf8ADsH/AA7B/yQOwf/dDsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsP//x9+n/80MC//NDAu/yF6mf8Ow///DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH/4g7B/yoOwf8ADsH/AA7B/wAOwf8ADsH/dw7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Ow///HoWp/zMzNP8zMzP/H4Gj/w7D//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH/fw7B/wAOwf8ADsH/AA7B/w0Owf/DDsH//w7B//8Svfv/E7r6/xG9/P8Owf//DsH//w7B//8Owf//DsH//w7B//8QufT/HIqw/x2Irv8QuPL/DsL//w7B//8Owf//DsH//w7B//8OwP//Erz7/xO6+v8Rvfz/DsH//w7B//8Owf/JDsH/EA7B/wAOwf8ADsH/MA7B/+0Owf//Frf3/xyv8f8drvD/HLDx/xS5+f8Owf//DsH//w7B//8Owf//DsH//w7C//8Nw///DcP//w7C//8Owf//DsH//w7B//8Owf//DsD//xa39/8cr/H/Ha7w/xyw8f8Uufn/DsH//w7B//AOwf82DsH/AA7B/wAOwf9XDsH//RG9/P8cr/H/Ha7w/x2u8P8drvD/G7Dy/xC+/f8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8SvPv/HK/x/x2u8P8drvD/Ha7w/xux8v8Qv/3/DsH//w7B/18Owf8ADsH/AA7B/3UOwf//E7v6/x2u8P8drvD/Ha7w/x2u8P8cr/H/Eb38/w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//xO6+v8drvD/Ha7w/x2u8P8drvD/HK/x/xG9/P8Owf//DsH/fQ7B/wAOwf8ADsH/hA7B//8Rvvz/G7Dy/x2u8P8drvD/Ha7w/xqx8/8Qv/3/DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//Eb38/xyw8f8drvD/Ha7w/x2u8P8asvP/D7/+/w7B//8Owf+IDsH/AA7B/wAOwf+EDsH//w7B//8Uuvn/G7Hy/xyv8f8asvP/Erv7/w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//FLr5/xux8v8cr/H/GrLz/xK8+/8Owf//DsH//w7B/4gOwf8ADsH/AA7B/3QOwf//DsH//w7B//8Qv/3/Eb38/w+//v8Owf//DsH//w7C//8Nw///DsL//w7B//8Owf//DsH//w7B//8Owf//DsH//w7D//8Nw///DsL//w7B//8Owf//EL/9/xG9/P8Pv/7/DsH//w7B//8Owf//DsH/fA7B/wAOwf8ADsH/Vg7B//0Owf//DsH//w7B//8Owf//DsH//w7B//8Owv//E6/k/xmXw/8UqNv/DsD+/w7B//8Owf//DsH//w7B//8Pvvv/FqPU/xmYxf8RtOz/DsL//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//4Owf9eDsH/AA7B/wAOwf8vDsH/7A7B//8Owf//DsH//w7B//8Owf//DsL//xai0v8tS1X/MjU2/y9CSP8bkbr/DsL//w7B//8Owf//DsD+/x+Aov8xPED/MjY3/ypWZf8TruT/DsL//w7B//8Owf//DsH//w7B//8Owf//DsH/7w7B/zUOwf8ADsH/AA7B/wwOwf/BDsH//w7B//8Owf//DsH//w7B//8Owf//JGyF/zQwLv8zMzP/NDAv/ypWZv8Qu/f/DsH//w7C//8Ssun/LkZP/zQxMP8zMzP/NDEw/x+Bo/8Nw///DsH//w7B//8Owf//DsH//w7B//8Owf/HDsH/Dw7B/wAOwf8ADsH/AA7B/3QOwf//DsH//w7B//8Owf//DsH//w7B//8kbIX/NDAu/zMzM/80MC//KlZm/xC79/8Owf//DsL//xKy6f8uRk//NDEw/zMzM/80MTD/H4Gj/w3D//8Owf//DsH//w7B//8Owf//DsH//w7B/3wOwf8ADsH/AA7B/wAOwf8ADsH/Ig7B/9sOwf//DsH//w7B//8Owf//DsL//xai0v8tS1X/MjU2/y9CSP8bkbr/DsL//w7B//8Owf//DsD+/x+Aov8xPED/MjY3/ypWZf8TruT/DsL//w7B//8Owf//DsH//w7B//8Owf/fDsH/Jw7B/wAOwf8AAAAAAA7B/wAOwf8ADsH/cw7B//4Owf//DsH//w7B//8Owf//DsL//xOv5P8Zl8L/FKjb/w7A/v8Owf//DsH//w7B//8Owf//D777/xaj1P8ZmMX/EbTs/w7C//8Owf//DsH//w7B//8Owf//DsH//w7B/3oOwf8ADsH/AAAAAAAAAAAADsH/AA7B/wAOwf8QDsH/tA7B//8Owf//DsH//w7B//8Owf//DsL//w3D//8Owv//DsH//w7B//8Owf//DsH//w7B//8Owf//DsP//w3D//8Owv//DsH//w7B//8Owf//DsH//w7B//8Owf+6DsH/Ew7B/wAOwf8AAAAAAAAAAAAAAAAADsH/AA7B/wAOwf8qDsH/0A7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH/1Q7B/y8Owf8ADsH/AAAAAAAAAAAAAAAAAAAAAAAOwf8ADsH/AA7B/wAOwf82DsH/0A7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B/9UOwf87DsH/AA7B/wAOwf8AAAAAAAAAAAAAAAAAAAAAAAAAAAAOwf8ADsH/AA7B/wAOwf8qDsH/tA7B//4Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf+5DsH/Lg7B/wAOwf8ADsH/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOwf8ADsH/AA7B/wAOwf8QDsH/cw7B/9sOwf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf/dDsH/dw7B/xIOwf8ADsH/AA7B/wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOwf8ADsH/AA7B/wAOwf8ADsH/Ig7B/3QOwf/BDsH/7A7B//0Owf//DsH//w7B//8Owf//DsH//Q7B/+0Owf/DDsH/dw7B/yQOwf8ADsH/AA7B/wAOwf8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADsH/AA7B/wAOwf8ADsH/AA7B/wwOwf8vDsH/Vg7B/3QOwf+EDsH/hA7B/3UOwf9XDsH/MA7B/w0Owf8ADsH/AA7B/wAOwf8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA7B/wAOwf8ADsH/AA7B/wAOwf8ADsH/AA7B/wAOwf8ADsH/AA7B/wAOwf8ADsH/AA7B/wAOwf8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA/4AB//4AAD/4AAAf8AAAD+AAAAfAAAADwAAAAYAAAAGAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAAABgAAAAcAAAAPAAAAD4AAAB/AAAA/4AAAf/gAAf/+AAf8="
        ,hiddenFaviconType : "image/x-icon"
        /*激活后显示图标路径及类型*/
        ,focusFaviconHref : "data:image/x-icon;base64,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Mf8zMzH/MzMx/zMzMv8zMzP/MzMz/zI3OP8ZmMT/DcP//w7B//8Owf//DsH//w7B/18Owf8ADsH/AA7B/3UOwf//DsH//w7B//8Nw///HoOm/zQxMP8zMjH/MzIx/zMyMf8zMjH/MzIx/zMyMf8zMjH/MzIx/zMyMf8zMjH/MzIx/zMyMf8zMjH/MzIx/zMyMf8zMjH/NDAv/x9/oP8Nw///DsH//w7B//8Owf//DsH/fQ7B/wAOwf8ADsH/hA7B//8Owf//DsH//w7D//8fgaP/L0RL/y5GTv8uRk7/LkZO/y5GTv8uRk7/LkZO/y5GTv8uRk7/LkZO/y5GTv8uRk7/LkZO/y5GTv8uRk7/LkZO/y5GTv8vREv/IH2e/w7C//8Owf//DsH//w7B//8Owf+IDsH/AA7B/wAOwf+EDsH//w7B//8Owf//DsH//xC58/8Ssej/ErHp/xKx6f8Ssen/ErHp/xKx6f8Ssen/ErHp/xKx6f8Ssen/ErHp/xKx6f8Ssen/ErHp/xKx6f8Ssen/ErHp/xKx6P8QuPL/DsH//w7B//8Owf//DsH//w7B/4gOwf8ADsH/AA7B/3QOwf//DsH//w7B//8Owf//DsL//w7C//8Owv//DsL//w3D//8NxP//DcP//w7C//8Owv//DsL//w7C//8Owv//DsL//w3E//8NxP//DsP//w7C//8Owv//DsL//w7C//8Owf//DsH//w7B//8Owf//DsH/fA7B/wAOwf8ADsH/Vg7B//0Owf//DsH//w7B//8Owf//DsH//w7B//8Owv//E6/k/xmXw/8UqNv/DsD+/w7B//8Owf//DsH//w7B//8Pvvv/FqPU/xmYxf8RtOz/DsL//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//4Owf9eDsH/AA7B/wAOwf8vDsH/7A7B//8Owf//DsH//w7B//8Owf//DsL//xai0v8tS1X/MjU2/y9CSP8bkbr/DsL//w7B//8Owf//DsD+/x+Aov8xPED/MjY3/ypWZf8TruT/DsL//w7B//8Owf//DsH//w7B//8Owf//DsH/7w7B/zUOwf8ADsH/AA7B/wwOwf/BDsH//w7B//8Owf//DsH//w7B//8Owf//JGyF/zQwLv8zMzP/NDAv/ypWZv8Qu/f/DsH//w7C//8Ssun/LkZP/zQxMP8zMzP/NDEw/x+Bo/8Nw///DsH//w7B//8Owf//DsH//w7B//8Owf/HDsH/Dw7B/wAOwf8ADsH/AA7B/3QOwf//DsH//w7B//8Owf//DsH//w7B//8kbIX/NDAu/zMzM/80MC//KlZm/xC79/8Owf//DsL//xKy6f8uRk//NDEw/zMzM/80MTD/H4Gj/w3D//8Owf//DsH//w7B//8Owf//DsH//w7B/3wOwf8ADsH/AA7B/wAOwf8ADsH/Ig7B/9sOwf//DsH//w7B//8Owf//DsL//xai0v8tS1X/MjU2/y9CSP8bkbr/DsL//w7B//8Owf//DsD+/x+Aov8xPED/MjY3/ypWZf8TruT/DsL//w7B//8Owf//DsH//w7B//8Owf/fDsH/Jw7B/wAOwf8AAAAAAA7B/wAOwf8ADsH/cw7B//4Owf//DsH//w7B//8Owf//DsL//xOv5P8Zl8L/FKjb/w7A/v8Owf//DsH//w7B//8Owf//D777/xaj1P8ZmMX/EbTs/w7C//8Owf//DsH//w7B//8Owf//DsH//w7B/3oOwf8ADsH/AAAAAAAAAAAADsH/AA7B/wAOwf8QDsH/tA7B//8Owf//DsH//w7B//8Owf//DsL//w3D//8Owv//DsH//w7B//8Owf//DsH//w7B//8Owf//DsP//w3D//8Owv//DsH//w7B//8Owf//DsH//w7B//8Owf+6DsH/Ew7B/wAOwf8AAAAAAAAAAAAAAAAADsH/AA7B/wAOwf8qDsH/0A7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH/1Q7B/y8Owf8ADsH/AAAAAAAAAAAAAAAAAAAAAAAOwf8ADsH/AA7B/wAOwf82DsH/0A7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B/9UOwf87DsH/AA7B/wAOwf8AAAAAAAAAAAAAAAAAAAAAAAAAAAAOwf8ADsH/AA7B/wAOwf8qDsH/tA7B//4Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf+5DsH/Lg7B/wAOwf8ADsH/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOwf8ADsH/AA7B/wAOwf8QDsH/cw7B/9sOwf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf//DsH//w7B//8Owf/dDsH/dw7B/xIOwf8ADsH/AA7B/wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOwf8ADsH/AA7B/wAOwf8ADsH/Ig7B/3QOwf/BDsH/7A7B//0Owf//DsH//w7B//8Owf//DsH//Q7B/+0Owf/DDsH/dw7B/yQOwf8ADsH/AA7B/wAOwf8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADsH/AA7B/wAOwf8ADsH/AA7B/wwOwf8vDsH/Vg7B/3QOwf+EDsH/hA7B/3UOwf9XDsH/MA7B/w0Owf8ADsH/AA7B/wAOwf8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA7B/wAOwf8ADsH/AA7B/wAOwf8ADsH/AA7B/wAOwf8ADsH/AA7B/wAOwf8ADsH/AA7B/wAOwf8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA/4AB//4AAD/4AAAf8AAAD+AAAAfAAAADwAAAAYAAAAGAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAAABgAAAAcAAAAPAAAAD4AAAB/AAAA/4AAAf/gAAf/+AAf8="
        ,focusFaviconType : "image/x-icon"
    };
    var originFaviconHref;
    var originFaviconType;

    var _visibilitychangeHandle ;
    function _init() {
        var faviconLink = getFaviconLink();
        originFaviconHref = faviconLink.href;
        originFaviconType = faviconLink.type;
        if(!(typeof originFaviconHref == "undefined" || originFaviconHref == null || originFaviconHref == "" )){
            if(originFaviconHref.indexOf("?")>-1){
                originFaviconHref += "&t=";
            }else{
                originFaviconHref += "?t=";
            }
        }
        _visibilitychangeHandle = function(){
            _visibilitychange(defaultOption);
        }
        document.addEventListener('visibilitychange',_visibilitychangeHandle);
    }
    _init();

    var recoverTimeoutObj;
    var _visibilitychange = function(option) {
        var originTitile = _getOriginTitile(option);
        var faviconLink = getFaviconLink();
        var timestamp = new Date().getTime();

        if(document.hidden) {
            if(option.changeTitle){
                document.title = option.hiddenTitleStr + originTitile;
            }
            if(option.changeFavicon){
                faviconLink.href = option.hiddenFaviconHref ;
                faviconLink.type = option.hiddenFaviconType ;
            }
            clearTimeout(recoverTimeoutObj);
        } else {
            if(option.changeTitle){
                document.title = option.focusTitleStr + originTitile;
            }
            if(option.changeFavicon){
                faviconLink.href = option.focusFaviconHref ;
                faviconLink.type = option.focusFaviconType ;
            }
            recoverTimeoutObj = setTimeout(function() {
                if(option.changeTitle){
                    document.title = _getOriginTitile(option);
                }
                if(option.changeFavicon){
                    faviconLink.href = originFaviconHref + timestamp;
                    faviconLink.type = originFaviconType + timestamp;
                }
            }, option.recoverTime);
        }
    }

    /**获取原始title*/
    function _getOriginTitile(option) {
        var originTitile = document.title;
        var index = originTitile.indexOf(option.hiddenTitleStr);
        if(index != -1) {
            originTitile = originTitile.substring(index + option.hiddenTitleStr.length);
        } else {
            index = originTitile.indexOf(option.focusTitleStr);
            if(index != -1) {
                originTitile = originTitile.substring(index + option.focusTitleStr.length);
            }
        }
        return originTitile;
    }

    /**获取图标link*/
    function getFaviconLink() {
        //var originFavicon = document.head.querySelector("link").href;
        var faviconLink;
        var links = document.head.querySelectorAll('link');
        for(var i=0;i<links.length;i++){
            faviconLink = links[i];
            if(faviconLink.rel == "shortcut icon" || link.rel == "icon"){
                break;
            }
        }
        if(!faviconLink){//不存在，动态创建一个
            faviconLink = document.createElement("link");
            link.setAttribute("rel", "shortcut icon");
            link.setAttribute("type", "image/x-icon");
            link.setAttribute("href", "");
        }
        return faviconLink;
    }

    function  _checkOptions(options){
        for(var i in defaultOption){
            if(typeof (options[i]) == 'boolean'){
                options[i] =  Boolean(options[i]).valueOf();
            }else{
                options[i] = options[i] || defaultOption[i];
            }
        }
    }

    var _reset = function(options){
        _checkOptions(options);
        document.removeEventListener("visibilitychange",_visibilitychangeHandle,false);
        _visibilitychangeHandle = function(){
            _visibilitychange(options);
        }
        document.addEventListener('visibilitychange',_visibilitychangeHandle);
    }
    MineCuteTitle.reset = _reset;
})();
