<%@page import="kr.co.ygtime.DTO.InviteMsgDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  

<form action="<%= request.getContextPath()%>/list.member" method="post">
	< <input type="text" name="userId" value="메세지번호"> 
	<!-- <input type="text" name="userId" value="아이디">
	<input type="text" name="projectNum" value="프로젝트번호">
	<input type="text" name="inviteUserId" value="초대한사람아이디"> -->
	<input type="submit" value="보내기">
	<!-- //<input type="text" name="msgDate" value="고용일"> -->
	
	</form>

</body>
</html>
