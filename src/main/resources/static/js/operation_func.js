function getOperationPanel() {
    $("#loader").show();
    $.ajax({
        type: 'GET',
        url: '/operation-panel',
        cache: true,
        success: function(text){
            $("#page-wrapper").html(text);
            $("#loader").hide();

        }
    });


}

function operationsFormReset(){
    $("#report_creating_in_progress").val("0");
    $("#report_view").val("0");
    $("#org_group_name").val("");
    $("#org_group_id").val("0");
    $("#org_name").val("");
    $("#org_id").val("0");
    $("#time").prop('selectedIndex', 0);
    chartOrgGroupSelect(1);


    var date = $.datepicker.formatDate('yy-mm-dd', new Date());
    $("#reportrange").val(date + " - " + date);

    getLastOperations();

}


function getLastOperations() {


    if($("*").is("#operation-panel-body")){
        if($("#report_creating_in_progress").val() === "0" && $("#report_view").val() === "0"){
            $("#loader").show();
            $.ajax({
                type: 'GET',
                url: '/get-last-operations',
                cache: false,
                data: {
                    org_group_id: $("#org_group_id").val(),
                    org_id: $("#org_id").val()
                },
                success: function(text){
                    $("#operation-panel-body").html(text);
                    $("#loader").hide();

                },
            });

        }

    }

}

function opShowInfo(id) {

    $("#loader").show();
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
            $("#loader").hide();

        }
    });

}

function closeOpInfo() {

    $('.menu_mobile').removeClass('active');
    $('.menu_mobile').html("");
}




