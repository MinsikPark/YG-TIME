<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function () {
	
	$("#btn").click(function() {
		var data = $("#gg").serialize();
		console.log("data :" + data);
		$.ajax({
			url : "memberinsert.project",
			datatype : "JSON",
			data : data,
			success : function (data) {
				console.log("data : " + data);
			}
			})	
	})
	
})
</script>
<title>Insert title here</title>
</head>
<body>

<form id="gg">
<input type = "text" name="projectNum" value="projectNum" >
<input type = "text" name="userId" value = "userId" >
<input type = "text" name="grade"  value = "grade">
<input type = "button" id ="btn" value="안녕">
</form>
<div id = "div">
</div>
</body>
</html>