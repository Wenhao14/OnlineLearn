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
			getUnTps(0);
			html = '<tr><td><span id="t1" onclick ="subMenu(0),getUnTps(0)">未测评</span></td><td><span id="t2" onclick ="subMenu(1),getEnTps(0)">已测评</span></td></tr>';
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
    $("#yzmImg").attr("src","/index/api/getYzm?"+new Date());
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
/**
 * 日期格式化工具
 * @param fmt
 * @returns {*}
 * @constructor
 */
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
/**
 *获取未作答试题
 */
function getUnTps(pn) {
    $("#t1v_tab").empty();
    $.ajax(
        {
            type: "post",
            url: "/tp/api/getUnTps",
            dataType:"json",
            async: false,
            data:{
                "pageNum":pn,
                "pageSize":6
            },
            success : function(data) {
                if(data.rtMCode == "T"){
                    var tps = data.rtMData;
                    var len = tps.length;
                    if(len > 0){
                        var html = "<tr class=\"tthead\"><th>科目</th><th>发布日期</th><th>结束日期</th><th>作答</th></tr>";
                        $("#t1v_tab").append(html);
                        for(var i = 0;i < len;i++){
                            var pssdate = new Date(tps[i].tppassdate).Format("yyyy-MM-dd");
                            html = "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\"> <td>"+tps[i].tpname+"</td><td>"+tps[i].tpupdate+"</td><td>"+pssdate+"</td><td><a href=\"testPaper.html?tpId="+tps[i].tpid+"\"><img class=\"tpdo\" src=\"/img/tpdo.png\"/></a></td></tr>";
                            $("#t1v_tab").append(html);
                        }
                    }else {
                        $("#t1v_tab").append("<tr><td>暂无数据!</td></tr>")
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
 * 获取已作答试题
 * @param pn
 */
function getEnTps(pn) {
    $("#t2v_tab").empty();
    $.ajax(
        {
            type: "post",
            url: "/tp/api/getEnTps",
            dataType:"json",
            async: false,
            data:{
                "pageNum":pn,
                "pageSize":6
            },
            success : function(data) {
                if(data.rtMCode == "T"){
                    var tps = data.rtMData;
                    var len = tps.length;
                    if(len > 0){
                        var html = "<tr class=\"tthead\"> <th>科目</th> <th>完成时间</th> <th>成绩</th> </tr>";
                        $("#t2v_tab").append(html);
                        for(var i = 0;i < len;i++){
                            html = "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\"> <td>"+tps[i][1]+"</td><td>"+tps[i][3]+"</td><td>"+tps[i][4]+"</td> </tr>";
                            $("#t2v_tab").append(html);
                        }
                    }else {
                        $("#t2v_tab").append("<tr><td>暂无数据!</td></tr>")
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
function alterUMAction(type,flag) {
    if(type == 'm'){
        if(flag == 1){
            $("#am").val('');
            $("#m").hide();
            $("#ama1").hide();
            $("#ama2").show();
            $("#ama3").show();
            $("#am").show();
        }else {
            $("#m").html($("#am").val());
            $("#m").show();
            $("#ama1").show();
            $("#ama2").hide();
            $("#ama3").hide();
            $("#am").hide();
        }
    }else if(type == 'p'){
        if(flag == 1){
            $("#ap").val('');
            $("#p").hide();
            $("#apa1").hide();
            $("#apa2").show();
            $("#apa3").show();
            $("#ap").show();
        }else {
            $("#p").html($("#ap").val());
            $("#p").show();
            $("#apa1").show();
            $("#apa2").hide();
            $("#apa3").hide();
            $("#ap").hide();
        }
    }else {
       alert("状态异常!");
    }
}
function cencel(type) {
    if(type == 1){
        $("#am").val('');
        $("#m").show();
        $("#ama1").show();
        $("#ama2").hide();
        $("#ama3").hide();
        $("#am").hide();
    }else if(type == 2){
        $("#ap").val('');
        $("#p").show();
        $("#apa1").show();
        $("#apa2").hide();
        $("#apa3").hide();
        $("#ap").hide();
    }else {
        alert("状态异常!");
    }
}
function alterUMsg(type) {
    var msg;
   if(type == 'm'){
      msg = $("#am").val();
      //手机号验证
   }else if(type == 'p'){
       msg = $("#ap").val();
       //邮箱验证
   }else {
       alert("状态异常!");
       return;
   }
    $.ajax(
        {
            type: "post",
            url: "/user/api/alterUMsg",
            dataType:"json",
            async: false,
            data:{
                "msg":msg,
                "type":type
            },
            success : function(data) {
                if(data.rtMCode == "T"){
                    alterUMAction(type,2);
                }
                alert(data.rtMsg);
            },
            error : function () {
                alert("内部错误!");
            }
        }
    );

}
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){
            return pair[1];
        }
    }
    return "F";
}
function urlPapamDeal(type) {
    if(type == "altPwd"){
        nav_action(3);
        subMenu(1);
        yzmUp();
    }
}
$(document).ready(function(){
    getUMsg("h");
    var type = getQueryVariable("type");
    if(type != "F"){
        urlPapamDeal(type);
    }else {
        nav_action(0);
    }
});