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

	if(projectName.toLowerCase() == delProjectName.toLowerCase()){
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
function projectComplete(projectNum,event) {

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
	$('#calendarArea').show()
}
function lastModDateUpdate(pNum){
	$.ajax({
		url : "lastmoddateupdate.project",
		data : {projectNum : pNum}
	})
}
//프로젝트 선택보기
function projectView(projectNum,obj){
	$('.button').removeClass('btnactive')
	$(obj).addClass('btnactive')
	
	//마지막 수정날짜 업데이트
	lastModDateUpdate(projectNum)
	//프로젝트 뷰
	projectSelectView(projectNum,obj)
	
}
function projectStartView(projectNum){
	$('#btn'+projectNum).addClass('btnactive')
	projectSelectView(projectNum,$('#btn'+projectNum))
}

//프로젝트 선택보기
function projectSelectView(pNum,obj){
	$.ajax({
		url:"allboardlist.board",
		datatype:"json",
		data: {projectNum : pNum},
		success: function(data){
			projectNameSelect(pNum);
			
			var json = JSON.parse(data);
			var boardArr = boardData(json);
			projectDisplay(boardArr);
			memberList();
			$('#mainFooterbar').show()
			//프로젝트 내에 보드가 존재할 때, 존재하지 않으면 아래 코드 실행되지 않음
			console.log("json.length: " + json.length);
			if(json.length > 0) {
				completedProjectView(obj, json);
			}
		}
	});
}

//프로젝트이름 가져오기
function projectNameSelect(projectNum) {
	$("#projName").html("");
	$.ajax({
		url:"projectselect.project",
		datatype:"json",
		data:{projectNum:projectNum},
		success: function(responsedata){
			var responsejson = JSON.parse(responsedata);
			$("#projName").html(responsejson.projectName);
			$('#projName').attr("onclick", "projectNameEdit(this, "+ projectNum +")");
		}
	});
}

//프로젝트이름 클릭
function projectNameEdit(obj, projectNum){
	var htmlObj = $(obj).html();
	$(obj).removeAttr("onclick");
	$(obj).html('');
	var edit = "<input class='inputtext' type='text' placeholder=" + htmlObj + " name='projectTitle' onkeyup='fnChkByte(this,20)' onkeypress='if(event.keyCode==13){projectNameModifyOk(this,"+ projectNum +")}'><a onclick='projectNameModifyOk(this,"+ projectNum +")'>완료</a>";
	
	$(obj).append(edit);
	$(obj).children('input').focus();
}

//프로젝트 수정 확인
function projectNameModifyOk(obj, projectNum){
	var name = $(obj).parent().children("input").val();
	var data = {
			projectNum: projectNum,
			projectName: name
	};
	$.ajax({
		url:"projectnameupdate.project",
		datatype:"text",
		data:data,
		success:function(data){
			if(data.trim() <= 0){
				alert("프로젝트이름 변경 실패");
			}else{
				alert("프로젝트이름 변경 완료");
				callprojectlist();
				projectNameSelect(projectNum);
			}
		}
	});
}
//완료된 프로젝트의 가장 오래된 보드 날짜로 달력 이동
function completedProjectView(element, json) {
	if(element.id == "cp" || element.id == "cpa") {
		var oldestStartDate = json[0].boardStartDate;
		for(var i in json) {
			oldestStartDate = (json[i].boardStartDate < oldestStartDate) ? json[i].boardStartDate : oldestStartDate;
		}
		$('#calendar').fullCalendar('gotoDate', oldestStartDate, true);
	}else{
		$('#calendar').fullCalendar('today');
	}
	
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


