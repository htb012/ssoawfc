<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.ssoserver.business.userMgr.UserMgr"%>
<%@page import="com.sso.service.UserSvc"%>
<%@page import="jp.co.pegatron.domain.model.Organization"%>
<%@page import="com.sso.service.OrgSvc"%>
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
		<title>社内組織管理</title>
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
			<img border="0" src="images/Hein33.jpg" width="40" height="30">
			<font size="6" color="#0080ff">組織管理</font>
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
						組織名
					</div>
				</th>
				<th width="168">
					<div align="center" class="STYLE5">
						権限組
					</div>
				</th>
				<th>
					<div align="center" class="STYLE5">
						組織リーダー
					</div>
				</th>
				<th>
					<div align="center" class="STYLE5">
						所属組織
					</div>
				</th>
				<th>
					<div align="center" class="STYLE5">
						組織概要
					</div>
				</th>
				<th>
					<div align="center" class="STYLE5">
						管理
					</div>
				</th>
			</tr>
			<logic:iterate id="organization" collection="${orgList}"
				scope="request" type="jp.co.pegatron.domain.model.Organization">
				<tr class="question">

					<%-- 					<td class="left">	--%>
					<%-- 						${organization.orgid}&nbsp;	--%>
					<%-- 					</td>	--%>

					<td class="left">
						${organization.orgname}&nbsp;
					</td>
					<td>
						${organization.authoritygroup.authgroupname}&nbsp;
					</td>
					<td>
						<%=UserSvc.getInstance().findById(
									((Organization) pageContext
											.getAttribute("organization"))
											.getManager()).getRealname()%>&nbsp;
					</td>
					<td>
						<%=OrgSvc.getInstance().findById(
									((Organization) pageContext
											.getAttribute("organization"))
											.getUplevel()).getOrgname()%>&nbsp;
					</td>
					<td>
						${organization.orgdiscription}&nbsp;
					</td>
					<td>
						<input type="button" value="編集" id="編集"
							onclick="openwindow('orgMgr.do?orgid=${organization.orgid}&task=edit','詳細',2)">
						<input type="button" value="メンバー管理" id="メンバー管理"
							onclick="openwindow('orgUserMgr.do?orgid=${organization.orgid}&task=prepconf','詳細',2)">
						<input type="button" value="削除" id="削除"
							onclick="del_par1('formDel','orgid','${organization.orgid}')">
					</td>
				</tr>
			</logic:iterate>
			<tr>
				<td class="left" colspan="10" align="left">
					<input type="button" value="追加" id="追加"
						onclick="openwindow('orgMgr.do?task=prepadd','追加',2)">
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
		<form action="orgMgr.do" id="formPage" name="formPage" method="get">
			<input type="hidden" id="formPageViewTask" name="task" value="view">
			<input type="hidden" id="formPageViewScope" name="scope"
				value="${scope}">
			<script type="text/javascript">
				var pginfo = new PageInfo("${page_pginfo.pginfostr}",document.all.formPage);
				pginfo.write();
			</script>
		</form>
		<form action="orgMgr.do" id="formDel" name="formDel" method="get">
			<input type="hidden" id="delTask" name="task" value="delete">
			<input type="hidden" id="formDelViewScope" name="scope"
				value="${scope}">
			<input type="hidden" id="orgid" name="orgid" value="">
		</form>
	</body>
</html>
