$(function () {
	getConfig();
});
function getConfig(){
	JY.Tags.cleanForm("configForm");
	JY.Model.loading();
	JY.Ajax.doRequest(null,jypath +'/backstage/weixin/config/getConfig',null,function(data){
		if(data.res==1){
			var mc=data.obj;
			$("#configForm input[name$='appId']").val(mc.appId);
			$("#configForm input[name$='appSecret']").val(mc.appSecret);
			$("#configForm input[name$='token']").val(mc.token);
			$("#configForm input[name$='aseKey']").val(mc.aeskey);
			$("#configForm input[name$='turing']").prop("checked",mc.turing);
		}else{
   		 	JY.Model.error(data.resMsg);
   	 	}
		JY.Model.loadingClose();
	});
}

function showPW(o,id){
	var checked=o.checked;
	if(checked){
		$("#configForm input[name$='"+id+"']").attr("type","text");
	}else{
		$("#configForm input[name$='"+id+"']").attr("type","password");
	}
}