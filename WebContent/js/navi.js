var j = 1	
$(function(){
	$("#cardFileUpload").hide()
	$('.setting, .button, .tab-content, #mainFooterbar').hide()
	
	//사이드 관련
	$('#navigationBars, #mainScreen, #calendar, #movingBox').click(function() {
		sideHide()
	}) 
	
	$('#mySidenav').click(function() {
		sideShow()
	})
	//여기까지 사이드 관련
	$('#btnFileUpLoad').click(function() {
		$('#btnFileUpload').click()
		$('#cardFileUpload').click()
	})
	
	$('#modalHeader').click(function(){
		cardNameMod();
	})
	
	$('.dropdown a.message, .dropdown-menu .membersearch').on("click", function(e){
		$(this).next('ul').toggle();
		e.stopPropagation();
		e.preventDefault();
	});
	
})
function notHideAuto(e) {
	e.stopPropagation()
	
	
	//e.preventDefault();
}

function project(){
	$('#mainScreen').hide()
	$('#calendarArea').show()
}

//사이드 숨길때
function sideHide(){
	$('#sideNav').css("width", "0px")
	$('#navigationBars').css("marginLeft", "20px")
	$('#mainScreen').css("marginLeft", "70px")
	$('.setting, .insert, .button, .tab-content').hide()
}
//사이드 보일때
function sideShow(){
	$('#sideNav').css("width", "330px")
	$('#navigationBars').css("marginLeft", "330px")
	$('#mainScreen').css("marginLeft", "380px")
	$('.setting, .insert, .button, .tab-content').delay(250).fadeIn()
} 


//카드에 파일 업로드 하기 
function changeValue(obj){
	var data = $("#cardfileupload").serialize();

	if(obj.value != ""){
		var div = '<div><input type="text" class="form-control inputtextbox" value="첨부 파일  : '+ obj.value.substr(12)+'" readonly>'
			div += '<button type="button" class="close" onclick="fileInputDel(this)">&times;</button></div>'
		$('#fileUploadForm').append(div)
		
		$("#cardfileupload").ajaxForm({
			url: "cardfileupload.card",
			data: data,
			type : "post",
			datatype: "text",
			enctype:"multipart/form-data",
			success: function(data){
				if(data.trim()<=0){
					alert("파일 업로드 실패");
				}
				callUploadList($("#hiddenCardnum").val());
				
			}
		})
	}
	$("#cardfileupload").submit();
	obj.value =""; 
	
}

//상세페이지 카드명 클릭시 텍스트 생성
function cardNameMod(){
	var cardNum = $('#hiddenCardnum').val();
	var htmlObj = $('#modalHeader').html();
	
	var div = '<div onfocusout="cardViewContents('+ cardNum +')">'
		+ '<input type="text" class="form-control inputtextbox" placeholder="' + htmlObj + '" onkeyup="fnChkByte(this, 26)"'
		+ 'onkeypress="if(event.keyCode==13) {cardNameModOk();}" >';

	$('#modalHeader').html(div);
	$('#modalHeader').children('div').children('input').focus();
}

//상세페이지 카드명 수정 완료
function cardNameModOk(){
	var cardNum = $('#hiddenCardnum').val();
	var value = $('#modalHeader').children('div').children('input').val();
	if(value.trim() != ""){
		$.ajax({
			url:"CardNameUpdate.card",
			datatype:"text",
			data:{cardNum:cardNum, cardName:value.trim()},
			success:function(data){
				var boardNum = $('#hiddenBoardnum').val();
				boardclick(boardNum);
				cardViewContents(cardNum);
			}
		});
	}else{
		cardViewContents(cardNum);
	}
}

//Check List 버튼을 클릭시
function addCheckListForm(){
	var div = '<div id="addCheckListdiv"><input type="text" class="form-control inputtextbox" onkeyup="fnChkByte(this, 50)" onkeypress="if(event.keyCode==13) {addCheckList($(this).parent().children(\'button\'));}">'
		div += '<button type="button" class="close glyphicon" onclick="addCheckList(this)">&#xe013;</button></div>'
	
	$('#addCheckList').attr('onclick', 'addCancel()');
	$('label[for=checklist]').after(div);
}

