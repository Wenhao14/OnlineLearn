var loginFlag = 0;
var isSel = 0;
var courseId;
var currTime = 0;
var vNum = 0;
var vData;
/**
 * 登录是否动作
 * @param flag
 * @param msg
 */
function isLoginAction(flag,msg) {
    var html;
    if(flag == "T"){
        $("#tip").html("欢迎["+msg.um+"]——友情提示：请勿直接关闭浏览器，以免数据丢失!");
        html = "<img title='返回' onclick=\"javascript:window.location.href='/page/front/userSpace.html'\" width='100%' height='100%' src='../../img/loginout.png'/>"
        $("#lo").append(html);
    }else {
        $("#tip").html("您未登陆，学习数据不会记录!");
        html = "<img title='返回' onclick=\"javascript:window.location.href='/page/front/local_resource_base.html'\" width='100%' height='100%' src='../../img/loginout.png'/>"
        $("#lo").append(html);
    }
}
function clickAction() {
    if(loginFlag == 1 && isSel == 1){
        $("#vTxt").hide();
        $("#vVid").show();
        $("#V").attr("currentTime",currTime);
        $("#V").attr("autoplay","true");
    }else {
        addUSC(courseId);
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
        vData = msg;
        for(var i = 0; i < len - 1;i++){
           html += "<div class=\"cr_item\">" +
                      "<div class=\"tmp\">" +
                        "<div class=\"cr_id\">" +
                           "<span>"+(i+1)+"</span>" +
                      "</div></div>" +
                      "<div class=\"cr_url\">" +
                         "<a href=\"javascript:lookV('"+msg[i].churl+"',"+i+");\">"+msg[i].cname+"</a>" +
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
            "<a href=\"javascript:lookV('"+msg[i].churl+"',"+(len-1)+");\">"+msg[len-1].cname+"</a>" +
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
            $("#vTxt").hide();
            $("#vVid").show();
        }else {
            alert("对不起，请按要求观看!");
        }
    }else {
        $("#V").attr("src",url);
        $("#vTxt").hide();
        $("#vVid").show();
        if(loginFlag == 1){
            $("#ac").show();
        }
    }
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
        currTime = msg.sc.studyhour;
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
            async:false,
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
/**
 * 视频播放结束事件
 */
// function end(){
//     if(loginFlag == 1&&isSel == 1){
//         vNum = vNum + 1;
//         if(vNum < vData.length){
//             currTime = 0;
//             $("#V").attr("src",vData[vNum].churl);
//             $("#V").attr("autoplay","true");
//             upLearnJD(1);
//         }else {
//             alert("恭喜你，学习完成");
//         }
//     }else {
//
//     }
// }
// function upLearnJD(type) {
//     if(loginFlag != 1 || isSel != 1){
//         return;
//     }
//     var jd;
//     var len = vData.length;
//     if(type == 1){
//         jd = (vNum-1)/len;
//     }else {
//         var ct = $("#V").currentTime;
//         if(ct > currTime){
//             currTime = ct;
//         }else {
//             return;
//         }
//         jd = (vNum)/len + currTime/$("#V").duration/len;
//     }
//     jd = jb*100;
//     jd = jb+"%";
//     $.ajax(
//         {
//             type: "post",
//             url: "/user/api/upLT",
//             dataType:"json",
//             data:{
//                 "scId":scId,
//                 "vNum":vNum,
//                 "ct":ct,
//                 "jd":jd
//             }
//         }
//     );
// }
function setCommonMsg() {
    if(loginFlag == 1 && isSel == 1){
        $("#msg").html("开始学习");
        $("#tipMsg").show();
    }else if(loginFlag == 1){
        $("#msg").html("添加课程");
        $("#tipMsg").show();
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
                 if(data.rtMCode == "T"){
                    isSel = 1;
                    $("#msg").html("开始学习");
                    $("#ac").hide();
                    $("#vTxt").show();
                    $("#vVid").hide();
                    $("#V").attr("src",vData[0].churl);
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
            async:false,
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
/**
 * 鼠标移出暂停播放
 */
function outStop() {
    $("#V").trigger('pause');
}
$(document).ready(function () {
    isLogin();
    var cid = getQueryVariable("cid");
    getCourse(cid);
})