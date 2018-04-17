//프로젝트 추가 From 생성
function addProjectForm(obj) {
	if ($('#projectName').length == 0) {
		var button = '<div><button class="button btn-1"><input type="text" id="projectName" name="projectName" onkeyup="fnChkByte(this,20)" style="margin-left:-60px; color:black;"></button><a class="glyphicon setting" onclick="addProject()">&#xe013;</a></div>'
		$('#progress').prepend(button)
		$('#projectName').focus();
		nameinput();

	}
}

//프로젝트 추가
function addProject() {
	var value = $('#projectName').val()

	if (value.trim() != "") {
		addproajax(value);
	} else {
		alert('프로젝트 명을 입력하세요')
	}

}

//프로젝트 삭제
function projectDel(projectNum,projectName) {
	var delProjectName = prompt("삭제하실 프로젝트 명을 입력해 주세요.");
	
	if(projectName == delProjectName){
		var data3 = {
			projectNum : projectNum
		};
		$.ajax({
			url : "delete.project",
			datatype : "text",
			data : data3,
			success : function(data) {
				callprojectlist();
			}
		})
	}
	
}

//프로젝트 완료
function projectComplete(projectNum) {

	$.ajax({
		url : "completeproject.project",
		data : {
			projectNum : projectNum,
			userId : $('#getsession').val()
		},//projectNum,userId
		datatype : "json",
		success : function(data) {
			callprojectlist();
		}
	});
}

//프로젝트복구
function projectProgress(projectNum) {
	$.ajax({
		url : "progressproject.project",
		data : {
			projectNum : projectNum,
			userId : $('#getsession').val()
		},//projectNum,userId
		datatype : "json",
		success : function(data) {
			callprojectlist();
		}
	});
}

//date가공 메서드
function dateProcess(dateStr, days) {
	var date = new Date(dateStr); // Date 객체 생성
	date.setDate(date.getDate() + days); // Date 가감설정
	var dateToISO = date.toISOString().substring(0,10); // Date -> String (ISO날짜 타입, 시간제거)
	return dateToISO;
}

//arr일때 date 가공
function dateProcessArray(arr, days) { 
	for(var i in arr) {
		arr[i].end = dateProcess(arr[i].end, days);
	}
}

//달력 표시 (출력)
function projectDisplay(dataArr) {
	$('#calendar').fullCalendar('removeEvents');
	dateProcessArray(dataArr, 1); //출력시 DB종료일 + 1일
	for(var i in dataArr) {
		$('#calendar').fullCalendar('renderEvent', dataArr[i], true);	
	}
	$('#mainScreen').hide()
	$('#calendar').show()
}

//프로젝트 선택보기
function projectView(projectNum,obj){
	
	$('.button').removeClass('btnactive')
	$(obj).addClass('btnactive')
	console.log('프로젝트 아이디를 받아서 다시 뿌려줘요')
	console.log("projectNum : " + projectNum);
	//$('#thisProjectNum').val(projectNum)
	var data = {projectNum:projectNum};
	$.ajax({
		url:"allboardlist.board",
		datatype:"json",
		data:data,
		success: function(data){
			var json = JSON.parse(data);
			var boardArr = boardData(json);
			projectDisplay(boardArr);
			memberList();
		
			$('#mainFooterbar').show()
		}
		
	})
	
}

//DB데이터 -> 달력 출력용 데이터
function boardData(json) {
	var boardArr = [];
	
	for(var i in json) {
		if(json[i].deleteOk==0){
			boardArr.push(
					{
						id: json[i].boardNum,
						title: json[i].boardTitle,
						start: json[i].boardStartDate.substring(0,10),
						end: json[i].boardEndDate.substring(0,10),
						color: json[i].label
					}
			);
		}
	}
	
	return boardArr;
}

//프로젝트 엔터누를 시 생성하도록 하는 함수
function nameinput() {
	$("#projectName").keypress(function(event) {
		var keycode = event.keyCode;
		console.log(keycode);
		if (keycode == '13') {
			console.log("엔터입력");
			var value = $("#projectName").val();
			
			if (value.trim() == "") {
				alert("프로젝트 이름을 입력해 주세요");
			} else {
				addproajax(value);
			}
		}
	})
}

//프로젝트 생성 관련 비동기 함수
function addproajax(value) {
	var data = {
		newprojectname : value
	};
	$.ajax({
		url : "addproject.project",
		data : data,
		datatype : "TEXT",
		success : function(data) {
			if (data.trim() == null || data.trim() <= 0) {
				alert("프로젝트 생성에 실패하였습니다");
			}
			callprojectlist();
		}
	})
}


