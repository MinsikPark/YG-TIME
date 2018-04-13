<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

 $(function () {
    
    $("#sub").click(function () {
        console.log("메롱");
        var param = $("#subform").serialize();
        $.ajax({
            url:"list.member",
            datatype:"JSON",
            data:param,
            success:function(data){
                console.log(">"+data.trim()+"<");
                
                $("#subform").append("<p>"+data+"</p>");
                
            }
            
        })
    })

 })
 
</script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id = "subform">
	   <input type="text" name="userId" value="메세지번호"> 
	    <input type="button" id = "sub" value="보내기">
    </form>
	
	
	
</body>
</html>
