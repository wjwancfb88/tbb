$(function(){
	$("#page9_jHtmlForm1").validationEngine();
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
	
	var page9_jListSelect1 = [{ value:'',
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
	document.getElementById("isp").addEventListener("toggle",function(event){
		if(event.detail.isActive){
			var btnArray = ['取消', '确认'];
			mui.confirm('开启公开选项,系统将公开此信件', '警告!', btnArray, function(e) {
				if(e.index==1){
					console.log("不公开");
				}else{
					mui("#isp").switch().toggle();

				}
			},'div')
		}else{

		}
	});

	document.getElementById("niming").addEventListener("toggle",function(event){
		if(event.detail.isActive){
			var btnArray = ['取消', '确认'];
			mui.confirm('开启匿名选项,系统将隐藏你的姓名', '警告!', btnArray, function(e) {
				if(e.index==1){
					console.log("匿名");
				}else{
					mui("#niming").switch().toggle();

				}
			},'div')
		}else{

		}
	});
	function add(){
		if(codemsg==$("#code").val()){
			//document.getElementById("page9_jHtmlForm1").submit();

			var  receiveName=$("#receiveName").val();
			var  sendName=$("#sendName").val();
			var  title=$("#title").val();
			var  content=$("#content").val();
			var isp = document.getElementById('isp');
			var overt='0';
			if(isp.classList.contains('mui-active')){
				overt='0';
			}else{
				overt='1';
			}
			var niming = document.getElementById('niming');
			var anonymous='0';
			if(niming.classList.contains('mui-active')){
				anonymous='0';
			}else{
				anonymous='1';
			}
			var  answer=$("#answer").val();
			$.ajax({
				type: 'POST',
				url: "../mailbox/add",
				data:{receiveName:receiveName,sendName:sendName,title:title,content:content,overt:overt,anonymous:anonymous},
				success: function (result) {

				}

			})
		}else{
			alert("验证码错误！！");
		}



	}
	var codemsg="";
	var canvas = document.getElementById('canvas');
	var cxt = canvas.getContext('2d');
	drawString(randomString());
	function randomString(){
		var code='';
		var source='012345789ascdefgqwrtyuioplkjghmnvczx';

		for(var i=0;i<4;i++){
			var index=Math.floor(Math.random()*source.length);
			code = code+ source.charAt(index);
		}
		codemsg=code;
		return code;
	}

	function drawString(code){
		sessionStorage.setItem('Code',code);
		cxt.clearRect(0,0,canvas.width,canvas.height);
		cxt.fillStyle='#ccc';
		cxt.font='30px 黑体';
		cxt.textBaseline='top';
		for(var i=0;i<code.length;i++){
			var r=randomNumber(0,255);
			var g=randomNumber(0,255);
			var b=randomNumber(0,255);
			var h=randomNumber(0,canvas.height-30);
			cxt.fillStyle='rgb('+ r + ',' + g + ',' + b + ')';
			cxt.fillText(code.charAt(i),10 + i*18,h);
		}
		for(var i=0;i<20;i++){
			var x1 = randomNumber(0,canvas.width);
			var y1 = randomNumber(0,canvas.height);

			var x2 = randomNumber(0,canvas.width);
			var y2 = randomNumber(0,canvas.height);

			cxt.strokeStyle='#ddd';
			cxt.beginPath();
			cxt.moveTo(x1,y1);
			cxt.lineTo(x2,y2);
			cxt.closePath();
			cxt.stroke();
		}
	}
	canvas.onclick=function(e){
		var code = randomString();

		drawString(code);
	}
	function randomNumber(min,max){
		return Math.floor(Math.random()*(max-min+1)+min);
	}