<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.ssoserver.common.rmi.UserMgrFactory"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>社内用オンライン業務システム</title>
		<style type="text/css">
<!--
.STYLE1 {
	font-size: 36px;
	font-style: italic;
	font-weight: bold;
}
-->
</style>
	</head>

	<body>
		<img name="imageField" src="image/pegatronLogo.jpg" />
		<div align="center" class="STYLE1">
			<img border="0" src="image/purelogo.JPG" width="38" height="31">
			<font color="#0080ff">社内用オンライン業務システム</font>
			<img width="38" height="31" border="0" src="image/purelogo.JPG">

			<img border="0" src="image/34.png" width="21" height="21">
			<font size="4" color="#408080" style="font-weight: normal;">現在オンライン
				<%=UserMgrFactory.getUserMgr().getOnlineCount()%> 人</font>

		</div>
	</body>
</html>
