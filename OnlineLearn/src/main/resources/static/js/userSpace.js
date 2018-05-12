var navs=new Array("#nav1","#nav2","#nav3","#nav4");
var main = new Array("#myClass","","#myTest","#myAccount");
var smenu;
function nav_action(sel){
	if(sel == 1){
		//跳到论坛
	}else{
		for(var i = 0;i < 4;i++){
			if(sel == i){
				$(navs[i]).css({"background-color":"#008ACF"});
				asNavAction(sel);
				$(main[i]).show();
			}else{
				$(navs[i]).css({"background-color":"#FAA046"});
				$(main[i]).hide();
			}
		}
	}
}
function asNavAction(sel){
	var html;
	switch(sel){
		case 0:{
			smenu = new Array("#c1","#c2");
			html = '<tr><td><span id="c1" onclick ="subMenu(0)">待学课程</span></td><td><span id="c2" onclick ="subMenu(1)">已完成课程</span></td></tr>';
			break
		}
		case 1:{
			break;
		}
		case 2:{
			smenu = new Array("#t1","#t2");
			html = '<tr><td><span id="t1" onclick ="subMenu(0)">未测评</span></td><td><span id="t2" onclick ="subMenu(1)">已测评</span></td></tr>';
			break;
		}
		case 3:{
			smenu = new Array("#m1","#m2");
			html = '<tr><td><span id="m1" onclick ="subMenu(0)">我的信息</span></td><td><span id="m2" onclick ="subMenu(1)">修改密码</span></td></tr>';
			break;
		}
		default:{
			smenu = new Array("#c1","#c2");
			html = '<tr><td><span id="c1" onclick ="subMenu(0)">待学课程</span></td><td><span id="c2" onclick ="subMenu(1)">已完成课程</span></td></tr>';
		}
	}
	$("#us_nav").empty();
	$("#us_nav").append(html);
	subMenu(0);
}
function subMenu(sel){
	for(var i = 0;i < smenu.length;i++){
		if(i == sel){
			$(smenu[i]).css({"background-color":"#0099FF","color":"white"});
            $(smenu[i]+"v").show();
		}else{
			$(smenu[i]).css({"background-color":"white","color":"#333333"});
			$(smenu[i]+"v").hide();
		}
	}
}
$(document).ready(function(){
	nav_action(0);
});