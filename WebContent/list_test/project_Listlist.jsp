<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<script type="text/javascript">
$(function(){
	
	
	$("#ajax").click(function () {
        var param = {boardnum : $('#boardnum').val()};
        
       $.ajax({
            url:"Listlist.list",
            datatype:"JSON",
            data:param,
            success:function(data){
                var json = JSON.parse(data);
                console.log(json);
            }
        })
    })
});
</script>
</head>
<body>
		<input type="text" id="boardnum">
		<input type="button" id="ajax" value="확인">
		
</body>
</html>