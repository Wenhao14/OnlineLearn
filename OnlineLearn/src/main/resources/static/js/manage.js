var navs=new Array("#nav1","#nav2","#nav3","#nav4");
var main = new Array("#um","#sm","#bm","#im");
var smenu;
var smenucli;
var wb;//读取完成的数据
var rABS = false; //是否将文件读取为二进制字符串
function nav_action(sel){
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
function asNavAction(sel){
	var html;
	switch(sel){
		case 0:{
			smenu = new Array("#u1","#u2","#u3","#u4","u");
			html = '<tr><td><span id="u1" onclick ="subMenu(0)">学员添加</span></td><td><span id="u2" onclick ="subMenu(1)">密码重置</span></td><td><span id="u3" onclick ="subMenu(2)">学员排名</span></td><td><span id="u4" onclick ="subMenu(3)">学员删除</span></td></tr>';
			break
		}
		case 1:{
			smenu = new Array("#s1","#s2","#s3","s");
			html = '<tr><td><span id="s1" onclick ="subMenu(0)">课程资源</span></td><td><span id="s2" onclick ="subMenu(1)">试题资源</span></td><td><span id="s3" onclick ="subMenu(2)">分类管理</span></td></tr>';
			break;
		}
		case 2:{
			$("#us_nav").empty();
			$("#us_navCli").empty();
			/**
			 * 跳转论坛管理
			 */
            window.location.href="http://127.0.0.1:8080/admin";
            return;
		}
		case 3:{
			smenu = new Array("#i1","#i2","#i3","i");
			html = '<tr><td><span id="i1" onclick ="subMenu(0)">新闻管理</span></td><td><span id="i2" onclick ="subMenu(1)">公告管理</span></td><td><span id="i3" onclick ="subMenu(2)">推荐课程</span></td></tr>';
			break;
		}
		default:{
			smenu = new Array("#u1","#u2","#u3","#u4","u");
			html = '<tr><td><span id="u1" onclick ="subMenu(0)">学员添加</span></td><td><span id="u2" onclick ="subMenu(1)">密码重置</span></td><td><span id="u3" onclick ="subMenu(2)">学员排名</span></td><td><span id="u4" onclick ="subMenu(3)">学员删除</span></td></tr>';
		}
	}
	$("#us_nav").empty();
	$("#us_nav").append(html);
	subMenu(0);
}
function subMenu(sel){
	for(var i = 0;i < smenu.length-1;i++){
		if(i == sel){
			$(smenu[i]).css({"background-color":"#0099FF","color":"white"});
            $(smenu[i]+"v").show();
            showNavCli(i,smenu[smenu.length-1]);
		}else{
			$(smenu[i]).css({"background-color":"white","color":"#333333"});
			$(smenu[i]+"v").hide();
		}
	}
}
function showNavCli(i,type){
	var html;
	switch(type){
		case "u":{
			switch(i){
				case 0:{
					smenucli = new Array("#u1v1","#u1v2");
					html = '<tr><td><span id="u1v1" onclick ="menuCli(0)">批量添加</span></td><td><span id="u1v2" onclick ="menuCli(1)">单个添加</span></td></tr>';
					break;
				}
				case 1:{
					$("#us_navCli").empty();
					return;
				}
				case 2:{
					smenucli = new Array("#u3v1","#u3v2");
                    userRank(1,0,6);
					html = '<tr><td><span id="u3v1" onclick ="menuCli(0),userRank(1,0,6);">正序排序</span></td><td><span id="u3v2" onclick ="menuCli(1),userRank(2,0,6);">逆序排序</span></td></tr>';
					break;
				}
				case 3:{
					smenucli = new Array("#u4v1","#u4v2");
					html = '<tr><td><span id="u4v1" onclick ="menuCli(0)">逻辑删除</span></td><td><span id="u4v2" onclick ="menuCli(1),findEnDelUser(0,6)">已删除</span></td></tr>';
					break;
				}
				default:{
					smenucli = new Array("#u1v1","#u1v2");
					html = '<tr><td><span id="u1v1" onclick ="menuCli(0)">批量添加</span></td><td><span id="u1v2" onclick ="menuCli(1)">单个添加</span></td></tr>';
				}
			}
			break;
		}
		case "s": {
			switch(i){
				case 0:{
					// smenucli = new Array("#s1v1","#s1v2","#s1v3","#s1v4");
					// html = '<tr><td><span id="s1v1" onclick ="menuCli(0)">发布课程</span></td><td><span id="s1v2" onclick ="menuCli(1)">修改课程</span></td><td><span id="s1v3" onclick ="menuCli(2)">逻辑删除</span></td><td><span id="s1v4" onclick ="menuCli(3)">已删除</span></td></tr>';
                    smenucli = new Array("#s1v1","#s1v2");
                    html = '<tr><td><span id="s1v1" onclick ="menuCli(0)">发布课程</span></td><td><span id="s1v2" onclick ="menuCli(1)">删除课程</span></td></tr>';
                    break;
				}
				case 1:{
					// smenucli = new Array("#s2v1","#s2v2","#s2v3");
					// html = '<tr><td><span id="s2v1" onclick ="menuCli(0)">发布试题</span></td><td><span id="s2v2" onclick ="menuCli(1)">逻辑删除</span></td><td><span id="s2v3" onclick ="menuCli(2)">已删除</span></td></tr>';
                    smenucli = new Array("#s2v1","#s2v2");
                    html = '<tr><td><span id="s2v1" onclick ="menuCli(0)">发布试题</span></td><td><span id="s2v2" onclick ="menuCli(1)">删除试题</span></td></tr>';

                    break;
				}
				case 2:{
					smenucli = new Array("#s3v1","#s3v2");
					html = '<tr><td><span id="s3v1" onclick ="menuCli(0)">添加类别</span></td><td><span id="s3v2" onclick ="menuCli(1)">修改类别</span></td></tr>';
					break;
				}
				default:{
					// smenucli = new Array("#s1v1","#s1v2","#s1v3","#s1v4");
					// html = '<tr><td><span id="s1v1" onclick ="menuCli(0)">发布课程</span></td><td><span id="s1v2" onclick ="menuCli(1)">修改课程</span></td><td><span id="s1v3" onclick ="menuCli(2)">逻辑删除</span></td><td><span id="s1v4" onclick ="menuCli(3)">已删除</span></td></tr>';
                    smenucli = new Array("#s1v1","#s1v2");
                    html = '<tr><td><span id="s1v1" onclick ="menuCli(0)">发布课程</span></td><td><span id="s1v2" onclick ="menuCli(1)">删除课程</span></td></tr>';
                }
			}
			break;
		}
		case "i":{
			switch(i){
				case 0:{
					smenucli = new Array("#i1v1","#i1v2");
					html = '<tr><td><span id="i1v1" onclick ="menuCli(0)">发布新闻</span></td><td><span id="i1v2" onclick ="menuCli(1)">删除新闻</span></td></tr>';
					break;
				}
				case 1:{
					smenucli = new Array("#i2v1","#i2v2");
					html = '<tr><td><span id="i2v1" onclick ="menuCli(0)">发布公告</span></td><td><span id="i2v2" onclick ="menuCli(1)">删除公告</span></td></tr>';
					break;
				}
				case 2:{
					smenucli = new Array("#i3v1","#i3v2");
					html = '<tr><td><span id="i3v1" onclick ="menuCli(0)">添加推荐</span></td><td><span id="i3v2" onclick ="menuCli(1)">取消推荐</span></td></tr>';
					break;
				}
				default:{
					smenucli = new Array("#i1v1","#i1v2");
					html = '<tr><td><span id="i1v1" onclick ="menuCli(0)">发布新闻</span></td><td><span id="i1v2" onclick ="menuCli(1)">删除新闻</span></td></tr>';
				}
			}
			break;
		}
		default:{
			switch(i){
				case 0:{
					smenucli = new Array("#u1v1","#u1v2");
					html = '<tr><td><span id="u1v1" onclick ="menuCli(0)">批量添加</span></td><td><span id="u1v2" onclick ="menuCli(1)">单个添加</span></td></tr>';
					break;
				}
				case 1:{
					smenucli = new Array("#u2v1");
					break;
				}
				case 2:{
					smenucli = new Array("#u3v1","#u3v2");
					userRank(1,0,6);
					html = '<tr><td><span id="u3v1" onclick ="menuCli(0),userRank(1,0,6);">正序排序</span></td><td><span id="u3v2" onclick ="menuCli(1),userRank(2,0,6);">逆序排序</span></td></tr>';
					break;
				}
				case 3:{
					smenucli = new Array("#u4v1","#u4v2");
					html = '<tr><td><span id="u4v1" onclick ="menuCli(0)">逻辑删除</span></td><td><span id="u4v2" onclick ="menuCli(1),findEnDelUser(0,6)">已删除</span></td></tr>';
					break;
				}
				default:{
					smenucli = new Array("#u1v1","#u1v2");
					html = '<tr><td><span id="u1v1" onclick ="menuCli(0)">批量添加</span></td><td><span id="u1v2" onclick ="menuCli(1)">单个添加</span></td></tr>';
				}
			}
		}
	}
	$("#us_navCli").empty();
	$("#us_navCli").append(html);
	menuCli(0);
}
function menuCli(sel){
	for(var i = 0;i < smenucli.length;i++){
		if(i == sel){
			$(smenucli[i]).css({"background-color":"#0099FF","color":"white"});
            $(smenucli[i]+"v").show();
		}else{
			$(smenucli[i]).css({"background-color":"white","color":"#333333"});
			$(smenucli[i]+"v").hide();
		}
	}
}
function patchAddUser() {//导入
	var obj = $("#usersUp").get(0);
    if(!obj.files) {
        return;
    }
    const IMPORTFILE_MAXSIZE = 5*1024;//这里可以自定义控制导入文件大小
    var suffix = obj.files[0].name.split(".")[1]
    if(suffix != 'xls' && suffix !='xlsx'){
        alert('导入的文件格式不正确!')
        return
    }
    if(obj.files[0].size/1024 > IMPORTFILE_MAXSIZE){
        alert('导入的表格文件不能大于5M')
        return
    }
    var f = obj.files[0];
    var reader = new FileReader();
    reader.onload = function(e) {
        var data = e.target.result;
        if(rABS) {
            wb = XLSX.read(btoa(fixdata(data)), {//手动转化
                type: 'base64'
            });
        } else {
            wb = XLSX.read(data, {
                type: 'binary'
            });
        }
        $.ajax(
            {
                type: "post",
                url: "/user/api/batchAdd",
                dataType:"json",
                data:{
                    usersJson:JSON.stringify( XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]))
                },
                success:function(data) {
					alert(data.rtMsg);
                },
				error:function () {
					alert("批量添加失败!");
                }
            }
        )
    };
    if(rABS) {
        reader.readAsArrayBuffer(f);
    } else {
        reader.readAsBinaryString(f);
    }
}
function fixdata(data) { //文件流转BinaryString
    var o = "",
        l = 0,
        w = 10240;
    for(; l < data.byteLength / w; ++l) o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w)));
    o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w)));
    return o;
}
function addUser() {
	var userName = $("#addUName").val();
	var grade = $("#uGrade").val();
	if(userName != undefined && userName.length >= 6){
        $.ajax(
            {
                type: "post",
                url: "/user/api/singleAdd",
                dataType:"json",
                data:{
                    "userName":userName,
                    "grade":grade
                },
                success:function(data) {
                    alert(data.rtMsg);
                },
                error:function () {
                    alert("添加失败!");
                }
            }
        )
	}else {
		alert("参数不合法!");
	}
}
function rePwd(){
    var userName = $("#rePwd").val();
    if(userName != undefined && userName.length >= 6){
        $.ajax(
            {
                type: "post",
                url: "/user/api/rePwd",
                dataType:"json",
                data:{
                    "userName":userName
				},
                success:function(data) {
                    alert(data.rtMsg);
                },
                error:function () {
                    alert("重置密码失败!");
                }
            }
        )
    }else {
        alert("参数不合法!");
    }
}
/**
 * 上传试题
 */
