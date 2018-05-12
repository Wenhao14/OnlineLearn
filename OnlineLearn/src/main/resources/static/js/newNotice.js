function nn_nav(sel){
	if(sel == 1){
		$("#notice").css({"background-color":"#008ACF"})
		$("#news").css({"background-color":"#FAA046"})
		$("#nav_name").text("公告通知");
		getNotices();
	}else{
		$("#news").css({"background-color":"#008ACF"})
		$("#notice").css({"background-color":"#FAA046"})
		$("#nav_name").text("热点新闻");
		getNews();
	}
}
function getContent(id){
	$("#titles").hide();
	$("#text").show();
}
function getNews(){
	
}
function getNotices(){
	
}
function paging(){
	$("#pageView").pagination({
		currentPage: 1,
		totalPage: 2,
		callback: function(current) {
	       //做某些事情
		}
	});
}
$(document).ready(function(){
	paging();
	nn_nav(1);
})
