<%--
	파일명: wifi.jsp
	설명: [Open API]서울시 공공와이파이 위치정보
	작성일: 2018-04-16
	작성자: 강성훈
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade" id="modalWifiMap" role="dialog">
		<!-- Modal content-->
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Wifi API</h4>
			</div>
			<div class="modal-body container">
				<div id="map" style="margin: 0 auto; width: 800px; height: 500px;"></div>
			</div>
		</div>
	</div>
</div>

<script src="js/wifi.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyANFCM82CSQsLcitkNXAadHB8bmsh6hrH8&callback=initMap" async defer></script>    
