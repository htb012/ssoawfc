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
			<img border="0" src="image/HeinH11.jpg" width="40" height="30">
			<font size="6" color="#0080ff"> 機種編集</font>
		</p>
		<p>
			&nbsp;
		</p>
		<html:form action="modelMgr.do" enctype="multipart/form-data"
			method="post">
			<html:hidden property="task" value="update" />
			<html:hidden property="modelid" />
			<p>
				機種名：
				<html:text property="modelname" maxlength="30"></html:text>
			</p>
			<p>
				社内wikiリンク：
				<html:textarea property="linkmark" rows="3" cols="50"></html:textarea>
			</p>
			<p>
				機種概要（スペックなど）：
				<html:textarea property="parameter" rows="10" cols="50"></html:textarea>
			</p>
			<input type="hidden" id="imgDelStr" name="imgDelStr">
			<logic:iterate id="photo" collection="${photos}"
				type="java.lang.String" indexId="indexId">
				<span id="id${indexId}"> <a href="${photo}" target="_blank"><img
							src="${photo}" width="100" height="100"> </a> <a
					onclick="imgDel(document.all.id${indexId},document.all.imgDelStr,'${photo}')"><img
							src="image/delete.gif"> </a> </span>
			</logic:iterate>
			<p id="fileSpace">
			</p>
			<input type="button" value="写真ファイルを追加する"
				onclick="fileAdd(document.all.fileSpace)">
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
