<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<header>
	<nav class="navbar z-index">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"><img src="<%=request.getContextPath()%>/images/logo.png"
				style="width: 110px; height: 30px;" onclick="project()"></a>
			<div class="navbar-brand">
				<img src ="<%=request.getContextPath()%>/images/wifiLogo.png" style="height: 30px;" data-toggle="modal" data-target="#modalWifiMap">
			</div>
		</div>
		<div>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${sessionScope.sessionId eq null}">
						<script>
							location.href = 'login.jsp'
						</script>
					</c:when>
					<c:otherwise>
						<input id="getsession" type="hidden" value="${sessionScope.sessionId}">
						<li>
							<a href="<%=request.getContextPath()%>/logout.member">Logout 
								<span class="glyphicon glyphicon-log-out"></span>
							</a>
						</li>
				        	
						<li id="profiledrop" class="dropdown" >
							<a class="dropdown-toggle container" style="padding:0px" data-toggle="dropdown">
							</a> 
							<ul class="dropdown-menu">
								<li data-toggle="modal"  data-target="#myModal2">
							      <a id="profilmodify">회원정보 수정</a>
								</li>
							      <li>
							        <a class="message" tabindex="-1" href="#">초대 메시지함<span class="caret"></span></a>
							        <ul class="dropdown-menu">
							      
							          <li>  
							          	<div id="dropdownchilddiv"></div>
							          </li>
							        </ul>
							      </li>
							    </ul>
							</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
</header>
