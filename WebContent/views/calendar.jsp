<%--
	파일명: calendar.jsp
	설명: 달력 페이지 jsp 작성
	작성일: 2018-04-12
	작성자: 강성훈
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/fullcalendar.print.min.css" media="print" />

<script>

$(function(){

	var projectNum = <%=session.getAttribute("projectNum")%>;
	if(projectNum!=null && projectNum != ""){
		projectView(this, projectNum);
	}

});

</script>

<script src="js/moment.min.js"></script>
<!-- (주의: 순서)fullcalendar.js import는 위의 js files import 이후에 작성되어야 함 -->
<script src="js/fullcalendar.js"></script>
<!-- (주의: 순서)locale-all.js(언어설정) import는 fullcalendar.js 밑에 작성되어야 함 -->
<script src="js/locale-all.js"></script>
<!-- script - calendar.js -->
<script src="js/calendar.js"></script>
<!-- calendar -->
<div id="calendar">
	<br>
	<img id="trashCan" src="images/trashcan.png" height="35px" width="35px"> 
</div>

	
