<%-- 
    Document   : addNewInterest
    Created on : Feb 26, 2019, 1:15:22 AM
    Author     : Nesma iTi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set scope="page" var="pageTitle" value="Add Interest"/>


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

                <div class="card-body">
                    <form action='<%=request.getContextPath()%>/account/AddNewInterest' method='post' class="my-modal-content">
                        <div class="columnTwoThird container">
                            <div class="centeredDiv2">
                                <label><b>Category Name</b></label>
                                <select  name="productCategory" class="custom-select" style="width:100%">
                                    <c:forEach items="${categoryList}" var="category">

                                        <option  value="${category.id}">${category.name} </option>


                                    </c:forEach>
                                </select>

                            </div>
                            <div class="centeredDiv2">
                                <button type="submit" class="submit-button-half">Add</button>
                            </div>
                        </div>
                    </form>

                </div>

                <c:choose>
                    <c:when test="${isAdded == 'true'}">
                        <div class="card-footer small text-muted">Changes saved successfully.</div>
                    </c:when> 
                    <c:when test="${isAdded == 'false'}">
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

