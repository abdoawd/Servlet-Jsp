<%-- 
    Document   : AccountDetails
    Created on : Feb 14, 2019, 8:20:25 PM
    Author     : Nesmaa iTi
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set scope="page" var="pageTitle" value="Edit Account"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${pageTitle}</title>

        <!-- Bootstrap core CSS -->
        <link href="<%=request.getContextPath()%>/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Page level plugin CSS-->
        <link href="<%=request.getContextPath()%>/resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Form CSS-->
        <link href="<%=request.getContextPath()%>/resources/css/login.css" rel="stylesheet">
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


                <form action='<%=request.getContextPath()%>/account/EditAccount' method='post' enctype="multipart/form-data" class="my-modal-content">
                    <!-- Left Column - .col-lg-9 -->
                    <div class="container">
                        <div class="centeredDiv">
                            <label><b>First Name</b></label>
                            <input type="text"  name="firstName" value="${user.firstName} ">
                        </div>

                        <div class="centeredDiv centeredDiv2">
                            <label><b>Last Name</b></label>
                            <input type="text"  name="lastName" value="${user.lastName}">
                        </div>

                        <div class="centeredDiv">
                            <label><b>Email</b></label>
                            <input type="email"   name="email" value="${user.email}" readonly />
                            <!--<span style="color: red; font-size:10; ">can't be updated</span>-->
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
                            <input type="number" min="0" step=".01"  name="creditlimits" value="${user.creditlimits}" readonly>
                        </div>

                        <div>
                            <label><b>Birthday</b></label>
                            <c:set var="birthday" value="${user.birthday}" />
                            <fmt:formatDate value="${birthday}" var="dateObject" pattern="MM/dd/yyyy" />
                            <input type="date"  name="birthday" value="${birthday}"pattern="MM/dd/yyyy"/>
                        </div>

                        <div>
                            <label><b>Street</b></label>
                            <input type="text" value="${user.address.street}" name="street"/> 
                        </div>

                        <div class="centeredDiv">
                            <label><b>City</b></label>
                            <input type="text" value="${user.address.city}" name="city"/>
                        </div>
                        <div class="centeredDiv centeredDiv2">
                            <label><b>Country</b></label>
                            <span><input type="text" value="${useraddress.country}" name="country"/></span>
                        </div>

                        <div class="centeredDiv centeredDiv2">
                            <label style="display: block;"><b>Profile Image</b></label>
                            <img id="productImageDisplay" hidden="true" src="#"/>
                            <input type="file" onchange="loadFile(event)" name="profileImage" size="5" accept="image/*" required>
                            <!-- To change the profile image based on the uploaded image -->
                            <script>
                                var loadFile = function (event) {
                                    var output = document.getElementById('productImageDisplay');
                                    output.height = 50;
                                    output.hidden = false;
                                    output.src = URL.createObjectURL(event.target.files[0]);

                                };
                            </script>
                        </div>

                        <div>
                            <button type="submit" >Save Changes</button>
                        </div>
                    </div>
                    <!-- / Left Column - .col-lg-9 -->
                </form>


                <c:choose>
                    <c:when test="${isChanged == 'true'}">
                        <div class="card-footer small text-muted">Changes saved successfully.</div>
                    </c:when> 
                    <c:when test="${isChanged == 'false'}">
                        <div class="card-footer small text-muted">An error occurred. Please check your input and try again.</div>
                    </c:when>
                    <c:otherwise>
                        <div class="card-footer small text-muted"></div>
                    </c:otherwise>
                </c:choose>

            </div>
            <!--Main Container-->
        </div>

        <!--Footer-->
        <jsp:include page="../blocks/footer.jsp"/>


        <script src="<%=request.getContextPath()%>/resources/js/demo/datatables-demo.js"></script>

        <!-- Bootstrap core JavaScript-->
        <script src="<%=request.getContextPath()%>/resources/vendor/jquery/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


    </body>
</html>
