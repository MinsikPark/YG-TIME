<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

	$(function () {
		
		$('#sendbtn').click(function () {
			
		   var obj = $("#complete").serialize();
		   console.log(obj);
		   $.ajax({
			   
			   url : "completeproject.project",
			   data : obj,
			   datatype : "json" ,
			   success : function(data){
				   
				   console.log(data);
				   
			   }
			   
			   
			   
			   
			   
		   })
		   
		   
			
		});
		
		
		
		
		
		
		
		
	});

</script>
</head>
<body>



 
 	<form id = "complete">
		<input type="text" name="projectNum" value="프로젝트번호">
		<input type="text" name="userId" value="유저아이디">
		<input id ="sendbtn" type="button" value="보내기" >
	
	</form>
</body>
</html>