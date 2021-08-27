function getChartPanel() {

    $.ajax({
        type: 'GET',
        url: '/chart-panel',
        cache: false,
        success: function(text){
            $("#page-wrapper").html(text);
        }
    })

}

function getChart() {

    $.ajax({
        type: "GET",
        cache: false,
        url: "/get-chart",
        data: {
            org_id: $("#org_id").val(),
            date: $("#daterange").val()

        },
        success: function (data) {
            console.log(data);
            chartDraw($("#daterange").val(), data);

        }
    });

}


function chartDraw(daterange, data) {

    var formattedNumber = new Intl.NumberFormat("ru-RU",{minimumFractionDigits: 0});

    var years = 0;
    var month = 0;
    var days = 0;
    var hours = 0;

    var operationsTotal = 0;
    var amountTotal = 0;



    for (let i = 0; i < data.length ; i++) {

        operationsTotal ++;
        amountTotal += data[i].op_amount;
        console.log(data[i].op_date_time);

    }

    console.log(formattedNumber.format(amountTotal));


    var options = {
        animationEnabled: true,
        theme: "light2",
        title:{
            text: daterange
        },
        axisY: {
            //title: "Revenue (Counts)",
            valueFormatString: "#0",
            includeZero: true,
            //suffix: "K",
            //prefix: "£"
        },
        legend: {
            cursor: "pointer",
            itemclick: toogleDataSeries
        },
        toolTip: {
            shared: true
        },
        data: [{
            type: "area",
            name: "Сумма " + formattedNumber.format(amountTotal),
            markerSize: 7,
            showInLegend: true,
            xValueFormatString: "MMMM",
            //yValueFormatString: "£#0K",
            yValueFormatString: "#",
            dataPoints: [
                { x: new Date(2017, 0), y: 12 },
                { x: new Date(2017, 1), y: 15 },
                { x: new Date(2017, 2), y: 12 },
                { x: new Date(2017, 3), y: 17 },
                { x: new Date(2017, 4), y: 20 },
                { x: new Date(2017, 5), y: 21 },
                { x: new Date(2017, 6), y: 24 },
                { x: new Date(2017, 7), y: 19 },
                { x: new Date(2017, 8), y: 22 },
                { x: new Date(2017, 9), y: 25 },
                { x: new Date(2017, 10), y: 21 },
                { x: new Date(2017, 11), y: 19 }
            ]
        }, {
            type: "area",
            name: "Операции " + + formattedNumber.format(operationsTotal),
            markerSize: 7,
            showInLegend: true,
            //yValueFormatString: "£#0K",
            yValueFormatString: "#",
            dataPoints: [
                { x: new Date(2017, 0), y: 8 },
                { x: new Date(2017, 1), y: 12 },
                { x: new Date(2017, 2), y: 9 },
                { x: new Date(2017, 3), y: 11 },
                { x: new Date(2017, 4), y: 15 },
                { x: new Date(2017, 5), y: 12 },
                { x: new Date(2017, 6), y: 13 },
                { x: new Date(2017, 7), y: 9 },
                { x: new Date(2017, 8), y: 7 },
                { x: new Date(2017, 9), y: 14 },
                { x: new Date(2017, 10), y: 18 },
                { x: new Date(2017, 11), y: 14 }
            ]
        }]
    };
    $("#chartContainer").CanvasJSChart(options);

    function toogleDataSeries(e) {
        if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
            e.dataSeries.visible = false;
        } else {
            e.dataSeries.visible = true;
        }
        e.chart.render();
    }


}
