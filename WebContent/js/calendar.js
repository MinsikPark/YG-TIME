/**
	파일명: calendar.js
	설명: 달력 페이지 JavaScrip 작성
	작성일: 2018-04-12
	작성자: 강성훈
*/

$(function() { // $(document).ready
	var boardCnt = 0;

	function dateProcess(dateStr, days){ // date가공 메서드
		var date = new Date(dateStr); // Date 객체 생성
		date.setDate(date.getDate() + days); // Date 가감설정
		var dateToISO = date.toISOString().substring(0,10); // Date -> String (ISO날짜 타입, 시간제거)
		return dateToISO;
	}

	// fullCalendar
	$('#calendar').fullCalendar({
		eventDragStop: function (event, jsEvent) { // Drag 후 삭제 기능
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
		dragRevertDuration : 0, // drag 속도
		locale: 'ko',
		selectable: true, // 일자를 클릭, 드래그 가능
		selectHelper: true, // 일자 드래그하면 보드바를 표시
		select: function(start, end) { // 이벤트객체에 입력될 시작일, 종료일 파라미터
			var endDate = dateProcess(end.format(), -1);
			console.log("달력에 표시되는 종료일: " + endDate); // 확인용 콘솔 코드
			
			$('#eventStart').val(start.format()); // start.format() : 시작일 값
			$('#eventEnd').val(endDate); // 종료일 값
			$('#eventStart, #eventEnd').datepicker({dateFormat: 'yy-mm-dd'}); // 시작일, 종료일 Datepicker
			$('#calEventDialog').dialog('open');
		},
		editable: true, // 달력에 종료일 수정 여부 (드래그, 크기 조정)
		eventLimit: true, // 하루에 표시되는 이벤트의 수를 제한. 나머지는 팝 오버에 나타남.
		displayEventTime: false, // 
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
			/*참고 코드
			var eventObject = {
                    type: 2,
                    id: $(this).attr("data-id"),
                    title: $(this).attr("data-name"),
                    duration: $(this).attr("data-duration"),
                    guid: $(this).attr("data-guid"),
                    color: $(this).attr("data-color")
                };
			*/
		],
		eventDrop: function(event, delta, revertFunc) { // Drag를 통한 날짜 변경 처리 함수
			// 데이터 확인용 콘솔 코드
			console.log("<< eventDrop >>");
			eventDataPrint(event);
			
			/*
			if(!confirm("날짜를 변경하시겠습니까?")) {
				revertFunc(); //날짜 변경 전의 값으로 되돌림
			}
			*/
		},
		eventResize: function(event, delta, revertFunc) { // Editable을 통한 날짜 변경 처리 함수
			// 데이터 확인용 콘솔 코드
			console.log("<< eventResize >>");
			eventDataPrint(event);
			
			/*
			if(!confirm("날짜를 변경하시겠습니까?")) {
				revertFunc(); //날짜 변경 전의 값으로 되돌림
			}
			*/
		},
	}); // end - fullCalendar
	
	// 보드 데이터 콘솔 확인 (추후 지울것)
	function eventDataPrint(event) {
		console.log("event.id: " + event.id);
		console.log("event.title: " + event.title);
		console.log("event.start: " + event.start.format());
		console.log("event.end: " + event.end.format());
		console.log("event.color: " + event.color);
	}
	
	// dialog 초기화
	function clearDialog() {
		$("#eventTitle").val("");
		$("#eventStart").val("");
		$("#eventEnd").val("");
		$("#eventColor").val("#000000");
	}			
	
	// dialog 설정
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
			// '추가'버튼 클릭 시
			추가: function() {
				var title = $('#eventTitle').val(); // 제목 
				var start = $('#eventStart').val(); // 시작일
				var end = dateProcess($('#eventEnd').val(), 1); // 가공된 종료일 값
				var color = $('#eventColor').val();
				var eventData = null; // 이벤트 객체 변수 선언
				console.log("datepicker로 받은 endDate: " + $('#eventEnd').val());
				console.log("실제 endDate: " + end);
				
				if(title === "" ){
					alert("보드명을 입력해주세요.");
					return;
				}else {
						eventData = { // 이벤트 객체
							id: boardCnt++, //id가 동일하면 drag, editable이 동일하게 동작
							title: title,
							start: start,
							end: end,
							color: color,
						};
						$('#calendar').fullCalendar('renderEvent', eventData, true);
						// 데이터 확인용 콘솔 코드
						console.log("<< createEvent >>");
						console.log("eventData.id: " + eventData.id);
						console.log("eventData.title: " + eventData.title);
						console.log("eventData.start: " + eventData.start);
						console.log("eventData.end: " + eventData.end);
						console.log("eventData.color: " + eventData.color);
					}
					$('#calendar').fullCalendar('unselect');
					$(this).dialog('close');
					clearDialog(); // dialog 초기화
				},
			// '취소'버튼 클릭 시
			취소: function() {
				$(this).dialog('close');
				clearDialog();
			}
		},
		close: function() { // 'x'버튼이나 esc를 눌러 dialog 창을 닫을 경우
			clearDialog();
		},				
	}); // end - calEventDialog
	
	//샘플 데이터
	var sampleData = [
			{ // 이벤트 객체
				//id: "sample1", //프로젝트넘버
				title: "안녕", //프로젝트명
				start: "2018-04-01", //프로젝트시작날짜
				end: "2018-04-02", //프로젝트종료날짜
				color: "#336699", //라벨색
				url: "http://www.daum.net/", //url
			},
			{
				id: "sample2",
				title: "뚱이",
				start: "2018-04-12",
				end: "2018-04-14",
				color: "#d25386",
				url: "http://www.naver.com/",
			},
			{
				id: "sample3",
				title: "스폰지밥",
				start: "2018-04-20",
				end: "2018-04-30",
				color: "yellow",
				url: "http://www.google.com/",
			},
	];
	console.log("sampleData: " + sampleData);
	console.log("sampleData Length: " + sampleData.length);
	console.log("sample1: " + sampleData[0].id);
	
	//프로젝트 추가/변경/삭제 처리
	$('#project1').click(function() {
		$('#calendar').fullCalendar('removeEvents');
	});
	
	$('#project2').click(function() {
		for(var i in sampleData) {
			console.log("i: " + i);
			console.log("sampleData[i].id: " + sampleData[i].id);
			$('#calendar').fullCalendar('renderEvent', sampleData[i], true);	
		}
		
	});
	
	$('#project3').click(function() {
		$('#calendar').fullCalendar('removeEvents');
		for(var i in sampleData) {
			console.log("i: " + i);
			console.log("sampleData[i].id: " + sampleData[i].id);
			$('#calendar').fullCalendar('renderEvent', sampleData[i], true);	
		}
	});
	
}); // end - $(document).ready