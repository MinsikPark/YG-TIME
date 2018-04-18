<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String) request.getAttribute("msg");
	String path =(String) request.getAttribute("path");
	String sessionid = (String) session.getAttribute("sessionId");
%>

<script>
	console.log(<%=msg%>)
	if("<%=msg%>"=="0"){
		alert("아이디와 비밀번호를 확인해 주세요");
	}
	  location.href='<%=path%>';


</script>