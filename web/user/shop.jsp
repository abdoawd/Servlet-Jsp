<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="pageTitle" value="Users"/>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Dokan</title>

        <!-- Bootstrap core CSS -->
        <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="resources/css/shop-homepage.css" rel="stylesheet">

    </head>

    <body>
        <!--Add Header Block-->
        <jsp:include page="../pages/userheader.jsp"/>  
        <style>
            .div_categories_container{
                display: flex ;
            }
            .div_categories_container .col-md-4{
                flex:0 0 100%;
                max-width: 100%;

            }
            .container{
                margin: auto;
            }
            .containerSpacing{
                justify-content: space-between;
            }
            .quantity {
                position: relative;
            }

            input[type=number]::-webkit-inner-spin-button,
            input[type=number]::-webkit-outer-spin-button
            {
                -webkit-appearance: none;
                margin: 0;
            }

            input[type=number]
            {
                -moz-appearance: textfield;
            }

            .quantity input {
                width: 45px;
                height: 42px;
                line-height: 1.65;
                float: left;
                display: block;
                padding: 0;
                margin: 0;
                padding-left: 20px;
                border: 1px solid #eee;
            }

            .quantity input:focus {
                outline: 0;
            }

            .quantity-nav {
                float: left;
                position: relative;
                height: 42px;
            }

            .quantity-button {
                position: relative;
                cursor: pointer;
                border-left: 1px solid #eee;
                width: 20px;
                text-align: center;
                color: #333;
                font-size: 13px;
                font-family: "Trebuchet MS", Helvetica, sans-serif !important;
                line-height: 1.7;
                -webkit-transform: translateX(-100%);
                transform: translateX(-100%);
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                -o-user-select: none;
                user-select: none;
            }

            .quantity-button.quantity-up {
                position: absolute;
                height: 50%;
                top: 0;
                border-bottom: 1px solid #eee;
            }

            .quantity-button.quantity-down {
                position: absolute;
                bottom: -1px;
                height: 50%;
            }
            .searchByName{
                border-radius: 4px;
                background-color: #f4511e;
                text-align: center;
                font-size: 28px;
                width: 200px;
                transition: all 0.5s;
                cursor: pointer;
            }

        </style>

        <!-- Page Content -->
        <div class="container containerSpacing row ">

           
                <div class="col-md-3">
                    <c:forEach var="category" items="${categories}">                                                
                        <div class="sidebar navbar-nav" >
                            <div class="nav-item active">
                                <div class="nav-link">
                                    <h4 class="card-title">
                                        <a href="#">${category.getName()}</a>
                                    </h4>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        
                <div class="col-md-1">
                    <label><small>From (EGP)</small>
                        <div class="quantity">
                            <input type="number"  min="0" max="84999" value="">
                        </div>
                    </label>
                    <label><small>To (EGP)</small>
                        <div class="quantity">
                            <input type="number" min="2241" value="85000">
                        </div>
                    </label>
                    <button type="button" class="searchByName"  data-type="Price" onclick="searchByPrice()">
                        Apply
                    </button>
                </div>
        
            <div class=" row col-md-8">
                <c:forEach var="product" items="${products}">                                                
                    <div class=" col-lg-4 col-md-6 mb-4"   >
                        <div class="asds card h-100" id="45">
                            <a href="#"><img class="card-img-top" id="45" src=product.image alt=""></a>
                            <div id="45" class="card-body">
                                <h4 class="card-title">
                                    ${product.getName()}
                                </h4>
                                <h5>${" price "}
                                    ${product.getPrice()}
                                    ${" $"}</h5>
                            </div>

                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <jsp:include page="../blocks/footer.jsp"/>

        <!-- Bootstrap core JavaScript -->
        <script> document.getElementById("home").classList.add("active");</script>
        <script src="resources/vendor/jquery/jquery.min.js"></script>
        <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script>
            var arr = document.getElementsByClassName('asds');
            for (var i = 0; i < arr.length; i++) {
                arr[i].addEventListener("click", function (e) {
                    alert(e.target.tagName)
                });
            }
        </script>

    </body>

</html>
