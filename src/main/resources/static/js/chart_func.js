function getChartPanel() {
    $("#loader").show();
    $.ajax({
        type: 'GET',
        url: '/chart-panel',
        cache: false,
        success: function(text){
            $("#page-wrapper").html(text);
            $("#loader").hide();

        }

    });


}


function parseMonths(data){

    let operationsTotal = 0;
    let amountTotal = 0;

    let y = "0000";
    let m = "00";

    let timeLine = [];
    let operationsCounter = 0;
    let amountCounter = 0;
    let operationsLine = [];
    let amountLine = [];


    for (let i = 0; i < data.length ; i++) {

        // if(data[i].op_status === 1){
        //     amountTotal += data[i].op_amount;
        // }
        operationsTotal ++;
        amountTotal += data[i].op_amount;


        if(y !== data[i].op_date_time.substr(0, 4)){
            y = data[i].op_date_time.substr(0, 4);
            //yearsArr.push(y);

            //operationsCounter = 0;
        }

        if(m !== data[i].op_date_time.substr(5, 2)){
            m = data[i].op_date_time.substr(5, 2);
            //monthsArr.push(m);

            timeLine.push(y + "." + m);
            if(operationsCounter > 0) {
                operationsLine.push(operationsCounter);
                amountLine.push(amountCounter);
            }

            operationsCounter = 0;
            amountCounter = 0;


        }




        operationsCounter ++;
        amountCounter += data[i].op_amount;


    }



    //operationsLine += operationsCounter + ", ";
    operationsLine.push(operationsCounter);
    amountLine.push(amountCounter);


    chartJSDraw(operationsTotal, amountTotal, operationsLine, amountLine, timeLine);


}

function parseDays(data){

    let operationsTotal = 0;
    let amountTotal = 0;

    let y = "0000";
    let m = "00";
    let d = "00";

    let timeLine = [];
    let operationsCounter = 0;
    let amountCounter = 0;
    let operationsLine = [];
    let amountLine = [];


    for (let i = 0; i < data.length ; i++) {

        // if(data[i].op_status === 1){
        //     amountTotal += data[i].op_amount;
        // }
        operationsTotal ++;
        amountTotal += data[i].op_amount;


        if(y !== data[i].op_date_time.substr(0, 4)){
            y = data[i].op_date_time.substr(0, 4);
            //yearsArr.push(y);

            //operationsCounter = 0;
        }

        if(m !== data[i].op_date_time.substr(5, 2)){
            m = data[i].op_date_time.substr(5, 2);
            //monthsArr.push(m);

        }

        if(d !== data[i].op_date_time.substr(8, 2)){
            d = data[i].op_date_time.substr(8, 2);
            //daysArr.push(d);

            timeLine.push(y + "." + m + "." + d);
            if(operationsCounter > 0) {
                operationsLine.push(operationsCounter);
                amountLine.push(amountCounter);
            }

            operationsCounter = 0;
            amountCounter = 0;


        }



        operationsCounter ++;
        amountCounter += data[i].op_amount;


    }



    //operationsLine += operationsCounter + ", ";
    operationsLine.push(operationsCounter);
    amountLine.push(amountCounter);


    chartJSDraw(operationsTotal, amountTotal, operationsLine, amountLine, timeLine);

}

function parseHours(data) {

    let operationsTotal = 0;
    let amountTotal = 0;

    let y = "0000";
    let m = "00";
    let d = "00";
    let h = "00";

    let timeLine = [];
    let operationsCounter = 0;
    let amountCounter = 0;
    let operationsLine = [];
    let amountLine = [];


    for (let i = 0; i < data.length ; i++) {

        // if(data[i].op_status === 1){
        //     amountTotal += data[i].op_amount;
        // }
        operationsTotal ++;
        amountTotal += data[i].op_amount;


        if(y !== data[i].op_date_time.substr(0, 4)){
            y = data[i].op_date_time.substr(0, 4);
            //yearsArr.push(y);


        }

        if(m !== data[i].op_date_time.substr(5, 2)){
            m = data[i].op_date_time.substr(5, 2);
            //monthsArr.push(m);

        }

        if(d !== data[i].op_date_time.substr(8, 2)){
            d = data[i].op_date_time.substr(8, 2);
            //daysArr.push(d);


        }

        if(h !== data[i].op_date_time.substr(11, 2)){
            h = data[i].op_date_time.substr(11,2);
            //daysArr.push(d);

            timeLine.push(y + "." + m + "." + d + "|" + h);
            if(operationsCounter > 0) {
                operationsLine.push(operationsCounter);
                amountLine.push(amountCounter);
            }

            operationsCounter = 0;
            amountCounter = 0;
        }


        operationsCounter ++;
        amountCounter += data[i].op_amount;


    }

    //operationsLine += operationsCounter + ", ";
    operationsLine.push(operationsCounter);
    amountLine.push(amountCounter);

    chartJSDraw(operationsTotal, amountTotal, operationsLine, amountLine, timeLine);

}



