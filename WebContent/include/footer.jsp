<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <nav class="navbar navbar-default navbar-fixed-bottom">
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
</nav> -->

<!-- FOOTER -->
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">진행중인 MEMBER &nbsp;&nbsp;&nbsp;</a>
    </div>
	<div class="dropdown" style="float:left;">
		<a class = "glyphicon glyphicon-user" data-toggle="dropdown" style="font-size: 25pt; top: 7px;"></a>
		<ul class="dropdown-menu">
			<li><a onclick="memberDel()">맴버제명</a></li>
			<li><a>맴버위임</a></li>
			<li><a>맴버탈퇴</a></li>
		</ul>
	</div>
    <div class="dropup"  style="float:left; margin-top:10px;">
		<button type="button" id = "plus" class="btn btn-default"  data-toggle="dropdown" style="margin: 0px 0px 10px 30px;">
			<span class="glyphicon glyphicon-plus" ></span> 멤버 추가
		</button>
		<ul class="dropdown-menu" style="width: 300px;">
			<li><div class="input-group">
					<div class="form-group">
			   			<input type="email" class="form-control" id="emailSearch" value="userId" placeholder="이메일 입력..">
					</div>
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">
							<span class="glyphicon glyphicon-search"  onclick="memberinvite(<%=request.getSession().getAttribute("projectNum")%>)"></span>
						</button>
					</span>
				</div>
			</li>
		</ul>
	</div>
</nav>
<!-- FOOTER END -->