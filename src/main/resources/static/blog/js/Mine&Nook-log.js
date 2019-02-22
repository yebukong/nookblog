/**
 *使用方法:将此js文件引入页面即可 
 *作者:叶小空
 *码云:https://git.oschina.net/yebukong
 *PS:狗头来源 https://github.com/Blankj/awesome-comment
 */
var MINE_LOG_DOG =
				"░░░░░░░░░░░░░░░░░░░░░░░░▄░░\n" + 
				"░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░\n" + 
				"░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐░\n" + 
				"░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐░\n" + 
				"░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐░\n" + 
				"░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌░\n" + 
				"░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒░\n" + 
				"░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐\n" + 
				"░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄\n" + 
				"░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒\n" + 
				"▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒\n" + 
				"=====来自单身狗的注视========";
function writeMineNookLog(){
	console.group("叶不空's 博客站点 ©"+new Date().getFullYear()+" Mine & Nook");
	//IE不支持样式
	if ((navigator.userAgent.indexOf("compatible") > -1 && navigator.userAgent.indexOf("MSIE") > -1)|| navigator.userAgent.indexOf("Trident/") > -1 ) {
        console.log(MINE_LOG_DOG);
    }else{
    	console.log("%c"+MINE_LOG_DOG, "color:green;font-size:16px;font-weight:blod");
    }
	console.groupEnd();
	console.group("GitHub:");
	console.log("https://github.com/yebukong");
	console.groupEnd();
}
writeMineNookLog();
