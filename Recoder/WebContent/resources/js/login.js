$(document).ready(function(){
    $(".container").fadeIn(1000);
    $(".s2class").css({"color":"#EE9BA3"});
    $(".s1class").css({"color":"#748194"}); 
    $("#left").removeClass("left_hover");
    $("#right").addClass("right_hover");
    $(".signin").css({"display":"none"});
    $(".signup").css({"display":""});
 });

/*(right)일반회원 클릭시*/
 $("#right").click(function(){
    $("#left").removeClass("left_hover");
    $(".s2class").css({"color":"#EE9BA3"});
    $(".s1class").css({"color":"#748194"});
    $("#right").addClass("right_hover");
    $(".signin").css({"display":"none"});
    $(".signup").css({"display":""});
	$(".c11").css({"background-image":"url('https://veerle.duoh.com/images/inspiration/_normal/dualit-coffee-labels-ii-big.jpg')"});
 });

/*(left)공인중개사 클릭시*/
 $("#left").click(function(){
    $(".s1class").css({"color":"#EE9BA3"});
    $(".s2class").css({"color":"#748194"}); 
    $("#right").removeClass("right_hover");
    $("#left").addClass("left_hover");
    $(".signup").css({"display":"none"});
    $(".signin").css({"display":""});
	$(".c11").css({"background-image":"url('https://i.pinimg.com/736x/b8/09/22/b80922f6ea2daaf36a6627378662803b--deck-of-cards-phone-wallpapers.jpg')"});
 });
