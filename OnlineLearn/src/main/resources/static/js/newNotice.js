function nn_nav(sel){
    $("#text").hide();
    $("#titles").show();
	if(sel == 1){
		$("#notice").css({"background-color":"#008ACF"})
		$("#news").css({"background-color":"#FAA046"})
		$("#nav_name").text("公告通知");
		getNotices(0);
	}else{
		$("#news").css({"background-color":"#008ACF"})
		$("#notice").css({"background-color":"#FAA046"})
		$("#nav_name").text("热点新闻");
		getNews(0);
	}
}
function getContent(nt){
	$("#nt").html(nt.nttitle);
	$("#nc").html(nt.ntcontent);
	$("#titles").hide();
	$("#text").show();
}
/**
 * 读取新闻
 */
function getNews(pn) {
    $.ajax(
        {
            type: "post",
            url: "/index/api/getNews",
            dataType:"json",
            data:{
                "pageNum":pn,
                "pageSize":6
            },
            success : function(data) {
                if(data.rtMCode == "T"){
                	$("#items").empty();
                    var newsHtml;
                    var news;
                    var newsList = data.rtMData;
                    for(var i = 0;i < newsList.length;i++){
                        news = newsList[i];
                        newsHtml = "<div class=\"title\"><a target=\"view_window\" href=\""+ news.nurl+"\"><b>·     </b>"+news.ntitle+"</a><span>"+news.nupdate+"</span></div>";
                        $("#items").append(newsHtml);
                    }
                }
            }
        }
    );
}
/**
 * 读取公告
 */
function getNotices(pn) {
    $.ajax(
        {
            type: "post",
            url: "/index/api/getNotices",
            dataType:"json",
            data:{
                "pageNum":pn,
                "pageSize":6
            },
            success : function(data) {
                if(data.rtMCode == "T"){
                    $("#items").empty();
                    var noticeHtml;
                    var nt;
                    var noticeList = data.rtMData;
                    for(var i = 0;i < noticeList.length;i++){
                        nt = noticeList[i];
						var ntJson =encodeURI(JSON.stringify(nt));
						noticeHtml = "<div class=\"title\"><a href=\"javascript:getContent("+ntJson+")\"><b>·     </b>" + nt.nttitle + "</a><span>" + nt.ntupdate + "</span></div>";
						$("#items").append(noticeHtml);
                    }
                }
            }
        }
    );
}
/**
 *分页组件
 */
function paging(){
	$("#pageView").pagination({
		currentPage: 1,
		totalPage: 2,
		callback: function(current) {
	       //做某些事情
		}
	});
}
/**
 * 登录与否动作
 * @param flag
 */
function isLoginAction(flag) {
    if(flag == "T"){
       $("#com_msg").hide();
    }else {
       $("#com_msg").show();
    }
}
/**
 * 是否登录
 */
function isLogin() {
    $.ajax(
        {
            type: "post",
            url: "/user/api/isLogin",
            dataType:"json",
            success : function(data) {
                if(data.rtMCode == "T"){
                    if(data.rtMData.um == "F") {
                        window.location.href = "";//跳转完善信息
                    }else {
                        isLoginAction("T",data.rtMData);
                    }
                }else {
                    isLoginAction("F");
                }
            },
            error : function () {
                isLoginAction("F");
            }
        }
    );
}
$(document).ready(function(){
	paging();
    isLogin();
	nn_nav(1);
})
