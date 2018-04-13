<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<script type="text/javascript">
	$(function () {
		
		$("#send").click(function () {
			
			var obj = $("#listinsertForm").serialize();
			$.ajax({
				url : "listinsert.list" ,
				data : obj ,
				datatype : "json" ,
				success : function (data) {
					console.log(data);
				}
			})
			
		});
				
	});
</script>
<body>


		<form id = "listinsertForm">
		<input type="text" name="boardNum" value="보드번호">
		<input type="text" name="listNum" value="리스트번호">
		<input type="text" name="listSequential" value="리스트seq">
		
		<input id ="send" type="button" value="보내기" >
	
	</form>
</body>
</html>
