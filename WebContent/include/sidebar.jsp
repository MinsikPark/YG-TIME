<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="mySidenav" class="sidenav">
	<div id="sideNav" class="sidenav">
		<a href="#" class="glyphicon glyphicon-plus insert" onclick="addProjectForm()"></a>
		<div class="tab-content">
			<ul class="nav nav-tabs nav-tabs-modify">
				<li class="active"><a data-toggle="tab" href="#progress">진행중인 프로젝트</a></li>
				<li><a data-toggle="tab" href="#complete">완료된 &nbsp;   프로젝트</a></li>
			</ul>
			<div id="progress" class="tab-pane fade in active">
				<div>
					<button class="button btn-1" onclick="project()" value="1">Button 1</button>
					
					<a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a>
    				<ul class="dropdown-menu" style= "float: right; position: unset;">
      					<li><a onclick="projectDel(this)">프로젝트 삭제</a></li>
      					<li><a onclick="projectComplete(this)">프로젝트 완료</a></li>
    				</ul>	
				</div>
				<div>
					<button class="button btn-1">Button 2</button>
					<a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a>
    				<ul class="dropdown-menu" style= "float: right; position: unset;">
      					<li><a onclick="projectDel(this)">프로젝트 삭제</a></li>
      					<li><a onclick="projectComplete(this)">프로젝트 완료</a></li>
    				</ul>	
				</div>
			</div>
			<div id="complete" class="tab-pane fade">
				<div>
					<button class="button btn-1">Button 3</button>
					<a class="glyphicon glyphicon-cog setting"  data-toggle="dropdown"></a>
    				<ul class="dropdown-menu" style= "float: right; position: unset;">
      					<li><a onclick="projectView(this)">프로젝트 보기</a></li>
      					<li><a onclick="projectProgress(this)">프로젝트 다시 진행</a></li>
    				</ul>	
				</div>
				<div class="dropdown">
					<button class="button btn-1">Button 4</button>
					<a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a>
    				<ul class="dropdown-menu" style= "float: right; position: unset;">
      					<li><a onclick="projectView(this)">프로젝트 보기</a></li>
      					<li><a onclick="projectProgress(this)">프로젝트 다시 진행</a></li>
    				</ul>	
				</div>
			</div>
		</div>
	</div>
</div>
    