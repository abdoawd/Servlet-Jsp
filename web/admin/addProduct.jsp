<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Dokan | New Product</title>

        <!-- Page level plugin CSS-->
        <link href="<%=request.getContextPath()%>/resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="<%=request.getContextPath()%>/resources/css/sb-admin.css" rel="stylesheet">

        <!-- Form CSS-->
        <link href="<%=request.getContextPath()%>/resources/css/login.css" rel="stylesheet">

        <!-- Select CSS-->
        <link href="<%=request.getContextPath()%>/resources/css/customSelect.css" rel="stylesheet">
    </head>

    <body id="page-top">

        <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

            <a class="navbar-brand mr-1" href="<%=request.getContextPath()%>/admin.jsp">Start Bootstrap</a>

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
                            <a href="<%=request.getContextPath()%>/admin.jsp">Dashboard</a>
                        </li>
                        <li class="breadcrumb-item active">Tables</li>
                    </ol>

                    <!-- DataTables Example -->
                    <div class="card mb-3">
                        <div class="card-header">
                            <i class="fas fa-table"></i>
                            Please fill in this to add the product.</div>
                        <div class="card-body">


                            <form action='<%=request.getContextPath()%>/admin/addProduct' method='post' enctype="multipart/form-data" class="my-modal-content">
                                <!-- Left Column - .col-lg-9 -->
                                <div class="columnTwoThird">
                                    <div class="container">
                                        <div class="centeredDiv">
                                            <label><b>Product Name</b></label>
                                            <input type="text" placeholder="Enter product name" name="productName" required>
                                        </div>

                                        <div class="centeredDiv centeredDiv2">
                                            <label><b>Quantity</b></label>
                                            <input type="text" placeholder="Enter quantity avaiable in stock" name="productQuantity" required>
                                        </div>

                                        <div class="centeredDiv">
                                            <label><b>Price</b></label>
                                            <input type="text" placeholder="Enter product price" name="productPrice" required>
                                        </div>

                                        <div class="centeredDiv centeredDiv2">
                                            <label><b>Discount</b></label>
                                            <input type="text" placeholder="Enter product discount (Optional)" name="productDiscount">
                                        </div>

                                        <div class="centeredDiv">
                                            <label><b>Category</b></label>
                                            <select name="productCategory" class="custom-select" style="width:100%">
                                                <option value="1">default</option>
                                                
                                            </select>
                                        </div>

                                        <div class="centeredDiv centeredDiv2">
                                            <label><b>Product Image</b></label>
                                            <input type="file" name="productImage" size="10" required>
                                        </div>

                                        <label><b><br>Description</b></label>
                                        <textarea placeholder="Enter product description" name="productDescription" required></textarea>
                                    </div>
                                    <div>
                                        <button type="submit" class="submit-button-half">Add</button>
                                    </div>
                                </div>
                                <!-- / Left Column - .col-lg-9 -->
                            </form>





                        </div>
                        <div class="card-footer small text-muted">Product added</div>
                    </div>



                </div>
                <!-- /.container-fluid -->

                <!-- Sticky Footer -->
                <footer class="sticky-footer">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright © Your Website 2019</span>
                        </div>
                    </div>
                </footer>

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