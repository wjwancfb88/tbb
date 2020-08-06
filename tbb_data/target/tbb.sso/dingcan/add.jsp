<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<html data-dpr="1" style="font-size: 300px;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <title>小餐厅预订</title>

  <link rel="stylesheet" type="text/css" href="../css/reset.css">
  <link rel="stylesheet" type="text/css" href="../css/all.css">
  <link rel="stylesheet" type="text/css" href="../css/mobiscroll.core.css">
  <link rel="stylesheet" type="text/css" href="../css/mobiscroll.animation.css">
  <link rel="stylesheet" type="text/css" href="../css/mobiscroll.android-ics.css">

  <link rel="stylesheet" type="text/css" href="../css/resetPC.css">

  <script type="text/javascript" src="../js/jquery-2.1.0.min.js"></script>
  <script type="text/javascript" src="../js/mobiscroll.core.js"></script>
  <script type="text/javascript" src="../js/mobiscroll.datetime.js"></script>
  <script type="text/javascript" src="../js/mobiscroll.android-ics.js"></script>
  <script type="text/javascript" src="../js/xtjs.js"></script>
  <script type="text/javascript" src="../js/util.js"></script>
  <script type="text/javascript" src="../js/meet.js"></script>

  <script>
    var __globalData = {
      appId: '',
      lightAppId: '',
      userName: '张三',
      eid: '',
      openId: '',
      personId: ''
    };
  </script>
  <link rel="stylesheet" type="text/css" href="../css/person-select.css"></head>
<body class="bg-write body-bg">

<div class="topW2">
  <div class="top">
    <div class="top_left"><a href="javaScript:;" onclick="location.href='/reservations/dingcan.do'"><img src="../images/wp_01.png"></a></div>
    <div class="top_center">小餐厅预订</div>
    <div class="top_right panes02">
      <div><a href="javaScript:;" onclick="location.href='/reservations/dingcan.do'"><img src="../images/x.png"></a></div>
    </div>
  </div>
  <div class="clear"></div>
</div>


<div id="userinfo-ps">提交中，请稍后...</div>
<!-- 填写会议信息 -->
<div class="meet-panel">
  <!-- <div class="title-input-wrap input-group">
      <input class="meeting-input meeting-theme" maxlength="80" type="text" placeholder="" />
      <span class="placeholder" data-top-tips="会议主题">请输入会议主题</span>
  </div> -->

  <div class="textarea-wrap end-time-wrap input-group input-margint-top">
    <textarea class="meeting-textarea meeting-content" placeholder="告诉餐厅你有什么要求"></textarea>
    <!-- <span class="placeholder" data-top-tips="会议内容">告诉大家会议概要内容和需要准备的资料...</span> -->
    <!-- <div class="limit-tips">已输入 <span class='limit-num'>0</span> 字</div> -->
  </div>

  <div class="address-input-wrap input-group">
    <input class="meeting-input meeting-address" maxlength="80" type="text" placeholder="请输入就餐人数">
    <!-- <span class="placeholder" data-top-tips="会议地点">请输入会议地点</span> -->
  </div>

  <div class="set-time">
    <div class="end-time-wrap input-group input-margint-top">
      <input class="set-time-btn meeting-input" type="text" id="start-time" name="start-time" readonly>
      <p class="placeholder in-top">预订时间</p>
      <img src="images/right-arrow1.png">
    </div>
  </div>

  <!-- <div class="set-time">
      <input class="set-time-btn " type="datetime-local" id="end-time" name="end-time">
      <div class="end-time-wrap input-group input-margint-top">
          <input class="set-time-btn meeting-input" type="datetime-local" id="end-time" name="end-time" />
          <p class="placeholder in-top">结束时间</p>
      </div>
  </div> -->
  <p class="send-a-msg clearfix">
    <!-- <img src="/clout/images/new/tel-shake.png" alt="手机" class="tel-shake"/>
    <img src="/clout/images/new/tel-grey.png" alt="手机" class="tel-grey"/> -->
    <%--<b class="reminders-icon reminders-icon-bg"></b>--%>
    <span style="margin-left:0px">黄泽民 13819241257</span>
  </p>

  <a class="btn btn-primary" id="startNow" href="javascript:void(0);">预订</a>
  <div class="height10"></div>

</div>

<script type="text/javascript">
  (function(){
    var dpr = window.devicePixelRatio;
    if(window.devicePixelRatio >= 1.5) {
      dpr = 2;
    }
    document.documentElement.setAttribute('data-dpr', dpr);
    document.documentElement.style.fontSize = document.documentElement.clientWidth / 6.4 + 'px';
  })();

  meet.init();
</script><script type="text/javascript" src="js/person-select.js"></script>


</body></html>