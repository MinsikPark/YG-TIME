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

//프로젝트 추가부분
function addProjectForm(obj){
	if($('#projectName').length == 0){
		var button = '<div><button class="button btn-1"><input type="text" id="projectName" style="margin-left:-60px; color:black;"></button><a class="glyphicon setting" onclick="addProject(this)">&#xe013;</a></div>'
			$('#progress').append(button)
		$('#projectName').focus()
	}
}

function memberDel() {
    confirm("멤버를 삭제하시겠습니까?");
}

function addProject(obj) {
	var value = $('#projectName').val() 
	if(value.trim() != ""){
		$(obj).closest('div').remove()
		var div  = '<div><button class="button btn-1">'+value+'</button><a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a><ul class="dropdown-menu" style= "float: right; position: unset;">'
			div += '<li><a onclick="projectDel(this)">프로젝트 삭제</a></li><li><a onclick="projectComplete(this)">프로젝트 완료</a></li></ul>	</div>'
		$('#progress').append(div)
	}else{
		alert('프로젝트 명을 입력하세요')
	}
}

function projectDel(obj){
	console.log($(obj).closest('div'))
	$(obj).closest('div').remove()
}

function projectComplete(obj){
	var ul = $(obj).closest('ul')
	var li = '<li><a onclick="projectView(this)">프로젝트 보기</a></li><li><a onclick="projectProgress(this)">프로젝트 다시 진행</a></li>'
	$(obj).closest('div').appendTo($('#complete'))
	ul.empty()
	ul.append(li)
}

function projectProgress(obj){
	console.log($(obj).closest('ul'))
	var ul = $(obj).closest('ul')
	var li = '<li><a onclick="projectDel(this)">프로젝트 삭제</a></li><li><a onclick="projectComplete(this)">프로젝트 완료</a></li>'
	$(obj).closest('div').appendTo($('#progress'))
	ul.empty()
	ul.append(li)
}

function projectView(obj){
	console.log('프로젝트 아이디를 받아서 다시 뿌려줘요')
}

function bokyeong(obj) {
	console.log($(obj).css('left'))
}