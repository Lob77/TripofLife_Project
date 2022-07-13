<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String userID = (String) session.getAttribute("userID");
	boolean login = userID != null ? true : false;
%>
<!-- 세션에 저장된 로그인 정보 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/team4.js"></script>
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

.text {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
	font-family: 'Roboto', Arial, sans-serif;
	font-size: 40px;
	font-weight: bold;
	line-height: 1.2;
	letter-spacing: 0.05em;
	white-space: nowrap;
	text-transform: uppercase;
	color: #fff;
	background-color: #000;
	mix-blend-mode: multiply;
	opacity: 0;
	animation: fadeInText 3s 2s ease-out forwards;
}

@
keyframes scaleImage { 100% {
	transform: scale(1);
}

}
@
keyframes fadeInText { 100% {
	opacity: 1;
}

}
.navbar-brand {
	font-family: 'Anton', sans-serif;
}
</style>
<title>Trip Of Life</title>
</head>

<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="./logoutMain"
				style="margin-right: 30px;">Trip Of Life</a>
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
				<!--<form class="d-flex" style="margin-right: 30px;">
						        <input class="form-control me-2" type="text" placeholder="Search">
						        <button class="btn btn-dark" type="button">Search</button>
      						</form> -->
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4"
					style="float: right">
					<li class="btn btn-light"></li>
				</ul>


				<form class="d-flex">
					<button class="btn btn-outline-dark" type="submit"
						style="margin: 10px">
						<a href="login" style="text-decoration: none; color: black;">login</a>
					</button>
					<button class="btn btn-outline-dark" type="submit"
						style="margin: 10px">
						<a href="register" style="text-decoration: none; color: black;">register</a>
					</button>
				</form>
			</div>
		</div>
	</nav>
	<!-- main -->
	<div class="container" id="wrap">
		<div class="panel-body">
			<div class="row">
				<div class="col-lg-12">
					<form id="register-form" action="registerOK" method="post"
						role="form" style="display: inline-block;">

						<div class="form-outline mb-4">
							<label class="form-label" for="loginName">이메일</label> <input
								value="" type="text" name="userID" id="userID" tabindex="1"
								class="form-control" placeholder="아이디(이메일)를 입력하세요."
								style="width: 500px" check_result="fail" required />

							<button class="btn btn-outline-dark" type="button"
								style="margin-top: 10px;" onclick="registerCheckFunction();">
								중복검사</button>
						</div>


						<div class="form-outline mb-4">
							<label class="form-label" for="loginPassword">비밀번호</label> <input
								type="password" name="userPassword" id="userPassword"
								tabindex="2" class="form-control" placeholder="비밀번호를 입력하세요"
								style="width: 500px" />
						</div>
						<div class="form-outline mb-4">
							<input type="password" name="confirmPassword"
								id="confirmPassword" tabindex="2" class="form-control"
								placeholder="한번 더 비밀번호를 입력하세요" style="width: 500px" />
						</div>

						<div>
							회원이신가요? <a href="./login" id="register-form-link"
								style="text-decoration: none; color: black; font-weight: bold">로그인</a>
						</div>
						<br />
						<div class="form-group">
							<div class="row">
								<div class="col-sm-6 col-sm-offset-3">
									<input class="btn btn-outline-dark" type="submit" value="회원가입"
										style="width: 500px; border-radius: 50px" />
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<div id="checkModal" class="modal fade" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<!-- checkType id 추가 -->
				<div id="checkType">
					<div class="modal-header panel-heading">
						<h4 class="modal-title">아이디 중복 체크 확인 메시지</h4>
					</div>
					<!-- checkMessage id 추가 -->
					<div id="checkMessage" class="modal-body"></div>
					<!--<div class="modal-footer">
										 <button class="btn btn-primary" type="button" data-dismiss="modal" onclick="modalclose();">확인</button> 
									</div>-->
				</div>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<c:import url="./Common/Footer.jsp" />


</body>
</html>









