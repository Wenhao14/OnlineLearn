function loadXmlFileToJson()
{
    var xmlFile = $("#file").get(0).files[0];
    var reader = new FileReader();//�����Ǻ��ģ�������ȡ��������������ɵġ�
    reader.readAsText(xmlFile);//��ȡ�ļ�������
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
        //             alert("��¼ʧ�ܣ�");
        //             yzmUp();
        //         }
        //     }
        // );
    }
}