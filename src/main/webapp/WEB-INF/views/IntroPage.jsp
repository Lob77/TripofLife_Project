<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="css/IntroPage.css" rel="stylesheet" />
	<style type="text/css">
		html, body {
		    margin: 0;
		    padding: 0;
		    height: 100%;
		    width: 100%;
		}
		nav{
			margin-bottom: none;
		}




    </style>
</head>
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
	        

		<div class="IntroPageJb" >
			<video muted autoplay loop>
				<source src="resources/video/AirPort.mp4"/>
			</video> 	
			<div class="JbText">
				<p>	We are Provide various experience.</br>
					Check Our Site.</br>
					Share of your experience.</br>
					And enjoy your trip.
				</p>
			</div>
		</div>
		
	</body>

</html>