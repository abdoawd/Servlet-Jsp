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

    </head>

    <body>
        <!--Add Header Block-->
        <jsp:include page="blocks/header.jsp"/>

        <!-- Page Content -->
        <div class="container">

            <!-- Page Content -->
            <div class="container containerSpacing">

                <h1 class="divHeader">Shopping Cart</h1>

                <div class="row">

                    <div class="columnTwoThird">

                        Hello, It's me :)

                    </div>
                    <!-- /.col-lg-9 -->

                    <div class="columnThird">
                        <div class="list-group">
                            <a class="list-group-item">Order Summary</a>
                            <div class="list-group-item">
                                <h4>1 x item</h4>
                                <h4>1 x item</h4>
                                <hr>
                                <h3>Total</h3>
                            </div>
                            <a href="checkout.jsp" class="list-group-item">Proceed to checkout</a>
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

    </body>

</html>