
// 서브메뉴 카테고리 리스트 불러오기
function storeSubmenuOver() {

    $.ajax({
       type: 'get',
       url: '/categoryCodeList',
       success: function (data) {

           let storeCategory = document.querySelector('#storeCategory');
           let addCategoryListStr="<li class='sub_title'><span>푸드</span></li>";

           storeCategory.innerHTML= "<li class='sub_title'><span>푸드</span></li>"

           $.each(data,function(index, value) {
                   // alert(value.category_name);
               addCategoryListStr+="<li><a href='/user/productPage?category_code="+value.category_code+"&" +
                                            "category_name="+value.category_name+"'>"+value.category_name+"</a></li>";

                   storeCategory.innerHTML=addCategoryListStr;
               });

           $("#storeCategory").css('display', 'block');
       }
    });
};

function storeSubmenuOut() {
    $("#storeCategory").css('display', 'none');
}

// 추천상품 슬라이드
$(function (){

    // new menu slider
    $(".new-slider__wrap").slick({
        slidesToShow: 4,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000,
        speed: 850,
        cssEase: "ease-in-out",
        dots: false,
        prevArrow: "<i class='xi-angle-left-thin new-prev new-btn'></i>",
        nextArrow: "<i class='xi-angle-right-thin new-next new-btn'></i>"
    });

});

// 페이지 스크롤 효과
AOS.init(); // 초기화


