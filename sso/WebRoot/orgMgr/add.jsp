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
			<img border="0" src="images/Hein33.jpg" width="40" height="30">
			<font size="6" color="#0080ff">組織追加</font>
		</p>
		<p>
			&nbsp;
		</p>
		<html:form action="orgMgr.do">
			<html:hidden property="task" value="add" />
			<html:hidden property="orgid" />
			<table width="591" border="0">
				<tr>
					<th width="116" scope="col">
						<div align="left">
							<strong>部門名：</strong>
						</div>
					</th>
					<th width="459" scope="col">
						<div align="left">
							<html:text property="orgname"></html:text>
						</div>
					</th>
				</tr>
				<tr>
					<td>
						<div align="left">
							<strong>部門リーダー：</strong>
						</div>
					</td>
					<td>
						<html:select property="manager">
							<html:options collection="users" property="userid"
								labelProperty="username" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							<strong>権限組：</strong>
						</div>
					</td>
					<td>
						<html:select property="authgroupid">
							<html:options collection="authgroups" property="authgroupid"
								labelProperty="authgroupname" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							<strong>所属部門：</strong>
						</div>
					</td>
					<td>
						<html:select property="uplevel">
							<html:options collection="orgs" property="orgid"
								labelProperty="orgname" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							<strong>組織概要：</strong>
						</div>
					</td>
					<td>
						<html:textarea property="orgdiscription" cols="50" rows="10"></html:textarea>
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
