<%--
	파일명: wifi.jsp
	설명: [Open API]서울시 공공와이파이 위치정보
	작성일: 2018-04-16
	작성자: 강성훈
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    
	<script type="text/javascript">
		function initMap(){
			// 맵 초기 좌표 설정
			var map = new google.maps.Map(document.getElementById('map'), {
				center: {
					lat: 37.494,
					lng: 127.027
				},
				zoom: 14,
				disableDefaultUI : true
			});
			// 맵 초기 마커
			var markerInit = new google.maps.Marker({map: map});
			
			// 현재 위치 좌표 설정
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					var pos = {
						lat: position.coords.latitude,
						lng: position.coords.longitude
					};
					
					var iconInit = "http://maps.google.com/mapfiles/kml/paddle/red-circle.png";
					markerInit.setIcon(iconInit);
					markerInit.setPosition(pos);
					map.setCenter(pos);
				}, function() {
					handleLocationError(true, infoWindow, map.getCenter());
				});
			} else {
				// 브라우저가 Geolocation 기능을 지원하지 않을 때
				handleLocationError(false, infoWindow, map.getCenter());
			}
			
			processApi(map);
		} // end - initMap()
		
		// 맵에 API 좌표 아이콘 출력
		function processApi(map) {
			var apiUrl = "http://openapi.seoul.go.kr:8088/61684e6a7573756e38316e6f504b69/json/PublicWiFiPlaceInfo/1/1000";
    		$.ajax({
                url : apiUrl,
                dataType:"json",
                success:function(data){
                	var row = data.PublicWiFiPlaceInfo.row;
					$.each(row, function(index, obj) {
						var marker = new google.maps.Marker({
							map: map,
							icon: "images/wifi.png",
							position: {
								lat: Number(obj.INSTL_Y),
								lng: Number(obj.INSTL_X)
							}
						});
						
						var infowindow = new google.maps.InfoWindow({
							content: obj.PLACE_NAME
						});
						
						marker.addListener('click', function() {
							infowindow.open(map, marker);
						});
					});
                }
		    });
    		
		} // end - processApi()
		
		// handleLocationError
		function handleLocationError(browserHasGeolocation, infoWindow, pos) {
			infoWindow.setPosition(pos);
			infoWindow.setContent(browserHasGeolocation ?
				'에러: 구글맵 서비스에 실패했습니다.' :
				'에러: 웹 브라우저가 geolocation 기능을 지원하지 않습니다.');
		} // end - handleLocationError()
		
		/* $('#wifiLogo').click(function(){
			$('#modalWifiMap').on('shown.bs.modal', function () {
				var currentCenter = map.getCenter();
				console.log("지금센터: " + currentCenter);
				google.maps.event.trigger(map, "resize");
				map.setCenter(currentCenter);
			});
		}); */
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyANFCM82CSQsLcitkNXAadHB8bmsh6hrH8&callback=initMap"async defer></script>    
