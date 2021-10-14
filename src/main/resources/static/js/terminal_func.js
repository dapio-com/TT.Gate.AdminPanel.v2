function getTerminalPanel() {
    $("#loader").show();
    $.ajax({
        type: 'GET',
        url: '/terminal-panel',
        cache: false,
        success: function(text){
            $("#page-wrapper").html(text);
            $("#loader").hide();

        }
    });


}

function cleanTerminalTid() {
    $("#terminal_tid").val("");
}
function cleanTerminalForm() {

    $("#terminal_org_name").val("");
    $("#terminal_org_id").val("0");
    $("#terminal_tid").val("");
    $("#terminal_tsp").val("");
}

function terminalAddCheck() {
    $("#loader").show();
    var terminal_org_id = $("#terminal_org_id").val();
    var terminal_tid = $("#terminal_tid").val().trim();
    var terminal_tsp = $("#terminal_tsp").val().trim();

    //alert(org_name);
    $.ajax({
        type: "POST",
        url: "/terminal-check",
        cache: false,
        data: {
            terminal_tid : terminal_tid
        },
        success: function (bool) {
            if(bool){
                alert ("Терминал " + terminal_tid + " уже существует");
                $("#terminal_tid").val("");
                $("#loader").hide();

            } else {
                terminalAdd(terminal_org_id, terminal_tid, terminal_tsp);
                cleanTerminalTid();
            }
        }
    });

}
function terminalAdd(terminal_org_id, terminal_tid, terminal_tsp) {

    if (terminal_tid.toString().length > 7 && terminal_org_id > 0){

        $.ajax({
            type: "POST",
            url: "/terminal-add",
            cache: false,
            data: {
                terminal_org_id: terminal_org_id,
                terminal_tid: terminal_tid,
                terminal_tsp: terminal_tsp
            },
            success: function (text) {
                $("#terminal-panel-body").html(text)
                $("#loader").hide();
                cleanTerminalTid();

            }
        });
    } else {
        alert ("Укажите TID и привяжите к Организации");
        $("#loader").hide();
    }



}



function getTerminalEditForm(id){
    $("#loader").show();
    $.ajax({
        type: "GET",
        url: "/terminal-edit-form",
        cache: false,
        data: {
            id: id
        },
        success: function (text) {
            $("#terminal_form").html(text);
            $("#loader").hide();

        }
    });

}

function terminalEdit(id) {

    var terminal_org_name = $("#terminal_org_name").val();
    var terminal_org_id = $("#terminal_org_id").val();
    var terminal_tid = $("#terminal_tid").val().trim();
    var terminal_tsp = $("#terminal_tsp").val().trim();
    var terminal_status = $("#terminal_status").val();

    if (terminal_tid.length > 0 && terminal_org_name.length > 0){
        $("#loader").show();
        $.ajax({
            type: "POST",
            url: "/terminal-edit",
            cache: false,
            data: {
                terminal_org_id: terminal_org_id,
                id: id,
                terminal_tid: terminal_tid,
                terminal_tsp: terminal_tsp,
                terminal_status: terminal_status
            },
            success: function (text) {
                $("#page-wrapper").html(text);
                $("#loader").hide();
                cleanTerminalForm();

            }
        });
    } else {
        alert ("Введите Наименование Организации\nили\nTID Терминала");
    }
    //cleanFormOrgAdd();

}

function terminalStatus() {

    var terminalStatus = $("#terminal_status").val();

    if(terminalStatus === "1"){
        $("#terminal_status").val("0");
        $("#terminal_status_btn").html("ВЫКЛ.");
        $("#terminal_status_btn").css('background-color', '#f0ad4e');
    } else {
        $("#terminal_status").val("1");
        $("#terminal_status_btn").html("ВКЛ.");
        $("#terminal_status_btn").css('background-color', '#5cb85c');


    }

}


const confirmTerminalDelete = async (id) => {

    const confirm = await ui.confirm("Подтвердить удаление записи о терминале ?");

    if(confirm){
        terminalDelete(id);
        //return true;
    }

};


function terminalDelete(id) {

    $("#loader").show();
        $.ajax({
            type: "POST",
            url: "/terminal-delete",
            cache: false,
            data: {
                id: id
            },
            success: function (text) {
                cleanTerminalForm();
                $("#terminal-panel-body").html(text);
                $("#loader").hide();


            }
        });


}




