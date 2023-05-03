<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productView.jsp</title>
<link rel="stylesheet" type="text/css" href="css/product.css">
</head>
<body>
	<div id="wrap" align="center">
		<h1>상품 상세</h1>
		<table>
			<tr>
				<td width="250" align="center">
				<c:choose>
					<c:when test="${empty product.pictureurl}">
						<img src="upload/noname.jpg" width="200">
					</c:when>
						<c:otherwise>
						<img src="upload/${product.pictureurl}" width="200">
						</c:otherwise>
				</c:choose>
				</td>
				<td>
					<table>
						<tr>
							<th width="80">상 품 명</th>
							<td>${product.name}</td>
						</tr>
						<tr>
							<th>가 격</th>
							<td>${product.price}원</td>
						</tr>
						<tr>
							<th>설 명</th>
							<td><div style="height: 200px; width: 100%">
									<pre>${product.description}</pre>
							</div></td>
						</tr>
					</table>

				</td>

			</tr>
		</table><br>
		<input type="button" value="목록" onClick="location.href='product.do?command=index'">
	</div>


</body>
</html>