<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>YG-TIME Calendar</title>
	<!-- 모바일 반응형을 위한 viewport 설정 -->
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/fullcalendar.css" />
	<link rel='stylesheet' href="css/fullcalendar.print.min.css" media="print" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/validate.js/0.12.0/validate.min.js"></script>
	<script src="js/moment.min.js"></script>
	<!-- (주의)fullcalendar.js import가 가장 마지막 라인에 작성되어야 함 -->
	<script src="js/fullcalendar.js"></script>
	<script src="js/locale-all.js"></script>
	<!-- script -->
	<script type="text/javascript">
	
		$(function() { // $(document).ready.
		
			function dateProcess(dateStr, days){ //date가공 메서드
				var date = new Date(dateStr); //Date 객체 생성
				date.setDate(date.getDate() + days); //Date 가감설정
				var dateToISO = date.toISOString().substring(0,10); //Date -> String (ISO날짜 타입, 시간제거)
				return dateToISO;
			}
		
			//fullCalendar
			$('#calendar').fullCalendar({
				eventDragStop: function (event, jsEvent) {
				    var trashEl = $('#trashCan');
				    var ofs = trashEl.offset();

				    var x1 = ofs.left;
				    var x2 = ofs.left + trashEl.outerWidth(true);
				    var y1 = ofs.top;
				    var y2 = ofs.top + trashEl.outerHeight(true);

				    if (jsEvent.pageX >= x1 && jsEvent.pageX <= x2 &&
				        jsEvent.pageY >= y1 && jsEvent.pageY <= y2) {
				    	
				    	var answer = confirm("정말 삭제 하시겠습니까?");
				    	if (answer) {
				    		$('#calendar').fullCalendar('removeEvents', event._id);
				    	}
				    }
				},
				dragRevertDuration : 0, //drag 속도
				locale: 'ko',
				selectable: true, //일자를 클릭, 드래그 가능
				selectHelper: true, //일자 드래그하면 보드바를 표시
				select: function(start, end) { //이벤트객체에 입력될 시작일, 종료일 파라미터
					var endDate = dateProcess(end.format(), -1);
					console.log("달력에 표시되는 종료일: " + endDate); //확인용 콘솔 코드
					
					$('#eventStart').val(start.format()); // start.format() : 시작일 값
					$('#eventEnd').val(endDate); // 종료일 값
					$('#eventStart, #eventEnd').datepicker({dateFormat: 'yy-mm-dd'}); //시작일, 종료일 Datepicker
					$('#calEventDialog').dialog('open');
				},
				editable: true, // 달력에 종료일 수정 여부 (드래그, 크기 조정)
				eventLimit: true, // 하루에 표시되는 이벤트의 수를 제한. 나머지는 팝 오버에 나타남.
				displayEventTime: false,
				events: [
					{
						id: 'jino',
						title: '지너니',
						start: '2018-04-07',
						end: '2018-04-10',
						color: '#000000'
					},
					{
						title: '재우기',
						url: 'http://www.daum.net/',
						start: '2018-04-15',
						end: '2018-04-17',
						color: '#336699'
					},
					{
						title: '윤그니',
						url: 'http://www.naver.com/',
						start: '2018-04-20',
						end: '2018-04-22',
						color: 'red'
					}
				],
			}); // end - fullCalendar
			
			//dialog 초기화
			function clearDialog() {
				$("#eventTitle").val("");
				$("#eventStart").val("");
				$("#eventEnd").val("");
				$("#eventColor").val("#000000");
			}			
			
			//dialog 설정
			$('#calEventDialog').dialog({
				resizable: false,
				autoOpen: false,
				width: 'auto',
				maxWidth: 350,
				height: 'auto',
				//responsive: true,
				modal: true,
				fluid: true,
				title: '보드 추가',
				buttons: {
					추가: function() {
						var title = $('#eventTitle').val(); //제목 
						var start = $('#eventStart').val(); //시작일
						var end = dateProcess($('#eventEnd').val(), 1);
						var color = $('#eventColor').val();
						var eventData = null; //이벤트 객체 변수 선언
						console.log("datepicker로 받은 endDate: " + $('#eventEnd').val());
						console.log("실제 endDate: " + end);
						
						if(title === "" ){
							alert("보드명을 입력해주세요.");
							return;
						}else {
								eventData = {
									title: title,
									start: start,
									end: end,
									color: color,
								};
								$('#calendar').fullCalendar('renderEvent', eventData, true);
							}
							$('#calendar').fullCalendar('unselect');
							$(this).dialog('close');
							clearDialog(); //dialog 초기화
						},
					취소: function() {
						$(this).dialog('close');
						clearDialog(); //dialog 초기화
					}
				},
			}); // end - calEventDialog
			
		}); // end - $(document).ready
	</script>
	<!-- style -->
	<style type="text/css">
		#calendar {
		    max-width: 900px;
		    margin: 0 auto;
		}
	</style>
</head>
<body>
	<!-- 달력 표시되는 영역 -->
	<div id="calendar">
		<br>
		<img id="trashCan" src="images/cal-trash.png"> <!-- 쓰레기통 이미지 -->
	</div>
	
	<!-- Dialog 영역 -->
	<!-- 초기에 깜빡이는 것을 없애기 위해 <div hidden ...> 사용 -->
	<div hidden id="calEventDialog">
	    <form>
	        <fieldset>
		        <label for="eventTitle">보드명</label>
		        <input type="text" name="eventTitle" id="eventTitle" /><br>
		        <label for="eventStart">시작일</label>
		        <input type="text" name="eventStart" id="eventStart" /><br>
		        <label for="eventEnd">종료일</label>
		        <input type="text" name="eventEnd" id="eventEnd" /><br>
		        <label for="eventColor">보드바 색상</label>
		        <input type="color" name="eventColor" id="eventColor" /><br>
	        </fieldset>
	    </form>
	</div>
	
</body>
</html>