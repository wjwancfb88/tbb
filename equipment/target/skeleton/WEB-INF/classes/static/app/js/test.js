
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
	
	var page2_jListSelect1 = [	{ value:'',
		 text:'德华兔宝宝装饰新材股份有限公司',
			children: [
			{ value:'',
			 text:'信息部'},
			{ value:'',
			 text:'电商部'}
			]},
		{ value:'',
		 text:'德华集团'}];
	
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


   function add(){
		var  problem=$("#problem").val();
	    console.log(problem);
	    var  answer=$("#answer").val();
	   $.ajax({
	   	type: 'POST',
	   	url: "../entry/testadd",
		data:{problem:problem,answer:answer},
		 success: function (result) {

	   	}

	   })


   }

	function addwin(){
		$("#addwin").show();
		$("#testwin").hide();


	}

function test(){

	$("#addwin").hide();
	$("#testwin").show();



}
