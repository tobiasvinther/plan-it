$(document).ready(function (){
    $('button[type="submit"]').prop('disabled', true);
    $('#confirmPassword').prop('disabled', true);
    $('#PasswordMessage ul li').hide();
    $('input[type="password"]').keyup(function validPassword(){
        var pwd = $('#password').val();
        if(pwd == ""){
            $('#empty').show().html('Password must not be empty')
        }else {
            $('#empty').hide();
        }if(pwd.length < 5){
            $('#length').show().html('Password must be at least 5 characters')

        }else {
            $('#length').hide();
        }if(!pwd.match(/[A-z]/)){
            $('#letter').show().html('Password must contain at least one letter')
        }else {
            $('#letter').hide();
        }if(!pwd.match(/[A-Z]/)){
            $('#capital').show().html('Password must contain at least one capital letter')
        }else {
            $('#capital').hide();
        }
    });
    $('input[type="password"]').on('keyup',function (){
        var pwd = $('#password').val();
       if(pwd != "" && pwd.length >= 5 && pwd.match(/[A-z]/) && pwd.match(/[A-Z>]/)) {
           $('#confirmPassword').prop('disabled', false);
       }else {
           $('#confirmPassword').prop('disabled', true);
       }
    });

    $('#confirmPassword').on('keyup', function () {
        if ($('#password').val() == $('#confirmPassword').val()) {
            $('#matcher').show().removeClass("list-group-item list-group-item-danger").html('Passwords are matching')
                .addClass("list-group-item list-group-item-success");
            $('button[type="submit"]').prop('disabled', false);
        } else {
            $('#matcher').show().removeClass("list-group-item list-group-item-success").html('Passwords must be the same')
                .addClass("list-group-item list-group-item-danger");
            $('button[type="submit"]').prop('disabled', true);
        }
    });
});