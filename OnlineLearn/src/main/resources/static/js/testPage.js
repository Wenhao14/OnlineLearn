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
        $.ajax(
            {
                type: "post",
                url: "/resource/api/addTp",
                dataType:"json",
                data:{
                    "title":"",
                    "descibe":"",
                    "passDate":"",
                    "content":jsonStr
                },
                success : function(data) {
                   alert(data.rtMsg);
                },
                error : function () {
                    alert("��������!");
                }
            }
        );
    }
}