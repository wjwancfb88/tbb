$(function(){
    $(window).scroll(function() {
    //$("body").css({"background-position":"center "+$(window).scrollTop()+""});
    if($(window).scrollTop()>=87){
    $(".topW").addClass("fixedNav");
    }else{
    $(".topW").removeClass("fixedNav");
    }
    });
 });

$(function(){
    $(window).scroll(function() {
    //$("body").css({"background-position":"center "+$(window).scrollTop()+""});
    if($(window).scrollTop()>=45){
    $(".topW2").addClass("fixedNav");
    }else{
    $(".topW2").removeClass("fixedNav");
    }
    });
 });


