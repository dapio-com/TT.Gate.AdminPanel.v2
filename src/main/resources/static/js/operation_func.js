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

function getLastOperationsInterval() {
    setInterval(function (){getLastOperations($("#last_operations_for_org_id").val())}, 10000);
}

function getLastOperations() {

    if($("#last_operations_for_org_id").val() > -1){
        $.ajax({
            type: 'GET',
            url: '/get-last-operations',
            cache: false,
            data: {
                org_id: $("#last_operations_for_org_id").val()
            },
            success: function(text){
                $("#operation-panel-body").html(text);
            }
        })

    }

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


function operationsFormReset() {

    $("#org_name").val("");
    $("#last_operations_for_org_id").val("0");


}


function getReport() {


    window.open("/get-report?org_id="+$("#last_operations_for_org_id").val()+"&date="+$("#reportrange").val(), '_blank');
    // $.ajax({
    //     type: 'GET',
    //     url: '/get-report',
    //     cache: false,
    //     data: {
    //         org_id: $("#last_operations_for_org_id").val(),
    //         date: $("#reportrange").val()
    //     },
    //     xhrFields: {
    //         // make sure the response knows we're expecting a binary type in return.
    //         // this is important, without it the excel file is marked corrupted.
    //         responseType: 'arraybuffer'
    //     }
    //     // success: function(text){
    //     //     $("#operation-panel-body").html(text);
    //     // }
    // }).done(function (data, status, xmlHeaderRequest) {
    //     var downloadLink = document.createElement('a');
    //     var blob = new Blob([data],
    //         {
    //             type: xmlHeaderRequest.getResponseHeader('Content-Type')
    //         });
    //     var url = window.URL || window.webkitURL;
    //     var downloadUrl = url.createObjectURL(blob);
    //     var fileName = '';
    //
    //
    //
    //     if (typeof window.navigator.msSaveBlob !== 'undefined') {
    //         window.navigator.msSaveBlob(blob, fileName);
    //     } else {
    //         if (fileName) {
    //             if (typeof downloadLink.download === 'undefined') {
    //                 window.location = downloadUrl;
    //             } else {
    //                 downloadLink.href = downloadUrl;
    //                 downloadLink.download = fileName;
    //                 document.body.appendChild(downloadLink);
    //                 downloadLink.click();
    //             }
    //         } else {
    //             window.location = downloadUrl;
    //         }
    //
    //         setTimeout(function () {
    //                 url.revokeObjectURL(downloadUrl);
    //             },
    //             100);
    //     }
    // });
    //


}

