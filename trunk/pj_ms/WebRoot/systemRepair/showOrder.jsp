<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="jp.co.pegatron.common.RepOrdPareseUtil"%>
<%@page import="jp.co.pegatron.domain.model.Repairorder"%>
<%@page import="jp.co.pegatron.common.RepOrdContactUtil"%>
<%@taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
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
		<link href="css/table.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/pagination/page.js"></script>
		<script type="text/javascript" src="js/infMgr.js"></script>
		<script type="text/javascript">
			function init(){
				var scope = document.all.formViewScope.value;
				if(scope == 'all'){
					document.all.isViewAll.checked = true;
				}
			}
		</script>
	</head>
	<body onload="init()">
		<p>
			&nbsp;
		</p>
		<p align="center">
			<img border="0" src="image/16.png" width="40" height="30">
			<font size="6" color="#0080ff"><span class="STYLE4">業務連絡事項一覧</span>
			</font>
		</p>

		<p align="left">
			<input type="checkbox" id="isViewAll" name="isViewAll" value="true"
				onclick="changeViewScope()" />
			其他の連絡事項を含めて全て表示する
		</p>


		<table width="1091" align="center">
			<tr>

				<%-- 				<th class="left" width="40" height="40">	--%>
				<%-- 					内部	--%>
				<%-- 					<br>	--%>
				<%-- 					項番	--%>
				<%-- 				</th>	--%>

				<th width="60" class="left">
					登録
					<br>
					日付
				</th>

				<!-- 				<th width="60">
					KeyIn
					<br>
					日付
				</th>	-->

				<th width="80">
					修理
					<br>
					担当者
				</th>
				<th width="40">
					置く
					<br>
					場所
				</th>
				<th width="80">
					RMA
					<br>
					番号
				</th>
				<th width="60">
					Model
					<br>
					番号
				</th>
				<th width="60">
					S/N
					<br>
					番号
				</th>
				<th width="100">
					最終
					<br>
					返答日
				</th>
				<th width="100">
					連絡
					<br>
					担当者
				</th>
				<th width="100">
					確認
					<br>
					事項
				</th>
				<th width="106">
					処理
					<br>
					状況
				</th>
				<th width="106">
					CID/OOW
				</th>
				<th width="40">
					管理
				</th>
			</tr>
			<logic:iterate id="repairOrder" collection="${repairOrderList}"
				scope="request" type="jp.co.pegatron.domain.model.Repairorder">
				<tr class="question">

					<%-- 					<td class="left">	--%>
					<%--						${repairOrder.repairorderid}&nbsp;	--%>
					<%--					</td>	--%>

					<td class="left">
						<bean:write format="yyyy-MM-dd" name="repairOrder"
							property="startdate" />
					</td>

					<%-- 					<td>	--%>
					<%--						${repairOrder.checkindatetime}&nbsp;	--%>
					<%--					</td>	--%>

					<td>
						${repairOrder.user.realname}&nbsp;
					</td>
					<td>
						${repairOrder.location}&nbsp;
					</td>
					<td>
						${repairOrder.rmano}&nbsp;
					</td>
					<td>
						${repairOrder.model.modelname}&nbsp;
					</td>
					<td>
						${repairOrder.sn}&nbsp;
					</td>
					<td>
						<%=RepOrdContactUtil
											.getLastContactdatetime(((Repairorder) pageContext
													.getAttribute("repairOrder"))
													.getContacts())%>&nbsp;
					</td>
					<td>
						<%=RepOrdContactUtil
											.getLastContacter(((Repairorder) pageContext
													.getAttribute("repairOrder"))
													.getContacts())%>&nbsp;
					</td>
					<td>
						<%=RepOrdPareseUtil
									.parseConfimStr(((Repairorder) pageContext
											.getAttribute("repairOrder"))
											.getValidate())%>&nbsp;
					</td>
					<td>
						${repairOrder.repairorderstate}&nbsp;
					</td>
					<td>
						<%=RepOrdPareseUtil
											.parseWarrantyStr(((Repairorder) pageContext
													.getAttribute("repairOrder"))
													.getWarranty())%>&nbsp;
					</td>
					<td>
						<input type="button" value="詳細" id="詳細"
							onclick="openwindow('orderMgr.do?id=${repairOrder.repairorderid}&task=particular','詳細',2)">
						<input type="button" value="連絡" id="連絡"
							onclick="openwindow('contactMgr.do?id=${repairOrder.repairorderid}&task=view','連絡',2)">
						<input type="button" value="削除" id="削除"
							onclick="del_par1('formDel','id','${repairOrder.repairorderid}')">
					</td>
				</tr>
			</logic:iterate>
		</table>
		<form action="orderMgr.do" id="formPage" name="formPage" method="get">
			<input type="hidden" id="formPageViewTask" name="task" value="view">
			<input type="hidden" id="formPageViewScope" name="scope"
				value="${scope}">
			<input type="hidden" id="formPageOldScope" name="oldScope"
				value="${oldScope}">
			<script type="text/javascript">
				var pginfo = new PageInfo("${page_pginfo.pginfostr}",document.all.formPage);
				pginfo.write();
			</script>
		</form>
		<form action="orderMgr.do" id="formDel" name="formDel" method="get">
			<input type="hidden" id="delTask" name="task" value="delete">
			<input type="hidden" id="formDelViewScope" name="scope"
				value="${scope}">
			<input type="hidden" id="formDelOldScope" name="oldScope"
				value="${oldScope}">
			<input type="hidden" id="id" name="id" value="">
		</form>

		<form action="orderMgr.do" id="formView" name="formView" method="get">
			<input type="hidden" id="formViewTask" name="task" value="view">
			<input type="hidden" id="formViewScope" name="scope" value="${scope}">
			<input type="hidden" id="formViewOldScope" name="oldScope"
				value="${oldScope}">
		</form>
	</body>
</html>
