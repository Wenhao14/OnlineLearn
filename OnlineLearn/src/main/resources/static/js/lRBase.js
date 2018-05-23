var item ;
function module(sel){
	if(sel == -1){
		$("#m-1").css({"background-color":"#008ACF"});
		$("#option").text("全部");
	}else{
		$("#m-1").css({"background-color":"#FAA046"});
	}
	if(item == null || item.length == 0){
		return;
	}else{
		for(var i = 0; i < item.length;i++){
			var id = '#m'+i;
            if(i == sel){
				$(id).css({"background-color":"#008ACF"});
				$("#option").text(item[sel]);
            }else{
            	$(id).css({"background-color":"#FAA046"});
            }
		}
	}
}
function getModule(){
    $.ajax(
        {
            type: "post",
            url: "/resource/api/getAM",
            dataType:"json",
            success : function(data) {
                if(data.rtMCode == "T"){
                    var msg = data.rtMData;
                    var len = msg.length;
                    item = new Array(len);
                    if(item == null ||item.length == 0){
                        return;
                    }else{
                        for(var i = 0;i < len;i++){
                            item[i] = msg[i].mname;
                            var id = 'id="m'+i+'"';
                            var html = '<div '+id+' onclick="module('+i+'),getCouserByMid(\'#cTb\',0,6,'+msg[i].mid+')" class="option"><a>'+item[i]+'</a></div>';
                            $("#lrb_nav").append(html);
                        }
                    }
                }
                module(-1);
            }
        }
    );
}
/**
 * 提取url中的参数
 * @param variable
 * @returns {*}
 */
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
 * 通过关键字查找课程
 * @param key
 */
function findCbyK(key) {
    if(key != "F"){
        $("#key").val(key);
    }
    getCouserBykey("#cTb",0,6);
}
function paging(){
	$("#pageView").pagination({
		currentPage: 1,
		totalPage: 12,
		callback: function(current) {
	       //做某些事情
		}
	});
}
$(document).ready(function(){
    paging();
	getModule();
	var key = getQueryVariable("key");
	if(key != "F"){
        findCbyK(key);
    }else {
        getCouser('/index/api/getAC','#cTb',0,6);
    }
});
