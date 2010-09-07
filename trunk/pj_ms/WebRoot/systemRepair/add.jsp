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
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/infMgr.js"></script>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<title>連絡事項作成</title>
		<script type="text/javascript">
		/**
		 * chageCustomer
		 *
		 */
		 function chageCustomer() {
		 	var result = document.all.isagentrepair.checked;
		 	if(result == true){
		 		document.all.customername.disabled= true;
		 		document.all.customer.disabled= true;
		 		document.all.agentid.disabled = false;
		 		document.all.agent.disabled = false;
		 	}else{
		 		document.all.customername.disabled = false;
		 		document.all.customer.disabled= false;
		 		document.all.agentid.disabled = true;
		 		document.all.agent.disabled = true;
		 	}
		 }
		</script>
	</head>
	<body>
		<p align="center">
			<img border="0" src="image/Woa37.jpg" width="40" height="30">
			<font size="6" color="#0080ff"> 連絡事項作成</font>
		</p>
		<html:errors />
		<html:form action="orderMgr.do" enctype="multipart/form-data"
			method="post">
			<html:hidden property="task" value="add" />
			<html:hidden property="customerid" />
			<html:hidden property="startdate" />

			<p>
				※赤い項目が入力必須です。
			</p>

			<table>
				<tr>
					<html:checkbox property="isagentrepair" onclick="chageCustomer()"
						value="1"></html:checkbox>
					代理商
				</tr>

				<tr>
					<td id="customer">
						お客様名
					</td>
					<td>
						<html:text property="customername"></html:text>
					</td>
				</tr>

				<tr>
					<td>
						<p id="agent" disabled="true">
							代理商名
						</p>
					</td>
					<td>
						<html:select property="agentid" disabled="true">
							<html:options collection="agents" property="agentid"
								labelProperty="agentname" />
						</html:select>
					</td>
				</tr>

				<tr>
					<td>
						<font color="#ff0000"><span>モデル名</span> </font>
					</td>
					<td>
						<html:select property="modelid">
							<html:options collection="models" property="modelid"
								labelProperty="modelname" />
						</html:select>
					</td>
				</tr>

				<tr>
					<td>
						<font color="#ff0000"><span>RMA番号</span> </font>
					</td>
					<td>
						<html:text property="rmano"></html:text>
					</td>
				</tr>

				<tr>
					<td>
						<font color="#ff0000"><span class="STYLE2">シリアル番号</span> </font>
					</td>
					<td>
						<html:text property="sn"></html:text>
					</td>
				</tr>
				<!--
				<tr>
					<td>
						<font color="#ff0000"><span>Key日付</span></font>
					</td>
					<td>
						<html:text property="checkindatetime" maxlength="20"
							onfocus="calendar()" />
					</td>
				</tr>
-->
				<tr>
					<td>
						<font color="#ff0000"><span>ロケーション</span> </font>
					</td>
					<td>
						<html:text property="location"></html:text>
					</td>
				</tr>

				<tr>
					<td>
						関連部品
					</td>
					<td>
						<html:text property="parts"></html:text>
					</td>
				</tr>

				<tr>
					<td>
						部品番号
					</td>
					<td>
						<html:text property="pn"></html:text>
					</td>
				</tr>

				<tr>
					<td>
						待ち部品名
					</td>
					<td>
						<html:text property="waitparts"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						<font color="#ff0000"> CID/OOW</font>
					</td>
					<td>
						<p>
							<html:multibox property="warranty">1</html:multibox>
							IW
							<html:multibox property="warranty">10</html:multibox>
							OOW
							<html:multibox property="warranty">100</html:multibox>
							本体IW/BAOOW
							<html:multibox property="warranty">1000</html:multibox>
							CID
							<html:multibox property="warranty">10000</html:multibox>
							OOW/CID
						</p>
					</td>
				</tr>
				<tr>
					<td>
						<font color="#ff0000"> 確認事項</font>
					</td>
					<td>
						<p>
							<html:multibox property="validate">1</html:multibox>
							P1:パスワード確認
							<html:multibox property="validate">10</html:multibox>
							P2:有償修理確認
							<html:multibox property="validate">100</html:multibox>
							P3:症状再現せず
							<html:multibox property="validate">1000</html:multibox>
							P4:リカバリー確認
						</p>
						<p>
							<html:multibox property="validate">10000</html:multibox>
							P5:部品到着待ち
							<html:multibox property="validate">100000</html:multibox>
							P6:其他連絡事項
							<html:multibox property="validate">1000000</html:multibox>
							P7:保証期間以外
						</p>
					</td>
				</tr>

				<tr>
					<td>
						<span class="STYLE2">確認内容</span>
					</td>
					<td>
						<html:textarea property="problem" cols="70" rows="10"
							readonly="10">
						</html:textarea>
					</td>
				</tr>
			</table>
			<p>
				<input type="file" name="file">
				（CID写真を選択する）
			</p>
			<p id="fileSpace">
			</p>
			<input type="button" value="写真ファイルを追加"
				onclick="fileAdd(document.all.fileSpace)">

			<p align="center">
				<html:submit value="確定" />
				<html:reset value="キャンセル" onclick="javascript:window.close();"></html:reset>
			</p>
		</html:form>
	</body>
</html>