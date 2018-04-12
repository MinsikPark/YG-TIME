<%--
	파일명: calendar.jsp
	설명: 달력 페이지 jsp 작성
	작성일: 2018-04-12
	작성자: 강성훈
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>YG-TIME Calendar</title>
	<!-- 모바일 반응형을 위한 viewport 설정 -->
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<!-- external css files -->
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/fullcalendar.css" />
	<link rel='stylesheet' href="../css/fullcalendar.print.min.css" media="print" />
	<link rel='stylesheet' href="../css/ygtimeboard.css"/>
	<!-- external js files -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/validate.js/0.12.0/validate.min.js"></script>
	<script src="../js/jquery.mCustomScrollbar.concat.min.js"></script>
	
	<script src="../js/ygtimeboard.js"></script>
	<script src="../js/moment.min.js"></script>
	<!-- (주의: 순서)fullcalendar.js import는 위의 js files import 이후에 작성되어야 함 -->
	<script src="../js/fullcalendar.js"></script>
	<!-- (주의: 순서)locale-all.js(언어설정) import는 fullcalendar.js 밑에 작성되어야 함 -->
	<script src="../js/locale-all.js"></script>
	<!-- script - calendar.js -->
	<script src="../js/calendar.js"></script>
	<!-- style -->
	<style type="text/css">
		#calendar {
		    max-width: 900px;
		    margin : 0px 400px;
		}
	</style>
	<script type="text/javascript">
	$(function() {
	
		$('#mainScreen').hide()
		
		
	})
	</script>
</head>
<body>

	<jsp:include page="../sidenavi.jsp"></jsp:include>
	<!-- 테스트 버튼 -->
	
	<!-- 달력 표시되는 영역 -->
	<div id="calendar">
		<br>
		<img id="trashCan" src="../images/cal-trash.png"> <!-- 쓰레기통 이미지 -->
	</div>
	
	<!-- Dialog 영역 -->
	<!-- 초기에 깜빡이는 것을 없애기 위해 <div hidden ...> 사용 -->
<!-- 	<div hidden id="calEventDialog">
	    <form>
	        <fieldset>
		        <label for="eventTitle">보드명</label>
		        <input type="text" name="eventTitle" id="eventTitle" /><br>
		        <label for="eventStart">시작일</label>
		        <input type="text" name="eventStart" id="eventStart" /><br>
		        <label for="eventEnd">종료일</label>
		        <input type="text" name="eventEnd" id="eventEnd" /><br>
		        <label for="eventColor">보드바 색상</label>
		        <input type="color" name="eventColor" id="eventColor" /><br>
	        </fieldset>
	    </form>
	</div>
	 -->
	
		<div id="mainScreen">
		<h2>Title</h2><p>세부내용 블라블라</p>
		<hr>
		<div id="content-md">
			<div class="listbox">
				<div class="listtitle">하고잇는것하고잇는것하고잇는</div>
				<div class="card" data-toggle="modal" data-target="#myModal1">하하하하1</div>
			    <div class="card" data-toggle="modal" data-target="#myModal1">하하하하2</div>
			    <div class="card" data-toggle="modal" data-target="#myModal1">하하하하3</div>
			    <div class="card" data-toggle="modal" data-target="#myModal1">하하하하4</div>
			    <div class="card" data-toggle="modal" data-target="#myModal1">하하하하5</div>
			    <div class="card" data-toggle="modal" data-target="#myModal1">하하하하6</div>
			    <div class="card" data-toggle="modal" data-target="#myModal1">하하하하7</div>
			    <a class="cardcreate" onclick="addCardView(this)">Add a card...</a>
			</div>
			<a class="listbox" onclick="addListView(this)">Add a list...</a>
		</div>
	
	</div>
</body>
</html>