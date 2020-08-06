
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

	function getVistor() {
		var vphone = $("#vphone").val();
		var flag = $("#flag").val();
		$.ajax({
			type:'GET',
			url:"../entry/selectByVistor",
			data:{vphone:vphone,flag:flag},
			dataType:"json",
			contentType:'text/json,charset=utf-8',
			success:function (entry) {
				console.log(entry);
				$("#plateNo").val(entry.plateNo);
				$("#vistorname").val(entry.vistor);
				$("#idCard").val(entry.idCard);
				// $("#vphone").val(entry.vistorPhone);
			}
		})
	}


   function vistor(){
		var vistor=$("#vistorname").val();
	    var phone=$("#phone").val();
	   var dept=$("#dept").val();
	   	var username=$("#username").val();
	   	var vistortime=$("#vistortime").val();
	   	var reason=$("#reason").val();
	   var vphone=$("#vphone").val();
	   var plateNo=$("#plateNo").val();

	   $.ajax({
	   	type: 'POST',
	   	url: "../entry/add",
		data:{plateNo:plateNo,dept:dept,vistorname:vistor,phone:phone,username:username,vistortime:vistortime,reason:reason,vphone:vphone},
	   	dataType: "json",
		contentType: 'text/json,charset=utf-8',
		   success: function (result) {
			$("#vistorname").val("");
			$("#phone").val("");
			$("#dept").val("");
			$("#username").val("");
			$("#vistortime").val("");
			$("#reason").val("");
			$("#vphone").val("");
			alert("登记成功！出入园区注意事项：" +
				"第一条 未经许可进入园区的出租车、三轮车；携带猫、狗、鸟、鸽及其它动物者；携带易燃、易爆等国家法律明令禁止的物品者，保安有权不让进入园区。" +
				"第二条 外来人员进入园区，须在门卫智能扫码登记（详见附件附件一），经允许后才能进入园区；来访人员离开时需在门卫结束登记流程。" +
				"第三条 在工作时间（上午8:00-11:00，下午13:00-17:00），园区内各公司人员外出需凭出入证进出园区，以统一书面形式凭各公司主管领导签字后方可有效，无出入证的员工门卫一律不予放行（详见附件二）。" +
				"第四条 员工入园上班，不准穿背心、不准穿短裤，不准穿拖鞋。园区内的企业员工统一着兔宝宝工作服，按公司着装要求规定穿戴，有不按规定着装，视情节轻重每次处以50-100元罚款。" +
				"第五条 园区内部员工车辆进入园区，须在门卫录入道闸系统登记（详见附件三），且必须按规定路线行驶，在指定地点停车。外来车辆进入园区须在门卫智能扫码登记后方能进入园区（详见附件一）。无牌、无证、非法车辆严禁进入园区，值班人员有责任对进入园区的此类车辆做报警处理。对超高、超重、超长的车辆不能进入园区；若入园车辆损坏园区设施的，要进行赔偿或恢复原状。在指定地点停车，车辆出门时安保人员有权检查车辆及后备箱。严禁任何人在公共道路和停车区域进行有碍交通或危害停放车辆安全的活动。" +
				"第六条 进出园区车辆实行限速管理，园区内禁止鸣笛，如有违反规章制度的，一经发现将扣款10-50元（详见附件六第三章第十一条）。" +
				"第七条 对违规行车和停放的车辆，安保人员有权采取警告、拖车等措施，对屡教不改或拒绝改正的员工车辆杜绝该车再次驶入园区。" +
				"第八条 公共车辆出园区要凭《公车使用申请审批单》，运输车辆需出具由各分公司相关领导签字确认的出入联系单经门卫检查核实后，方能放行（详见附件四）。" +
				"第九条 所有要搬离园区的物品，必须由使用部门出具放行凭证，并由经办人与所在部门主管以上签字，经值班人员核对物品名称及数量后方可放行。" +
				"第十条 安保人员每日定时（白班：上午、下午各一次；晚班：每两小时巡查一次）、定点（危化品仓库、配电房等重要场所）进行巡查，发现安全隐患及时排除。对防盗意识淡薄，心存侥幸心理的人员，将给予批评教育。对于屡教不改、麻痹大意造成严重安全隐患甚至被盗案件的，将给予责任人追究责任。" +
				"第十一条 下班时门窗、电器必须关闭，行政及生产各部门、组须由相关人员负责下班后门窗、电器的关闭。" +
				"第十二条 进出园区的基建外包工作人员需要出具园区管理中心提供的有效施工证方可进出");


	   	}

	   })


   }


	function findEmp(){
		var name=$("#username").val();
		$("#namelist").empty();
		$.ajax({
			type: 'POST',
			url: "../user/findByName",
			data:{name:name},
			success: function (result) {
				$.each(result,function(n,value) {
					var depts=[];
					var   dept;
					if (value.department != null) {
						depts= value.department.split("\\");
						dept=depts[depts.length-1]
					}else{
						depts= "";
						dept="";
					}
					var name=value.name;
					dept=dept+"T"+name;
					console.log(value.department);
					$("#category").append("<option value='"+value.id+"'>"+value.name+"</option>");
					$("#namelist").append('<li id="'+value.id+'"class="mui-table-view-cell"><a href="#" onclick="fill('+value.phone+'\,\''+dept+'\')">'+value.name+'</a></li>')

					//var tn = '<a id="btn_archive" href="#" onclick="archiveDocumentList('+row.id+'\,\''+row.name+'\',\''+row.type+'\');">'+subname+'</a>';

					showMenuPop(page2_jMenuPop2,'show');

				});

				//alert("登记成功！");


			},

		})






	}
	//mui('#page2_jEdit7')[0].addEventListener('tap', function(e) {
	//	showMenuPop(page2_jMenuPop2,'show');
	//});

	function showMenuPop(domquery, state){
		mui(domquery).popover(state);
	}
	function fill(phone,department){
		var depts=[];
		depts=department.split("T");
		$("#phone").val(phone);
		$("#dept").val(depts[0]);
		$("#username").val(depts[1]);
		showMenuPop(page2_jMenuPop2,'hide');

	}
	$(function () {
		$("#connect_redirect").val("1");

//去掉定时器的方法
//	window.clearInterval(t2);


	});
	function closeweb(){
		open(location, '_self').close();
		window.location.href="about:blank";
		window.close();
	}