var tpId;
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){
            return pair[1];
        }
    }
    return(false);
}
function getTpContent(id) {
    $.ajax(
        {
            type: "post",
            url: "/tp/api/getTpCont",
            dataType:"json",
            data:{
                "tpId":id
            },
            success : function(data) {
                var tp = data.rtMData;
                tp = JSON.parse(tp);
                $(function(){
                    $('#tp').jquizzy({
                        questions: tp.questions
                    });
                });
            },
            error : function () {
                alert("操作出错!");
            }
        }
    );
}
function saveScore(score){
    $.ajax(
        {
            type: "post",
            url: "/tp/api/addAnswer",
            dataType:"json",
            data:{
                "tpId":tpId,
                "score":score
            },
            success : function(data) {
                 upGoal(5);
                 alert(data.rtMsg);
            },
            error : function () {
                 alert("出错了!");
            }
        }
    );
}
function upGoal(goal){
    $.ajax(
        {
            type: "post",
            url: "/user/api/addGoal",
            dataType:"json",
            data:{
               "goal":goal
            }
        }
    );
}
$(document).ready(function(){
    tpId = getQueryVariable("tpId");
    getTpContent(tpId);
});