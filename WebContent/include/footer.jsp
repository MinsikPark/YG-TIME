<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">진행중인 MEMBER &nbsp;&nbsp;&nbsp;</a>
    </div>
    <div class="dropup">
		<a class = "glyphicon glyphicon-user" onclick="memberDel()" style="font-size: 25pt; top: 7px;"></a>
		<button type="button" class="btn btn-default" data-toggle="dropdown" style="margin: 0px 0px 10px 30px;" onclick="bokyeong(this)">
			<span class="glyphicon glyphicon-plus"></span> 멤버 추가
		</button>
		<ul class="dropdown-menu" style="width: 300px; margin-left:100px;" id="addMemberSearch">
			<li><div class="input-group">
					<div class="form-group">
			   			<input type="email" class="form-control" id="emailSearch" placeholder="이메일 입력..">
					</div>
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</span>
				</div>
			</li>
		</ul>
	</div>
</nav>