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
}); // 로그인 비동기 처리 함수 
	
	
	
	profileimglogin();
});

function profileimglogin() {
	
	 var sessionid = $("#getsession").val(); 

		 $.ajax({
			
			 url : "profileimg.member", 
			 datatype: "json",
			 data: {userId:sessionid},
			 success : function(data) { 
				 var json = JSON.parse(data); 
				 console.log(json);	
				 if( sessionid == null || json.userProfile == ""){ //회원의 프로필 사진이 null이라면
					 console.log("로그인상태");
						$("#profiledrop > a > span").remove();
						$("#profiledrop > a > img").remove();
						var span ="<span class = 'glyphicon glyphicon-user'style='width: 50px;height:50px' > </span>"
						$("#profiledrop > a ").append(span);
				 }else{ //회원의 프로필 사진이 있다면
					$("#profiledrop > a > span").remove();
					//이미지 태그 생성
					var img = '<img class="img-circle" id="profileimg"style="width: 50px;height:50px;" src = "profile/'+json.userProfile+'" />';
					
					 //이미지 태그를 $("#profiledrop > a 자식 업펜드
					 $("#profiledrop > a").append(img);
					 
				 }
				 		 
			}
			 
		 });
		 	 
 }
	


