<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>権限組管理</title>
		<link href="css/table.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/pagination/page.js"></script>
		<script type="text/javascript" src="js/infMgr.js"></script>
		<style type="text/css">
<!--
.STYLE1 {
	font-size: 24px
}
-->
</style>
	</head>

	<body>
		<p>
			&nbsp;
		</p>
			<p align="center">
			<img border="0" src="images/Hein37.jpg" width="40" height="30">
			<font size="6" color="#0080ff">権限組管理</font>
		</p>
		<p>
			&nbsp;
		</p>
		<table cellspacing="0" align="center">
			<tr>

				<%-- 				<th class="left">	--%>
				<%-- 					<div align="center" class="STYLE5">	--%>
				<%-- 						項番	--%>
				<%-- 					</div>	--%>
				<%-- 				</th>	--%>

				<th width="168" class="left">
					<div align="center" class="STYLE5">
						権限組名
					</div>
				</th>
				<th width="168">
					<div align="center" class="STYLE5">
						作成日付
					</div>
				</th>
				<th>
					<div align="center" class="STYLE5">
						概要
					</div>
				</th>
				<th>
					<div align="center" class="STYLE5">
						管理
					</div>
				</th>
			</tr>
			<logic:iterate id="authoritygroup" collection="${authgroupList}"
				scope="request" type="jp.co.pegatron.domain.model.Authoritygroup">
				<tr class="question">

					<%-- 					<td class="left">	--%>
					<%-- 						${authoritygroup.authgroupid}&nbsp;	--%>
					<%-- 					</td>	--%>

					<td class="left">
						${authoritygroup.authgroupname}&nbsp;
					</td>
					<td>
						${authoritygroup.authgroupcreatetime}&nbsp;
					</td>
					<td>
						${authoritygroup.authgroupdiscription}&nbsp;
					</td>
					<td>
						<input type="button" value="基本編集" id="基本編集"
							onclick="openwindow('authgroupMgr.do?authgroupid=${authoritygroup.authgroupid}&task=edit','詳細',2)">
						<input type="button" value="権限配置" id="権限配置"
							onclick="openwindow('authgrpAuthMgr.do?authgroupid=${authoritygroup.authgroupid}&task=prepconf','権限配置',2)">
						<input type="button" value="削除" id="削除"
							onclick="del_par1('formDel','authgroupid','${authoritygroup.authgroupid}')">
					</td>
				</tr>
			</logic:iterate>
			<tr>
				<td class="left" colspan="10" align="left">
					<input type="button" value="追加" id="追加"
						onclick="openwindow('authgroupMgr/add.jsp','追加',2)">
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
		<form action="authgroupMgr.do" id="formPage" name="formPage"
			method="get">
			<input type="hidden" id="formPageViewTask" name="task" value="view">
			<input type="hidden" id="formPageViewScope" name="scope"
				value="${scope}">
			<script type="text/javascript">
				var pginfo = new PageInfo("${page_pginfo.pginfostr}",document.all.formPage);
				pginfo.write();
			</script>
		</form>
		<form action="authgroupMgr.do" id="formDel" name="formDel"
			method="get">
			<input type="hidden" id="delTask" name="task" value="delete">
			<input type="hidden" id="formDelViewScope" name="scope"
				value="${scope}">
			<input type="hidden" id="authgroupid" name="authgroupid" value="">
		</form>
	</body>
</html>
