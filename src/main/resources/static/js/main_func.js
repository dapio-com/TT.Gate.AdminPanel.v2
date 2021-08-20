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

                var formattedNumber = new Intl.NumberFormat("ru-RU",{minimumFractionDigits: 0});

                $("#total-orgs").html(formattedNumber.format(item.total_orgs));
                $("#total-terminals").html(formattedNumber.format(item.total_terminals));
                $("#total-operations").html(formattedNumber.format(item.total_operations));
            });

        }
    });

}

function getOperationsPerMinute() {

    $.ajax({
        type: "GET",
        url: "/ops-per-minute",
        cache: false,
        success: function (text) {
            $("#operations-per-minute").html(text);
        }
    });

}

function getErrors(){

        $.ajax({
            url: "/get-errors",
            type: "GET",
            success: function (text) {
                $("#error-panel-body").html(text);
            }
        });

}



