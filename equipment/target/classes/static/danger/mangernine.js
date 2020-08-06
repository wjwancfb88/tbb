function 	showtab(){
	$("#tooltab").toggle();


}
	$(function () {
		$("#showtab").click();
		//1.初始化Table
		var oTable = new TableInit();
		oTable.Init();

		//2.初始化Button的点击事件
		var oButtonInit = new ButtonInit();
		oButtonInit.Init();

	});

	var TableInit = function () {
		var oTableInit = new Object();
		//初始化Table
		oTableInit.Init = function () {
			$('#manger').bootstrapTable({
				url: '/admin/danger/mangerlist',         //请求后台的URL（*）
				method: 'get',                      //请求方式（*）
				//工具按钮用哪个容器
				striped: true,                      //是否显示行间隔色
				cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination: true,
				dataType:"json",//是否显示分页（*）
				sortable: false,                     //是否启用排序
				sortOrder: "asc",
				queryParamsType: "",//排序方式//排序方式//排序方式
				queryParams: oTableInit.queryParams,//传递参数（*）
				sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
				pageNumber:1,                       //初始化加载第一页，默认第一页
				pageSize: 10,                       //每页的记录行数（*）
				pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
				search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
				strictSearch: true,
				showColumns: true,                  //是否显示所有的列
				showRefresh: false,                  //是否显示刷新按钮
				minimumCountColumns: 2,             //最少允许的列数
				clickToSelect: true,                //是否启用点击选中行
				height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
				uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
				showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
				cardView: false,                    //是否显示详细视图
				detailView: true,                   //是否显示父子表
				columns: [{
					checkbox: true
				}, {
					field: 'dealerName',
					title: '负责人'
				}, {
					field: 'dangerDescript',
					title: '详情',
					formatter:function detailFormatter(value, row, index) {
							var res = '<a data-toggle="modal" style="10px" href="dangerManger.html#detail" data-row='+row.id+'>'+value+'</a>';

							var empstr="<a href='#' onclick=\"showDetail('"+row.imgUrl+"')\">"+value+"</a>";
							return res;
						}
				}, {
					field: 'dutyDept',
					title: '部门',
					formatter:function deptFormatter(value, row, index) {
						if(value=='0'){
							return '地板';
						}
						if(value=='1'){
							return '门业';
						}
						if(value=='2'){
							return '衣柜';
						}
						if(value=='3'){
							return '胶粘剂';
						}
						if(value=='4'){
							return '公共';
						}

					}
				}, {
					field: 'type',
					title: '隐患等级',
					formatter:function levelFormatter(value, row, index) {
						if(value=='0'){
							return '延期整改';
						}
						if(value=='1'){
							return '立即整改';
						}


					}
				},
					{
						field: 'dangerStatus',
						title: '状态',
						formatter:function statusFormatter(value, row, index) {
							if(value=='0'){
								return '待会签';
							}
							if(value=='1'){
								return '正在会签';
							}
							if(value=='2'){
								return '已会签';
							}
							if(value=='3'){
								return '待指派';
							}
							if(value=='4'){
								return '整改中';
							}
							if(value=='5'){
								return '已整改';
							}
						}
					}
			        ,{
						field: 'operation',
						title: '操作',
						formatter:function(value,row,index){
							var status=row.dangerStatus;
							var res = '<button data-toggle="modal" class="btn btn-primary btn-sm btnsmall " style="10px" href="dangerManger.html#detail" data-row='+row.id+'>详情</button>';
							return res;
					},},
				]
			});
		};

		//得到查询的参数
		oTableInit.queryParams = function (params) {
			var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
				limit: params.pageSize,   //页面大小
				offset: params.offset,
				pageNumber:params.pageNumber,//页码
				sort: params.sort,      //排序列名
				sortOrder: params.order //排位命令（desc，asc）
				//departmentname: $("#txt_search_departmentname").val(),
				//statu: $("#txt_search_statu").val()
			};
			return temp;
		};
		return oTableInit;
	};


	var ButtonInit = function () {
		var oInit = new Object();
		var postdata = {};

		oInit.Init = function () {
			$("#zpshow").click(function() {
				$('#modal-tj-form').modal("show");
			});
			$("#zpbutton").click(function() {
				var zpmanager=$('#mainManger').val();
				console.log(zpmanager);
				var selectedRaido = $('#manger').bootstrapTable('getSelections');
				//var check=0;
				var ids=[];
				var state=0;
				for (var i = 0; i <  selectedRaido.length; i++){
					ids[i]=selectedRaido[i].id;
					if(selectedRaido[i].dangerStatus=='0'||selectedRaido[i].dangerStatus=='2'||selectedRaido[i].dangerStatus=='1'||selectedRaido[i].dangerStatus=='4'||selectedRaido[i].dangerStatus=='5'){
						state=1;
					}
				}
				console.log(state);
				if(state==1){
					alert("选择待指派的记录！");
				}else {
					console.log("123");
					$.ajax({
						type: 'POST',
						url: "/admin/danger/zpnine",
						traditional: true,
						data: {
							jobNo: zpmanager,
							ids: ids
						},
						success: function (result) {
							$('#modal-tj-form').modal("hide");
							//$('#manger').bootstrapTable('refresh', {});

						},
						dataType: "json"
					});
					$('#modal-tj-form').modal("hide");
					console.log("123");
					//$('#manger').bootstrapTable('refresh', {});
				}
			});
			//初始化页面上面的按钮事件
			$("#hqbutton").click(function() {
					var selectedRaido = $('#manger').bootstrapTable('getSelections');
					var check=0;
				 	$.each(selectedRaido,function(n,value) {
						console.log("allcheck:"+check);
						if(value.dangerStatus!='0'){
							console.log("check:"+check);
							check=1;
						}

				   });
				console.log("acheck:"+check);
				if(check==1){
					alert('请选择需要会签的记录');
				}else if (selectedRaido.length === 0) {

					alert("请先选择要提交会签的记录！");
				}
				else {
					$('#hq').modal("show");
				}
				});
			$("#allData").click(function() {
				var selectedRaido = $('#manger').bootstrapTable('getSelections');
				if (selectedRaido.length === 0) {
					alert("请先选择要提交会签的记录！");
				} else {
					$('#hq').modal("show");
				}
			});
			$("#tjhq").click(function() {
				var selectedRaido = $('#manger').bootstrapTable('getSelections');
				var hqmanager=$('#hqmanager').val();
				var ids=[];
				for (var i = 0; i <  selectedRaido.length; i++){
					ids[i]=selectedRaido[i].id;
				}
				console.log(ids);
				$.ajax({
					type: 'POST',
					url: "hq",
					traditional: true,
					data: {
						hqmanager: hqmanager,
						ids:ids
					},
					success: function (result) {
						alert("提交成功");
						$('#manger').bootstrapTable('refresh',{});

					},
					dataType: "json"
				});

			});



		};

		return oInit;
	};

  ////初始化分公司老总
	//$.ajax({
	//	type: 'POST',
	//	url: "/findMangerLeader",
	//	data:{role:"1"},
	//	success: function (result) {
	//		$.each(result,function(n,value) {
	//			$("#mainManger").append("<option value='"+value.jobno+"'>"+value.name+"</option>");
	//			//var tn = '<a id="btn_archive" href="#" onclick="archiveDocumentList('+row.id+'\,\''+row.name+'\',\''+row.type+'\');">'+subname+'</a>';
  //
	//			showMenuPop(page2_jMenuPop2,'show');
  //
	//		});
  //
	//		//alert("登记成功！");
  //
  //
	//	},
  //
	//});

  //初始化分公司经理
	//初始化分公司老总
	//$.ajax({
	//	type: 'POST',
	//	url: "/findMangerLeader",
	//	data:{role:"0"},
	//	success: function (result) {
	//		$.each(result,function(n,value) {
	//			$("#manager").append("<option value='"+value.jobno+"'>"+value.name+"</option>");
	//			//var tn = '<a id="btn_archive" href="#" onclick="archiveDocumentList('+row.id+'\,\''+row.name+'\',\''+row.type+'\');">'+subname+'</a>';
    //
	//			showMenuPop(page2_jMenuPop2,'show');
    //
	//		});
    //
	//		//alert("登记成功！");
    //
    //
	//	},
    //
	//});





