$(function(){
	callprojectlist();
});

//프로젝트 리스트 불러오기
function callprojectlist(){
	$("#progress").empty();
	$("#complete").empty();
	var sessionId = $("#getsession").val();
	if(sessionId!=null){
		$.ajax({
			url:"projectlist.project",
			datatype:"json",
			data: {userId:sessionId},
			success:function(data){
				var json = JSON.parse(data);
				
				 $.each(json,function(key,value){
					var proejectName = value.projectName;
					var projectEndDate = value.projectEndDate;
					var projectNum = value.projectNum;
					
					$.ajax({
						url:"projectadmin.project",
						datatype:"text",
						data: {projectnum:projectNum, userid:sessionId},
						success:function(data){
							var cp = '<div><button class="button btn-1" onclick="projectView('+projectNum+')">'+ proejectName +'</button>';
							var pg = '<div><button class="button btn-1" onclick="projectView('+projectNum+')">' + proejectName + '</button>';
							
							if(data.trim()=="0"){
								cp += '<a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a>'
								+ '<ul class="dropdown-menu" style="float: right; position: unset;">'
								+ '<li><a onclick="projectView('+projectNum+')">프로젝트 보기</a></li>'
								+ '<li><a onclick="projectProgress('+projectNum+')">프로젝트 다시 진행</a></li></ul>';
								
								pg += '<a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a>'
								+'<ul class="dropdown-menu" style="float: right; position: unset;">'
								+'<li><a onclick="projectDel('+projectNum+')">프로젝트 삭제</a></li>'
								+'<li><a onclick="projectComplete('+projectNum+')">프로젝트 완료</a></li></ul>';
							}
							cp += '</div>';
							pg += '</div>';
							
							if(projectEndDate != ""){ //시작 날짜가 비어있지 않다면 >> 프로젝트가 완료 되었다면
								$("#complete").append(cp);
								
							}else{ // 프로젝트가 현재도 진행중이라면
								$("#progress").append(pg);
								
							}
						}
					});
				})  
			}
		})
	}
}
//프로젝트 관리 함수 끝

//프로젝트 차트
//And for a doughnut chart
function chart() {
	var userId = $("#getsession").val();
	var strat = null;
	var end = null;
	$.ajax({
			url : "startcount.project",
			datatype : "text",
			data : {userId : userId},
			success : function (data) {
				console.log("안녕재욱아");
				start = data.trim();
			}
			});
	$.ajax({
			url : "endcount.project",
			datatype : "text",
			data : {userId : userId},
			success : function(datas){
				console.log("반가워재욱아");
				end = datas.trim();
			}
			});
	var myDoughnutChart = new Chart(ctx,{
		
		// And for a doughnut chart
		    type: 'doughnut',
		    data:  {
		        datasets: [{
		            data: [start, end]
		        }],

		        // These labels appear in the legend and in the tooltips when hovering different arcs
		        labels: [
		            'Red',
		            'Yellow'
		            
		        ]
		    },
		    options: options
		});
}


