<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Account</title>

        <!-- Bootstrap core CSS -->
        <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="resources/css/shop-homepage.css" rel="stylesheet"/>
        <style>
             .data{
                    padding: 5px 10px;
            }
            table{
                spacing:10;
            }
            
        </style>
        
        
        

    </head>

    <body>
        <!--Add Header Block-->
        <jsp:include page="blocks/header.jsp"/>
        
        <!-- Page Content -->
        <div class="container">

            <!-- Page Content -->
            <div class="container containerSpacing">
                
                <h1 class="divHeader">${user.firstName} ${user.lastName}</h1>
                
                <div class="row">

                     <jsp:include page="/user/nav_bar.jsp" />
                    <!-- /.col-lg-3 -->

                    <div class="col-lg-9">
                    <br>
                       
                        
                                            <fieldset>
                                               
                    <br>
                    <table>
                       
                        <tr>
                            <th>First Name: </th>
                            <td class="data">${user.firstName}</td>
                        </tr>
                        <tr></tr>
                        <tr>
                            <th>Last Name: </th>
                            <td class="data">${user.lastName}</td>
                        </tr>
                         <tr>
                            <th>E-Mail: </th>
                            <td class="data">${user.email}</td>
                         </tr>
                 
                        <tr>
                            <th>Password: </th>
                            <td class="data">${user.password}</td>
                        </tr>
                        
                        <tr>
                            <th>Job: </th>
                            <td class="data">${user.job}</td>
                        </tr>
                        <tr>
                            <th>Street: </th>
                            <td class="data" >${user.address.street}</td>
                        </tr>
                        <tr>
                            <th>City: </th>
                            <td class="data" >${user.address.city}</td>
                        </tr>
                        <tr>
                            <th>Country: </th>
                            <td class="data" >${user.address.country}</td>
                        </tr>
                        <tr>
                            <th>Credit-limit:  </th>
                            <td class="data">${user.creditlimits}</td>
                        </tr>
                        
                        
                        
                    </table>
                    <br>
                    <form action="<%=request.getContextPath()%>/account/EditAccount" method="get">
                    <input type="submit" value="Edit Profile" id="editbtn" />
                    </form>
                </fieldset>

                    </div>
                    <!-- /.col-lg-9 -->

                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->

        </div>
        <!-- /.container -->

        <!--Add Footer Block-->
        <jsp:include page="blocks/footer.jsp"/>

        <!-- Bootstrap core JavaScript -->
        <script> document.getElementById("account").classList.add("active");</script>
        <script src="resources/vendor/jquery/jquery.min.js"></script>
        <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    </body>

</html>
