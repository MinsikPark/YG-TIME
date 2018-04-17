var j = 1	
$(function(){
	$('.setting, .button, .tab-content, #mainFooterbar').hide()
	
	//사이드 관련
	$('#navigationBars, #mainScreen, #calendar').click(function() {
		sideHide()
	}) 
	
	$('#mySidenav').click(function() {
		sideShow()
	})
	//여기까지 사이드 관련
	$('#btnFileUpLoad').click(function() {
		$('#btnFileUpload').click()
	})
	
	$('#modalHeader').click(function(){
		cardNameMod();
	})
	
	$('.dropdown a.message').on("click", function(e){
		$(this).next('ul').toggle();
		e.stopPropagation();
		e.preventDefault();
	});
})

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

function changeValue(obj){
	if(obj.value != ""){
		var div = '<div><input type="text" class="form-control inputtextbox" value="첨부 파일  : '+ obj.value.substr(12)+'" readonly>'
			div += '<button type="button" class="close" onclick="fileInputDel(this)">&times;</button></div>'
		$('#fileUploadForm').append(div)
	}
	obj.value =""; 
}

function fileInputDel(obj){
	obj.closest('div').remove()
}

//상세페이지 카드명 클릭시 텍스트 생성
function cardNameMod(){
	var cardnum = $('#hiddenCardnum').val();
	var htmlObj = $('#modalHeader').html();
	
	var div = '<div onfocusout="focusoutdelay('+ cardnum +')"><input type="text" class="form-control inputtextbox" placeholder=' + htmlObj + '>'
		+ '<button type="button" class="close glyphicon" onclick="cardNameModOk()">&#xe013;</button></div>';

	$('#modalHeader').html(div);
	$('#modalHeader').children('div').children('input').focus();
}

//상세페이지 카드명 수정 완료
function cardNameModOk(){
	var cardnum = $('#hiddenCardnum').val();
	var value = $('#modalHeader').children('div').children('input').val();
	if(value.trim() != ""){
		$.ajax({
			url:"CardNameUpdate.card",
			datatype:"text",
			data:{cardNum:cardnum, cardName:value.trim()},
			success:function(data){
				var boardnum = $('#hiddenBoardnum').val();
				boardclick(boardnum);
				cardViewDetail(cardnum);
			}
		});
	}else{
		cardViewDetail(cardnum);
	}
}

//Check List 버튼을 클릭시
function addCheckListForm(){
	var cardnum = $('#hiddenCardnum').val();
	
	var div = '<div id="addCheckListdiv"><input type="text" class="form-control inputtextbox">'
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
	var cardnum = $('#hiddenCardnum').val();
	var value = $(obj).closest('div')[0].children[0].value
	
	if(value.trim() != ""){
		$.ajax({
			url:"Checkinsert.card",
			datatype:"text",
			data:{cardNum:cardnum, checkboxcontents:value},
			success:function(data){
				console.log(data.trim());
				cardViewDetail(cardnum);
				addCancel();
			}
		});
	}
}

//체크를 클릭했을 때 바로 업데이트 하기
function checkClick(obj, checknum){
	var checked = 0;
	var content = $(obj).parent().children('label').html();
	
	$(obj).change(function(){
        if($(obj).is(":checked")){
        	checked = 1;
        }else{
        	checked = 0;
        }
        checkUpdate(checked, content, checknum);
    });
}

//체크박스 업데이트
function checkUpdate(checked, content, checknum){
	var cardnum = $('#hiddenCardnum').val();
	$.ajax({
		url:"Checkupdate.card",
		datatype:"text",
		data:{Checked:checked, Checkboxcontents:content, Cardnum:cardnum, Checknum:checknum},
		success:function(data){
			//성공 아무것도 없어도 됨.
			focusoutdelay(cardnum);
		}
	});
}

