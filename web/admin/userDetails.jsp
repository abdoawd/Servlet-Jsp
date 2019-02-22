<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="pageTitle" value="${user.firstName} ${user.lastName}"/>

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

        <!-- My Admin CSS-->
        <link href="<%=request.getContextPath()%>/resources/css/my-admin.css" rel="stylesheet">
        
        <!-- Form CSS-->
        <link href="<%=request.getContextPath()%>/resources/css/forms.css" rel="stylesheet">

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
                        <li class="breadcrumb-item">
                            <a href="<%=request.getContextPath()%>/admin/users">Users</a>
                        </li>
                        <li class="breadcrumb-item active">User</li>
                    </ol>

                    <!-- DataTables Example -->
                    <div class="card mb-3">
                        <div class="card-header">
                            <i class="fas fa-table"></i>
                            #${user.id} - ${pageTitle}
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <div class="columnTwoThird container">
                                    <div class="centeredDiv">
                                        <label><b>First Name</b></label>
                                        <input type="text" value="${user.firstName}" name="firstName" readonly required>
                                        <input hidden="true" type="text" value="${user.id}" name="id">
                                    </div>

                                    <div class="centeredDiv centeredDiv2">
                                        <label><b>Last Name</b></label>
                                        <input type="text" value="${user.lastName}" name="lastName" readonly required>
                                    </div> 
                                    
                                    <div class="centeredDiv">
                                        <label><b>Email</b></label>
                                        <input type="text" value="${user.email}" name="firstName" readonly required>
                                    </div>

                                    <div class="centeredDiv centeredDiv2">
                                        <label><b>Job</b></label>
                                        <input type="text" value="${user.job}" name="lastName" readonly required>
                                    </div>
                                    
                                    <div class="centeredDiv">
                                        <label><b>Birthday</b></label>
                                        <input type="text" value="${user.birthday}" name="firstName" readonly required>
                                    </div>

                                    <div class="centeredDiv centeredDiv2">
                                        <label><b>Credit Limit</b></label>
                                        <input type="text" value="${user.creditlimits}" name="lastName" readonly required>
                                    </div>
                                    
                                    <div>
                                        <label><b>Address</b></label>
                                        <input type="text" value="${user.street}" name="firstName" readonly required>
                                    </div>
                                    
                                    <div class="centeredDiv">
                                        <label><b>City</b></label>
                                        <input type="text" value="${user.city}" name="firstName" readonly required>
                                    </div>

                                    <div class="centeredDiv centeredDiv2">
                                        <label><b>Country</b></label>
                                        <input type="text" value="${user.country}" name="lastName" readonly required>
                                    </div>
                                </div>







                            </div>
                        </div>
                        <div class="card-footer small text-muted">Updated 1 min ago.</div> 
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

    </body>

</html>