function addTp()
{
    alert("正在解析，请稍后");
    var tpt = $("#tp").val();
    var tpc = $("#toc").val();
    var xmlFile = $("#tp").get(0).files[0];
    var reader = new FileReader();//这里是核心！！！读取操作就是由它完成的。
    reader.readAsText(xmlFile);//读取文件的内容
    reader.onload = function(){
        var xmlStr = this.result;
        var x2js = new X2JS();
        var jsonObj = x2js.xml_str2json(xmlStr);
        var jsonStr = JSON.stringify(jsonObj.testPage);
        alert("正在上传，请稍后");
        $.ajax(
            {
                type: "post",
                url: "/resource/api/addTp",
                dataType:"json",
                data:{
                    "title":tpt,
                    "descibe":tpc,
                    "passDate":new Date(),
                    "content":jsonStr
                },
                success : function(data) {
                    alert(data.rtMsg);
                },
                error : function () {
                    alert("操作出错!");
                }
            }
        );
    }
}
/**
 * 读取已删除用户
 * @param pNum
 * @param pSize
 */
function findEnDelUser(pNum,pSize) {
	var html = "<tr class=\"tthead\"> <th>序号</th> <th>用户名</th> <th>操作</th> </tr>"
	$("#t1v_tab").empty();
    $("#t1v_tab").append(html);
    $.ajax(
        {
            type: "post",
            url: "/manage/api/getADU",
            dataType:"json",
            data:{
                "pNum":pNum,
                "pSize":pSize
            },
            success : function(data) {
                if(data.rtMCode == "T"){
                    var msg = data.rtMData;
                    var len = msg.length;
                    if(len < 1){
                        html = "<tr><td>暂无数据!</td></tr>";
                    }else {
                        html = "";
                        for(var i = 0;i < len;i++){
                           html += "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\"> " +
							"<td>"+(i+1)+"</td><td>"+msg[i].username+"</td><td><a href=\"javascript:uDeal(2,'"+msg[i].username+"')\"><img title=\"恢复\" class=\"delImg\" src=\"../../img/recove.jpg\"/></a><a href=\"javascript:uDeal(3,'"+msg[i].username+"')\"><img class=\"delImg\" title=\"彻底删除\" src=\"../../img/predel.jpg\"/></a></td></tr>"
                        }
                    }
                }else {
                    html = "<tr><td>内部错误!</td></tr>";
                }
                $("#t1v_tab").append(html);
            },
            error : function () {
                html = "<tr><td>内部错误!</td></tr>";
                $("#t1v_tab").append(html);
            }
        }
    );
}
/**
 * 逻辑/物理删除,恢复删除用户
 */
