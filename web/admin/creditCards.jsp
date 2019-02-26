<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set scope="page" var="pageTitle" value="Credit Cards"/>

<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>${pageTitle}</title>

        <!-- Page level plugin CSS-->
        <link href="<%=request.getContextPath()%>/resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="<%=request.getContextPath()%>/resources/css/sb-admin.css" rel="stylesheet">

        <!-- Form CSS-->
        <link href="<%=request.getContextPath()%>/resources/css/forms.css" rel="stylesheet">

        <!-- Select CSS-->
        <link href="<%=request.getContextPath()%>/resources/css/customSelect.css" rel="stylesheet">
    </head>

    <body id="page-top">

        <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

            <a class="navbar-brand mr-1" href="<%=request.getContextPath()%>/admin">Dokan</a>

            <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
                <i class="fas fa-bars"></i>
            </button>
        </nav>

        <div id="wrapper">

            <!-- Sidebar -->
            <jsp:include page="../blocks/adminSideBar.jsp"/>

            <div id="content-wrapper">

                <div class="container-fluid">

                    <!-- Breadcrumbs-->
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="<%=request.getContextPath()%>/admin">Dashboard</a>
                        </li>
                        <li class="breadcrumb-item active">${pageTitle}</li>
                    </ol>

                    <!-- DataTables Example -->
                    <div class="card mb-3">
                        <div class="card-header">
                            <i class="fas fa-table"></i>
                            Add a card number to be added.</div>
                        <div class="card-body">
                            <form action='<%=request.getContextPath()%>/admin/creditcards' method='post' class="my-modal-content">
                                <div class="columnTwoThird container">
                                    <button type="button" onclick="getRandomValue()" style="width: auto;padding: 14px;"><img src="../resources/pictures/refresh-button.png" width="80%"/></button>
                                    <div class="oneThirdDiv">
                                        <label><b>Card Number</b></label>
                                        <input type="number" placeholder="Enter Card Number" name="cardNumber" pattern="\d+" min="100000000000" max="999999999999" required>
                                    </div>
                                    <div class="oneThirdDiv">
                                        <label><b>Card Value</b></label>
                                        <input type="number" placeholder="Enter Card Value" name="cardValue" type="number" min="1" step=".01" required>
                                    </div>
                                    <div class="oneThirdDiv">
                                        <button type="submit" class="submit-button-half">Add</button>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <c:choose>
                            <c:when test="${isCardAdded == 1}">
                                <div class="card-footer small textSuccess">Card added successfully.</div>
                            </c:when> 
                            <c:when test="${isCardAdded == -1}">
                                <div class="card-footer small textWarning">Same card number already exist!</div>
                            </c:when> 
                            <c:when test="${isCardAdded == 0}">
                                <div class="card-footer small textFail">An error occurred. Please check your input and try again.</div>
                            </c:when>
                        </c:choose>
                        
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Card Number</th>
                                            <th>Amount</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${cardsList}">                                                
                                        <tr>
                                            <td>${user.number}</td>
                                            <td>${user.amount}</td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->

                <!-- Sticky Footer -->
                <jsp:include page="../blocks/adminFooter.jsp"/>

            </div>
            <!-- /.content-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Bootstrap core JavaScript-->
        <script src="<%=request.getContextPath()%>/resources/vendor/jquery/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="<%=request.getContextPath()%>/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="<%=request.getContextPath()%>/resources/js/sb-admin.min.js"></script>

        <!-- Demo scripts for this page-->
        <script src="<%=request.getContextPath()%>/resources/js/demo/datatables-demo.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/demo/chart-area-demo.js"></script>

        <!-- Select scripts for this page-->
        <script src="<%=request.getContextPath()%>/resources/js/demo/customSelect.js"></script>

        <!-- Generate random value -->
        <script>
            function getRandomValue() {
                var x = Math.floor(100000000000 + Math.random() * 900000000000);
                console.log(x);
                document.querySelector("input[name='cardNumber']").value = x;
            }
        </script>
    </body>

</html>