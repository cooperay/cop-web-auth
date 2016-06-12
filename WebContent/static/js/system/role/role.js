$(function () {
	JY.Dict.setSelect("selectisValid","isValid",2,'全部');
	loadOrgTree();
	//增加回车事件
	$("#baseForm").keydown(function(e){
		 keycode = e.which || e.keyCode;
		 if (keycode==13) {
			 search();
		 }
	});
	//授权机构组织
	$('#authOrgBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		var orgId=$("#baseForm input[name$='orgId']").val();
		if(JY.Object.notNull(orgId)){
			JY.Ajax.doRequest(null,jypath +'/backstage/role/orglistAuthorized',{id:orgId},function(data){
				 if(data.res==1){  
			         $.fn.zTree.init($("#authorityTree"),{check: {enable: true,chkDisabledInherit: true},data:{simpleData:{enable: true}}},data.obj);	      
			         JY.Model.edit("authorityDiv","机构组织授权",function(){
			        		var zTree = $.fn.zTree.getZTreeObj("authorityTree"),
		 					nodes = zTree.getCheckedNodes(),aus ="";
		 					for (var i=0, l=nodes.length; i<l; i++) {
		 						aus += nodes[i].id + ",";
		 					}
		 					if (aus.length > 0 ) aus = aus.substring(0, aus.length-1);
		 					var that =$(this);
		 					JY.Ajax.doRequest(null,jypath +'/backstage/role/saveOrgAuthorized',{id:orgId,aus:aus},function(data){
		 						 if(data.res==1){
		 	 					       that.dialog("close");
		 	 					       JY.Model.info(data.resMsg,function(){search();});			        		 
		 	 					    }else{
		 	 					       JY.Model.error(data.resMsg);
		 	 					    } 		
		 					});
			         });
			     }else{
			         JY.Model.error(data.resMsg);
			     }  
			});
		}else{
			 JY.Model.error("请选择机构组织");
		}
	});
	//新加机构组织
	$('#addOrgBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		cleanOrgForm();
		loadPreOrgTree();
		JY.Model.edit("auOrgDiv","新增机构组织",function(){
			 if(JY.Validate.form("auOrgForm")){
				 var that =$(this);
				 JY.Ajax.doRequest("auOrgForm",jypath +'/backstage/role/addOrg',null,function(data){
					 if(data.res==1){  	
						 that.dialog("close"); 
		        		 JY.Model.info(data.resMsg,function(){refreshOrgTree();});
		        	 }else{
		        		 JY.Model.error(data.resMsg);
		        	 }       
				 }); 
			 }	
		});
	});
	//修改机构组织
	$('#editOrgBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		cleanOrgForm();
		$("#preOrgTR").addClass('hide');
		loadPreOrgTree();
		var orgId=$("#baseForm input[name$='orgId']").val();
		JY.Ajax.doRequest(null,jypath+'/backstage/role/findOrg',{id:orgId},function(data){
			if(data.res==1){
				setOrgForm(data);    
				JY.Model.edit("auOrgDiv","修改机构组织",function(){		
					 if(JY.Validate.form("auOrgForm")){
						 var that =$(this);
						 JY.Ajax.doRequest("auOrgForm",jypath +'/backstage/role/updateOrg',null,function(data){
							 if(data.res==1){  	
								 that.dialog("close"); 
				        		 JY.Model.info(data.resMsg,function(){refreshOrgTree();});
				        	 }else{
				        		 JY.Model.error(data.resMsg);
				        	 }       
						 }); 
					 }	
				});
			}else{
	    		JY.Model.info(data.resMsg);
	    	}   
		});		
	});
	
	//删除机构组织
	$('#delOrgBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		var orgId=$("#baseForm input[name$='orgId']").val();
		var treeObj = $.fn.zTree.getZTreeObj("orgTree");
		var ptreeNode=treeObj.getNodeByParam("id",orgId,null);
		JY.Model.confirm("确认要删除【"+ptreeNode.name+"】吗？",function(){		
			JY.Ajax.doRequest(null,jypath+'/backstage/role/delOrg',{id:orgId},function(data){
				 if(data.res==1)  JY.Model.info(data.resMsg,function(){loadOrgTree();});
		         else             JY.Model.error(data.resMsg);
			});
		});	
	});
	
	//新加角色
	$('#addBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		cleanForm();
		 var orgId=$("#baseForm input[name$='orgId']").val();		
		 if(JY.Object.notNull(orgId)){
			 var treeObj = $.fn.zTree.getZTreeObj("orgTree");
		     var ptreeNode=treeObj.getNodeByParam("id",orgId,null);
			 if(JY.Object.notNull(ptreeNode.pId)&&ptreeNode.pId!="0"){
				JY.Model.edit("auDiv","新增角色",function(){
				 if(JY.Validate.form("auForm")){
					 var that =$(this);			
					 JY.Ajax.doRequest("auForm",jypath +'/backstage/role/add',{orgId:orgId},function(data){
						 if(data.res==1){
			        		 that.dialog("close");      
			        		 JY.Model.info(data.resMsg,function(){search();});
			        	 }else{
			        		 JY.Model.error(data.resMsg);
			        	 }       
					 }); 
				 }	
			});
			 }else{
				 JY.Model.error("请在二级机构组织新增角色"); 
			 }
		 }else{
			 JY.Model.error("请先选择机构组织");
		 }
	});
	//批量删除
	$('#delBatchBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		var chks =[];    
		$('#baseTable input[name="ids"]:checked').each(function(){chks.push($(this).val());});  	
		if(chks.length==0) {
			JY.Model.info("您没有选择任何内容!"); 
		}else{
			JY.Model.confirm("确认要删除选中的角色吗?",function(){
				JY.Ajax.doRequest(null,jypath +'/backstage/role/delBatch',{chks:chks.toString()},function(data){
					if(data.res==1) JY.Model.info(data.resMsg,function(){search();});
				    else            JY.Model.error(data.resMsg);
				});
			});	
		}	
	});
});
function search(){
	$("#searchBtn").trigger("click");
}
function loadOrgTree(){
	JY.Ajax.doRequest(null,jypath +'/backstage/role/getOrgTree',null,function(data){
		//设置
		$.fn.zTree.init($("#orgTree"),{view:{selectedMulti:false,fontCss:{color:"#393939"}},data:{simpleData: {enable: true}},callback:{onClick:clickOrg}},data.obj);
		var treeObj = $.fn.zTree.getZTreeObj("orgTree");
		var nodes = treeObj.getNodes();
		if(nodes.length>0){
			//默认选中第一个
			treeObj.selectNode(nodes[0]);
			clickOrg(null,null,nodes[0]);
		}
	});
}
function refreshOrgTree(){
	 JY.Model.loading();
	 JY.Ajax.doRequest(null,jypath +'/backstage/role/getOrgTree',null,function(data){
			//设置
			$.fn.zTree.init($("#orgTree"),{view:{selectedMulti:false,fontCss:{color:"#393939"}},data:{simpleData: {enable: true}},callback:{onClick:clickOrg}},data.obj);
			JY.Model.loadingClose();	 
	 });
	 
}
function loadPreOrgTree(){
	JY.Ajax.doRequest(null,jypath +'/backstage/role/getPreOrgTree',null,function(data){
		//设置
		$.fn.zTree.init($("#preOrgTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickPreOrg}},data.obj);
	});
}
function emptyPreOrg(){
	$("#preOrg").prop("value","");
	$("#auOrgForm input[name$='pId']").prop("value","0");
}
var preisShow=false;//窗口是否显示
function showPreOrg() {
	if(preisShow){
		hidePreOrg();
	}else{
		var obj = $("#preOrg");
		var offpos = $("#preOrg").position();
		$("#preOrgContent").css({left:offpos.left+"px",top:offpos.top+obj.heith+"px"}).slideDown("fast");	
		preisShow=true;
	}
}
function clickPreOrg(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("preOrgTree"),
	nodes = zTree.getSelectedNodes(),v ="",n ="";	
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";//获取name值
		n += nodes[i].id + ",";	//获取id值
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (n.length > 0 ) n = n.substring(0, n.length-1);
	$("#preOrg").prop("value",v);
	$("#auOrgForm input[name$='pId']").prop("value",n);
	//因为单选选择后直接关闭，如果多选请另外写关闭方法
	hidePreOrg();
}
function hidePreOrg(){
	$("#preOrgContent").fadeOut("fast");
	preisShow=false;
}
function clickOrg(event,treeId,treeNode) {
	$("#baseForm input[name$='orgId']").val(treeNode.id);
	$("#orgName1").html("");
	$("#orgName2").html("");
	if(JY.Object.notNull(treeNode.pId)){
		var treeObj = $.fn.zTree.getZTreeObj("orgTree");
		var ptreeNode=treeObj.getNodeByParam("id",treeNode.pId,null);
		$("#orgName1").html(ptreeNode.name);
		$("#orgName2").html(treeNode.name);
	}else{
		$("#orgName1").html(treeNode.name);
	}
	getbaseList();
}
function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);	
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/backstage/role/findByPage',null,function(data){
		 $("#baseTable tbody").empty();
    	 if(data.res==1){
    		 var obj=data.obj;
        	 var list=obj.results;
        	 var permitBtn=data.permitBtn;
        	 var pageNum=obj.pageNum,pageSize=obj.pageSize,totalRecord=obj.totalRecord;
        	 var html="";
    		 if(list!=null&&list.length>0){			
        		 var leng=(pageNum-1)*pageSize;//计算序号
        		 for(var i = 0;i<list.length;i++){
            		 var l=list[i];
            		 html+="<tr>";
            		 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.id+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 html+="<td class='center hidden-480'>"+(i+leng+1)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.name)+"</td>";
            		 if(l.isValid==1){
            			 html+="<td class='center hidden-480'><span class='label label-sm label-success'>有效</span></td>";
            		 }else{
            			 html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>无效</span></td>";
            		 } 
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.description)+"</td>";
            		 html+=JY.Tags.setFunction(l.id,permitBtn);
            		 html+="</tr>";		 
            	 } 
        		 $("#baseTable tbody").append(html);
        		 JY.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
        	 }else{
        		html+="<tr><td colspan='8' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }	 
    	 }else{
    		 JY.Model.error(data.resMsg);
    	 } 	        	 
    	 JY.Model.loadingClose();	
	});
}
function authorized(id){
	JY.Ajax.doRequest(null,jypath +'/backstage/role/listAuthorized',{id:id},function(data){
		 if(data.res==1){  
	         //获取数据
	    	 var zNodes=data.obj;
	         //设置
	    	 var setting = {check: {enable: true,chkDisabledInherit: true},data:{simpleData:{enable: true}}};
	         $.fn.zTree.init($("#authorityTree"),setting,zNodes);	      
	         JY.Model.edit("authorityDiv","角色授权",function(){
	        		var zTree = $.fn.zTree.getZTreeObj("authorityTree"),
 					nodes = zTree.getCheckedNodes(),aus ="";
 					for (var i=0, l=nodes.length; i<l; i++) {
 						aus += nodes[i].id + ",";
 					}
 					if (aus.length > 0 ) aus = aus.substring(0, aus.length-1);
 					var that =$(this);
 					JY.Ajax.doRequest(null,jypath +'/backstage/role/saveAuthorized',{id:id,aus:aus},function(data){
 						 if(data.res==1){
 	 					       that.dialog("close");
 	 					       JY.Model.info(data.resMsg,function(){search();});			        		 
 	 					    }else{
 	 					       JY.Model.error(data.resMsg);
 	 					    } 		
 					});
	         });
	     }else{
	         JY.Model.error(data.resMsg);
	     }  
	});
}
function showCode(str) {
	if (!code) code = $("#code");
	code.empty();
	code.append("<li>"+str+"</li>");
}
function check(id){
	//清空表单
	cleanForm();
	JY.Ajax.doRequest(null,jypath+'/backstage/role/find',{id:id},function(data){
		if(data.res==1){
    		setForm(data);    
    		JY.Model.check("auDiv");
    	}else{
    		JY.Model.info(data.resMsg);
    	}       
	});
}
function edit(id){
	//清空表单
	cleanForm();	
	JY.Ajax.doRequest(null,jypath+'/backstage/role/find',{id:id},function(data){
		if(data.res==1){
    		setForm(data);    
    		JY.Model.edit("auDiv","修改角色",function(){
    			 if(JY.Validate.form("auForm")){
					 var that =$(this);
					 JY.Ajax.doRequest("auForm",jypath+'/backstage/role/update',null,function(data){
						 if(data.res==1){
			        		 that.dialog("close");
			        		 JY.Model.info(data.resMsg,function(){search();});      				        		 
			        	 }else{
			        		 JY.Model.error(data.resMsg);
			        	 } 	
					 });
				}
    		});
    	}else{
    		JY.Model.info(data.resMsg);
    	}   
	});
}
function del(id){
	JY.Model.confirm("确认删除角色吗？",function(){	
		JY.Ajax.doRequest(null,jypath+'/backstage/role/del',{id:id},function(data){
			 if(data.res==1)  JY.Model.info(data.resMsg,function(){search();});
	         else             JY.Model.error(data.resMsg);
		});
	});	
}
function cleanOrgForm(){
	JY.Tags.cleanForm("auOrgForm");
	$("#auOrgForm input[name$='pId']").val('0');//上级资源
	$("#preOrgTR").removeClass('hide');
	hidePreOrg();
}
function cleanForm(){
	JY.Tags.cleanForm("auForm");
	JY.Tags.isValid("auForm","1");
}

function setOrgForm(data){
	var l=data.obj;
	$("#auOrgForm input[name$='id']").val(JY.Object.notEmpty(l.id));
	$("#auOrgForm input[name$='name']").val(JY.Object.notEmpty(l.name));
	$("#auOrgForm textarea[name$='description']").val(JY.Object.notEmpty(l.description));//描述
	$("#preOrg").val(JY.Object.notEmpty(l.pName));
	$("#auForm input[name$='pId']").val(JY.Object.notEmpty(l.pId));//上级资源
};  
function setForm(data){
	var l=data.obj;
	$("#auForm input[name$='id']").val(JY.Object.notEmpty(l.id));
	JY.Tags.isValid("auForm",(JY.Object.notNull(l.isValid)?l.isValid:"0"));
	$("#auForm input[name$='name']").val(JY.Object.notEmpty(l.name));
	$("#auForm textarea[name$='description']").val(JY.Object.notEmpty(l.description));//描述
}
  