function uDeal(type,value) {
    var du;
    if(type == 1){
        du = $("#delU").val();
    }else if(type == 2) {
        du = value;
    }else {
        du = value
    }
    if(du == undefined ||du.length < 6){
        alert("非法参数!");
        return;
    }
    $.ajax(
        {
            type: "post",
            url: "/manage/api/uDeal",
            dataType:"json",
            data:{
                "type":type,
                "uName":du
            },
            success : function(data) {
                alert(data.rtMsg);
            },
            error : function () {
                alert("操作出错!");
            }
        }
    );
}
/**
 * 学员排名
 * @param type
 * @param pNum
 * @param pSize
 */
function userRank(type,pNum,pSize) {
	$("#t1v_t").empty();
	var html = "<tr class='tthead'> <th>用户名</th><th>姓名</th> <th>积分</th> <th>排名</th> </tr>";
    $("#t1v_t").append(html);
	$.ajax(
        {
            type: "post",
            url: "/manage/api/uRank",
            dataType:"json",
            data:{
                "type":type,
				"pNum":pNum,
				"pSize":pSize
            },
            success : function(data) {
                 if(data.rtMCode == "T"){
					 var msg = data.rtMData;
					 var len = msg.length;
                 	 if(len < 1){
                         html = "<tr><td>暂无数据!</td></tr>";
					 }else {
                 	 	html = "";
                 	 	for(var i = 0;i < len;i++){
                 	 		html += "<tr onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\"> " +
							"<td>"+msg[i][1]+"</td><td>"+msg[i][2]+"</td><td>"+msg[i][3]+"</td><td>"+msg[i][4]+"</td></tr>"
						}
					 }
				 }else {
                     html = "<tr><td>内部错误!</td></tr>";
				 }
                $("#t1v_t").append(html);
            },
            error : function () {
				html = "<tr><td>内部错误!</td></tr>";
                $("#t1v_t").append(html);
            }
        }
    );
}
/**
 * 添加新闻公告
 * @param type
 */
function addNN(type) {
	var nt;
	var ntxt;
	if(type == 1){
		nt = $("#nst").val();
        ntxt = $("#nstxt").val();
	}else{
        nt = $("#ntt").val();
        ntxt = $("#nttxt").val();
	}
	if(nt.length < 3 || ntxt < 5){
		alert("请输入有效参数!");
		return;
	}
    $.ajax(
        {
            type: "post",
            url: "/manage/api/addNN",
            dataType:"json",
            data:{
                "type":type,
                "title":nt,
				"content":ntxt
            },
            success : function(data) {
                alert(data.rtMsg);
            },
            error : function () {
                alert("操作出错!");
            }
        }
    );
}
$(document).ready(function(){
	nav_action(0);
});