$(function () {
	// var t2 = window.setInterval("findMsg()",5000);
//去掉定时器的方法
// 	window.clearInterval(t2);
	$('.form_datetime').datetimepicker({
		format: 'yyyy-mm-dd',
		minView: "month",
		language: 'zn', //汉化
		todayBtn: 1,
		icon: 'row-right',
		autoclose: true
		//选择日期后自动关闭
	});

	// var flag = $('#flag').val();
	// var temp = "?flag="+flag;
});



function queryList(){
	var instime=$('#instime').val();
	var inetime=$('#inetime').val();
	var flag = $('#flag').val();
	var vistor=$("#vistor").val();
	var plateNo=$("#plateNo").val();
	var user=$("#user").val();
	console.log(inetime);
	$('#userTable').bootstrapTable('refresh', {query: {insTime:instime,ineTime:inetime,flag:flag,vistor:vistor,plateNo:plateNo,user:user}});



}

function exportFile(){
	//location.href='export'
	var flag = $("#flag").val();
	var instime = $("#instime").val();
	var ineTime = $("#ineTime").val();
	var vistor = $("#vistor").val();
	var plateNo = $("#plateNo").val();
	var user = $("#user").val();
	window.location.href="/admin/entry/export?flag="+flag+"&instime="+instime+"&ineTime="+ineTime+"&vistor="+vistor+"&plateNo="+plateNo+"&user="+user;
	//$("#searchForm").prop("action","/admin/entry/export");

	// $.ajax({
	// 	type: 'GET',
	// 	url: "../entry/export",
	// 	dataType: "json",
	// 	data:{flag:flag},
	// 	contentType:'text/json,charset=utf-8',
	// 	success: function (result) {
	//
	// 	}
	//
	// });
}

$('#page2_jHtmlForm1').bootstrapValidator({

	message: 'This value is not valid',
	feedbackIcons: {
		valid: 'glyphicon glyphicon-ok',
		invalid: 'glyphicon glyphicon-remove',
		validating: 'glyphicon glyphicon-refresh'
	},
	fields: {
		phone: {
			message: '手机验证失败',
			validators: {
				notEmpty: {
					message: '手机号不能为空'
				},
				regexp:{
				regexp:/^1[3|5|8|7|9|4|6]{1}[0-9]{9}$/,
				message:'请输入正确的手机号码'
				}
			}
		},
		code: {
			validators: {
				notEmpty: {
					message: '设备编码不能为空'
				}
			}
		}
	}
});

function dateFormatter(value, row, index) {
	if (value != null && value != "") {
		var dates = value.split(".");
		dates[0] = dates[0].replace("T", " ");
		return dates[0];
	} else {
		return "-"
	}
}

function flagFormatter(value, row, index){
	var flag=row.flag;
	if(flag==1){
		return "家居产业园";
	}else if(flag==2){
		return "武康南门";
	}else if (flag==3){
		return "供应中心";
	}else if(flag == 4){
		return "武康北门";
	}
}

function findMsg(){
	$.ajax({
		type: 'POST',
		url: "../entry/findMsg",
		dataType: "json",
		success: function (result) {
			if(result=="0"||result==""){

			}else{
				$('#userTable').bootstrapTable('refresh');
				$("#msgphone").html(result);
				$("#msgmodal").modal("show");
				changeMsg();
			}
		},
	});
}

function TypeFormatter(value, row, index) {
	if(value=='0'){
		return '客户';
	}
	if(value=='1'){
		return '参观';
	}
	if(value=='2'){
		return '拜访';
	}
	if(value=='3'){
		return '其他';
	}
}
function closewin(){
	window.close();
}

function deptFormatter(value, row, index) {
	var depts=[];
	var   dept;
	if (value != null) {
		depts= value.split("\\");
		dept=depts[depts.length-1]
		return dept;
	}else{
		depts= "";
		dept="";
		return dept;
	}
}

function actionFormatter(value, row, index) {
	var status = row.status;
	var res = '<button data-toggle="modal" class="btn btn-primary btn-sm btnsmall " style="10px" href="entrylist.html#leave" data-row='+row.id+'>离开</button>';
	if(row.status=="0"){
		return res;
	}else{
		return "已离开";
	}
	var id = row.id;
	//res += ' <button typ-e="button" class="btn btn-success" onclick="showdetail(\'' + id + '\')">详情</button>';
}

function close(){
	$.ajax({
		type: 'POST',
		url: "../entry/cstatus",
		dataType: "json",
		success: function (result) {
			$('#userTable').bootstrapTable('refresh');
		}
	});
	$("#msgmodal").modal("hide");
}



function changeMsg(){
	$.ajax({
		type: 'POST',
		url: "../entry/stopMsg",
		dataType: "json",
		success: function (result) {

		}
	});
}

function leave(){
	var id=$("#lid").val();

	$.ajax({
		type: 'POST',
		url: "../entry/cstatus",
		dataType: "json",
		data:{id:id},
		success: function (result) {
			console.log(1);
			$('#userTable').bootstrapTable('refresh');
			console.log(2);
		}

	});
	console.log(3);
	$('#userTable').bootstrapTable('refresh');
	console.log(4);
	$("#leave").modal("hide");

	$('#userTable').bootstrapTable('refresh');
}
$('#leave').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget);
	var id = button.data('row');
	$("#lid").val(id);
});