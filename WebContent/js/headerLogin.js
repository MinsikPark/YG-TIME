$(function() {
	$("#btnLogin").click(function() {
		if ($("#loginEmail").val() == "" || $("#loginPwd").val() == "") {
			alert("아이디와 비밀번호를 입력해 주세요");

		}else{

			$("#formLogin").submit();
		}
	}) 
	
	if($("#getsession").val() != null){
		profileimglogin();
	}
});

function btnloginclick(){
	if ($("#loginEmail1").val() == "" || $("#loginPwd1").val() == "") {
		alert("아이디와 비밀번호를 입력해 주세요");

	} else {
		$("#loginform").submit();
	}
}

function profileimglogin(){

	var sessionid = $("#getsession").val();

	$.ajax({
		url : "profileimg.member",
		datatype : "json",
		data : {
			userId : sessionid
		},
		success : function(data) {
			var json = JSON.parse(data);
			$("#profiledrop > a > span").remove();
			var img = '<img class="img-circle" id="profileimg"style="width: 40px;height:40px;" src = "profile/'
						+ json.userProfile + '" />';
			$("#profiledrop > a").append(img);
		}
	});
}
