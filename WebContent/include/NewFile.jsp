<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="addMember" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" onclick="chartClose()">&times;</button>
				<h4 class="modal-title">멤버추가</h4>
			</div>

			<div class="modal-body">
				<form id="addMemberForm">
					<input id="emailSearch" onclick="autoComplet()">
					
					<div class="modal-footer">
						
					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="chartClose()">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>