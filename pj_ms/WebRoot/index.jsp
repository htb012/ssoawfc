<%@ page language="java" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
	<head>
		<base href="<%=basePath%>" />
		<title>社内用オンライン業務システム</title>
	</head>

	<frameset rows="200,*" frameborder="yes" border="1" framespacing="1">
		<frame src="common/topFrame.jsp" name="topFrame" scrolling="no"
			noresize="noresize" id="topFrame" title="topFrame" />
		<frameset rows="*" cols="210,*" framespacing="1" frameborder="yes"
			border="1">
			<frame src="common/leftFrame.jsp" name="leftFrame" scrolling="no"
				noresize="noresize" id="leftFrame" title="leftFrame" />
			<frame src="bulletinMgr.do?task=view&scope=community" name="mainFrame" id="mainFrame"
				title="mainFrame" />
		</frameset>
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>
