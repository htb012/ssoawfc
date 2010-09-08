<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


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
		<title>従業員情報編集画面</title>
		<link href="css/table.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function init(){
				var task = document.all.task.value;
				if('update' != task){
					document.all.task.value = 'add';
				}
			}
		</script>
	</head>
	<body onload="init()">
		<html:errors />

		<p>
			&nbsp;
		</p>
			<p align="center">
			<img border="0" src="images/38.png" width="40" height="30">
			<font size="6" color="#0080ff">従業員情報</font>
		</p>
		<p>
			&nbsp;
		</p>

		<html:form action="userMgr.do">
			<html:hidden property="task" />
			<html:hidden property="userid" />
			<table width="560" border="1" align="center">
				<tr>
					<td colspan="4">
						<div align="center">
							従業員情報
						</div>
					</td>
				</tr>
				<tr>
					<td width="80">
						従業員ＩＤ:
					</td>
					<td width="194">
						<label>
							<html:text property="username"></html:text>
						</label>
					</td>
					<td width="80">
						従業員氏名:
					</td>
					<td width="185">
						<label>
							<html:text property="realname"></html:text>
						</label>
					</td>
				</tr>
				<tr>
					<td>
						パスワード:
					</td>
					<td>
						<label>
							<html:password property="password"></html:password>
						</label>
					</td>
					<td>
						パスワード確認:
					</td>
					<td>
						<label>
							<html:password property="repassword"></html:password>

						</label>
					</td>
				</tr>
				<tr>
					<td>
						性別:
					</td>
					<td>
						<label>
							<html:select property="sex">
								<html:option value="male">男性</html:option>
								<html:option value="female">女性</html:option>
							</html:select>
						</label>
					</td>
					<td>
						連絡番号:
					</td>
					<td>
						<label>
							<html:text property="phone" errorKey="cellPhone"></html:text>
						</label>
					</td>
				</tr>
				<tr>
					<td>
						チーム名:
					</td>
					<td>
						<label>
							<html:text property="address"></html:text>
						</label>
					</td>
					<td>
						E-Mail:
					</td>
					<td>
						<label>
							<html:text property="email"></html:text>
						</label>
					</td>
				</tr>
				<tr align="center">
					<td colspan="4">
						<label>
							<html:submit>確定</html:submit>
						</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<label>
							<html:reset value="取消" onclick="javascript:window.close();"></html:reset>
						</label>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>