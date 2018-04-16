<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="chartModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" onclick="chartClose()">&times;</button>
				<h4 class="modal-title">차트</h4>
			</div>

			<div class="modal-body">
				<form id="chartprojectForm">
					<input class ='btn btn-primary' type = "button"  id="startchart" onclick = "projectChartView()" value = "진행률">
					<input class ='btn btn-danger' type = "button"  id="listchart" onclick ="listChartView()" value = "리스트/카드">
					<canvas id="myChart"></canvas>

					<div class="modal-footer">
						
					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="chartClose()">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>