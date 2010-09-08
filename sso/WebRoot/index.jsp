<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>シングルサインオンシステム（SSO）</title>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>
	<frameset rows="50,*" frameborder="no" border="0" framespacing="0">
		<frame src="title.html" name="topFrame" scrolling="No"
			noresize="noresize" id="topFrame" title="topFrame" />
		<frame src="blank.jsp" name="mainFrame" id="mainFrame"
			title="mainFrame" />
	</frameset>
	<noframes>
		<body>
			お使いのブラウザではこのシステムにアクセスできません。
			このシステムの推奨ブラウザであるInternet Explorer5.0以上をお使いください。
		</body>
	</noframes>
</html>
