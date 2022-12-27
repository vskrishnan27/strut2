
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
  
</head>
<body>
        <h1>USRES PANEL</h1>
            
            <select name="user" class="usr" id="usrOptions">
              <option value="history">History</option>
              <option value="wish_list">wish list</option>
              <option value="viw_products">view products</option>
        
            </select>
            <br><br>
            <input type="submit" id="usroption" >
          </form>
          <br><br>
          <div id="dvTable">
          </div>
          

          <script>


  $(document).ready(function() {
    
      $("select.usr").change(function(){
        var options = $(this).children("option:selected").val();

          let endpoint = ""
          let way=""
          if(options=='history'){
              endpoint="http://localhost:8080/ecommerce/user/orderHistoryAction.action"
              way="orderHistory"
          }
          if (options=='wish_list') {
            endpoint="http://localhost:8080/ecommerce/user/wishListAction.action"
            way="wishList"
          }
          if (options=='viw_products') {
            endpoint="http://localhost:8080/ecommerce/user/showProducts.action"
          }
         
          
          $.ajax({
        url: endpoint,
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {

            if(options=='history'){
              createTable(res.orderHistory,false)
              return
            }
            if(options=='wish_list'){
              createTable(res.wishList,false)
              return
            }
            if(options=='viw_products'){
              createTable(res.productList,true)
              return
            }

            
            alert(res);
        },
        error:function(err){
          
        }
    });



            });
        
        })
     
        // create a table 

         function createTable(data,btn) {
                var table = $("<table />");
                table[0].border = "1";
                var dataCount = Object.keys(data[0]).length
                      // adding a header
                      var row = $(table[0].insertRow(-1));
                      for(key in data[0]){
                        var headerCell = $("<th />");
                        headerCell.html(key);
                        row.append(headerCell);
                      }
                      if(btn){
                        var buuton = $("<th/>")
                      buuton.html("add wishlist")
                      row.append(buuton)
                      var qty = $("<th/>")
                      qty.html("qty")
                      row.append(qty)
                      var bnow = $("<th/>")
                      bnow.html("buynow")
                      row.append(bnow)
                      }

                      for (i in data) {
                          row = $(table[0].insertRow(-1));       
                          for (key in data[i]) {
                              var cell = $("<td />");
                              cell.html(data[i][key]);
                              row.append(cell);
                	        }

                          console.log("id of the product",data[i]["productId"])
                          if(btn){


                            var wishListBtn = $("<button/>")
                                wishListBtn.html("Add to wish List")
                                wishListBtn.attr('id',"wishListBtn")
                                wishListBtn.click(function() {
                                  let arr = $(this).parent().parent()[0].innerText.split('\t')

                                  console.log("add to wishlisst call")
                                              $.ajax({
                                            url: "http://localhost:8080/ecommerce/user/addToWishListAction.action",
                                            type: 'GET',
                                            dataType: 'json', // added data type
                                            data:{
                                              productId:arr[1]
                                            },
                                          
                                            success: function(res) {
                                                alert("added to wishlist")
                                                alert(res);
                                            },
                                            error:function(err){
                                              console.log("wishlist_er")
                                            }})
                                  


                                })
                                var cell = $("<td />");
                                cell.append(wishListBtn)
                                row.append(cell);
                                // get the qty of input
                                var qtyInput = $("<input min=1 type=number />")
                                qtyInput.attr('id',data[i]["productId"])
                                qtyInput.click(function() {
                                    

                                })

                                var cell = $("<td />");
                                cell.append(qtyInput)
                                row.append(cell);


                                var buuton = $("<button/>")
                                buuton.html("buynow")
                                buuton.attr('id',"buybtn")
                                buuton.click(function() {
                                  console.log($(this).parent)
                                  let arr = $(this).parent().parent()[0].innerText.split('\t')
                                  console.log(arr)

                                  // console.log("value of the text  field",$('#'+).val())
                                  console.log(document.getElementById(data[i]["productId"]).value)
                                  
                                  if(true){
                                    // ajax call to buy product
                                    
                                    $(this).parent().parent()[0].childNodes[3].innerText=100
                                      console.log("ajax call")
                                    
                                  }else{
                                    alert("stock limit is only = "+arr[3] )
                                  }


                                })
                                var cell = $("<td />");
                                cell.append(buuton)
                                row.append(cell);
                            
                              }
                	    }


                
                var dvTable = $("#dvTable");
                dvTable.html("");
                dvTable.append(table);
            

          }

          $("#buybtn").click( function() {
            console.log("first")
              console.log($(this).html())
            });

          </script>


</body>
</html>

