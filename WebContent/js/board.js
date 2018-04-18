$(function(){
	
	$.mCustomScrollbar.defaults.scrollButtons.enable=true; //enable scrolling buttons by default
	$.mCustomScrollbar.defaults.axis="y"; //enable 2 axis scrollbars by default
	$("#content-md").mCustomScrollbar({theme:"minimal-dark"});
	
	$('#mainScreen').hide()
	
	autoWidth()
	sortable()
	 $('#content-md').draggable(
          {
             axis: "x"
        },{
            stop: function() {
                
                var left = $('#content-md')[0].offsetLeft
                var maxwidth = $(window).width() - $('#content-md').width()
                
                if(left > 0){
                    $('#content-md').css('left','0px')
                }else if($(window).width() > $('#content-md').width()){
                    if(left < 0){ //화면크기가 div길이보다 크고 left가 0보다 작으면!!
                        $('#content-md').css('left','0px')
                    }
                }else if($(window).width() < $('#content-md').width()){
                    if(left < maxwidth){ //화면크기가 div길이보다 작고 left가 maxwidth보다 작으면!!
                        $('#content-md').css('left',maxwidth-80)
                    }
                }
                $('#content-md').off('mousemove')
            }
        }
    )
	$('body').attr({
		oncontextmenu:"return false",
	 	onselectstart:"return false",
	}) 
	
})
function sortable(){
	$('div[class=listbox]').sortable({
		items:'div:not(.listtitle)',
		placeholder: "ui-state-highlight",
		connectWith: '.listbox',
		start : function(event, ui){
				$('#movingBox').css({
					left: event.pageX - ui.item[0].offsetLeft - (ui.item[0].clientWidth/2),
					top : event.pageY - ui.item[0].offsetTop - (ui.item[0].clientHeight/2)
				})
				$('#movingBox').append(ui.item) 
		},
		update: function(event, ui) {
			var productOrder = $(this).sortable('toArray').toString();
			var children = $(this)[0].children
			if (children[1].className === 'cardcreate'){
				var children0 = children[0], 
					children1 = children[1], 
					children2 = children[2]
				$(this).empty()
				$(this).append(children0, children2, children1)
			}
			
			$.ajax({
				url : 'CardSequenceUpdate.card',
				data : { 
							listNum : children[0].id,
							sequential : productOrder
						}
			})
				
		}
	}).disableSelection(); 
}

function autoWidth(){
	var width = ($('.listbox').length) * 320 +"px";
	$('#content-md').css("width", width)
	$('#mainScreen').css("width", width)
}

//카드를 추가하는 텍스트박스를 생성한다
function addCardView(e, listnum, boardNum) {
	$(e).parent().find("#addcard").remove();
	var div = "<div class='card' id='addcard'>" +
			"<input class='inputtext' type='text' placeholder='card title' name='title' " +
			"onkeypress='if(event.keyCode==13) {addCard($(this).parent().children(\"a\"), "+ listnum +");}' " +
			"onfocusout='focusOutBoardDelay("+boardNum+")' onkeyup='fnChkByte(this, 26)'>" +
			"<a onclick='addCard(this, "+ listnum +")'>완료</a></div>";
	$(e).before(div);
	$('#addcard').children('input').focus();
}


function addCard(obj, listnum){
	var parent = $(obj).closest('div')
	var value = parent[0].firstChild.value //cardname
	if(value.trim() != ""){
		$.ajax({
			url:"Cardinsert.card",
			datatype:"JSON",
			data:{listNum:listnum, cardName:value},
			success:function(data){
				$(parent).remove();
				callCardList(listnum);
				$('#contentDetail').empty();
				
			}
		});
	}
}

//카드삭제
function deleteCard(cardid, listNum) {
	event.stopPropagation();//상위 이벤트 중지
	var cardNum = cardid;
	$.ajax({
			url : "carddelete.card",
			datatype:"text",
			data:{cardNum:cardNum},
			success:function(data){
				$('#div'+cardid).remove();
				callCardList(listNum)
			}
			})
}

//리스트생성 텍스트박스를 불러오기
function addListView(obj, boardnum){
	var div = "<div class='listbox'><input onkeypress='if(event.keyCode==13) {addList($(this).parent().children(\"a\"), "+ boardnum +");}' " +
			"onfocusout='focusOutBoardDelay("+boardnum+")' onkeyup='fnChkByte(this, 20)'" +
			"class='inputtext' type='text' placeholder='list title' name='title'>" +
			"<a onclick='addList(this,"+ boardnum +")'>완료</a></div>"
	var parent = $(obj).parent().attr('id');
	$(obj).before(div)
	$('#content-md').children().children('input').focus();
	autoWidth()
}

//리스트 생성
function addList(obj, boardnum){
	var parent = $(obj).closest('.listbox')
	var value = parent[0].firstChild.value
	if(value.trim() != ""){
		$.ajax({
			url:"listinsert.list",
            datatype:"JSON",
            data:{boardNum:boardnum, listname:value},
            success:function(data){
            	parent.empty()
            	boardclick(boardnum);
            	sortable();
            }
		});
		
	}
}

