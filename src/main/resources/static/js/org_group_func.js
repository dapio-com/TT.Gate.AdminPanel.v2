function getOrgGroupPanel() {
    $("#loader").show();
    $.ajax({
        type: 'GET',
        url: '/org-group-panel',
        cache: false,
        success: function(text){
            $("#page-wrapper").html(text);
            $("#loader").hide();

        }
    });


}

function cleanOrgGroupForm() {

    $("#org_group_name").val("");
    $("#org_group_description").val("");
}


function orgGroupAddCheck() {
    $("#loader").show();
    var org_group_name = $("#org_group_name").val().trim();
    var org_group_description = $("#org_group_description").val().trim();

    //alert(org_name);
    $.ajax({
        type: "POST",
        url: "/org-group-check",
        cache: false,
        data: {
            org_group_name : org_group_name
        },
        success: function (bool) {
            if(bool){
                cleanOrgGroupForm();
                alert("Такая группа уже существует");
                $("#loader").hide();
            } else {
                orgGroupAdd(org_group_name, org_group_description);
                //cleanOrgGroupForm();
            }


        }
    });

}
function orgGroupAdd(org_group_name, org_group_description) {

    if (org_group_name.toString().length > 0){

        $.ajax({
            type: "POST",
            url: "/org-group-add",
            cache: false,
            data: {
                org_group_name: org_group_name,
                org_group_description: org_group_description
            },
            success: function (text) {
                $("#org-group-panel-body").html(text);
                $("#loader").hide();
                cleanOrgGroupForm();
            }
        });
    } else {
        alert ("Введите Наименование Группы !");
        $("#loader").hide();
    }

    //cleanOrgGroupForm();

}



const confirmOrgGroupDelete = async (id) => {

    const confirm = await ui.confirm("Подтвердить удаление группы ?");

    if(confirm){
        orgGroupDelete(id);
        //return true;
    }

};

function orgGroupDelete(id) {
    $("#loader").show();
        $.ajax({
            type: "POST",
            url: "/org-group-delete",
            cache: false,
            data: {
                id: id
            },
            success: function (text) {
                if(text.length === 0){
                    alert("Невозможно удалить ! Существуют привязанные организации !");
                    $("#loader").hide();
                } else {
                    //cleanOrgGroupForm();
                    $("#page-wrapper").html(text);
                    $("#loader").hide();
                }

            }
        });

}


function getOrgGroupEditForm(id){
    $("#loader").show();
    $.ajax({
        type: "GET",
        url: "/org-group-edit-form",
        cache: false,
        data: {
            id: id
        },
        success: function (text) {
            $("#org_group_form").html(text);
            $("#loader").hide();

        }
    });

}

function orgGroupEdit(id) {

    var org_group_name = $("#org_group_name").val().trim();
    var org_group_description = $("#org_group_description").val().trim();

    if (org_group_name.toString().length > 0){
        $("#loader").show();
        $.ajax({
            type: "POST",
            url: "/org-group-edit",
            cache: false,
            data: {
                id: id,
                org_group_name: org_group_name,
                org_group_description: org_group_description
            },
            success: function (text) {

                //cleanOrgGroupForm();
                $("#page-wrapper").html(text);
                $("#loader").hide();


            }

        });
    } else {
        alert ("Введите Наименование Группы !");
    }

    //cleanFormOrgAdd();
}



function showOrgList(org_group_id) {

    $("#loader").show();

    $.ajax({
        type: "GET",
        url: "/org-list",
        cache: false,
        data: {
            org_group_id: org_group_id
        },
        success: function (text) {
            //alert(text);
            $(".list_text").html(text);
            $("#loader").hide();

        }
    });

    $('#list').show();

}