<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
	
	/* 여기까지 상세페이지만을 위한 css  */
</style>

<script>
var i = 1	
$(function(){
	$('.setting').hide()
	$('.button').hide()
	$('.tab-content').hide()
	
	//사이드 관련
	$('#navigationBars').click(function() {
		sideHide()
	}) 
	$('#mainScreen').click(function() {
		sideHide()
	})
	 
	$('#mySidenav').click(function() {
		sideShow()
	})
	//여기까지 사이드 관련
	$('#btnFileUpLoad').click(function() {
		$('#btnFileUpload').click()
	})
	
	$('#addCheckList').click(function(){
		addCheckListForm()
	})
})

//사이드 숨길때
function sideHide(){
	$('#sideNav').css("width", "0px")
	$('#navigationBars').css("marginLeft", "20px")
	$('#mainScreen').css("marginLeft", "70px")
	$('.setting').hide()
	$('.insert').hide()
	$('.button').hide()
	$('.tab-content').hide()
}
//사이드 보일때
function sideShow(){
	$('#sideNav').css("width", "330px")
	$('#navigationBars').css("marginLeft", "330px")
	$('#mainScreen').css("marginLeft", "380px")
	$('.tab-content').delay(250).fadeIn()
	$('.button').delay(250).fadeIn()
	$('.setting').delay(250).fadeIn()
	$('.insert').delay(250).fadeIn()
} 

function changeValue(obj){
	if(obj.value != ""){
		var div = '<div><input type="text" class="form-control inputtextbox" value="첨부 파일  : '+ obj.value.substr(12)+'" readonly>'
			div += '<button type="button" class="close" onclick="fileInputRemove(this)">&times;</button></div>'
		$('#fileUploadForm').append(div)
	}
	obj.value =""; 
}

function fileInputRemove(obj){
	obj.closest('div').remove()
}

function addCheckListForm(){
	var div = '<div><input type="text" class="form-control inputtextbox">'
		div += '<button type="button" class="close glyphicon" onclick="addCheckList(this)">&#xe013;</button></div>'

		$('label[for=checklist]').after(div)
}

function addCheckList(obj) {
	var value = $(obj).closest('div')[0].children[0].value
	if(value != ""){
		var div = '<p><input type="checkbox" id="checkbox'+i+'"><label for="checkbox'+i+'">'+value+'</label><button type="button" class="close" onclick="removeCheckList(this)">&times;</button></p>'
		i++
		
		obj.closest('div').remove()
		
		$('#checkListForm').append(div)
	}
}

function removeCheckList(obj){
	$(obj).closest('p').remove()
}

function addComment(obj){
	var value = $(obj).closest('div')[0].children[1].value
	if(value != ""){
		var div = '<div class="commentlist"><img src="img/bg-banner.jpg" class="img-circle person" alt="Random Name" width="30" height="30">'
			div += '<input type="text" class="form-control commentinputtextbox" value="' + value + '" readonly>'
			div += '<button type="button" class="close" onclick="removeComment(this)">&times;</button></div>'
			
		$('#commentListForm').append(div)
		$(obj).closest('div')[0].children[1].value = ""
	}
}

function removeComment(obj){
	$(obj).closest('div').remove()
}

function updateDetail(obj){
	console.log($('#contentDetail')[0].value)
}

//프로젝트 추가부분
function addProjectForm(obj){
	var button = '<div><button class="button btn-1"><input type="text" style="margin-left:-50px;"></button><a class="glyphicon glyphicon-cog setting"></a></div>'
	$('#progress').append(button)
}

</script>  
</head>
<!-- SIDEBAR -->
<body>
<div id="mySidenav" class="sidenav">

	<div id="sideNav" class="sidenav">
		<a href="#" class="glyphicon glyphicon-plus insert" onclick="addProjectForm()"></a>
		<div class="tab-content">
			<ul class="nav nav-tabs nav-tabs-modify">
				<li class="active"><a data-toggle="tab" href="#progress">진행중인 프로젝트</a></li>
				<li><a data-toggle="tab" href="#complete">완료된 &nbsp;   프로젝트</a></li>
			</ul>
			<div id="progress" class="tab-pane fade in active">
				<div>
					<button class="button btn-1">Button 1</button>
					<a class="glyphicon glyphicon-cog setting"></a>
				</div>
				<div>
					<button class="button btn-1">Button 2</button>
					<a class="glyphicon glyphicon-cog setting"></a>
				</div>
			</div>
			<div id="complete" class="tab-pane fade">
				<div>
					<button class="button btn-1">Button 3</button>
					<a class="glyphicon glyphicon-cog setting"></a>
				</div>
				<div>
					<button class="button btn-1">Button 4</button>
					<a class="glyphicon glyphicon-cog setting"></a>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="navigationBars">
<!-- SIDEBAR END -->

