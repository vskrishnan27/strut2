
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
    
</head>
<body>
        <h1>user Page</h1>
        <select>
            <option value="actual value 1">Display Text 1</option>
            <option value="actual value 2">Display Text 2</option>
            <option value="actual value 3">Display Text 3</option>
          </select>
                  
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
                if(response.model.role=='user'){
                    console.log("user",response.model.username)
                    window.location.href = 'userPage.jsp'

                }
                if (response.model.role=='admin') {
                    console.log("admin",respionse.UUID)
                    window.location.href = 'adminPage.jsp'

                }




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

