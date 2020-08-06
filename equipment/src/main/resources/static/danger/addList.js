$(function () {
	getDept();
	$("#photo").click(function(){
		if($("img").hasClass('minimg')){

			$("img").toggleClass('max');
		}
		if($("img").hasClass('max')){

			$("img").toggleClass('minimg');
		}

		if($("img").hasClass('abs')){

			$("img").toggleClass('max');
		}
	});

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


	var blobs;

	document.querySelector('#file').onchange = function (evt) {
		//alert(1);
		var files = evt.target.files;
		for(var i = 0, f; f = files[i]; i++){
			if(!f.type.match('image.*')) continue;

			var reader = new FileReader();
			//alert(2);
			reader.onload = (function(theFile){
				//alert(3);
				return function(e){
					//alert(4);
					var img = new Image();
					img.title = theFile.name;
					img.src = e.target.result;
					img.setAttribute('class','abs');
					img.onload = function() {
						//创建一个image对象，给canvas绘制使用
						//alert(5);
						var cvs = document.createElement('canvas');
						//alert(cvs);
						var scale =1 ;
						//if (this.width > 1000 || this.height > 1000) {  //1000只是示例，可以根据具体的要求去设定
						//	if (this.width > this.height) {
						//		scale = 1000 / this.width;
						//	} else {
						//		scale = 1000 / this.height;
						//	}
						//}
						cvs.width = this.width * scale;
						cvs.height = this.height * scale;
						//计算等比缩小后图片宽高
						var ctx = cvs.getContext('2d');
						//alert(6);
						ctx.drawImage(img, 0, 0, cvs.width, cvs.height);
						//alert(7);
						//var canvas = document.getElementById('canvas');
						var ndata = cvs.toDataURL("image/jpeg", 0.9);
						//alert(8);
						datadata = ndata.split(',')[1];
						//alert(9);
						//alert(datadata);
						//alert(10);
						data = window.atob(datadata);
						console.log(data.length);
						var ia = new Uint8Array(data.length);
						for (var i = 0; i < data.length; i++) {
							ia[i] = data.charCodeAt(i);
						};
						//canvas.toDataURL 返回的默认格式就是 image/png
						var blob = new Blob([ia], {
							type: "image/jpeg"
						});
						blobs=blob;
						console.log(blob)
						var fd = new FormData();
						fd.append('file', blob);
						var xhr = new XMLHttpRequest();
						console.log(fd)
						console.log(xhr)
						//xhr.addEventListener("load", "index", false);
						//xhr.addEventListener("error", "error", false);


					}
					document.getElementById("photo").appendChild(img); //这里你想插哪插哪
				}
			})(f);
			reader.readAsDataURL(f);
		}
	}
	function upload(){
		     var $c = document.querySelector("#file");//上传出发按钮
		     var $d = document.querySelector("#img");//图片容器
		     var file = $c.files[0];//获取file对象单张
		     var reader = new FileReader();//创建filereader对象
			for (var i = 0; i <  $c.files.length; i++){
				reader.readAsDataURL($c.files[i]);//转换数据
			}

		        reader.onload = function(e){//加载ok时触发的事件
			            console.log(file);
			         $d.setAttribute("src", e.target.result);//给图片地址,显示缩略图
			        $d.style.display="block";//样式显示
			     };
		 };


//var file = {
//	upload: function (e) {
//		alert("开始");
//		var self = this;
//		var files = e.target.files;
//		var type = files[0].type.split('/')[0];
//		if (type != 'image') {
//			alert("image");
//			return;
//		}
//		//var size = Math.floor(file.size / 1024 / 1024);
//		//if (size > 3) {
//		//  alert('图片大小不得超过3M');
//		//  return;
//		//};
//		for (var i = 0; i < files.length; i++) {
//			var reader = new FileReader();
//			reader.readAsDataURL(files[i]);
//			reader.onloadstart = function () {
//				//用以在上传前加入一些事件或效果，如载入中...的动画效果
//			};
//			reader.onloadend = function (e) {
//				var dataURL = this.result;
//				var imaged = new Image();
//				imaged.src = dataURL;
//				imaged.onload = function () {  //利用canvas对图片进行压缩
//					var canvas = document.createElement('canvas');
//					var ctx = canvas.getContext('2d');
//					var w = 0;
//					var h = 0;
//					if (this.width > this.height) {
//						h = 1000;
//						var scale = this.width / this.height;
//						h = h > this.height ? this.height : h;
//						w = h * scale;
//					}
//					else {
//						w = 1000;
//						var scale = this.width / this.height;
//						w = w > this.width ? this.width : w
//						h = w / scale;
//					}
//					var anw = document.createAttribute("width");
//					var anh = document.createAttribute("height");
//					if (this.width > this.height) {
//						anw.value = h;
//						anh.value = w;
//					}
//					else {
//						anw.value = w;
//						anh.value = h;
//					}
//					canvas.setAttributeNode(anw);
//					canvas.setAttributeNode(anh);
//					if (this.width > this.height) {
//						ctx.translate(h, 0);
//						ctx.rotate(90 * Math.PI / 180)
//						ctx.drawImage(this, 0, 0, w, h);
//						ctx.restore();
//					}
//					else {
//						ctx.drawImage(this, 0, 0, w, h);
//					}
//					dataURL = canvas.toDataURL('image/jpeg');
//					//回调函数用以向数据库提交数据
//					self.callback(dataURL);
//				};
//			};
//		}
//	},
//	event: function () {
//		$("#upload").change(function (e) {
//			alert("开始");
//			file.upload(e);
//		});
//	},
//	callback: function (dataURL) {
//		$.ajaxSettings.async = false;  //这里必须将ajax的异步改为同步才可以把返回并保存在隐藏域中的图片地址取出同时加在地址栏中作为参数一并传入下一个页面，这样做的目的是因为返回的图片地址不是一个json数组，而是单个的json字符串，所以只能将返回的图片地址json字符串拼接在一起作为参数传到下一个页面
//		$.post(url, dataURL, function (res) {  //将base64图片流的图片通过后台转换成普通的图片路径并上传至服务器
//			var imgUrl = $("#hiddenImgUrl").val();
//			if (res.success) {
//				$(".loading").hide();
//				if (imgUrl != "") {
//					$("#hiddenImgUrl").val(imgUrl + "|" + res.imgUrl);  //中间加一个 | 是为了到下一个页面便于将传过去的图片地址参数转换为数组
//				} else {
//					$("#hiddenImgUrl").val(res.imgUrl);
//				}
//				var imgUrl = $("#hiddenImgUrl").val();
//				window.location.href = "apply.html?imgUrl=" + imgUrl;
//			} else {
//				alert(res.message);
//			}
//		}, "json");
//	},
//	init: function () {
//		alert("kaishi")
//		this.event();
//	}
//}
function resizeMe(img) {
	//压缩的大小
	var max_width = 1920;
	var max_height = 1080;

	var canvas = document.createElement('canvas');
	var width = img.width;
	var height = img.height;
	if(width > height) {
		if(width > max_width) {
			height = Math.round(height *= max_width / width);
			width = max_width;
		}
	}else{
		if(height > max_height) {
			width = Math.round(width *= max_height / height);
			height = max_height;
		}
	}

	canvas.width = width;
	canvas.height = height;

	var ctx = canvas.getContext("2d");
	ctx.drawImage(img, 0, 0, width, height);
	//压缩率
	uploadPhoto(canvas.toDataURL("image/jpeg",0.92));
	return;
}
function  getDept(){
	$.ajax({
		type: 'POST',
		url: "/admin/danger/getDept",
		data: {
		},
		success: function (result) {

			$.each(result,function(n,value) {

				$("#dutyDept").append("<option value='"+value.id+"'>"+value.deptName+"</option>");

			});

		},
		dataType: "json"
	});



}

function uploadPhoto(_data) {
	alert("开始上传图片");
	//4.获取canvas中的图片信息
	//window.atob方法将其中的base64格式的图片转换成二进制字符串；若将转换后的值直接赋值给Blob会报错，需Uint8Array转换：最后创建Blob对象；
	_data = _data.split(',')[1];
	_data = window.atob(_data);

	//如果不用ArrayBuffer,发送给服务器的图片格式是[object Uint8Array],上传失败...
	var _buffer = new ArrayBuffer(_data.length);
	var _ubuffer = new Uint8Array(_buffer);
	for (var i = 0; i < _data.length; i++) {
		_ubuffer[i] = _data.charCodeAt(i);
	}

	// 安卓 UC浏览器不支持 new Blob()，使用BlobBuilder
	var _blob;
	try {
		_blob = new Blob([_buffer], {type:'image/jpeg'});
	} catch(ee) {
		window.BlobBuilder = window.BlobBuilder || window.WebKitBlobBuilder || window.MozBlobBuilder || window.MSBlobBuilder;
		if (ee.name == 'TypeError' && window.BlobBuilder) {
			var _bb = new BlobBuilder();
			_bb.append(_buffer);
			_blob = _bb.getBlob('image/jpeg');
		}
	}

	var _suffix = 'jpg';
	if(_blob.type === 'image/jpeg') {
		_suffix = 'jpg';
	}

	//获取NOStoken
	this.__cache._$requestDWRByGet({url: 'ImageBean.genTokens',param: [_suffix,'','','','1'],onload: function(_tokens) {
		_tokens = _tokens || [];
		var _token = _tokens[0];
		if(!_token || !_token.objectName || !_token.uploadToken){
			alert('token获取失败！');
			return false;
		}

		//上传图片到NOS
		var _objectName = _token.objectName,
			_uploadToken = _token.uploadToken,
			_bucketName = _token.bucketName;

		var _formData = new FormData();
		_formData.append('Object', _objectName);
		_formData.append('x-nos-token', _uploadToken);
		_formData.append('file',_blob);

		var _xhr;
		if (window.XMLHttpRequest) {
			_xhr = new window.XMLHttpRequest();
		} else if (window.ActiveXObject) {
			_xhr = new ActiveXObject("Microsoft.XMLHTTP");
		}

		//_xhr.onreadystatechange = function() {
		//	if(_xhr.readyState === 4) {
		//		if((_xhr.status >= 200 && _xhr.status < 300) || _xhr.status === 304) {
		//			var _imgurl = "http://nos.netease.com/" + _bucketName + "/" + _objectName + "?imageView";
		//			var _newUrl = mb.x._$imgResize(_imgurl, 750, 750, 1, true);
        //
		//			window.location.href = 'http://www.lofter.com/act/taxiu?op=effect&originImgUrl=' + _newUrl;
		//		}
		//	}
		//};
		_xhr.open('POST', 'http://app.tubaoyz.com/admin/danger/addDanger', true);
		_xhr.send(_formData);
	}});
}

function  uploadpic(){
	var ff = new FormData();
	console.log(blobs)
	ff.append('file', blobs);
	var xhr = new XMLHttpRequest();
	if($("#dangerDescript").val()==""){
		$("#dangerDescript").attr('placeholder','隐患内容不能为空');
	}else {
		ff.append('dangerDescript', $("#dangerDescript").val());
		ff.append('type', $("#type").val());
		ff.append('dutyDept', $("#dutyDept").val());
		ff.append('remark', $("#remark").val());

		console.log(xhr);
		xhr.addEventListener("load", success, false);
		//xhr.addEventListener("error", "error", false);
		xhr.open("POST", "http://app.tubaoyz.com/admin/danger/addDanger");
		xhr.send(ff);
	}

}

function success(){
	$("#file").val("");
	alert("提交成功！");
	//document.getElementsByClassName("abs").parentNode.re
	$("img").removeAttr("src");
	$("img").removeAttr("title");
	$("#photo").empty();
	document.getElementById("page3_jHtmlForm1").reset();

}