(function (window, $, undefined) {
    var meet = {
    	init: function() {
    		// 移动端隐藏右上角按钮
			if(this.getOS() !== ''){
				XuntongJSBridge && XuntongJSBridge.call('hideOptionMenu');	
			}
			// 桌面端加载选人组件
			else if(this.isCloudhub()){
				this.loadCloudhubPersonSelect();
			}
			// web端加载选人组件
			else{
				this.loadWebPersonSelect();
			}
			
    	    this.initDatePlugin();
    	    this.bindEvt();
            this.inputAnimate();
            this.stopClickThrough();
    	},
    	
    	//免费短信通知阻止点击穿透
    	stopClickThrough: function(){
    		$('.dwb-c span').on('click',function(e){
        		alert(2)
    			e.preventDefault();
    		})
    	},
        getOS : function(){  //获取操作系统平台，IOS或安卓
            var userAgent = navigator.userAgent;
            return userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)
                ? 'ios' : userAgent.match(/Android/i)
                ? 'android' : '';
        },      
        
        // 判断是否桌面端
        isCloudhub: function(){
        	var userAgent = navigator.userAgent;
        	return /App\/cloudhub/.test(userAgent);
        }, 
        
        // 判断是否web端
        isWebClient: function(){
        	return this.getOS() === '' && !this.isCloudhub();
        },
		
		// 加载web端选人组件资源
		loadWebPersonSelect: function(){
			Util.loadCss('css/person-select.css?201511091500');
			Util.loadJs('js/person-select.js?201511091500');
		},
		
		// 加载桌面端选人组件资源
		loadCloudhubPersonSelect: function(){
			Util.loadCss('css/person-select.css?201511091500');
			Util.loadJs('js/person-select-cloudhub.js?201511091500');
		},
		
        initDatePlugin: function(){
        	if (!Util.checkMobileSystem('ios')) {
        		//android
                $("#start-time").attr("type","text");
                //$("#end-time").attr("type","text");
        	    var currYear = (new Date()).getFullYear();	
				var opt={};
				opt.date = {preset : 'date'};
				opt.datetime = {preset : 'datetime'};
				opt.time = {preset : 'time'};
				opt.defaults = {
					theme: 'android-ics light', //皮肤样式
			        display: 'modal', //显示方式 
			        mode: 'scroller', //日期选择模式
					lang:'zh',
			        startYear:currYear, //开始年份
			        endYear:currYear + 1 //结束年份
				};
	
			  	var optDateTime = $.extend(opt['datetime'], opt['defaults']);
			  	$("#start-time").mobiscroll(optDateTime).datetime(optDateTime);
			    //$("#end-time").mobiscroll(optDateTime).datetime(optDateTime);	
        	}
        	
        	var starttimeVal = Util.conversInputTime(new Date(),1800000);
            
            if (Util.checkMobileSystem('ios')) {
                starttimeVal = starttimeVal.replace(/\//g,'-').replace(/\s/g, 'T');
            }
            
    		$("#start-time").prop("value", starttimeVal);
    	},

    	bindEvt: function(){ 
    		var me = this;
    		
            //textarea输入字数提醒
            $('.meeting-textarea').on('input', function(){
                var text = $(this).val().trim(),
                    currLen = text.length;
                
                $('.limit-num').html(currLen);
            });

            //切换开启免费短信手机图标
            $('.send-a-msg').on('click' ,function (e) {
            });
            
            //发起会议按钮
            $('#startNow').click(function () {
            	var data = me.getData();
            	if (me.checkData(data)) {
        			//移动端
                	if(me.getOS() !== ''){
                		me.createMeet(data);
                	}
                	else{
                		me.createMeetWeb(data);
                	}          		
            	};           	
            });
    	},

        inputAnimate: function(){
            /*-- 输入框title动画部分 --*/
            var $inputList = $('.input-group input, .input-group textarea');

            $inputList.each(function () {
                Util.placeholderAnimate($(this));
            });
            
            $inputList.on('blur', function () {
            	if ($(this).val().trim()) {
            		$(this).removeClass('error');
            	}
            });
        },
        
        //创建会议
        createMeet: function (data) {
        	var baseUrl = Util.getRootPath(),
        		theme = '【会议】' + (data.meetContent.length > 20 ? data.meetContent.substring(0, 20) + '...' : data.meetContent),
        		title = data.userName + ' 发起了一个会议',
        		content = ['会议将于', '5月12日15:00', '在', '主办公室', '开始！'].join(''),
        		webpageUrl = baseUrl + '/lightapp/c/redirect.json?lappName=meeting&viewName=detail&oldData=false&meeting=todo'+ (Util.checkMobileSystem('ios') ? '&' : ''),
                shareUrl = baseUrl + '/clout/meeting/add?',
                successCallback = function(data){
                    window.location.href = '';
                };
                shareUrlParams = {
                    'meetingTheme': data.meetTheme,
                    'meetingContent': data.meetContent,
                    'meetingPlace': data.meetAddress,
                    'meetingStart': data.startTime,
                    'sendMsg': data.sendMsg,
                    'personId': data.personId
                },
        		createMeetParams = [
                    'meetingTheme=' + encodeURIComponent(data.meetTheme),
                    'meetingContent=' + encodeURIComponent(data.meetContent), 
                    'meetingPlace=' + encodeURIComponent(data.meetAddress),
                    'meetingStart=' + data.startTime,
                    'sendMsg=' + data.sendMsg,
                    'personId=' + encodeURIComponent(data.personId)
        		],
        		createMeetUrl = baseUrl + '/clout/meeting/add?' + createMeetParams.join('&'),
        		logoBase64 = '';
        	new LappPersonSelect({
                parentElement: 'body',          
                appName: '会议通知',
                title: title,           
                content: content,
                callbackUrl: shareUrl,
                callbackParam: shareUrlParams,
                successCallback: successCallback
            });
        	/*XuntongJSBridge.call('share' , {
        		'shareType': '4',
				'appId': data.appId,
				'lightAppId': data.lightAppId, // iOS接口需要String类型，将其强制转换 (10001)。
				'appName': '会议通知',
				'theme': theme,			//主题（可选），如传入，创建组时以此命名组名称
				'title': title,			//分享弹窗时的标题，也是会话组消息的标题
				'content': content,		//分享弹窗界面内容
				'cellContent': content,	//聊天界面显示的内容
				'thumbData' : logoBase64,	//分享的图标（base64）
				'webpageUrl' : webpageUrl,	//分享内容的跳转链接
				'sharedObject' : 'all',	//分享的对象：all（所有），group（组），person（人）
				'callbackUrl' : createMeetUrl	//创建会话组分享成功后的回调地址，参照 http://192.168.0.22/cms/pages/viewpage.action?pageId=4325562 (2.10 分享（新增默认人）)
			}, function(result){
			});*/
        },
        
        createMeetWeb: function(data){
        	var baseUrl = Util.getRootPath(),
    		theme = '【会议】' + (data.meetContent.length > 20 ? data.meetContent.substring(0, 20) + '...' : data.meetContent),
    		title = data.userName + ' 发起了一个会议',
    		content = ['会议将于', '5月12日15:00', '在', '主办公室', '开始！'].join(''),
    		webpageUrl = baseUrl + '/lightapp/c/redirect.json?lappName=meeting&viewName=detail&oldData=false&meeting=todo',
    		shareUrl = baseUrl + '/clout/meeting/add?',
    		shareUrlParams = {
        		'meetingTheme': data.meetTheme,
        		'meetingContent': data.meetContent,
        		'meetingPlace': data.meetAddress,
        		'meetingStart': data.startTime,
        		'sendMsg': data.sendMsg,
        		'personId': data.personId
        	},
        	appLogo = baseUrl + '/clout/images/icon-app-huiyi.png';
        	successCallback = function(data){
				window.location.href = '';
			};
        	new LappPersonSelect({
				parentElement: 'body',			
				appName: '会议通知',
				title: title,		    
				content: content,
				callbackUrl: shareUrl,
				callbackParam: shareUrlParams,
				successCallback: successCallback
    		});
        },
        
        //校验数据
        checkData: function (data) {
        	
        	return true;
        },
        
        //获取数据
        getData: function () {
            var data={};
            data.meetContent="";
            data.userName="张三"
        	return data;
        }

    };
    
    window.meet = meet;
})(window, jQuery);
    

