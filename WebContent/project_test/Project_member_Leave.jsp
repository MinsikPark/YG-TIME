<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
$(function () {
	$("#btn23").click(function() {
		var data = $("#for").serialize();
		$.ajax({
				url : "leave.project",
				data : data,
				datatype : "JSON",
				success : function () {
					console.log("data : " + data);
				}
			
			})
	})
})

</script>
</head>
<body>
<form id ="for">
<input type="text" value="userId" name ="userId">
<input type="text" value="projectNum" name = "projectNum">
<input type="button" id ="btn23" value="메롱">
</form>
</body>
</html>