$(function(){
	$("#btnLogin").click(function(){
		if($("#loginEmail").val() =="" || $("#loginPwd").val() ==""){
			alert("아이디와 비밀번호를 입력해 주세요");
			
		}else{
			$("#formLogin").submit();
		}			
	}) // 로그인 비동기 처리 함수 
	
	$("#btnLogin1").click(function(){
		console.log('실행되까여?')
		if($("#loginEmail1").val() =="" || $("#loginPwd1").val() ==""){
			alert("아이디와 비밀번호를 입력해 주세요");
			
		}else{
			$("#loginform").submit();
		}			
	}) // 로그인 비동기 처리 함수 
});