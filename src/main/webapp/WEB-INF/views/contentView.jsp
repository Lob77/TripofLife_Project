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
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<link rel="icon" href="./img/logo.png"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- 구글 폰트 사용 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">

<style type="text/css">
body {
	margin: 0;
	padding: 0;
	height: 100%;
}

.board_title {
	font-weight: 700;
	font-size: 22pt;
	margin: 10pt;
}

.board_info_box {
	color: #6B6B6B;
	margin: 10pt;
}

.board_author {
	font-size: 10pt;
	margin-right: 10pt;
}

.board_date {
	font-size: 10pt;
}

.board_content {
	color: #444343;
	font-size: 12pt;
	margin: 10pt;
}

.board_tag {
	font-size: 11pt;
	margin: 10pt;
	padding-bottom: 10pt;
}

.navbar-brand {
	font-family: 'Anton', sans-serif;
}
a {
	text-decoration: none;
	color: black;
}

a:hover {
	font-weight: 900;
}
</style>

<script type="text/javascript">
	function reservation(){
		let idx = document.getElementById('idx').value; // 콘텐츠 번호
		let userID = '<c:out value="${co.userID}"/>'; //콘텐츠 소유자
		let resID = '<%=(String) session.getAttribute("userID")%>'; //콘텐츠 신청자
		let subject = '<c:out value="${co.subject}"/>' //콘텐츠 제목
		console.log(idx)
		console.log(resID)
		console.log(userID)
		console.log(subject)
		$.ajax({
			type: 'POST',
			url: './Reservation',
			data: {
				idx: idx,
				userID: userID,
				resID: resID,
				subject: subject,
			},
			success: response => {
				console.log('요청성공: ', response);
				
				switch (response) {
				case '1':
					$('#messageType').html('에러메시지');
					$('#messageContent').html('먼저 로그인 해야합니다.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
				case '2':
					$('#messageType').html('에러메시지');
					$('#messageContent').html('콘텐츠 소유자는 예약이 불가능합니다.');
					$('#messageCheck').attr('class', 'modal-content panel-danger');
					break;
				case '3':
					$('#messageType').html('성공메시지');
					$('#messageContent').html('예약에 성공했습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-success');
					break;
				case '4':
					$('#messageType').html('에러메시지');
					$('#messageContent').html('예약에 실패했습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-info');
					break;
			}
			$('#messageModal').modal('show');
			},
			error: error => {
				console.log('요청실패: ', error.status);
			}
		});
	}
	const insertRequest = new XMLHttpRequest(); 
	const searchRequest = new XMLHttpRequest(); 
	
	function insertComment() {
		// console.log('insertComment()');
		// console.log(document.getElementById('idx').value);
		// console.log('<%=(String) session.getAttribute("userID")%>');
		// console.log(document.getElementById('userComment').value);
	
		let url = './InsertComment?idx=' + encodeURIComponent(document.getElementById('idx').value)
			+ '&userID=' + encodeURIComponent('<%=(String) session.getAttribute("userID")%>')
			+ '&userComment=' + encodeURIComponent(document.getElementById('userComment').value)
		// console.log(url);
		insertRequest.open('GET', url, true);
		insertRequest.send(null);
		insertRequest.onreadystatechange = insertProcess;
	}
	 function insertProcess() {
		// console.log('insertProcess()');
		// console.log(insertRequest.readyState)
		// console.log(insertRequest.status)

		if (insertRequest.readyState == 4 && insertRequest.status == 200) {
			let result = insertRequest.responseText;
			// console.log('결과' + result)
			//	저장 여부와 관계없이 텍스트 상자의 내용을 지운다.
			if (result == 11) {
				alert('댓글 저장 성공');
				commentList();
				document.getElementById('userComment').value = '';
			} else {
				document.getElementById('userComment').value = '';
				alert('댓글 저장 실패')
			} 
		}
	}
	 function commentList() {
		 encodeURIComponent();
		 //console.log('commentList()');
		 //console.log(document.getElementById('userID').value);
		 //console.log(document.getElementById('userComment').value);
		let url = './CommentList?idx=' + encodeURIComponent(document.getElementById('idx').value)
		+ '&userID=' + encodeURIComponent('<%=(String) session.getAttribute("userID")%>')
		+ '&userComment=' + encodeURIComponent(document.getElementById('userComment').value)
		//console.log(url);
		searchRequest.open('GET', url, true);
		searchRequest.send(null);
		searchRequest.onreadystatechange = searchProcess;
	 }
	 
	 function searchProcess() {
		 if (searchRequest.readyState == 4 && searchRequest.status == 200) {
				console.log('responseText: ', searchRequest.responseText);
				let object = eval('(' + searchRequest.responseText + ')');
				console.log("object" + object);
				let result = object.result;
				console.log("result" + result);

				let table = document.getElementById('commentTable');
				table.innerHTML = '';

				for (let i = 0; i < result.length; i++) {
					let row = table.insertRow(i);
					for (let j = 0; j < result[i].length; j++) {
						let cell = row.insertCell(j);
						cell.innerHTML = result[i][j].value;
					}
				}
			}
	 }
		onload = function() {
			 encodeURIComponent();
			 //console.log('commentList()');
			 //console.log(document.getElementById('userID').value);
			 //console.log(document.getElementById('userComment').value);
			let url = './CommentList?idx=' + encodeURIComponent('<c:out value="${co.idx}"/>')
			+ '&userID=' + encodeURIComponent('admin')
			+ '&userComment=' + encodeURIComponent('admin')
			//console.log(url);
			searchRequest.open('GET', url, true);
			searchRequest.send(null);
			searchRequest.onreadystatechange = searchProcess;
			}
		
		function loginGo() {
			alert('로그인을 하신 후 이용해 주시기 바랍니다.')
			location.href="./login"
		}

</script>
</head>
<body>
	<%
		if (login) {
	%>
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
			<form action="contentSearch.nhn" class="d-flex"
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
					href="./mypageView.nhn">여행지 관리</a></li>
			</ul>
			<form action="logout.nhn">
				<button class="btn btn-outline-dark" type="submit"
					style="margin: 10px">
					 <a href="./login.nhn" style="text-decoration: none; color: black;">
						<i class="bi bi-person-circle" style="margin-right: 2px;"></i>logout</a>
				</button>
			</form>
		</div>
	</nav>
	<article>

		<div class="container" role="main">
			<form action="?" method="post">
				<div class="bg-white rounded shadow-sm">
					<div class="board_title">${co.subject}</div>
					<div class="board_info_box">
						<span class="board_author">${co.idx},</span><span
							class="board_author">${co.userID},</span><span class="board_date"><fmt:formatDate
								value="${co.writeDate}" pattern="yyyy/MM/dd(E)" /></span>
					</div>
					<div class="board_content">
						<p style="white-space: pre-line;">${co.content}</p>
					</div>
				</div>

				<div style="margin-top: 20px">

					<input type="hidden" id="idx" name="idx" value="${co.idx}" /> <input
						type="hidden" id="currentPage" name="currentPage"
						value="${currentPage}" />
					<button type="button" tabindex="2" class="btn btn-outline-dark"
						onclick="reservation()">찜하기</button>
					<input type="button" value="삭제하기" tabindex="2"
						class="btn btn-outline-dark"
						onclick="location.href = 'DeleteCheck?idx=${co.idx}&currentPage=${currentPage}&userID1=${co.userID}'" />
					<!-- 삭제권한부여와 관련해 변경한 부분 -->
					<input type="button" value="수정하기" tabindex="2"
						class="btn btn-outline-dark"
						onclick="location.href = 'contentViewUpdate?idx=${co.idx}&currentPage=${currentPage}'" />
					<!-- 글 수정 관련해 변경한 부분 -->
				</div>

			</form>
		</div>



		<!-- 댓글 입력 양식 -->
		<div class="container" style="margin-top: 20px;">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td><input type="text" class="form-control" name="userID"
						id="userID" value="${userID}" readonly="readonly"> <textarea
							class="form-control" rows="3" name="userComment" id="userComment"
							placeholder="내용을 입력해 주세요" style="resize: none;"></textarea> <input
						class="btn btn-sm btn-primary" type="button" value="댓글입력"
						onclick="insertComment()" style="float: right; margin: 3px;" /></td>
				</tr>
			</table>
		</div>

		<!-- 댓글 출력 양식 -->
		<div class="container">
			<div class="row">
				<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th style="background-color: #eeeeee; text-align: center;">아이디</th>
							<th style="background-color: #eeeeee; text-align: center;">댓글
								내용</th>
							<th style="background-color: #eeeeee; text-align: center;">작성일</th>
						</tr>
					</thead>
					<tbody id="commentTable">
						<tr></tr>
					</tbody>
				</table>
			</div>
		</div>

	</article>
	<%
		} else {
	%>
	<!-- <h1>로그인하지 않은 상태</h1> -->
	<!-- Navigator -->
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="./">Trip Of Life</a>
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
					<li class="btn btn-light"><a class="nav-link"
						href="./login.nhn">여행지 등록하기</a></li>
				</ul>
				<form class="d-flex">
					<button class="btn btn-outline-dark" type="button"
						onclick="location.href='./login'">
						<i class="bi bi-person-circle" style="margin-right: 2px;"></i> login
					</button>
				</form>
				<form class="d-flex">
					<button class="btn btn-outline-dark" type="submit"
						style="margin: 10px">
						<a href="./register" style="text-decoration: none; color: black;">register</a>
					</button>
				</form>
			</div>
		</div>
	</nav>
	<!-- main -->
	<article>
		<div class="container" role="main">
			<form action="login" method="post">
				<div class="bg-white rounded shadow-sm">
					<div class="board_title">${co.subject}</div>
					<div class="board_info_box">
						<span class="board_author">${co.idx},</span><span
							" class="board_author">${co.userID},</span><span
							class="board_date"><fmt:formatDate value="${co.writeDate}"
								pattern="yyyy/MM/dd(E)" /></span>
					</div>
					<div class="board_content">${co.content}</div>
				</div>

				<div style="margin-top: 20px">
					<button type="submit" tabindex="2" class="btn btn-outline-dark">찜하기</button>
				</div>
			</form>
		</div>
	</article>

	<!-- 댓글 입력 양식 -->
	<div class="container" style="margin-top: 20px;">
		<table class="table table-striped"
			style="text-align: center; border: 1px solid #dddddd">
			<tr>
				<td><textarea class="form-control" rows="3" name="userComment"
						id="userComment" placeholder="댓글을 작성하려면 로그인 해주세요"
						onclick="loginGo()"></textarea></td>
			</tr>
		</table>
	</div>

	<!-- 댓글 출력 양식 -->

	<div class="container">
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">아이디</th>
						<th style="background-color: #eeeeee; text-align: center;">댓글
							내용</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<tbody id="commentTable">
					<tr></tr>
				</tbody>
			</table>
		</div>
	</div>

	<%
		}
	%>



	<!-- Footer -->
	<c:import url="./Common/Footer.jsp" />

	<!-- 모달창 -->
	<div id="messageModal" class="modal fade" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div>
					<!-- messageType id 추가 -->
					<div id="messageCheck">
						<div class="modal-header panel-heading">
							<!-- messageType id 추가 -->
							<h4 id="messageType" class="modal-title"></h4>
						</div>
						<!-- messageType content 추가 -->
						<div id="messageContent" class="modal-body"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="closeModalBtn">확인</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 로그인 메시지 모달 창 -->

</body>

</html>