//리스트 삭제
function listDel(obj){
	var input = confirm('삭제하시겠습니까?')
	if(input){
		 $.ajax({
	         url : "listdelete.list",
	         datatype : "JSON",
	         data : {listNum : $(obj).closest('.listtitle')[0].id.substr(7)},
	         success : function (data) {
	            $(obj).closest('.listbox').remove()
	         }
         })
	}else{
	}
}

//(텍스트 클릭하면 텍스트박스 불러오기)리스트 수정
function listmodify(obj, listNum, boardnum){
	var html = $(obj).html();
	var text = "<input onkeypress='if(event.keyCode==13) {listmodifyOk($(this).parent().children(\"a\"), "+ listNum +", "+ boardnum +");}' " +
			"onfocusout='focusOutBoardDelay("+boardnum+")' class='inputtext' " +
			"type='text' placeholder=" + html + " name='title' onkeyup='fnChkByte(this,20)' >" +
			"<a onclick='listmodifyOk(this,"+ listNum + "," + boardnum +")'>완료</a>";;
	
	$(obj).removeAttr("onclick");
	$(obj).html("");
	$(obj).parent().children('a').remove();
	$(obj).append(text);
	$(obj).children('input').focus();
}

//리스트 수정 확인
function listmodifyOk(obj, listNum, boardnum){
	var name = $(obj).parent().children("input").val();
	if(name.trim() == ""){
		alert("빈 문자열로 수정이 되지 않습니다");
	} else {
		$.ajax({
			url:"listupdate.list",
	        datatype:"text",
	        data:{listnum:listNum, listname:name},
	        success:function(data){
	        	boardclick(boardnum);
	        }
		});
	}
}

//보드타이틀 클릭
function boardTitleEdit(obj, boardNum){
	var htmlObj = $(obj).html();
	$(obj).removeAttr("onclick");
	$(obj).html('');
	var edit = "<input onfocusout='focusOutBoardDelay("+boardNum+")' class='inputtext' type='text' placeholder='" + htmlObj + "' " +
			"name='title' onkeyup='fnChkByte(this,20)' onkeypress='if(event.keyCode==13) {boardmodifyOk($(this).parent().children(\"a\"), "+ boardNum +");}'>" +
			"<a onclick='boardmodifyOk(this,"+ boardNum +")'>완료</a>";
	
	$(obj).append(edit);
	$(obj).children('input').focus();
}

//보드디테일 클릭
function boardDetailEdit(obj, boardNum){
	var htmlObj = $(obj).html();
	$(obj).removeAttr("onclick");
	$(obj).html('');
	var edit = "<input onfocusout='focusOutBoardDelay("+boardNum+")' class='inputtext' type='text' placeholder='" + htmlObj + "' " +
			"name='title' onkeyup='fnChkByte(this,30)' onkeypress='if(event.keyCode==13) {detailmodifyOk($(this).parent().children(\"a\"), "+ boardNum +");}'>" +
			"<a onclick='detailmodifyOk(this,"+ boardNum +")'>완료</a>";
	
	$(obj).append(edit);
	$(obj).children('input').focus();
}

//보드제목 수정 확인
function boardmodifyOk(obj, boardnum){
	var title = $(obj).parent().children("input").val();
	var data = {
			boardNum:boardnum,
			boardTitle: title
	};
	$.ajax({
		url:"boardtitlemodify.board",
		datatype:"text",
		data:data,
		success:function(data){
			if(data.trim() <= 0){
				alert("제목 변경 실패");
			}else{
				boardclick(boardnum);
			}
		}
	});
}

//보드 디테일 수정 확인
function detailmodifyOk(obj, boardnum){
	var detailtitle = $(obj).parent().children("input").val();
	var data = {
			boardNum:boardnum,
			detail: detailtitle
	};
	$.ajax({
		url:"boarddetailmodify.board",
		datatype:"text",
		data:data,
		success:function(data){
			if(data.trim() <= 0){
				alert("제목 변경 실패");
			}else{
				boardclick(boardnum);
			}
		}
	});
}

//보드 디테일 모달창 띄우기
function cardDetail(obj){
	//$(obj).attr('id') == 카드넘버
	var cardnum = $(obj).attr('id');
	$('#hiddenCardnum').attr("value", cardnum);
	//view DB뿌려주기
	cardViewDetail(cardnum);
	
	//onclick 변경 (카드넘버 가지고)
	$('#detaiAddbtn').attr("onclick", "updateDetail(this,"+ cardnum +")");
}

//카드 상세 페이지
function cardViewDetail(cardnum){

	//파일리스트 있으면 불러오기 
	callUploadList(cardnum);
	cardViewContents(cardnum);
	cardViewCheckList(cardnum);
	cardViewReplys(cardnum);
	cardMemberListView(cardnum);
}

//카드제목 보여주기 & 카드내용이 있었다면 보여주기
function cardViewContents(cardnum){
	
	$.ajax({
		url:"Cardselect.card",
		datatype:"json",
		data:{CardNum:cardnum},
		success:function(data){
			var json = JSON.parse(data);
			//json : cardContents, cardName, cardNum, cardSequential, deleteCheck, listNum
			$('#modalHeader').html(json.cardName);
			$('#contentDetail').val(json.cardContents);
		}
	});

}

