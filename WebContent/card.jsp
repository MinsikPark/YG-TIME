<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<style type="text/css">
		input[type=checkbox]	{
			float:left;
			margin:10px;
		}
		input[type=text] {
			width: 340px;
		}
	</style>
</head>
<body>

	<div class="container">
		<h2>카드상세페이지</h2>
		<!-- Button to Open the Modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#myModal">Card</button>

		<!-- The Modal -->
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">카드 제목</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						<div class="container">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-sm-4" for="email">상세내용</label>
									<div class="col-sm-7">
										<input type="email" class="form-control" id="email"
											placeholder="상세내용을 입력해주세요." style="height: 100px">
											 <div class="fileupload">
           										file1 : <input type="file" name="filename1"><br>
       										 </div>
									</div>
								</div>
								<div class="form-group">
									체크리스트:<br> <input type="checkbox" id="myCheck" > <input
										type="text" class="form-control" id="pwd"
										placeholder="내용을 입력해주세요">
										<input type="checkbox" id="myCheck"> <input
										type="text" class="form-control" id="pwd"
										placeholder="내용을 입력해주세요">
										<input type="checkbox" id="myCheck"> <input
										type="text" class="form-control" id="pwd"
										placeholder="내용을 입력해주세요">
								</div>

								<!-- Modal footer -->
								<div class="modal-footer">
									<button type="submit" class="btn btn-default">등록</button>
									<button type="submit" class="btn btn-default">취소</button>
								</div>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
