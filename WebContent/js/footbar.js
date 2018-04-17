//초대메세지 보내기
function memberinvite() {
	var userId1 = $("#emailSearch").val();
	$.ajax({
		url : "idcheck.member",
		data : {email : userId1},
		success : function (datas) {
			console.log(datas.trim())
			if(datas.trim() === 'true'){
				alert('없는 회원입니다')
				return false;
			}else{
				alert('초대메시지가 전송 합니까?')
				inviteIdCheck()
			}
		}
	})
}

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
			console.log('유자아이디가 있나여??')
			console.log()
			if(datas.trim() == 2){
				alert('초대메시지가 발송 되었습니다')
				$.ajax({
					url : "invite.member",
					datatype : "JSON",
					data : data,
					success : function(datas) {
						console.log("data : " + datas);
					}
				})
			}else{
				alert('이미 가입된 회원입니다')
			}
		}
	})
}

// 멤버리스트뿌려주기
function memberList() {
	memberOwnerView()
	var userId = $("#getsession").val()
	
	$.ajax({
		url : "memberlist.project",
		datatype : "JSON",
		success : function(data) {
			memberOwnerView()
			var json = JSON.parse(data)
			console.log(json);
			var div = ""
			$('#thisProjctMeber').empty()
			var grade = $('#thisMemberGrade').val()
			$.each(json, function(i, elt) {
				div += '<div class="dropdown" style="float:left;">'
				div += '<a class = "glyphicon glyphicon-user" data-toggle="dropdown" style="font-size: 25pt; top: 7px;"></a>'
				div += '<ul class="dropdown-menu">'
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
				console.log(data);
				var id = $(obj).parent().children("input").val();
				console.log($(obj).parent());
				
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
				console.log(data);
				memberList()
				callprojectlist()
				}
			})
	
}

//팀탈퇴
function memberDelete() {
	console.log("메롱메롱메롱")
	var userId = $("#getsession").val();
	$.ajax({
			url : "memberdeleteproject.project",
			data : {userId : userId},
			datatype : "JSON",
			success : function (data) {
				console.log(data);
				memberList()
				callprojectlist()
			}
		})
}