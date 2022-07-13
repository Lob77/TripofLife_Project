<%@page import="java.util.ArrayList"%>
<%@page import="com.tjoeun.vo.ContentList"%>
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
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
 -->
<meta name="description" content="" />
<meta name="author" content="" />
<title>TripOfLife</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<link rel="icon" href="./img/logo.png"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js">
<link rel="preconnect" href="https://fonts.googleapis.com"><!-- 구글 폰트 사용 -->
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
	
<link href="css/styles.css" rel="stylesheet" />
<link href="css/list.css" rel="stylesheet" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="./js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/team4.js"></script>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Lobster&family=Rubik+Moonrocks&display=swap');/* 구글 폰트 사용 */

	.best_content{
           display: flex;
           justify-content: center;
       }
	html, body {
	    margin: 0;
	    padding: 0;
	    height: 100%;
	}
	nav{
		margin-bottom: 50px;
	}
*		btn btn-outline-dark
	#wrap{
	    position: relative;
	    padding-bottom: 50px;
	    margin:auto;
	    text-align:center;
	}
	a{
		text-decoration: none;
		color: black;
	}
	a:hover{
		color:black;
		font-weight: 900;
	} 

	.navbar-brand{
		font-family: 'Anton', sans-serif;
	}
</style>
	<title>Trip Of Life</title>
	
