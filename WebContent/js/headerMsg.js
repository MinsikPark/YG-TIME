$(function(){
	// 헤더 관련 (profile 이미지 눌렀을 시) 초대메세지 리스트
	$("#profiledrop").click(function () {
        var param = {userId : $('#getsession').val()};
        
       $.ajax({
            url:"list.member",
            datatype:"JSON",
            data:param,
            success:function(data){
                var json = JSON.parse(data);
                
                $("#dropdownchilddiv").empty();
                
                $.each(json, function(index, obj) {
                	var str = '<div class="joinmessage" id="fromUser'+index+'">';
                	str += '<label>'+obj[0].inviteUserId+'님이 "'+ obj[1].projectName +'" 프로젝트로 초대 하였습니다</label>';
                	str += '<button  class="btn btn-warning joinbutton" value="N" onclick="msgreject(this,'+obj[0].projectNum+')">거절</button>';
                	str += '<button  class="btn btn-success joinbutton" value="Y" onclick="msgaccept(this,'+obj[0].projectNum+')">수락</button>';
                	str += '</div>';
                	
                	$("#dropdownchilddiv").append(str);
                })
            }
        })
    })
	// 여기까지 초대메세지 리스트 뽑아오기(메세지 수락, 메세지 거절)
});


// 초대승락
function msgaccept(me, projectNum){
	var param = {userId : $('#getsession').val(), projectNum : projectNum}
	
	$.ajax({
          url:"msgagree.member",
          datatype:"JSON",
          data:param,
          success:function(data){
              
              callprojectlist();
          }
      })
}

// 초대거절
function msgreject(me, projectNum){
	var param = {userId : $('#getsession').val(), projectNum : projectNum}
	
	$.ajax({
          url:"msgdel.member",
          datatype:"JSON",
          data:param
      })
}