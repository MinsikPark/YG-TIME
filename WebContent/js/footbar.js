//초대메세지 보내기
function memberinvite() {
	var userId1 = $("#emailSearch").val();
	$.ajax({
		url : "idcheck.member",
		data : {email : userId1},
		success : function (datas) {
			if(datas.trim() === 'true'){
				alert('없는 회원입니다')
				return false;
			}else{
				inviteIdCheck()
			}
		}
	})
}
//초대아이디 체크하기
function inviteIdCheck(){
	var userId1 = $("#emailSearch").val();
	var inviteUserId1 = $("#getsession").val(); // session ID로 바꿔라
	var data = {
		userId : userId1,
		inviteUserId : inviteUserId1
	}
	
	$.ajax({
		url :"owner.project",
		data : {userId : userId1},
		success : function(datas) {
			if(datas.trim() == 2){
				alert('초대메시지가 발송 되었습니다')
				$('#emailSearch').val("");
				$.ajax({
					url : "invite.member",
					datatype : "JSON",
					data : data,
					success : function(datas) {
					}
				})
			}else{
				alert('이미 가입된 회원입니다')
				$('#emailSearch').val("");
			}
		}
	})
}

// 멤버리스트뿌려주기
function memberList() {
	$('#thisProjctMeber').empty();
	memberOwnerView()
	var userId = $("#getsession").val()
	
	$.ajax({
		url : "memberlist.project",
		datatype : "JSON",
		success : function(data) {
			memberOwnerView()
			var json = JSON.parse(data)
			var div = ""
			$('#thisProjctMeber').empty()
			var grade = $('#thisMemberGrade').val()
			$.each(json, function(i, elt) {
				div += '<div class="dropup" style="float:left;">'
				if(elt.userProfile ==""){
					div += '<a data-toggle="dropdown" style="font-size: 25pt; top: 7px; cursor: pointer;"><img style="width: 50px;height:50px" class="img-circle" src = "profile/profile.png" /></a>'	;				
				}else{
					div +='<a data-toggle="dropdown" style="font-size: 25pt; top: 7px; cursor: pointer;"><img style="width: 50px;height:50px" class="img-circle" src = "profile/'+elt.userProfile+'" /></a>';
				}		
				div += '<ul class="dropdown-menu" style="cursor: pointer;">'
				if(grade == '0' && elt.userId!=userId){
					div += '<li><input type="hidden" value="'+ elt.userId +'"><a onclick="memberToKickOut(this)">맴버제명</a></li>'
					div += '<li><input type="hidden" value="'+ elt.userId +'"><a onclick="memberMendate(this)">팀장위임</a></li>'
				}else if ($("#getsession").val() ==  elt.userId && grade=='1') {
					div += '<li><a onclick="memberDelete()">멤버탈퇴</a></li>'
				}
				div += '<li><a>'+ elt.userNicname+'</a></li>'
				div += '</ul></div>'
				
					
			})
			$('#thisProjctMeber').append(div)
			
		}

	})

}
//오너인지판단하고 드롭박스보여주기
function memberOwnerView() {
	var userId = $("#getsession").val();
	$.ajax({
			url : "owner.project",
			data : {userId : userId},
			datatype : "JSON",
			success: function (data) {
				owner = data.trim()
				$('#thisMemberGrade').val(owner)
			}
			
		})
}
//오너위임
function memberMendate(obj) {
	$.ajax({
			url : "mendate.project",
			datatype : "JSON",
			data : {userId : $("#getsession").val()},
			success: function (data){
				var id = $(obj).parent().children("input").val();
				$.ajax({
					url : "mendate.project",
					datatype : "JSON",
					data : {userId : id},
					success: function () {
						memberList()
						callprojectlist()
					}
				});
			}
			
	})
}

//팀원제명
function memberToKickOut(obj) {
	var userId = $("#getsession").val();
	var id = $(obj).parent().children("input").val();
	$.ajax({
		url : "tokickout.project",
		datatype : "JSON",
		data : {
				userId : userId,
				outUserId : id
				},
		success: function (data){
				memberList()
				callprojectlist()
				
				}
			})
	
}
//오토컴플릿
function autoComplete() {
	 $.ajax({
		 		url : "comple.member",
		 		datatype : "JSON",
		 		success : function (data) {
					var param = JSON.parse(data);
					$('#emailSearch').autocomplete({
						 source: param,
						 appendTo: "#friend",
						 minLength: 2
					})
					
				}
	 	
	 		})
}
 
//팀탈퇴
function memberDelete() {
	var userId = $("#getsession").val();
	$.ajax({
			url : "memberdeleteproject.project",
			data : {userId : userId},
			datatype : "JSON",
			success : function (data) {
				memberList()
				callprojectlist()
			}
		})
}