//열려 있는 Check List 버튼 클릭시 or 생성완료 후
function addCancel(){
	$('#addCheckListdiv').remove();
	$('#addCheckList').attr('onclick', 'addCheckListForm()');
}

//체크리스트를 추가 했을 때
function addCheckList(obj) {
	var cardNum = $('#hiddenCardnum').val();
	var value = $(obj).closest('div')[0].children[0].value
	
	if(value.trim() != ""){
		$.ajax({
			url:"Checkinsert.card",
			datatype:"text",
			data:{cardNum:cardNum, checkBoxContents:value},
			success:function(data){
				cardViewCheckList(cardNum);
				addCancel();
			}
		});
	}
}

//체크를 클릭했을 때 바로 업데이트 하기
function checkClick(obj, checkNum){
	var checked = 0;
	var content = $(obj).parent().children('label').html();
	
	$(obj).change(function(){
        if($(obj).is(":checked")){
        	checked = 1;
        }else{
        	checked = 0;
        }
        checkUpdate(checked, content, checkNum);
    });
}

//체크박스 업데이트
function checkUpdate(checked, content, checkNum){
	var cardNum = $('#hiddenCardnum').val();
	$.ajax({
		url:"Checkupdate.card",
		datatype:"text",
		data:{checked:checked, checkBoxContents:content, cardNum:cardNum, checkNum:checkNum},
		success:function(data){
			cardViewCheckList(cardNum);
		}
	});
}

//체크박스를 삭제한다
function removeCheckList(obj, checkNum){
	var cardNum = $('#hiddenCardnum').val();
	$.ajax({
		url:"Checkdelete.card",
		datatype:"text",
		data:{cardNum:cardNum, checkNum:checkNum},
		success:function(data){
			cardViewCheckList(cardNum);
		}
	});
}

//체크박스를 텍스트를 생성하다
function checkBoxMod(obj, checkNum){
	var cardNum = $('#hiddenCardnum').val();
	var p = $(obj).closest('p');
	var text = p.children('label').html();
	
	var div = '<div onfocusout="focusoutdelay('+ cardNum +')">'
		+ '<input type="text" class="form-control inputtextbox" onkeyup="fnChkByte(this, 50)">'
		+ '<button type="button" class="close glyphicon" onclick="checkBoxModOk(this, '+checkNum+')">&#xe013;</button></div>';
	p.empty();
	p.html(div);
	p.children('div').children('input').focus();
}

//체크박스 내용 수정
function checkBoxModOk(obj, checkNum){
	var content = $(obj).parent().children('input').val();
	checkUpdate(0, content, checkNum);
}

//포커스 아웃 딜레이(카드)
function focusoutdelay(cardNum){
	setTimeout(function() {
		cardViewDetail(cardNum);
	}, 300);
}

//포커스 아웃 딜레이(보드 -> 리스트와 카드)
function focusOutBoardDelay(boardNum){
	setTimeout(function() {
		boardclick(boardNum);
	}, 300);
}

//포커스 아웃 딜레이(보드 -> 카드 불러오기)
function focusOutCardDelay(listNum){
	setTimeout(function() {
		callCardList(listNum);
	}, 300);
}

//댓글을 추가하다
function addComment(obj){
	//userid, cardnum, replycontents
	var id = $('#getsession').val();
	var cardNum = $('#hiddenCardnum').val();
	var value = $(obj).closest('div')[0].children[1].value
	
	if(value.trim() != ""){
		$.ajax({
			url:"ReplyAdd.card",
			datatype:"text",
			data:{userId:id, cardNum:cardNum, replyContents:value},
			success:function(data){
				cardViewReplys(cardNum);
				$(obj).parent().children('input').val("");
			}
		});
	}
}

