<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal fade" id="myModal1" role="dialog">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 id="modalHeader" class="modal-title">Modal Header</h4>
			</div>
			<div class="modal-body container">
				<div class="flex2">
					<form action="#" method="post">
						
						<div class="form-group">
							<label for="content">상세 내용</label>
						</div>
						<textarea id="contentDetail" onkeyup="fnChkByte(this, 1000)"></textarea>
						<div class="form-group">
							<button id="detaiAddbtn" class="btn btn-success" type="button">작성</button>
						</div>
						<div class="form-group" id="fileUploadForm"></div>
						<div class="form-group">
							<label for="checklist">Check List</label>
							<div id="checkListForm"></div>
						</div>
						<div class="form-group">
							<label for="comment">댓글</label> <input type="text" class="form-control commentlist" placeholder="댓글을 입력하세요" onkeyup="fnChkByte(this, 80)">
							<button class="btn btn-success" type="button" onclick="addComment(this)">작성</button>
							<div id="commentListForm"></div>
						</div>
					</form>
				</div>
				<div class="flex1">
					<br> 

					<form id= "cardfileupload" method="post">
						<input id="hiddenCardnum" type="hidden" name="hiddenCardnum" >
						<input id="cardFileUpload" type="file" name="cardFileUpload" onchange="changeValue(this)">
						<input class="detailbutton btn btn-primary" type="button" value="파일 추가 하기" id="btnFileUpLoad" >			
					</form>
					<input class="detailbutton btn btn-primary" type="button" value="Check List" id="addCheckList" onclick="addCheckListForm()">
					<div class="dropdown">
						<input class="detailbutton btn btn-primary" type="button" value="Member" data-toggle="dropdown" onclick="cardMemberAddList(this)">
						<ul class="dropdown-menu" style="cursor: pointer;"></ul>
					</div>
					<div id="cardMemberView" style="margin-left: 10px;"></div>
				</div>
			</div>
		</div>
	</div>
</div>

