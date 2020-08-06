/* 
 * 通用工具集
 * @author muqin_deng
 * @time 2015/01/08
 */
(function (window, undefined) {
	var util = {
		data: {
			
		},
	
		userAgent: navigator.userAgent,
		
		//会议、活动时间转换
		parseTime: function(startTime, endTime){
			var startDate = new Date(+startTime),
				endDate = new Date(+endTime),
				formateStr = 'MM月dd日 hh:mm';
			
			return startDate.format(formateStr) + ' -- ' + endDate.format(formateStr);
        },
        
        //时间补0
        timeAddZero: function (time) {
        	if (time < 10) {
                return '0' + time;
            } else {
                return '' + time;
            }
        },
	        
		//微信提示分享朦层
		showOverLayer: function () {
			var $body = $('body'),
				$montLayer = $('.mont-layer');
			
			var stopClick = function () {
				return false;
			};
			
			$montLayer.show();
            
            //阻止滚动
            $body.addClass('hidden').on('touchmove', stopClick);
            
            $montLayer.on('click', function (e) {
            	e.preventDefault();
            	e.stopPropagation();
            	//还原body可滚动
            	$body.removeClass('hidden').off('touchmove', stopClick);
                $montLayer.hide();
                
                $montLayer.off('click');
                
                return false;
            });
		},
		
		//显示消息提示
		showTips : function(tips, time){
			var me = this,
				$userinfoPs = $("#userinfo-ps"),
				closeTime = time || 500;
			
			if (tips =='HIDE') {
				$userinfoPs.fadeOut();
				return ;
			}
			
			$userinfoPs.html(tips).fadeIn("slow",function(){
				if (closeTime == 'NOHIDE') {
					return ;
				}
				
				me.delay(closeTime, function () {
    				$userinfoPs.fadeOut(1500);
    			});
    		});
 		},
		
		//延时函数
        delay: function (t, callback) {
        	return window.setTimeout(function () {
        		callback();
        	}, t);
        },
        
        //字符串去除所有空格
        trimAll: function (str) {
        	return str.replace(/\s/g, '');
        },
        
        //判断手机号格式是否正确
        checkMobile: function (tel) {
        	return /^1\d{10}$/.test(tel);
        },
        
        //转义特殊字符<,>,",'
		escapeToHtmlEntity: function (str) {
			if (!str) {
				return '';
			}

			return str.replace(/[<>"']/g, function (match) {
				var entity = '';
				
				switch (match) {
					case '<':
						entity = '&#60;';
						break;
					case '>':
						entity = '&#62;';
						break;
					case '\"':
						entity = '&#34;';
						break;
					case '\'':
						entity = '&#39;';
						break;
				}

				return entity;
			});
		},
        
        //元素滚动到可见区域
        scrollIntoView: function (elem, always) {
        	if (always === true) {
        		elem.scrollIntoView();
        		return ;
        	}
        	elem.scrollIntoViewIfNeeded();
        },
        
        //使用iscroll插件横向滚动
        scrollX: function (id) {
        	if (!document.querySelector(id)) {
        		return ;
        	}
        	
        	return new IScroll(id, {
        		scrollX: true,
        		scrollY: false,
        		eventPassthrough: true,	//横向滚动时允许页面纵向滚动
        		disableMouse: true,		//是否关闭鼠标事件探测。如知道运行在哪个平台，可以开启它来加速。
        		disablePointer: true,	//是否关闭指针事件探测。如知道运行在哪个平台，可以开启它来加速。
        		deceleration: 0.002		//滚动动量减速，越大减速越快，建议不大于0.01, 默认0.0006
        		/*bounceTime: 600,
        		bounceEasing: 'bounce'*/

        	});
        },

        //获取字节长度
        getRealLength: function(str){
            return str.replace(/[^\x00-\xff]/g,"**").length;
        },
        
        //获取根路径（可含项目路径）
        getRootPath: function (needProjectPath) {
        	var origin = location.origin;
        	
        	if (!origin) {
        		origin = location.protocol + '//' + location.host;
        	}
        	
        	var path = origin;
        	
        	if (needProjectPath) {
        		var reg = /\/[^\/]+\//,
        			projectName = reg.exec(location.pathname);
        		
        		path += (projectName ? projectName[0] : '');
        	}
			
        	return path;
        },
        
        //转换发布时间格式
        parsePubTime: function (time) {
        	var thisdateTime = new Date(),
        		timeText = new Date(Number(time)),
        		thisTimeHours, thisTimeMinutes, thisTimeMon, thisTimeDate;
	    		
        	thisdateTime = new Date(thisdateTime.getFullYear()+"/"+thisdateTime.getMonth()+1+"/"+thisdateTime.getDate()+" 00:00");
        		    		
			if(time > thisdateTime.getTime()){
				
				thisTimeHours = this.timeAddZero(timeText.getHours());
				thisTimeMinutes = this.timeAddZero(timeText.getMinutes());
	    		
	    		timeText = "今天 "+thisTimeHours+" : "+thisTimeMinutes;
			} else {
    			
    			thisTimeHours = this.timeAddZero(timeText.getHours());
    			thisTimeMinutes = this.timeAddZero(timeText.getMinutes());
    			thisTimeMon = this.timeAddZero(timeText.getMonth()+1);
    			thisTimeDate = this.timeAddZero(timeText.getDate());
    			
        		timeText = thisTimeMon + "月"+thisTimeDate+"日 "+thisTimeHours+":"+thisTimeMinutes;
			}
			
			return timeText;
        },

        //转换输入框时间
        conversInputTime : function(date,msGap,endTime,vote){
            msGap = msGap || 0;
            var me = this;
            var us = new Date((new Date(date).getTime()) + msGap);
            var Year = us.getFullYear();
            var Month = us.getMonth()+1;
            var Hours = us.getHours();
            var Minutes = us.getMinutes();
            var getDate = us.getDate();
            
            if(endTime == "endTime"){
                var thisDay = me.getLastDay(Year,Month);
                if(thisDay == getDate){
                    getDate = 1;
                    if(Month == 12){
                        Month = 1;
                        Year += 1;
                    }else{
                        Month += 1;
                    }
                }else{
                    getDate += 1;
                }
            }
            if(!/^\d{2}$/.test(Month)){
                Month = "0" + Month;
            }           
            if(!/^\d{2}$/.test(getDate)){
                getDate = "0" + getDate;
            }
            if(!/^\d{2}$/.test(Hours)){
                Hours = "0" + Hours;
            }           
            if(!/^\d{2}$/.test(Minutes)){
                Minutes = "0" + Minutes;
            }
            if(vote == "vote"){
                return Year+"年"+Month+"月"+getDate+"日 "+Hours+":"+Minutes;
            }else{
                return Year+"/"+Month+"/"+getDate+" "+Hours+":"+Minutes;
            }
        },

        //获取某年某月最后一天
        getLastDay : function(year,month) {  
    		return new Date(year, month, 0).getDate();
        },
        
        /*
         * 移动端tap事件
         * @param {HTMLElement} elem 需要绑定事件的dom节点
         * @param {string} proxySelecter 是否有事件代理，有则该值是选择器字符串
         * @param {function} callback 绑定事件的回调
         */
        tap: function (elem, proxySelecter, callback) {
        	
        },
        
        //输入框动画
        placeholderAnimate: function ($input, ignoreText) {
        	var me = this;
        	
        	if (!$input || !$input.length) {
        		return ;
        	}

			var $title = $input.siblings('.placeholder'),	
				topTips = $title.data('top-tips'),  //提示语处于顶部时的内容
				text = $title.text(),				//处于输入框中部时的内容
				str = ignoreText || '';

			$input.on('focus', function () {
				var val = $input.val().trim();

				$title.html(topTips).addClass('in-top');
			})
			.on('blur', function () {
				var val = $input.val().trim();

				if (val && val != str) {
					$title.html(topTips).addClass('in-top');
				} else {
					$input.val('');
					$title.removeClass('in-top');
					me.delay(50, function () {
						$title.html(text);
					});
				}
			});
			
			//如果输入框有默认值
			if ($input.val().trim()) {
				$title.html(topTips).addClass('in-top');
			}
        },
        
        //微信显示右上角菜单按钮
        showOptionMenu: function () {
        	if (typeof WeixinJSBridge == "undefined") {
        		document.addEventListener('WeixinJSBridgeReady', function () {
        			WeixinJSBridge.call('showOptionMenu');
        		}, false);
        	} else {
        		WeixinJSBridge.call('showOptionMenu');
        	}
        },
        
        //微信隐藏右上角菜单按钮
        hideOptionMenu: function () {
        	if (typeof WeixinJSBridge == "undefined") {
        		document.addEventListener('WeixinJSBridgeReady', function () {
        			WeixinJSBridge.call('hideOptionMenu');
        		}, false);
        	} else {
        		WeixinJSBridge.call('hideOptionMenu');
        	}
        },
        
        //判断移动端系统
        checkMobileSystem: function (type) {
        	var regObj = {
	        		'ios': /\(i[^;]+;( U;)? CPU.+Mac OS X/,
	        		'android': /Android/i
	        	};
        	
        	if (!regObj[type]) {
        		return ;
        	}
        	
        	return regObj[type].test(this.userAgent);
        },
        
        //逐个单线程加载图片、为解决ios图片错位问题
        loadImage1By1: function ($imgList) {
        	var $img = $imgList,
				timeTag = $.now(),
				i = 0;
        	
        	if (!$img || !$img.length) {
        		return ;
        	}

			(function load() {
				var $curr = $img.eq(i);
	
				if (!$curr.length) {
					return ;
				}
	
				//加载成功或失败继续加载下一个
				$curr.on('load error', function () {
					$curr.off();
					load();
				})
				.attr('src', $curr.data('url') + '?' + timeTag);
	
				i++;
			})();
        },
        
        //获取url键值对
        getUrlKeyValObj: function () {
        	var url = window.location.href,
				arr, i, len,
				paramsObj = {};			
	
			arr = url.substring(url.indexOf('?')+1).split('&');
			
			for (i = 0, len = arr.length; i < len; i++) {
				var reg = /(.*)\=(.*)/g,
					match = reg.exec(arr[i]);
	
				if (match && match[1]) {
					paramsObj[decodeURIComponent(match[1])] = decodeURIComponent(match[2]);
				}
			}
			
			return paramsObj;
        },
        
        //获取url的对应参数的值
		getUrlValue: function (param) {
			if (!param) {
				return '';
			}
			
			var paramsObj = this.getUrlKeyValObj();

			if (paramsObj.hasOwnProperty(param)) {
				return paramsObj[param];
			} else {
				return '';
			}
		},
		
		//监听scroll事件，悬停底部按钮
    	toFixedBtn: function ($btn) {
    		var me = this;
    		
    		if (!$btn.length) {
    			return ;
    		}
    		
    		var top = $btn.offset().top,
    			width = $btn.width(),
    			height = $btn.outerHeight(),
    			windowHeight = $(window).height(),
    			$btnFixedWrap = $('.btn-fixed-wrap'),
    			$btnGroup = $('.bottom-btn-group');
     		
    		//设置底部按钮高度，防止按钮归位时有底部元素有跳动感
     		$btnGroup.height($btnGroup.height());
    		
     		//设置按钮的宽度，防止按钮fixed定位后错位
    		//$btn.width(width);
     		
     		var onScroll = function () {
     			
				var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
				    			
    			if (top + height > windowHeight + scrollTop && scrollTop > 0) {
    				$btnFixedWrap.show().removeClass('floatout').addClass('floatin');
    				$btn.addClass('btn-fixed').removeClass('floatout').addClass('floatin');
    			} else {
    				
    				if (scrollTop === 0) {
    					$btnFixedWrap.removeClass('floatin').addClass('floatout');
        				$btn.removeClass('floatin').addClass('floatout');
        				me.delay(300, function () {
        					$btn.removeClass('btn-fixed');
        					$btnFixedWrap.hide();
        				});
        				
        				return ;
    				}
    				
    				$btnFixedWrap.hide().removeClass('floatin');
    				$btn.removeClass('btn-fixed').removeClass('floatin');
    			}
    		};
     		    		
    		$(window).off('scroll').on('scroll', onScroll);
    		
    		//onScroll();
    	},
    	
    	/*
		 * 页面模板引擎解析
		 * @param {string} html 要进行解析的html字符串（在<##>里面的转换成js来执行）
		 * @param {object} options html中解析传入的对象
		 * @return {string} 返回解析后的html字符串
		 */
		templateEngine: function(html, options) {
		    var re = /<#(.*?)#>/g, 
		    	reExp = /(^(\s|\t|\n|\r)*(var|if|for|else|switch|case|break|{|}))(.*)?/g, 
		    	code = 'var r=[];\n', 
		    	cursor = 0;
		    
		    html = html.replace(/\n|\r|\t/g, '');

		    var add = function(line, js) {
		        js? (code += line.match(reExp) ? line + '\n' : 'r.push(' + line + ');\n') :
		            (code += line !== '' ? 'r.push("' + line.replace(/"/g, '\\"') + '");\n' : '');
		        return add;
		    };
		    
		    while(match = re.exec(html)) {
		    	var noJsStr = html.slice(cursor, match.index);
		        add(/[^\s]/g.test(noJsStr) ? noJsStr : '')(match[1], true);
		        cursor = match.index + match[0].length;
		    }
		    add(html.substr(cursor, html.length - cursor));
		    code += 'return r.join("");';
		    
		    return new Function(code.replace(/[\r\t\n]/g, '')).apply(options ? options : {});
		},
		
		//获取用户当前经纬度，使用HTML5 Geolocation API
		getLocation: function (successFn, errorFn) {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function (position) {
					successFn(position.coords.longitude, position.coords.latitude);
				}, errorFn);
			} else {
				this.delay(100, errorFn);
			}
		},
		
		//获取当前城市，使用百度地图api接口 http://api.map.baidu.com/api?v=2.0&ak=kx7GASe6dXLGpwUsvqKxQdMn
		getCurrentCity: function () {
			var deferred = $.Deferred();
			//获取经纬度
			this.getLocation(function (lng, lat) {
				var point = new BMap.Point(lng, lat),
		        	geoc = new BMap.Geocoder(); 
	
				geoc.getLocation(point, function (rs) {
					var addComp = rs.addressComponents;
	
			        if (!addComp.city) {
			          addComp.city = '海外';
			        }
	
			        deferred.resolve(addComp.city);
				});
			}, function (error) {
				deferred.reject(error);
			});
			
			return deferred.promise();
		},
		
		loadCss: function(filePath){
        	var link = document.createElement('link'),
        		head = document.getElementsByTagName('head')[0];
        	link.rel = 'stylesheet';
        	link.type = 'text/css';
        	link.href = filePath;
        	head.appendChild(link);
        },
        
        loadJs: function(filePath){
        	var script = document.createElement('script');
        	script.type = 'text/javascript';
        	script.src = filePath;
        	document.body.appendChild(script);
        }
	};

	if (typeof define == 'function') {
		define(['jquery'], function ($) {
			return util;
		});
	}
	
	window.Util = window.Util || util;
	
	/*** 时间格式化相关 ***/
	var format = function(formatStr) {
        var year, month, day, hour, minute, second, 
            reg, rule, afterFormat;

        if (!formatStr) {
            return this.getTime();
        }

        year = this.getFullYear();
        month = this.getMonth() + 1;
        day = this.getDate();
        hour = this.getHours();
        minute = this.getMinutes();
        second = this.getSeconds();

        rule = {
            'yy': year - 2000,
            'yyyy': year,
            'M': month,
            'MM': util.timeAddZero(month),
            'd': day,
            'dd': util.timeAddZero(day),
            'h': hour,
            'hh': util.timeAddZero(hour),
            'm': minute,
            'mm': util.timeAddZero(minute),
            's': second,
            'ss': util.timeAddZero(second)
        };

        reg = /y{2,4}|M{1,2}|d{1,2}|h{1,2}|m{1,2}|s{1,2}/g;

        afterFormat = formatStr.replace(reg, function($) {
            if ($ in rule) {
                return rule[$];
            } else {
                return $;
            }
        });

       return afterFormat;

    };
    if (window.localStorage) {
    	util.ls = {
	    	set: function (key, value) {
	    		var oldval = this.get(key);

				//在setItem前先removeItem避免iPhone/ipad上偶尔的QUOTA_EXCEEDED_ERR错误
				if (oldval !== '') {
				    this.remove(key);
				}

				window.localStorage.setItem(key, value);

				return this;
	    	},

	    	get: function (key) {
	    		var value = window.localStorage.getItem(key);
				//如果为空统一返回null
				return !value ? '' : value;
	    	},

	    	remove: function (key) {
            	window.localStorage.removeItem(key);
            	
            	return this;
	    	}
	    };
    } else {
    	//操作cookie
		util.ls = {
			//expires传过期月数
			set: function (name, value, expires, path, domain, secure) {
				var cookieText = encodeURIComponent(name) + '=' + encodeURIComponent(value);

				if (!expires) {
					expires = 12;	//默认一年过期
				}

				var today = new Date();
				expires *= 2592000000 ;
				var expires_date = new Date(today.getTime() + expires) ;
				cookieText += '; expires=' + expires_date.toGMTString();

				if (path) {
					cookieText += '; path=' + path;
				}

				if (domain) {
					cookieText += '; domain=' + domain;
				}

				if (secure) {
					cookieText += '; secure'; 
				}

				document.cookie = cookieText;

				return this;
			},

			get: function (name) {
				var reg = new RegExp(name + '\=([^;=]+)');
				var match = reg.exec(document.cookie);

				return match ? match[1] : '';
			},

			remove: function (name, path, domain, secure) {
				this.set(name, '', new Date(0), path, domain, secure);

				return this;
			}
		};
    }
    //天，时，分，秒的毫秒数
    var ONE_SECOND = 1000,
        ONE_MINUTE = ONE_SECOND * 60,
        ONE_HOUR = ONE_MINUTE * 60,
        ONE_DAY = ONE_HOUR * 24;

    var timeGone = function (infoObj) {
        

        var now = new Date(),
            currYear = now.getFullYear(),                                       //当前年
            currMonDays = new Date(currYear, now.getMonth() + 1, 0).getDate(),  //当前月的总天数
            firstDay = new Date(currYear, 0, 1),                                //当前年的第一天
            lastDay = new Date(currYear, 11, 31),                               //当前年的最后一天
            oneYearDays = lastDay.getTime() - firstDay.getTime() + ONE_DAY;     //当前年的总毫秒数

        var compareArr = [
                {'type': 'year', 'time': oneYearDays},
                {'type': 'month', 'time': currMonDays * ONE_DAY},
                {'type': 'day', 'time': ONE_DAY},
                {'type': 'hour', 'time': ONE_HOUR},
                {'type': 'minute', 'time': ONE_MINUTE},
                {'type': 'second', 'time': ONE_SECOND}
            ],
            info = {
            	'year': '年',
            	'month': '个月',
            	'day': '天',
            	'hour': '小时',
            	'minute': '分钟',
            	'second': '秒'
            },
            i = 0,
            differ = Math.abs(now.getTime() - this.getTime()),    //传入时间与当前时间的毫秒差值
            result = 0;
        
        //从年开始算，不满1就继续往下推，大于等于1就结束
        while (result < 1 && i < compareArr.length) {
            result = differ / compareArr[i].time;
            i++;
        }
        
        var type = compareArr[i-1].type;
        
        result = Math.floor(result) === 0 ? 1 : Math.floor(result);

        return result + (infoObj && infoObj[type] ? infoObj[type] : info[type]) + '前';
    };
    
    Date.prototype.format = format;
    Date.prototype.timeGone = timeGone;
	
})(window);