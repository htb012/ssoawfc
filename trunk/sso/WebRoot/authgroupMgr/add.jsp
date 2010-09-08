<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/infMgr.js"></script>
		<title>社内用オンライン業務システム</title>
		<style type="text/css">
<!--
.STYLE1 {
	font-size: 24px
}
-->
</style>
	</head>

	<body>
		<html:errors />
		<p>
			&nbsp;
		</p>
			<p align="center">
			<img border="0" src="images/Hein37.jpg" width="40" height="30">
			<font size="6" color="#0080ff">権限組追加</font>
		</p>
		<p>
			&nbsp;
		</p>
		<html:form action="authgroupMgr.do">
			<html:hidden property="task" value="add" />
			<html:hidden property="authgroupid" />
			<p>
				権限組名：
				<html:text property="authgroupname" maxlength="50" size="50"></html:text>
			</p>
			<p>
				権限組概要：
				<html:textarea property="authgroupdiscription" rows="3" cols="50"></html:textarea>
			</p>


			<p align="center">
				<html:submit value="確定" />
				<html:reset value="キャンセル" onclick="javascript:window.close();"></html:reset>
			</p>
		</html:form>

		<p>
			&nbsp;
		</p>
	</body>
</html>
