/**
 * 登录与否动作
 * @param flag
 */
function isLoginAction(flag,msg) {
    if(flag == "T"){
       $("#ohI").attr("src",msg.hi);
       $("#ouN").html(msg.um);
       $("#login").hide();
       $("#online").show();
    }else {
        $("#online").hide();
        $("#login").show();
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
$(document).ready(function(){
    isLogin();
})