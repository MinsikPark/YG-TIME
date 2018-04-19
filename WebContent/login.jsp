<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/headerLogin.js"></script>
<script src="js/headerJoin.js"></script>
<title>YG-TIME</title>
<style type="text/css"> 
body {
	background-image: url(images/login-background.jpg);
	background-size: cover;
	background-repeat: no-repeat;
	position: fixed;
	background-attachment: fixed;
	background-position: 50% 50%;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	content: "";
	z-index: -1000;
}
</style>

</head>
<body>
<jsp:include page="include/register.jsp"></jsp:include>
	<div class="container">
		<div style="top: 100px;"
			class="col-md-6 col-md-offset-3">
				<img src="<%=request.getContextPath()%>/images/ygtime.png" style="width:100%">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>

				<div class="panel-body">

					<form id="loginform" class="form-horizontal" role="form" action="<%=request.getContextPath()%>/login.member">

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="loginEmail"
								type="text" class="form-control" name="loginEmail" value=""
								placeholder="username or email" onkeypress="if(event.keyCode==13) {btnloginclick();}">
						</div>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="loginPwd"
								type="password" class="form-control" name="loginPwd"
								placeholder="password" onkeypress="if(event.keyCode==13) {btnloginclick();}">
						</div>
						<div style="margin-top: 10px;" class="form-group">
							<div class="col-sm-12 controls">
								<a id="btnLogin1" class="btn btn-success" onclick="btnloginclick()">Login </a>
							</div>
						</div>
					</form>
					<div class="form-group">
						<div class="col-md-14 control">
							<div style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
								계정이 없으신가요?
								<a data-toggle="modal" data-target="#myModal">회원가입 </a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>