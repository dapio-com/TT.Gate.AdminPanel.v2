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
                cleanTerminalForm();
                alert ("Терминал " + terminal_tid + " уже существует");
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
