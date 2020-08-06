$(function () {
	$.ajax({
		type: 'POST',
		url: "/admin/test/list",
		traditional: true,
		data: {
		},
		success: function (result) {
			$('#testcontent').html(result.problem);
			$('#id').val(result.id);
			$('#showanswer').html(result.answer);
		},
		dataType: "json"
	});

});
function showanswer(){
	$('#showanswer').css("display", "block");
}
function eaxm(){

	var testanswer=$('#testanswer').html();
	var id=$('#id').val();
	$.ajax({
		type: 'POST',
		url: "/admin/test/exam",
		traditional: true,
		data: {
			id:id,
			testanswer:testanswer
		},
		success: function (result) {
         alert(result);
		},
		dataType: "json"
	});
	$('#showanswer').css("display", "none");
}

function shuaxin(){

	window.location.reload();

}
	mui.init({
		gestureConfig:{
			tap: true,
			doubletap: true,
			longtap: true,
			hold: true,
			drag: true,
			swipe: true,
			release: true
		}
	});
	
	var page1_jListSelect1 = [	{ value:'',
		 text:'转账'},
		{ value:'',
		 text:'支票'},
		{ value:'',
		 text:'现金'}];
	
	function showListSelect(data,layer,objid,sign,showtype){
		var listSelect = new mui.PopPicker({
			layer: layer
		});
		listSelect.setData(data);
		listSelect.show(function(SelectedItem){
			var showinfo='';
				for (var i=0;i<layer;i++){
					if (typeof(SelectedItem[i].text)!='undefined'){
						if(showtype==1){					
							showinfo=showinfo+' '+SelectedItem[i].text;
						}else{
							showinfo=SelectedItem[i].text;
						}
					}
				}
			if (objid != ''){
				if (sign == 'input'){
					mui('#' + objid + ' input')[0].value=showinfo;
				} else {
					document.getElementById(objid).innerText = showinfo;
				}
			}
		});
	}
	
	function showTimeSelect(stype,objid,sign){
		var timeSelect = new mui.DtPicker({
			type: stype
		});
		timeSelect.show(function(rs){
			if (objid != ''){
				if (sign == 'input'){
					mui('#' + objid + ' input')[0].value=rs.text;
				} else {
					document.getElementById(objid).innerText = rs.text;
				}
			}
		});
	}
