function loadXmlFileToJson()
{
    var xmlFile = $("#file").get(0).files[0];
    var reader = new FileReader();//这里是核心！！！读取操作就是由它完成的。
    reader.readAsText(xmlFile);//读取文件的内容
    reader.onload = function(){
        var xmlStr = this.result;
        var x2js = new X2JS();
        var jsonObj = x2js.xml_str2json( xmlStr );
        var jsonStr = JSON.stringify(jsonObj.testPage);
        // $.ajax(
        //     {
        //         type: "post",
        //         url: "/user/api/userLogin",
        //         dataType:"json",
        //         data:{
        //             "userName":userName,
        //             "passWord":pwd
        //         },
        //         success : function(data) {
        //             if(data.rtMCode=="T"){
        //                 window.location.href="/index.html";
        //             }else{
        //                 alert(data.rtMsg);
        //                 yzmUp();
        //             }
        //         },
        //         error : function () {
        //             alert("登录失败！");
        //             yzmUp();
        //         }
        //     }
        // );
    }
}