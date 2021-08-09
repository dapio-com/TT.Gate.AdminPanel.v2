function getMainPanel() {

    $.ajax({
        type: 'GET',
        url: '/main-panel',
        cache: false,
        success: function(text){
            $("#page-wrapper").html(text);
        }
    })

}

function getCounters() {

    $.ajax({
        url: "/get-counters",
        dataType: "json",
        type: "GET",
        success: function (data) {
            //resultData = data;

            $.map(data, function (item) {
                console.log(item);
                $("#total-orgs").html(item.total_orgs);
                $("#total-terminals").html(item.total_terminals);
                $("#total-operations").html(item.total_operations);
            });

        }
    });

}


