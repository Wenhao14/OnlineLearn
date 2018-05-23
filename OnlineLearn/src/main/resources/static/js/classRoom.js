var loginFlag = 0;
var isSel = 0;
var courseId;
var vNum = 0;
/**
 * 登录是否动作
 * @param flag
 * @param msg
 */
function isLoginAction(flag,msg) {
    if(flag == "T"){
        $("#tip").html("欢迎["+msg.um+"]——友情提示：请勿直接关闭浏览器，以免数据丢失!");
    }else {
        $("#tip").html("您未登陆，学习数据不会记录!");
    }
}
function learn(flag) {
    if(flag == 1){
        $("#vTxt").hide();
        $("#vVid").show();
    }else {
        $("#vTxt").show();
        $("#vVid").hide();
    }
}
/**
 * 设置课程信息
 * @param msg
 */
function setContent(msg) {
    courseId = msg.cid;
    $("#cName").html(msg.cname);
    $("#cDescipt").html(msg.ccontent);
    $("#cImg").attr("src",msg.cimg);
    $("#V").poster = msg.cimg;
}
/**
 * 设置课时信息
 * @param msg
 */
function setCh(msg) {
    var html = "";
    var len = msg.length;
    if(len > 0){
        for(var i = 0; i < len - 1;i++){
           html += "<div class=\"cr_item\">" +
                      "<div class=\"tmp\">" +
                        "<div class=\"cr_id\">" +
                           "<span>"+(i+1)+"</span>" +
                      "</div></div>" +
                      "<div class=\"cr_url\">" +
                         "<a href=\"javascript:lookV("+msg[i].churl+","+i+");\">"+msg[i].cname+"</a>" +
                   "</div></div>" +
                   "<div class=\"line\">" +
                      "<div class=\"lineShow\">" +
                   "</div></div>"
        }
        html += "<div class=\"cr_item\">" +
            "<div class=\"tmp\">" +
            "<div class=\"cr_id\">" +
            "<span>"+len+"</span>" +
            "</div></div>" +
            "<div class=\"cr_url\">" +
            "<a href=\"javascript:lookV("+msg[i].churl+","+(len-1)+");\">"+msg[len-1].cname+"</a>" +
            "</div></div>"
    }else {
        html = "暂无数据!"
    }
    $("#ch").append(html);
}
function lookV(url,index) {
    if(loginFlag == 1 && isSel == 1){
        if(vNum >= index){
            $("#V").attr("src",url);
        }else {
            alert("对不起，请按要求观看!");
        }
    }else {
        $("#V").attr("src",url);
    }
    $("#vTxt").hide();
    $("#vVid").show();
}
/**
 * 设置初始视频
 * @param msg
 */
function setSc(msg) {
    if(msg.sc != "F"){
        isSel = 1;
        vNum = msg.sc.chnum;
        var url = msg.ch[vNum].churl;
        $("#V").attr("src",url);
        $("#ivideo").attr("currentTime",msg.sc.studyhour);
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
                    loginFlag = 1;
                    isLoginAction("T",data.rtMData);
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
function setCommonMsg() {
    $("#tipMsg").empty();
    var html = "";
    if(loginFlag == 1 && isSel == 1){
        html = "<div class='button' onclick='learn(1)'> <span>开始学习</span> </div>";
        $("#tipMsg").append(html);
    }else if(loginFlag == 1){
        html = "<div class='button' onclick='addUSC(courseId)'> <span>加入课堂</span> </div>";
        $("#tipMsg").append(html);
    }
}
/**
 * 添加课程
 * @param cid
 */
function addUSC(cid) {
    $.ajax(
        {
            type: "post",
            url: "/user/api/addC",
            dataType:"json",
            data:{
                "cid":cid
            },
            success : function(data) {
                 if(data.rtCode == "T"){
                    isSel = 1;
                    setCommonMsg();
                 }
                 alert(data.rtMsg);
            }
        }
    );
}
function getCourse(cid) {
    $.ajax(
        {
            type: "post",
            url: "/resource/api/getVMsg",
            dataType:"json",
            data:{
               "cid":cid
            },
            success : function(data) {
                if(data.rtMCode == "T"){
                    setContent(data.rtMData.c);
                    setSc(data.rtMData);
                    setCh(data.rtMData.ch);
                    setCommonMsg();
                }
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
$(document).ready(function () {
    isLogin();
    var cid = getQueryVariable("cid");
    getCourse(cid);
})