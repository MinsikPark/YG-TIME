<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function () {
	$("#delete").click(function () {
		var data = $("#del").serialize();
		console.log("data : " + data);
		$.ajax({
					url : "delete.project",
					datatype : "JSON",
					data : data,
					success : function (data) {
						console.log("data1 : " + data);
					}
				})	
	})
})

</script>
</head>
<body>
<form id="del">
<input type="text" id = "pronum" name="projectNum">
<input type="button" id = "delete" value="삭제">


</form>
</body>
</html>