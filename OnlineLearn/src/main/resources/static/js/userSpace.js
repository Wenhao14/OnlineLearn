var navs=new Array("#nav1","#nav2","#nav3","#nav4");
var main = new Array("#myClass","","#myTest","#myAccount");
var smenu;
function clearnAlterPwd() {
    $("#oldPwd").val("");
    $("#newPwd").val("");
    $("#reNewPwd").val("");
    $("#yzm").val("");
}
function clearnWarn() {
    $("#yzmWarn").html("");
    $("#reNewPwdWarn").html("");
    $("#newPwdWarn").html("");
    $("#oldPwdWarn").html("");
}
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
			html = '<tr><td><span id="m1" onclick ="subMenu(0),getUMsg(m)">我的信息</span></td><td><span id="m2" onclick ="subMenu(1),yzmUp(),clearnWarn(),clearnAlterPwd()">修改密码</span></td></tr>';
			getUMsg("m");
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
function yzmUp() {
    $("#yzmImg").attr("src","http://127.0.0.1:8888/index/api/getYzm?"+new Date());
}
function getUMsg(flag) {
    $.ajax(
        {
            type: "post",
            url: "/user/api/getMyMsg",
            dataType:"json",
            success : function(data) {
                if(data.rtMCode == "T"){
                    var msg = data.rtMData;
                    if(flag == "h"){
                       $("#up-img-touch").attr("src",msg.user.headimg);
                       $("#uName").html(msg.userMsg.uname);
                    }else {
                        $("#m").html(msg.userMsg.umail);
                        $("#p").html(msg.userMsg.uphone);
                        $("#d").html(msg.userMsg.usection);
                        $("#g").html(msg.userMsg.ugoal);
                        $("#r").html(msg.userMsg.urank);
                        $("#u").html(msg.user.username);
                        $("#c").html(msg.user.grade);
                    }
                }else {
                   alert(data.rtMsg);
                }
            },
            error : function () {
              alert("内部错误!");
            }
        }
    );
}
/**
 * 检查验证码
 */
function alterPwd() {
    var yzm = $("#yzm").val();
    if(yzm == undefined ||yzm.length != 4){
          $("#yzmWarn").html("*请输入正确的验证码!");
	}else {
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
                        checkPwd();
                    }else {
                        $("#yzmWarn").html("*验证码错误!");
                        yzmUp();
                    }
                },
                error : function () {
                    $("#yzmWarn").html("*验证码错误!");
                    yzmUp();
                }
            }
        );
	}
}
/**
 * 检查密码
 */
function checkPwd() {
	var oldPwd = $("#oldPwd").val();
	if(oldPwd == undefined || oldPwd.length < 6){
        $("#oldPwdWarn").html("*请输入正确的密码!");
	}else {
        $.ajax(
            {
                type: "post",
                url: "/user/api/checkPwd",
                dataType:"json",
                async: false,
                data:{
                    "pwd":oldPwd
                },
                success : function(data) {
                    if(data.rtMCode=="T"){
                        updatePwd();
                    }else {
                        $("#oldPwdWarn").html("*原始密码错误!");
                        yzmUp();
                    }
                },
                error : function () {
                    $("#oldPwdWarn").html("*原始密码错误!");
                    yzmUp();
                }
            }
        );
	}
}
function updatePwd() {
	var newPwd = $("#newPwd").val();
	var reNewPwd = $("#reNewPwd").val();
	if(newPwd == undefined || newPwd.length < 6){
        $("#newPwdWarn").html("*请按要求重新输入新密码!");
	}else {
		if(reNewPwd == undefined || reNewPwd.length < 6){
            $("#reNewPwdWarn").html("*请按要求重新输入新密码!");
		}else {
			if(newPwd == reNewPwd){
                $.ajax(
                    {
                        type: "post",
                        url: "/user/api/alterPwd",
                        dataType:"json",
                        async: false,
                        data:{
                            "newPwd":newPwd
                        },
                        success : function(data) {
                            alert("密码修改成功!")
                        },
                        error : function () {
                            alert("密码修改失败！");
                            yzmUp();
                        }
                    }
                );
			}else {
                $("#reNewPwdWarn").html("*两次密码输入不一致!");
			}
		}
	}
}
$(document).ready(function(){
    getUMsg("h");
	nav_action(0);
});