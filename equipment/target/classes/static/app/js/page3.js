
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


	$(function () {

		var flag = $("#flag").val();
		if (flag == 1){
			//alert("温馨提示：欢迎您进入兔宝宝家居产业园！本园区内禁止吸烟，车辆限速5码。爱护园区环境，需要您的参与和支持。【兔宝宝家居产业园】");
		}else if (flag == 2){
			//alert("温馨提示：欢迎您进入德华兔宝宝！本公司内禁止吸烟，车辆限速5码。爱护公司环境，需要您的参与和支持。【兔宝宝】");
		}else {
			//alert("温馨提示：欢迎您进入德华兔宝宝！本公司内禁止吸烟，车辆限速5码。爱护公司环境，需要您的参与和支持。【兔宝宝】");
		}

		window.opener=null;
		window.open('','_self');
		window.close();
	});
	window.onload=function(){
		var t2 = window.setInterval("closeall()",5000);

	}

	function closeall(){
		window.opener=null;
		window.open('','_self');
		window.close();
	}