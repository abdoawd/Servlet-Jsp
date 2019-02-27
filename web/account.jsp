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

        <!-- Form CSS-->
        <link href="<%=request.getContextPath()%>/resources/css/forms.css" rel="stylesheet">

    </head>

    <body>
        <!--Add Header Block-->
        <jsp:include page="blocks/header.jsp"/>  

        <!-- Page Content -->
        <div class="justify-content-center container containerSpacing row" style="margin: 5em auto;">
            <!--Sidebar-->
            <div class="col-lg-3">
                <jsp:include page="/user/nav_bar.jsp" />
            </div>
            <!--Sidebar End-->
            <!--Main Container-->
            <div class=" col-md-9">

                <div style="float: right;">
                    <h1>${user.creditlimits}</h1>
                </div>
                <h3 class="divHeader">Welcome,</h3>

                <!--Form begin-->
                <div class="centeredDiv">
                    <label><b>First Name</b></label>
                    <input type="text" value="${user.firstName}" readonly>
                </div>

                <div class="centeredDiv centeredDiv2">
                    <label><b>Last Name</b></label>
                    <input type="text" value="${user.lastName}" readonly>
                </div>

                <div class="centeredDiv">
                    <label><b>E-Mail</b></label>
                    <input type="text" value="${user.email}" readonly>
                </div>

                <div class="centeredDiv centeredDiv2">
                    <label><b>Job</b></label>
                    <input type="text" value="${user.job}" readonly>
                </div>

                <div>
                    <label><b>Street</b></label>
                    <input type="text" value="${user.address.street}" readonly>
                </div>

                <div class="centeredDiv">
                    <label>City<b></b></label>
                    <input type="text" value="${user.address.city}" readonly>
                </div>

                <div class="centeredDiv centeredDiv2">
                    <label><b>Country</b></label>
                    <input type="text" value="${user.address.country}" readonly>
                </div>

                <form action="<%=request.getContextPath()%>/account/EditAccount" method="get">
                    <center>
                        <button type="submit" class="submit-button-half oneThirdDiv">Edit Profile</button>
                    </center>
                </form>
                <!--Form End-->

            </div>
            <!--Main Container-->
        </div>

        <!--Footer-->
        <jsp:include page="blocks/footer.jsp"/>


        <!-- Bootstrap core JavaScript -->
        <script> document.getElementById("account").classList.add("active");</script>
        <script src="resources/vendor/jquery/jquery.min.js"></script>
        <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    </body>

</html>
