(function ($, myWindow) {
    var $z = {

        LOGIN_FAIL_CODE: 101,
        PLEASELOGIN_CODE: 102,
        SUCCCSS_CODE: 200,
        FAIL_CODE: 500,
        /**
         * 处理返回信息的公共方法
         */
        dealCommonResult: function dealCommonResult(data, func) {

            if (data.code == $z.SUCCCSS_CODE) {
                func();
            } else if (data.code == $z.LOGIN_FAIL_CODE) {
                $.MsgBox.Alert("错误", "用户名或密码错误");
            } else if (data.code == $z.PLEASELOGIN_CODE) {
                window.location.href = basePath + "/login";
            } else if (data.code == $z.FAIL_CODE) {
                $.MsgBox.Alert("错误", data.msg);
                // 进度条消失
                MprogressEnd();
            }
        },
        // RequestBody接收 async(是否异步)
        ajaxStrAndJson: function ajaxStrAndJson(allData) {
            $.ajax({
                url: allData.url,
                type: "post",
                headers: {
                    Accept: "application/json; charset=utf-8"
                },
                async: allData.async,
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(allData.data),
                beforeSend: allData.beforeSend,
                success: allData.success,
                complete: function (XMLHttpRequest) {
                },
                error: function (data, status, e) {
                    $.MsgBox.Alert("错误", "服务器连接失败");
                }
            });
        },
        // 普通参数或对象接收
        ajaxFormAndJson: function ajaxFormAndJson(allData) {
            $.ajax({
                url: allData.url,
                type: "post",
                headers: {
                    Accept: "application/json; charset=utf-8"
                },
                async: allData.async,
                data: allData.data,
                beforeSend: allData.beforeSend,
                success: allData.success,
                complete: function (XMLHttpRequest) {
                },
                error: function (data, status, e) {
                    $.MsgBox.Alert("错误", "服务器连接失败");
                }
            });
        },
        downLoadExcel: function downLoadExcel(filePath) {
            // alert(filePath);
            window.parent.location.href = '/excel/downloadfile?filePath='
                + encodeURI(filePath);
        }

    };
    myWindow.$z = $z;
})(jQuery, window);