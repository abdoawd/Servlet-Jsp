<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set scope="page" var="pageTitle" value="Create an Account"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- Custom styles for login page -->
        <link href="<%=request.getContextPath()%>/resources/css/forms.css" rel="stylesheet">

        <title>${pageTitle}</title>
    </head
    <body>
        <div id="id01" class="modal">
            <form action="<%=request.getContextPath()%>/SinuUp" method='post' class="modal-content">
                <div class="container">
                    <img class="logoImage" src="<%=request.getContextPath()%>/resources/pictures/Dokan-Logo.png" />
                    <h1>${pageTitle}</h1>

                    <p>Please fill in this form to create an account.</p>
                    <hr>

                    <div class="centeredDiv">
                        <label for="firstName"><b>First Name</b></label>
                        <input type="text" placeholder="Enter Your First Name" name="firstName" value="nsma"required>
                    </div>

                    <div class="centeredDiv centeredDiv2">
                        <label for="lastName"><b>Last Name</b></label>
                        <input type="text" value="ahmed" placeholder="Enter Your Last Name" name="lastName" pattern="(?=.*[A-Z](?=.*[a-z]){3,})" required>
                    </div>

                    <label for="email"><b>E-Mail</b></label>
                    <input type="text" placeholder="Enter your email" name="email" id="userEmail"  required>

                    <label for="jop"><b> JOb </b></label>
                    <input type="text" placeholder="Enter your job " name="jop" required>

                    <label for="password"><b>Password</b></label>
                    <input type="password" placeholder="Enter Your Password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,}" id="userPassword" required>

                    <label for="password2"><b>Confirm Password</b></label>
                    <input type="password" placeholder="Re-Enter Your Password" name="confirmPassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,}" id="conPassword"required>
                    <div class="clearfix">
                        <button type="submit" onclick="validate()"  class="signupbtn">Sign up</button>
                    </div>
                    <div class="wrongText"   id="wrongText">Wrong Data</div>
                     <c:if test="${not empty emailNotValid}">
                         <span style="color: red">${emailNotValid}</span> 
                    </c:if>
                    
                </div>
            </form>
        </div>
        <script src="../resources/js/validation.js"></script>
    </body>
</html>
