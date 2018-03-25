var flag;
var navs=new Array("#nav1","#nav2","#nav3","#nav4","#nav5","#nav6");
function nav_action(sel){
	if(sel == 1){
		$("#login").hide();
	}else{
		$("#login").show();
	}
	$(navs[sel-1]).css({"background-color":"#008ACF","color":"white"});
}
function navUrl(sel){
	switch(sel){
		case 2:{
			window.location.href="/static/page/front/news_notice.html";
			break;
		}
		case 3:{
			window.location.href="/static/index.html"
			break;
		}
		case 4:{
			window.location.href="/static/page/front/re_course.html"
			break;
		}
		case 5:{
            window.location.href="/static/page/front/hot_course.html"
			break;
		}
		case 6:{
			window.location.href="/static/page/front/local_resource_base.html"
			break;
		}
		default:{
			window.location.href="/static/index.html"
			break;
		}
	}
}
function init(sel){
	flag = sel-1;
	nav_action(sel);
}
function over(id){
	if(id != flag){
	    $(navs[id]).css({"background-color":"#008ACF","color":"white"})	
	}
}
function out(id){
	if(id != flag){
		$(navs[id]).css({"background-color":"#EBEBEB","color":"black"})
	}
}
$(document).ready(function(){
	$("#nav1").mousemove(function(){
		over(0);
	})
	$("#nav1").mouseout(function(){
		out(0);
	})
	$("#nav2").mousemove(function(){
		over(1);
	})
	$("#nav2").mouseout(function(){
		out(1);
	})
	$("#nav3").mousemove(function(){
		over(2);
	})
	$("#nav3").mouseout(function(){
		out(2);
	})
	$("#nav4").mousemove(function(){
		over(3);
	})
	$("#nav4").mouseout(function(){
		out(3);
	})
	$("#nav5").mousemove(function(){
		over(4);
	})
	$("#nav5").mouseout(function(){
		out(4);
	})
	$("#nav6").mousemove(function(){
		over(5);
	})
	$("#nav6").mouseout(function(){
		out(5);
	})
});