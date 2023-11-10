
function enterkey() {
    if (event.keyCode === 13) {
        document.getElementById("all_search_btn").click();
    }
}

// 검색
function prodSearchBtn(obj) {

    // 키워드가 없거나 공백일 때 전체 상품으로 검색
    let noSearch = $("#prod_name").val();
    if (!noSearch || noSearch.trim() == "") {
        alert("전체 상품/레시피가 검색됩니다");
    }

    // 검색결과리스트 출력 부분 변수 선언
    let searchListArea = $(".searchArea");

    // let btns = document.querySelectorAll('.search_btn');
    // $('.search_btn').removeClass('active');

    // 사용자가 입력한 키워드를 가져와서 변수 선언
    // console.log(obj.getAttribute('data-mode'));
    let mode = obj.getAttribute('data-mode');

    if(mode == 'z'){
        mode = $('.active').data('mode');
        // console.log("mode : " + mode);
        // console.log($(`[data-mode="mode"]`));
        $(`[data-mode="${mode}"]`).addClass('active');
    }else{
        $('.search_btn').removeClass('active');
        obj.classList.add('active');
    }
    // alert(mode);

    let prod_name = document.getElementById("prod_name").value;
    // console.log(prod_name);

    if(mode ==='p'){

        $.ajax({
            type: 'post',
            url: '/user/prodSeach',
            data: {"prod_name" : prod_name},
            // data:JSON.stringify(jsonObj),
            // contentType: 'application/json; charset=utf8',
            success: (data) => {
                let productViewLine = '</div><div class="d-flex w-100 justify-content-center mt-3">';
                let productCount = 0;

                // if(data) callback(data);
                if(data.length != 0 ){
                    for(let i=0; i < data.length; i++ ){
                        // productViewLine +=
                            // + '<p>'+data[i].prod_name+'<img src="/image/'+data[i].prod_thumbnail+'" width="100px"/>'+' :: '+data[i].prod_comment+'</p>';
                            productViewLine += '<div class="card me-2 mb-3" style="width:265px; border:none; height:300px;">'
                                + '<div class="d-flex justify-content-center">'
                                + '<a href="/user/productView?prod_code=' + data[i].prod_code + '" style="height:180px; overflow:hidden;">'
                                + '<img class="card-image" src="/image/' + data[i].prod_thumbnail + '" alt="Card image" style="width:70%">'
                                + '</a></div>'
                                + '<div class="mt-3" style="width:70%;">'
                                + '<h7  style="font-size:15px;">'
                                + '<b class="mt-2">' + data[i].prod_brand + data[i].prod_name + '</b></h7>'
                                + '<p class="mt-3">' + data[i].prod_price + '</p>'
                                + '</div>'
                                + '</div>';
                            if ((i+1) % 4 == 0) {
                                productViewLine += '</div>'
                                    + '<div class="d-flex justify-content-center mt-3">';
                            }
                            if((data.length-(i+1))<4 && ((i+1)%4==0)){
                                productViewLine += '</div>'
                                    + '<div class="d-flex justify-content-start mt-3" style="width:1232px;margin:auto; padding-left: 4.2em">';
                            }
                                productCount++;
                        }
                } else {
                    productViewLine += "검색된 결과가 없습니다";
                }
                document.getElementById('prodspanCnt').innerHTML='('+productCount+')';
                searchListArea.html(productViewLine);
            },
            error: () => {
                alert("상품 에러");
            }
        });
    }else if(mode ==='r'){
        $.ajax({
            type: 'post',
            url: '/user/recipeSeach',
            data: {"prod_name" : prod_name},
            // data:JSON.stringify(jsonObj),
            // contentType: 'application/json; charset=utf8',
            success: (data) => {

                // console.log(data);
                // if(data) callback(data);
                let recipeViewLine='</div><div class="d-flex w-100 justify-content-center mt-3">';
                let recipeCount = 0;

                // if(data) callback(data);
                if(data.length != 0 ){
                    for(let i=0; i < data.length; i++ ){
                        // str +='<p>'+data[i].rcp_NM+' :: '+data[i].rcp_PAT2+'</p>';
                        recipeViewLine += '<div class="card me-2 mb-3" style="width:265px; border:none; height:300px;">'
                            + '<div class="d-flex justify-content-center">'
                            + '<a href="/user/recipeView?id='+data[i].id+'" style="height:180px; overflow:hidden;">'
                            + '<img class="card-image" src="'+data[i].att_FILE_NO_MAIN+'" alt="Card image" style="width:70%">'
                            + '</a></div>'
                            + '<div class="mt-3" style="width:70%;">'
                            + '<h7  style="font-size:15px;">'
                            + '<b class="mt-2">'+ data[i].rcp_NM +'</b></h7>'
                            + '<p class="mt-3">['+data[i].rcp_PAT2+'/'+data[i].rcp_WAY2+']</p>'
                            + '</div>'
                            + '</div>';
                        if ((i+1) % 4 == 0) {
                            recipeViewLine += '</div>'
                                + '<div class="d-flex justify-content-center mt-3">';
                        }
                        if((data.length-(i+1))<4 && ((i+1)%4==0)){
                            recipeViewLine += '</div>'
                                + '<div class="d-flex justify-content-start mt-3" style="width:1232px;margin:auto; padding-left: 4.2em">';
                        }
                        recipeCount++;
                    }
                }else{
                    recipeViewLine += "검색된 결과가 없습니다";
                }
                document.getElementById('recipeCnt').innerHTML='('+recipeCount+')';
                searchListArea.html(recipeViewLine);
            },
            error: () => {
                alert("레시피 에러");
            }
        });
    }
}