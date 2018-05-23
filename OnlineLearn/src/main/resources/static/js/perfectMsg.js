/**
 * 完善信息
 */
var n;
var p;
var e;
var d;
var check = new Array(0,0,0,0);
function paramCheck(type) {
    switch (type){
        case "n":{
            n = $("#n").val();
            var han = new RegExp("^[\u4e00-\u9fa5]+$");
            if(n.length >= 2 && han.test(n)){
                check[0] = 1;
                $("#en").html("");
            }else {
                check[0] = -1;
                $("#en").html("*请输入真实姓名!");
            }
            break;
        }
        case "p":{
            p = $("#p").val();
            var phone = new RegExp("^[1][3,4,5,7,8][0-9]{9}$");
            if(phone.test(p)){
                check[1] = 1;
                $("#ep").html("");
            }else {
                check[1] = -1;
                $("#ep").html("*请正确手机号!");
            }
            break;
        }
        case "e":{
            e = $("#e").val();
            var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
            if(reg.test(e)){
                check[2] = 1;
                $("#ee").html("");
            }else {
                check[2] = -1;
                $("#ee").html("*请输入正确邮箱!");
            }
            break;
        }
        case "d":{
            d = $("#d").val();
            var han = new RegExp("!^[\u4e00-\u9fa5]+$");
            if(d.length > 3 && han.test(d)){
                check[3] = 1;
                $("#ed").html("");
            }else {
                check[3] = -1;
                $("#ed").html("*请输入详细部门!");
            }
            break;
        }
        default:{
        }
    }
}
function perfectMsg() {
    var flag  = 0;
    for (var i = 0; i < check.length;i++){
        flag += check[i];
    }
    if(flag == 4){
        flag = 0;
        $.ajax(
            {
                type: "post",
                url: "/user/api/addUMsg",
                dataType:"json",
                async: false,
                data:{
                    "name":n,
                    "email":e,
                    "phone":p,
                    "dp":d
                },
                success : function(data) {
                    $("#ebt").html(data.rtMsg);
                },
                error : function () {
                    $("#ebt").html("完善信息出错!");
                }
            }
        );
    }
}