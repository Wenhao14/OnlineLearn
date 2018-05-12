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
					html = '<tr><td><span id="u3v1" onclick ="menuCli(0)">正序排序</span></td><td><span id="u3v2" onclick ="menuCli(1)">逆序排序</span></td></tr>';
					break;
				}
				case 3:{
					smenucli = new Array("#u4v1","#u4v2");
					html = '<tr><td><span id="u4v1" onclick ="menuCli(0)">逻辑删除</span></td><td><span id="u4v2" onclick ="menuCli(1)">已删除</span></td></tr>';
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
					smenucli = new Array("#s1v1","#s1v2","#s1v3","#s1v4");
					html = '<tr><td><span id="s1v1" onclick ="menuCli(0)">发布课程</span></td><td><span id="s1v2" onclick ="menuCli(1)">修改课程</span></td><td><span id="s1v3" onclick ="menuCli(2)">逻辑删除</span></td><td><span id="s1v4" onclick ="menuCli(3)">已删除</span></td></tr>';
					break;
				}
				case 1:{
					smenucli = new Array("#s2v1","#s2v2","#s2v3");
					html = '<tr><td><span id="s2v1" onclick ="menuCli(0)">发布试题</span></td><td><span id="s2v2" onclick ="menuCli(1)">逻辑删除</span></td><td><span id="s2v3" onclick ="menuCli(2)">已删除</span></td></tr>';
					break;
				}
				case 2:{
					smenucli = new Array("#s3v1","#s3v2");
					html = '<tr><td><span id="s3v1" onclick ="menuCli(0)">添加类别</span></td><td><span id="s3v2" onclick ="menuCli(1)">修改类别</span></td></tr>';
					break;
				}
				default:{
					smenucli = new Array("#s1v1","#s1v2","#s1v3","#s1v4");
					html = '<tr><td><span id="s1v1" onclick ="menuCli(0)">发布课程</span></td><td><span id="s1v2" onclick ="menuCli(1)">修改课程</span></td><td><span id="s1v3" onclick ="menuCli(2)">逻辑删除</span></td><td><span id="s1v4" onclick ="menuCli(3)">已删除</span></td></tr>';
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
					html = '<tr><td><span id="u3v1" onclick ="menuCli(0)">正序排序</span></td><td><span id="u3v2" onclick ="menuCli(1)">逆序排序</span></td></tr>';
					break;
				}
				case 3:{
					smenucli = new Array("#u4v1","#u4v2");
					html = '<tr><td><span id="u4v1" onclick ="menuCli(0)">逻辑删除</span></td><td><span id="u4v2" onclick ="menuCli(1)">已删除</span></td></tr>';
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
	if(userName != undefined && userName.length >= 6){
        $.ajax(
            {
                type: "post",
                url: "/user/api/singleAdd",
                dataType:"json",
                data:{
                    "userName":userName,
                    "grade":""
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
$(document).ready(function(){
	nav_action(0);
});