<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta name ="google-signin-client_id" content="1010181044700-npq3av62ugl7b9o27uo06i83h9bv8mbd.apps.googleusercontent.com">

<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<style type="text/css">
		html, body {
		    margin: 0;
		    height: 100%;
		}
		.panel-body{
  			margin-top: 100px;
    		padding-bottom: 50px; /* footer의 높이 */
    		text-align: center;
    		margin-left: 0;
    		margin-right: 0;
		}
		nav{
			margin-bottom: 50px;
		}
 		#wrap{
		    min-height: 100%;
    		position: relative;
    		width: 100%;
		}
		footer{
		    width: 100%;
		    height: 360px; /* footer의 높이 */
		    position: absolute;  
		    bottom: 0;
		    left: 0;
		    text-align: center;
			}


    </style>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
<title>MY TRIP</title>
</head>
	<body>
	<!-- Navigation-->
	        <nav class="navbar navbar-expand-lg navbar-light bg-light">
	            <div class="container px-4 px-lg-5">
	                <a class="navbar-brand" href="./logoutMain" style="margin-right:30px;">MY TRIP</a>
	                	<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
	               			 <div class="collapse navbar-collapse" id="navbarSupportedContent">
				     			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4" style="float: right">
	                    			<li class="btn btn-light"></li>
	                   			</ul>
	                		</div>
	          	</div>
	        </nav>
	        
		<!-- main -->
	<div class="container" id="wrap">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<form id="login-form" action="loginOK.nhn" method="post"
							role="form" style="display: inline-block; ">
							<div class="nav nav-pills nav-justified mb-3" id="ex1"
								role="tablist">
								<div class="nav-item" role="presentation">
									<a href="./register" class="nav-link active" id="tab-login"
										data-mdb-toggle="pill"
										style="background-color: #173B0B; width: 500px;">회원가입하기</a>
								</div>
							</div>
							
							<div class="form-outline mb-4">
								<label class="form-label" for="loginName" >이메일</label> 
								<input
									type="text" name="userID" id="userID" tabindex="1"
									class="form-control" placeholder="아이디(이메일)를 입력하세요" value=""
									style="width: 500px" />
							</div>
							<div class="form-outline mb-4">
								<label class="form-label" for="loginPassword">비밀번호</label> <input
									type="password" name="userPassword" id="userPassword"
									tabindex="2" class="form-control" placeholder="비밀번호를 입력하세요"
									style="width: 500px" />
							</div>
							<div>
								회원이 아니신가요? <a href="register.nhn" id="register-form-link"
									style="text-decoration: none; color: black; font-weight: bold">회원가입</a>
							</div>
							<br />
							<div class="form-group">
								<div class="row">
									<div class="col-sm-6 col-sm-offset-3">
										<input class="btn btn-outline-dark" type="submit" value="로그인"
											style="width: 500px; border-radius: 50px"/>
									</div>
								</div>
							</div>
							<div class="g-signin2" data-onsuccess="onSignIn"/>
						</form>	
					</div>
				</div>
			</div>
	</div> 
	</body>
		<!-- Footer -->	
			<c:import url="./Common/Footer.jsp" />
</html>









