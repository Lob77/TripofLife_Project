// ajax search 메소드

// ajax 검색요청함수
const searchRequest = new XMLHttpRequest();

function searchFunction(){
//	console.log('searchFunction()');
//	console.log(document.getElementById('userSubject').value);
	
	let url = './HomeController?subject='+ encodeURIComponent(document.getElementById('userSubject').value);
	searchRequest.open('GET',url,true);
	
	// send() 함수로 서버에 요청한다.
	searchRequest.send(null);
//	console.log(url);
	
	//Ajax 요청이 완료되면 실행할 콜백 함수를 지정한다.
	searchRequest.onreadystatechange = searchProcess;
}

// 	ajax 요청이 완료되면 결과를 처리하는 함수가 필요
	function searchProcess(){
		// 	console.log('searchFunction() 함수에서 요청한 Ajax가 완료되면 자동으로 실행되는 함수');
		// XMLHttpRequest 객체의 readyState
		// 0: 아직 실행되지 않음
		// 1: 로딩중
		// 2: 로딩됨
		// 3: 통신중
		// 4: 통신완료
		// console.log('readyState: ', searchRequest.readyState)
		
		// XMLHttpRequest 객체의 status
		// 200: 수신성공
		// 3xx: 금지
		// 4xx: 페이지없음
		// 5xx: 서버오류
		// console.log('status: ', searchRequest.status)
		
		if (searchRequest.readyState == 4 && searchRequest.status == 200) {
			//	console.log('reponseText: ',searchRequest.responseText);
		
			// 서블릿에서 리턴된 문자열을 json 타입(javascript객체)으로 변환하기 위해 괄호를 붙여 eval() 함수로 실행해 객체에 저장
			
			let object = eval('(' + searchRequest.responseText+ ')');// syntax 에러 발생=> 06월24일 확인해보기 
			// console.log(object);
			
			let result = object.result;
			// console.log(result);
			let table = document.getElementById('ajaxTable');
			// 새로 검색되는 데이터가 표시되어야 하므로 이전에 <tbody> 태그에 들어있던 내용은 지운다.
			table.innerHTML = '';
			
			// 데이터의 개수만큼 반복하며 테이블에 행을 만들어 추가한다.
			for (let i=0; i<result.length; i++) {
				// <tbody>에 넣어줄 행을 만든다.
				let row = table.insertRow(i);
				// 한 행에 출력할 열의 개수만큼 반복하며 행에 열을 추가한다.
				for (let j=0; j<result[i].length; j++) {
					// 행에 넣어줄 열을 만든다.
					let cell = row.insertCell(j);
					// 열에 화면에 표시할 데이터를 넣어준다.
					cell.innerHTML = result[i][j].value;
		
				}
			}
		} 
	}

	onload = function () {
		searchFunction();
	}

//	id 중복검사 ajax
	function registerCheckFunction() {
		// ajax를 이용해서 중복 검사 할 아이디를 얻어온다.
		let userID = $('#userID').val();
		console.log("registerCheckFunction 실행: ")
		console.log(userID);
		
		// jQuery ajax
		$.ajax({
			type: 'POST',
			url: 'TripOfLife_Project/UserRegisterCheck',
			data: {
				userID: userID
			},
			success: response => {
				 console.log('요청성공:', response);
				switch (response) {
					case '0':
						$('#checkMessage').html('아이디를 입력하고 중복 체크 버튼을 누르세요');
						$('#checkType').attr('class', 'modal-content panel-warning');
						$('#userID').val('');
						break;
					case '1':
						$('#checkMessage').html('이미 존재하는 아이디 입니다.');
						$('#checkType').attr('class', 'modal-content panel-info');
						$('#userID').val('');
						break;
					case '2':
						$('#checkMessage').html('사용 가능한 아이디 입니다.');
						$('#checkType').attr('class', 'modal-content panel-success');
						break;
					default: 
						$('#checkMessage').html('sql 명령 오류');
						$('#checkType').attr('class', 'modal-content panel-danger');
						break;
				}
				$('#checkModal').modal('show');
			},
			error: () => {
				alert('요청 실패');
			}
		});
	}

//	register 페이지 모달 창 끄기
	function modalclose() {
		window.open('','_self').close(); 
	}
