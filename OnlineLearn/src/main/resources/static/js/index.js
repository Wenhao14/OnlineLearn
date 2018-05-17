/**
 * 登录是否动作
 * @param flag
 * @param msg
 */
function isLoginAction(flag,msg) {
    if(flag == "T"){
        $("#isZx").show();
        $("#userLogin").hide();
        $("#headImg").attr("src",msg.hi);
        $("#userName").html(msg.um);
    }else {
        $("#isZx").hide();
        $("#userLogin").show();
    }
}
/**
 * 用户是否登录
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
/**
 * 退出登录
 */
function loginOut() {
    $.ajax(
        {
            type: "post",
            url: "/user/api/loginOut",
            dataType:"json",
            success : function(data) {
                if(data.rtMCode == "T"){
                    window.location.href = "/index.html";
                }else {
                    alert("退出登录出错!");
                }
            },
            error : function () {
                alert("退出登录出错!");
            }
        }
    );
}
/**
 *获取新闻
 */
function getNews() {
    $.ajax(
        {
            type: "post",
            url: "/index/api/getNews",
            dataType:"json",
            data:{
                "pageNum":0,
                "pageSize":6
            },
            success : function(data) {
                if(data.rtMCode == "T"){
                    var newsHtml;
                    var news;
                    var newsList = data.rtMData;
                    for(var i = 0;i < newsList.length;i++){
                        news = newsList[i];
                        newsHtml = "<div class=\"li\"><b style='color: #636C72;float: left'>·</b><a title=\'"+news.ntitle+"\' target=\"view_window\" href=\""+ news.nurl+"\">"+news.ntitle+"</a><span>"+news.nupdate+"</span> </div>";
                        $("#newsList").append(newsHtml);
                    }
                }
            }
        }
    );
}
$(document).ready(function () {
    isLogin();
    getNews();
})
