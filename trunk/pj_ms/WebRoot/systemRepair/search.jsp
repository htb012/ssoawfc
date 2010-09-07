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
		<title>連絡事項検索</title>
		<style type="text/css">
<!--
.STYLE1 {
	font-size: 18px
}

.STYLE2 {
	color: #FF0000
}
-->
</style>
	</head>
	<body>
		<p align="center" class="STYLE1">
			&nbsp;
		</p>
		<p align="center" class="STYLE1">
			&nbsp;
		</p>
		<p align="center" class="STYLE1">
			<img border="0" src="image/164.png" width="40" height="30">
			<font size="6" color="#0080ff"> 連絡事項検索</font>
		</p>
		<form id="searchForm" action="orderMgr.do" name="searchForm"
			method="post">
			<input type="hidden" name="task" value="search">
			<p>
				&nbsp;
			</p>
			<p align="center" class="STYLE2">
				お客様名
				<input name="cname" type="text" />
			</p>
			<p align="center">
				<span class="STYLE2">モデル名</span>
				<input name="modelname" type="text" />
			</p>
			<p align="center">
				<span class="STYLE2">RMA番号</span>
				<input name="rmano" type="text" />
			</p>
			<p align="center">
				<span class="STYLE2">シリアル番号</span>
				<input name="sno" type="text" />
			</p>
			<p>
				&nbsp;
			</p>
			<p align="center">
				<input type="submit" name="Submit" value="検索" />
				<input type="reset" name="キャンセル" value="キャンセル" />
			</p>
		</form>
	</body>
</html>