function statusFormatter(value, row, index) {
	if(value=='0'){
		return '待会签';
	}
	if(value=='1'){
		return '正在会签';
	}
	if(value=='2'){
		return '已会签';
	}
	if(value=='3'){
		return '总经理指派';
	}
	if(value=='4'){
		return '已整改';
	}

}


function actionFormatter(value, row, index) {
	console.log(row);
	var status = row;
	var res = '<button data-toggle="modal" class="btn btn-primary btn-sm btnsmall " style="10px" href="dangerManger.html#detail" data-row='+row.id+'>详情</button>';
	var zp = '<button data-toggle="modal" class="btn btn-primary btn-sm btnsmall " style="10px" href="dangerManger.html#modal-add-form" data-row='+row.id+'>指派</button>';
	var sh = '<button data-toggle="modal" class="btn btn-danger btn-sm btnsmall " style="10px" href="dangerManger.html#modal-tj-form" data-row='+row.id+'>指派负责人</button>';
	var hq = '<button data-toggle="modal" class="btn btn-danger btn-sm btnsmall " style="10px" href="dangerManger.html#modal-tj-form" data-row='+row.id+'>选择会签人</button>';
	var zjlzp = '<button data-toggle="modal" class="btn btn-primary btn-sm btnsmall " style="10px" href="dangerManger.html#modal-add-form" data-row='+row.id+'>总经理指派</button>';

	//指派状态
	if(status==0){
		return "待会签";
	}else if(status==1){
		return '<span>会签中</span>';
	}//完成
	else if(status==2){
		return zp;
	}else if(status==3){
		return zjlzp;
	}
	else if(status==4){
		return "整改中";
	}	else if(status==5){
		return "已整改";
	}
	var id = row.id;

	//res += ' <button typ-e="button" class="btn btn-success" onclick="showdetail(\'' + id + '\')">详情</button>';

}

