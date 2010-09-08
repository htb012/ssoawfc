<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>社内用オンライン業務システム</title>
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
			<input type="image" name="imageField" src="images/pegatronLogo.jpg" />
		</div>
		<div align="center" class="STYLE2">
			<img border="0" src="images/purelogo.JPG" width="38" height="31"><font color="#0080ff">社内用オンライン業務システム</font><img width="38" height="31" border="0" src="images/purelogo.JPG">
		</div>
		<%-- <p> --%>
		<%-- <a href="userMgr/editUser.jsp">新規登録</a> --%>
		<%-- </p> --%>
		<br>
		<html:form action="logonMgr.do">
			<html:hidden property="task" value="logon" />
			<input type="hidden" id="accessnetsite" name="accessnetsite"
				value="<%=request.getParameter("accessnetsite")%>" />
			<input type="hidden" id="sessionid" name="sessionid"
				value="<%=request.getParameter("sessionid")%>" />
			<p align="center" class="STYLE3">
				<img border="0" src="images/Hein83.jpg" width="27" height="27"><font color="#408080"><font size="6">ログイン画面</font>
				</font>
			</p>
			<p align="center">
				<html:errors />
			</p>
			<p align="center" class="STYLE3">
				&nbsp;
				<img width="31" height="31" border="0" src="images/Hein82.jpg"><font color="#408080" size="5" face="ＭＳ Ｐゴシック">従業員番号：</font>
				<font size="5"><html:text property="username"></html:text>
				</font>
			</p>
			<p align="center" class="STYLE3">
				<img width="31" height="31" border="0" src="images/Hein63.jpg"><font size="5" face="ＭＳ Ｐゴシック" color="#408080">パスワード：</font>
				<html:password property="password"></html:password>
			</p>
			<div align="center">
				<input type="image" alt="ログインする" src="images/39.png">
			</div>

		</html:form>
	</body>
</html>