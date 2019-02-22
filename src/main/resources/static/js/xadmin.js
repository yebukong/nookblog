$(function () {
    //加载弹出层
    layui.use(['form','element'],
    function() {
        layer = layui.layer;
        element = layui.element;
    });

  //触发事件
  var tab = {
		tabAdd: function(title, url, id) {
			//新增一个Tab项
			element.tabAdd('xbs_tab', {
				title: title,
				content: '<iframe tab-id="' + id + '" frameborder="0" src="' + url + '" scrolling="yes" class="x-iframe"></iframe>',
				id: id
			})
		},
		tabDelete: function(othis) {
			//删除指定Tab项
			element.tabDelete('xbs_tab', '44');
			othis.addClass('layui-btn-disabled');
		},
		tabChange: function(id) {
			//切换到指定Tab项
			element.tabChange('xbs_tab', id);
		},
		tabDelCur: function() {
			//删除当前
			var curID = $('.layui-tab>.layui-tab-title>.layui-this').attr("lay-id");
			element.tabDelete('xbs_tab', curID);
		},
		tabDelOther: function() {
			//删除其他
			var lis = $('.layui-tab>.layui-tab-title>li').not('.layui-this');
			if(lis) {
				for(var i = 0; i < lis.length; i++) {
					element.tabDelete('xbs_tab', $(lis[i]).attr("lay-id"));
				}
			}
		},
		tabDelAll: function() {
			var lis = $('.layui-tab>.layui-tab-title>li');
			if(lis) {
				for(var i = 0; i < lis.length; i++) {
					element.tabDelete('xbs_tab', $(lis[i]).attr("lay-id"));
				}
			}
		}
	};


    tableCheck = {
        init:function  () {
            $(".layui-form-checkbox").click(function(event) {
                if($(this).hasClass('layui-form-checked')){
                    $(this).removeClass('layui-form-checked');
                    if($(this).hasClass('header')){
                        $(".layui-form-checkbox").removeClass('layui-form-checked');
                    }
                }else{
                    $(this).addClass('layui-form-checked');
                    if($(this).hasClass('header')){
                        $(".layui-form-checkbox").addClass('layui-form-checked');
                    }
                }
                
            });
        },
        getData:function  () {
            var obj = $(".layui-form-checked").not('.header');
            var arr=[];
            obj.each(function(index, el) {
                arr.push(obj.eq(index).attr('data-id'));
            });
            return arr;
        }
    }

    //开启表格多选
    tableCheck.init();
      

    $('#btn-turn-side').click(function(event) {
        if($('.left-nav').css('left')=='0px'){
            $('.left-nav').animate({left: '-221px'}, 100);
            $('.page-content').animate({left: '0px'}, 100);
            $('.page-content-bg').hide();
            $(this).removeClass('layui-icon-spread-left');
            $(this).addClass('layui-icon-shrink-right');
        }else{
            $('.left-nav').animate({left: '0px'}, 100);
            $('.page-content').animate({left: '221px'}, 100);
            if($(window).width()<768){
                $('.page-content-bg').show();
            }
            $(this).addClass('layui-icon-spread-left');
            $(this).removeClass('layui-icon-shrink-right');
        }
        return false;
        //阻止父元素事件触发
		//return false做了三件事：
		//stopPropagation()阻止事件传播
		//preventDefault()禁止浏览器默认行为
		//立即结束当前函数并且返回
    });
	//重载当前tab标签页内容
	$('#btn-reload-curtab').click(function(event) {
		//获取layui-show    $('#iframe').attr('src', $('#iframe').attr('src'));
		var curIframe = $(".layui-tab>.layui-tab-content>.layui-show>iframe");
		curIframe.attr('src', curIframe.attr('src'));
		return false;
	});
	$('#btn-resize').click(function(event) {
		//获取layui-show    $('#iframe').attr('src', $('#iframe').attr('src'));
		var curIframe = $(".layui-tab>.layui-tab-content>.layui-show>iframe");
		curIframe.attr('src', curIframe.attr('src'));
		return false;
	});
    $('.page-content-bg').click(function(event) {
        $('.left-nav').animate({left: '-221px'}, 100);
        $('.page-content').animate({left: '0px'}, 100);
       	$('#btn-turn-side').removeClass('layui-icon-spread-left');
        $('#btn-turn-side').addClass('layui-icon-shrink-right');
        $(this).hide();
    });

    $('.layui-tab-close').click(function(event) {
        $('.layui-tab-title li').eq(0).find('i').remove();
    });

   $("tbody.x-cate tr[fid!='0']").hide();
    // 栏目多级显示效果
    $('.x-show').click(function () {
        if($(this).attr('status')=='true'){
            $(this).html('&#xe625;'); 
            $(this).attr('status','false');
            cateId = $(this).parents('tr').attr('cate-id');
            $("tbody tr[fid="+cateId+"]").show();
       }else{
            cateIds = [];
            $(this).html('&#xe623;');
            $(this).attr('status','true');
            cateId = $(this).parents('tr').attr('cate-id');
            getCateId(cateId);
            for (var i in cateIds) {
                $("tbody tr[cate-id="+cateIds[i]+"]").hide().find('.x-show').html('&#xe623;').attr('status','true');
            }
       }
    })

    //左侧菜单效果
    // $('#content').bind("click",function(event){
    $('.left-nav #nav li').click(function (event) {

        if($(this).children('.sub-menu').length){
            if($(this).hasClass('open')){
                $(this).removeClass('open');
                $(this).find('.nav_right').html('&#xe697;');
                $(this).children('.sub-menu').stop().slideUp();
                $(this).siblings().children('.sub-menu').slideUp();
            }else{
                $(this).addClass('open');
                $(this).children('a').find('.nav_right').html('&#xe6a6;');
                $(this).children('.sub-menu').stop().slideDown();
                $(this).siblings().children('.sub-menu').stop().slideUp();
                $(this).siblings().find('.nav_right').html('&#xe697;');
                $(this).siblings().removeClass('open');
            }
        }else{
            var url = $(this).children('a').attr('_href');
            var title = $(this).find('cite').html();
            var index  = $('.left-nav #nav li').index($(this));

            for (var i = 0; i <$('.x-iframe').length; i++) {
                if($('.x-iframe').eq(i).attr('tab-id')==index+1){
                    tab.tabChange(index+1);
                    event.stopPropagation();
                    return;
                }
            };
            
            tab.tabAdd(title,url,index+1);
            tab.tabChange(index+1);
        }
        event.stopPropagation();
    });
    x_admin_tab = tab;
});