<!-- HEADER -->
<header>
	<nav class="navbar">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#"><img src="images/logo.JPG" style="width: 110px; height: 30px;"></a>
	    </div>
	    <div> 
	      <!-- 로그인 창 -->
	      <ul class="nav navbar-nav navbar-right">
	        <li data-toggle="modal" data-target="#myModal"><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
	        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Login <span class="glyphicon glyphicon-log-in"></span></a>
	          <div class="dropdown-menu">
	            <form id="formLogin" class="form container-fluid">
	              <div class="form-group">
	                <label for="email">Email:</label>
	                <input type="email" class="form-control" id="loginEmail">
	              </div>
	              <div class="form-group">
	                <label for="pwd">Password:</label>
	                <input type="password" class="form-control" id="loginPwd">
	              </div>
	              <button type="button" id="btnLogin" class="btn btn-block">Btn Login</button>
	              <button type="submit" class="btn btn-block">Submit Login</button>
	            </form>
	            <div class="container-fluid">
	              <br>
	            </div> 
	          </div>
	        </li>
	         <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">님 환영합니다 <span class="glyphicon glyphicon-user"></span></a>
	          <div class="dropdown-menu">
	            <form id="fromUser" class="form container-fluid">
	              <div class="form-group">
	                <label for="email">프로젝트에 참여 하시겠습니까</label>
	                <input type="button" value="Y">
	                <input type="button" value="N">
	              </div>
	            </form>
	          </div>
	        </li>
	      </ul>
	      <!-- 로그인 창 -->
	    </div>
	</nav>
</header>
<!-- HEADER END -->

<!-- FOOTER -->
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">진행중인 MEMBER</a>
    </div>
    <div> 
      <ul class="nav navbar-nav">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> 지너니 </a></li> 
      </ul>
    </div>
</nav>
<!-- FOOTER END -->

<!-- MODAL-->
 <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">회원가입</h4>
        </div>
        <div class="modal-body">
        	<form action="#" method="post">
	        	<div class="form-group">
				    <label for="email">이메일 주소</label>
				    <input type="email" class="form-control" id="email" placeholder="이메일을 입력하세요">
				</div>
	        	<div class="form-group">
				    <label for="password">비밀 번호</label>
				    <input type="password" class="form-control" id="password" placeholder="비밀번호를 입력하세요">
				</div>
	        	<div class="form-group">
				    <label for="passwordCheck">비밀 번호 확인</label>
				    <input type="password" class="form-control" id="passwordCheck" placeholder="비밀번호를 입력하세요">
				</div>
	        	<div class="form-group">
				    <label for="nickName">닉네임</label>
				    <input type="text" class="form-control" id="nickName" placeholder="닉네임을 입력하세요">
				</div>
				<div class="form-group">
				    <label for="fileUpLoad">파일 업로드</label>
				    <input type="file" id="fileUpLoad">
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
        	</form>
        </div>
      </div>
    </div>
  </div> 
<!-- MODAL END -->  


<!--  보경이 상세페이지   -->
  <div class="modal fade" id="myModal1" role="dialog">
    <div class="modal-dialog modal-lg">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body container">
        	<div class="flex2">
	        	<form action="#" method="post"> 
		        	<div class="form-group">
					    <label for="content">상세 내용</label>
					</div>
					<textarea id="contentDetail"></textarea>
				  	<div class="form-group">
				  		<button class="btn btn-success" type="button" onclick="updateDetail(this)">작성</button>
				  	</div>
		        	<div class="form-group" id="fileUploadForm">
				    </div>
				    <div class="form-group">
					    <label for="checklist">Check List</label>
					    <div id="checkListForm"></div>
					</div>
		        	<div class="form-group">
					    <label for="comment">댓글</label>
					    <input type="text" class="form-control commentlist" placeholder="댓글을 입력하세요">
					    <button class="btn btn-success" type="button" onclick="addComment(this)">작성</button>
					    <div id="commentListForm"></div>
					</div>
	        	</form>
        	</div>
			<div class="flex1"><br>
				<input id="btnFileUpload" type="file" onchange="changeValue(this)">
				<input class="detailbutton btn btn-primary" type="button" value="파일 추가 하기" id="btnFileUpLoad">
				<input class="detailbutton btn btn-primary" type="button" value="Check List" id="addCheckList">
				<div class="dropdown">
					<input class="detailbutton btn btn-primary" type="button" value="Member" data-toggle="dropdown">
					<ul class="dropdown-menu">
						<li><a href="#">HTML</a></li>
						<li><a href="#">CSS</a></li>
						<li><a href="#">JavaScript</a></li>
					</ul>
				</div>
			</div>
	  	 </div>
      </div>
    </div>
  </div> 
 </div>

<!--  보경이 상세페이지 끝   -->


     
</body>
</html> 
