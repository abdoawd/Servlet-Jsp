<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap core CSS -->
        <link href="../resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="../resources/css/shop-homepage.css" rel="stylesheet">
        <style>
            .input-group-field
            {
                margin-right: auto;
                position: relative;
                z-index: 151;
                height: 38px;
                margin-right: 30px;
                background-color: darkgray;
                border: 5px ;
                box-shadow: none;
                margin-left: auto;
                padding-right: 550px;
                border-radius: 4px;

            }
        </style>

    </head>

    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <!--      <a class="navbar-brand" href="#">Start Bootstrap</a>-->
                <img class="navbar-brand headerLogo" src="<%=request.getContextPath()%>/resources/pictures/Dokan-Logo.png" />
                <button  class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li id="home" class="nav-item">
                            <input type="text" id="search_value" name="q" class="input-group-field" onkeydown="search(this)"
                                   placeholder="What are you looking for?" autocomplete="off" tabindex="-1">
                        </li>
                        <li id="home" class="nav-item">
                        </li>
                        <li id="account" class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/account.jsp">Account</a>
                        </li>
                        <li id="cart" class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/cart.jsp">Cart</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Bootstrap core JavaScript -->
        <script src="../resources/vendor/jquery/jquery.min.js"></script>
        <script src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script>
                                function search(ele) {
                                    if (event.key === 'Enter') {
//                                        alert(document.getElementById("search_value").value)
                                        document.location.href = "/dokan/UserHomeServlet?search=" + document.getElementById("search_value").value;
                                    }
                                }
        </script>

    </body>

</html>
