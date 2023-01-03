
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
    <link rel="stylesheet" href="LoginPage.css">
  
</head>
<body>
    <div id="container">
        <h1>Login Page</h1>
        <input type="text" placeholder="Email" id="email-input">
        <input type="text" placeholder="password" id="paswrd-input">
        <button type="submit" id="submit-btn">Login</button>
    </div>
    
    <style>
    
    #container {
    background-color: rgb(255, 255, 255);
    align-items: center;
    display: flex;
    flex-direction: column;
    height: 100vh;
    width: 100vw;
    align-items: center;
    }
    
    #container > input{
        margin: 1rem;
        padding: 1rem;
        width: 20%;
        border-radius: 1rem;
        font-size: larger;
    }
    
    #container>button{
        width: 20%;
        margin: 1rem;
        padding: 1rem;
        border-radius: 1rem;
        font-size: larger;
        background-color: #332d2d;
        color: white;
    }
    
    #container>button:hover{
        width: 20%;
        margin: 1rem;
        padding: 1rem;
        border-radius: 1rem;
        font-size: larger;
        background-color: #605b5b;
        color: white;
    }
    
    </style>
    
<script>
    $(document).ready(function() {
        console.log("ready")
      
        $("#submit-btn").click(async function() {
            console.log("first")
            var email = $("#email-input").val();
            var password = $("#paswrd-input").val();
            var js = JSON.stringify( {
                "username":email,
                "password":password
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
                if(response.model.role=='buyer'){
                    console.log("user",response.model.username)
                    window.location.href = 'userPage.jsp'
                    return;

                }
                if (response.model.role=='admin') {
                    console.log("admin",response.model.UUID)
                    window.location.href = 'adminPage.jsp'
                    return;
                }
                alert("invalid credential fields")
            },
            error: function (xhr, exception, thrownError) {
            console.log("error==>"+exception)
                           alert("invalid credential")
                    }
            })
         })

        })
</script>

</body>
</html>

