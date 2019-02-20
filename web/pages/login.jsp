<%-- 
    Document   : login
    Created on : Feb 13, 2019, 11:42:44 AM
    Author     : A7med
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        

        <!-- Custom styles for login page -->
        <link href="../resources/css/login.css" rel="stylesheet">

        <title>Login</title>
        
    </head>
    <body>
        <form action='<%=request.getContextPath()%>/Login' method='post' class="modal-content">
            <div class="container">
                <h1>iTi | Login</h1>
                <p>Please fill in this form to Login.</p>
                <hr>
                <label for="email"><b>E-Mail</b></label>

                <input type="text" placeholder="Enter your email" name="email" value="awadabdo222@gmail.com" id ="userEmail" required>

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" value="a" name="password"  id="userPassword" required>

                <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me

                <div class="clearfix">
                    <button type="submit" class="signupbtn" >Login</button>
                    <p><a href="signup.jsp">Create new account</a></p>
                </div>
                <div class="wrongText" id="wrongText">Wrong Username/ password</div>
            </div>
        </form>
        <script src="../resources/js/validation.js"></script>
    </body>
</html>
