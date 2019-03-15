var searchvisible = 0;

$("#search-menu").click(function(e){ 
    //This stops the page scrolling to the top on a # link.
    e.preventDefault();

    var val = $('#search-icon');
    if(val.hasClass('ion-ios-search')){
        val.addClass('ion-ios-close');
        val.removeClass('ion-ios-search');
    }else{
         val.removeClass('ion-ios-close');
        val.addClass('ion-ios-search');
    }
    
    
    if (searchvisible ===0) {
        //Search is currently hidden. Slide down and show it.
        $("#search-form").slideDown(200);
        $("#s").focus(); //Set focus on the search input field.
        searchvisible = 1; //Set search visible flag to visible.
    } 

    else {
        //Search is currently showing. Slide it back up and hide it.
        $("#search-form").slideUp(200);
        searchvisible = 0;
    }
});

/*!
 * classie - class helper functions
 * from bonzo https://github.com/ded/bonzo
 * 
 * classie.has( elem, 'my-class' ) -> true/false
 * classie.add( elem, 'my-new-class' )
 * classie.remove( elem, 'my-unwanted-class' )
 * classie.toggle( elem, 'my-class' )
 */

/*jshint browser: true, strict: true, undef: true */
/*global define: false */

( function( window ) {

'use strict';

// class helper functions from bonzo https://github.com/ded/bonzo

function classReg( className ) {
  return new RegExp("(^|\\s+)" + className + "(\\s+|$)");
}

// classList support for class management
// altho to be fair, the api sucks because it won't accept multiple classes at once
var hasClass, addClass, removeClass;

if ( 'classList' in document.documentElement ) {
  hasClass = function( elem, c ) {
    return elem.classList.contains( c );
  };
  addClass = function( elem, c ) {
    elem.classList.add( c );
  };
  removeClass = function( elem, c ) {
    elem.classList.remove( c );
  };
}
else {
  hasClass = function( elem, c ) {
    return classReg( c ).test( elem.className );
  };
  addClass = function( elem, c ) {
    if ( !hasClass( elem, c ) ) {
      elem.className = elem.className + ' ' + c;
    }
  };
  removeClass = function( elem, c ) {
    elem.className = elem.className.replace( classReg( c ), ' ' );
  };
}

function toggleClass( elem, c ) {
  var fn = hasClass( elem, c ) ? removeClass : addClass;
  fn( elem, c );
}

var classie = {
  // full names
  hasClass: hasClass,
  addClass: addClass,
  removeClass: removeClass,
  toggleClass: toggleClass,
  // short names
  has: hasClass,
  add: addClass,
  remove: removeClass,
  toggle: toggleClass
};

// transport
if ( typeof define === 'function' && define.amd ) {
  // AMD
  define( classie );
} else {
  // browser global
  window.classie = classie;
}

})( window );

