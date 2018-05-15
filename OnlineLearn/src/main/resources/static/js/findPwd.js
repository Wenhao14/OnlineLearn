/**
 * 找回密码
 */
function findPwd() {
    var um = $("#um").val();
    var email = $("#email").val();
    if(um != undefined && um.length >= 6){
        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
        if(email != undefined && reg.test(email)){
            $.ajax(
                {
                    type: "post",
                    url: "/user/api/findPwd",
                    dataType:"json",
                    async: false,
                    data:{
                        "userName":um,
                        "email":email
                    },
                    success : function(data) {
                       alert(data.rtMsg);
                    },
                    error : function () {
                        alert("找回密码出错!");
                    }
                }
            );
        }else {
           alert("非法的邮箱!")
        }
    }else {
        alert("非法的用户名!");
    }
}
