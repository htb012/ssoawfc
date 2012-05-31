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
		<p align="center">
			<img border="0" src="image/HeinH11.jpg" width="40" height="30">
			<font size="6" color="#0080ff">媒体类型管理</font>
		</p>

		<table width="1083" height="101" border="1">
			<tr>

				<%-- 				<th width="125">	--%>
				<%-- 					<div align="center" class="STYLE5">	--%>
				<%-- 						項番	--%>
				<%-- 					</div>	--%>
				<%-- 				</th>	--%>

				<th width="168" class="left">
					<div align="center" class="STYLE5">
						类型名
					</div>
				</th>
				<th>
					<div align="center" class="STYLE5">
						标识后缀
					</div>
				</th>
				<th>
					<div align="center" class="STYLE5">
						文件后缀
					</div>
				</th>
				<th>
					<div align="center" class="STYLE5">
						管理
					</div>
				</th>
			</tr>
			<logic:iterate id="mediaType" collection="${mediaTypeList}" scope="request"
				type="cn.yohan.damain.model.Mediatype">
				<tr class="question">

					<%-- 					<td class="left">	--%>
					<%-- 						${model.modelid}&nbsp;	--%>
					<%-- 					</td>	--%>

					<td class="left">
						${mediaType.mediaTypeName}&nbsp;
					</td>
					<td>
						${mediaType.fileNameSuffix}&nbsp;
					</td>
					<td>
						${mediaType.fileSuffix}&nbsp;
					</td>
					<td>
						<input type="button" value="編集" id="編集"
							onclick="openwindow('mediaTypeMgr.do?mediaTypeid=${mediaType.mediaTypeId}&task=edit','詳細',2)">

						<input type="button" value="削除" id="削除"
							onclick="del_par1('formDel','mediaTypeid','${mediaType.mediaTypeId}')">
					</td>
				</tr>
			</logic:iterate>
			<tr>
				<td class="left" colspan="10" align="left">
					<input type="button" value="追加" id="追加"
						onclick="openwindow('mediaType/add.jsp','追加',2)">
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
		<form action="mediaTypeMgr.do" id="formPage" name="formPage" method="get">
			<input type="hidden" id="formPageViewTask" name="task" value="view">
			<input type="hidden" id="formPageViewScope" name="scope"
				value="${scope}">
			<script type="text/javascript">
				var pginfo = new PageInfo("${page_pginfo.pginfostr}",document.all.formPage);
				pginfo.write();
			</script>
		</form>
		<form action="mediaTypeMgr.do" id="formDel" name="formDel" method="get">
			<input type="hidden" id="delTask" name="task" value="delete">
			<input type="hidden" id="formDelViewScope" name="scope"
				value="${scope}">
			<input type="hidden" id="modelid" name="modelid" value="">
		</form>
	</body>
</html>
