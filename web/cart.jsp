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
                    <div class="col-md-9">

                        <div class="d-flex">
                            <div class=""  style="flex-grow: 1">
                                <div class="image_product">
                                    <img src="cr71.jpg" width="200" height="200"/>
                                </div>
                                Hello, It's me :)

                            </div>
                            <div style="flex-grow: 2">
                                <p>hgfhadfajgdskudgsjgdsakdasdiasdtasydgasidkgasudgsadi</p>
                                <br>
                                <p>hgfhadfajgdskudgsjgdsakdasdiasdtasydgasidkgasudgsadi</p>
                            </div>
                            <!-- /.col-lg-9 -->

                            <!-- /.col-lg-3 -->

                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="columnThirdtest">
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
                    </div>
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