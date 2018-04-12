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
 		if($("#loginEmail").val() =="" || $("#loginPwd").val() ==""){
			alert("아이디와 비밀번호를 입력해 주세요");
			
		}else{
			$("#formLogin").submit();
		}			
	}) // 로그인 비동기 처리 함수 
	//여기까지 헤더 관련 
	
	//헤더 관련 (profile 이미지 눌렀을 시) 초대메세지 리스트
	
	$("#profiledrop").click(function () {
        var param = {userId : $('#getsession').val()};
        
       $.ajax({
            url:"list.member",
            datatype:"JSON",
            data:param,
            success:function(data){
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
	//여기까지 초대메세지 리스트 뽑아오기(메세지 수락, 메세지 거절)
})

//초대승락
function msgaccept(me, projectNum){
	var param = {userid : $('#getsession').val(), projectnum : projectNum}
	
	$.ajax({
          url:"msgagree.member",
          datatype:"JSON",
          data:param,
          success:function(data){
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
      
      callprojectlist();
}

//초대거절
function msgreject(me, projectNum){
	var param = {userid : $('#getsession').val(), projectnum : projectNum}
	
	$.ajax({
          url:"msgdel.member",
          datatype:"JSON",
          data:param,
          success:function(data){
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
}
	
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
$(function() {

 	//로그인 체크 후 프로젝트 리스트 불러오는 함수
 	callprojectlist();
	
}); // onload 밖

	//회원가입 유효성 체크
	function joinsubmit() {
		   
		  if ($('#email').val() == "") { //이메일검사
			idcheck();
		    return false;
		   
		  } else if($('#result').html() != "사용가능한 아이디입니다."){
			alert("아이디를 형식 또는 중복을 확인하세요");
			return false;
		  } else if ($('#password').val() == "") { //비밀번호 검사
		   /* alert('PWD를 입력해 주세요.');
		   $('#password').focus(); */
		   passwordfunction();
		   return false;
		   
		  }else if ($("#nickName").val().trim() == "") { //nickName 검사
		   Nicfunction();
		   return false;
		  }   
		  
		  joinclear();
		  
		  $.ajax({
			  url : "Join.member",
			  type : "post",
			  data : {email:$("#email").val(), password:$("#password").val(), nickName:$("#nickName").val(), fileUpLoad:$('fileUpLoad').val() },
			  success : function(data){
				  alert("회원가입 되었습니다.");
			  }
		  });
	}
	
	function joinclear(){
		$("#email").val("");
		$("#password").val("");
		$("#passwordCheck").val("");
		$("#nickName").val("");
		$("#result").text("");
		$("#pwdcheck").text("");
		$("#nickcheck").text("");
	}
	
	///////////////////////////////프로젝트 관리 함수//////////////////////////////////////
	
	//////프로젝트 추가 From 생성
	function addProjectForm(obj){
		if($('#projectName').length == 0){
			var button = '<div><button class="button btn-1"><input type="text" id="projectName" name="projectName" style="margin-left:-60px; color:black;"></button><a class="glyphicon setting" onclick="addProject()">&#xe013;</a></div>'
				$('#progress').prepend(button)
			$('#projectName').focus();
			nameinput();
		
			
		}
	}
	
	//////멤버 삭제 
	function memberDel() {
	    confirm("멤버를 삭제하시겠습니까?");
	}
	
	
	
	
	/////프로젝트 추가
	function addProject() {
		var value = $('#projectName').val() 
		
		if(value.trim() != ""){
		
			addproajax(value);
			
		}else{
			alert('프로젝트 명을 입력하세요')
		}
		
	}
	
	///////프로젝트 삭제
	function projectDel(projectNum){
		console.log("projectNum :" + projectNum);
		var data3 = {projectNum:projectNum};
		console.log("data22 : " + data3);
		$.ajax({
					url : "delete.project",
					datatype : "text",
					data : data3,
					success : function (data) {
						console.log("data1 : " + data);
					}	
				})	

		callprojectlist();
	}
	
	
	//////프로젝트 완료
	function projectComplete(obj){
		$.ajax({
			url : "completeproject.project",
			data : {projectNum:obj, userId:$('#getsession').val()},//projectNum,userId
			datatype : "json" ,
			success : function(data){
				$("#complete").empty();
				$("#progress").empty();
				callprojectlist();
			}
		});

	}
	
	
	
	
	///////프로젝트복구
	function projectProgress(obj){
		$.ajax({
			url : "progressproject.project",
			data : {projectNum:obj, userId:$('#getsession').val()},//projectNum,userId
			datatype : "json" ,
			success : function(data){
				$("#complete").empty();
				$("#progress").empty();
				callprojectlist();
			}
		});
		
	}
	
	
	
	/////////프로젝트 선택보기
	function projectView(obj){
		console.log('프로젝트 아이디를 받아서 다시 뿌려줘요')
	}
	
	

	//////프로젝트 엔터누를 시 생성하도록 하는 함수
	function nameinput(){ 
		$("#projectName").keypress(function(event){
			 var keycode = event.keyCode;
			 console.log(keycode);
			 if(keycode =='13'){ 
				console.log("엔터입력");
				var value = $("#projectName").val();
				//프로젝트 이름이 입력 되었는지 검증한다.
				if(value.trim()==""){
					alert("프로젝트 이름을 입력해 주세요");
				}else{
					addproajax(value);	
				}				
			}
		})
	}
	
	/////////프로젝트 생성 관련 비동기 함수
	function addproajax(value){
		 var data ={
				 newprojectname:value
				   };
			$.ajax({
				url: "addproject.project",
				data:data,
				datatype:"TEXT",
				success:function(data){
					if(data.trim()==null ||data.trim()<=0){
						alert("프로젝트 생성에 실패하였습니다");				
					}
					callprojectlist(); 
				}
				
			}) 
		
	}

	//////////////프로젝트 리스트 불러오기
	function callprojectlist(){
		$("#progress").empty();
		$("#complete").empty();
		var sessionId = '<%=session.getAttribute("sessionId")%>';
		if(sessionId!=null){
			$.ajax({
				url:"projectlist.project",
				datatype:"json",
				data: {userId:sessionId},
				success:function(data){
					var json = JSON.parse(data);
					
					 $.each(json,function(key,value){
						var proejectName = value.projectName;
						var projectEndDate = value.projectEndDate;
						var projectNum = value.projectNum;
						//var projectStartDate = value.projectStartDate;
						
						if(projectEndDate != ""){ //시작 날짜가 비어있지 않다면 >> 프로젝트가 완료 되었다면
							$("#complete").append(
								'<div><button class="button btn-1">'
								+ proejectName + '</button>'
								+ '<a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a>'
								+ '<ul class="dropdown-menu" style="float: right; position: unset;">'
								+ '<li><a onclick="projectView('+projectNum+')">프로젝트 보기</a></li>'
								+ '<li><a onclick="projectProgress('+projectNum+')">프로젝트 다시 진행</a></li></ul></div>'	
							);
							
						}else{ // 프로젝트가 현재도 진행중이라면
							$("#progress").append(
								'<div><button class="button btn-1">'
								+ proejectName + '</button><a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a>'
								+'<ul class="dropdown-menu" style="float: right; position: unset;">'
								+'<li><a onclick="projectDel('+projectNum+')">프로젝트 삭제</a></li>'
								+'<li><a onclick="projectComplete('+projectNum+')">프로젝트 완료</a></li></ul></div>'
							)
							
							
						}
					})  
					
				}
			
			})
			
		}
		
	}
	
	//프로젝트 관리 함수 끝





//비밀번호 일치여부
function passwordfunction(){

	if($("#password").val() != $("#passwordCheck").val() || $("#password").val()==""){
		$("#pwdcheck").css("color", "red");
		$("#pwdcheck").html("* 비밀번호가 일치 하지 않습니다.");
		$("#password").val('');
		$("#passwordCheck").val('');
		$("#password").focus();

	}else{
		$("#pwdcheck").css("color", "blue");
		$("#pwdcheck").html("* 비밀번호 일치");
		$("#nickName").focus();
	}
	
}
//닉네임 공백 여부 확인
function Nicfunction(){

	if($("#nickName").val().trim() == ""){
		$("#nickcheck").html("* 닉네임을 입력해 주세요");
		$("#password").focus();

	}else{
		$("#nickcheck").html("");
	}
	
}
//아이디중복체크 비동기
function idcheck() {
	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	
	$.ajax({
	          url : "idcheck.member" ,
	          data :  { email:$("#email").val()},
	          success : function(data){
	         
	
	               if(data == "true"){
	                    $("#result").text("중복된 아이디입니다.");
	                    $("#result").css("color","red");
	               } 
	               else if(data == "false" || exptext.test($('#email').val()) == true){
	                    $("#result").text("사용가능한 아이디입니다.");
	                    $("#result").css("color","blue");
	                    
	               }
	               if(exptext.test($('#email').val()) == false){
	                   
	        			  $("#result").text("이메일 형식이 올바르지 않습니다.");
	                    $("#result").css("color","red");
	               } 
	               if (data == "empty" ) {
	             	   $("#result").text("이메일을 입력해주세요");
	                    $("#result").css("color","red");
	 				 }
	          }
	});
}




</script>  
</head>
<!-- SIDEBAR -->
<body>
	<c:if test="${!empty sessionScope.sessionId}">
		<div id="mySidenav" class="sidenav">
		
			<div id="sideNav" class="sidenav">
			
				
				<!-- <a href="#" class="glyphicon glyphicon-plus insert"></a> -->
				<div class="tab-content">
					<a href="#" class="glyphicon glyphicon-plus insert" onclick="addProjectForm()"></a>
					<ul class="nav nav-tabs nav-tabs-modify">
						<li class="active"><a data-toggle="tab" href="#progress">진행중인 프로젝트</a></li>
						<li><a data-toggle="tab" href="#complete">완료된 &nbsp;   프로젝트</a></li>
					</ul>
			
					<div id="progress" class="tab-pane fade in active"> <!-- 진행중인 프로젝트 -->
					
					</div>
					<div id="complete" class="tab-pane fade"> <!-- 완료 -->
					</div>
				</div>
			</div>
		</div>
	</c:if>

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
		        	<li id="profiledrop" class="dropdown" ><a class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span></a>
			        	<input id="getsession" type="hidden" value="${sessionScope.sessionId}">
			        	<div id = "dropdowndiv" class="dropdown-menu">
				        	<a id="profilmodify">회원정보 수정</a><br>
				        	<!-- <a>초대리스트</a> -->
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
          <button type="button" class="close" data-dismiss="modal" onclick="joinclear()">&times;</button>
          <h4 class="modal-title">회원가입</h4>
        </div>
        
        
        <div class="modal-body">
        	<form id ="joinForm">
	        	<div class="form-group">
				    <label for="email">이메일 주소</label>
				    <button id="idcheckhover" type="button"  class ="btn btn-default" onclick="idcheck()">이메일 중복확인</button> 
				    <span id="result"></span> <br><br>
				    <input type="email" class="form-control" id="email" name="email" placeholder="이메일을 입력하세요" onfocus="idcheck()" onchange="idcheck()">
				 
	
					
				
				</div>
	        	<div class="form-group">
				    <label for="password">비밀 번호</label>
				    <input type="password" class="form-control" id="password"  name="password" >
				</div>
	        	<div class="form-group">
				    <label for="passwordCheck">비밀 번호 확인 <span id="pwdcheck" style="color: red;"></span></label>
				    <input type="password" class="form-control" id="passwordCheck" name="passwordCheck" onchange="passwordfunction()">
				</div>
	        	<div class="form-group">
				    <label for="nickName">닉네임  <span id="nickcheck" style="color: red;"></span></label>
				    <input type="text" class="form-control" id="nickName" name="nickName">
				</div>
				<div class="form-group">
				    <label for="fileUpLoad">파일 업로드</label>
				    <input type="file" id="fileUpLoad" name="fileUpLoad">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="joinsubmit()">Submit</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="joinclear()">Close</button>
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
