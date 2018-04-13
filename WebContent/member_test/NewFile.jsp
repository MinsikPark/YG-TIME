<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String uploadpath = application.getRealPath("upload");	
	
int size = 10*1024*1024; //10M
	MultipartRequest multi = new MultipartRequest(
			request,//기존 사용했던 request 객체 주소
			uploadpath, //실 저장 경로
			size,
			"UTF-8",
			new DefaultFileRenamePolicy() //파일 중복된 파일 올리면 (btn.jpg > 업로드시 > btn1.jpg )
		); 
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>