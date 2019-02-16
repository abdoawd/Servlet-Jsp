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
                width: 150px;
                float: left;
                height: 100%;
            }
        </style>

        <!-- Page Content -->
        <div class="container containerSpacing">
            <div class="div_categories_container">

                <c:forEach var="category" items="${categories}">                                                


                    <div class="sidebar navbar-nav" class="row">
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


            <div class="row">

                <div class="col-lg-9">


                    <div class="row">



                        <c:forEach var="prod" items="${products}">                                                
                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card h-100">
                                    <a href="#"><img class="card-img-top" src=prod.alt=""></a>
                                    <div class="card-body">
                                        <h4 class="card-title">
                                            ${prod.getName()}
                                        </h4>
                                        <h5>${" price "}
                                            ${prod.getPrice()}
                                        ${" $"}</h5>
                                    </div>

                                </div>
                            </div>
                        </c:forEach>



                    </div>
                    <!-- /.row -->

                </div>
                <!-- /.col-lg-9 -->

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

    </body>

</html>
