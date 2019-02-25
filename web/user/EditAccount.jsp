<%-- 
    Document   : AccountDetails
    Created on : Feb 14, 2019, 8:20:25 PM
    Author     : Nesmaa iTi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

                <h1 class="divHeader">${user.firstName} ${user.lastName}</h1>

                <div class="row" style="flex-flow: inherit;">

                    <jsp:include page="nav_bar.jsp" />


                    <!-- DataTables Example -->
                    <div class="card mb-3" id="form">
                        <div class="card-body">


                            <form action='<%=request.getContextPath()%>//EditAccount' method='post' enctype="multipart/form-data" class="my-modal-content">
                                <!-- Left Column - .col-lg-9 -->
                                <div class="columnTwoThird" style="max-width:90%;">
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
                                                <span style="color: red; font-size:10; ">can't be updated</span>

                                                
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
                                            <label><b>Birthday</b></label>
                                            <c:set var="birthday" value="${user.birthday}" />
                                            <fmt:formatDate value="${birthday}" var="dateObject" pattern="MM/dd/yyyy" />
                                            <input type="date"  name="birthday" value="${user.birthday}"pattern="MM/dd/yyyy"/>
                                        </div>

                                        <div>
                                           
                                            <label><b>Street</b></label>
                                            <input type="text" value="street" name="street"/> 
                                            <label><b>City</b></label>
                                           <input type="text" value="city" name="city"/>
                                           <label><b>Country</b></label>
                                            <span><input type="text" value="country" name="country"/></span>
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
