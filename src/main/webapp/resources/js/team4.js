// ajax search 메소드

// ajax 검색요청함수
function searchFunction() {
	let url = 'TripOfLife_Project/SearchTest?subject=' + encodeURIComponent(document.getElementById('userSubject').value);
	fetch(url, {
		method: 'GET'
	}).then(function (response) {
		response.text().then(function (text) {
			let result = eval('(' + text + ')').result;
			let table = document.getElementById('ajaxTable');
			table.innerHTML = '';
			for (let i=0; i<result.length; i++) {
				let row = table.insertRow(i);
				for (let j=0; j<result[i].length; j++) {
					let cell = row.insertCell(j);
					cell.innerHTML = result[i][j].value;
				}
			}
		});
	});
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
