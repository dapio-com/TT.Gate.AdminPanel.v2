function getOrgPanel() {

    $.ajax({
        type: 'GET',
        url: '/org-panel',
        cache: false,
        success: function(text){
            $("#page-wrapper").html(text);
        }
    })

}

function cleanOrgForm() {

    $("#org_name").val("");
    $("#org_owner").val("");
}


function orgAddCheck() {

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
        success: function (text) {
            if(text.trim() === 'true'){
                cleanOrgForm();
                alert ("Такая организация уже существует");
            } else {
                orgAdd(org_name, org_owner);
                cleanOrgForm();
            }
        }
    });
}
function orgAdd(org_name, org_owner) {

    //var org_name = $("#org_name").val().trim();
    //var org_owner = $("#org_owner").val().trim();
    if (org_name.length !== 0){
        $.ajax({
            type: "POST",
            url: "/org-add",
            cache: false,
            data: {
                org_name: org_name,
                org_owner: org_owner
            },
            success: function (text) {
                $("#org-panel-body").html(text);
            }
        });
    } else {
        alert ("Введите Наименование Организации !");
    }

    cleanOrgForm();

}

function orgDelete(id) {

    if(confirm("Подтвердить удаление ?")){
        $.ajax({
            type: "POST",
            url: "/org-delete",
            cache: false,
            data: {
                id: id
            },
            success: function (text) {
                if(text === "ok"){
                    cleanOrgForm();
                    getOrgPanel();
                }
            }
        });
    }

}

function getOrgEditForm(id){
    $.ajax({
        type: "GET",
        url: "/org-edit-form",
        cache: false,
        data: {
            id: id
        },
        success: function (text) {
            $("#org_form").html(text);
        }
    })
}

function orgEdit(id) {

    var org_name = $("#org_name").val().trim();
    var org_owner = $("#org_owner").val().trim();

    if (org_name.length !== 0){
        $.ajax({
            type: "POST",
            url: "/org-edit",
            cache: false,
            data: {
                id: id,
                org_name: org_name,
                org_owner: org_owner
            },
            success: function (text) {
                if(text === "ok"){
                    cleanOrgForm();
                    getOrgPanel();
                }
            }
        });
    } else {
        alert ("Введите Наименование Организации !");
    }
    //cleanFormOrgAdd();
}

function orgShowAll() {

    $.ajax({
        type: "POST",
        url: "/org-show-all",
        cache: false,
        success: function (text) {
            $("#org-panel-body").html(text);
        }
    });


}


