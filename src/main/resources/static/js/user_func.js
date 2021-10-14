function getUserPanel() {
    $("#loader").show();
    $.ajax({
        type: 'GET',
        url: '/user-panel',
        cache: false,
        success: function(text){
            $("#page-wrapper").html(text);
            $("#loader").hide();

        }
    });


}

//function passwordGen(len){
function passwordGen(len){
    // if(len > 10) len = 10;
    // len = len * (-1);
    // return Math.random().toString(36).slice(len);

    //return Math.random().toString(36).slice(-8);
    let password = Math.random().toString(36).slice(-8);
    alert("Пароль : " + password.toUpperCase());
    $("#password").val(password.toUpperCase());

}

function passwordGen2(len){
    var password = "";
    var symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!№;%:?*()_+=";
    for (var i = 0; i < len; i++){
        password += symbols.charAt(Math.floor(Math.random() * symbols.length));
    }
    //return password;
    alert("Пароль : " + password);
    $("#password").val(password);
}


function cleanUserForm() {

    $("#username").val("");
    $("#password").val("");
    $("#userdescription").val("");
}


function userAddCheck() {
    $("#loader").show();

    var userName = $("#username").val().trim();
    var userPassword = $("#password").val();
    var userRole = $("#userrole").val();
    var userOrgGroupId = $("#org_group_id").val();
    var userOrgId = $("#org_id").val();
    var userDescription = $("#userdescription").val().trim();

    //console.log(userName)
    //alert(org_name);
    $.ajax({
        type: "POST",
        url: "/user-check",
        cache: false,
        data: {
            userName : userName
        },
        success: function (bool) {
            //alert(bool);
            if(bool){
                //cleanUserForm();
                alert("Пользователь с таким именем существует");
                $("#loader").hide();
            } else {
                userAdd(userName, userPassword, userDescription, userRole, userOrgGroupId, userOrgId);
                cleanUserForm();
            }

        }
    });

}
function userAdd(userName, userPassword, userDescription, userRole, userOrgGroupId, userOrgId) {

    if (userName.toString().length > 0 && userPassword.toString().length > 0){

        $.ajax({
            type: "POST",
            url: "/user-add",
            cache: false,
            data: {
                userName: userName,
                userPassword: userPassword,
                userRole: userRole,
                userDescription: userDescription,
                userOrgGroupId: userOrgGroupId,
                userOrgId: userOrgId

            },
            success: function (text) {
                $("#user-panel-body").html(text);
                $("#loader").hide().then(cleanUserForm());


            }
        });
    } else {
        alert ("Заполните все поля !");
        $("#loader").hide();
    }

    cleanUserForm();

}

const confirmUserDelete = async (id) => {

    const confirm = await ui.confirm("Подтвердить удаление пользователя ?");

    if(confirm){
        userDelete(id);
        //return true;
    }

};

function userDelete(id) {
    $("#loader").show();
    $.ajax({
        type: "POST",
        url: "/user-delete",
        cache: false,
        data: {
            id: id
        },
        success: function (text) {
            //cleanUserForm();
            $("#page-wrapper").html(text);
            $("#loader").hide();


        }
    });

}

function getUserEditForm(id){
    $("#loader").show();
    $.ajax({
        type: "GET",
        url: "/user-edit-form",
        cache: false,
        data: {
            id: id
        },
        success: function (text) {
            $("#user_form").html(text);
            $("#loader").hide();

        }
    });

}

function userEdit(id) {

    var userName = $("#username").val().trim();
    var userStatus = $("#user_status").val();
    var userPassword = $("#password").val();
    var userDescription = $("#userdescription").val().trim();

    if (userName.toString().length > 0){
        $("#loader").show();
        $.ajax({
            type: "POST",
            url: "/user-edit",
            cache: false,
            data: {
                id: id,
                userName: userName,
                userStatus: userStatus,
                userPassword: userPassword,
                userDescription: userDescription
            },
            success: function (text) {

                //cleanOrgGroupForm();
                $("#page-wrapper").html(text);
                $("#loader").hide();


            }

        });
    } else {
        alert ("Введите Имя пользователя (логин) !");
    }

    //cleanFormOrgAdd();
}


function userStatus() {

    var userStatus = $("#user_status").val();

    if(userStatus === "true"){
        $("#user_status").val("false");
        $("#user_status_btn").html("ВЫКЛ.");
        $("#user_status_btn").css('background-color', '#f0ad4e');
    } else {
        $("#user_status").val("true");
        $("#user_status_btn").html("ВКЛ.");
        $("#user_status_btn").css('background-color', '#5cb85c');


    }

}

function groupInputDisplay() {


    if($("#userrole").val() === "OPERATOR"){
        $(".group_input").removeAttr('disabled');
        $(".group_input").attr('id', 'org_group_name');
        $(".group_input").attr('placeholder', 'группа');
        $(".group_input").val('');

        $("#org_group_id").val("0");
        $("#org_id").val("0");

    } else if($("#userrole").val() === "AGENT") {
        $(".group_input").removeAttr('disabled');
        $(".group_input").attr('id', 'org_name');
        $(".group_input").attr('placeholder', 'орг.');
        $(".group_input").val('');

        $("#org_group_id").val("0");
        $("#org_id").val("0");
    } else {
        $(".group_input").attr('disabled', 'disabled');
        $(".group_input").val('');
        $("#org_group_id").val("0");
        $("#org_id").val("0");

    }

}
