<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>従業員情報チェック画面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<link href="css/table.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/infMgr.js"></script>
	<script type="text/javascript" src="js/pagination/page.js"></script>
	<script type="text/javascript">
		function edit(id){
			window.open("<%=basePath%>userMgr.do?id="+id+"&task=modify");
		}
		function del(id){
			var delFlag = window.confirm("削除してよろしいですか？");
			if (delFlag) {
				document.getElementById("id").value = id;
				document.all.form_del.submit();
			}
		}

		function register(){
			window.open("<%=basePath%>userMgr/editUser.jsp");
		}
	</script>
	<body>
		<p>
			&nbsp;
		</p>
			<p align="center">
			<img border="0" src="images/38.png" width="40" height="30">
			<font size="6" color="#0080ff">従業員情報管理</font>
		</p>
		<p>
			&nbsp;
		</p>
		<table cellspacing="0" align="center">
			<tr>

				<%-- 				<th class="left">	--%>
				<%--					項番	--%>
				<%--				</th>	--%>

				<th class="left">
					従業員ID
				</th>
				<th>
					従業員氏名
				</th>
				<th>
					パスワード
				</th>
				<th>
					性別
				</th>
				<th>
					連絡番号
				</th>
				<th>
					チーム名
				</th>
				<th>
					E-Mail
				</th>
				<th>
					管理
				</th>
			</tr>
			<logic:iterate collection="${userList}" id="user" scope="request"
				type="jp.co.pegatron.domain.model.User">
				<tr class="question">

					<%-- 					<td class="left">	--%>
					<%--						${user.userid}&nbsp;	--%>
					<%--					</td>	--%>

					<td class="left">
						${user.username}&nbsp;
					</td>
					<td>
						${user.realname}&nbsp;
					</td>
					<td>
						${user.password}&nbsp;
					</td>
					<td>
						${user.sex}&nbsp;
					</td>
					<td>
						${user.phone}&nbsp;
					</td>
					<td>
						${user.address}&nbsp;
					</td>
					<td>
						${user.email}&nbsp;
					</td>
					<td>
						<input type="button" value="編集" id="編集"
							onclick="edit('${user.userid}')">
						<input type="button" value="权限配置" id="权限配置"
							onclick="openwindow('authgrpUserMgr.do?userid=${user.userid}&task=prepconf','詳細',2)">
						<input type="button" value="削除" id="削除"
							onclick="del('${user.userid}')">
					</td>
				</tr>
			</logic:iterate>
			<tr>
				<td class="left" colspan="9" align="left">
					<input type="button" value="追加" id="追加" onclick="register()">
				</td>
			</tr>


		</table>
		<br />
		<form action="userMgr.do" name="form_view" method="get">
			<input type="hidden" id="viewTask" name="task" value="view">
			<script type="text/javascript">
				var pginfo = new PageInfo("${page_pginfo.pginfostr}",document.all.form_view);
				pginfo.write();
			</script>
		</form>

		<form action="userMgr.do" name=form_del method="get">
			<input type="hidden" id="delTask" name="task" value="del">
			<input type="hidden" id="id" name="id" value="">
		</form>
	</body>
</html>
