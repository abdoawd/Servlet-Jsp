<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Your Cart</title>

        <!-- Bootstrap core CSS -->
        <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="resources/css/shop-homepage.css" rel="stylesheet">

        <!-- Custom styles for cart page -->
        <link href="resources/css/flatsome-shop.css" rel="stylesheet">
        <link href="resources/css/flatsome.css" rel="stylesheet">

    </head>

    <body>
        <!--Add Header Block-->
        <jsp:include page="blocks/header.jsp"/>

        <!-- Page Content -->
        <div class="container containerSpacing">

            <div class="row">

                <div class="columnTwoThird cart_table_div">
                    <h1 class="divHeader">Shopping Cart</h1>
                    <c:if test="${userCartProducts != null}" > 

                        <table  class="shop_table shop_table_responsive cart woocommerce-cart-form__contents" cellspacing="0">
                            <thead>
                                <tr>
                                    <th class="product-name" colspan="3">Product</th>
                                    <th class="product-price">Price</th>
                                    <th class="product-quantity">Quantity</th>
                                    <th class="product-subtotal">Total</th>
                                </tr>
                            </thead>
                            <c:forEach var="cartItem" items="${userCartProducts}">   
                                <tbody>
                                    <tr class="woocommerce-cart-form__cart-item cart_item">
                                        <td class="product-remove">
                                            <a href="/dokan/UserCart?productId=${cartItem.productId}&delete=true"  class="noTextDecoration remove" aria-label="Remove this item" data-product_id="${cartItem.productId}" data-product_sku="">×</a>          
                                        </td>

                                        <td class="product-thumbnail">
                                            <a href="#"><img width="300" height="300" src="data:image/jpeg;base64,${cartItem.stringImage}" class="attachment-woocommerce_thumbnail size-woocommerce_thumbnail wp-post-image" alt="Product Image"></a>          
                                        </td>

                                        <td class="product-name" data-title="Product">
                                            <a href="#">${cartItem.productName}</a>          
                                        </td>

                                        <td class="product-price" idata-title="Price">
                                            <span class="woocommerce-Price-amount amount" >${cartItem.productPrice}<span class="woocommerce-Price-currencySymbol">EGP</span></span>          
                                        </td>
                                        <td class="product-quantity" data-title="Quantity">
                                            <div class="quantity buttons_added">
                                                <input type="button"  id = "decreaseBtn" onclick="getTotalAmount(${cartItem.productId},${cartItem.productPrice}, 'sub',${cartItem.productQuantity}, '${cartItem.productName}')" value="-" class="minus button is-form">    
                                                <input type="number" id ="${cartItem.productId}" 
                                                       class="input-text qty text" step="1" min="1" max="10"
                                                       value="${cartItem.userCartProductQuantity}" title="Qty" size="4"
                                                       pattern="[0-9]*" inputmode="numeric">
                                                <input type="button" id = "inecreaseBtn"
                                                       onclick = "getTotalAmount(${cartItem.productId},
                                                       ${cartItem.productPrice}, 'add', ${cartItem.productQuantity}, 
                                                                   '${cartItem.productName}')" value="+" class="plus button is-form">  
                                            </div>
                                            <div><p><span>Total Quantity </span>${cartItem.productQuantity}</p></div>
                                        </td>
                                        <td class="product-subtotal" data-title="Total">
                                            <span   id="${cartItem.productId}${"d"}" class="sumProductPrices" class="woocommerce-Price-amount amount">${cartItem.productPrice * cartItem.userCartProductQuantity}<span class="woocommerce-Price-currencySymbol">EGP</span></span>            
                                                                               <c:set var="finalTotal" value="${finalTotal + (cartItem.productPrice * cartItem.userCartProductQuantity)}"/>

                                        </td>
                                        

                                    </tr>
                                    <tr>
                                        <td colspan="6" class="actions clear">

                                            <input type="submit" class="button primary mt-0 pull-left small" ac onclick="updateCart(${cartItem.productId})" name="update_cart" value="Update cart">
                                        </td>
                                    </tr>

                                </c:forEach>
                                <tr>
                                    <td colspan="6" class="actions clear">

                                        <div class="continue-shopping pull-left text-left">
                                            <a class="button-continue-shopping button primary is-outline noTextDecoration mainColor" href="<%=request.getContextPath()%>/shop">
                                                Continue shopping    
                                            </a>
                                        </div>

                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${userCartProducts == null}">
                        Cart Is Empty
                    </c:if>

                </div>
                <!-- /.col-lg-9 -->

                <div class="columnThird" style="width: 30%;" important>
                    <div class="list-group">
                        <form action="checkout" action="POST">
                            <a class="list-group-item">Order Summary</a>
                            <div class="list-group-item">
                                <c:set var="cartTotal" value="${0}" scope="page" />

                                <c:forEach var="summrizeItems" items="${userCartProducts}">   
                                    <h4 class="itemPriceSummrize" id="${summrizeItems.productId}${"e"}">${summrizeItems.userCartProductQuantity} ${summrizeItems.productName}</h4>
                                    <hr>
                                    
                                    <c:set var="cartTotal" value="${cartTotal + summrizeItems.productPrice}" />
                                </c:forEach>
                                    <h3 id ="total">Total  ${finalTotal}  EGP</h3>
                            </div>
                            <input type="hidden" id="checkout_sum" name="totalsum" value="${finalTotal}"/>
                      
                            <button type="submit" class="submit-button list-group-item">Proceed to checkout</button>
                        </form>
                    </div>
                </div>
                <!-- /.col-lg-3 -->

            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->

        <!--Add Footer Block-->
        <jsp:include page="blocks/footer.jsp"/>

        <!-- Bootstrap core JavaScript -->
        <script> document.getElementById("cart").classList.add("active");</script>
        <script src="resources/vendor/jquery/jquery.min.js"></script>
        <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <script>
            function getTotalAmount(id, m, type, totalQuntity, productName) {
                var amountValue = document.getElementById(id);
                var summrizeItemNumber = document.getElementById(id + "e");
                var itemPriceSummrize = document.getElementsByClassName("sumProductPrices");
                var x = 0;
                var total = document.getElementById("total");
                var oo = id + "d";
                var finalTotal = document.getElementById(oo);
                if ((type === 'sub') && (amountValue.value > 1))
                {
                    finalTotal.innerText = parseInt(--amountValue.value) * parseInt(m) + "EGP";

                } else if ((type === 'add') && (amountValue.value < totalQuntity))
                {
                    finalTotal.innerText = parseInt(++amountValue.value) * parseInt(m) + "EGP";

                }
                summrizeItemNumber.innerText = amountValue.value + " " + productName;
                for (var i = 0; i < itemPriceSummrize.length; i++)
                {
                    x += parseInt(itemPriceSummrize[i].innerText);
                }
                total.innerText = "Total " + x + " EGP";
                document.getElementById("checkout_sum").value = parseInt(total.innerText);
            }
            function updateCart(id)
            {
                var amountValue = document.getElementById(id).value;
                document.location.href = "/dokan/UserCart?productId=" + id + "&itemNumber=" + amountValue;
            }

        </script>

    </body>

</html>