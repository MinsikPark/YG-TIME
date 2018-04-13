//회원가입 유효성 체크
function joinsubmit() {

	if ($('#email').val() == "") { //이메일검사
		idcheck();
		return false;

	} else if ($('#result').html() != "사용가능한 아이디입니다.") {
		alert("아이디 형식 또는 중복을 확인하세요");
		return false;
	} else if ($('#password').val() == "") { //비밀번호 검사
		/* alert('PWD를 입력해 주세요.');
		$('#password').focus(); */
		passwordfunction();
		return false;

	} else if ($("#nickName").val().trim() == "") { //nickName 검사
		Nicfunction();
		return false;
	}

	data = $("#joinForm").serialize();
	console.log("data : " + data);
	$.ajax({
		url : "Join.member",
		type : "post",
		data : data,
		success : function(data) {
			console.log(data);
			if (data.trim() == "success") {
				alert("회원가입에 성공하셨습니다");
				joinclear();
				$("#joinclose").trigger('click');
			} else {
				alert("회원가입에 실패하였습니다");
			}

		}
	});
}

//조인 모델 창 클리어
function joinclear() {
	$("#joinemail").val("");
	$("#joinpassword").val("");
	$("#joinpasswordCheck").val("");
	$("#joinnickName").val("");
	$("#joinresult").text("");
	$("#joinpwdcheck").text("");
	$("#joinnickcheck").text("");
}

//비밀번호 일치여부
function passwordfunction() {

	if ($("#joinpassword").val() != $("#passwordCheck").val()
			|| $("#password").val() == "") {
		$("#pwdcheck").css("color", "red");
		$("#pwdcheck").html("* 비밀번호가 일치 하지 않습니다.");
		$("#password").val('');
		$("#passwordCheck").val('');
		$("#password").focus();

	} else {
		$("#pwdcheck").css("color", "blue");
		$("#pwdcheck").html("* 비밀번호 일치");
		$("#nickName").focus();
	}

}

//닉네임 공백 여부 확인
function Nicfunction() {

	if ($("#nickName").val().trim() == "") {
		$("#nickcheck").html("* 닉네임을 입력해 주세요");
		$("#password").focus();

	} else {
		$("#nickcheck").html("");
	}

}

//아이디중복체크 비동기
function idcheck() {
	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

	$.ajax({
		url : "idcheck.member",
		data : {
			email : $("#email").val()
		},
		success : function(data) {

			if (data == "true") {
				$("#result").text("중복된 아이디입니다.");
				$("#result").css("color", "red");
			} else if (data == "false"
					|| exptext.test($('#email').val()) == true) {
				$("#result").text("사용가능한 아이디입니다.");
				$("#result").css("color", "blue");

			}
			if (exptext.test($('#email').val()) == false) {

				$("#result").text("이메일 형식이 올바르지 않습니다.");
				$("#result").css("color", "red");
			}
			if (data == "empty") {
				$("#result").text("이메일을 입력해주세요");
				$("#result").css("color", "red");
			}
		}
	});
}