<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
          <script src=" https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <link rel="stylesheet" href="adminPage.css">
</head>
<body>
  <header>
    <nav class="navbar">
        <ul>
            <li><a href="#" id="adminHome">Home</a></li>
            <li><a href="#" id="addProduct">Add Product</a></li>
            <li><a href="#" id="logoutAdmin">logout</a></li>
        </ul>
    </nav>
</header>


    <br>
    <br>
    <hr>
    <div id="dvTable"></div>

<script>

  $(document).ready(function() {
    console.log("ready")
    start()

    $("#addProduct").click(function () { 
        window.location.href="http://localhost:8080/ecommerce/addProduct.jsp"
    	
     })

       $("#adminHome").click(function () { 
        console.log("home")
        start()
    	
     })
     
         $("#logoutAdmin").click(function(){
      console.log("logout new")
      let endpt="http://localhost:8080/ecommerce/logout/logout.action"
      console.log(endpt)
     
     	var cookies = $.cookie();
			for(var cookie in cookies) {
			console.log(cookie)
			   $.removeCookie(cookie);
			}
     
		
      $.ajax({
        url:endpt ,
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            alert("Logged out - successfully:)");
            window.location.reload()
        },
        error:function(err){
         console.log(err)
        }
    });
    })


function start(){
    

    $.ajax({
            url: "http://localhost:8080/ecommerce/admin/product/showProducts",
            type: 'GET',
            dataType: 'json', // added data type
            success: function(res) {
                console.log(res);
                createTable(res,true)
               
            }
        });
    }
       




        function createTable(data,btn) {
                var table = $("<table />");
                table[0].border = "1";
                var dataCount = Object.keys(data[0]).length
                console.log(dataCount)
                      // adding a header
                      var row = $(table[0].insertRow(-1));
                      for(key in data[0]){
                        var headerCell = $("<th />");
                        headerCell.html(key);
                        row.append(headerCell);
                      }
                      if(btn){
                      var bnow = $("<th/>")
                      bnow.html("Delete")
                      row.append(bnow)
                      }

                      for (i in data) {
                          row = $(table[0].insertRow(-1));       
                          for (key in data[i]) {
                              var cell = $("<td />");
                              cell.html(data[i][key]);
                              row.append(cell);
                	        }

                          if(btn){


                            var deleteBtn = $("<button/>")
                                deleteBtn.html("deleteBtn")
                                deleteBtn.attr('id',"deleteBtn")
                                deleteBtn.click(function() {
                                	
                            
                                	
                                  let arr = $(this).parent()[0].innerText.split('\t')
                                  console.log(arr)
                                  $(this).parent().remove();
                            
                                              $.ajax({
                                            url: "http://localhost:8080/ecommerce/admin/product/delete",
                                            type: 'GET',
                                            dataType: 'json', // added data type
                                            data:{
                                              id:arr[0]
                                            },
                            
                                            success: function(res) {
                                                alert(res)
                                              
                                            },
                                            error:function(err){
                                               
                                              console.log("wishlist_er")
                                            }})
                                  


                                })
                                row.append(deleteBtn);
                                
                            }}


                
                var dvTable = $("#dvTable");
                dvTable.html("");
                dvTable.append(table);
            

          }



})


</script>
</body>
</html>