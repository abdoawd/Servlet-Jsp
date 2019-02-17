<%-- 
    Document   : AccountDetails
    Created on : Feb 14, 2019, 8:20:25 PM
    Author     : Nesmaa iTi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
                <!-- Bootstrap core CSS -->
        <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


        
                <!-- Page level plugin CSS-->
        <link href="<%=request.getContextPath()%>/resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

                <!-- Form CSS-->
        <link href="<%=request.getContextPath()%>/resources/css/login.css" rel="stylesheet">
        
        
        
    </head>
    <body>


        <jsp:include page="/blocks/header.jsp"/>

        <!-- Page Content -->
        <div class="container">

            <!-- Page Content -->
            <div class="container containerSpacing">

                <h1 class="divHeader">User Name</h1>

                <div class="row" style="flex-flow: inherit;">

                    <div class="col-lg-3">
                        <div class="list-group">
                            <a href="../account.jsp" class="list-group-item">Account Details</a>
                            <a href="EditAccount.jsp" class="list-group-item">Edit Profile</a>
                            <a href="#" class="list-group-item">Orders</a>
                            <a href="#" class="list-group-item">Logout</a>
                        </div>
                    </div>


                    <!-- DataTables Example -->
                    <div class="card mb-3" id="form">
                        <div class="card-body">


                            <form action='<%=request.getContextPath()%>/admin/addProduct' method='post' enctype="multipart/form-data" class="my-modal-content">
                                <!-- Left Column - .col-lg-9 -->
                                <div class="columnTwoThird" style="max-width:90%;">
                                    <div class="container">
                                        <div class="centeredDiv">
                                            <label><b>First Name</b></label>
                                            <input type="text"  name="firstName" value="${user.firstName}">
                                        </div>

                                        <div class="centeredDiv centeredDiv2">
                                            <label><b>Last Name</b></label>
                                            <input type="text"  name="lastName" value="${user.lastName}">
                                        </div>

                                        <div class="centeredDiv">
                                            <label><b>Email</b></label>
                                            <input type="email"   name="email" value="${user.email}">
                                        </div>

                                        <div class="centeredDiv centeredDiv2">
                                            <label><b>Password</b></label>
                                            <input type="text" name="password" value="${user.password}">
                                        </div>
                                        <div class="centeredDiv ">
                                            <label><b>Job</b></label>
                                            <input type="text"  name="job" value="${user.job}">
                                        </div>
                                        <div class="centeredDiv centeredDiv2">
                                            <label><b>Credit Limit</b></label>
                                            <input type="number" min="0" step=".01"  name="creditlimits" value="${user.creditlimits}">
                                        </div>

                                        <div>
                                            <label><b>Address</b></label>
                                            <span><input type="text" value="street"/></span>  
                                            <span><input type="text" value="city"/></span>
                                            <span><input type="text" value="country"/></span>
                                        </div>

                                        <div class="centeredDiv centeredDiv2">
                                            <label><b>Profile Image</b></label>
                                            <input type="file" name="profileImage" size="5" accept="image/*" >
                                        </div>

                                        
                                    </div>
                                    <div>
                                        <button type="submit" >Save Changes</button>
                                    </div>
                                </div>
                                <!-- / Left Column - .col-lg-9 -->
                            </form>





                        </div>
                                
                        <c:choose>
                            <c:when test="${isChanged == 'true'}">
                                <div class="card-footer small text-muted">Changes saved successfully.</div>
                            </c:when> 
                            <c:when test="${isChanged == 'false'}">
                                <div class="card-footer small text-muted">An error occurred. Please check your input and try again.</div>
                            </c:when>
                            <c:otherwise>
                                <div class="card-footer small text-muted">Everything is just fine.</div>
                            </c:otherwise>
                        </c:choose>

                        
                    </div>
                        

                    </div>
                </div>
            </div>       
 <jsp:include page="/blocks/footer.jsp"/>

 <script src="<%=request.getContextPath()%>/resources/js/demo/datatables-demo.js"></script>
 
        <!-- Bootstrap core JavaScript-->
        <script src="<%=request.getContextPath()%>/resources/vendor/jquery/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


    </body>
</html>