<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>Trip Of Life</title>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<link rel="icon" href="./img/logo.png"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js">
<script type="text/javascript" src="./contentSearch"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/team4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- 구글 폰트 사용 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
<style type="text/css">

.navbar-brand {
	font-family: 'Anton', sans-serif;
}
a:hover {
	font-weight: 900;
}
</style>
</head>

<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="./" style="margin-right: 30px;">Trip
				Of Life</a>
			<form action="contentSearch" class="d-flex"
				style="margin-right: 20px;">
				<input id="item" name="item" class="form-control me-2" type="text"
					placeholder="Search" autocomplete="off">
				<button class="btn btn-dark" name="item-submit" id="item-submit"
					type="submit">Search</button>
			</form>
			<!-- 글쓰기 버튼 -->
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4"
				style="text-align: center;">
				<li class="btn btn-light"><a class="nav-link" href="./content">여행
						등록하기</a></li>
			</ul>
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
				<li class="btn btn-light"><a class="nav-link"
					href="./mypageView">여행지 관리</a></li>
			</ul>
			<form action="logout">
				<button class="btn btn-outline-dark" type="submit"
					style="margin: 10px">
					<a href="./login" style="text-decoration: none; color: black;">
					<i class="bi bi-person-circle" style="margin-right: 2px;"></i>logout</a>
				</button>
			</form>
		</div>
	</nav>
	<article>
		<div class="container" role="main">

			<h2 style="margin-top: 20px;">여행 등록하기</h2>

			<form action="contentOK" method="post">
				<div class="mb-3">
					<label for="subject">제목</label> <input type="text"
						class="form-control" name="subject" id="subject"
						placeholder="제목을 입력해 주세요">
				</div>

				<div class="mb-3">
					<label for="userID">작성자</label> <input type="text"
						class="form-control" name="userID" id="userID" value="${userID}"
						readonly="readonly">
				</div>

				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" rows="20" name="content"
						id="content" placeholder="내용을 입력해 주세요" style="resize: none;"></textarea>
				</div>

				<div style="margin-bottom: 20px;">
					<input type="submit" value="저장하기" class="btn btn-outline-dark" /> <input
						type="reset" value="다시쓰기" class="btn btn-outline-dark" /> <input
						type="button" value="돌아가기" class="btn btn-outline-dark"
						onclick="history.back()" />
				</div>
			</form>
		</div>
	</article>

	<!-- Footer -->
	<c:import url="./Common/Footer.jsp" />

</body>
</html>