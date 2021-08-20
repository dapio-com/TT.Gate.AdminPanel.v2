function getOperationPanel() {

    $.ajax({
        type: 'GET',
        url: '/operation-panel',
        cache: false,
        success: function(text){
            $("#page-wrapper").html(text);
        }
    })

}

function opShowInfo(id) {

    $('.menu_mobile').removeClass('hidden').addClass('active');

    $.ajax({
        type: "GET",
        url: "/op_show-info",
        cache: false,
        data: {
            id: id
        },
        success: function (text) {
            //alert(text);
            $(".menu_mobile").html(text);
        }
    });
}

function closeOpInfo() {

    $('.menu_mobile').removeClass('active');
    $('.menu_mobile').html("");
}