//자신이 입력한 댓글 삭제
function removeComment(obj){
	var id = $('#getsession').val();
	var cardNum = $('#hiddenCardnum').val();
	var replyNum = $(obj).parent().attr("id").substr(8);
	
	$.ajax({
		url:"ReplyDel.card",
		datatype:"text",
		data:{userId:id, cardNum:cardNum, replyNum:replyNum},
		success:function(data){
			if(data.trim() == '0'){
				alert('댓글을 등록한 멤버가 아닙니다');
			}else{
				alert('삭제가 완료 되었습니다.');
				cardViewReplys(cardNum);
			}
		}
	});
}

//댓글을 수정하기전 작성한 인원이 맞는지 확인
function replyMod(obj, replyNum){
	var id = $('#getsession').val();
	var cardNum = $('#hiddenCardnum').val();
	
	$.ajax({
		url:"ReplySel.card",
		datatype:"json",
		data:{cardNum:cardNum, replyNum:replyNum},
		success:function(data){
			var json = JSON.parse(data);
			if(json.userId == id){
				$(obj).removeAttr('readonly');
				$(obj).attr('onfocusout', 'focusoutdelay('+ cardNum +')');
				$(obj).parent().children('button').attr({
					"class":"glyphicon close",
					"onclick":"replyModOk(this, "+replyNum+")"
				});
				$(obj).parent().children('button').html('&#xe065;');
				$(obj).focus();
			}else{
				alert('등록한 멤버가 아닙니다.');
			}
		}
	});
}

//댓글 수정완료
function replyModOk(obj, replyNum){
	var cardNum = $('#hiddenCardnum').val();
	var value = $(obj).parent().children('input').val();
	
	$.ajax({
		url:"ReplyUp.card",
		datatype:"text",
		data:{replyContents:value, cardNum:cardNum, replyNum:replyNum},
		success:function(data){	}
	});
}

//카드 상세페이지, 상세내용 추가
function updateDetail(obj, cardNum){
	var contentDetail = $('#contentDetail').val();
	
	$.ajax({
		url:"Cardcontentsupdate.card",
		datatype:"text",
		data:{cardNum:cardNum, cardContents:contentDetail},
		success:function(data){
			if(data == 1) {	}else {alert("오류가 발생하였습니다.");}
		}
	});

}

//프로젝트 멤버의 모든 리스트를 넣어준다.
function cardMemberAddList(obj){
	$.ajax({
		url:"CardMemeberAddList.card",
		datatype:"json",
		success:function(data){
			var json = JSON.parse(data);
			var htmldata = "";
			$.each(json, function(index, elt){
				htmldata += '<li><a onclick="cardMemberAdd(this)">'+ elt.userId +'</a></li>'; 
			})	
			
			$(obj).parent().children('ul').html(htmldata);
		}
	});
}

//해당 카드에 카드멤버를 추가 시킨다
function cardMemberAdd(obj){
	var id = $(obj).html();
	var cardNum = $('#hiddenCardnum').val();
	
	$.ajax({
		url:"CardMemeberAdd.card",
		datatype:"text",
		data:{cardNum:cardNum, userId:id},
		success:function(data){
			cardMemberListView(cardNum);
		}
	});
}

//해당 카드에 카드멤버를 삭제 시킨다
function cardMemberDel(obj){
	var id = $(obj).children('input').val();
	var cardNum = $('#hiddenCardnum').val();
	
	$.ajax({
		url:"CardMemeberDel.card",
		datatype:"text",
		data:{cardNum:cardNum, userId:id},
		success:function(data){
			cardMemberListView(cardNum);
		}
	});
}

//해당 카드에 대한 다운로드 파일을 지운다
function fileInputDel(obj, fileNum){
	var cardNum = $('#hiddenCardnum').val();
	
	$.ajax({
		url:"DownLoadDel.card",
		datatype:"text",
		data:{cardNum:cardNum, upLoadNum:fileNum},
		success:function(data){
			callUploadList(cardNum);
		}
	});
}
