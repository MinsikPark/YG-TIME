<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
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
	    background-color: #111;
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
	
	//헤더 관련
	$("#btnLogin").click(function(){
		console.log("login 클릭");
 		if($("#loginEmail").val() =="" || $("#loginPwd").val() ==""){
			alert("아이디와 비밀번호를 입력해 주세요");
			
		}else{
			$("#formLogin").submit();
		}			
	}) // 로그인 비동기 처리 함수 
	//여기까지 헤더 관련 
	
	//헤더 관련 (profile 이미지 눌렀을 시) 초대메세지 리스트
	
	$("#profiledrop").click(function () {
        var param = {userId : $('#getsession').val(), };
        console.log($('#getsession').val());
        
       $.ajax({
            url:"list.member",
            datatype:"JSON",
            data:param,
            success:function(data){
            	console.log(">"+data.trim()+"<");
            	
                var json = JSON.parse(data);
                
                $("#dropdownchilddiv").empty();
                
                $.each(json, function(index, obj) {
                	var str = '<form id="fromUser'+index+'" class="form container-fluid">';
                	str += '<div class="form-group">';
                	str += '<label for="email">'+obj.inviteUserId+'님이 프로젝트로 초대 하였습니다</label>';
                	str += '<input type="button" value="Y" onclick="msgaccept(this,'+obj.projectNum+')">';
                	str += '<input type="button" value="N" onclick="msgreject(this,'+obj.projectNum+')">';
                	str += '</div></form></div>';
                	
                	$("#dropdownchilddiv").append(str);
                })
            }
            
        })
        
    })
    
    function msgaccept(me, projectNum){
		var params = {userid : $('#getsession').val(), projectnum : projectNum}
		
		console.log($('#getsession').val());
		console.log(projectNum);
		
		/* $.ajax({
            url:"msgagree.member",
            datatype:"JSON",
            data:params,
            success:function(data){
            	console.log(">"+data.trim()+"<");
            	
                var json = JSON.parse(data);
                
                $("#dropdownchilddiv").empty();
                
                $.each(json, function(index, obj) {
                	var str = '<form id="fromUser'+index+'" class="form container-fluid">';
                	str += '<div class="form-group">';
                	str += '<label for="email">'+obj.inviteUserId+'님이 프로젝트로 초대 하였습니다</label>';
                	str += '<input type="button" value="Y" onclick="msgaccept(this,'+obj.projectNum+')">';
                	str += '<input type="button" value="N" onclick="msgreject(this,'+obj.projectNum+')">';
                	str += '</div></form></div>';
                	
                	$("#dropdownchilddiv").append(str);
                }) 
            }
            
        }) */
	}
	//여기까지 초대메세지 리스트 뽑아오기(메세지 수락, 메세지 거절)
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

//jquery 로 간단하게 유효성 check 하기
$(function() {
 	$('#joinForm').submit(function() {
	   //alert("가입");
	if ($('#email').val() == "") { //이메일검사
   	alert('ID(email)를 입력해 주세요.');
   	$('#email').focus();
   return false;
   
  } else if ($('#password').val() == "") { //비밀번호 검사
   alert('PWD를 입력해 주세요.');
   $('#password').focus();
   return false;
   
  }else if ($('#passwordCheck').val() == "" ) {//passwordCheck 검사
	  
  $('#passwordCheck').focus();
   return false;
   
  }else if ($('#nickName').val() == "") { //nickName 검사
   alert('nickName를 입력해 주세요.');
   $('#nickName').focus();
   return false;
  }
    
 });
 	
  function mykeychange() {
 
	 
 			
		 	if($('#password').val() != "$('#passwordCheck').val()="   ){
		 		alert("일치");
		 	   		
		 	   	}else{
		 	   		alert("불일치2");
		 	   	}
  }	
 
});


</script>  
</head>
<!-- SIDEBAR -->
<body>
<div id="mySidenav" class="sidenav">

	<div id="sideNav" class="sidenav">
		<a href="#" class="glyphicon glyphicon-plus insert"></a>
		<div class="tab-content">
			<ul class="nav nav-tabs nav-tabs-modify">
				<li class="active"><a data-toggle="tab" href="#home">진행중인 프로젝트</a></li>
				<li><a data-toggle="tab" href="#menu1">완료된 &nbsp;   프로젝트</a></li>
			</ul>
			<div id="home" class="tab-pane fade in active">
				<div>
					<button class="button btn-1">Button 1</button>
					<a class="glyphicon glyphicon-cog setting"></a>
				</div>
				<div>
					<button class="button btn-1">Button 2</button>
					<a class="glyphicon glyphicon-cog setting"></a>
				</div>
			</div>
			<div id="menu1" class="tab-pane fade">
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
	      <a class="navbar-brand" href="#">YG Time</a>
	    </div>
	    <div> 
	      <!-- 로그인 창 -->
	      <ul class="nav navbar-nav navbar-right">
	        
	         <c:choose>
	        	<c:when test = "${sessionScope.sessionId eq null}"> 
			        <li data-toggle="modal" data-target="#myModal"><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
			        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Login <span class="glyphicon glyphicon-log-in"></span></a>
			          <div class="dropdown-menu">
			            <form id="formLogin" class="form container-fluid" action="login.member">
			              <div class="form-group">
			                <label for="email">Email:</label>
			                <input type="email" class="form-control" name="loginEmail" id="loginEmail">
			              </div>
			              <div class="form-group">
			                <label for="pwd">Password:</label>
			                <input type="password" class="form-control" name="loginPwd" id="loginPwd">
			              </div>
			            </form>
			             <button type="button" id="btnLogin" class="btn btn-block">Btn Login</button>
			             <button type="submit" class="btn btn-block">Submit Login</button> 
			            <div class="container-fluid">
			              <br>
			            </div> 
			          </div>
			        </li>
	        	</c:when>
	        	<c:otherwise>
	        		<li ><a href = "logout.member">Logout <span class="glyphicon glyphicon-log-out"></span></a></li> 
		        	<li id="profiledrop" class="dropdown" ><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span></a>
			        	<input id="getsession" type="hidden" value="${sessionScope.sessionId}">
			        	<div id = "dropdowndiv" class="dropdown-menu">
				        	<a id="profilmodify">회원정보 수정</a>
				        	<div id="dropdownchilddiv"></div>
			        	</div>
		        	</li>
				     
	        	</c:otherwise>
	        </c:choose>
	        
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
        	<form id ="joinForm" action="Join.member" method="post">
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
				    <input type="password" class="form-control" id="passwordCheck" placeholder="비밀번호를 입력하세요"  onchange = "mykeychange()">&nbsp;&nbsp;<span id="p"></span>     
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



</div>

     
</body>
</html> 
