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
		top:3em;
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
</style>

<script>
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
        function myFunction() {
    	confirm("멤버를 삭제 하시겠습니까?");}

</script>
</head>
<!-- SIDEBAR -->
<body>
<div id="mySidenav" class="sidenav">

	<div id="sideNav" class="sidenav">
		<a href="#" class="glyphicon glyphicon-plus insert"></a>
		<div class="tab-content">
			<ul class="nav nav-tabs nav-tabs-modify">
				<li class="active"><a data-toggle="tab" href="#home">진행</a></li>
				<li><a data-toggle="tab" href="#menu1">완료</a></li>
			</ul>
			<div id="home" class="tab-pane fade in active">
				<div>
					<button class="button btn-1">Button 1</button>
					<a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a>
    				<ul class="dropdown-menu" style= "float: right; position: unset;">
      					<li><a href="#">프로젝트 삭제</a></li>
      					<li><a href="#">프로젝트 완료</a></li>
    				</ul>	
				</div>
				<div>
					<button class="button btn-1">Button 2</button>
					<a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a>
    				<ul class="dropdown-menu" style= "float: right; position: unset;">
      					<li><a href="#">프로젝트 삭제</a></li>
      					<li><a href="#">프로젝트 완료</a></li>
    				</ul>	
				</div>
			</div>
			<div id="menu1" class="tab-pane fade">
				<div>
					<button class="button btn-1">Button 3</button>
					<a class="glyphicon glyphicon-cog setting"  data-toggle="dropdown"></a>
    				<ul class="dropdown-menu" style= "float: right; position: unset;">
      					<li><a href="#">프로젝트 삭제</a></li>
      					<li><a href="#">프로젝트 완료</a></li>
    				</ul>	
				</div>
				<div class="dropdown">
					<button class="button btn-1">Button 4</button>
					<a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a>
    				<ul class="dropdown-menu" style= "float: right; position: unset;">
      					<li><a href="#">프로젝트 삭제</a></li>
      					<li><a href="#">프로젝트 완료</a></li>
    				</ul>	
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
	        <li data-toggle="modal" data-target="#myModal1"><a href="#"><span class="glyphicon glyphicon-user"></span> 상세페이지</a></li>
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
	              	<li data-toggle="modal" data-target="#myModal2"><a href="#">프로필 수정</a></li>
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
      <a class="navbar-brand" href="#">진행중인 MEMBER &nbsp;&nbsp;&nbsp;</a>
    </div>
    <div class="dropup">
			<a class = "glyphicon glyphicon-user" onclick="myFunction()" style="font-size: 25pt; top: 7px;"></a>
			<button type="button" class="btn btn-default" data-toggle="dropdown" style="margin: 0px 0px 10px 30px;">
				<span class="glyphicon glyphicon-plus" ></span> 멤버 추가
			</button>
			<ul class="dropdown-menu" style="width: 300px;">
				<li><div class="input-group">
						<div class="form-group">
				   			<input type="email" class="form-control" id="email" placeholder="이메일 입력..">
						</div>
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</li>
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
				    <label for="fileUpLoad">프로필 사진 설정</label>
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


<!--  보경이 상세페이지 끝   -->

</div>

     
</body>
</html> 
