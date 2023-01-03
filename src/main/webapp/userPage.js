
  $(document).ready(function() {
    calls("viw_products")
    
    
    console.log("user page ready")
    $("#home").click(function(){
      console.log("home clicked")
      calls("viw_products")
     
    })

    $("#wishList").click(function(){
      console.log("wishlist clicked")
      calls("wish_list")
    })

    $("#orderHistory").click(function(){
      console.log("orde history clicked")
      calls("history")
    })



    $("#userLogout").click(function(){
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

    
   function calls(options){
        var endpoint=""
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

}

      
   
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
                                     
                                              console.log(res)
                                              alert(res.status);
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
                                quantity = parseInt($("#"+arr[1]).val())
                                console.log("qty",quantity)

                                
                                console.log("qty ",$(this).closest().quantity)

                                if(quantity<=parseInt(arr[3])){


                                  // call for buty product
                                  $.ajax({
                                    url: "http://localhost:8080/ecommerce/user/buyAction.action",
                                    type: 'GET',
                                    dataType: 'json', // added data type
                                    data:{
                                      productId:arr[1],
                                      qty:quantity
                                    },
                                  
                                    success: function(res) {
                                        alert("sucess")
                                        $.ajax({
                                          url: "http://localhost:8080/ecommerce/user/showProducts.action",
                                          type: 'GET',
                                          dataType: 'json', // added data type
                                          success: function(res) {
                                                createTable(res.productList,true)
                                                return
                                          },
                                          error:function(err){   
                                          }
                                      });  
                                    },
                                    error:function(err){
                                      console.log("wishlist_er")
                                    }})
                          


                                  
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


        })