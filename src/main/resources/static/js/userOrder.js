function payBtn(orderList) {

    let pg = $('input[name="radioTxt"]:checked').val();
    let buyerEmail = document.getElementById('user_email').textContent;
    let amount = document.getElementById('cartTotPrice').value;
    let prod_name;

    if(orderList.length > 1) {
        prod_name = orderList[0].prod_name + "외 " + (orderList.length - 1) + "개";
    } else {
        prod_name = orderList[0].prod_name;
    }

    let user_id = $("#user_id").val();
    let user_name = $("#user_name").text();
    let receiver_name = $("#receiver_name").val();
    let receiver_tel = $("#receiver_tel").val();
    let user_zipcode = $("#sample4_postcode").val();
    let user_roaddr = $("#sample4_roadAddress").val();
    let user_detailaddr = $("#user_detailaddr").val();


    let userOrderCheckDTO = {user_id: user_id, user_name: user_name, receiver_name: receiver_name, receiver_tel: receiver_tel, user_zipcode: user_zipcode, user_roaddr: user_roaddr, user_detailaddr: user_detailaddr, prod_name: prod_name};

    let data = {
        orderList: orderList,
        userOrderCheckDTO: userOrderCheckDTO
    }

    IMP.init("imp63140874");
    IMP.request_pay({
            pg: pg,
            pay_method: "card",
            merchant_uid: 'merchant_' + new Date().getTime(),
            amount: amount,
            name: prod_name,
            buyer_email: buyerEmail,
            buyer_name: user_name,
            buyer_tel: receiver_tel,
            buyer_addr: user_roaddr + user_detailaddr,
            buyer_postcode: user_zipcode
        },
        function(rsp) {
            if (rsp.success) {
                $.ajax({
                    type: "post",
                    //카카오페이,신용카드 결제
                    url: "/user/userOrder/pay",
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    success: function () {
                        window.location.href = "/user/userOrder/success";
                    },
                    error: function (error) {
                        console.error("에러:", error);
                    }
                });
            } else {
                alert("결제에 실패하였습니다. 에러 : " + rsp.error_msg);
            }
        });
}

function fastPayBtn(dto) {

    let pg = $('input[name="radioTxt"]:checked').val();
    let buyerEmail = document.getElementById('user_email').textContent;
    let amount = document.getElementById('cartTotPrice').value;
    let prod_name;

    prod_name = dto.prod_name;

    let user_id = $("#user_id").val();
    let user_name = $("#user_name").text();
    let receiver_name = $("#receiver_name").val();
    let receiver_tel = $("#receiver_tel").val();
    let user_zipcode = $("#sample4_postcode").val();
    let user_roaddr = $("#sample4_roadAddress").val();
    let user_detailaddr = $("#user_detailaddr").val();


    let userOrderCheckDTO = {user_id: user_id, user_name: user_name, receiver_name: receiver_name, receiver_tel: receiver_tel, user_zipcode: user_zipcode, user_roaddr: user_roaddr, user_detailaddr: user_detailaddr, prod_name: prod_name};

    let data = {
        userFastPayDTO: dto,
        userOrderCheckDTO: userOrderCheckDTO
    }

    IMP.init("imp63140874");
    IMP.request_pay({
            pg: pg,//카드와 카카오 페이 구분
            pay_method: "card",
            merchant_uid: 'merchant_' + new Date().getTime(),
            amount: amount,
            name: prod_name,
            buyer_email: buyerEmail,
            buyer_name: user_name,
            buyer_tel: receiver_tel,
            buyer_addr: user_roaddr + user_detailaddr,
            buyer_postcode: user_zipcode
        },
        function(rsp) {
            if (rsp.success) {
                $.ajax({
                    type: "post",
                    //바로결제
                    url: "/user/userOrder/fastPay",
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    success: function () {
                        window.location.href = "/user/userOrder/success";
                    },
                    error: function (error) {
                        console.error("에러:", error);
                    }
                });
            } else {
                alert("결제에 실패하였습니다. 에러 : " + rsp.error_msg);
            }
        });
}

function orderCancle(order_no) {
    if(confirm("주문을 취소하시겠습니까?") == true) {
        $.ajax({
            type: "post",
            url: "/user/userOrder/delete",
            data: JSON.stringify(order_no),
            contentType: "application/json; charset=utf-8",
            success: function () {
                window.location.replace("/user/userOrder/orderlist");
                alert("주문취소 되었습니다");
            },
            error: function (error) {
                console.error("에러:", error);
            }
        });
    } else {
        return;
    }
}

function adminOrderCancle(order_no) {
    if(confirm("주문을 취소하시겠습니까?") == true) {
        $.ajax({
            type: "post",
            url: "/admin/order/delete",
            data: JSON.stringify(order_no),
            contentType: "application/json; charset=utf-8",
            success: function () {
                window.location.replace("/admin/order/orderList");
                alert("주문취소 되었습니다");
            },
            error: function (error) {
                console.error("에러:", error);
            }
        });
    } else {
        return;
    }
}