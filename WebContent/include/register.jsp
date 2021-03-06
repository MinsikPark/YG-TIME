<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<form id="joinForm" method="post">
					<div class="form-group">
						<label for="email">이메일 주소</label>
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
							class="form-control" id="nickName" name="nickName" onkeypress="if(event.keyCode==13) {joinsubmit();}">
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
			<div class="modal-header text-center">
				<button type="button" class="close" data-dismiss="modal"
					onclick="modclear()">&times;</button>
				<h3 class="modal-title"><b>회원정보 수정</b></h3>
			</div>


			<div class="modal-body">
				<form id="modprofile">
					<h4>프로필 수정</h4>
					<br>
					<div class="form-group container text-center">
						<img class="img-circle center-block"  id="currentProfile" style=" width: 150px; height: 150px;" onclick="modupload()"/> 
						<input type="file" id="modfileUpLoad" name="modfileUpLoad" onchange="profileimgmodify()">
					</div>
				</form>
				<div class="panel-group">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title text-center">
								<a data-toggle="collapse" href="#collapse1">기본 정보 수정</a>
							</h4>
						</div>
						<div id="collapse1" class="panel-collapse collapse">
							<div class="panel-body">
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

									<div class="modal-footer">
										<button type="button" class="btn btn-default" onclick="withDrawal()">회원탈퇴</button>
										<button type="button" class="btn btn-default"
											onclick="modsubmit()">Submit</button>
										<button type="button" class="btn btn-default"
											data-dismiss="modal" id="modclose" onclick="modclear()">Close</button>
									</div>
									<div class="panel-footer"></div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- MODAL END -->