function viewFormatter(value, row, index) {
     var res = '<button data-toggle="modal" class="btn btn-primary btn-sm btnsmall " style="10px" href="dangerManger.html#detail" data-row=' + row.id + '>详情</button>';
	return res;


}
function checkFormatter(value, row, index) {
	var res='<input name="checkAll" type="checkbox" value="" />';
	return res;
}


function opinionFormatter(value, row, index) {
	var status=row.dangerStatus;
	var hq=row.isHq;
	if ((status == 3 || status == 1||status == 0)&&hq==1) {
		var res = '<button data-toggle="modal" disabled=true class="btn btn-grey btn-sm btnsmall " style="10px" href="dangerManger.html#hq" data-row='+row.id+'>已会签</button>';
		return res;
	}else{
	var res = '<button data-toggle="modal" class="btn btn-primary btn-sm btnsmall " style="10px" href="dangerManger.html#hq" data-row='+row.id+'>会签</button>';
	return res;
   }

}

function levelFormatter(value, row, index) {
	if(value=='0'){
		return '延期整改';
	}
	if(value=='1'){
		return '立即整改';
	}


}

function deptFormatter(value, row, index) {
	if(value=='0'){
		return '地板';
	}
	if(value=='1'){
		return '门业';
	}
	if(value=='2'){
		return '衣柜';
	}
	if(value=='3'){
		return '胶粘剂';
	}
	if(value=='4'){
		return '公共';
	}

}

$('#hq').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget); // 触发事件的按钮
	var id = button.data('row');
	var modal = $(this);
	$("#dangerid").val(id);


})

$('#modal-tj-form').on('show.bs.modal', function (event) {

	$("#hqlist").empty();
	getMainManager("mainManger");
	var button = $(event.relatedTarget); // 触发事件的按钮
	var id = button.data('row');
	var modal = $(this);
	$.ajax({
		type: 'POST',
		url: "opinionList",
		data: {
			id: id,
		},
		success: function (result) {
			$.each(result,function(n,value) {

				$("#hqlist").append("<li  class=' form-control col-sm-8 hq'><span>"+value.viewTime+"</sapn>"+value.opinionManager+"已完成会签"+"</li>");

			});
		},
		dataType: "json"
	});
	$("#tid").val(id);


})

$('#modal-add-form').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget); // 触发事件的按钮
	var id = button.data('row');
	var modal = $(this);
	$("#id").val(id);


})

$('#detail').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget); // 触发事件的按钮
	var id = button.data('row');
	var modal = $(this);
	$.ajax({
		type: 'POST',
		url: "dangerDetail",
		data: {
			id: id,
		},
		success: function (result) {
			$("#before").attr("src",result.imgUrl);
			$("#after").attr("src",result.dealUrl);
			$("#des").val(result.dangerDescript);
			$("#res").val(result.dealWay);
			$("#passId").val(result.id);

		},
		dataType: "json"
	});


})

function pass(){
	$('#detail').modal('hide');




}

function back(){

	$("#yj").modal("show");



}

function backsure(){
	var id=$("#passId").val();
	$.post("back",{id:id,msg:"ceshi"},function(result){
		console.log(123);
		window.location.reload();
	});

}

function detailFormatter(value, row, index) {
	var res = '<a data-toggle="modal" style="10px" href="dangerManger.html#detail" data-row='+row.id+'>'+value+'</a>';

	var empstr="<a href='#' onclick=\"showDetail('"+row.imgUrl+"')\">"+value+"</a>";
	return res;
}

function showDetail(img){
	console.log(img);
	$("#imgDetail").attr("src",img);
	$("#detail").modal("show");
}

	function boot(){
		$('#manger').bootstrapTable('refresh', {query: {id:"1"}});
	}
	function mainPage(){

		$('#manger').bootstrapTable('refresh',{});
	}
	function floor(){
		$('#manger').bootstrapTable('refresh', {query: {id:"0"}});
	}
	function jnj(){
		$('#manger').bootstrapTable('refresh', {query: {id:"3"}});
	}function yg(){
		$('#manger').bootstrapTable('refresh', {query: {id:"2"}});
	}

	//确认会签
	function queren(){
		var key=[];
		$("#page4_jList1").each(function () {
			//var tmp;

			$(this).find('li').each(function() {

				var self = $(this).children('input');
				var id=self.eq(0).val();
				//tmp = $(this).text();

				key.push(id);
			});

		});
		alert(key.length);
		$.ajax({
			type: 'POST',
			url: "/admin/danger/addHq",
			traditional: true,
			data: {
				ids: key,
			},
			success: function (result) {


			},
			dataType: "json"
		});
		//遍历该数组可以获取所有值
		for (var i = 0 ; i < key.length; i++) {
			//todo
		}


	}

function  getMainManager(str){
	$("#"+str).empty();
	$.ajax({
		type: 'POST',
		url: "/admin/danger/getManager",
		data: {
			role:"0"
		},
		success: function (result) {
			$.each(result,function(n,value) {
				$("#"+str).append("<option value='"+value.jobNo+"'>"+value.name+"</option>");

			});

		},
		dataType: "json"
	});



}