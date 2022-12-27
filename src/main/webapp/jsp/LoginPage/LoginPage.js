$(document).ready(function () {
    $("#submit").click(function(){
        console.log("first")
        $.ajax({
            type: "POST",
            url: "/login/loginAction.action",
            data: {
                "email": $("#email-input").val(),
                "password":$("#paswrd-input").val()
            }
    })
  })
})