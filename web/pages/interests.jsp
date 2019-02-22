<%-- 
    Document   : interests
    Created on : Feb 20, 2019, 1:53:24 AM
    Author     : Nesma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>interests</title>
                <!-- Bootstrap core CSS -->
        <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="resources/css/shop-homepage.css" rel="stylesheet">
    </head>
    <body>

 <!--Add Header Block-->
        <jsp:include page="../blocks/header.jsp"/>  
       

     <!-- Page Content -->
     <div class="container containerSpacing" style="width: 50%;">
         <form action="" method="post" style="width: 100%;">
        <div class="row">
            

                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100">
                     
                        <div class="card-body">
                            <h4 class="card-title">                           
                            Clothes
                            <input type="checkbox" name="interests" value="clothes">
                             </h4>
                        </div>
                        
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100">
                      
                        <div class="card-body">
                           <h4 class="card-title">                           
                            Electronics
                            <input type="checkbox" name="interests" value="Electronics">
                             </h4>
                           
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100">
                        <img class="card-img-top" src="../resources/pictures/p1.png" alt="">
                        <div class="card-body">
                            <h4 class="card-title">
                                Fashions <input type="checkbox" name="interests" value="fashion" >
                            </h4>
                           
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100">
                        <img class="card-img-top" src="../resources/pictures/p1.png" alt="">
                        <div class="card-body">
                            <h4 class="card-title">
                            Shoes <input type="checkbox" name="interests" value="Shoes" >

                            </h4>
                           
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100">
                       <img class="card-img-top" src="../resources/pictures/p1.png" alt="">
                        <div class="card-body">
                            <h4 class="card-title">
                            
                             Smart Devices <input type="checkbox" name="interests" value="Smart_Devices" >

                            </h4>
                           
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100">
                        <a href="#"><img class="card-img-top" src="../resources/pictures/p1.png" alt=""></a>
                        <div class="card-body">
                            <h4 class="card-title">
                              Furniture <input type="checkbox" name="interests" value="Furniture" >
                            </h4>
                            
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                        </div>
                    </div>
                </div>
                   <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100">
                        <a href="#"><img class="card-img-top" src="../resources/pictures/p1.png" alt=""></a>
                        <div class="card-body">
                            <h4 class="card-title">
                              Books <input type="checkbox" name="interests" value="Books" >
                            </h4>
                            
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                        </div>
                    </div>
                </div>
            
                
           
        </div>
             <div>
                <input type="submit" value="Go to Home Page"/>
            </div>
          </form>
        </div>
         <script src="resources/vendor/jquery/jquery.min.js"></script>
        <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        
        
    </body>
</html>
