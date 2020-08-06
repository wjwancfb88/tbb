(function($){
	$.fn.tabs=function(paneSelector){
		$(paneSelector).hide().eq(0).show();
		var current=this.eq(0).addClass("active");
		this.each(function(i){
			$(this).click(function(){
				$(paneSelector).hide().eq(i).show();
				current.removeClass("active");
				current=$(this);
				current.addClass("active");
			});
		});
	}
})(jQuery);

(function($){
	$.fn.tabs2=function(paneSelector){
		$(paneSelector).hide().eq(1).show();
		var current=this.eq(1).addClass("active");
		this.each(function(i){
			$(this).click(function(){
				$(paneSelector).hide().eq(i).show();
				current.removeClass("active");
				current=$(this);
				current.addClass("active");
			});
		});
	}
})(jQuery);