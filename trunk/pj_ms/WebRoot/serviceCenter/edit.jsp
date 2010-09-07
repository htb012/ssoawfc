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
		<title>連絡事項詳細</title>
		<style type="text/css">
<!--
.STYLE2 {
	color: #FF0000
}

.STYLE3 {
	font-size: 18px
}
-->
</style>
	</head>
	<body>

		<p align="center" class="STYLE3">
			連絡事項詳細
		</p>
		<form id="form1" enctype="multipart/form-data" method="post" action=""
			name="form1">
			<p>
				お客様名
				<input name="textfield" type="text" />
			</p>
			<p>
				<span class="STYLE2">モデル名</span>
				<input name="textfield2" type="text" />
			</p>
			<p>
				<span class="STYLE2">RMA番号</span>
				<input name="textfield3" type="text" />
				−
				<input type="text" name="textfield9" />
			</p>
			<p>
				<span class="STYLE2">シリアル番号</span>
				<input name="textfield4" type="text" />
			</p>
			<p>
				<span class="STYLE2">ロケーション</span>
				<input name="textfield5" type="text" />
			</p>
			<p>
				関連部品
				<input name="textfield6" type="text" />
			</p>
			<p>
				部品番号
				<input name="textfield7" type="text" />
			</p>
			<p>
				待ち部品名
				<input name="textfield8" type="text" />
			</p>
			<div>
				<p>
					確認事項：
					<select name="select">
						<option value="">
						</option>
						<option value="">
							P1
						</option>
						<option value="">
							P2
						</option>
						<option value="">
							P3
						</option>
						<option value="">
							P4
						</option>
						<option value="">
							P5
						</option>
						<option value="">
							P6
						</option>
						<option value="">
							P7
						</option>
					</select>
				</p>
			</div>
			<p align="left">
				<span class="STYLE2">確認内容</span>
				<textarea name="example" cols="70" rows="10"></textarea>

			</p>
			<p>
				abcd.jpg&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="submit" type="submit" value="ダウンロード" />
			</p>
			<p>
				cdefgdfg.jpg&nbsp;&nbsp;
				<input name="submit2" type="submit" value="ダウンロード" />
			</p>
			<p align="center">
				<input type="submit" name="Submit" value="確定" />
				<input type="submit" name="Submit2" value="キャンセル" />
			</p>
		</form>
	</body>
</html>