//회원정보 수정관련 함수
$(function() {
	$("#modfileUpLoad").hide();
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
				$("#currentProfile").attr("src", "profile/"+json.userProfile);
			}
		});

	});
})

//회원수정 비동기 유효성 함수
function modsubmit() {
	console.log("gdgdgdg")

	var param = $("#modForm").serialize();
	console.log(param)
	console.log("ddddddd들어감");
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
				profileimgmodify();
				$("#modclose").trigger("click");
			}
		}
	})

}

// 프로필 이미지 수정을 위해 클릭할 경우 fileuplaod 실행되는 함수
function modupload(){
	$('#modfileUpLoad').trigger("click");
	
}

function profileimgmodify() {
	console.log("실행")
	var data = $("#modprofile").serialize();
	console.log(">"+data+"<");
	 $("#modprofile").ajaxForm({
		 url : "profileImgUpdate.member", 
		 datatype: "json",
		 type:"post",
		 enctype: "multipart/form-data",
		 beforeSubmit:function(data, frm, opt){
			console.log("before data11 : " + data);				
			console.log("before frm : " + frm);
			console.log("befor opt: " + opt);
		 },
		 success : function(data) { 
			if(data.trim()>0){
				alert("프로필을 수정하였습니다.");
			}
		 },
		 error: function(){
			 alert("업로드에 실패하였습니다");
		 }

	 });
	 $("#modprofile").submit();
	 l
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