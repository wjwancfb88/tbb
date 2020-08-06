



/*******用户相关********/
var deletebox= new Array();
var addbox= new Array();
var deleteAddress= new Array();
var addAddress= new Array();

$(document).keyup(function(event){
    if(event.keyCode ==13){
        $("#search").trigger("click");
    }
});

function changecategory(){
    var parentId=$("#type").val();
    $.ajax({
        type: 'POST',
        url: "../equipmentType/queryById",
        dataType: "json",
        data:{parentId:parentId},
        success: function (result) {
            $("#category").val("");
            $("#category").empty();

            $.each(result,function(n,value) {

                $("#category").append("<option value='"+value.id+"'>"+value.name+"</option>");

            });

        },

    });

}

function updatecategory(){
    var parentId=$("#type1").val();
    $("#category1").empty();
    $.ajax({
        type: 'POST',
        url: "../equipmentType/queryById",
        dataType: "json",
        data:{parentId:parentId},
        success: function (result) {
            $("#category1").val("");
            $("#category1").empty();

            $.each(result,function(n,value) {

                $("#category1").append("<option value='"+value.id+"'>"+value.name+"</option>");

            });

        },

    });

}

$(function () {
    //$("#save").click(function() {
    //    $('#add').data('bootstrapValidator').validate();
    //console.log(${total});});

    $('.form_datetime').datetimepicker({
        format: 'yyyy-mm-dd',
        minView: "month",
        language: 'zn', //汉化
        todayBtn: 1,
        icon: 'row-right',
        autoclose: true
        //选择日期后自动关闭
    });

    $.ajax({
        type: 'POST',
        url: "../equipmentType/type",
        dataType: "json",
        success: function (result) {
            $.each(result,function(n,value) {
                $("#type").append("<option value='"+value.id+"'>"+value.name+"</option>");
                $("#type1").append("<option value='"+value.id+"'>"+value.name+"</option>");
                $(".equipmentType").append("<option value='"+value.id+"'>"+value.name+"</option>");
            });

        },

    });

    $.ajax({
        type: 'POST',
        url: "../equipmentType/queryAll",
        dataType: "json",
        data:{type:"s"},
        success: function (result) {
            $("#equipmentCategory").val("");
            $.each(result,function(n,value) {
                $("#equipmentCategory").append("<option value='"+value.id+"'>"+value.name+"</option>");
                $(".equipmentCategory").append("<option value='"+value.id+"'>"+value.name+"</option>");
            });

        },

    });


    $.ajax({
        type: 'POST',
        url: "../equipmentType/queryAll",
        dataType: "json",
        data:{type:"l"},
        success: function (result) {
            $(".equipmentBelong").val("");
            $.each(result,function(n,value) {
                $(".equipmentBelong").append("<option value='"+value.id+"'>"+value.name+"</option>");

            });

        },

    });

    $.ajax({
        type: 'POST',
        url: "../equipmentAddress/address",
        dataType: "json",
        success: function (result) {
            $.each(result,function(n,value) {
                if (value.name == "武康兔宝宝总部"){
                    $("#offAddress").append("<option value='"+value.id+"' selected='selected'>"+value.name+"</option>");
                }else{
                    $("#offAddress").append("<option value='"+value.id+"'>"+value.name+"</option>");
                }
                $("#offAddress1").append("<option value='"+value.id+"'>"+value.name+"</option>");
                $(".equipmentAddress").append("<option value='"+value.id+"'>"+value.name+"</option>");
            });

        },

    });


    $('#add').bootstrapValidator({

        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            //equipmentName: {
            //    message: '设备名验证失败',
            //    validators: {
            //        notEmpty: {
            //            message: '设备品牌不能为空'
            //        }
            //    }
            //},
            //code: {
            //    validators: {
            //        notEmpty: {
            //            message: '设备编码不能为空'
            //        }
            //    }
            //},
            //dept: {
            //    validators: {
            //        notEmpty: {
            //            message: '部门不能为空'
            //        }
            //    }
            //}, modal: {
            //    validators: {
            //        notEmpty: {
            //            message: '设备型号不能为空'
            //        }
            //    }
            //}
        }
    });

    $('#update').bootstrapValidator({

        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            equipmentName: {
                message: '设备名验证失败',
                validators: {
                    notEmpty: {
                        message: '设备名不能为空'
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


});

function searchtable(){
    queryList();

}


function addrepair(){


    $("#repair").modal("show");



}
var type=""
function typeFormatter(value, row, index){
    var status=row.type;

    $.ajax({
        type: 'POST',
        url: "../equipmentType/selectById",
        dataType: "json",
        async: false,
        data:{id:status},
        success: function (result) {

          $.each(result,function(n,value) {
               type=value.name;

            });

        },

    });

    return type;

}


function codeFormatter(value, row, index) {
    var empstr="<a href='#' onclick=\"showEquipment('"+row.id+"')\">"+value+"</a>";
    return empstr;
}


function showEquipment(data){
    var temp = "";
    $.ajax({
        type: 'POST',
        url: "list",
        data: {
            id: data,
        },
        success: function (result) {
            $.ajax({
                type: 'POST',
                url: "../equipmentAddress/selectById",
                dataType: "json",
                async: false,
                data:{id:result.records[0].offAddress},
                success: function (result) {
                    $.each(result,function(n,value) {
                        temp=value.name;
                    });
                },
            });

            $('#equipmentName_d').html(result.records[0].name);
            $('#modal_d').html(result.records[0].modal);
            $('#code_d').html(result.records[0].code);
            $('#ipAddress_d').html(result.records[0].ipAddress);
            $('#offAddress_d').html(temp);
            var date = result.records[0].buyDate;

            if (date != null) {
                var buydate = date.split("T")[0];
                $('#date_d').html(buydate);
            }

            if (result.records[0].guaranty != null) {

            var guarantys = result.records[0].guaranty;
            var guaranty = guarantys.split("T")[0];
            $('#guaranty_d').html(guaranty);
             }
            var depts=[];
            var   dept=result.records[0].department;

            if (dept != null) {

                depts= dept.split("\\");
                dept=depts[depts.length-1]
                $ ('#departmentName_d').html(dept);
            }
            selectByType(result.records[0].type,'type');
            selectByType(result.records[0].category,'category');
            selectByType(result.records[0].belong,'belong');
            console.log("type"+type);
            var status=result.records[0].status;
            if(status=="0"){
                $ ('#status_d').html("闲置");
            }
            if(status=="1"){
                $ ('#status_d').html("领用");
            }
            if(status=="2"){
                $ ('#status_d').html("公用");
            }
            if(status=="3"){
                $ ('#status_d').html("个人");
            }
            if(status=="4"){
                $ ('#status_d').html("个人补贴");
            }
            if(status=="5"){
                $ ('#status_d').html("闲置未报废");
            }
            if(status=="6"){
                $ ('#status_d').html("闲置可调拨");
            }
            $ ('#type_d').html(selectByType(result.records[0].type));
            $ ('#user_d').html(result.records[0].username);
            $ ('#belong_d').html(result.records[0].belong);
            $ ('#price_d').html(result.records[0].price);
            $ ('#remarks_d').html(result.records[0].remark);

        },
        dataType: "json"
    });

    $ ('#modal-equipment').modal("show");


}
function sexFormatter(value, row, index) {
    if (value == 1)
        return "男";
    else if (value == 2)
        return "女";
    else
        return "未知";
}

function dateFormatter(value, row, index) {
    if (value != null && value != "") {
        var dates = value.split("T");
        return dates[0];
    } else {
   return "-"
   }
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


function empFormatter(value, row, index){
    //console.log(row);
    //console.log(index);
    //console.log(row.user);
    //console.log(row[index].user);
    var empstr="<a href='#' onclick=\"showEmp('"+row.user+"')\">"+value+"</a>";
    var empstring="<a href='#' onclick='showEmp("+row.user+")'>"+value+"</a>";
    return empstr;

}




function statusFormatter(value, row, index){
    var status=row.status;
    if(value==0){
        return "闲置";
    }else if(value==1){
        return "领用";
    }else if(value==2){
        return "公用";
    }else if(value==3){
        return "个人";
    }else if(value==4){
        return "个人补贴";
    }else if(value==5){
        return "闲置未报废";
    }else if(value==6){
        return "闲置可调拨";
    }
}


function addressFormatter(value, row, index){
    var name = "";
    var offAddress=row.offAddress;

    $.ajax({
        type: 'POST',
        url: "../equipmentAddress/selectById",
        dataType: "json",
        async: false,
        data:{id:offAddress},
        success: function (result) {

            $.each(result,function(n,value) {
                if(value.name=="-" || value.name==""){
                    name="-";
                }else{
                    name=value.name;
                }
            });

        },

    });
    return name;
}

function erwi(){
    jQuery('#code').empty();
    $('#code').qrcode("www.baidu.com");
}

function actionFormatter(value, row, index) {
    var state = row.state;
    var id = row.id;
    var flag = $("#flag").val();
    var res = '<button data-toggle="modal" class="btn btn-primary btn-sm btnsmall " style="10px" href="list.html#modal-form" data-row='+id+'>修改</button>';
    //res += '<button data-toggle="modal" class="btn btn-primary btn-sm btnsmall " style="10px" href="list.html#modal-equipment" data-row='+id+'>生成二维码</button>';
    if(flag == "admin"){
        res += ' <button typ-e="button" class="btn btn-danger  btn-sm btnsmall" style="10px" onclick="deleteOne(\'' + id + '\')">删除</button>';
    }
    //res += ' <button typ-e="button" class="btn btn-success" onclick="showdetail(\'' + id + '\')">详情</button>';
    return res;
}



function changeSate(id, state) {
    $.ajax({
        type: 'POST',
        url: "changeState",
        data: {
            id: id,
            state: state
        },
        success: function () {
            $('#userTable').bootstrapTable('refresh', {url: '/admin/user/list'});
        },
        dataType: "json"
    });
}

function closeModal() {

    $('#modal-form').modal('hide');


}
function closeModaladd() {

    $('.modal .fade').modal('hide');


}

function deleteOne(id) {
    if(confirm("真的要删除吗?")){
        $.ajax({
            type: 'POST',
            url: "deleteOne",
            data: {
                id: id,
            },
            success: function (status) {
                if (status == 1) {
                    layer.msg('删除成功', {icon: 1});
                    $('#userTable').bootstrapTable('refresh');
                }else {
                    layer.msg('删除失败', {icon: 1});
                }
            },
            dataType: "json"
        });
    }
    else{
        // alert("点击了取消按钮");
    }
    //layer.confirm('您确定要删除此行数据？', {
    //    btn: ['确定','取消'] //按钮
    //}, function(){
    //    console.log("into delete");
    //    $.ajax({
    //        type: 'POST',
    //        url: "deleteOne",
    //        data: {
    //            id: id,
    //        },
    //        success: function (status) {
    //            if (status == 1) {
    //                layer.msg('删除成功', {icon: 1});
    //                $('#userTable').bootstrapTable('refresh');
    //            }else {
    //                layer.msg('删除失败', {icon: 1});
    //            }
    //        },
    //        dataType: "json"
    //    });
    //
    //}, function(){
    //    layer.msg('您已取消操作', {
    //        time: 1000, //20s后自动关闭
    //        btn: ['明白了', '知道了']
    //    });
    //});

}

/**
 * 为模态框填充数据
 */
$('#modal-form').on('show.bs.modal', function (event) {



    var button = $(event.relatedTarget); // 触发事件的按钮
    var id = button.data('row');
    var modal = $(this);
    $.ajax({
        type: 'POST',
        url: "getEquipmentById",
        data: {
            id: id
        },
        success: function (data) {
            $ ('#user1').empty();
            modal.find('#id').val(data.id);
            modal.find('#equipmentName').val(data.name);
            modal.find('#modal').val(data.modal);
            modal.find('#type1').val(data.type);
            modal.find('#category1').val(data.category);
            modal.find('#departmentName1').val(data.department);
            modal.find('#price').val(data.price);
            modal.find('#remarks').val(data.remark);
            modal.find('#belong1').val(data.belong);
            var datae=[];
            if(data.buyDate!=null){
                dates=data.buyDate.split("T")
            }else{
                dates=[""]
            }
            var guarantys=[];
            if(data.guaranty!=null){
                guarantys=data.guaranty.split("T")
            }else{
                guarantys=[""]
            }
            var guaranty=guarantys[0];
            var date=dates[0];
            modal.find('#code').val(data.code);
            modal.find('#ipAddress').val(data.ipAddress);
            // var offAddress = "";
            // $.ajax({
            //     type: 'POST',
            //     url: "../equipmentAddress/selectById",
            //     dataType: "json",
            //     async: false,
            //     data:{id:data.offAddress},
            //     success: function (result) {
            //         $.each(result,function(n,value) {
            //             offAddress=value.name;
            //         });
            //     },
            // });
            modal.find('#offAddress1').val(data.offAddress);
            modal.find('#date').val(date);
            modal.find('#guaranty').val(guaranty);
            modal.find('#user1').text(data.username);
            modal.find('#user1').html(data.username);
            modal.find('#user1').val(data.username);
            $ ('#user1').append("<option value='"+data.user+"'>"+data.username+"</option> ");
            modal.find('#status1').val(data.status)
            var parentId=$("#type1").val();

            $("#category1").empty();
            $.ajax({
                type: 'POST',
                url: "../equipmentType/queryById",
                dataType: "json",
                data:{parentId:parentId},
                success: function (result) {
                    $.each(result,function(n,value) {

                        $("#category1").append("<option value='"+value.id+"'>"+value.name+"</option>");

                    });
                    $("#category1").val(data.category);
                },

            });
            console.log(data.category);
            $("#category1").val(data.category);


        },
        dataType: "json"
    });


})


/**
 * 列表单行点击事件
 */
// $('#userTable').on('click-row.bs.table', function (row, $element) {
//     alert(row);
// })
function showEmp(data){
    $.ajax({
        type: 'POST',
        url: "list",
        data: {
            user: data,
        },
        success: function (result) {
            $ ('#empname').html(result.records[0].username);
            $ ('#empdept').html(result.records[0].department);
            $ ('#empeq').html(result.records.length+"台");
           console.log();
        },
        dataType: "json"
    });
    $ ('#empname').html();
    $ ('#modal-emp').modal("show");


}
//function showEmp(){
//
//    console.log(123);
//
//}

function selectByType(id,node){

    $.ajax({
        type: 'POST',
        url: "../equipmentType/selectById",
        data: {
            id: id,
        },
        success: function (result) {
            console.log(result[0].name);
            $ ('#'+node+'_d').html(result[0].name);
        },
        dataType: "json"
    });

}