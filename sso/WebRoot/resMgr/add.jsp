<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<script type="text/javascript" src="js/infMgr.js"></script>
		<script type="text/javascript" src="js/JQuery/jquery.min.js"></script>
		<script type="text/javascript" src="js/JQuery/tiper.js"></script>
		<script type="text/javascript">
			$(document).ready(function($){
		    	tiper();
			}) 
		</script>
		<title>社内用オンライン業務システム</title>
		<link rel="stylesheet" type="text/css" href="css/c3.css" />
		<link rel="stylesheet" type="text/css" href="css/JQueryTiper.css" />
	</head>

	<body>
		<html:errors />
		<p>
			&nbsp;
		</p>
		<p align="center">
			<img border="0" src="images/Hein72.jpg" width="40" height="30">
			<font size="6" color="#0080ff">リソース追加</font>
		</p>
		<p>
			&nbsp;
		</p>
		<html:form action="resMgr.do">
			<html:hidden property="task" value="add" />
			<html:hidden property="resid" />
			<p>
				リソース名：
				<html:text property="resname" maxlength="50" size="50"></html:text>
				支持
				<span class="tiper" id="wildcard">通配符</span>的使用。
			</p>
			<div class='wildcard' style='display: none;'>
				<table class="dataintable">
					<tr>
						<th>
							通配符
						</th>
						<th>
							描述
						</th>
					</tr>
					<tr>
						<td>
							%
						</td>
						<td>
							替代一个或多个字符
						</td>
					</tr>
					<tr>
						<td>
							_
						</td>
						<td>
							仅替代一个字符
						</td>
					</tr>
					<tr>
						<td>
							[charlist]
						</td>
						<td>
							字符列中的任何单一字符
						</td>
					</tr>
					<tr>
						<td>
							<p>
								[^charlist]
							</p>
							<p>
								或者
							</p>
							<p>
								[!charlist]
							</p>
						</td>
						<td>
							不在字符列中的任何单一字符
						</td>
					</tr>
				</table>
			</div>

			<p>
				リソース概要：
				<html:textarea property="resdiscription" rows="3" cols="50"></html:textarea>
			</p>

			<p align="center">
				<html:submit value="確定" />
				<html:reset value="キャンセル" onclick="javascript:window.close();"></html:reset>
			</p>
		</html:form>

		<p>
			&nbsp;
		</p>
	</body>
</html>
