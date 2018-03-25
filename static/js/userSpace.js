var flag;
var navs=new Array("#nav1","#nav2","#nav3","#nav4");
var navMsg = new Array("我的课堂","论坛互动","我的测评","账户管理");
function nav_action(sel){
	$("#navMsg").html(navMsg[sel]);
	for(var i = 0;i < 4;i++){
		if(sel == i){
			$(navs[i]).css({"background-color":"#008ACF"});
			continue;
		}
		$(navs[i]).css({"background-color":"#FAA046"});
	}
}
$(document).ready(function(){
	nav_action(0);
});