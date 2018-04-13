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
                	var str = '<form id="fromUser'+index+'" class="form container-fluid">';
                	str += '<div class="form-group">';
                	str += '<label for="email">'+obj.inviteUserId+'님이 프로젝트로 초대 하였습니다</label>';
                	str += '<input type="button" value="Y" onclick="msgaccept(this,'+obj.projectNum+')">';
                	str += '<input type="button" value="N" onclick="msgreject(this,'+obj.projectNum+')">';
                	str += '</div></form></div>';
                	
                	$("#dropdownchilddiv").append(str);
                })
            }
        })
    })
	// 여기까지 초대메세지 리스트 뽑아오기(메세지 수락, 메세지 거절)
});


// 초대승락
function msgaccept(me, projectNum){
	var param = {userid : $('#getsession').val(), projectnum : projectNum}
	
	$.ajax({
          url:"msgagree.member",
          datatype:"JSON",
          data:param,
          success:function(data){
              var json = JSON.parse(data);
              
              $("#dropdownchilddiv").empty();
              
              $.each(json, function(index, obj) {
              	var str = '<form id="fromUser'+index+'" class="form container-fluid">';
              	str += '<div class="form-group">';
              	str += '<label for="email">'+obj.inviteUserId+'님이 프로젝트로 초대 하였습니다</label>';
              	str += '<input type="button" value="Y" onclick="msgaccept(this,'+obj.projectNum+')">';
              	str += '<input type="button" value="N" onclick="msgreject(this,'+obj.projectNum+')">';
              	str += '</div></form></div>';
              	
              	$("#dropdownchilddiv").append(str);
              })
              
              callprojectlist();
          }
      })
}

// 초대거절
function msgreject(me, projectNum){
	var param = {userid : $('#getsession').val(), projectnum : projectNum}
	
	$.ajax({
          url:"msgdel.member",
          datatype:"JSON",
          data:param,
          success:function(data){
              var json = JSON.parse(data);
              
              $("#dropdownchilddiv").empty();
              
              $.each(json, function(index, obj) {
              	var str = '<form id="fromUser'+index+'" class="form container-fluid">';
              	str += '<div class="form-group">';
              	str += '<label for="email">'+obj.inviteUserId+'님이 프로젝트로 초대 하였습니다</label>';
              	str += '<input type="button" value="Y" onclick="msgaccept(this,'+obj.projectNum+')">';
              	str += '<input type="button" value="N" onclick="msgreject(this,'+obj.projectNum+')">';
              	str += '</div></form></div>';
              	
              	$("#dropdownchilddiv").append(str);
              }) 
          }
      })
}