function getChart2() {

    $("#loader").show();
    $.ajax({
        type: "GET",
        cache: false,
        url: "/get-chart",
        data: {
            org_group_id: $("#org_group_id").val(),
            org_id: $("#org_id").val(),
            date: $("#daterange").val()
        },
        success: function (data) {
            console.log(data);


            if($("#chartType").val() === "1"){
                parseMonths(data);
                $("#loader").hide();
            } else if ($("#chartType").val() === "2"){
                parseDays(data);
                $("#loader").hide();
            } else if ($("#chartType").val() === "3"){
                parseHours(data);
                $("#loader").hide();
            }

            //$("#loader").hide();

        }
    });

}

function poolColors(arrLen, subject) {
    var pool = [];
    for(i = 0; i < arrLen; i++) {
        if(subject === "operation"){
            pool.push("rgba(204, 153, 51)");
        } else if (subject === "amount") {
            pool.push("rgba(51, 102, 153)");
        }

    }
    return pool;
}

function chartJSDraw(operationsTotal, amountTotal, operationsLine, amountLine, timeLine) {

    var formattedNumber = new Intl.NumberFormat("ru-RU",{minimumFractionDigits: 0});


    var chartData = {
        labels: timeLine,
        datasets: [{
            label: "Всего операций (" + formattedNumber.format(operationsTotal) + ")",
            backgroundColor: poolColors(timeLine.length, "operation"),
            data: operationsLine
            //data: amountLine

        },{
            label: "Сумма (" + formattedNumber.format(amountTotal) + ")",
            backgroundColor: poolColors(timeLine.length, "amount"),
            //data: operationsLine
            data: amountLine
        }]

    };

    var chartOptions = {
        plugins: {
            title: {
                display: true,
                text: $("#daterange").val(),
                font: {
                    size: 20
                }
            }
        },
        legend: {
            display: true,
            position: 'top',
            labels: {
                boxWidth: 80,
                fontColor: 'black'
            }
        }
    };



    $("#chartContainer").html("<canvas id='chartCanvas' width='700px' height='180px'></canvas>");
    var chartCanvas = $("#chartCanvas");

    var lineChart = new Chart(chartCanvas, {
        type: 'bar',
        data: chartData,
        options: chartOptions
    });


}


function chartTypeSelect(val) {
    if(val === 1){
        $("#month").removeClass("btn-default").addClass("btn-warning");
        $("#day").removeClass("btn-warning").addClass("btn-default");
        $("#hour").removeClass("btn-warning").addClass("btn-default");

    } else if (val === 2){
        $("#month").removeClass("btn-warning").addClass("btn-default");
        $("#day").removeClass("btn-default").addClass("btn-warning");
        $("#hour").removeClass("btn-warning").addClass("btn-default");

    } else if (val === 3){
        $("#month").removeClass("btn-warning").addClass("btn-default");
        $("#day").removeClass("btn-warning").addClass("btn-default");
        $("#hour").removeClass("btn-default").addClass("btn-warning");

    }
    $("#chartType").val(val);

}


function chartOrgGroupSelect(val){


    if(val === 1){
        $("#org_btn").removeClass("btn-default").addClass("btn-warning");
        $("#org_group_btn").removeClass("btn-warning").addClass("btn-default");

        $("#label_for_org_input").css("display", "block");
        $("#org_name").attr("type", "text");
        $("#org_name").val("");
        $("#org_id").val("0");

        $("#label_for_group_input").css("display", "none");
        $("#org_group_name").attr("type", "hidden");
        $("#org_group_name").val("");
        $("#org_group_id").val("0");


    } else {
        $("#org_btn").removeClass("btn-warning").addClass("btn-default");
        $("#org_group_btn").removeClass("btn-default").addClass("btn-warning");

        $("#label_for_org_input").css("display", "none");
        $("#org_name").attr("type", "hidden");
        $("#org_name").val("");
        $("#org_id").val("0");

        $("#label_for_group_input").css("display", "block");
        $("#org_group_name").attr("type", "text");
        $("#org_group_name").val("");
        $("#org_group_id").val("0");

    }



}




