
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
    
</head>
<body>
        <h1>Login Page</h1>
        <input type="text" placeholder="Email" id="email-input">
        <input type="text" placeholder="password" id="paswrd-input">
        <button type="submit" id="submit-btn">Login</button>
<script>
    $(document).ready(function() {
        console.log("ready")

      
        $("#submit-btn").click(async function() {
            console.log("first")
            var email = $("#email-input").val();
            var password = $("#paswrd-input").val();
            var js = JSON.stringify( {
                "username":"vsk1@gmail.com",
                "password":"vsk"
            })
            console.log(js)
            $.ajax({
            type: "POST",
            url: "/ecommerce/login/loginAction.action",
            async:true,
            data: js,
            contentType: "application/json; charset=utf-8",
            success: function(response){
            	console.log(response.model)
                if(response.model.role=='admin'){
                    console.log("user",response.model.username)
                    window.location.href = 'userPage.jsp'
                    return;

                }
                if (response.model.role=='admin') {
                    console.log("admin",respionse.UUID)
                    window.location.href = 'adminPage.jsp'
                    return;
                }

                alert("invalid fields")




            },
            error: function (xhr, exception, thrownError) {
            console.log("error==>"+exception)
                    }
            })
         })

        })
    
</script>

</body>
</html>

