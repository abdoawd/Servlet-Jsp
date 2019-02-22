<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Checkout</title>

        <!-- Bootstrap core CSS -->


        <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for login page -->
        <link href="resources/css/forms.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="resources/css/shop-homepage.css" rel="stylesheet">

    </head>

    <body>
        <!--Add Header Block-->
        <jsp:include page="blocks/headerLogoOnly.jsp"/>

        <!-- Page Content -->
        <div class="container">

            <!-- Page Content -->
            <div class="container containerSpacing">

                <h1 class="divHeader">Shopping Cart</h1>
                <div class="row">
                    <form action='checkout' method='post' class="my-modal-content">
                        <!-- Left Column - .col-lg-9 -->
                        <div class="columnTwoThird">
                            <div class="container">
                                <p>Please fill in this form to complete your order.</p>
                                <hr>

                                <div class="centeredDiv">
                                    <label for="firstName"><b>FirstName</b></label>
                                    <input type="text" value="${sessionScope.user.firstName}" placeholder="Enter Your First Name" name="firstName" required>
                                </div>

                                <div class="centeredDiv centeredDiv2">
                                    <label for="lastName"><b>Last Name</b></label>
                                    <input type="text" value="${sessionScope.user.lastName}" placeholder="Enter Your Last Name" name="lastName" required>
                                </div>

                                <label for="email"><b>E-Mail</b></label>
                                <input type="text"value="${sessionScope.user.email}" placeholder="Enter your email" name="email" required>

                                <label for="street"><b>Street</b></label>
                                <input type="text" placeholder="Enter Your Street" name="street" required>

                                <div class="centeredDiv">
                                    <label for="city"><b>City</b></label>
                                    <input type="text" placeholder="Enter Your City" name="city" required>
                                </div>

                                <div class="centeredDiv centeredDiv2">
                                    <label for="country"><b>Country</b></label>
                                    <input type="text" name="country" value="Egypt" placeholder="Enter Your Country" required>
                                </div>
                            </div>

                        </div>
                        <!-- / Left Column - .col-lg-9 -->

                        <!-- Right Column - .col-lg-3 -->
                        <div class="columnThird">
                            <div class="list-group">
                                <a class="list-group-item">Order Summary</a>
                                <div class="list-group-item">

                                    <c:forEach var="checkoutItem" items="${checkoutCartList}">
                                        <h4>"${checkoutItem.productName}" </h4>
                                    </c:forEach>

                                    <hr>
                                    <h3>${totalPrices} EGP</h3>
                                </div>
                                <div class="list-group-item">
                                    <p><b>Payment Method</b></p>
                                    <input type="radio" name="payment" value="cashOnDelivery" checked="true" onclick="hideCredit()"> Cash On Delivery<br>
                                    <input type="radio" name="payment" value="credit" onclick="showCredit()"> Credit Card<br>
                                    <div id="creditDetails">
                                        <hr>
                                        <table class="table-checkout">
                                            <tr>
                                                <td>Your Card Limit</td>
                                                <td>500EGP</td>
                                            </tr>
                                            <tr>
                                                <td>Order Total</td>
                                                <td>159.99</td>
                                            </tr>
                                            <tr><td colspan="2">Sufficient Credit!</td></tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="clearfix">
                                    <button type="submit" class="submit-button">Complete Your Order</button>
                                </div>
                                <div class="wrongText" id="wrongText">An error occurred!</div>
                            </div>
                        </div>


                        <!-- /Right Column - .col-lg-3 -->
                    </form>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->

        </div>
        <!-- /.container -->

        <!--Add Footer Block-->
        <jsp:include page="blocks/footer.jsp"/>

        <!-- Bootstrap core JavaScript -->
        <script src="resources/vendor/jquery/jquery.min.js"></script>
        <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


        <!--Script to display and hide credit details-->
        <script>
                                        function showCredit() {
                                            document.getElementById('creditDetails').style.display = "block";
                                        }
                                        function hideCredit() {
                                            document.getElementById('creditDetails').style.display = "none";
                                        }
        </script>

    </body>
</html>