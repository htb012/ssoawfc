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
		<title>出错啦</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
	</head>
	<body><div align="center"><br><br><br><br><br><br><br><br><br><strong>-_-!  出错啦!</strong> 
	</div></body>
</html>