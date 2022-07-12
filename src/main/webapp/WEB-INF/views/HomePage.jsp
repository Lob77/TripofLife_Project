<%@page import="com.tjoeun.vo.ContentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% 
	  String userID = (String)session.getAttribute("userID"); 
	  String userPassword = (String)session.getAttribute("userPassword");
	  boolean login = userID != null ? true : false;

%> <!-- 세션에 저장된 로그인 정보 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<meta name="description" content="" />
<meta name="author" content="" />
<title>TripOfLife</title>
        <!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
<link rel="preconnect" href="https://fonts.googleapis.com"><!-- 구글 폰트 사용 -->
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">        
<link href="css/styles.css" rel="stylesheet" />
<link href="css/HomePage.css" rel="stylesheet" />

<style type="text/css">
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


@keyframes scaleImage {
    100% {
        transform: scale(1);
    }
}


@keyframes fadeInText {
    100% {
        opacity: 1;
    }
}
.navbar-brand{
	font-family: 'Anton', sans-serif;
}

</style>
        
</head>
<body>
<% 
	if(login){
%>
       <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!">Trip Of Life</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="btn btn-light"><a class="nav-link active" aria-current="page" href="./IntroPage">홈페이지 소개</a></li>
                        <li class="btn btn-light"><a class="nav-link" href="./list">여행지 후기게시판</a></li>
                       
                       <!--  <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#!">All Products</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li> -->
                    </ul>
                    
       	<!--   <form class="d-flex" style="margin-right: 20px;">
			        <input class="form-control me-2" type="text" placeholder="Search">
			        <button class="btn btn-dark" type="button">Search</button>
      			</form> -->
                    <form class="d-flex">
                        <button class="btn btn-outline-dark" type="button" onclick="location.href='./logout'">
                            <i class="bi bi-person-circle"></i>
                            logout
                        </button>
                    </form>
                     <form class="d-flex">
                        <button class="btn btn-outline-dark" type="submit" style="margin: 10px">
                            <a  href="./register" style="text-decoration:none; color:black;">register</a>
                        </button>
                      </form>   
                    
                </div>
            </div>
        </nav>
<%
} else {
%>
       <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!">Trip Of Life</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="btn btn-light"><a class="nav-link active" aria-current="page" href="IntroPage">홈페이지 소개</a></li>
                        <li class="btn btn-light"><a class="nav-link" href="./list">여행지 후기게시판</a></li>
                       
                       <!--  <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#!">All Products</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li> -->
                    </ul>
                    
			<!-- <form class="d-flex" style="margin-right: 20px;">
			        <input class="form-control me-2" type="text" placeholder="Search">
			        <button class="btn btn-dark" type="button">Search</button>
      			</form> -->
                    <form class="d-flex">
                        <button class="btn btn-outline-dark" type="button" onclick="location.href='./login'">
                            <i class="bi bi-person-circle"></i>
                            login
                        </button>
                    </form>
                    <form class="d-flex">
                         <button class="btn btn-outline-dark" type="submit" style="margin: 10px">
                            <a  href="./register" style="text-decoration:none; color:black;">register</a>
                        </button>
                    </form>
                </div>
            </div>
        </nav>
<%
}
%>        
        <!-- Header-->
	<div class="header" style="background-color: #F6F6F1; height: 400px;">        
        <div class="container" style="text-align: center;">
	    	<div id="demo" class="carousel slide" data-bs-ride="carousel">
	
				  <!-- Indicators/dots -->
				  <div class="carousel-indicators">
				    <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
				    <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
				    <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
				  </div>
				
				  <!-- The slideshow/carousel -->
				  <div class="carousel-inner" style="width:1300px; height:400px;">
				    <div class="carousel-item active">
				      <img src="resources/images/main1.jpg" class="d-block w-100">
				    </div>
				    <div class="carousel-item">
				      <img src="resources/images/main2.jpg" class="d-block w-100">
				    </div>
				    <div class="carousel-item">
				      <img src="resources/images/main3.jpg" class="d-block w-100">
				    </div>
				  </div>
				
				  <!-- Left and right controls/icons -->
				  <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
				    <span class="carousel-control-prev-icon"></span>
				  </button>
				  <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
				    <span class="carousel-control-next-icon"></span>
				  </button>
			</div>
		</div>
	</div>    
    
    <p class="text">find fun life, doing travel</p>
    
    
       <!-- Section-->
	<div class="section wrapper">
		<div class="section1" style="text-align: center;">
			<a href="https://korean.visitseoul.net/map-guide-book">
				<div class="Mainbox" style="width: 350px; height: 200px; display: inline-block; margin:50px;">
						<img id="mainPics" src="resources/images/guide.jpg" class="d-block w-100">
						<div class="MainboxText">여행가이드 북 보러가기</div>
				</div>
			</a>
			<a href="./list">
				<div class="Mainbox" style="width: 350px; height: 200px;display: inline-block; margin:50px;">
						<img id="mainPics" src="resources/images/review.jpg" class="d-block w-100">
						<div class="MainboxText">베스트 후기글 보러가기</div>
				</div>
			</a>
		</div>
		<div class="section2" style="text-align: center;">
			<a href="https://kr.hotels.com" />
				<div class="Mainbox" style="width: 350px; height: 200px; display: inline-block; margin:25px; margin-top: 0px; margin-bottom: 50px;">
						<img id="mainPics" src="resources/images/hotel.jpg" class="d-block w-100">
						<div class="MainboxText">숙소 가격 비교하러 가기</div>
				</div>
			</a>	
			<a href="https://sky.interpark.com">
				<div class="Mainbox" style="width: 350px; height: 200px;display: inline-block; margin:25px;margin-top: 0px; margin-bottom: 50px;">
						<img id="mainPics" src="resources/images/ticket.jpg" class="d-block w-100">
						<div class="MainboxText">항공권 가격비교하러 가기</div>
				</div>
			</a>	
			<a href="IntroPage">
				<div class="Mainbox" style="width: 350px; height: 200px;display: inline-block; margin:25px;margin-top: 0px; margin-bottom: 50px;">
						<img id="mainPics" src="resources/images/introduce.jpg" class="d-block w-100">
						<div class="MainboxText">홈페이지 소개보러 가기</div>
				</div>
			</a>
			</div>
	</div>


       
       
       