(function() {
    var triggerBttn = document.getElementById( 'trigger-overlay' ),
        overlay = document.querySelector( 'div.overlay' ),
        closeBttn = overlay.querySelector( 'button.overlay-close' );
        transEndEventNames = {
            'WebkitTransition': 'webkitTransitionEnd',
            'MozTransition': 'transitionend',
            'OTransition': 'oTransitionEnd',
            'msTransition': 'MSTransitionEnd',
            'transition': 'transitionend'
        },
        transEndEventName = transEndEventNames[ Modernizr.prefixed( 'transition' ) ],
        support = { transitions : Modernizr.csstransitions };

    function toggleOverlay() {
        if( classie.has( overlay, 'open' ) ) {
            classie.remove( overlay, 'open' );
            classie.add( overlay, 'close' );
            var onEndTransitionFn = function( ev ) {
                if( support.transitions ) {
                    if( ev.propertyName !== 'visibility' ) return;
                    this.removeEventListener( transEndEventName, onEndTransitionFn );
                }
                classie.remove( overlay, 'close' );
            };
            if( support.transitions ) {
                overlay.addEventListener( transEndEventName, onEndTransitionFn );
            }
            else {
                onEndTransitionFn();
            }
            
        }
        else if( !classie.has( overlay, 'close' ) ) {
            classie.add( overlay, 'open' );
        }
    }

    triggerBttn.addEventListener( 'click', toggleOverlay );
    closeBttn.addEventListener( 'click', toggleOverlay );
    $(".overlay>nav>ul>li>a").click(
		function(e){
			//先把弹出菜单关闭再跳转
			toggleOverlay();
	});
})();
function _getJinrishici(reload){
    if (window.localStorage && !reload) {
        //如果本地存在未过期(三分钟)缓存诗词，直接展示
        var curTime = new Date().getTime();
        var time = localStorage.getItem("jinrishici_time");
        if((curTime - time) < 1000*60*3){
            var data = localStorage.getItem("jinrishici_data");
            showData(JSON.parse(data));
            return;
        }
    }
	$.ajax({
	  url: 'https://v2.jinrishici.com/one.json',
	  xhrFields: {
	     withCredentials: true
	  },
	  success: function (result, status) {

	    if(result.status=="success"){
            showData(result);
            if(window.localStorage){
                localStorage.setItem("jinrishici_time",new Date().getTime()+'');
                localStorage.setItem("jinrishici_data",JSON.stringify(result));
            }
	    }else{
            $(".jinrishici-origin").css('display','none');
            $(".jinrishici-msg").css('display','block');
	    	$(".jinrishici-msg").text("数据获取异常=￣ω￣=");
	    }
	  },
	  error:function(){
        $(".jinrishici-origin").css('display','none');
        $(".jinrishici-msg").css('display','block');
	  	$(".jinrishici-msg").text("数据获取异常_(:3」∠)_");

	  }
	});


    function showData(result) {
        $(".jinrishici-title").text(result.data.origin.title);
        $(".jinrishici-title").attr("title","百度搜索"+"《"+result.data.origin.title+"》");
        $(".jinrishici-author").text("["+result.data.origin.dynasty+"]·"+result.data.origin.author);
        var content = "";
        var limit = 10;//超10句截断
        var len = result.data.origin.content.length>limit?limit:result.data.origin.content.length;
        var words = 0;
        for(var i=0;i<len;i++){
            var linC = result.data.origin.content[i];
            words += linC.length;
            linC = linC.replace(result.data.content,"<b class='jinrishici-mark' title='灵魂诗句，Get！(‘▽′)ψ'>"+result.data.content+"</b>");
            content+="<div >"+linC+"</div>";
            if(words>200){//超200字截断
                break;
            }
        }

        if(limit<result.data.origin.content.length || words > 200){
            content+="<div title='诗词显示不完整，完整请点击标题'><small><i>[注:诗句过长，已截断……]</i></small></div>";
        }

        $(".jinrishici-origin-content").html(content);
        $(".jinrishici-origin").css('display','block');
        if(result.warning){
            $(".jinrishici-msg").css('display','block');
            $(".jinrishici-msg").text("接口异常："+result.warning);
        }else{
            $(".jinrishici-msg").css('display','none');
        }
        $(".jinrishici-title").click(function(){
            window.open("http://www.baidu.com/s?wd="+result.data.origin.title +" "+result.data.origin.author);
        });
    }
}

function _getSinceTimeStr(beginTime){
	var durTime = new Date().getTime()-beginTime;
	if(durTime<0){
		return "-_-!";
	}
	var days=Math.floor(durTime/(24*3600*1000));
	var hTime=durTime%(24*3600*1000);
	var hours=Math.floor(hTime/(3600*1000));
	var mTime=hTime%(3600*1000);
	var minutes=Math.floor(mTime/(60*1000));
	var sTime=mTime%(60*1000);
	var seconds=Math.round(sTime/1000);
	return days+"天 "+hours+"小时 "+minutes+"分钟"+seconds+"秒";
}
