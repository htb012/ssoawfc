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
		<p align="center" class="STYLE2 STYLE1"><img border="0" src="image/9.png" width="30" height="30"><font size="6" color="#0080ff">インフォメーション掲示板編集</font>
		</p>
		<p>
			&nbsp;
		</p>
		<html:form action="bulletinMgr.do">
			<html:hidden property="task" value="update" />
			<html:hidden property="bulletinid" />
			<html:hidden property="userid" />
			お知らせタイプ：
			<html:select property="bulletintype">
				<html:option value="all">全社内</html:option>
				<html:option value="sysRepair">システムリペアチーム</html:option>
				<html:option value="svcCenter">サービスセンターチーム</html:option>
			</html:select>
			<br />
			<br />
			表題：
			<html:text property="bulletintitle"></html:text>
			<p align="left">
				<span class="STYLE2">内容</span>
				<html:textarea property="content" rows="10" cols="50"></html:textarea>
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
