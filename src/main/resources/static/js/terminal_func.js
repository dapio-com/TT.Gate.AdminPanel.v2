function getTerminalPanel() {

    $.ajax({
        type: 'GET',
        url: '/terminal-panel',
        cache: false,
        success: function(text){
            $("#page-wrapper").html(text);
        }
    })

}

function cleanTerminalForm() {

    $("#terminal_org_name").val("");
    $("#terminal_org_id").val("");
    $("#terminal_tid").val("");
    $("#terminal_tsp").val("");
}

function terminalAddCheck() {

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
        success: function (text) {
            if(text.trim() === 'true'){
                alert ("Терминал " + terminal_tid + " уже существует");
                $("#terminal_tid").val("");
            } else {
                terminalAdd(terminal_org_id, terminal_tid, terminal_tsp);
                cleanTerminalForm();
            }
        }
    });
}
function terminalAdd(terminal_org_id, terminal_tid, terminal_tsp) {

    //var org_name = $("#org_name").val().trim();
    //var org_owner = $("#org_owner").val().trim();
    if (terminal_tid.length !== 0 && terminal_org_id != null){
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
                $("#terminal-panel-body").html(text);
                let button = $("#allBtn");
                button.val(parseInt(button.val()) + 1);
            }
        });
    } else {
        alert ("Укажите TID и привяжите к Организации");
    }

    cleanTerminalForm();

}


function terminalShowAll() {
    var size = $("#allBtn").val();
    $.ajax({
        type: "GET",
        url: "/terminal-show-all?size=" + size,
        cache: false,
        success: function (text) {
            $("#terminal-panel-body").html(text);
        }
    });


}

function getTerminalEditForm(id){
    $.ajax({
        type: "GET",
        url: "/terminal-edit-form",
        cache: false,
        data: {
            id: id
        },
        success: function (text) {
            $("#terminal_form").html(text);
        }
    })
}

function terminalEdit(id) {

    var terminal_org_name = $("#terminal_org_name").val();
    var terminal_org_id = $("#terminal_org_id").val();
    var terminal_tid = $("#terminal_tid").val().trim();
    var terminal_tsp = $("#terminal_tsp").val().trim();
    var terminal_status = $("#terminal_status").val();

    console.log(terminal_org_name);
    console.log(terminal_org_id);
    console.log(terminal_tid);
    if (terminal_tid.length > 0 && terminal_org_name.length > 0){
        console.log("in AJAX");
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
                if(text === "ok"){
                    cleanTerminalForm();
                    getTerminalPanel();
                }
            }
        });
    } else {
        alert ("Введите Наименование Организации\nили\n! TID Терминала !");
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

function terminalDelete(id) {

    if(confirm("Вы действиельно хотите\nудалить запись об этом\nтерминале ?")){
        $.ajax({
            type: "POST",
            url: "/terminal-delete",
            cache: false,
            data: {
                id: id
            },
            success: function (text) {
                if(text === "ok"){
                    cleanTerminalForm();
                    getTerminalPanel();
                }
            }
        });
    }

}

function terminalShowAll() {

    $.ajax({
        type: "GET",
        url: "/terminal-show-all",
        cache: false,
        success: function (text) {
            $("#terminal-panel-body").html(text);
        }
    });


}


