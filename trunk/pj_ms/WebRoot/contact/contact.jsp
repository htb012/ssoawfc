<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="jp.co.pegatron.common.RepOrdPareseUtil"%>
<%@page import="jp.co.pegatron.domain.model.Contact"%>
<%@page import="jp.co.pegatron.domain.model.Repairorder"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="sso" uri="http://java.sun.com/jsp/jstl/sso"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>" />
		<link href="css/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/infMgr.js"></script>
		<script type="text/javascript" src="js/customerAddAgent.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>連絡事項詳細</title>
	</head>
	<body onload="showCstOrAgt(${repairorder.isagentrepair})">
		<p align="center">
			<img border="0" src="image/2.png" width="40" height="30">
			<font size="6" color="#0080ff"> 連絡事項詳細</font>
		</p>
		<html:errors />
		<table>
			<tr>
				<td>
					※修理${repairCount}回目
				</td>
				<td>

				</td>
			</tr>

			<tr id="customerText">
				<td id="customer">
					お客様名
				</td>
				<td>
					<input type="text" value="${repairorder.customer.customername}"
						readonly="readonly" />
				</td>
			</tr>

			<tr id="agentText">
				<td>
					<p id="agent">
						代理商名
					</p>
				</td>
				<td>
					<input type="text" value="${repairorder.agent.agentname}"
						readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>
					<font color="#ff0000"><span>モデル名</span> </font>
				</td>
				<td>
					<input type="text" value="${repairorder.model.modelname}"
						readonly="readonly" />
				</td>
			</tr>

			<tr>
				<td>
					<font color="#ff0000"><span>RMA番号</span> </font>
				</td>
				<td>
					<input type="text" value="${repairorder.rmano}" readonly="readonly" />
				</td>
			</tr>

			<tr>
				<td>
					<font color="#ff0000"><span class="STYLE2">シリアル番号</span> </font>
				</td>
				<td>
					<input type="text" value="${repairorder.sn}" readonly="readonly" />
				</td>
			</tr>

			<tr>
				<td>
					<font color="#ff0000"><span>ロケーション</span> </font>
				</td>
				<td>
					<input type="text" value="${repairorder.location}"
						readonly="readonly" />
				</td>
			</tr>

			<tr>
				<td>
					関連部品
				</td>
				<td>
					<input type="text" value="${repairorder.parts}" readonly="readonly" />
				</td>
			</tr>

			<tr>
				<td>
					部品番号
				</td>
				<td>
					<input type="text" value="${repairorder.pn}" readonly="readonly" />
				</td>
			</tr>

			<tr>
				<td>
					待ち部品名
				</td>
				<td>
					<input type="text" value="${repairorder.waitparts}"
						readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>
					<font color="#ff0000"> CID/OOW</font>
				</td>
				<td>
					<p>
						<%=RepOrdPareseUtil.parseWarrantyStr(((Repairorder) request
							.getAttribute("repairorder")).getWarranty())%>&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td>
					<font color="#ff0000"> 確認事項</font>
				</td>
				<td>
					<%=RepOrdPareseUtil.parseConfimStr(((Repairorder) request
							.getAttribute("repairorder")).getValidate())%>&nbsp;
				</td>
			</tr>

			<tr>
				<td>
					<span class="STYLE2">確認内容</span>
				</td>
				<td>
					<textarea rows="3" cols="50" readonly="readonly">${repairorder.problem}</textarea>
				</td>
			</tr>
		</table>

		<p>
			&nbsp;
			<br>
		</p>
		<input type="hidden" id="imgDelStr" name="imgDelStr">
		<logic:iterate id="photo" collection="${photos}"
			type="java.lang.String" indexId="indexId">
			<span id="id${indexId}"> <a href="${photo}" target="_blank"><img
						src="${photo}" width="100" height="100"> </a> </span>
		</logic:iterate>
		<br />
		<br />
		<input type="button" value="詳細内容編集" id="詳細内容編集"
			onclick="openwindow('orderMgr.do?id=${repairorder.repairorderid}&task=particular','詳細',2)">
		<hr>
		<html:form action="contactMgr.do" method="post">
			<html:hidden property="task" value="add" />
			<html:hidden property="repairorderid"
				value="${repairorder.repairorderid}" />
			<table>
				<tr>
					<td>
						処理種類：
					</td>
					<td>
						<select name="repairorderstate">
							<sso:CheckPermisstion res="/pj_ms/contact/logoned">
								<option value="登録済み" />
									登録済み
							</sso:CheckPermisstion>
							<sso:CheckPermisstion res="/pj_ms/contact/contactStart">
								<option value="連絡開始" />
									連絡開始
							</sso:CheckPermisstion>
							<sso:CheckPermisstion res="/pj_ms/contact/waitResponse">
								<option value="回答待ち" />
									回答待ち
							</sso:CheckPermisstion>
							<sso:CheckPermisstion res="/pj_ms/contact/contacted">
								<option value="連絡完了" />
									連絡完了
							</sso:CheckPermisstion>
							<sso:CheckPermisstion res="/pj_ms/contact/updated">
								<option value="更新済み" />
									更新済み
							</sso:CheckPermisstion>
							<sso:CheckPermisstion res="/pj_ms/contact/repaired">
								<option value="修理完了" />
									修理完了
							</sso:CheckPermisstion>
							<sso:CheckPermisstion res="/pj_ms/contact/finished">
								<option value="業務終了" />
									業務終了
							</sso:CheckPermisstion>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<font color="#ff0000"> 確認事項</font>：
					</td>
					<td>
						<html:multibox property="contactvalidate">1</html:multibox>
							P1:パスワード確認
						<html:multibox property="contactvalidate">10</html:multibox>
							P2:有償修理確認
						<html:multibox property="contactvalidate">100</html:multibox>
							P3:症状再現せず
						<html:multibox property="contactvalidate">1000</html:multibox>
							P4:リカバリー確認
						<html:multibox property="contactvalidate">10000</html:multibox>
							P5:部品到着待ち
						<html:multibox property="contactvalidate">100000</html:multibox>
							P6:其他連絡事項
						<html:multibox property="contactvalidate">1000000</html:multibox>
							P7:保証期間以外
					</td>
				</tr>
				<tr>
					<td>
						<span class="STYLE2">連絡内容</span>
					</td>
					<td>
						<html:textarea property="customresponse" cols="70" rows="10"></html:textarea>
					</td>
				</tr>
			</table>

			<p align="center">
				<html:submit value="確定" />
				<html:reset value="キャンセル" onclick="javascript:window.close();"></html:reset>
			</p>
		</html:form>
		<input id="showContact" type="button" value="全ての連絡履歴を表示する"
			onclick="showDiv(document.all.contactLayer,document.all.showContact)">
		<div id="contactLayer">
			<logic:iterate id="contact" collection="${contacts}" scope="request"
				type="jp.co.pegatron.domain.model.Contact">
				<table width="1015" height="21" border="1">
					<tr>
						<td width="186" bgcolor="#CCCCCC">
							処理時間
						</td>
						<td width="308" bgcolor="#CCCCCC">
							${contact.contactdatetime}&nbsp;
						</td>
						<td width="308" bgcolor="#CCCCCC">
							担当者
						</td>
						<td width="308" bgcolor="#CCCCCC">
							${contact.user.realname}&nbsp;
						</td>
						<td width="308" bgcolor="#CCCCCC">
							確認事項
						</td>
						<td width="308" bgcolor="#CCCCCC">
							<%=RepOrdPareseUtil
									.parseConfimStr(((Contact) pageContext
											.getAttribute("contact"))
											.getContactvalidate())%>&nbsp;
						</td>
					</tr>
					<tr>
						<td bgcolor="#CCCCCC">
							連絡内容
						</td>
						<td bgcolor="#CCCCCC" colspan="5">
							${contact.customresponse}&nbsp;
						</td>
					</tr>
				</table>
				<p>
					&nbsp;
				</p>
				<p>
					&nbsp;
				</p>
			</logic:iterate>
		</div>
	</body>
</html>