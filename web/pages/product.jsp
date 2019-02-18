<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Product</title>

        <!-- Bootstrap core CSS -->
        <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="resources/css/shop-homepage.css" rel="stylesheet">

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">

        <!-- CSS -->
        <link href="../resources/css/style-product-page.css" rel="stylesheet">

    </head>

    <body>
        <!--Add Header Block-->
        <jsp:include page="../blocks/header.jsp"/>

        <!-- Page Content -->
        <div class="container containerSpacing">

            <div class="row">

                <main class="container-product">
                    <!-- Left Column / Headphones Image -->
                    <div class="left-column">
                        <!--                            <img data-image="red" src="red.png" alt="">
                                                    <img data-image="blue" src="red.png" alt="">-->
                        <img data-image="red" class="active" src="data:image/jpeg;base64,${product.stringImage}" alt="">

                    </div>


<<<<<<< HEAD
                        <!-- Right Column -->
                        <div class="right-column">
                            <!-- Product Description -->
                            <div class="product-description">
                                <span>${product.getName()}</span>
                                <h1>${product.getName()}</h1>
                                <p>${product.getDescription()}</p>
                            </div>
                            <img data-image="red" class="active"  alt="">

                            <!-- Product Pricing -->
                            <div class="product-price">
                                <span>${product.getPrice()}</span>
                                <a href="#" class="cart-btn no-underline">Add to cart</a>
                            </div>
=======
                    <!-- Right Column -->
                    <div class="right-column">
                        <!-- Product Description -->
                        <div class="product-description">
                            <span>${product.getName()}</span>
                            <h1>${product.getName()}</h1>
                            <p>${product.getDescription()}</p>
>>>>>>> 798c6eb1638f3c2846ab59474ed82e923af9509e
                        </div>

                        <!-- Product Pricing -->
                        <div class="product-price">
                            <span>${product.getPrice()}</span>
                            <a href="#" class="cart-btn no-underline">Add to cart</a>
                        </div>
                    </div>
                </main>


            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->

        <!--Add Footer Block-->
        <jsp:include page="../blocks/footer.jsp"/>

        <!-- Bootstrap core JavaScript -->
        <script> document.getElementById("home").classList.add("active");</script>
        <script src="resources/vendor/jquery/jquery.min.js"></script>
        <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Scripts -->
        <script src="../resources/js/script-product-page.js" charset="utf-8"></script>

    </body>

</html>
