<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/vader/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>

<style type="text/css">
	body{
		position: absolute;
		height: 100%;
		width: 100%;
	}
	#mainScreen {
		white-space: nowrap; 
		padding: 10px 10px 5px;
		background: #fff;
		width: 90%;
		position: absolute;
		margin-top:-30px;
		margin-left:70px;
		height: 90%;
		overflow-x: auto;
		overflow-y: hidden;
	}
	
	#content-md{
		height: 85%;
	}
	
	.listbox {
	    border:10px solid #e2e4e6;
	    border-radius: 12px;
	    background-color: #e2e4e6;
	    margin:10px;
	    width:300px;
	    float:left;
	    overflow-y: auto;
	    overflow-x: hidden;
	    position:relative;
	    max-height: 600px;
	}
	
	.listtitle{
		text-align:left;
	    font-weight:bold;
	    overflow: auto;
	    z-index: 2;
	}
	
	.card{
		margin-top:15px;
		border : 1px solid white;
	    border-radius: 8px;
	    background-color:white;
	    padding : 10px;
	}
	hr {
		clear: both;
	}
	.inputtext{
		background-color : transparent; 
		border:none; 
		outline:none;
		margin-left: 10px;
	}
</style>

<script type="text/javascript">
$(function(){
	$.mCustomScrollbar.defaults.scrollButtons.enable=true; //enable scrolling buttons by default
	$.mCustomScrollbar.defaults.axis="y"; //enable 2 axis scrollbars by default
	$("#content-md").mCustomScrollbar({theme:"minimal-dark"});
	
	autoWidth()
	sortable()
	
	/* $('.listbox').on('mousewheel',function(e){
		var wheelDelta = e.originalEvent.wheelDelta;

		if(wheelDelta > 0){

			$(this).scrollLeft(-wheelDelta + $(this).scrollLeft());

		}else{

			$(this).scrollLeft(-wheelDelta + $(this).scrollLeft());
		}
	});
	 */
	 
	$('#content-md').draggable(
		  {
			 axis: "x"
		},{
			stop: function() {
				var left = $('#content-md')[0].offsetLeft
				var maxwidth = $(window).width() - $('#content-md').width()
				
				if(left > 0){
					$('#content-md').css('left','0px')
				}else if($(window).width() > $('#content-md').width()){
					if(left < 0){ //화면크기가 div길이보다 크고 left가 0보다 작으면!!
						$('#content-md').css('left','0px')
					}
				}else if($(window).width() < $('#content-md').width()){
					if(left < maxwidth){ //화면크기가 div길이보다 작고 left가 maxwidth보다 작으면!!
						$('#content-md').css('left',maxwidth-80)
					}
				}
				$('#content-md').off('mousemove')
			}
		}
	)
	
	$('body').attr({
		oncontextmenu:"return false",
	 	onselectstart:"return false",
	}) 
	
})
function sortable(){
	$('.listbox').sortable({
		items:'div:not(.listtitle)',
		placeholder: "ui-state-highlight",
		connectWith: '.listbox'
	}).disableSelection(); 
}

function autoWidth(){
	var width = ($('.listbox').length) * 320 +"px";
	$('#content-md').css("width", width)
	$('#mainScreen').css("width", width)
}

function addCardView(e) {
	var div = "<div class='card'><input class='inputtext' type='text' placeholder='card title' name='title'><a onclick='addCard(this)'>완료</a></div>"
	$(e).before(div)
}

function addCard(e){
	var parent = $(e).closest('div')
	var value = parent[0].firstChild.value
	$(parent).empty()
	parent[0].innerHTML = value
	sortable()
}

function addListView(e){
	var div = "<div class='listbox'><input class='inputtext' type='text' placeholder='list title' name='title'><a onclick='addList(this)'>완료</a></div>"
	$(e).before(div)
	autoWidth()
}
function addList(e){
	var parent = $(e).closest('.listbox')
	var value = parent[0].firstChild.value
	parent.empty()
	var	div = '<div class="listtitle">' + value + '</div>'
		div += "<a class='cardcreate' onclick='addCardView(this)'>Add a card...</a>"
	parent.append(div)
}
</script>
</head>
<body >

	<jsp:include page="sidenavi.jsp"></jsp:include>
	
	<div id="mainScreen">
		<h2>Title</h2><p>세부내용 블라블라</p>
		<hr>
		<div id="content-md">
			<div class="listbox">
				<div class="listtitle">하고잇는것하고잇는것하고잇는</div>
				<div class="card" onclick="cardDetail">하하하하1</div>
			    <div class="card">하하하하2</div>
			    <div class="card">하하하하3</div>
			    <div class="card">하하하하4</div>
			    <div class="card">하하하하5</div>
			    <div class="card">하하하하6</div>
			    <div class="card">하하하하7</div>
			    <a class="cardcreate" onclick="addCardView(this)">Add a card...</a>
			</div>
			<a class="listbox" onclick="addListView(this)">Add a list...</a>
		</div>
	
	</div>
</body>
</html>