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
        <div class="container">

            <!-- Page Content -->
            <div class="container containerSpacing">



                <div class="row">

                    <div class="columnTwoThird cart_table_div">
                        <h1 class="divHeader">Shopping Cart</h1>
                        <c:forEach var="cartItem" items="${userCartProducts}">   

                            <table  class="shop_table shop_table_responsive cart woocommerce-cart-form__contents" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th class="product-name" colspan="3">Product</th>
                                        <th class="product-price">Price</th>
                                        <th class="product-quantity">Quantity</th>
                                        <th class="product-subtotal">Total</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <tr class="woocommerce-cart-form__cart-item cart_item">

                                        <td class="product-remove">
                                            <a href="#" class="noTextDecoration remove" aria-label="Remove this item" data-product_id="364" data-product_sku="">×</a>          
                                        </td>

                                        <td class="product-thumbnail">
                                            <a href="#"><img width="300" height="300" src="data:image/jpeg;base64,${cartItem.stringImage}" class="attachment-woocommerce_thumbnail size-woocommerce_thumbnail wp-post-image" alt="Product Image"></a>          
                                        </td>

                                        <td class="product-name" data-title="Product">
                                            <a href="#">${cartItem.name}</a>          
                                        </td>

                                        <td class="product-price" idata-title="Price">
                                            <span class="woocommerce-Price-amount amount" >${cartItem.price}<span class="woocommerce-Price-currencySymbol">EGP</span></span>          
                                        </td>

                                        <td class="product-quantity" data-title="Quantity">
                                            <div class="quantity buttons_added">
                                                <input type="button"  id = "decreaseBtn" onclick="getTotalAmount()" value="-" class="minus button is-form">    
                                                <input type="number" id ="${cartItem.id}"   class="input-text qty text" step="1" min="0" max="10" value="1" title="Qty" size="4" pattern="[0-9]*" inputmode="numeric">
                                                <input type="button" id = "inecreaseBtn" onclick = "getTotalAmount(${cartItem.id},${cartItem.price})" value="+" class="plus button is-form">  
                                            </div>
                                        </td>

                                        <td class="product-subtotal" data-title="Total">
                                            <span   id="${cartItem.id}${"d"}" class="woocommerce-Price-amount amount"><span class="woocommerce-Price-currencySymbol">EGP</span></span>            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="6" class="actions clear">

                                            <div class="continue-shopping pull-left text-left">
                                                <a class="button-continue-shopping button primary is-outline noTextDecoration mainColor" href="<%=request.getContextPath()%>">
                                                    Continue shopping    
                                                </a>
                                            </div>
                                            <input
                                                <input type="submit" class="button primary mt-0 pull-left small" onclick="updateCart(${cartItem.id})" name="update_cart" value="Update cart">
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </c:forEach>

                    </div>
                    <!-- /.col-lg-9 -->

                    <div class="columnThird">
                        <div class="list-group">
                            <form action="checkout.jsp">
                                <a class="list-group-item">Order Summary</a>
                                <div class="list-group-item">
                                    <h4>1 x item</h4>
                                    <h4>1 x item</h4>
                                    <hr>
                                    <h3>Total</h3>
                                </div>
                                <button type="submit" class="submit-button list-group-item">Proceed to checkout</button>
                            </form>
                        </div>
                    </div>
                    <!-- /.col-lg-3 -->

                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->

        </div>
        <!-- /.container -->

        <!--Add Footer Block-->
        <jsp:include page="blocks/footer.jsp"/>

        <!-- Bootstrap core JavaScript -->
        <script> document.getElementById("cart").classList.add("active");</script>
        <script src="resources/vendor/jquery/jquery.min.js"></script>
        <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script>
            function getTotalAmount(id, m) {
                var amountValue = document.getElementById(id);
                var oo = id + "d";
                document.getElementById(oo).innerText = parseInt(amountValue.value) * parseInt(m) + "EGP";
            }
            function updateCart(id)
            {
                var amountValue = document.getElementById(id).value;

                document.location.href = "/dokan/UserCart?productId=" + id + "&itemNumber=" + amountValue;
            }
        </script>

    </body>

</html>