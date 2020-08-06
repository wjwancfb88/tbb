<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" pageEncoding="utf-8" />
    <element x-ms-format-detection="none"/>
    <meta name="viewport" content="width = device-width,initial-scale=1">
    <meta name=keywords content="">
    <meta name=description content="">
    <title>订餐系统</title>
    <link href="../css/css.css" rel="stylesheet" type="text/css" />
    <script src="../js/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="../js/topfixed.js"></script>
    <script type="text/javascript" src="../js/tabs.js"></script>
    <script type="text/javascript" src="../js/tab.js"></script>
    <script type="text/javascript" src="../js/dingcan.js"></script>

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
    <div class="top_left"><a href="javaScript:;"><span class="btnl">&lt;</span>应用</a></div>
    <div class="top_center">订餐首页</div>
    <div class="top_right panes02">
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="leave_main">
<c:forEach items="${navigatemap.TAB}" var="indexNavigate" varStatus="index">
  <div class="notice">
    <div class="notice_title"><a href="javascript:void(0)" onclick="location.href='/reservations/detail.do'">2017/12/16 </a></div>
    <div class="notice_date"><a href="javascript:void(0)" onclick="showDetail()">共8人</a></div>
    <div class="notice_content"><a href="javascript:void(0)"onclick="showDetail()">等待负责人确认…</a></div>
  </div>
  </c:forEach>
</div>


<div class="clear"></div>
<div class="h45"></div>
<div class="bottomW">
  <div class="bottom">
    <ul>
      <li class="bgline"><a href="javascript:void(0)" onclick="location.href='/reservations/addOrder.do'">我要预订</a></li>
      <li><a href="count.html">历史预订</a></li>
    </ul>
  </div>
</div>
</body>
</html>
