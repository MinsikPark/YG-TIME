<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/vader/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
  
<style>
	::-webkit-scrollbar {width: 12px; height: 5px;  }
	::-webkit-scrollbar-button:start:decrement, 
	::-webkit-scrollbar-button:end:increment {display: block; width: 12px;height: 12px; background: url() rgba(0,0,0,.05);}
	::-webkit-scrollbar-track {     background: rgba(0,0,0,.05); }
	::-webkit-scrollbar-thumb {  background: rgba(0,0,0,.1);  }

	body {
	    font-family: "Lato", sans-serif;
	}
	
	header {
		margin : 0 20px; 
	}
	.z-index{
		z-index: 10;
	}
	
	.sidenav {
	    height: 100%;
	    width: 0;
	    position: fixed;
	    z-index: 1;
	    top: 0;
	    left: 0;
	    background-color: #fed057;
	    overflow-x: hidden;
	    transition: 0.5s;
	    padding-top: 10px;
	  
	}
	
	.sidenav .insert {
	    padding: 8px 8px 8px 32px;
	    text-decoration: none;
	    font-size: 25px;
	    color: white;
	    display: block;
	    transition: 0.3s;
	    position: absolute;
	    margin-top: 10px;
	}
	
	.sidenav .setting {
		font-size:20px;
		top:4em;
	    float : left;
	    color:white;
	}
	
	#navigationBars {
	    transition: margin-left .5s;
	    margin-left:20px;
	}
	
	#mySidenav{
		width:20px;
	}
		
	.button,
	.button::after {
	  -webkit-transition: all 0.3s;
	}
	
	.button {
	  background: none;
	  border: 3px solid #fff;
	  border-radius: 5px;
	  color: #fff;
	  display: block;
	  font-weight: bold;
	  margin : 10px;
	  margin-left : 30px;
	  padding: 2em 6em;
	  position: relative;
	  text-transform: uppercase;
	  float:left;
	  height: 102px;
	  width: 245px;
	}
	
	.button::before,
	.button::after {
	  background: #fff;
	  content: '';
	  position: absolute;
	  z-index: -1;
	}
	
	.button:hover {
	  color: #2ecc71;
	}
	
	/* BUTTON 1 */
	.btn-1::after {
	  height: 0;
	  left: 0;
	  top: 0;
	  width: 100%;
	}
	
	.btn-1:hover:after {
	  height: 100%;
	}	
	
	.bottomnavbar {
	  overflow: hidden;
	  background-color: #333;  
	  position: fixed;
	  bottom: 0;
	  width: 100%;
	}
	
	.bottomnavbar a {
	  float: left;
	  display: block;
	  color: #f2f2f2;
	  text-align: center;
	  padding: 14px 16px;
	  text-decoration: none;
	  font-size: 17px;
	}
	
	.bottomnavbar a:hover {
	  background-color: #ddd;
	  color: black;
	}
	
	.bottomnavbar a.active {
	  background-color: #4CAF50;
	  color: white;
	}
	
	.bottomnavbar .icon {
	  display: none;
	}
	
	.bottomnav{
		bottom: 0;
	}
	.nav-tabs-modify{
	  margin-left: 120px;
	  margin-bottom:10px;
	}
	
	.nav-tabs li a {
		color:white;
		width: 100px;
	}
	
	/* 여기부터 상세페이지만을 위한 css  */
	
	input[type="checkbox"]{
	  position:absolute; 
	  overflow:hidden;
	  clip:rect(0 0 0 0);
	}
	
	p label{
	  height:15px; 
	  line-height:15px; 
	  padding-left:20px; 
	  display:inline-block; 
	  background:url(http://hcs1105.com/wp/wp-content/themes/hcs1105/images/checkbox1.png) no-repeat 0 0; 
	  font-size:15px; 
	  vertical-align:middle; 
	  cursor:pointer;
	  font-weight: 1;
	}
	 
	input[type="checkbox"]:checked + label{
	  background-position: 0 -15px;
	}
	
	.modalstyle {
		width:50%;
	}
	textarea {
		width:100%;
		height: 200px;
		float:left;
		margin:0px 10px 10px 0px;
		resize: none;	
	}
	
	.detailbutton{
		width:80%; 
		margin:20px 0px 0px 20px;
	}
	
	.container {
	  display: -webkit-flex;
	  display: flex;
	  width: 100%;
	}
	.flex1 {
	  -webkit-flex: 1;
	          flex: 1;
	}
	.flex2 {
	  -webkit-flex: 4;
	          flex: 4;
	}
	#btnFileUpload{
		display: none;
	}
	.inputtextbox{
		width:93%;
		float:left;
		margin : 4px 2px;
	}
	label[for=comment]{
		margin:10px;
	}
	img {
		float : left;
		margin: 5px ;
	}
	.commentlist{
		margin : 10px 5px;
	}
	.commentinputtextbox{
		width:90%;
		float:left;
		margin : 5px 2px;
	}
	@media screen and (max-width: 600px) {
		.inputtextbox{
			width: 85%;
		}
		.commentinputtextbox{
			width : 50%
		}
	}
	@media screen and (max-width: 1000px) {
		.commentinputtextbox{
			width : 80%
		}
	}
	@media screen and (min-width: 601px) {
		.inputtextbox{
			width:93%;
		}
	}
	@media screen and (min-width: 1001px) {
		.commentinputtextbox{
			width : 90%
		}
	}
	
</style>

<script type="text/javascript">

function projectView(projectNum){
	console.log('프로젝트 아이디를 받아서 다시 뿌려줘요')
	var data = {projectNum:projectNum};
	$.ajax({
		url:"allboardlist.board",
		datatype:"json",
		data:data,
		success: function(data){
			console.log(">"+data.trim()+"<");
		}
		
	})
	
}

</script>
</head>
<body>


<div class = "container"style="border:1px solid black"><button class="button btn-1" onclick="projectView(68)">프로젝트 끝난</button>
<a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a>
<ul class="dropdown-menu" style="float: right; position: unset;">
<li><a onclick="projectView('+projectNum+')">프로젝트 보기</a></li>
<li><a onclick="projectProgress('+projectNum+')">프로젝트 다시 진행</a></li></ul>
</div>			
			
									
<div class = "container"style="border:1px solid black"><button class="button btn-1" onclick="projectView(66)">프로젝트 진행중</button>								
<a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a>
<ul class="dropdown-menu" style="float: right; position: unset;">
<li><a onclick="projectDel('+projectNum+')">프로젝트 삭제</a></li>
<li><a onclick="projectComplete('+projectNum+')">프로젝트 완료</a></li></ul>
</div>								


<hr>

<div id ="boardlist">

</div>				
								


</body>
</html>
