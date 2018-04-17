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
							var cp = '<div><button class="button btn-1" id="cp" onclick="projectView(this, '+projectNum+')">'+ proejectName +'</button>';
							var pg = '<div><button class="button btn-1" id="pg" onclick="projectView(this, '+projectNum+')">' + proejectName + '</button>';
							
							if(data.trim()=="0"){
								cp += '<a class="glyphicon glyphicon-cog setting" data-toggle="dropdown"></a>'
								+ '<ul class="dropdown-menu" style="float: right; position: unset;">'
								+ '<li><a onclick="projectView(this, '+projectNum+')">프로젝트 보기</a></li>'
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



function chartClose() {
	$("#divChart").empty()
	var div = "<canvas id='myChart'></canvas>"
		$("#divChart").append(div);
}


//프로젝트 차트
//And for a doughnut chart
function chart(start, end) {
	chartClose()
	
	var ctx = document.getElementById('myChart').getContext('2d');
	var myDoughnutChart = new Chart(ctx,{
		
		// And for a doughnut chart
		    type: 'doughnut',
		    data:  {
		    	
		    	labels: [
		            '진행중 프로젝트',
		            '완료된 프로젝트'
		            
		        ],
		        datasets: [{
		            data: [start, end],
		        

		        // These labels appear in the legend and in the tooltips when hovering different arcs
		        
		        backgroundColor: [
	                'rgba(255, 99, 132, 0.7)',
	                'rgba(54, 162, 235, 0.7)'
	             
	            ]
		        }]
		    },

		    
		});
}
//리스트 차트
//And for a doughnut chart
function listChart(start, end) {
	chartClose()
	
	var ctx = document.getElementById('myChart').getContext('2d');
	var myDoughnutChart = new Chart(ctx,{
		
		// And for a doughnut chart
		    type: 'doughnut',
		    data:  {
		    	
		    	labels: [
		           
		            '진행중인 리스트',
		            '진행중인 카드'
		            
		        ],
		        datasets: [{
		            data: [start, end],
		        

		        // These labels appear in the legend and in the tooltips when hovering different arcs
		        
		        backgroundColor: [
	                'rgba(255, 99, 132, 0.8)',
	                'rgba(54, 162, 235, 0.8)'
	            ]
		        }]
		    }
		    
		});
}

//프로젝트 차트보여주기
function projectChartView(){
	console.log("들어왔니?123")
	var userId = $("#getsession").val();
	var start =null;
	var end = null;
	
	$.ajax({
			url : "startcount.project",
			datatype : "text",
			data : {userId : userId},
			success : function (data) {
				start = data.trim();
				
				$.ajax({
					url : "endcount.project",
					datatype : "text",
					data : {userId : userId},
					success : function(datas){
						end = datas.trim();
						
						chart(start,end);
					}
					});
			}
			});
	
}

//리스트차트보여주기
function listChartView() {
	var userId = $("#getsession").val();
	var start = null;
	var end = null;
	
	$.ajax({
			url : "listcount.project",
			datatype : "text",
			data : {userId : userId},
			success: function (data) {
				start = data.trim();
				
				$.ajax({
						url : "cardcount.project",
						datatype : "text",
						data : {userId : userId},
						success : function (datas) {
							end = datas.trim();
							listChart(start,end);
							
						}
						})		
			}
			})
}
