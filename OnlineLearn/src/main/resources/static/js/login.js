function yzmUp() {
    $("#yzmImg").attr("src","/index/api/getYzm?"+new Date());
}
function login() {
    var userName = $("#userName").val();
    var pwd = $("#pwd").val();
    var yzm = $("#yzmValue").val();
    if(userName.length < 6 || pwd.length < 6 || yzm.length != 4 ){
        alert("参数不合法！");
    }else{
        $.ajax(
            {
                type: "post",
                url: "/index/api/yzmCheck",
                dataType:"json",
                async: false,
                data:{
                    "yzm":yzm
                },
                success : function(data) {
                    if(data.rtMCode=="T"){
                        $.ajax(
                            {
                                type: "post",
                                url: "/user/api/userLogin",
                                dataType:"json",
                                async: false,
                                data:{
                                    "userName":userName,
                                    "passWord":pwd
                                },
                                success : function(data) {
                                    if(data.rtMCode=="T"){
                                        window.location.href="/index.html";
                                    }else{
                                        alert(data.rtMsg);
                                        yzmUp();
                                    }
                                },
                                error : function () {
                                    alert("登录失败！");
                                    yzmUp();
                                }
                            }
                        );
                    }else {
                        alert("验证码错误！");
                        yzmUp();
                    }
                },
                error : function () {
                    alert("验证码错误！");
                    yzmUp();
                }
            }
        );
    }
}