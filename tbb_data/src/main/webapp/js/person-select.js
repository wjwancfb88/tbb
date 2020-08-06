(function(window, $, undefined){
	function LappPersonSelect(cfg){
		this.selectedCount = 0;
		this.xhrList = [];
		this.selectedMembers = {};
		this.selectedGroupId = '';
		this.orgPath = [];
		this.orgCache = {};
		this._$root = null;
		this._$parentElement = null;
		this.contactList = [];				  // 会话列表数据
		this.sessionLoading = true;         // 是否需要显示会话loading
		this.loadContactsComplete = false;    // 判断会话列表是否加载完成
		this.historyLen = 0;                  // 选人组件的历史记录数
		this.scrollIndex = 1;               
		this.cfg = {
			parentElement: 'body',	
			appId: '',				// 公众号id
			lightAppId: '', 		// 轻应用id	
			eId: '',				// 企业id
			openId: '',				// 用户id
			appName: '',            // 应用名称
			theme: '',			    // 主题，如传入，创建组时以此命名组名称
			title: '',			    // 分享弹窗时的标题，也是会话组消息的标题
			content: '',		    // 分享弹窗界面内容
			cellContent: '',	    // 聊天界面显示的内容
			thumbData : '',	    	// 分享的图标（base64）
			webpageUrl : '',		// 分享内容的跳转链接
			callbackUrl : '',      	// 创建会话组分享成功后的回调地址
			callbackParam: {},		// 回调地址传参
			successCallback: ''	,	// 回调成功后的处理函数
			existingSessionIsNeed: true //是否需要已有会话 
		};

		this.init(cfg);
	}

	LappPersonSelect.prototype = {
		init: function(cfg){
			this.initCfg(cfg);
			this.renderUI();
			this.initContactList(0, 150);	
			this.bindEvt();
		},

		initCfg: function(cfg){
			$.extend(this.cfg, cfg);
		},

		renderUI: function(){
			this._$parentElement = $(this.cfg.parentElement);
			this.setParentEleOverflow();
			window.location.hash='#lapp-contact-list';

			var $tpl = $(this.tpl());
			this._$root = $tpl;
			this._$parentElement.append($tpl);
		},

		initContactList: function(offset, count){
			var self = this;

			$('.lapp-person-select-wrap .contact-list-wrap .lapp-search-loading').hide();
    		self.sessionLoading = false;
    		$('.lapp-person-select-wrap .person-select-exist-sessions .lapp-search-loading').hide();
    		$('.lapp-result-none').hide();

    		var html1 ='<li class="contact-detail" data-userid="1" data-groupid="11"><div class="contact-detail-inner clearfix"><span class="contact-checkbox"></span><img src="images/headimg.jpg"><div class="contact-content"><h3>张三</h3><p>职员</p></div></div></li>';
    		var html2 ='<li class="contact-detail" data-userid="2" data-groupid="22"><div class="contact-detail-inner clearfix"><span class="contact-checkbox"></span><img src="images/headimg.jpg"><div class="contact-content"><h3>李四</h3><p>职员</p></div></div></li>';
    		var html3 ='<li class="contact-detail" data-userid="3" data-groupid="33"><div class="contact-detail-inner clearfix"><span class="contact-checkbox"></span><img src="images/headimg.jpg"><div class="contact-content"><h3>王五</h3><p>职员</p></div></div></li>';
    		$(".contact-detail-ul").append(html1);
    		$(".contact-detail-ul").append(html2);
    		$(".contact-detail-ul").append(html3);
    		
           
		},

		
		bindEvt: function(){
			this.bindSearchEvt();
			this.bindSearchSession();
			this.bindContactListEvt();
			this.bindLinkEvt();
			this.bindHashChangeEvt();
			this.bindSelectedPersonsEvt();
			this.bindOrgTreeEvt();
			this.bindButtonEvt();
			this.bindScrollEvt();
		},

		// 联系人列表点击事件
		bindContactListEvt: function(){
			var self = this;
			// 点击用户
			$('.lapp-person-select-wrap').on('click', '.contact-detail-ul li.contact-detail', function(e){
				var $li = $(e.target).closest('li'),
					userId = $li.data('userid'),
            		photoUrl = $li.find('img').attr('src'),
            		groupId = $li.data('groupid');

            	if($li.hasClass('checked')){
            		$('.lapp-person-select-wrap .contact-detail-ul').find('li[data-userid="' + userId + '"]').removeClass('checked');
            		if(self.selectedMembers[userId]){
            			delete self.selectedMembers[userId];
            		}

            		$('.lapp-person-select-wrap .selected-persons-wrap').find('li[data-userid="' + userId + '"]').remove();
            		self.selectedCount--;

            		// 选择人员数目小于6，隐藏左右滚动箭头
					if(self.selectedCount == 5){
						$('.lapp-person-select-wrap .person-move-left').hide();
	            		$('.lapp-person-select-wrap .person-move-right').hide();
	            		$('.lapp-person-select-wrap .selected-persons-scroll-wrap').removeClass('selected-persons-four');
	            		$('.lapp-person-select-wrap .selected-persons-wrap').animate({'marginLeft': '0px'}, 300);
	            		self.scrollIndex = 1;
					}
					// 选择删除最后一版元素
					else if(self.selectedCount >= 6 && self.scrollIndex == self.selectedCount - 2){
						var marginLeft = -(self.scrollIndex - 2) * 46 + 'px';
						$('.lapp-person-select-wrap .selected-persons-wrap').animate({'marginLeft': marginLeft}, 300);
						self.scrollIndex--;
					}
            	}
            	else{
            		$('.lapp-person-select-wrap .contact-detail-ul').find('li[data-userid="' + userId + '"]').addClass('checked');
            		if(self.selectedMembers[userId]){
            			return;
            		}
            		// 如果groupId为空，表明是在搜索列表或组织架构列表，则去常用联系人列表中尝试获取groupId
					if(!groupId){
						groupId = $('.lapp-person-select-wrap .contact-list-wrap .contact-detail-ul').find('li[data-userid="' + userId + '"]').data('groupid');
					}

            		var obj = {
            			userId: userId, 
            			photoUrl: photoUrl,
            			groupId: groupId
            		};
            		self.selectedMembers[obj.userId] = obj;

            		$('.lapp-person-select-wrap .selected-persons-wrap').prepend(self.selectedPersonTpl(userId, photoUrl));
            		self.selectedCount++;

            		// 如果选择的人数大于5，显示左右滚动箭头
	            	if(self.selectedCount == 6){
	            		$('.lapp-person-select-wrap .person-move-left').show();
	            		$('.lapp-person-select-wrap .person-move-right').show();
	            		$('.lapp-person-select-wrap .selected-persons-scroll-wrap').addClass('selected-persons-four');
	            	}	            	
            	}	
            	self.showSelectedCount();
            	
			});	
				
			// 点击已有会话
			$('.lapp-person-select-wrap').on('click', '.contact-detail-ul li.group-detail', function(e){
				$(this).addClass('selected');
				$('.lapp-person-select-wrap .content-box').fadeIn();
				$('.lapp-person-select-wrap .person-select-overlay').fadeIn();
				// 标识为点击已有会话打开
				$('.lapp-person-select-wrap .content-box').attr('openSource', '1');
				self.selectedGroupId = $(this).data('groupid');
			});
		},

		// 已选择的人员列表点击事件
		bindSelectedPersonsEvt: function(){
			var self = this;
			$('.lapp-person-select-wrap .selected-persons-wrap').on('click', '.delete-selected-person', function(e){
				var $li = $(e.target).closest('li'),
					userId = $li.data('userid');
				$('.lapp-person-select-wrap li.contact-detail[data-userid="' + userId + '"]').removeClass('checked');
				if(self.selectedMembers[userId]){
					delete self.selectedMembers[userId];
				}

				$li.remove();
				self.selectedCount--;
				self.showSelectedCount();

				// 选择人员数目小于6，隐藏左右滚动箭头
				if(self.selectedCount < 6){
					$('.lapp-person-select-wrap .person-move-left').hide();
            		$('.lapp-person-select-wrap .person-move-right').hide();
            		$('.lapp-person-select-wrap .selected-persons-scroll-wrap').removeClass('selected-persons-four');
            		$('.lapp-person-select-wrap .selected-persons-wrap').animate({'marginLeft': '0px'}, 300);
            		self.scrollIndex = 1;
				}
				// 选择删除最后一版元素
				else if(self.scrollIndex == self.selectedCount - 2){
					var marginLeft = -(self.scrollIndex - 2) * 46 + 'px';
					$('.lapp-person-select-wrap .selected-persons-wrap').animate({'marginLeft': marginLeft}, 300);
					self.scrollIndex--;
				}
			});
		},

		// 搜索用户
		bindSearchEvt: function(){
			var	self = this;
			$('.person-search-wrap .search-input').on('input propertychange', function(e){
				var word = htmlEntities($(e.target).val().trim()),
					me = this;

				clearTimeout(this.searchTimeout);
		        this.searchTimeout = setTimeout(function(){

		            clearTimeout(me.searchTimeout);
		            me.searchTimeout = null;
		        }, 500);
			});			
		},

		// 搜索会话组
		bindSearchSession: function () {
			
		},

		bindButtonEvt: function(){
			var self = this;

			// 取消
			$('.lapp-person-select-wrap .content-box-footer .content-box-cancel').click(function(){
				// 去掉选中会话组的效果
				var openSource = $('.lapp-person-select-wrap .content-box').attr('openSource');
				if(openSource === '1'){
					var groupId = self.selectedGroupId;
					$('.lapp-person-select-wrap .person-select-exist-sessions li[data-groupid="' + groupId + '"]').removeClass('selected');
				}

				$('.lapp-person-select-wrap .content-box').fadeOut();
				$('.lapp-person-select-wrap .person-select-overlay').fadeOut();
			});

			// 发送
			$('.lapp-person-select-wrap .content-box-footer .content-box-confirm').click(function(){
				$('.lapp-person-select-wrap .content-box').fadeOut();
				$('.lapp-person-select-wrap .person-select-overlay').fadeOut();
			});

			// 点击开始按钮
			$('.lapp-person-select-wrap .select-persons-button-start').click(function(){
				if(!$(this).hasClass('selected')){
					return;
				}
				$('.lapp-person-select-wrap .content-box').fadeIn();
				$('.lapp-person-select-wrap .person-select-overlay').fadeIn();
				// 标识为点击开始按钮打开
				$('.lapp-person-select-wrap .content-box').attr('openSource', '0');
			});

			// 关闭
			$('.lapp-person-select-wrap .select-persons-button-close').click(function(){
				self.destroy();
				self.resetHistory();
			});
		},

		bindLinkEvt: function(){
			//点击组织架构
			$('.lapp-person-select-wrap .link-list .organization-link').click(function(){
				
			});

			// 点击已有会话
			$('.lapp-person-select-wrap .link-list .exist-sessions-link').click(function(){
				window.location.hash = '#lapp-exist-sessions';
			});

		},

		bindScrollEvt: function(){
			var self = this;
			// 已选择人员列表向右滚动
			$('.lapp-person-select-wrap .person-move-left').click(function(){
				var index = self.scrollIndex;
				if(index == 1){
					return;
				}
				var marginLeft = -(index - 2) * 46 + 'px';
				$('.lapp-person-select-wrap .selected-persons-wrap').animate({'marginLeft': marginLeft}, 300);
				self.scrollIndex--;				
			});

			// 已选择人员列表向左滚动
			$('.lapp-person-select-wrap .person-move-right').click(function(){
				var index = self.scrollIndex;
				if(index == self.selectedCount - 3){
					return;
				}
				var marginLeft = -index * 46 + 'px';
				$('.lapp-person-select-wrap .selected-persons-wrap').animate({'marginLeft': marginLeft}, 300);
				self.scrollIndex++;
			});
		},

		bindHashChangeEvt: function(){
			this.newHashChangeHandler = this.hashChangeHandler.bind(this);
			$(window).on('hashchange', this.newHashChangeHandler);
		},

		unbindHashChangEvt: function(){
			$(window).off('hashchange', this.newHashChangeHandler);
		},

		hashChangeHandler: function(){
			var hash = window.location.hash;

			if(hash === '#lapp-exist-sessions'){
				$('.lapp-person-select-wrap .person-select-container').hide();
				$('.lapp-person-select-wrap .person-select-exist-sessions').show();
				$('.lapp-person-select-wrap .selected-persons-toolbar-left').hide();
				$('.lapp-person-select-wrap .select-persons-button-start').hide();

				if (this.loadContactsComplete === true) {
					// 已有会话为空
					if($('.lapp-person-select-wrap .person-select-exist-sessions .contact-detail-ul li').length === 0){
						$('.lapp-person-select-wrap .person-select-exist-sessions .lapp-result-none').show();
					}
				}

				if (this.sessionLoading === true) {
					$('.lapp-person-select-wrap .person-select-exist-sessions .lapp-search-loading').show();
					this.sessionLoading = false;									
				} 
				this.historyLen = 2;
			} else {
				var $searchInput = $('.person-select-exist-sessions .search-input');
				if ($searchInput.val().trim() != '') {
					$searchInput.val('').trigger('input');
				}

				if(hash === '#lapp-organization'){
					$('.lapp-person-select-wrap .person-select-container').hide();
					$('.lapp-person-select-wrap .person-select-organization').show();
					$('.lapp-person-select-wrap .selected-persons-toolbar-left').show();
					$('.lapp-person-select-wrap .select-persons-button-start').show();

					this.renderOrgTree();
					this.historyLen = 2;
				}
				else if(hash === '#lapp-contact-list'){
					$('.lapp-person-select-wrap .person-select-container').hide();
					$('.lapp-person-select-wrap .person-select-contacts').show();
					$('.lapp-person-select-wrap .selected-persons-toolbar-left').show();
					$('.lapp-person-select-wrap .select-persons-button-start').show();

					this.historyLen = 1;
				}
				else{
					this.destroy();
					this.historyLen = 0;
				}
			}
		},

		bindOrgTreeEvt: function(){
			
		},

		tpl: function(){
			var t = new SimpleTemplate();

			t._('<div class="lapp-person-select-wrap">')
				._('<div class="person-select-contacts person-select-container">')
					._('<div class="person-search-wrap">')
						._('<span class="search-icon"></span>')
						._('<input class="search-input" autocomplete="off" placeholder="姓名/拼音/电话"></input>')
					._('</div>')
					._('<div class="contact-list-wrap">')
						._('<div class="link-list">')
							._('<div class="organization-link link clearfix">')
								._('<img src="images/organization-icon.png">')
								._('<h3>组织架构</h3>')
							._('</div>');
						//是否需要已有会话
						if(this.cfg.existingSessionIsNeed){
							t._('<div class="exist-sessions-link link clearfix">')
								._('<img src="images/exist-sessions-icon.png">')
								._('<h3>已有会话</h3>')
							._('</div>');
						}
								
						t._('</div>')
						._('<h3>常用联系人</h3>');
						if(this.cfg.existingSessionIsNeed){
							t._('<div class="contact-list">');
						}
						else{
							t._('<div class="contact-list no-need-session">');
						};
						
							t._('<div class="contact-detail-wrap">')
								._('<ul class="contact-detail-ul">')

							//	._('<li class="contact-detail checked"><div class="contact-detail-inner clearfix"><span class="contact-checkbox"></span>')
							//	._('<img src="images/headimg.jpg"><div class="contact-content"><h3>张三</h3><p>职员</p></div></div></li>')

								._('</ul>')
							._('</div>')
							//._('<div class="lapp-search-loading"><img src="images/loading.gif"></div>')
							._('<div class="lapp-result-none">')
								._('<img src="images/result-none.png">')
								._('<span>暂无联系人</span>')
							._('</div>')							
						._('</div>')
					._('</div>')
					._('<div class="search-list-wrap">')
						._('<div class="contact-detail-wrap">')
							._('<ul class="contact-detail-ul">')
							._('</ul>')
						._('</div>')
						._('<div class="lapp-search-loading"><img src="images/loading.gif"></div>')
						._('<div class="lapp-result-none">')
							._('<img src="images/result-none.png">')
							._('<span>无结果</span>')
						._('</div>')
					._('</div>')
				._('</div>')
				._('<div class="person-select-exist-sessions person-select-container">')
					._('<div class="contact-detail-wrap">')
						._('<div class="session-search-wrap"><span class="search-icon">')
							._('</span><input class="search-input" autocomplete="off" placeholder="会话组名">')
						._('</div>')
						._('<ul class="contact-detail-ul">')
						._('</ul>')
					._('</div>')
					._('<div class="lapp-search-loading"><img src="images/loading.gif"></div>')
					._('<div class="lapp-result-none">')
						._('<img src="images/result-none.png">')
						._('<span>暂无会话</span>')
					._('</div>')
				._('</div>')
				._('<div class="person-select-organization person-select-container">')
					._('<div class="contact-detail-wrap clearfix">')
					._('</div>')
					._('<div class="lapp-search-loading"><img src="images/loading.gif"></div>')
					._('<div class="lapp-result-none">')
						._('<img src="images/result-none.png">')
						._('<span>没有组织架构</span>')
					._('</div>')
				._('</div>')
				._('<div class="selected-persons-toolbar clearfix">')
					._('<div class="selected-persons-toolbar-left clearfix">')
						._('<span class="person-move-left"></span>')
						._('<div class="selected-persons-scroll-wrap">')
							._('<ul class="selected-persons-wrap clearfix"></ul>')
						._('</div>')
						._('<span class="person-move-right"></span>')
					._('</div>')
					._('<div class="select-persons-button-list clearfix">')
						._('<button class="select-persons-button-start">开始</button>')
						._('<button class="select-persons-button-close">取消</button>')
					._('</div>')
				._('</div>')
				._('<div class="person-select-overlay"></div>')
				._('<div class="content-box" openSource="0">')
					._('<h3 class="ellipsis">' + this.cfg.title + '</h3>')
					._('<div class="content-box-wrap">')
						._('<div class="content-box-main clearfix">')
							._('<img src="images/icon-app-huiyi.png">')
							._('<p>' + escapeContent(this.cfg.content) + '</p>')
						._('</div>')
						._('<div class="content-box-source">')
							._('<span>来自：</span>')
							._('<span class="app_name">' + this.cfg.appName + '</span>')
						._('</div>')
					._('</div>')
					._('<div class="content-box-footer clearfix">')
						._('<button class="content-box-cancel">取消</button>')
						._('<button class="content-box-confirm">发送</button>')
					._('</div>')
				._('</div>')
				._('<div class="person-select-prompt-msg"></div>')
			._('</div>');

			return t.toString();
		},

		//显示开始按钮上的数字
		showSelectedCount: function(){
			var $confirmButton = $('.lapp-person-select-wrap .select-persons-button-start');
	        if(this.selectedCount > 0){
	            $confirmButton.text('开始 (' + this.selectedCount +')');
	            if(!$confirmButton.hasClass('selected')){
	            	$confirmButton.addClass('selected');
	            }
	        }
	        else{
	            $confirmButton.removeClass('selected').text('开始');
	        }
	    },

	    selectedPersonTpl: function(userId, photoUrl){
	    	var t = new SimpleTemplate();
	    	t._('<li data-userid="' + userId + '">')
	    		._('<img src="' + photoUrl + '">')
	    		._('<span class="delete-selected-person"></span>')
	    	 ._('</li>');

	    	return t.toString();
	    },

	    renderSearchList: function(data, keyword){
	    	var t = new SimpleTemplate(),
            	photoUrl,
            	additional_message,
            	defaultUrl = 'http://kdweibo.com/space/c/photo/load?id=';
            for(var i = 0, len = data.length; i < len; i++){
            	photoUrl = data[i].photoUrl || defaultUrl;
            	isSelectedCls = this.selectedMembers[data[i].id] ? ' checked' : '';
            	var fullPinyin = data[i].fullPinyin,
            		phone = data[i].defaultPhone;
            	additional_message = phone.indexOf(keyword) > -1 ? phone : fullPinyin.indexOf(keyword) > -1 ? fullPinyin : phone;

            	t._('<li class="contact-detail clearfix' + isSelectedCls + '" data-userid="' + data[i].id + '">')
            		._('<div class="contact-detail-inner clearfix">')
	            		._('<span class="contact-checkbox"></span>')
	            		._('<img src="' + photoUrl + '">')
	            		._('<div class="contact-content">')
	            			._('<div class="clearfix">')
	            				._('<h3 class="search-name-text ellipsis">' + data[i].name + '</h3>')
	            				._('<h3 class="search-department-text ellipsis">' + data[i].department + '</h3>')
	            			._('</div>')
	            			._('<p>' + additional_message + '</p>')
	            		._('</div>')
	            	._('</div>')
            	._('</li>');
            }

            $('.lapp-person-select-wrap .search-list-wrap .contact-detail-ul').append(t.toString());
	    },

	    renderContactList: function(data){
	    	for(var i = 0, len = data.length; i < len; i++){
	    		if(data[i].type === 1){
	    			$('.lapp-person-select-wrap .contact-list-wrap .contact-detail-ul').append(this.contactDetailTpl(data[i]));
	    		}
	    	}
	    },

	    renderSessionList: function(data){
	    	for(var i = 0, len = data.length; i < len; i++){
	    		if(data[i].type === 2){
	    			$('.lapp-person-select-wrap .person-select-exist-sessions .contact-detail-ul').append(this.groupDetailTpl(data[i]));
	    		}
	    	}
	    },

	    contactDetailTpl: function(data){
	    	var t = new SimpleTemplate(),
            	defaultUrl = 'http://kdweibo.com/space/c/photo/load?id=',
            	jobTitle = data.participant[5][0] || '职员',
            	userId = data.participant[0][0],
            	userName = data.groupName,
            	groupId = data.groupId,
            	photoUrl = data.participant[2][0] || defaultUrl,
				isSelectedCls = this.selectedMembers[userId] ? ' checked' : ''; 

            t._('<li class="contact-detail' + isSelectedCls + '" data-userid="' + userId + '" data-groupid="' + groupId + '">')
            	._('<div class="contact-detail-inner clearfix">')
	        		._('<span class="contact-checkbox"></span>')
	        		._('<img src="' + photoUrl + '">')
	        		._('<div class="contact-content">')
	        			._('<h3>' + userName + '</h3>')
	        			._('<p>' + jobTitle + '</p>')
	        		._('</div>')
	        	._('</div>')
        	._('</li>');

            return t.toString();
	    },

	    groupDetailTpl: function(data){
	    	var t = new SimpleTemplate(),
	            photoUrl,
	            photoCount,
	            defaultUrl = 'http://kdweibo.com/xtweb/pub/img/default_man.png';

	        var searchVal = $('.person-select-exist-sessions .search-input').val().trim();
	        var style = '';

			if (searchVal && data.groupName.indexOf(searchVal) == -1) {
				style = 'style="display: none;"';
			}

	        t._('<li class="group-detail" ' + style + ' data-groupid="' + data.groupId + '" data-groupname="' + data.groupName + '">')
	        	._('<div class="contact-detail-inner clearfix">')
	        		._('<div class="group-avatar clearfix">');

	       	photoCount = data.participant[2].length > 4 ? 4 : data.participant[2].length;
            for(var j = 0; j < photoCount; j++){
                photoUrl = data.participant[2][j] || defaultUrl;
                t._('<img src="' + photoUrl + '">');
            }

            t._('</div>')
            ._('<div class="group-content ellipsis">' + data.groupName + '(' + (parseInt(data.participant[2].length) + 1) + '人)</div></div>')
            ._('</li>');

           	return t.toString();
	    },

	    renderOrgTree: function(){
	    	var orgPath = this.orgPath,
	    		self = this;
	    	if(orgPath && orgPath.length > 0){
	    		if(orgPath.length > 1){
	    			orgPath.splice(1);
	    		}

	    		var tpl = this.orgTreeTpl(orgPath[0], 'org-index');
	    		$('.lapp-person-select-wrap .person-select-organization .contact-detail-wrap').empty().append(tpl);

	    		// 组织架构为空
            	if($('.lapp-person-select-wrap .person-select-organization .contact-detail-ul li').length === 0){
            		$('.lapp-person-select-wrap .person-select-organization .lapp-result-none').show();
            	}
	    	}
	    	else{
	    		var eid = this.cfg.eId || '',
	    			reqData = {
	                	protocol: 'post',
	                	url: '/im/web/treeOrg.do',
	                	param: {
	                		eid: eid,
		                    orgId: '',
		                    begin: 0,
		                    count: 10000
	                	}
	                };

	            this.commonRequest(reqData).done(function (resp){
	            	$('.lapp-person-select-wrap .person-select-organization .lapp-search-loading').hide();
	            	var key = 'root';
	            	resp.data.id = key;
	            	self.orgCache[key] = resp.data;
                	orgPath.push(resp.data);

                	var tpl = self.orgTreeTpl(orgPath[0], 'org-index');
	    			$('.lapp-person-select-wrap .person-select-organization .contact-detail-wrap').empty().append(tpl);

	    			// 组织架构为空
	            	if($('.lapp-person-select-wrap .person-select-organization .contact-detail-ul li').length === 0){
	            		$('.lapp-person-select-wrap .person-select-organization .lapp-result-none').show();
	            	}
	            });
	    	}
	    },

	    orgTreeTpl: function(data, type){
	    	var t = new SimpleTemplate();

	    	if(type === 'org-index'){
	    		t._('<ul class="contact-detail-ul contact-detail-ul-org-index" data-id="' + data.id + '">');
	    	}
	    	else{
	    		t._('<ul class="contact-detail-ul" data-id="' + data.id + '">');
	    	}	    	

	    	if(data.parent){
	    		t._('<li class="parent-org">')
	    			._('<div class="contact-detail-inner">')
		    			._('<span class="prev-arrow"></span>')
		    			._('<a href="javascript:void(0)" class="parent-name ellipsis" title="' + data.parent + '">' + data.parent + '</a>')
		    		._('</div>')
	    		._('</li>');
	    	}

	    	for(var i = 0, len = data.children.length; i < len; i++){
	    		var department = data.children[i];
	    		t._('<li class="department" data-id="' + department.id + '">')
	    			._('<div class="contact-detail-inner">')
		    			._('<a class="dep-name ellipsis" href="javascript:void(0)" title="' + department.name + '">' + department.name + '</a>')
		    			._('<span class="next-arrow"></span>')
		    		._('</div>')
	    		._('</li>');
	    	}

	    	for(var j = 0, len = data.person.length; j < len; j++){
	    		var user = data.person[j],
		    		defaultUrl = 'http://kdweibo.com/space/c/photo/load?id=',
	            	photoUrl = user.photoUrl || defaultUrl,
	            	userId = user.id,
					isSelectedCls = this.selectedMembers[userId] ? ' checked' : ''; 
	    			user.jobTitle = user.jobTitle || '职员';
	    		t._('<li class="contact-detail' + isSelectedCls + '" data-userid="' + userId + '">')
	    			._('<div class="contact-detail-inner clearfix">')
		        		._('<span class="contact-checkbox"></span>')
		        		._('<img src="' + photoUrl + '">')
		        		._('<div class="contact-content">')
		        			._('<h3>' + user.name + '</h3>')
		        			._('<p>' + user.jobTitle + '</p>')
		        		._('</div>')
		        	._('</div>')
	        	._('</li>');
	    	}

	    	t._('</ul>');

	    	return t.toString();
	    },

	    destroy: function(){
	    	this.clearXhr();
	    	this.unbindHashChangEvt();
	    	this.resetParentEleOverflow();
	    	
	    	if(this._$root){
	    		this._$root.remove();
	    		this._$root = null;
	    	}
	    },

	    clearXhr: function () {
	    	for (var i = 0, len = this.xhrList.length; i < len; i++) {
	    		this.xhrList[i].abort();
	    	}
	    },
	   
        commonRequest: function(data){
			var deferred = $.Deferred();							
			$.ajax({
				type: data.protocol || 'get',
				cache: false,
				dataType: 'json',
				url: data.url,
				data: data.param
			}).done(function (resp) {
				if (resp.success === true) {
					deferred.resolve(resp);
				} 
				else {
					deferred.reject(resp);
				}
			}).fail(function (resp) {
				deferred.reject(resp);
			});		
			return deferred.promise();
		},

		addUrlParam: function(url, str){
			if(url.indexOf('?') <= 0){
				url = url + '?';
			}
			else{
				url = url + '&';
			}
			return url + str;
		},

		handleCallback: function(groupId, userId){

		},

		// 设置父元素overflow为hidden，使得选人组件铺满父窗口
		setParentEleOverflow: function(){
			this.parentEleOverflow = this._$parentElement.css('overflow');
			this._$parentElement.css('overflow', 'hidden');
		},

		resetParentEleOverflow: function(){
			this._$parentElement.css('overflow', this.parentEleOverflow);
		},

		resetHistory: function(){
			if(this.historyLen > 0){
				window.history.go(-this.historyLen );
			}
		},

		showTips: function(tips, time, callback){
			var $promptMsg = $(".lapp-person-select-wrap .person-select-prompt-msg"),
                fadeOutTime = time || 500;

            $promptMsg.html(tips).fadeIn('slow', function(){
            	$promptMsg.fadeOut(fadeOutTime, function(){
            		if(callback){
            			setTimeout(callback, 300);
            		}
            	});
            });
		}

	};

	function SimpleTemplate () {
        this.parts = [];
        this._pushAll(arguments);
    }

    SimpleTemplate.prototype = {
        _: function () {
            this._pushAll(arguments);
            return this;
        },

        toString: function () {
            return this.parts.join('');
        },

        _pushAll: function (arr) {
            var i, n = arr.length;
            for (i = 0; i < n; i++) {
                this.parts.push(arr[i]);
            }
        }
    };

    function htmlEntities(str) {
        if (typeof str === 'undefined') return '';
        return str.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;');
    }

    function escapeContent(content){
		return content.replace(/\n|\r/g, '<br>').replace(/ /g, '&nbsp;');
	}

	window.LappPersonSelect = window.LappPersonSelect || LappPersonSelect;
})(window, window.Zepto || window.jQuery);