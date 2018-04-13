<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	String msg = (String) request.getAttribute("msg");
	String path =(String) request.getAttribute("path");
	String sessionid = (String) session.getAttribute("sessionId");
%>
<script>

	if("<%=msg%>"=="fail"){
		alert("아이디와 비밀번호를 확인해 주세요");
	}else if("<%=msg%>"=="error"){
		alert("유효하지 않는 요청입니다");
	}
	  location.href='<%=path%>';


</script>