<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<base href="<%=basePath%>" />
	<head>
		<title>单点登录系统</title>
	</head>
	<script type="text/javascript">
function check_img(){
	var image = document.getElementById('validateImage');
        image.src="servlet/CreateValideCode?flag="+Math.random();
  }
</script>
	<body bgcolor="#CCCCFF" onload="check_img()">
		<html:errors />
		<form action="Login/validate.do">
			<input type="hidden" id="loginTask" name="task" value="login" />
			<input type="hidden" id="accessnetsite" name="accessnetsite"
				value="<%=request.getParameter("accessnetsite")%>" />
			<input type="hidden" id="sessionid" name="sessionid"
				value="<%=request.getParameter("sessionid")%>" />
			<table align="center" border="0">
				<tr align="center">
					<td>
						<b>系统登录</b>
					</td>
				</tr>
				<tr>
					<td>
						用户名：
						<input type="text" name="username" size="20" />
					</td>
				</tr>
				<tr>
					<td>
						密&nbsp;&nbsp;码：
						<input type="password" name=password " size="20" />
					</td>
				</tr>

				<tr>
					<td>
						验证码：
						<input type="text" name="validateCode" size="20" />
						<a href="javascript:check_img()"><img id="validateImage"
								src="servlet/CreateValideCode" alt="双击改变" /> </a>
					</td>
				</tr>
				<tr align="center">
					<td>
						<input type="submit" value="确定" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value="取消" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="userMgr/register.jsp">注册</a>
					</td>
				</tr>
			</table>
		</form>
		<div align="right">
			<font size="2" color="#8080c0">用户名：admin<br> </font><font
				size="2" color="#8080c0">密码：admin</font>
			<br>
		</div>
	</body>
</html>
