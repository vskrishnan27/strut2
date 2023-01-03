<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <link rel="stylesheet" href="addProduct.css">
</head>

<body>
    
    <div id="ad-product">
        <label for="">ADD A PRODUCT</label>
        <input type="text" placeholder="Product Name" id="pName">
        <input type="number" placeholder="Price" id="pPrice">
        <input type="number" placeholder="stock" id="pStock">
        <button type="submit" id="addProductBtn">Add to product List</button>
    </div>
    <script>
        $("#addProductBtn").click(function() {
        	console.log("add product btn")
        	
        	    var js = JSON.stringify( {
                "productName":$("#pName").val(),
                "price":$("#pPrice").val(),
                "stock":$("#pStock").val()
            })
        	
            $.ajax({
            url: "http://localhost:8080/ecommerce/admin/product/addProduct",
            type: 'POST',
            dataType: 'json', // added data type
            data:js,
            contentType: "application/json; charset=utf-8",
            success: function(res) {
                alert("Product added success fully");
                if(alert){
                   window.location.href="http://localhost:8080/ecommerce/adminPage.jsp" 
                }
            },
            error:function(err){
                console.log("Error==>",err)
           window.location.href="http://localhost:8080/ecommerce/adminPage.jsp" 
            }
        });
        })
    </script>


</body>
</html>