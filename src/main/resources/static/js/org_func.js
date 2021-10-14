function getOrgPanel() {
    $("#loader").show();
    $.ajax({
        type: 'GET',
        url: '/org-panel',
        cache: false,
        success: function(text){
            $("#page-wrapper").html(text);
            $("#loader").hide();

        }
    });


}

function cleanOrgForm() {

    $("#org_group_name").val("");
    $("#org_group_id").val("0");
    $("#org_name").val("");
    $("#org_owner").val("");
}


function orgAddCheck() {
    $("#loader").show();
    var org_group_id = $("#org_group_id").val();
    var org_name = $("#org_name").val().trim();
    var org_owner = $("#org_owner").val().trim();

    //alert(org_name);
    $.ajax({
        type: "POST",
        url: "/org-check",
        cache: false,
        data: {
            org_name : org_name
        },
        success: function (bool) {
            if(bool){
                cleanOrgForm();
                alert("Такая организация уже существует");
                $("#loader").hide();
            } else {
                orgAdd(org_group_id, org_name, org_owner);
                //cleanOrgForm();
            }


        }
    });

}
function orgAdd(org_group_id, org_name, org_owner) {

    //var org_name = $("#org_name").val().trim();
    //var org_owner = $("#org_owner").val().trim();
    if (org_name.toString().length > 0){
        $.ajax({
            type: "POST",
            url: "/org-add",
            cache: false,
            data: {
                org_group_id: org_group_id,
                org_name: org_name,
                org_owner: org_owner
            },
            success: function (text) {
                $("#org-panel-body").html(text);
                $("#loader").hide();
                cleanOrgForm();

            }
        });
    } else {
        alert ("Введите Наименование Организации !");
        $("#loader").hide();
    }

    //cleanOrgForm();

}


const confirmOrgDelete = async (id) => {

    const confirm = await ui.confirm("Подтвердить удаление организации ?");

    if(confirm){
        orgDelete(id);
        //return true;
    }

};


function orgDelete(id) {

    $("#loader").show();
        $.ajax({
            type: "POST",
            url: "/org-delete",
            cache: false,
            data: {
                id: id
            },
            success: function (text) {
                if(text.length === 0){
                    alert("Невозможно удалить ! Существуют привязанные терминалы !")
                } else {
                    cleanOrgForm();
                    $("#page-wrapper").html(text);
                }

                $("#loader").hide();

            }
        });


}

function getOrgEditForm(id){
    $("#loader").show();
    $.ajax({
        type: "GET",
        url: "/org-edit-form",
        cache: false,
        data: {
            id: id
        },
        success: function (text) {
            $("#org_form").html(text);
            $("#loader").hide();
        }

    });

    $("#loader").hide();
}

function orgEdit(id) {

    var org_group_id = $("#org_group_id").val();
    var org_name = $("#org_name").val().trim();
    var org_owner = $("#org_owner").val().trim();

    if (org_name.length > 0){
        $("#loader").show();
        $.ajax({
            type: "POST",
            url: "/org-edit",
            cache: false,
            data: {
                id: id,
                org_group_id: org_group_id,
                org_name: org_name,
                org_owner: org_owner
            },
            success: function (text) {

                cleanOrgForm();
                $("#page-wrapper").html(text);

                $("#loader").hide();

            }

        });
    } else {
        alert ("Введите Наименование Организации !");
    }
    //cleanFormOrgAdd();

}



function showTerminalList(org_id) {

    $("#loader").show();

    $.ajax({
        type: "GET",
        url: "/terminal-list",
        cache: false,
        data: {
            org_id: org_id
        },
        success: function (text) {
            //alert(text);
            $(".list_text").html(text);
            $("#loader").hide();
        }
    });

    $('#list').show();

}


