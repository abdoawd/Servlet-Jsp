<%-- 
    Document   : interests
    Created on : Feb 20, 2019, 1:53:24 AM
    Author     : Nesma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
      <title>Interests</title>
        <!-- Custom styles for login page -->
        <link href="../resources/css/login.css" rel="stylesheet">
          <!-- Page level plugin CSS-->
        <link href="<%=request.getContextPath()%>/resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
        
             <!-- Form CSS-->
        <link href="<%=request.getContextPath()%>/resources/css/forms.css" rel="stylesheet">
        
         <!-- Custom styles for this template-->
        <link href="<%=request.getContextPath()%>/resources/css/sb-admin.css" rel="stylesheet">
        
        <style>
            form{
                width:75%;
            }
            
        </style>
</head>
<body>

                                <form action="<%=request.getContextPath()%>/account/AddInterest" method="post" class="modal-content">
                                    
                                    <div class="table-response container" style="text-align:center">
                                    
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                     
                                    <thead>
                                        <tr>
                                            <th>Check</th>
                                           
                                            <th>Categories</th>
                                            
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Check</th>
                                            <th>Categories</th>
                                            
                                        </tr>
                                    </tfoot>
                                  
                                    <tbody>
                                   
                                        <c:forEach items="${categoryList}" var="category" >                                                
                                        <tr>
                                            <td><input type="checkbox" name="Categories" value="${category.id}"/> </td>
                                            <td>${category.name}</td>
  
                                        </tr>
                                        </c:forEach>
                                           
                                    </tbody>
                                    
                                </table>
                                        
                                        
                                            <div class="clearfix">
                                                <button type="submit" class="submit-button-half">Add Interests</button>
                                            </div>
                                        </div>
                                        
                                    </form>
                                
                           
                
                        <script src="../resources/js/validation.js"></script>
                         <!-- Bootstrap core JavaScript-->
        <script src="<%=request.getContextPath()%>/resources/vendor/jquery/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="<%=request.getContextPath()%>/resources/vendor/jquery-easing/jquery.easing.min.js"></script>


                        
                        
    </body>
</html>
