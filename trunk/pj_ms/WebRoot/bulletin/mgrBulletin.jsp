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
		<title>社内用オンライン業務システム</title>
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
		<p align="center" class="STYLE2 STYLE1">
			<img border="0" src="image/9.png" width="30" height="30"><font size="6" color="#0080ff">インフォメーション掲示板管理</font>
		</p>
		<table width="1083" height="101" border="1">
			<tr>
				<%-- 				<th width="60">	--%>
				<%-- 					<div align="center" class="STYLE5">	--%>
				<%-- 						項番		--%>
				<%-- 					</div>	--%>
				<%-- 				</th>	--%>
				<th width="80" class="left">
					<div align="center" class="STYLE5">
						日付
					</div>
				</th>
				<th width="100">
					<div align="center" class="STYLE5">
						情報提供者
					</div>
				</th>
				<th width="700">
					<div align="center" class="STYLE5">
						内容
					</div>
				</th>
				<th width="100">
					<div align="center" class="STYLE5">
						管理
					</div>
				</th>
			</tr>
			<logic:iterate id="bulletin" collection="${bulletinList}"
				scope="request" type="jp.co.pegatron.domain.model.Bulletin">
				<tr class="question">
					<%-- 					<td class="left">	--%>
					<%-- 						${bulletin.bulletinid}&nbsp;	--%>
					<%-- 					</td>	--%>
					<td class="left">
						${bulletin.publishdate}&nbsp;
					</td>
					<td>
						${bulletin.user.username}&nbsp;
					</td>
					<td>
						${bulletin.content}&nbsp;
					</td>
					<td>
						<input type="button" value="編集" id="編集"
							onclick="openwindow('bulletinMgr.do?bulletinid=${bulletin.bulletinid}&task=edit','詳細',2)">

						<input type="button" value="削除" id="削除"
							onclick="del_par1('formDel','bulletinid','${bulletin.bulletinid}')">
					</td>
				</tr>
			</logic:iterate>
			<tr>
				<td class="left" colspan="10" align="left">
					<input type="button" value="追加" id="追加"
						onclick="openwindow('bulletin/add.jsp','追加',2)">
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
		<form action="bulletinMgr.do" id="formPage" name="formPage"
			method="get">
			<input type="hidden" id="formPageViewTask" name="task" value="view">
			<input type="hidden" id="formPageViewScope" name="scope"
				value="${scope}">
			<script type="text/javascript">
				var pginfo = new PageInfo("${page_pginfo.pginfostr}",document.all.formPage);
				pginfo.write();
			</script>
		</form>
		<form action="bulletinMgr.do" id="formDel" name="formDel" method="get">
			<input type="hidden" id="delTask" name="task" value="delete">
			<input type="hidden" id="formDelViewScope" name="scope"
				value="${scope}">
			<input type="hidden" id="bulletinid" name="bulletinid" value="">
		</form>
	</body>
</html>
