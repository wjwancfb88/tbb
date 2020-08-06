<html>
<head>

  <meta charset="UTF-8">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
  <title>��ѧ����ת</title>

  <style>
    html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,embed,figure,figcaption,footer,header,hgroup,menu,nav,output,ruby,section,summary,time,mark,audio,video{margin:0;padding:0;border:0;font-size:100%;font:inherit;vertical-align:baseline}article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{display:block}body{line-height:1}ol,ul{list-style:none}blockquote,q{quotes:none}blockquote:before,blockquote:after,q:before,q:after{content:'';content:none}table{border-collapse:collapse;border-spacing:0}

  </style>

  <script src="jquery-1.11.1.js"></script>
  <script src="https://do.yunzhijia.com/pub/js/qingjs.js"></script>
  <script src="/laydate-v1.1/laydate/laydate.js"></script>
  <script>
    $(function() {
      var token="${key}";
      var useagent = (navigator.userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/) ? 'iOS' :
              navigator.userAgent.match(/Android/i) ? 'Android' : '' );
      if (useagent == "Android") {
        var aurl = "wisecrm://login/tokenLogin?url=https://www.365.wisecrm.com/pub/a/1036&token=" + token + "&serverUrl=http://crm.tubaobao.com";
        XuntongJSBridge.call('gotoApp', {
          "data": aurl
        }, function (result) {

        });
      }else{
        var url="com.wisecrm.mobile.tubaobao://token="+token+"&serverUrl=http://crm.tubaobao.com&url=https://www.365.wisecrm.com/pub/a/1037";
        XuntongJSBridge.call('gotoApp', {
          "data": url
        }, function (result) {

        });
      }
      setTimeout("backyun()","6000");

    });

    function backyun(){
      XuntongJSBridge.call('closeWebView');
    }
  </script>

</head>
<body>
<a id="test2"></a>
</body>
</html>
