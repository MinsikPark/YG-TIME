<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<header>
	<nav class="navbar z-index">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"><img src="images/logo.JPG"
				style="width: 110px; height: 30px;" onclick="project()"></a>
		</div>
		<div>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${sessionScope.sessionId eq null}">
						<li data-toggle="modal" data-target="#myModal"><a href="#"><span
								class="glyphicon glyphicon-user"></span> Sign Up</a></li>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown">Login <span
								class="glyphicon glyphicon-log-in"></span></a>
							<div class="dropdown-menu">
								<form id="formLogin" class="form container-fluid"
									action="login.member">
									<div class="form-group">
										<label for="email">Email:</label> <input type="email"
											class="form-control" name="loginEmail" id="loginEmail">
									</div>
									<div class="form-group">
										<label for="pwd">Password:</label> <input type="password"
											class="form-control" name="loginPwd" id="loginPwd">
									</div>
								</form>
								<button type="button" id="btnLogin" class="btn btn-block">Btn
									Login</button>
								<button type="submit" class="btn btn-block">Submit
									Login</button>
								<div class="container-fluid">
									<br>
								</div>
							</div></li>
					</c:when>
					<c:otherwise>
						<input id="getsession" type="hidden" value="${sessionScope.sessionId}">
						<li>
							<a href="logout.member">Logout 
								<span class="glyphicon glyphicon-log-out"></span>
							</a>
						</li>
				        	
						<li id="profiledrop" class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown">
								<span class="glyphicon glyphicon-user"></span>
							</a> 
							<ul class="dropdown-menu">
								<li data-toggle="modal"  data-target="#myModal2">
							      <a id="profilmodify">회원정보 수정</a>
								</li>
							      <li>
							        <a class="message" tabindex="-1" href="#">초대 메시지함<span class="caret"></span></a>
							        <ul class="dropdown-menu">
							      
							          <li>  
							          	<div id="dropdownchilddiv"></div>
							          </li>
							        </ul>
							      </li>
							    </ul>
							
							<!-- 	<div id="dropdowndiv" class="dropdown-menu">
									<ul>
										<li data-toggle="modal" data-target="#myModal2">
											<a id="profilmodify">회원정보 수정</a><br>
										</li>
									</ul>
									<a>초대리스트</a>
									<div id="dropdownchilddiv"></div>
								</div> -->
							</li>
					</c:otherwise>
				</c:choose>
			</ul>
							<!-- <div class="dropdown">
							    <a class = "glyphicon glyphicon-user" data-toggle="dropdown"></a>
							    <ul class="dropdown-menu">
							      <li><a tabindex="-1" href="#">프로필 수정</a></li>
							      <li>
							        <a class="joinmessage" tabindex="-1" href="#">초대 메시지함<span class="caret"></span></a>
							        <ul class="dropdown-menu">
							          <li><a tabindex="-1" href="#">1nd 프로젝트에 초대 되었습니다 </a>
							          	<button style="float:right; margin:5px;" class="btn btn-warning">거절</button>
							          	<button style="float:right; margin:5px;" class="btn btn-success">수락</button>
							          </li>
							          <li><a tabindex="-1" href="#">2nd 프로젝트에 초대 되었습니다 </a>
							          	<button style="float:right; margin:5px;" class="btn btn-warning">거절</button>
							          	<button style="float:right; margin:5px;" class="btn btn-success">수락</button>
						          	  </li>
							        </ul>
							      </li>
							    </ul>
							  </div> -->
		</div>
	</nav>
</header>
