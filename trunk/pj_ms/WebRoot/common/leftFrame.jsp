<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.ssoserver.business.userMgr.UserMgr"%>
<%@page import="com.ssoserver.common.rmi.UserMgrFactory"%>
<%@taglib prefix="sso" uri="http://java.sun.com/jsp/jstl/sso"%>
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
		<link href="css/tree.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/date.js"></script>
		<script type="text/javascript" src="js/leftMenu.js"></script>
	</head>

	<body>
		&nbsp;
		<!-- <div align="center">
			<script type="text/javascript">time();</script>
		</div> -->
		<div align="center">
			<em><br> <font color="#408080"><strong></strong> </font> </em>
			<img border="0" src="image/Favourites.jpg">
			<em><font color="#408080"><strong>登録者：<%=UserMgrFactory.getUserMgr().getCurrentUser(
							request.getSession().getAttribute(UserMgr.SECKEY)
									.toString()).getRealname()%> <a
						href="/sso/logonMgr.do?task=logout">退出</a> </strong> </font> </em>
			<br>
		</div>
		<br>
		<div id="PARENT">
			<ul id="nav">
				<sso:CheckPermisstion res="/pj_ms/systemRepair">
					<li>
						<a href="bulletinMgr.do?task=view&scope=repairer"
							onclick="DoMenu('ChildMenu1')" target="mainFrame"><img
								border="0" src="image/Hein22.jpg" width="20" height="20">
							システムリペア業務</a>
						<ul id="ChildMenu1" class="collapsed">
							<li>
								<a href="orderMgr.do?task=view&scope=repairer"
									target="mainFrame"><img border="0" src="image/3.png"
										width="20" height="20"> 連絡事項一覧</a>
							</li>
							<li>
								<a href="orderMgr.do?task=prepadd"
									target="mainFrame"><img border="0" src="image/Woa37.jpg"
										width="20" height="20"> 連絡事項作成</a>
							</li>
							<li>
								<a href="systemRepair/search.jsp"
									target="mainFrame"><img border="0" src="image/164.png"
										width="20" height="20"> 連絡事項検索</a>
							</li>
							<li>
								<a href="orderMgr.do?task=view&scope=finish"
									target="mainFrame"><img border="0" src="image/201.png"
										width="20" height="20"> 完了事項一覧</a>
							</li>
							<li>
								<a href="bulletinMgr.do?task=manage&scope=repairer"
									target="mainFrame"><img border="0" src="image/79.png"
										width="20" height="20"> お知らせ管理</a>
							</li>
						</ul>
					</li>
				</sso:CheckPermisstion>
				<!--
				<li>
					<a href="bulletin/" onclick="DoMenu('ChildMenu2')"  target="mainFrame">マザーボードリペア関連業務</a>
					<ul id="ChildMenu2" class="collapsed">
						<li>
							<a href="" target="mainFrame">文件1</a>
						</li>
						<li>
							<a href="" target="mainFrame">文件夹1</a>
						</li>
						<li>
							<a href="" target="mainFrame">系统1</a>
						</li>
					</ul>
				</li>
 -->
				<sso:CheckPermisstion res="/pj_ms/serviceCenter">
					<li>
						<a href="bulletinMgr.do?task=view&scope=contacter"
							onclick="DoMenu('ChildMenu3')" target="mainFrame"><img
								border="0" src="image/Hein031.jpg" width="20" height="20">
							サービスセンター業務</a>
						<ul id="ChildMenu3" class="collapsed">
							<li>
								<a href="orderMgr.do?task=view&scope=untreated"
									target="mainFrame"><img border="0" src="image/3.png"
										width="20" height="20"> 未処理事項一覧</a>
							</li>
							<li>
								<a href="orderMgr.do?task=view&scope=treating"
									target="mainFrame"><img border="0" src="image/2.png"
										width="20" height="20"> 処理中事項一覧</a>
							</li>
							<li>
								<a href="systemRepair/search.jsp"
									target="mainFrame"><img border="0" src="image/164.png"
										width="20" height="20"> 連絡事項検索</a>
							</li>
							<li>
								<a href="orderMgr.do?task=view&scope=treated"
									target="mainFrame"><img border="0" src="image/201.png"
										width="20" height="20"> 処理済事項一覧</a>
							</li>
							<li>
								<a href="bulletinMgr.do?task=manage&scope=contacter"
									target="mainFrame"><img border="0" src="image/79.png"
										width="20" height="20"> お知らせ管理</a>
							</li>
						</ul>
					</li>
				</sso:CheckPermisstion>
				<sso:CheckPermisstion res="/pj_ms/manageBus">
					<li>
						<a href="bulletinMgr.do?task=view&scope=community"
							onclick="DoMenu('ChildMenu4')" target="mainFrame"><img
								border="0" src="image/Hein06.jpg" width="20" height="20">
							管理業務</a>
						<ul id="ChildMenu4" class="collapsed">
							<li>
								<a href="agentMgr.do?task=view&scope=all"
									target="mainFrame"><img border="0" src="image/173.png"
										width="20" height="20"> 代理商管理</a>
							</li>
							<li>
								<a href="modelMgr.do?task=view&scope=all"
									target="mainFrame"><img border="0" src="image/HeinH11.jpg"
										width="20" height="20"> 機種管理</a>
							</li>
							<li>
								<a href="bulletinMgr.do?task=manage&scope=community"
									target="mainFrame"><img border="0" src="image/79.png"
										width="20" height="20"> お知らせ管理</a>
							</li>
							<li>
								<a href="/sso/userMgr.do?task=view"
									target="mainFrame"><img border="0" src="image/Hein82.jpg"
										width="20" height="20"> 従業員情報管理</a>
							</li>
							<li>
								<a href="/sso/resMgr.do?task=view"
									target="mainFrame"><img border="0" src="image/Hein72.jpg"
										width="20" height="20"> リソース管理</a>
							</li>
							<li>
								<a href="/sso/metaauthMgr.do?task=view"
									target="mainFrame"><img border="0" src="image/Hein39.jpg"
										width="20" height="20"> 権限種類管理</a>
							</li>
							<li>
								<a href="/sso/authMgr.do?task=view"
									target="mainFrame"><img border="0" src="image/Hein38.jpg"
										width="20" height="20"> リソース権限管理</a>
							</li>
							<li>
								<a href="/sso/authgroupMgr.do?task=view"
									target="mainFrame"><img border="0" src="image/Hein37.jpg"
										width="20" height="20"> 権限組管理</a>
							</li>
							<li>
								<a href="/sso/orgMgr.do?task=view"
									target="mainFrame"><img border="0" src="image/Hein33.jpg"
										width="20" height="20"> 社内組織管理</a>
							</li>

						</ul>
					</li>
				</sso:CheckPermisstion>
			</ul>
		</div>
	</body>
</html>