<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set scope="page" var="pageTitle" value="Delete Product"/>

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
                            Choose a product you want to delete.</div>
                        <div class="card-body">
                            <form action='<%=request.getContextPath()%>/admin/editProduct' method='post' class="my-modal-content">
                                <div class="columnTwoThird">
                                    <div class="container">
                                        <div class="twoThirdDiv">
                                            <label><b>Product Name</b></label>
                                            <select name="productId" class="custom-select" style="width:100%" required>
                                                <c:forEach var="product" items="${productsList}">
                                                    <option value="${product.id}">${product.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="oneThirdDiv">
                                            <button type="submit" class="submit-button-half errorColor">Delete</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <c:choose>
                            <c:when test="${isProductDeleted == 'true'}">
                                <div class="card-footer small text-muted">Product deleted successfully.</div>
                            </c:when> 
                            <c:when test="${isProductDeleted == 'false'}">
                                <div class="card-footer small text-muted">An error occurred. Please check your input and try again.</div>
                            </c:when>
                        </c:choose>
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
    </body>

</html>