//체크박스를 삭제한다
function removeCheckList(obj, checknum){
	var cardnum = $('#hiddenCardnum').val();
	$.ajax({
		url:"Checkdelete.card",
		datatype:"text",
		data:{Cardnum:cardnum, Checknum:checknum},
		success:function(data){
			cardViewDetail(cardnum);
		}
	});
}

//체크박스를 텍스트를 생성하다
function checkBoxMod(obj, checknum){
	var cardnum = $('#hiddenCardnum').val();
	var p = $(obj).closest('p');
	var text = p.children('label').html();
	
	var div = '<div onfocusout="focusoutdelay('+ cardnum +')"><input type="text" class="form-control inputtextbox">'
		+ '<button type="button" class="close glyphicon" onclick="checkBoxModOk(this, '+checknum+')">&#xe013;</button></div>';
	p.empty();
	p.html(div);
	p.children('div').children('input').focus();
}

//체크박스 내용 수정
function checkBoxModOk(obj, checknum){
	var content = $(obj).parent().children('input').val();
	checkUpdate(0, content, checknum);
}

//포커스 아웃 딜레이
function focusoutdelay(cardnum){
	setTimeout(function() {
		cardViewDetail(cardnum);
	}, 300);
}

//댓글을 추가하다
function addComment(obj){
	//userid, cardnum, replycontents
	var id = $('#getsession').val();
	var cardnum = $('#hiddenCardnum').val();
	var value = $(obj).closest('div')[0].children[1].value
	
	if(value.trim() != ""){
		$.ajax({
			url:"ReplyAdd.card",
			datatype:"text",
			data:{UserId:id, CardNum:cardnum, ReplyContents:value},
			success:function(data){
				cardViewDetail(cardnum);
			}
		});
	}
}

//자신이 입력한 댓글 삭제
function removeComment(obj){
	var id = $('#getsession').val();
	var cardnum = $('#hiddenCardnum').val();
	var replynum = $(obj).parent().attr("id").substr(8);
	
	$.ajax({
		url:"ReplyDel.card",
		datatype:"text",
		data:{UserId:id, CardNum:cardnum, ReplyNum:replynum},
		success:function(data){
			console.log(data.trim());
			if(data.trim() == '0'){
				alert('댓글을 등록한 멤버가 아닙니다');
			}else{
				alert('삭제가 완료 되었습니다.');
				cardViewDetail(cardnum);
			}
		}
	});
}

//댓글을 수정하기전 작성한 인원이 맞는지 확인
function replyMod(obj, replyNum){
	var id = $('#getsession').val();
	var cardnum = $('#hiddenCardnum').val();
	
	$.ajax({
		url:"ReplySel.card",
		datatype:"json",
		data:{CardNum:cardnum, ReplyNum:replyNum},
		success:function(data){
			var json = JSON.parse(data);
			console.log(json);
			if(json.userId == id){
				$(obj).removeAttr('readonly');
				$(obj).attr('onfocusout', 'focusoutdelay('+ cardnum +')');
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
	var cardnum = $('#hiddenCardnum').val();
	var value = $(obj).parent().children('input').val();
	
	$.ajax({
		url:"ReplyUp.card",
		datatype:"text",
		data:{ReplyContents:value, CardNum:cardnum, ReplyNum:replyNum},
		success:function(data){
			alert('댓글을 수정했습니다.');
		}
	});
}

//카드 상세페이지, 상세내용 추가
function updateDetail(obj, cardNum){
	console.log("카드넘버: " + cardNum);
	console.log($('#contentDetail').val());
	var contentDetail = $('#contentDetail').val();
	
	$.ajax({
		url:"Cardcontentsupdate.card",
		datatype:"text",
		data:{cardNum:cardNum, cardContents:contentDetail},
		success:function(data){
			console.log("카드 업데이트 : " + data);
			alert("카드완료");
			if(data == 1) {
				alert("카드 내용이 추가되었습니다.");
			}else {
				alert("오류가 발생하였습니다.");
			}
		}
	});
}

function bokyeong(obj) {
	console.log($(obj).css('left'))
}