
//초대메세지 보내기
function memberinvite(projectNum1) {
	var userId1 = $("#emailSearch").val();
	var inviteUserId1 = $("#getsession").val(); //session ID로 바꿔라
	console.log("inviteUserId1" + inviteUserId1);
	console.log("userId1" + userId1);
	console.log("projectNum1" + projectNum1);
	var data = {
				projectNum : projectNum1,
				userId : userId1,
				inviteUserId : inviteUserId1
				}
	console.log(data);
	$.ajax({
		url : "invite.member",
		datatype : "JSON",
		data : data,
		success : function (data) {
			console.log("data : " + data);
		}
		})	
}