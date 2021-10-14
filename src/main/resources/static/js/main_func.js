function getMainPanel() {
    $("#loader").show();
    $.ajax({
        type: 'GET',
        url: '/main-panel',
        cache: false,
        success: function(text){
            $("#page-wrapper").html(text);
            $("#loader").hide();

        }
    });



}

function getCounters() {

    if($("*").is("#total-orgs")){
        $("#loader").show();
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

                $("#loader").hide();



            }
        });

    }



}

function getOperationsPerMinute() {

    if($("*").is("#operations-per-minute")){
        $("#loader").show();
        $.ajax({
            type: "GET",
            url: "/ops-per-minute",
            cache: false,
            success: function (text) {
                $("#operations-per-minute").html(text);
                $("#loader").hide();

            }
        });

    }


}

function getErrors(){
    if($("*").is("#error-panel-body")){
        $("#loader").show();
        $.ajax({
            url: "/get-errors",
            type: "GET",
            success: function (text) {
                $("#error-panel-body").html(text);
                $("#loader").hide();

            }
        });

    }


}

function setIntervals(milliseconds){

    setInterval(function (){ getCounters(); getErrors(); getOperationsPerMinute() }, milliseconds);
    setInterval(function (){ getLastOperations() }, milliseconds);



}




