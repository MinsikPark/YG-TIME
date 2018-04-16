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
<script src="js/headerJoin.js"></script>
<title>Insert title here</title>
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
		<div style="margin-top: 50px; top: 200px;"
			class="col-md-6 col-md-offset-3">
				YG TIME
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>

				<div class="panel-body">

					<form id="loginform" class="form-horizontal" role="form">

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="login-username"
								type="text" class="form-control" name="username" value=""
								placeholder="username or email">
						</div>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
								type="password" class="form-control" name="password"
								placeholder="password">
						</div>
						<div style="margin-top: 10px;" class="form-group">
							<div class="col-sm-12 controls">
								<a id="btn-login" href="#" class="btn btn-success">Login </a>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12 control">
								<div style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									Don't have an account! 
									<a data-toggle="modal" data-target="#myModal">Sign Up Here </a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>