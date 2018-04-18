<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>YG-TIME</title>
<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="css/board.css" />
<link rel="stylesheet" href="css/navi.css" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="js/jquery.form.js"></script>
<script src="js/board.js"></script>
<script src="js/navi.js"></script>
</head>

<body style="background-color: #e6e6e6" >
	<div id="movingBox" style = 'position: absolute;'></div>

	<jsp:include page="include/sidebar.jsp"></jsp:include>
	
	<div id="navigationBars">
		<jsp:include page="include/header.jsp"></jsp:include>
		<jsp:include page="include/footer.jsp"></jsp:include>
		<jsp:include page="include/addboard.jsp"></jsp:include>
		<jsp:include page="include/carddetail.jsp"></jsp:include>
		<jsp:include page="include/wifi.jsp"></jsp:include>
		<jsp:include page="include/register.jsp"></jsp:include>
		<jsp:include page="include/chart.jsp"></jsp:include>
		<jsp:include page="views/calendar.jsp"></jsp:include>
	</div>
	
	<div class="content-wrapper container">
		<input type="hidden" id="hiddenBoardnum">
		<div id="mainScreen" class="container-fluid">
			<h2 id='boardTitle'>Title</h2><p id='boardDetail'>세부내용 블라블라</p>
			<hr>
			<div id="content-md">
				<div class="listbox">
				    <a class="cardcreate" onclick="addCardView(this)">Add a card...</a>
				</div>
				<a class="listbox" onclick="addListView(this)">Add a list...</a>
			</div>
		</div>
	</div>
</body>
</html>