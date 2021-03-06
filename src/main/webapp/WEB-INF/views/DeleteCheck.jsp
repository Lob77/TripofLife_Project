<%@page import="com.tjoeun.vo.ContentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- 구글 폰트 사용 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
<style type="text/css">
html, body {
	margin: 0;
	padding: 0;
	height: 100%;
}

nav {
	margin-bottom: 50px;
}

#wrap {
	position: relative;
	padding-bottom: 50px;
	margin: auto;
	text-align: center;
}

.navbar-brand {
	font-family: 'Anton', sans-serif;
}
a:hover{
	color:black;
	font-weight: 900;
} 
</style>

<title>Trip Of Life</title>
</head>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="./" style="margin-right: 30px;">Trip
				Of Life</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<!--  <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#!">All Products</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li> -->

				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4"
					style="float: right">
					<li class="btn btn-light"></li>
				</ul>

				<!--             
                    <form class="d-flex">
                        <button class="btn btn-outline-dark" type="submit" style="margin: 10px">
                           <a href="./login.nhn" style="text-decoration:none; color:black;">login</a>
                        </button>
                       <button class="btn btn-outline-dark" type="submit" style="margin: 10px">
                            <a href="./register.nhn" style="text-decoration:none; color:black;">register</a>
                        </button>
                    </form>
 -->
			</div>
		</div>
	</nav>

	<!-- main -->
	<%
		String userID = (String) session.getAttribute("userID");
		System.out.println("deleteCheck 뷰 페이지의 userID:" + userID);
		String idx = request.getParameter("idx");
		String currentPage = request.getParameter("currentPage");
		String userID1 = request.getParameter("userID1");
		if (userID == null) {

			response.sendRedirect("login.jsp");
		}
	%>

	<div name="delete_confirm" style="align: center; margin: 80px;">
		<fieldset style="text-align: center;">
			<legend> 회원정보 확인</legend>
			<form action="DeleteConfirm">
				<input type="hidden" name="userID" value="<%=userID%>"> 
				<input type="hidden" name="idx" value="<%=idx%>">
				<!-- 삭제권한부여와 관련해 변경한 부분 -->
				<input type="hidden" name="currentPage" value="<%=currentPage%>">
				<!-- 삭제권한부여와 관련해 변경한 부분 -->
				<input type="hidden" name="userID1" value="<%=userID1%>">
				<!-- 컨텐츠 작성자 ID  -->
				계정 비밀번호를 입력해주세요 : <input type="password" name="userPassword"><br>
				<input type="submit" class="btn btn-outline-dark" value="삭제하기"
					style="margin: 20px;">
			</form>
		</fieldset>
	</div>
	
	<!-- Footer -->
	<c:import url="./Common/Footer.jsp" />

</body>
</html>









