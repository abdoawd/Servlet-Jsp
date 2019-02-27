<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="pageTitle" value="Orders"/>


<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>${pageTitle}</title>

        <!-- Bootstrap core CSS -->
        <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="resources/css/shop-homepage.css" rel="stylesheet"/>

    </head>

    <body>
        <!--Add Header Block-->
        <jsp:include page="../blocks/header.jsp"/>  

        <!-- Page Content -->
        <div class="justify-content-center container containerSpacing row" style="margin: 5em auto;">
            <!--Sidebar-->
            <div class="col-lg-3">
                <jsp:include page="/user/nav_bar.jsp" />
            </div>
            <!--Sidebar End-->
            <!--Main Container-->
            <div class=" col-md-9">

                <!--Title Start-->
                <div class="card-header">
                    <i class="fas fa-table">${pageTitle}</i>  
                </div>
                <!--Title End-->
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
                            </div>
            <!--Main Container-->
        </div>

        <!--Footer-->
        <jsp:include page="../blocks/footer.jsp"/>

        <!-- Bootstrap core JavaScript -->
        <script> document.getElementById("account").classList.add("active");</script>
        <script src="resources/vendor/jquery/jquery.min.js"></script>
        <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    </body>

</html>
