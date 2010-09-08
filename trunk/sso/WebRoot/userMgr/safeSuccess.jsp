<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
			function showMsg(){
				alert("${safeMsg}");
				opener.location.reload();
				javascript:window.close();
			}
		</script>
		<title>保存完了</title>
	</head>
	<body onload="showMsg()">
		${safeMsg}
		<br>
		<div align="center">
			<a href="null" onclick="javascript:window.close();">【ウィンドウを閉じる】</a>
			<br>

		</div>
	</body>
</html>