var x_admin_tab = null;

var cateIds = [];
function getCateId(cateId) {
    $("tbody tr[fid="+cateId+"]").each(function(index, el) {
        id = $(el).attr('cate-id');
        cateIds.push(id);
        getCateId(id);
    });
}

/*弹出层*/
/*
    参数解释：
    title   标题
    url     请求的url
    id      需要操作的数据id
    w       弹出层宽度（缺省调默认值）
    h       弹出层高度（缺省调默认值）
*/
function x_admin_show(title,url,w,h){
    if (title == null || title == '') {
        title=false;
    };
    if (url == null || url == '') {
        url="404.html";
    };
    if (w == null || w == '') {
        w=($(window).width()*0.9);
    };
    if (h == null || h == '') {
        h=($(window).height() - 50);
    };
    var x = layer.open({
        type: 2,
        area: [w+'px', h +'px'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: true,
        shade:0.4,
        title: title,
        content: url
    });
}
//全屏展开
function x_admin_full(title,url){
	 var w=$(window).width();
	 var h=$(window).height();
    //全屏
	var index = layer.open({
	  type: 2,
	  fix: true,
	  content: url,
	  area: [w+'px', h +'px'],
	  title: title,
	  cancel: function(openIndex, layero){
	    layer.confirm('确认关闭?', function(confIndex){
	  		layer.close(confIndex);
	  		layer.close(openIndex);
		});
		return false; 
	  }  
	});
}

/*关闭弹出框口*/
function x_admin_close(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

//判断字符是否为空的方法
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}

function isTrimEmpty(obj){
    if(isEmpty(obj)){
    	 return true;
    }else{
    	if(obj.trim().length<1){
    		 return true;
    	}else{
    		 return false;
    	}
    }
}
var MineUtil = (function(){
  var _config = {
  	baseUrl : 'http://localhost:10086/'
  }
  var defaultOption = {
    url: null
    ,params:{}
    ,title: false
    ,w : null
    ,h: null
    ,success: null
    ,yes: null 
    ,cancel: null 
    ,resultFor:null //将layer.open 页面返回值临时存放的标签
    ,resultAttrName:'x-layer-result' //存放的属性
    ,result :false
    ,appendTimestamp : true
  };
  var defaultAjaxOption = {
    url: false
    ,type: 'GET'
    ,dataType : "JSON"
    ,data: null
    ,success: false
    ,error: false
    ,async : true
    ,offset: '50%'
    ,showDoingMsg : true 
    ,doingMsg: '处理中...'
  }
  
  var Mine_Data ={
  	 config:_config
  	,openPage:_open
  	,parseParams:_parseParams
  	,getParams:_getParams
  	,ajax:_ajax
  	,deepCopy:_deepCopy
  	,mergeMap:_mergeMap
  };
  function _mergeMap() {
  	var result={};
  	for (var arg in arguments) {
  		if(typeof arg==='object' ){
			for (var key in arg) {
	      		result[key] = typeof source[key]==='object'? deepCoyp(source[key]): source[key];
	  		} 
  		}
  	}
    return result; 
  }
  function _deepCopy(source) { 
    var result={};
    for (var key in source) {
      result[key] = typeof source[key]==='object'? deepCoyp(source[key]): source[key];
    } 
    return result; 
  }
  function _open(options){
    _checkOptions(options,defaultAjaxOption);
    if(options.appendTimestamp){//添加时间戳,防止缓存
    	var timestamp = new Date().getTime();
  		options.params['t']=timestamp; 
    }
    if(!options.w){
    	 options.w=($(window).width()*0.9);
    }else if('full'==options.w){
    	options.w=$(window).width();
    }
    if(!options.h){
    	 options.h=($(window).height()-50);
    }else if('full'==options.h){
    	options.h=$(window).height();
    }
    if(options.url && !$.isEmptyObject(options.params)){
    	options.url = options.url + '?'+_parseParams(options.params);
    	var xx = _getParams(options.url);
    	console.log(xx);
    }
    if(options.result && options.resultFor && $(options.resultFor)){
    	$(options.resultFor).attr('x-layer-result',null);//清除旧值
    }
    layer.open({
        type: 2
        ,area: [options.w+'px', options.h +'px']
        ,fix: false //不固定
        ,maxmin: true
        ,shadeClose: true
        ,shade:0.4
        ,title: options.title
        ,content: options.url || '内容未定义'
        ,success: options.success
	    ,yes: options.yes 
	    ,cancel: options.cancel
	    ,end :function(){
	    	options.result && options.result($(options.resultFor).attr('x-layer-result'));
	    }
    });
  }
  function _getParams(url) {
	try {
		var index = url.indexOf('?');
		url = url.match(/\?([^#]+)/)[1];
		var obj = {},
			arr = url.split('&');
		for(var i = 0; i < arr.length; i++) {
			var subArr = arr[i].split('=');
			obj[decodeURIComponent(subArr[0])] = decodeURIComponent(subArr[1]);
		}
		return obj;
	} catch(err) {
		return null;
	}
  }
  function _parseParams(data) {
  	try {
  		var tempArr = [];
  		for(var i in data) {
  			var key = encodeURIComponent(i);
  			var value = data[i];
  			value = (typeof value == 'undefined' || value==null) ? '' : value;
  			if(typeof value =='object'){
  				value = encodeURIComponent(JSON.stringify(value));
  			}else{
  				value = encodeURIComponent(value);
  			}
  			tempArr.push(key + '=' + value);
  		}
  		var urlParamsStr = tempArr.join('&');
  		return urlParamsStr;
  	} catch(err) {
  		return '';
  	}
  }
  
  /**
   * 封装ajax
   */
  function _ajax(options) {
  	_checkOptions(options,defaultAjaxOption);
  	var doingMsgIndex;
    if(options.showDoingMsg){
    	doingMsgIndex = layer.msg(options.doingMsg,{icon: 16 , offset: options.offset,time:0 });
    }
    $.ajax({
		url: options.url,
		type: options.type,
		dataType: options.dataType,
		data: options.data,
		async: options.async,
		success: function(data, status) {
			if(doingMsgIndex){ layer.close(doingMsgIndex); }
			options.success && options.success(data,status,options);
		},
		error: function (jqXHR, textStatus, errorThrown) {
			if(doingMsgIndex){ layer.close(doingMsgIndex); }
			if(options.error){
				options.error(data,status,options);
			}else{
				var errmsg = '['+jqXHR.status+':'+textStatus+']请求错误!';
				layer.msg(errmsg, { offset: options.offset, anim: 6, icon: 2});
			}
		}
	});
  }
  /**
   * 校验参数信息
   */
  function  _checkOptions(options,defaultOption){
  	for(var i in defaultAjaxOption){
  	  if(typeof (options[i]) == 'boolean'){
  	  	options[i] = Boolean(options[i]).valueOf();
  	  }else{
  	  	 options[i] = options[i] || defaultAjaxOption[i];
  	  }
    }
  }
  
  return Mine_Data;
})();
var CMS = (function(){
    var _baseUrl = "http://localhost:10086/minenook/";
    var _base = "/minenook/";
    var CMS_Data ={
        base : _base
        ,baseUrl : _baseUrl
    };
    return CMS_Data;
})();