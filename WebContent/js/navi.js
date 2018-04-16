var j = 1	
$(function(){
	$("#cardFileUpload").hide()
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
		$('#cardFileUpload').click()
	})
	
	$('#addCheckList').click(function(){
		addCheckListForm()
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


//카드에 파일 업로드 하기 
function changeValue(obj){
	console.log("파일 업로드");
	
	var data = $("#cardfileupload").serialize();

	if(obj.value != ""){
		
		$("#cardfileupload").ajaxForm({
			url: "cardfileupload.card",
			data: data,
			type : "post",
			datatype: "text",
			enctype:"multipart/form-data",
			success: function(data){
				if(data.trim()<=0){
					console.log(">"+data+"<")
					console.log(">"+data.trim()+"<")
					alert("파일 업로드 실패");
				}
				callUploadList($("#hiddenCardnum").val());
				
			}
		})
	}
	$("#cardfileupload").submit();
	obj.value =""; 
	
}



function fileInputDel(obj){
	obj.closest('div').remove()
}

//Check List 버튼을 클릭시
function addCheckListForm(){
	var div = '<div><input type="text" class="form-control inputtextbox">'
		div += '<button type="button" class="close glyphicon" onclick="addCheckList(this)">&#xe013;</button></div>'

	$('label[for=checklist]').after(div)
	//$('#checkListForm').append(div);
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
			}
		});
		
		/*var div = '<p><input type="checkbox" id="checkbox'+j+'"><label for="checkbox'+i+'">'+value+'</label><button type="button" class="close" onclick="removeCheckList(this)">&times;</button></p>'
		j++
		
		obj.closest('div').remove()
		
		$('#checkListForm').append(div)*/
	}
}

function removeCheckList(obj){
	$(obj).closest('p').remove()
}

function addComment(obj){
	var value = $(obj).closest('div')[0].children[1].value
	if(value.trim() != ""){
		var div = '<div class="commentlist"><img src="images/profile.png" class="img-circle person" alt="Random Name" width="30" height="30">'
			div += '<input type="text" class="form-control commentinputtextbox" value="' + value + '" readonly>'
			div += '<button type="button" class="close" onclick="removeComment(this)">&times;</button></div>'
			
		$('#commentListForm').append(div)
		$(obj).closest('div')[0].children[1].value = ""
	}
}

function removeComment(obj){
	$(obj).closest('div').remove()
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