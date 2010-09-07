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
		<p align="center" class="STYLE2">
			<img border="0" src="image/9.png" width="30" height="30"><font size="6"><font color="#0080ff">インフォメーション掲示板</font> </font>
		</p>
		<table width="1083" border="0">
			<tr>

				<%-- 				<th width="125" class="left">	--%>
				<%-- 					<div align="center" class="STYLE5">	--%>
				<%-- 						項番	--%>
				<%-- 					</div>	--%>
				<%-- 				</th>	--%>

				<th width="168" class="left">
					<div align="center" class="STYLE5">
						日付
					</div>
				</th>
				<th width="168">
					<div align="center" class="STYLE5">
						情報提供者
					</div>
				</th>
				<th width="768">
					<div align="center" class="STYLE5">
						内容
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
				</tr>
			</logic:iterate>
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
	</body>
</html>
