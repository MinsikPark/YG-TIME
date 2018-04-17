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
		axis : 'y',
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


function addCardView(e, listnum) {
	$(e).parent().find("#addcard").remove();
		var div = "<div class='card' id='addcard'><input class='inputtext' type='text' placeholder='card title' name='title'><a onclick='addCard(this, "+ listnum +")'>완료</a></div>"
		$(e).before(div);
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
				console.log("카드 추가 돼었나? "+ data.trim());
				$(parent).remove();
				callCardList(listnum);
			}
		});
	}
}
//카드삭제
function deleteCard(cardid, listNum) {
	event.stopPropagation();//상위 이벤트 중지
	var cardNum = cardid;
	console.log("메롱메롱" + cardNum);
	$.ajax({
			url : "carddelete.card",
			datatype:"text",
			data:{cardNum:cardNum},
			success:function(data){
				console.log("너는 누구냐?" + data.trim());
				console.log("listNum이래요 : " + listNum)
				$('#div'+cardid).remove();
				callCardList(listNum)
			}
			})
}

//리스트생성 텍스트박스를 불러오기
function addListView(obj, boardnum){
	console.log(boardnum);
	var div = "<div class='listbox'><input class='inputtext' type='text' placeholder='list title' name='title'><a onclick='addList(this,"+ boardnum +")'>완료</a></div>"
	$(obj).before(div)
	autoWidth()
}

//리스트 생성
function addList(obj, boardnum){
	var parent = $(obj).closest('.listbox')
	var value = parent[0].firstChild.value
	console.log(boardnum)
	console.log(value)
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

function listDelete(obj) {
	   console.log("나는리스트넘버야 : " + listNum)
}

//리스트 삭제
function listDel(obj){
	console.log($(obj))
	var input = confirm('삭제하시겠습니까?')
	if(input){
		console.log('true')
		 $.ajax({
	         url : "listdelete.list",
	         datatype : "JSON",
	         data : {listNum : $(obj).closest('.listtitle')[0].id.substr(7)},
	         success : function (data) {
	            $(obj).closest('.listbox').remove()
	            console.log('완료')
	         }
         })
	}else{
		console.log('false')
	}
}

//(텍스트 클릭하면 텍스트박스 불러오기)리스트 수정
function listmodify(obj, listNum, boardnum){
	var html = $(obj).html();
	var text = "<input class='inputtext' type='text' placeholder=" + html + " name='title'><a onclick='listmodifyOk(this,"+ listNum + "," + boardnum +")'>완료</a>" 
			+"<a onclick='listmodifyNo(this,"+ listNum + "," + boardnum +")'>취소</a>";
	
	$(obj).removeAttr("onclick");
	$(obj).html("");
	$(obj).append(text);
	
}

//리스트 수정 확인
function listmodifyOk(obj, listNum, boardnum){
	var name = $(obj).parent().children("input").val();
	console.log($(obj).parent().children("input").val());
	if(name.trim() == ""){
		alert("빈 문자열로 수정이 되지 않습니다");
	} else {
		$.ajax({
			url:"listupdate.list",
	        datatype:"text",
	        data:{listnum:listNum, listname:name},
	        success:function(data){
	        	var div = $(obj).parent();
	        	
	        	div.empty();
	        	div.html(name);
	        	div.attr("onclick", 'listmodify(this, '+ listNum +',' + boardnum +')');
	        }
		});
	}
}

//리스트 수정 취소
function listmodifyNo(obj, listNum, boardnum){
	$.ajax({
		url:"listselect.list",
        datatype:"JSON",
        data:{listnum:listNum},
        success:function(data){
        	var div = $(obj).parent();
        	console.log(div.attr("class"));
        	var json = JSON.parse(data);
        	div.empty();
        	div.html(json.listName);
        	div.attr("onclick", 'listmodify(this, '+ listNum +',' + boardnum +')');
        }
	});
}

//보드타이틀 클릭
function boardTitleEdit(obj, boardNum){
	var htmlObj = $(obj).html();
	$(obj).removeAttr("onclick");
	$(obj).html('');
	var edit = "<input class='inputtext' type='text' placeholder=" + htmlObj + " name='title'><a onclick='boardmodifyOk(this,"+ boardNum +")'>완료</a>" 
			+"<a onclick='boardclick("+boardNum+")'>취소</a>";
	
	$(obj).append(edit);
}

//보다디테일 클릭
function boardDetailEdit(obj, boardNum){
	var htmlObj = $(obj).html();
	$(obj).removeAttr("onclick");
	$(obj).html('');
	var edit = "<input class='inputtext' type='text' placeholder=" + htmlObj + " name='title'><a onclick='detailmodifyOk(this,"+ boardNum +")'>완료</a>" 
	+"<a onclick='boardclick("+boardNum+")'>취소</a>";
	
	$(obj).append(edit);
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
	console.log("cardDetail:cardnum:"+cardnum);
	//view DB뿌려주기
	cardViewDetail(cardnum);
	
	//onclick 변경 (카드넘버 가지고)
	$('#detaiAddbtn').attr("onclick", "updateDetail(this,"+ cardnum +")");
}

//카드 상세 페이지
function cardViewDetail(cardnum){
	/*console.log($('#'+cardnum).closest('button').click.bind(this));
	if($('#'+cardnum).closest('button').click){
		console.log("123132132132132321321321")
	}else{*/
		cardViewContents(cardnum);
		cardViewCheckList(cardnum);
		cardViewReplys(cardnum);	
	//}
	
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
			$('#contentDetail').html(json.cardContents);
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
			$('#checkListForm').empty();
			var htmldata = '';
			$.each(json, function(index, elt) {
				if(elt.checked == '0'){
					htmldata += '<p><input type="checkbox" id="checkbox'+elt.checkNum+'" onclick="checkClick(this, '+elt.checkNum+')"><label for="checkbox'+elt.checkNum+'">'+elt.checkBoxContents+'</label><button type="button" class="close" onclick="removeCheckList(this, '+elt.checkNum+')">&times;</button><button type="button" class="glyphicon close" onclick="checkBoxMod(this, '+elt.checkNum+')">&#xe065;</button></p>';
				} else{
					htmldata += '<p><input type="checkbox" id="checkbox'+elt.checkNum+'" onclick="checkClick(this, '+elt.checkNum+')" checked><label for="checkbox'+elt.checkNum+'" style="text-decoration:line-through; font-style: oblique;">'+elt.checkBoxContents+'</label><button type="button" class="close" onclick="removeCheckList(this)">&times;</button><button type="button" class="glyphicon close" onclick="checkBoxMod(this, '+elt.checkNum+')">&#xe065;</button></p>';
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