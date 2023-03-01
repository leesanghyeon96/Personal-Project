$(document).ready(function(){
    $(".header_div2_menu_ul").mouseenter(function(){    // 마우스오버
        $("#header_div2_submenu").show();   //서브메뉴
        $("#header_div2_submenu").css('background-color', 'white');
        
        $(".header_div2_logo_text").css('color', 'black');  //로고 텍스트
        $(".header_div2_logo").css('background-color', 'white');    //로고 배경화면
        
        $(".pr1").css('color','black'); // 메인메뉴 텍스트
        $("#n1").css('border-bottom','1px solid black');

    });
    $(".header_div2").mouseleave(function(){
        $("#header_div2_submenu").hide();

        $(".header_div2_logo_text").css('color', 'black');  //로고 텍스트
        $(".header_div2_logo").css('background', 'none');   //로고 배경화면

        $("#n1").css('border','0');
    });
});




