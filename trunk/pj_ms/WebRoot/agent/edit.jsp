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
		<p align="center">
			<img border="0" src="image/119.png" width="40" height="30">
			<font size="6" color="#0080ff">代理商編集</font>
		</p>
		<p>
			&nbsp;
		</p>
		<html:form action="agentMgr.do">
			<html:hidden property="task" value="update" />
			<html:hidden property="agentid" />
			<p>
				代理商名：
				<html:text property="agentname" maxlength="20"></html:text>
			</p>
			<p>
				連絡番号：
				<html:text property="agentphone" maxlength="20"></html:text>
			</p>
			<p>
				E-mail：
				<html:text property="agentemail" maxlength="30"></html:text>
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
