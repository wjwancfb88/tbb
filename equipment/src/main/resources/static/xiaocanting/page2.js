qing.config({
	debug : false,
	// 声明需要调用的API
	jsApiList : ['checkJsApi', 'share'],
	// 声明需要监听的事件，声明后可通过 document.addEventListener 绑定监听函数
	jsEventList: ['appear', 'disappear']
});
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
		 text:'一荤一素+水果+汤'},
		{ value:'',
		 text:'一荤两素+水果+汤'},
		{ value:'',
		 text:'两荤一素+水果+汤'},
		{ value:'',
			text:'两荤两素+水果+汤'},
		{ value:'',
			text:'自助餐'}];

	var page2_jListSelect2 = [	{ value:'',
		text:'一号位'},
		{ value:'',
			text:'二号位'},
		{ value:'',
			text:'三号位'},
		{ value:'',
			text:'四号位'},
		{ value:'',
			text:'五号位'}];
	var page2_jListSelect3 = [	{ value:'',
		text:'门业'},
		{ value:'',
			text:'地板'},
		{ value:'',
			text:'胶黏剂'},
		{ value:'',
			text:'衣柜'}];
	
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
					console.log(showinfo);
					$('#'+objid+"Input").val(showinfo);
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
					$('#'+objid+"Input").val(rs.text);
					document.getElementById(objid).innerText = rs.text;
				}
			}
		});
	}
	
	mui('#dept')[0].addEventListener('tap', function(e) {
		showListSelect(page2_jListSelect3,2,'dept','','1');
	})
	
	mui('#orderTime')[0].addEventListener('tap', function(e) {
		showTimeSelect('date','orderTime','');
	})
	
	mui('#type')[0].addEventListener('tap', function(e) {
		showListSelect(page2_jListSelect1,2,'type','','1');
	})
	
	mui('#seat')[0].addEventListener('tap', function(e) {
		showListSelect(page2_jListSelect2,2,'seat','','1');
	})

	qing.call('selectPersons', {
		'isMulti':false,
		'isShowMe':false,
		'success': function(result){
			console.log(result);
		}
	});