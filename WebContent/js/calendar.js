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
			
			$('#eventStart').val(start.format()); // start.format() : 시작일 값
			$('#eventEnd').val(endDate); // 종료일 값
			$('#eventStart, #eventEnd').datepicker({dateFormat: 'yy-mm-dd'}); // 시작일, 종료일 Datepicker
			$('#calEventDialog').dialog('open');
		},
		editable: true, // 달력에 종료일 수정 여부 (드래그, 크기 조정)
		eventLimit: true, // 하루에 표시되는 이벤트의 수를 제한. 나머지는 팝 오버에 나타남.
		displayEventTime: false, // 
		eventClick: function(event) {
			$.ajax({
				url:"Listlist.list",
	            datatype:"JSON",
	            data:{boardnum:event.id},
	            success:function(data){
	                var json = JSON.parse(data);
	                console.log(json);
	                $('#calendar').hide();
	                
	                $('#content-md .listbox').remove();
	                var content =""
	                $.each(json, function(index, json) {
	                	content += '<div class="listbox">'
	                		+ '<div class="listtitle">'+ json.listName +'</div>'
	                		+ '<a class="cardcreate" onclick="addCardView(this)">Add a card...</a>'
	                		+ '</div>';
	                });
	                content +='<a class="listbox" onclick="addListView(this)">Add a list...</a>'
	                $('#content-md').prepend(content);
	                $('#mainScreen').show();
	                autoWidth();
	            }
			});
		},
		eventDrop: function(event, delta, revertFunc) { // Drag를 통한 날짜 변경 처리 함수
		
		},
		eventResize: function(event, delta, revertFunc) { // Editable을 통한 날짜 변경 처리 함수
			
		},
	}); // end - fullCalendar
	
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
				
				if(title === "" ){
					alert("보드명을 입력해주세요.");
					return;
				}else {
						eventData = { // 이벤트 객체
							title: title,
							start: start,
							end: end,
							color: color,
						};	
						var inputParam = {
								boardTitle: eventData.title,
								boardStartDate: eventData.start,
								boardEndDate: eventData.end,
								label: eventData.color,

						};
						//////
						$.ajax({
							url:"boardadd.board",
							datatype:"text",
							data: inputParam,
							success: function(data){
								console.log("보드 생성 데이터 :" + data)
								if(data.trim()<=0 ||data==null){
									alert("보드 생성에 실패하셨습니다");
								}else{
									$('#calendar').fullCalendar('renderEvent', eventData, true);
									alert("보드 생성했음");	
								}
							}

						})
						////////
						
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
	
}); // end - $(document).ready

var sampleData = [
	{ // 이벤트 객체
		//id: "sample1", //프로젝트넘버
		title: "안녕", //프로젝트명
		start: "2018-04-01", //프로젝트시작날짜
		end: "2018-04-02", //프로젝트종료날짜
		color: "#336699" //라벨색
	},
	{
		id: "sample2",
		title: "뚱이",
		start: "2018-04-12",
		end: "2018-04-14",
		color: "#d25386"
	},
	{
		id: "sample3",
		title: "스폰지밥",
		start: "2018-04-20",
		end: "2018-04-30",
		color: "yellow"
	},
];

/*function project() {
	$('#calendar').fullCalendar('removeEvents');
	for(var i in sampleData) {
		$('#calendar').fullCalendar('renderEvent', sampleData[i], true);	
	}
	$('#mainScreen').hide()
	$('#calendar').show()
	
}*/
