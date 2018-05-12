var isLogin = false;
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
function isLogin(){
	
}
function getModule(){
	item = new Array("军事","政治","教育","世界")
	if(item == null ||item.length == 0){
		return;
	}else{
		for(var i = 0;i<item.length;i++){
			var id = 'id="m'+i+'"';
			var html = '<div '+id+' class="option"><a href="javascript:module('+i+')">'+item[i]+'</a></div>';
			$("#lrb_nav").append(html);
		}
	}

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
	module(-1);
	if(isLogin){
		
	}
});
