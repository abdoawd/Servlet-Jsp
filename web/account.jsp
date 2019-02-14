<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Account</title>

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
                
                <h1 class="divHeader">User Name</h1>
                
                <div class="row">

                    <div class="col-lg-3">
                        <div class="list-group">
                            <a href="#" class="list-group-item">Account Details</a>
                            <a href="#" class="list-group-item">Orders</a>
                            <a href="#" class="list-group-item">Logout</a>
                        </div>

                    </div>
                    <!-- /.col-lg-3 -->

                    <div class="col-lg-9">

                        test test test<br><br>
                        This space to show user data{first name, last name, address,...} so he could be able to change them<br><br>
                        The second button supposed to show customer orders.

                    </div>
                    <!-- /.col-lg-9 -->

                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->

        </div>
        <!-- /.container -->

        <!--Add Footer Block-->
        <jsp:include page="blocks/footer.jsp"/>

        <!-- Bootstrap core JavaScript -->
        <script> document.getElementById("account").classList.add("active");</script>
        <script src="resources/vendor/jquery/jquery.min.js"></script>
        <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    </body>

</html>
