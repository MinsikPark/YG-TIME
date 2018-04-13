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
		
		
	

$("#btn1").click(function() {
	var sessionpnum = $('#gg').serialize();  <%-- <%=session.getAttribute("projectNum")%> --%>
	console.log(sessionpnum);
	$.ajax({
			url : "memberlist.project",
			data : sessionpnum,
			datatype : "JSON",
			success : function (data) {
				console.log("data : " + data);
			}
			
			})
	

	
	
})	
})
</script>

</head>
<body>
<form id="gg">
<input type = "text" id = "mem" name = "projectNum" >
<input type = "button" id = "btn1" value="윤근짱">
</form>
</body>
</html>