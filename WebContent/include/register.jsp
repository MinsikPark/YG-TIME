<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">회원가입</h4>
			</div>
			<div class="modal-body">
				<form action="#" method="post">
					<div class="form-group">
						<label for="email">이메일 주소</label> <input type="email" class="form-control" id="email" placeholder="이메일을 입력하세요">
					</div>
					<div class="form-group">
						<label for="password">비밀 번호</label> <input type="password" class="form-control" id="password" placeholder="비밀번호를 입력하세요">
					</div>
					<div class="form-group">
						<label for="passwordCheck">비밀 번호 확인</label> <input type="password" class="form-control" id="passwordCheck" placeholder="비밀번호를 입력하세요">
					</div>
					<div class="form-group">
						<label for="nickName">닉네임</label> <input type="text" class="form-control" id="nickName" placeholder="닉네임을 입력하세요">
					</div>
					<div class="form-group">
						<label for="fileUpLoad">프로필 사진 설정</label> <input type="file" id="fileUpLoad">
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