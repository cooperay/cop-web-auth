<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- #dialog-confirm -->
<div id="jyConfirm" class="hide">
	<div class="center">
		<h4><i class="icon-hand-right blue bigger-120"></i> <span id="jyConfirmStr"></span></h4>
	</div>
</div>
<div id="jyInfo" class="hide">
	<div class="center">
		<h4><i class="icon-info-sign blue bigger-120"></i> <span id="jyInfoStr"></span></h4>
	</div>
</div>
<div id="jyError" class="hide">
	<div class="center">
		<h4><i class="icon-warning-sign red bigger-120"></i> <span id="jyErrorStr"></span></h4>
	</div>
</div>
<div id="jyLoading" class="hide">
	<div class="center">
		<h4><img alt="loading" src="<c:url value="/static/images/system/loading.gif"/>"><span id="jyLoadingStr"></span></h4>
	</div>
</div>