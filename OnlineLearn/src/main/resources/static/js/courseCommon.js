function getCouser(url,id,pNum,pSize) {
    $.ajax(
        {
            type: "post",
            url: url,
            dataType:"json",
            data:{
                "pageNum":pNum,
                "pageSize":pSize
            },
            success : function(data) {
                var html = "";
                if(data.rtMCode == "T"){
                    $(id).empty();
                    var courses = data.rtMData;
                    var len = courses.length;
                    if(len > 0){
                        var t = 1;
                        for(var i = 0;i < len;i++){
                           if(t == 1){
                               t += 1;
                              html += "<tr><td onclick=\"javascript:window.location.href='/page/front/classRoom.html?cid="+courses[i][0]+"'\"> <div class='course'>" +
                               "<img src='"+courses[i][1]+"'/>"+
                               "<a>"+courses[i][2]+"</a><br/>"+
                               "<span>"+courses[i][3]+"</span>"+
                               "</div></td>";

                           }else if(t == 3){
                               html += "<td onclick=\"javascript:window.location.href='/page/front/classRoom.html?cid="+courses[i][0]+"'\"> <div class='course'>" +
                                   "<img src='"+courses[i][1]+"'/>"+
                                   "<a>"+courses[i][2]+"</a><br/>"+
                                   "<span>"+courses[i][3]+"</span>"+
                                   "</div></td></tr>";
                               t = 1;
                           }else {
                               t += 1;
                               html += "<td onclick=\"javascript:window.location.href='/page/front/classRoom.html?cid="+courses[i][0]+"'\"> <div class='course'>" +
                                   "<img src='"+courses[i][1]+"'/>"+
                                   "<a>"+courses[i][2]+"</a><br/>"+
                                   "<span>"+courses[i][3]+"</span>"+
                                   "</div></td>";
                           }
                        }
                        if(t != 1){
                            for(t; t < 4;t++){
                                html += "<td></td>";
                            }
                            html += "</tr>";
                        }
                        $(id).append(html);
                    }else {
                        html = "<tr><td>暂无数据!</td></tr>";
                        $(id).append(html);
                    }
                }else {
                    html = "<tr><td>"+ data.rtMsg+"</td></tr>";
                    $(id).append(html);
                }
            },
            error : function () {
                var html = "<tr><td>内部出错!</td></tr>";
                $(id).append(html);
            }
        }
    );
}
function getCouserBykey(id,pNum,pSize) {
    var key = $("#key").val();
    if(key == undefined || key.length < 1)return;
    $.ajax(
        {
            type: "post",
            url: "/index/api/getCByKey" ,
            dataType:"json",
            data:{
                "key":key,
                "pageNum":pNum,
                "pageSize":pSize
            },
            success : function(data) {
                var html = "";
                if(data.rtMCode == "T"){
                    $(id).empty();
                    var courses = data.rtMData;
                    var len = courses.length;
                    if(len > 0){
                        var t = 1;
                        for(var i = 0;i < len;i++){
                            if(t == 1){
                                t += 1;
                                html += "<tr><td onclick=\"javascript:window.location.href='/page/front/classRoom.html?cid="+courses[i][0]+"'\"> <div class='course'>" +
                                    "<img src='"+courses[i][1]+"'/>"+
                                    "<a>"+courses[i][2]+"</a><br/>"+
                                    "<span>"+courses[i][3]+"</span>"+
                                    "</div></td>";

                            }else if(t == 3){
                                html += "<td onclick=\"javascript:window.location.href='/page/front/classRoom.html?cid="+courses[i][0]+"'\"> <div class='course'>" +
                                    "<img src='"+courses[i][1]+"'/>"+
                                    "<a>"+courses[i][2]+"</a><br/>"+
                                    "<span>"+courses[i][3]+"</span>"+
                                    "</div></td></tr>";
                                t = 1;
                            }else {
                                t += 1;
                                html += "<td onclick=\"javascript:window.location.href='/page/front/classRoom.html?cid="+courses[i][0]+"'\"> <div class='course'>" +
                                    "<img src='"+courses[i][1]+"'/>"+
                                    "<a>"+courses[i][2]+"</a><br/>"+
                                    "<span>"+courses[i][3]+"</span>"+
                                    "</div></td>";
                            }
                        }
                        if(t != 1){
                            for(t; t < 4;t++){
                                html += "<td></td>";
                            }
                            html += "</tr>";
                        }
                        $(id).append(html);
                    }else {
                        html = "<tr><td>暂无数据!</td></tr>";
                        $(id).append(html);
                    }
                }else {
                    html = "<tr><td>"+ data.rtMsg+"</td></tr>";
                    $(id).append(html);
                }
            },
            error : function () {
                var html = "<tr><td>内部出错!</td></tr>";
                $(id).append(html);
            }
        }
    );
}
function getCouserByMid(id,pNum,pSize,mid) {
    $.ajax(
        {
            type: "post",
            url: "/index/api/getCByMid",
            dataType:"json",
            data:{
                "mid":mid,
                "pageNum":pNum,
                "pageSize":pSize
            },
            success : function(data) {
                var html = "";
                if(data.rtMCode == "T"){
                    $(id).empty();
                    var courses = data.rtMData;
                    var len = courses.length;
                    if(len > 0){
                        var t = 1;
                        for(var i = 0;i < len;i++){
                            if(t == 1){
                                t += 1;
                                html += "<tr><td onclick=\"javascript:window.location.href='/page/front/classRoom.html?cid="+courses[i][0]+"'\"> <div class='course'>" +
                                    "<img src='"+courses[i][1]+"'/>"+
                                    "<a>"+courses[i][2]+"</a><br/>"+
                                    "<span>"+courses[i][3]+"</span>"+
                                    "</div></td>";

                            }else if(t == 3){
                                html += "<td onclick=\"javascript:window.location.href='/page/front/classRoom.html?cid="+courses[i][0]+"'\"> <div class='course'>" +
                                    "<img src='"+courses[i][1]+"'/>"+
                                    "<a>"+courses[i][2]+"</a><br/>"+
                                    "<span>"+courses[i][3]+"</span>"+
                                    "</div></td></tr>";
                                t = 1;
                            }else {
                                t += 1;
                                html += "<td onclick=\"javascript:window.location.href='/page/front/classRoom.html?cid="+courses[i][0]+"'\"> <div class='course'>" +
                                    "<img src='"+courses[i][1]+"'/>"+
                                    "<a>"+courses[i][2]+"</a><br/>"+
                                    "<span>"+courses[i][3]+"</span>"+
                                    "</div></td>";
                            }
                        }
                        if(t != 1){
                            for(t; t < 4;t++){
                                html += "<td></td>";
                            }
                            html += "</tr>";
                        }
                        $(id).append(html);
                    }else {
                        html = "<tr><td>暂无数据!</td></tr>";
                        $(id).append(html);
                    }
                }else {
                    html = "<tr><td>"+ data.rtMsg+"</td></tr>";
                    $(id).append(html);
                }
            },
            error : function () {
                var html = "<tr><td>内部出错!</td></tr>";
                $(id).append(html);
            }
        }
    );
}