/**
 * ��¼�����
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
 * �Ƿ��¼
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
                        window.location.href = "";//��ת������Ϣ
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
 * �˳���¼
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
                    alert("�˳���¼����!");
                }
            },
            error : function () {
                alert("�˳���¼����!");
            }
        }
    );
}
$(document).ready(function(){
    isLogin();
})