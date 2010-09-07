<%@ page language="java" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
	<head>
		<base href="<%=basePath%>" />
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
		<p align="center" class="STYLE2 STYLE1"><img border="0" src="image/9.png" width="30" height="30"><font size="6" color="#0080ff">インフォメーション掲示板</font>
		</p>
		<table width="1083" height="106" border="1">
			<tr>
				<td width="125" height="32">
					<div align="center" class="STYLE5">
						項番
					</div>
				</td>
				<td width="168">
					<div align="center" class="STYLE5">
						日付
					</div>
				</td>
				<td width="768">
					<div align="center" class="STYLE5">
						内容
					</div>
				</td>
			</tr>
			<tr>
				<td height="23">
					<div align="center" class="STYLE6">
						1
					</div>
				</td>
				<td>
					<div align="center">
						<span class="STYLE6">2010年1月30日</span>
					</div>
				</td>
				<td>
					<div align="center">
						チーム会議
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						2
					</div>
				</td>
				<td>
					<div align="center">
						2010年2月1日
					</div>
				</td>
				<td>
					<div align="center">
						朝会
					</div>
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
	</body>
</html>
