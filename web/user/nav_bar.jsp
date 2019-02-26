<%-- 
    Document   : nav_bar
    Created on : Feb 24, 2019, 11:17:06 PM
    Author     : FAMA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
    </head>
    <body>
        
            <div class="list-group">

                            <a href="<%=request.getContextPath()%>/account" class="list-group-item">Account Details</a>
                            <a href="<%=request.getContextPath()%>/account/EditAccount" class="list-group-item">Edit Profile</a>
                            <a href="#" class="list-group-item">Orders</a>
                            <a href="<%=request.getContextPath()%>/account/AddNewInterest" class="list-group-item">Add Interest</a>
                            <a href="<%=request.getContextPath()%>/account/RemoveInterest" class="list-group-item">Remove Interest</a>

                            <a href="/account/AddCreditCard" class="list-group-item">Add Credit Number</a>
                            <a href="<%=request.getContextPath()%>/logout" class="list-group-item">Logout</a>
                        </div>
                        
                        
        <!-- Bootstrap core JavaScript-->
        <script src="<%=request.getContextPath()%>/resources/vendor/jquery/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
