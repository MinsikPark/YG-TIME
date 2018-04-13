<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="js/headerLogin.js"></script>
<script src="js/headerJoin.js"></script>
<script src="js/headerMod.js"></script>
<script src="js/headerMsg.js"></script>
<script src="js/sidebarProject.js"></script>
<script src="js/sidebarProjectList.js"></script>

<c:if test="${!empty sessionScope.sessionId}">
	<div id="mySidenav" class="sidenav">
		<div id="sideNav" class="sidenav">
			<div class="tab-content">
				<a href="#" class="glyphicon glyphicon-plus insert"
					onclick="addProjectForm()"></a>
				<ul class="nav nav-tabs nav-tabs-modify">
					<li class="active"><a data-toggle="tab" href="#progress">진행중인
							프로젝트</a></li>
					<li><a data-toggle="tab" href="#complete">완료된 &nbsp; 프로젝트</a></li>
				</ul>

				<div id="progress" class="tab-pane fade in active">
					<!-- 진행중인 프로젝트 -->

				</div>
				<div id="complete" class="tab-pane fade">
					<!-- 완료 -->
					
				</div>
			</div>
		</div>
	</div>
</c:if>
