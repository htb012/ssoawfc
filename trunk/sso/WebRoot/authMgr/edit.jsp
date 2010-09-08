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
		<p>
			&nbsp;
		</p>
			<p align="center">
			<img border="0" src="images/Hein38.jpg" width="40" height="30">
			<font size="6" color="#0080ff">リソース権限編集</font>
		</p>
		<p>
			&nbsp;
		</p>
		<html:form action="authMgr.do">
			<html:hidden property="task" value="update" />
			<html:hidden property="authid" />
			<table width="591" border="0">
				<tr>
					<td>
						<div align="left">
							<strong>リソース：</strong>
						</div>
					</td>
					<td>
						<html:select property="resid">
							<html:options collection="resources" property="resid"
								labelProperty="resname" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							<strong>権限種類：</strong>
						</div>
					</td>
					<td>
						<html:select property="metaauthid">
							<html:options collection="metaauths" property="metaauthid"
								labelProperty="metaauthname" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							<strong>権限概要：</strong>
						</div>
					</td>
					<td>
						<html:textarea property="authdiscription" cols="50" rows="10"></html:textarea>
					</td>
				</tr>
			</table>

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
