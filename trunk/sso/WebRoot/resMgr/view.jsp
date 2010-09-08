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
		<title>リソース管理</title>
		<link href="css/tablesorter.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/JQuery/jquery.min.js"></script>
		<script type="text/javascript"
			src="js/JQuery/jquery.tablesorter.min.js"></script>
		<script type="text/javascript" src="js/pagination/page.js"></script>
		<script type="text/javascript" src="js/infMgr.js"></script>
		<script type="text/javascript">
           $(document).ready(function(){
            $("#resView").tablesorter();
           });
        </script>
	</head>

	<body>
		<p>
			&nbsp;
		</p>
		<p align="center">
			<img border="0" src="images/Hein72.jpg" width="40" height="30">
			<font size="6" color="#0080ff">リソース管理</font>
		</p>
		<p>
			&nbsp;
		</p>
		<table id="resView" class="tablesorter">
			<thead>
				<tr>

					<%--                <th class="left">   --%>
					<%--                    <div align="center" class="STYLE5"> --%>
					<%--                        項番  --%>
					<%--                    </div>  --%>
					<%--                </th>   --%>

					<th>
						リソース名
					</th>
					<th>
						リソース概要
					</th>
					<th>
						管理
					</th>
				</tr>
			</thead>
			<tbody>
				<logic:iterate id="resource" collection="${resList}" scope="request"
					type="jp.co.pegatron.domain.model.Resource">
					<tr class="question">

						<%--                    <td class="left">   --%>
						<%--                        ${resource.resid}&nbsp; --%>
						<%--                    </td>   --%>

						<td class="left">
							${resource.resname}&nbsp;
						</td>
						<td>
							${resource.resdiscription}&nbsp;
						</td>
						<td>
							<input type="button" value="編集" id="編集"
								onclick="openwindow('resMgr.do?resid=${resource.resid}&task=edit','詳細',2)">

							<input type="button" value="削除" id="削除"
								onclick="del_par1('formDel','resid','${resource.resid}')">
						</td>
					</tr>
				</logic:iterate>
				<tr>
					<td class="left" colspan="10" align="left">
						<input type="button" value="追加" id="追加"
							onclick="openwindow('resMgr/add.jsp','追加',2)">
					</td>
				</tr>
			</tbody>
		</table>
		<form action="resMgr.do" id="formPage" name="formPage" method="get">
			<input type="hidden" id="formPageViewTask" name="task" value="view">
			<input type="hidden" id="formPageViewScope" name="scope"
				value="${scope}">
			<script type="text/javascript">
                var pginfo = new PageInfo("${page_pginfo.pginfostr}",document.all.formPage);
                pginfo.write();
            </script>
		</form>
		<form action="resMgr.do" id="formDel" name="formDel" method="get">
			<input type="hidden" id="delTask" name="task" value="delete">
			<input type="hidden" id="formDelViewScope" name="scope"
				value="${scope}">
			<input type="hidden" id="resid" name="resid" value="">
		</form>
	</body>
</html>
