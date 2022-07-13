<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% 
	String userID = (String) session.getAttribute("userID");
	String userPassword = (String) session.getAttribute("userPassword");
	boolean login = userID != null ? true : false;
%>
<!-- 세션에 저장된 로그인 정보 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trip Of Life</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- 구글 폰트 사용 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
<style type="text/css">
.nav-left {
	background-color: #f8f9fa;
	width: 15em;
	height: 31em;
	list-style-type: none;
	margin: 3em 0;
}

.container-pos {
	width: 18em;
}

#container {
	margin-top: 50px;
}

h5 {
	font-weight: 400;
	margin-left: 0.5em;
}

h6 {
	font-weight: 400;
	margin-left: 0.5em;
}

a {
	text-decoration: none;
	color: black;
}

a:hover {
	font-weight: 900;
}

li a {
	display: block;
	color: #000000;
	padding: 8px;
	margin-left: 0.5em;
	text-decoration: none;
}

li a:hover {
	background-color: gray;
}

.navbar-brand {
	font-family: 'Anton', sans-serif;
}
</style>
</head>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="./" style="margin-right: 30px;">Trip
				Of Life</a>

			<!-- 글쓰기 버튼 -->
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4"
				style="text-align: center;">
				<li class="btn btn-light"><a class="nav-link" href="./content">여행
						등록하기</a></li>
			</ul>
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
				<form action="myContentGo">
					<input type="hidden" name="userID" value="${userID}" />
					<li class="btn btn-light"><input class="nav-link"
						type="submit" value="내 여행지 관리"
						style="border: none; background-color: #FAFAFA;"></li>
				</form>
			</ul>
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
				<form action="myContentResGo">
					<input type="hidden" id="idx" name="userID" value="${userID}" />
					<li class="btn btn-light"><input class="nav-link"
						type="submit" value="찜한 내역 확인"
						style="border: none; background-color: #FAFAFA;"></li>
				</form>
			</ul>
			<form action="logout.nhn">
				<button class="btn btn-outline-dark" type="submit"
					style="margin: 10px">
					<a href="./login" style="text-decoration: none; color: black;">logout</a>
				</button>
			</form>
		</div>
	</nav>

	<!-- side nav -->
	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav container-pos">
				<ul class="nav-left">
					<h6 style="padding-top: 15px; font-weight: bold; font-size: 20px;">${userID}</h6>
					<li><a href="logout">logout</a></li>
					<br />
					<h5>My Account</h5>
					<li><a href="mypageView">회원정보수정</a></li>
					<br />
					<h5>My Order</h5>
					<li>
						<form action="myContentResGo">
							<input type="hidden" id="idx" name="userID" value="${userID}" />
							<!--<input type="text" id="userID" name="userID" value="${userID}">-->
							<input type="submit" value="내 컨텐츠 찜내역">
						</form>
					</li>
					<%-- 		      <li>
		      	<form action="reservationGo">
		        <input type="hidden" id="idx" name="userID" value="${userID}"/>
		      	<!-- <input type="text" id="userID" name="userID" value="${userID}"> -->
		      	<input type="submit" value="찜한 내역 조회">
	      		</form>
		     </li> --%>
					<li style="margin-top: 5px;">
						<form action="myContentGo">
							<input type="hidden" name="userID" value="${userID}" /> <input
								type="submit" value="내 콘텐츠 보러가기" />
						</form>
					</li>
					<br />
					<h5>Customer Service</h5>
					<li><a href="#">상품 문의</a></li>
				</ul>
			</div>

			<!-- Main info -->
			<div class="col-sm-9" id="container">
				<h4>
					<small>회원정보수정</small>
				</h4>
				<hr>
				<h2>회원정보</h2>
				<div class="table-responsive">
					<table class="table">
						<tr>
							<th>이메일</th>
							<td>${userID}</td>
							<td></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" readonly="readonly"
								style="border: none;" value="${userPassword}"></td>
							<td><a href="updatePassword" style="text-decoration: none;">비밀번호
									변경</a></td>
						</tr>
						<tr>
							<th>닉네임</th>
							<td>${nickname}</td>
							<td></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<c:import url="./Common/Footer.jsp" />

</body>
</html>