//회원정보 수정관련 함수
$(function() {

	$("#profilmodify").click(function() {
		var datas = {
			userId : '<%=session.getAttribute("sessionId")%>'
		};
		$.ajax({
			url : "mod.member",
			datatype : "JSON",
			data : datas,
			success : function(data) {
				console.log(data);
				var json = JSON.parse(data);
				$("#modemail").val(json.userId);
				$("#modnickName").val(json.userNicname);
				$("#modfileUpLoad").val(json.userProfile);
			}
		});

	});
})

//회원수정 비동기 유효성 함수
function modsubmit() {

	var param = $("#modForm").serialize();
	console.log(param)

	if ($('#modpassword').val() == "") { //비밀번호 검사
		alert('PWD를 입력해 주세요.');
		$('#password').focus();
		modpasswordfunction();
		return false;

	} else if ($("#modnickName").val().trim() == "") { //nickName 검사
		modNicfunction();
		return false;
	}

	$.ajax({
		url : "mod1.member",
		datatype : "JSON",
		data : param,
		success : function(data) {
			if (data <= 0) {
				alert("회원정보 수정에 실패하였습니다");
			} else {
				alert("회원정보 수정에 성공하였습니다.");
				$("#modclose").trigger("click");
			}
		}
	})

}

///비밀번호 일치 여부 확인
function modpasswordfunction() {

	if ($("#modpassword").val() != $("#modpasswordCheck").val()
			|| $("#modpassword").val() == "") {
		$("#modpwdcheck").css("color", "red");
		$("#modpwdcheck").html("* 비밀번호가 일치 하지 않습니다.");
		$("#modpassword").val('');
		$("#modpasswordCheck").val('');
		$("#modpassword").focus();

	} else {
		$("#modpwdcheck").css("color", "blue");
		$("#modpwdcheck").html("* 비밀번호 일치");
		$("#modnickName").focus();
	}

}

//닉네임 공백 여부 확인
function modNicfunction() {

	if ($("#modnickName").val().trim() == "") {
		$("#modnickcheck").html("* 닉네임을 입력해 주세요");
		$("#modpassword").focus();

	} else {
		$("#modnickcheck").html("");
	}

}

//수정 모델 클리어
function modclear() {
	$("#modemail").val("");
	$("#modpassword").val("");
	$("#modpasswordCheck").val("");
	$("#modnickName").val("");
	$("#modresult").text("");
	$("#modpwdcheck").text("");
	$("#modnickcheck").text("");
}