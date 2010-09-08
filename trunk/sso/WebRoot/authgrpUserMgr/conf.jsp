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
		<script type="text/javascript" src="js/selectListTools.js"></script>
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

	<body onload="init(document.all.allauthgrps,document.all.hasauthgrps)">
		<html:errors />
		<p>
			&nbsp;
		</p>
			<p align="center">
			<img border="0" src="images/38.png" width="40" height="30">
			<font size="6" color="#0080ff">従業員権限編集</font>
		</p>
		<p>
			&nbsp;
		</p>
		<html:form action="authgrpUserMgr.do">
			<html:hidden property="task" value="conf" />
			<html:hidden property="userid" />
			<html:hidden property="authgrpstr" />
			<p>
				従業員ID：
				<html:text property="username" readonly="true"></html:text>
			</p>
			<br>

			<table width="281" height="131" border="1">
				<tr>
					<td width="115" scope="col">
						<html:select multiple="true" property="allauthgrps" size="8">
							<html:options collection="allauthgrps" property="authgroupid"
								labelProperty="authgroupname" />
						</html:select>
					</td>
					<td width="50" scope="col">
						<INPUT style="border: 1px solid black" type="button" value=">>>"
							onClick="moveSelected(document.all.allauthgrps,document.all.hasauthgrps)">
						<INPUT style="border: 1px solid black" type="button"
							value="<<<" onClick="moveSelected(document.all.hasauthgrps,document.all.allauthgrps)">
					</td>
					<td width="94" scope="col">
						<html:select multiple="true" property="hasauthgrps" size="8">
							<html:options collection="hasauthgrps" property="authgroupid"
								labelProperty="authgroupname" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<p align="center">
							<html:submit value="確定"
								onclick="buildTargetstr(document.all.hasauthgrps,document.all.authgrpstr)" />
							<html:reset value="キャンセル" onclick="javascript:window.close();"></html:reset>
						</p>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
