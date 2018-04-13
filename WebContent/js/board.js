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
	$('.listbox').sortable({
		items:'div:not(.listtitle)',
		placeholder: "ui-state-highlight",
		connectWith: '.listbox'
	}).disableSelection(); 
}

function autoWidth(){
	var width = ($('.listbox').length) * 320 +"px";
	$('#content-md').css("width", width)
	$('#mainScreen').css("width", width)
}

function addCardView(obj) {
	var div = "<div class='card'><input class='inputtext' type='text' placeholder='card title' name='title'><a onclick='addCard(this)'>완료</a></div>"
	$(obj).before(div)
}

function addCard(obj){
	var parent = $(obj).closest('div')
	var value = parent[0].firstChild.value
	if(value.trim() != ""){
		$(parent).empty()
		parent[0].innerHTML = value
		$(parent).attr({ 
			'data-toggle':'modal',
			'data-target':'#myModal1'
		})
		sortable()
	}
}

function addListView(obj){
	var div = "<div class='listbox'><input class='inputtext' type='text' placeholder='list title' name='title'><a onclick='addList(this)'>완료</a></div>"
	$(obj).before(div)
	autoWidth()
}
function addList(obj){
	var parent = $(obj).closest('.listbox')
	var value = parent[0].firstChild.value
	if(value.trim() != ""){
		parent.empty()
		var	div = '<div class="listtitle">' + value + '</div>'
			div += "<a class='cardcreate' onclick='addCardView(this)'>Add a card...</a>"
		parent.append(div)
	}
}