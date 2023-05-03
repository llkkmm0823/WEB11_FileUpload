<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productWriteForm.jsp</title>
<link rel="stylesheet" type="text/css" href="css/product.css">
<script src="script/product.js"></script>
</head>
<body>
	<div id="wrap" align="center">
		<h1>상품 등록 - 관리자페이지</h1>
		<form name="frm" method="post" action="product.do?command=productWrite"
			enctype="multipart/form-data">
			<!-- <input type="hidden" name="command" value="productWrite" /> 
			이렇게 작성할 수 있었지만 action에 붙여 쓴 이유는 따로 작성할 시 
			servlet에 mutipart형태로 전달이 불가능하여 null값으로 전달되기 때문-->
			<table>
				<tr>
					<th>상 품 명</th>
					<td><input type="text" name="name" size="80"></td>
				</tr>
				<tr>
					<th>가 격</th>
					<td><input type="text" name="price"> 원</td>
				</tr>
				<tr>
					<th>사 진</th>
					<td><input type="file" name="pictureurl"><br></td>
				</tr>
				<tr>
					<th>설 명</th>
					<td><textarea cols="80" rows="10" name="description"></textarea></td>
				</tr>
			</table>
			<br> <input type="submit" value="등록"> 
		<input type="reset" value="다시 작성">
		<input type="button" value="목록" onclick="location.href='product.do?command=index'">
		</form>
	</div>
</body>
</html>