<!-- Footer -->
   <footer class="text-center text-lg-start bg-light text-muted" style="height:300px; background-color: white;">
      <!-- Section: Social media -->
      <section
         class="d-flex justify-content-center justify-content-lg-between p-4 border-bottom">
      </section>
      <!-- Section: Social media -->
      
      <!-- Section: Links  -->
      <section class="">
         <div class="container text-center text-md-start mt-5">
            <!-- Grid row -->
            <div class="row mt-3">
               <!-- Grid column -->
               <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                  <!-- Content -->
                  <h6 class="text-uppercase fw-bold mb-4">
                     <i class="fas fa-gem me-3"></i>Customer Service
                  </h6>
                  <p>Here you can use rows and columns to organize your footer
                     content. Lorem ipsum dolor sit amet, consectetur adipisicing
                     elit.</p>
               </div>
               <!-- Grid column -->

               <!-- Grid column -->
               <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
                  <!-- Links -->
                  <h6 class="text-uppercase fw-bold mb-4">Products</h6>
                  <p>
                     <a href="#!" class="text-reset">Angular</a>
                  </p>
                  <p>
                     <a href="#!" class="text-reset">React</a>
                  </p>
                  <p>
                     <a href="#!" class="text-reset">Vue</a>
                  </p>
                  <p>
                     <a href="#!" class="text-reset">Laravel</a>
                  </p>
               </div>
               <!-- Grid column -->

               <!-- Grid column -->
               <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                  <!-- Links -->
                  <h6 class="text-uppercase fw-bold mb-4">Useful links</h6>
                  <p>
                     <a href="#!" class="text-reset">Pricing</a>
                  </p>
                  <p>
                     <a href="#!" class="text-reset">Settings</a>
                  </p>
                  <p>
                     <a href="#!" class="text-reset">Orders</a>
                  </p>
                  <p>
                     <a href="#!" class="text-reset">Help</a>
                  </p>
               </div>
               <!-- Grid column -->

               <!-- Grid column -->
               <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                  <!-- Links -->
                  <h6 class="text-uppercase fw-bold mb-4">Contact</h6>
                  <p>
                     <i class="fas fa-home me-3"></i> New York, NY 10012, US
                  </p>
                  <p>
                     <i class="fas fa-envelope me-3"></i> info@example.com
                  </p>
                  <p>
                     <i class="fas fa-phone me-3"></i> + 01 234 567 88
                  </p>
                  <p>
                     <i class="fas fa-print me-3"></i> + 01 234 567 89
                  </p>
               </div>
               <!-- Grid column -->
            </div>
            <!-- Grid row -->
         </div>
      </section>
      <!-- Section: Links  -->

      
   </footer>
   <!-- Footer -->
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
   
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
 
    </body>


</html>