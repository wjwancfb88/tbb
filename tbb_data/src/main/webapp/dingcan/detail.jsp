<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <element x-ms-format-detection="none"/>
    <meta name="viewport" content="width = device-width,initial-scale=1">
    <meta name=keywords content="">
    <meta name=description content="">
    <title>餐厅预订</title>
    <link href="../css/css.css" rel="stylesheet" type="text/css" />
    <script src="../js/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="../js/topfixed.js"></script>
    <script type="text/javascript" src="../js/tabs.js"></script>
    <script type="text/javascript" src="../js/tab.js"></script>
    <script type="text/javascript" src="../js/jquery.divbox.js"></script>
    <script type="text/javascript" src="../js/divbox.js"></script>

    <!--[if lte IE 6]>
    <script src="../js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
    <script type="text/javascript">
      DD_belatedPNG.fix('div, dl, dt, dd, ul, img, li, input , a');
    </script>
    <![endif]-->
</head>
<body>
<div class="topW2">
  <div class="top">
    <div class="top_left"><a href="javascript:void(0)"  onclick="location.href='/reservations/dingcan.do'"><span class="btnl">&lt;</span>返回</a></div>
    <div class="top_center">预订详情</div>
    <div class="top_right panes02">
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="detail_main01">
  <div class="notice_title"><a href="detail.html">共8人</a> </div>
  <div class="notice_date">04/07 上午 -04/28 下午<img src="../images/approval_01.png" width="45" height="45"></div>
  <div class="notice_content">等待负责人审批…</div>
</div>
<%--<div class="detail_h10"></div>--%>
<%--<div class="detail_main02">--%>
  <%--<div class="detail_date">04/07--%>
    <%--12:07</div>--%>
  <%--<div class="detail_img"><img src="../images/approval_10.png"></div>--%>
  <%--<div class="detail_right">我的预订</div>--%>
<%--</div>--%>
<%--<div class="detail_h10"></div>--%>
<%--<div class="detail_main02">--%>
  <%--<div class="detail_date">04/07--%>
    <%--12:07</div>--%>
  <%--<div class="detail_img"><img src="../images/approval_10.png"></div>--%>
  <%--<div class="detail_right agree">李四已同意</div>--%>
<%--</div>--%>
<div class="clear"></div>
<div class="h45"></div>
<div class="bottomW">
  <div class="bottom">
    <ul>
      <li class="bgline"><a href="javaScript:;" class="f1">不同意</a></li>
      <li><a href="javaScript:;">同意</a></li>
    </ul>
  </div>
</div>

<!--弹出同意开始-->
<div class="agreebox">
  <div class="agreebox_title">请输入批复</div>
  <div class="agreebox_conent">
    <input type="text" class="text03" title=""/>
  </div>
  <div class="agreebox_bottom">
    <ul>
      <li><span onClick="closeDiv()">取消</span></li>
      <li><a href="javaScript:;">同意</a></li>
    </ul>
  </div>
</div>
<!--弹出同意结束-->
</body>
</html>
