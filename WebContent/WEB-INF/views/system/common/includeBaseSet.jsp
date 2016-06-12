<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>统一权限管理平台</title>
<c:set var="jypath" value="${pageContext.request.contextPath}"/>
<script>
    var jypath = '${jypath}';
</script>
<script src="${jypath}/static/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript">
			if("ontouchend" in document) {
				//手机适应;
				document.write("<script src='"+jypath+"/static/js/jquery/jquery.mobile.custom.min.js'><"+"/script>");
				//document.write("<script src='"+jypath+"/static/js/jquery/jquery.mobile-1.4.5.min.js'><"+"/script>");
			}
</script>
<!--提示组件start-->
<script type="text/javascript" src="${jypath}/static/js/jquery/jquery.tips.js"></script>
<!--提示组件end-->
<link rel="icon" href="${jypath}/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${jypath}/favicon.ico" type="image/x-icon" />
<link rel="bookmark" href="${jypath}/favicon.ico" type="image/x-icon" />
