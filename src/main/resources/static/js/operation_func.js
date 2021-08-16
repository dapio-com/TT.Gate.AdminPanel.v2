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