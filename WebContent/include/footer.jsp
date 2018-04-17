<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav id="mainFooterbar" class="navbar navbar-default navbar-fixed-bottom">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">진행중인 MEMBER &nbsp;&nbsp;&nbsp;</a>
    </div>
    <input type="hidden" id="thisMemberGrade">
    <div id="thisProjctMeber">
		<div class="dropdown" style="float:left;">
		</div>
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
						<input type="hidden" id="thisProjectNum">
						<button class="btn btn-default" type="button"  onclick="memberinvite()">
							<span class="glyphicon glyphicon-search" ></span>
						</button>
					</span>
				</div>
			</li>
		</ul>
	</div>
</nav>
<!-- FOOTER END -->