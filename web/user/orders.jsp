<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Account</title>

        <!-- Bootstrap core CSS -->
        <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="resources/css/shop-homepage.css" rel="stylesheet"/>
        <style>
            .data{
                padding: 5px 10px;
            }
            table{
                spacing:10;
            }

        </style>


    </head>

    <body>
        <!--Add Header Block-->
        <jsp:include page="../blocks/header.jsp"/>

        <!-- Page Content -->
        <div class="container">

            <!-- Page Content -->
            <div class="container containerSpacing">

                <h1 class="divHeader">User Name</h1>

                <div class="row">

                  <div class="row" style="flex-flow: inherit;">

                    <div class="col-lg-5">
                        <div class="list-group">
                            <a href="../account.jsp" class="list-group-item">Account Details</a>
                            <a href="EditAccount.jsp" class="list-group-item">Edit Profile</a>
                            <a  href="<%=request.getContextPath()%>/orders" class="list-group-item">Orders</a>
                            <a href="<%=request.getContextPath()%>/logout" class="list-group-item">Logout</a>
                        </div>
                    </div>

                    </div>
                    <!-- /.col-lg-3 -->

                    <div class="col-lg-7">
                        <br>

                        <fieldset>

                            <br>
                       <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                        <thead>
                                                            <tr>
                                                                <th>Order number</th>
                                                                <th>Total amount</th>
                                                                <th>Time</th>
                                                                <th>Status</th>

                                                            </tr>
                                                        </thead>
                                                        <tfoot>
                                                            <tr>
                                                                <th>Order number</th>
                                                                <th>Total amount</th>
                                                                <th>Time</th>
                                                                <th>Status</th>
                                                            </tr>
                                                        </tfoot>
                                                        <tbody>
                                                            <c:forEach var="order" items="${list}">                                                
                                                                <tr>
                                                                    <td>${order.orderNumber}</td>
                                                                    <td>${order.totalAmount}</td>
                                                                    <td>${order.orderTime}</td>
                                                                    <td>${order.status}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                            <br>
                          
                        </fieldset>

                    </div>
                    <!-- /.col-lg-9 -->

                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->

        </div>
        <!-- /.container -->

        <!--Add Footer Block-->
        <jsp:include page="../blocks/footer.jsp"/>

        <!-- Bootstrap core JavaScript -->
        <script> document.getElementById("account").classList.add("active");</script>
        <script src="resources/vendor/jquery/jquery.min.js"></script>
        <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    </body>

</html>
