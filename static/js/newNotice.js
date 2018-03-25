function nn_nav(sel){
	if(sel == 1){
		$("#notice").css({"background-color":"#008ACF"})
		$("#news").css({"background-color":"#FAA046"})
		$("#nav_name").text("公告通知");
		getNotices();
	}else{
		$("#news").css({"background-color":"#008ACF"})
		$("#notice").css({"background-color":"#FAA046"})
		$("#nav_name").text("红色新闻");
		getNews();
	}
}
function getContent(id){
	$("#titles").hide();
	$("#content").show();
}
function getNews(){
	
}
function getNotices(){
	
}
$(document).ready(function(){
	nn_nav(1);
})
