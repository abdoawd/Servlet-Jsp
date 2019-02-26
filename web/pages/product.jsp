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

        <!-- CSS -->
        <link href="resources/css/style-product-page.css" rel="stylesheet">

    </head>

    <body>
        <!--Add Header Block-->
        <jsp:include page="../blocks/header.jsp"/>
        
       <!-- Page Content -->
        <div class="container containerSpacing">
            <div class="row">
                    <main class="container-product">
                        <!-- Left Column / Headphones Image -->
                        <div class="left-column col-8">
                            <img data-image="red" class="active" src="data:image/jpeg;base64,${product.stringImage}" alt="">
                        </div>


                        <!-- Right Column -->
                        <div class="right-column col-4">
                            <!-- Product Description -->
                            <div class="product-description">
                                <span>${product.getCategoryName()}</span>
                                <h1>${product.getName()}</h1>
                                <p>${product.getDescription()}</p>
                            </div>
                            <!-- Product Pricing -->
                            <div class="product-price">
                                <h4 class="productPriceDiscounted">${product.getPrice()+product.getDiscount()}</h4>
                                <h2 class="productPriceFinal">${product.getPrice()} EGP</h2>
                            </div>
                            <div>
                                <a id="addToCart" href="/dokan/UserCart?addToCart=true&product_id=${product.id}" class="submit-button no-underline">
                                    Add to cart</a>
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
        <script>
            function search() {
                document.location.href = "/dokan/UserCart";
            }
        </script>
        <!-- Scripts -->
        <script src="../resources/js/script-product-page.js" charset="utf-8"></script>

    </body>

</html>
