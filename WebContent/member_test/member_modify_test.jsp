<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(function(){
		//var data = $("#memberform").serialize();
		
		$("#btn").click(function(){
			var datas = $("#memberform").serialize();
			console.log(datas)
			console.log("데이터 뒤뒤뒤")
			$.ajax({
				 url:"mod.member",
		            datatype:"JSON",
		            data:datas,
		            success:function(data){
		            	console.log("여기ㅣㅣ")
		                console.log(">"+data.trim()+"<");
		            	 $("#memberform").append("<p>"+data+"</p>");
		            	 var json = JSON.parse(data);
		            	 $("#email").val(json.userId);
		            	 //$("#password").val(json.userPwd);
		            	 $("#nickName").val(json.userNicname);
		            	 $("#fileUpLoad").val(json.userProfile);
						}
				});
			
			});
	$("#btn1").click(function () {
		var param = $("#joinForm").serialize();
		console.log(param)
		if($("#password").val()==$("#passwordCheck").val()){
						alert("수정성공");	
		$.ajax({
				url:"mod1.member",
				datatype:"JSON",
				data:param,
				success: function (data) {
					console.log("data124 : "+ data);
				}
				})
		}else{
			alert("비밀번호달라요 다시입력해요");
			$("#password").val('');
			$("#passwordCheck").val('');
			$("#password").focus();
		}
		})
	})
		

</script>
</head>
<body>


 <form id = "memberform">
 <input type = "text" name = "userId"  value = "userId">
 <input type = "button" id="btn" value = "안녕">
 </form>
 
 <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">회원가입</h4>
        </div>
        
        
        <div class="modal-body" >
        	<form id ="joinForm">
	        	<div class="form-group">
				    <label for="email">이메일 주소</label>
				    <input type="email" class="form-control" id="email" name = "userId" readonly="readonly" placeholder="이메일을 입력하세요" value = json.user>
				</div>
	        	<div class="form-group">
				    <label for="password">비밀 번호</label>
				    <input type="password" class="form-control" id="password" name = "userPwd" placeholder="비밀번호를 입력하세요">
				</div>
	        	<div class="form-group">
				    <label for="passwordCheck">비밀 번호 확인</label>
				    <input type="password" class="form-control" id="passwordCheck" placeholder="비밀번호를 입력하세요">     
				</div>
	        	<div class="form-group">
				    <label for="nickName">닉네임</label>
				    <input type="text" class="form-control" id="nickName" name = "userNicname" placeholder="닉네임을 입력하세요">
				</div>
				<div class="form-group">
				    <label for="fileUpLoad">파일 업로드</label>
				    <input type="file" id="fileUpLoad" name = "userProfile">
				</div>
				<div class="modal-footer">
					<button type="button" id = "btn1" class="btn btn-default">Submit</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
        	</form>
        </div>
      </div>
    </div>
  </div> 
</body>
</html>
