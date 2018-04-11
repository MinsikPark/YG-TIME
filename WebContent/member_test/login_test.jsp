<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	
	
	
	var userId = "";
	$(function(){
		$("#loginbtn").click(function(){
			console.log("login 클릭");
	 		if($("#userId").val() =="" || $("#userPwd").val() ==""){
				alert("아이디와 비밀번호를 입력해 주세요");
				return;
			} 			
			var data = $("#loginform").serialize();
			console.log("2");
			$.ajax({
				url:"login.member",
				datatype:"html",
				data:data,
				success:function(data){
					console.log(">"+data.trim()+"<");
					if(data.trim()=="true"){
						userId = "<%= session.getAttribute("sessionId")%>";
				
						$("#header").append(
						"<h3>" +userId +"님 로그인 하셨습니다</h3>"	
						);
					}else if(data.trim()=="false"){
						alert("아이디와 비밀번호를 확인하세요");
						$("#userId").val("");
						$("#userPwd").val("");
					}else{
						alert("요청 실패");
					}
					
				}
			})
		})
	
	})
	

	

</script>
</head>
<body>


	<form id ="loginform">
		<input type = "text" name = "userId" id = "userId">
		<input type = "password" name = "userPwd" id= "userPwd">
	</form>
		<input type = "button" value = "로그인" id="loginbtn" >

<div id = "header"></div>


</body>
</html>
