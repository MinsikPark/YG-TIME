<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header>
	<nav class="navbar z-index">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"><img src="images/logo.JPG" style="width: 110px; height: 30px;" onclick="project()"></a>
		</div>
		<div>
			<ul class="nav navbar-nav navbar-right">
				<li data-toggle="modal" data-target="#myModal">
					<a href="#">
					<span class="glyphicon glyphicon-user"></span> Sign Up</a>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown">Login 
						<span class="glyphicon glyphicon-log-in"></span>
					</a>
					<div class="dropdown-menu">
						<form id="formLogin" class="form container-fluid">
							<div class="form-group">
								<label for="email">Email:</label> <input type="email" class="form-control" id="loginEmail">
							</div>
							<div class="form-group">
								<label for="pwd">Password:</label> <input type="password" class="form-control" id="loginPwd">
							</div>
							<button type="button" id="btnLogin" class="btn btn-block">Btn Login</button>
							<button type="submit" class="btn btn-block">Submit Login</button>
						</form>
						<div class="container-fluid">
							<br>
						</div>
					</div>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">님 환영합니다 
						<span class="glyphicon glyphicon-user"></span>
					</a>
					<div class="dropdown-menu">
						<form id="fromUser" class="form container-fluid">
							<div class="form-group">
								<label>프로젝트에 참여 하시겠습니까</label> <input type="button" value="Y">
								<input type="button" value="N">
							</div>
						</form>
					</div>
				</li>
			</ul>
		</div>
	</nav>
</header>