</head>
<body>
<% 
	if(login){
%>
<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="./" style="margin-right:30px;">Trip Of Life</a>
			<form class="d-flex" style="margin-right: 20px;">
				<input id="userSubject" name="item" class="form-control me-2" type="text"
					placeholder="Search" autocomplete="off" onkeyup="searchFunction()" >
				<button class="btn btn-dark" name="item-submit" id="userSubject"
					type="button" onclick="searchFunction()">Search</button>
			</form>
			<!-- 글쓰기 버튼 -->
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4" style="text-align:center;">
				<li class="btn btn-light"><a class="nav-link"
					href="./content">여행 등록하기</a></li>
			</ul>
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
				<li class="btn btn-light"><a class="nav-link"
					href="./mypageView">여행지 관리</a></li>
			</ul>
			<form action="logout">
				<button class="btn btn-outline-dark" type="submit"
					style="margin: 10px">
					<a href="login" style="text-decoration: none; color: black;">
					<i class="bi bi-person-circle" style="margin-right: 2px;"></i> logout</a>
				</button>
			</form>
		</div>
	</nav>
	

	
	<%
	} else {
	%>
 <!-- Navigation-->
       <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
            	<a class="navbar-brand" href="./" style="margin-right:30px;">Trip Of Life</a>
                	<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
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
							<form class="d-flex" style="margin-right: 20px;">
									<input id="userSubject" name="item" class="form-control me-2" type="text"
										placeholder="Search" autocomplete="off" onkeyup="searchFunction()" >
									<input class="btn btn-dark" name="item-submit" id="userSubject"
										type="button" onclick="searchFunction()" value="Search"/>
							</form>
                 			   <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4" style="float: right">
                    			<li class="btn btn-light"></li>
                   				 </ul>
                    
              
                    <form class="d-flex">
                        <button class="btn btn-outline-dark" type="submit" style="margin: 10px">
						<a  href="./login" style="text-decoration:none; color:black;">
						<i class="bi bi-person-circle" style="margin-right: 2px;"></i>login</a>
                        </button>
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
<!-- Main -->

<div class="container">
	<div class="bestMainTitle">
		<p>조회수 베스트3 </p>	
	</div>	 
	<div class = "best_content" style="margin-bottom: 90px;">
		<c:set var="bestContentlist" value="${BestContentList.list}"></c:set>
			<c:if test="${bestContentlist.size() == 0 }">
				<tr>
					<td colspan="5" align="right">
						<marquee>테이블에 저장된 글이 없습니다.</marquee>
					</td>
				</tr>
			</c:if>
			<c:if test="${bestContentlist.size() != 0 }">
				<c:forEach var="i" begin="0" end="${fn:length(bestContentlist)-1}"/> 			
					<div class="bestContent1">
						 <img src="resources/images/list_1.jpg" class="bestImg1" >
						 	<div class="bestTitle">
						 		${bestContentlist[0].subject}
							<button type="button" class="btn btn-warning" id="bestButton1">
								<a href="contentView?idx=${bestContentlist[0].idx}&currentPage=${contentList.currentPage}">보러가기</a>
							</button>
						 	</div>
					</div>
					<div class="bestContent2">
						 <img src="resources/images/list_2.jpg" class="bestImg2" >
						 	<div class="bestTitle">
						 		${bestContentlist[1].subject}
							<button type="button" class="btn btn-warning" id="bestButton2">
								<a href="contentView?idx=${bestContentlist[1].idx}&currentPage=${contentList.currentPage}">보러가기</a>
							</button>
						 	</div>
					</div>
					<div class="bestContent3">
						 <img src="resources/images/list_3.jpg" class="bestImg3" >
						 	<div class="bestTitle">
						 		${bestContentlist[2].subject}
							<button type="button" class="btn btn-warning" id="bestButton3">
								<a href="contentView?idx=${bestContentlist[2].idx}&currentPage=${contentList.currentPage}">보러가기</a>
							</button>
						 	</div>
					</div>
			</c:if>
	</div>

		<div class="row" style="margin-bottom: 60px;">
			<table class="table table-striped"
				style="border: 1px solid #dddddd">
				<thead>
					<div style="padding: 4px; background-color:none;">
					<h1 id ="title" colspan="5" style="font-size:40px; color: black; text-align: center; font-weight: bold; text-decoration: underline; text-underline-position: underline;">
					Review List <br/>
					</h1>
						<h3 id ="title" style ="color: black; text-align: right; font-weight: bold"> ${ContentList.totalCount} (${ContentList.currentPage} / ${ContentList.totalPage}) &nbsp; &nbsp;</h3>
					</h3>
					</div>
					<tr>
						<th id ="title" style="background-color: #eeeeee; text-align: center;">Index</th>
						<th id ="title" style="background-color: #eeeeee; text-align: center;">Title</th>
						<th id ="title" style="background-color: #eeeeee; text-align: center;">Name</th>
						<th id ="title" style="background-color: #eeeeee; text-align: center;">Date</th>
						<th id ="title" style="background-color: #eeeeee; text-align: center;">Hit</th>
					</tr>
				</thead>
				
			
					<tbody id="ajaxTable">
						<c:set var="list" value="${ContentList.list}"></c:set>
						<c:if test="${list.size() == 0 }">
						<tr>
							<td colspan="5" align="right">
								<marquee>테이블에 저장된 글이 없습니다.</marquee>
							</td>
						</tr>
						</c:if>
						<c:if test="${list.size() != 0 }">
						<c:forEach var="vo" items="${list}">
						<tr>
							<td align="center">${vo.idx}</td>
							<td align="center">
								<c:set var="subject" value="${fn:replace(vo.subject, '<', '&lt;')}"></c:set>
								<c:set var="subject" value="${fn:replace(vo.subject, '>', '&gt;')}"></c:set>
								<a href="increment?idx=${vo.idx}&currentPage=${contentList.currentPage}">${subject}</a>
							</td>
							
							<td align="center">
							<c:set var="userID" value="${fn:replace(vo.userID, '<', '&lt;')}"></c:set> 
							<c:set var="userID" value="${fn:replace(vo.userID, '>', '&gt;')}"></c:set>
							${userID}</td>
							<td align="center">
							<c:if test="${date.year == vo.writeDate.year && date.month == vo.writeDate.month &&
								date.date == vo.writeDate.date}">
							<fmt:formatDate value="${vo.writeDate}" pattern="a h:mm:ss" />
							</c:if> 
							<c:if test="${date.year != vo.writeDate.year || date.month != vo.writeDate.month || date.date != vo.writeDate.date}">
							<fmt:formatDate value="${vo.writeDate}" pattern="yyyy.MM.dd(E)" />
							</c:if> <!-- ${vo.writeDate}  --></td>
							<td align="center">${vo.hit}</td>

						 	</tr>
					</c:forEach>
				</c:if>
			</tbody>
		
		<!-- 페이지 이동 버튼 -->
					<tr>
						<td colspan="5" align="center">
							<!-- 처음으로 --> 	<br/><c:if test="${ContentList.currentPage > 1}">
								<button class="btn btn-outline-dark" type="button"
									title="첫 페이지로 이동합니다." onclick="location.href='?currentPage=1'">처음</button>
							</c:if> <c:if test="${ContentList.currentPage <= 1}">
								<button class="btn btn-outline-dark" type="button"
									title="이미 첫 페이지 입니다." disabled="disabled">처음</button>
							</c:if> <!-- 10페이지 앞으로 --> <c:if test="${ContentList.startPage > 1}">
								<button class="btn btn-outline-dark" type="button"
									title="이전 10 페이지로 이동합니다."
									onclick="location.href='?currentPage=${ContentList.startPage - 1}'">
									이전</button>
							</c:if> <c:if test="${ContentList.startPage <= 1}">
								<button class="btn btn-outline-dark" type="button"
									title="이미 첫 10 페이지 입니다." disabled="disabled">이전</button>
							</c:if> <!-- 페이지 이동 --> 
							<c:forEach var="i"
								begin="${ContentList.startPage}"
								end="${ContentList.endPage}" step="1">

								<c:if test="${ContentList.currentPage == i}">
									<button class="btn btn-outline-dark" type="button"
										disabled="disabled">${i}</button>
								</c:if>

								<c:if test="${ContentList.currentPage != i}">
									<button class="btn btn-outline-dark" type="button"
										onclick="location.href='?currentPage=${i}'">${i}</button>
								</c:if>
							
							</c:forEach> <!-- 10페이지 뒤로 --> 
							<c:if
								test="${ContentList.endPage < ContentList.totalPage}">
								<button class="btn btn-outline-dark" type="button"
									title="다음 10 페이지로 이동합니다."
									onclick="location.href='?currentPage=${ContentList.currentPage + 10}'">다음</button>
							</c:if> <c:if test="${ContentList.endPage >= ContentList.totalPage}">
								<button class="btn btn-outline-dark" type="button"
									title="이미 마지막 10 페이지 입니다." disabled="disabled">다음</button>
							</c:if> <!-- 마지막으로 -->
							 <c:if
								test="${ContentList.currentPage < ContentList.totalPage}">
								<button class="btn btn-outline-dark" type="button"
									title="첫 페이지로 이동합니다."
									onclick="location.href='?currentPage=${ContentList.totalPage}'">마지막</button>
							</c:if> <c:if
								test="${ContentList.currentPage >= ContentList.totalPage}">
								<button class="btn btn-outline-dark" type="button"
									title="이미 마지막 페이지 입니다." disabled="disabled">마지막</button>
							</c:if>

						</td>
					</tr>
		
		</table>
	</div>
</div>
		
	<!-- Footer -->
	<c:import url="./Common/Footer.jsp" />

</body>
</html>


<!-- https://whakscjs.tistory.com/entry/2012513-MVC%ED%8C%A8%ED%84%B4%EC%9D%84-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%9D%B4%EB%A6%84%EC%9C%BC%EB%A1%9C-%ED%9A%8C%EC%9B%90-%EC%A1%B0%ED%9A%8C%ED%95%98%EA%B8%B0like-%EC%82%AC%EC%9A%A9 -->