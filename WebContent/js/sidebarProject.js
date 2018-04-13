//프로젝트 추가 From 생성
function addProjectForm(obj) {
	if ($('#projectName').length == 0) {
		var button = '<div><button class="button btn-1"><input type="text" id="projectName" name="projectName" style="margin-left:-60px; color:black;"></button><a class="glyphicon setting" onclick="addProject()">&#xe013;</a></div>'
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
function projectDel(projectNum) {
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

//프로젝트 선택보기
function projectView(projectNum) {
	console.log('프로젝트 아이디를 받아서 다시 뿌려줘요')
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