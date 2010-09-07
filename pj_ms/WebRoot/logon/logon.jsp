<%@ page language="java" pageEncoding="UTF-8"%>

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

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style type="text/css">
<!--
.STYLE2 {
	font-size: 36px;
	font-weight: bold;
	font-style: italic;
}

.STYLE3 {
	font-size: 24px
}
-->
</style>
	</head>

	<body>

		<div align="left">
			<img name="imageField" src="<%=basePath%>image/pegatronLogo.jpg">
		</div>
		<form id="form1" name="form1" method="post"
			action="<%=basePath%>logon.do">
			<div align="center" class="STYLE2"><img border="0" src="/image/purelogo.JPG"><font color="#0080ff">
				社内用オンライン業務システム</font><img border="0" src="/image/purelogo.JPG">
			</div>
			<p>
				&nbsp;
			</p>
			<p align="center" class="STYLE3">
				ログイン画面
			</p>
			<p align="center" class="STYLE3">
				従業員番号：
				<input type="text" name="id" size="20" maxlength="6" value="" />
			</p>
			<p align="center" class="STYLE3">
				パスワード ：
				<input type="text" name="id" size="20" maxlength="6" value="" />
			</p>
			<p align="center" class="STYLE3">
				<input type="submit" value="ログイン" />
			</p>
		</form>

	</body>
</html>