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
						<li><a href="logout.member">Logout <span
								class="glyphicon glyphicon-log-out"></span></a></li>
						<li id="profiledrop" class="dropdown"><a
							class="dropdown-toggle" data-toggle="dropdown"><span
								class="glyphicon glyphicon-user"></span></a> <input id="getsession"
							type="hidden" value="${sessionScope.sessionId}">
							<div id="dropdowndiv" class="dropdown-menu">
								<ul>
									<li data-toggle="modal" data-target="#myModal2"><a
										id="profilmodify">회원정보 수정</a><br></li>
								</ul>
								<!-- <a>초대리스트</a> -->
								<div id="dropdownchilddiv"></div>

							</div></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
</header>

<!-- MODAL-->
<!-- 회원가입 -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					onclick="joinclear()">&times;</button>
				<h4 class="modal-title">회원가입</h4>
			</div>

			<div class="modal-body">
				<form id="joinForm">
					<div class="form-group">
						<label for="email">이메일 주소</label>
						<button id="idcheckhover" type="button" class="btn btn-default"
							onclick="idcheck()">이메일 중복확인</button>
						<span id="result"></span> <br> <br> <input type="email"
							class="form-control" id="email" name="email"
							placeholder="이메일을 입력하세요" onfocus="idcheck()" onchange="idcheck()">

					</div>
					<div class="form-group">
						<label for="password">비밀 번호</label> <input type="password"
							class="form-control" id="password" name="password">
					</div>
					<div class="form-group">
						<label for="passwordCheck">비밀 번호 확인 <span id="pwdcheck"
							style="color: red;"></span></label> <input type="password"
							class="form-control" id="passwordCheck" name="passwordCheck"
							onchange="passwordfunction()">
					</div>
					<div class="form-group">
						<label for="nickName">닉네임 <span id="nickcheck"
							style="color: red;"></span></label> <input type="text"
							class="form-control" id="nickName" name="nickName">
					</div>
					<div class="form-group">
						<label for="fileUpLoad">파일 업로드</label> <input type="file"
							id="fileUpLoad" name="fileUpLoad">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							onclick="joinsubmit()">Submit</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="joinclose" onclick="joinclear()">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 회원가입 끝  -->
<!-- 회원수정 -->
<div class="modal fade" id="myModal2" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					onclick="modclear()">&times;</button>
				<h4 class="modal-title">회원정보 수정</h4>
			</div>


			<div class="modal-body">
				<form id="modForm">
					<div class="form-group">
						<label for="modemail">이메일 주소</label> <input type="text"
							name="userId" value="${sessionScope.sessionId}"
							readonly="readonly" class="form-control">
					</div>
					<div class="form-group">
						<label for="modpassword">비밀 번호</label> <input type="password"
							class="form-control" id="modpassword" name="modpassword">
					</div>
					<div class="form-group">
						<label for="modpasswordCheck">비밀 번호 확인 <span
							id="modpwdcheck" style="color: red;"></span></label> <input
							type="password" class="form-control" id="modpasswordCheck"
							name="modpasswordCheck" onchange="modpasswordfunction()">
					</div>
					<div class="form-group">
						<label for="modnickName">닉네임 <span id="modnickcheck"
							style="color: red;"></span></label> <input type="text"
							class="form-control" id="modnickName" name="modnickName">
					</div>
					<div class="form-group">
						<label for="modfileUpLoad">파일 업로드</label> <input type="file"
							id="modfileUpLoad" name="modfileUpLoad">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							onclick="modsubmit()">Submit</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="modclose" onclick="modclear()">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- MODAL END -->
