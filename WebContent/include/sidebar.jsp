<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script src="js/headerLogin.js"></script>
<script src="js/headerJoin.js"></script>
<script src="js/headerMod.js"></script>
<script src="js/headerMsg.js"></script>
<script src="js/sidebarProject.js"></script>
<script src="js/sidebarProjectList.js"></script>
<script src="js/footbar.js"></script>

<c:if test="${!empty sessionScope.sessionId}">

	<div id="mySidenav" class="sidenav">
		<div id="sideNav" class="sidenav">
			<div class="tab-content">
				<a href="#" class="glyphicon glyphicon-plus insert"
					onclick="addProjectForm()"></a>
				<ul class="nav nav-tabs nav-tabs-modify">
					<li><img src="https://png.icons8.com/ios/50/ffffff/combo-chart-filled.png" data-toggle="modal" data-target="#chartModal" onclick ="chartClose()" style='width:35px; border:none;'></li>
					<li class="active"><a data-toggle="tab" href="#progress">진행</a></li>
					<li><a data-toggle="tab" href="#complete">완료</a></li> 
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
