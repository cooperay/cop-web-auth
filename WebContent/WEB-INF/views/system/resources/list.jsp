<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../common/includeBaseSet.jsp" %>
<%@include file="../common/includeSystemSet.jsp" %>
<link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
</head>
<body>
	<div class="page-content">
		<div class="row-fluid">
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
				<div class="row">
					<div class="widget-main customBtn">	
						<c:forEach var="pbtn" items="${permitBtn}">
							<a href="#" title="${pbtn.name}" id="${pbtn.btnId}" class="lrspace3" ><i class='${pbtn.icon} bigger-220'></i></a>
						</c:forEach>
						<a href="#" title="刷新" id="searchBtn" onclick="getbaseList()" class="lrspace3" ><i class='icon-refresh bigger-220 orange'></i></a>		
					</div>
				</div>
				</form>
				<table id="baseTable" class="table table-striped table-bordered table-hover" >
					<thead>
						<tr>
							<th style="width:3%" class="center">
								<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
							</th>
							<th style="width:5%" class="center hidden-480">序号</th>
							<th style="width:35%" class="center">资源名称</th>
							<th style="width:25%" class='center hidden-480'>资源路径</th>		
							<th style="width:4%"  class='center hidden-480'>状态</th>
							<th style="width:18%" class='center hidden-480'>资源描述</th>
							<th style="width:13%" class="center">操作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<div class="row">
					<div class="col-sm-4">
						<div class="dataTables_info customBtn"></div>
					</div>
					<div class="col-sm-8"></div>
				</div>
			<!-- #addorUpdateFrom -->
			<%@include file="form.jsp" %>
			<!-- #dialog-confirm -->
			<%@include file="../common/dialog.jsp" %>
			</div>
		</div>
	</div>
<script src="${jypath}/static/js/system/resources/resources.js"></script>
</body>
</html>