/**
	파일명: calendar.js
	설명: 달력 페이지 JavaScrip 작성
	작성일: 2018-04-12
	작성자: 강성훈
*/

$(function() { // $(document).ready
	var boardCnt = 0;
/*
	function dateProcess(dateStr, days){ // date가공 메서드
		var date = new Date(dateStr); // Date 객체 생성
		date.setDate(date.getDate() + days); // Date 가감설정
		var dateToISO = date.toISOString().substring(0,10); // Date -> String (ISO날짜 타입, 시간제거)
		return dateToISO;
	}
*/
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
		    		var data = {boardNum:event.id};
		    		
		    		$.ajax({
		    			url:"boarddelete.board",
		    			datatype:"text",
		    			data:data,
		    			success:function(data){
		    				console.log(">"+data.trim()+"<");
		    			}
		    		
		    		})
		    		
		    		$('#calendar').fullCalendar('removeEvents', event._id);
		    	}
		    }
		},
		dragRevertDuration : 0, // drag 속도
		locale: 'ko',
		selectable: true, // 일자를 클릭, 드래그 가능
		selectHelper: true, // 일자 드래그하면 보드바를 표시
		select: function(start, end) { // 이벤트객체에 입력될 시작일, 종료일 파라미터
			var endDate = dateProcess(end.format(), -1); //추가 Dialog에서 종료값 -1일
			
			$('#eventStart').val(start.format()); // start.format() : 시작일 값
			$('#eventEnd').val(endDate); // 종료일 값
			$('#eventStart, #eventEnd').datepicker({dateFormat: 'yy-mm-dd'}); // 시작일, 종료일 Datepicker
			$('#calEventDialog').dialog('open');
		},
		editable: true, // 달력에 종료일 수정 여부 (드래그, 크기 조정)
		eventLimit: true, // 하루에 표시되는 이벤트의 수를 제한. 나머지는 팝 오버에 나타남.
		displayEventTime: false, // 
		eventClick: function(event) {			
			boardclick(event.id);
		},
		eventDrop: function(event, delta, revertFunc) { // Drag를 통한 날짜 변경 처리 함수
			boarddateupdate(event);
		},
		eventResize: function(event, delta, revertFunc) { // Editable을 통한 날짜 변경 처리 함수
			boarddateupdate(event);
		},
	}); // end - fullCalendar
	
	//board 날짜 변경 비동기 함수 (입력)
	function boarddateupdate(event){
		console.log("event.end.format() : " + event.end.format());
		var data = {
				boardNum:event.id,
				boardStartDate:event.start.format(),
				boardEndDate:dateProcess(event.end.format(), -1),
				   };
		
		$.ajax({
			url:"boarddatemodify.board",
			datatype:"text",
			data:data,
			success:function(data){
				console.log(">"+data.trim()+"<")
				if(data.trim() <= 0){
					alert("날짜 변경 실패");
				}
			}
		});
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
			// '추가'버튼 클릭 시 (입력)
			추가: function() {
				var title = $('#eventTitle').val(); // 제목 
				var start = $('#eventStart').val(); // 시작일
				//var end = dateProcess($('#eventEnd').val(), 1); // 가공된 종료일 값
				var end = $('#eventEnd').val();
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
									eventData.end = dateProcess(eventData.end, 1); //출력시 DB종료일 + 1일
									$('#calendar').fullCalendar('renderEvent', eventData, true); //출력
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

//보드를 클릭하면 카드와 리스트를 가져온다 
function boardclick(boardNum){
	$.ajax({
		url:"Listlist.list",
        datatype:"JSON",
        data:{boardnum:boardNum},
        success:function(data){
        	
            var json = JSON.parse(data);
            
            $('#calendar').hide();
            
            $('#content-md .listbox').remove();
            var content =""
            $.each(json, function(index, elt) {
            	console.log(elt);
            	content += '<div class="listbox">'
            		+ '<div id="listnum'+elt.listNum+'"class="listtitle"><label onclick="listmodify(this, '+elt.listNum+',' + boardNum +')">'+ elt.listName +'</label></div>'
            		+ '<a class="cardcreate" onclick="addCardView(this)">Add a card...</a>'
            	+ '</div>';
            });
            content +='<a class="listbox" onclick="addListView(this, '+ boardNum +')">Add a list...</a>'
            $('#content-md').prepend(content);
            $.each(json, function(index, elt) {
            	$.ajax({
            		url:"Cardlist.card",
            		datatype:"JSON",
            		data:{listnum:elt.listNum},
            		success:function(carddata){
            			var cardjson = JSON.parse(carddata);
            			var cardcontent = "";
            			$.each(cardjson, function(indexcard, eltcard) {
            				cardcontent += '<div class="card ui-sortable-handle" data-toggle="modal" data-target="#myModal1" style="">'+eltcard.cardName+'</div>';
            			});
            			$("#listnum"+elt.listNum+" ").append(cardcontent);
            		}
            	})
            });
            $('#mainScreen').show();
            autoWidth();
        }
	});
}


/*function project() {
	$('#calendar').fullCalendar('removeEvents');
	for(var i in sampleData) {
		$('#calendar').fullCalendar('renderEvent', sampleData[i], true);	
	}
	$('#mainScreen').hide()
	$('#calendar').show()
	
}*/
