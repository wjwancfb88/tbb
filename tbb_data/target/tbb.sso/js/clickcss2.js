$(function(){
 $(".more").click(function(){
 $(this).toggleClass("more2");
 $(".dp").toggleClass("dp2");
 })
})
$(function() {
    $(".add_main02 a").eq(0).addClass("clickclass");
    $(".add_main02 a").bind("click",
    function() {
        $(".add_main02 a:not(this)").removeClass("clickclass");
        $(this).addClass("clickclass");
    });
});
	