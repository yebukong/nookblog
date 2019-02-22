/**
 * jQuery.toTop.js v1.1
 * Developed by: MMK Jony
 * Fork on Github: https://github.com/mmkjony/jQuery.toTop
 **/

!function(o){"use strict";o.fn.toTop=function(t){var i=this,e=o(window),s=o("html, body"),n=o.extend({autohide:!0,offset:420,speed:500,position:!0,right:15,bottom:30},t);i.css({cursor:"pointer"}),n.autohide&&i.css("display","none"),n.position&&i.css({position:"fixed",right:n.right,bottom:n.bottom}),i.click(function(){s.animate({scrollTop:0},n.speed)}),e.scroll(function(){var o=e.scrollTop();n.autohide&&(o>n.offset?i.fadeIn(n.speed):i.fadeOut(n.speed))})}}(jQuery);
 /*直接引入css,js即可，标签动态生成*/
 jQuery(function($) {
 	var toTopA = document.createElement('a');
	var toTopImgUrl = "lib/to-top/to-top.png";
	toTopA.className = 'to-top mine-to-top';
	toTopA.title = '回到顶部';
	//toTopA.style.cssText="";//弃用css文件，图片路径根据项目目录不同需要调整，不够通用
  	$('body').append(toTopA);
 	$('.to-top').toTop({
 		autohide: true, //自动隐藏
 		offset: 200, //距离顶部多少距离时自动隐藏按钮
 		speed: 500, //滚动持续时间
 		position: true, //如果设置为 false，则需要手动在 css 中设置“按钮”的位置
 		right: 15, //右侧距离
 		bottom: 30 //底部距离
 	});
 });