//카드체크리스트 있다면 보여주기
function cardViewCheckList(cardnum){
	$.ajax({
		url:"Checklist.card",
		datatype:"json",
		data:{CardNum:cardnum},
		success:function(data){
			var json = JSON.parse(data);
			//json : cardNum, checkBoxContents, checkNum, checked
			var htmldata = '';
			$.each(json, function(index, elt) {
				if(elt.checked == '0'){
					htmldata += '<p><input type="checkbox" id="checkbox'+elt.checkNum+'" onclick="checkClick(this, '+elt.checkNum+')"><label for="checkbox'+elt.checkNum+'">'+elt.checkBoxContents+'</label><button type="button" class="close" onclick="removeCheckList(this, '+elt.checkNum+')">&times;</button><button type="button" class="glyphicon close" onclick="checkBoxMod(this, '+elt.checkNum+')">&#xe065;</button></p>';
				} else{
					htmldata += '<p><input type="checkbox" id="checkbox'+elt.checkNum+'" onclick="checkClick(this, '+elt.checkNum+')" checked><label for="checkbox'+elt.checkNum+'" style="text-decoration:line-through; font-style: oblique;">'+elt.checkBoxContents+'</label><button type="button" class="close" onclick="removeCheckList(this, '+elt.checkNum+')">&times;</button><button type="button" class="glyphicon close" onclick="checkBoxMod(this, '+elt.checkNum+')">&#xe065;</button></p>';
				}
			});
			
			$('#checkListForm').html(htmldata);
		}
	});
}


//카드댓글이 있다면 보여주기
function cardViewReplys(cardnum){
	$.ajax({
		url:"Replylist.card",
		datatype:"json",
		data:{CardNum:cardnum},
		success:function(data){
			var json = JSON.parse(data);
			//json : cardNum, replyContents, replyNum, userId
			var arr = [];
			
			var htmldata = '';
			$.each(json, function(index, elt) {
				htmldata += '<div id="replyNum'+ elt.replyNum +'" class="commentlist">'
						+ '<input type="text" class="form-control commentinputtextbox" value="' + elt.replyContents + '" readonly onclick="replyMod(this, '+ elt.replyNum +')">'
						+ '<button type="button" class="close" onclick="removeComment(this)">&times;</button></div><br>';
				arr.push({userId:elt.userId, replyNum:elt.replyNum});
			});
			
			$('#commentListForm').html(htmldata);

			userMember(arr);
		}
	});
}

//멤버 찾아서 멤버 프로필 보여주기
function userMember(arr){
	$.each(arr, function(i, elt) {
		$.ajax({
			url : "userSelect.member",
			datatype : "JSON",
			data : {userId:elt.userId},
			success : function(data) {
				//json : userId, userNicname, userProfile
				var json = JSON.parse(data);
				var htmldata = '';
				if(json.userProfile == "" || json.userProfile == null){
					htmldata += '<img src="profile/profile.png" class="img-circle person" alt="Random Name" width="30" height="30">';
				}else{
					htmldata += '<img src="profile/'+json.userProfile+'" class="img-circle person" alt="Random Name" width="30" height="30">';
				}
				htmldata += '<label>'+json.userNicname+'</label><br>';
				
				$('#replyNum'+elt.replyNum).prepend(htmldata);
			}
		});
	});
}

// 카드에 업로드되어 있는 파일 목록 불러오기
function callUploadList(cardNum){
	$('#fileUploadForm').empty();
	$.ajax({
		url:"carduploadlist.card",
		data:{cardNum:cardNum},
		datatype:"json",
		success:function(data){
			var json = JSON.parse(data);
			$.each(json, function(index,json){
				var div ='<div><a class="down" href="download?fileName='+json.filePath+'">'+ json.originFileName+'</a>' 
				div += '<button type="button" class="close" onclick="fileInputDel(this, '+ json.fileNum +')">&times;</button></div>'
				$('#fileUploadForm').append(div)			
			})	
		}
	})
}

// 해당카드의 카드멤버 목록 불러오기
function cardMemberListView(cardnum){
	$.ajax({
		url:"CardMemberList.card",
		datatype:"json",
		data:{cardNum:cardnum},
		success:function(data){
			var json = JSON.parse(data);
			//json : userId, userNicname, userProfile, userPwd
			var htmldata = '';
			$.each(json, function(i, elt) {
				htmldata += '<div onclick="cardMemberDel(this)" style="cursor: pointer;">';
				if(elt.userProfile == "" || elt.userProfile == null){
					htmldata += '<img src="profile/profile.png" class="img-circle person" width="30" height="30">';
				}else{
					htmldata += '<img src="profile/'+elt.userProfile+'" class="img-circle person" width="30" height="30">';
				}
				htmldata += '<input type="hidden" value="'+elt.userId+'"></div>'
			});
			
			$('#cardMemberView').html(htmldata);
		}
	});
}
