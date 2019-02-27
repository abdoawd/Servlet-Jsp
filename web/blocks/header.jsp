<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon/>

        <!-- Bootstrap core CSS -->
        <link href="<%=request.getContextPath()%>/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="<%=request.getContextPath()%>/resources/css/shop-homepage.css" rel="stylesheet">

    </head>

    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top headerNavBar">
            <div class="container headerLogo">
                <div class="nav-item">
                    <input type="text" id="search_value" name="q" class="input-group-field" onkeydown="search(this)"
                           placeholder="Search.." autocomplete="off" tabindex="-1">
                </div>
                <a href="<%=request.getContextPath()%>">
                    <img class="navbar-brand headerLogo" src="<%=request.getContextPath()%>/resources/pictures/Dokan-Logo.png" />
                </a>
                <button  class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li id="cart" class="nav-item">   
                            <a class="nav-link headerImagesContainer" href="<%=request.getContextPath()%>/UserCart">
                                <img src="<%=request.getContextPath()%>/resources/pictures/cart.png" class="headerImages"/>
                                Cart</a>
                        </li>
                        <li id="account" class="nav-item">   
                            <a class="nav-link headerImagesContainer" href="<%=request.getContextPath()%>/account">
                                <img src="<%=request.getContextPath()%>/resources/pictures/account.png" class="headerImages"/>
                                Account</a>
                        </li>
                        <li id="user" class="nav-item">   
                            <div class="nav-link headerImagesContainer">
                                <img src="<%=request.getContextPath()%>/resources/pictures/user.png" class="headerImages" style="height: 1.75em"/>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Bootstrap core JavaScript -->
        <script src="<%=request.getContextPath()%>/resources/vendor/jquery/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Search bar -->
        <script>
                            function search(ele) {
                                if (event.key === 'Enter') {
                                    document.location.href = "/dokan/shop?search=" + document.getElementById("search_value").value;
                                }
                            }
        </script>
    </body>


</html>
