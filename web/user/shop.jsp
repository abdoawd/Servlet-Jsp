<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="pageTitle" value="Users"/>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Dokan</title>

        <!-- Bootstrap core CSS -->
        <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="resources/css/shop-homepage.css" rel="stylesheet">

    </head>

    <body>
        <!--Add Header Block-->
        <jsp:include page="../pages/userheader.jsp"/>  
        <style>
            .div_categories_container{
                display: flex ;
            }
            .div_categories_container .col-md-4{
                flex:0 0 100%;
                max-width: 100%;

            }
            .container{
                margin: auto;
            }
            .containerSpacing{
                justify-content: space-between;
            }
            .quantity {
                position: relative;
            }

            input[type=number]::-webkit-inner-spin-button,
            input[type=number]::-webkit-outer-spin-button
            {
                -webkit-appearance: none;
                margin: 0;
            }

            input[type=number]
            {
                -moz-appearance: textfield;
            }

            .quantity input {
                width: 45px;
                height: 42px;
                line-height: 1.65;
                float: left;
                display: block;
                padding: 0;
                margin: 0;
                padding-left: 20px;
                border: 1px solid #eee;
            }

            .quantity input:focus {
                outline: 0;
            }

            .quantity-nav {
                float: left;
                position: relative;
                height: 42px;
            }

            .quantity-button {
                position: relative;
                cursor: pointer;
                border-left: 1px solid #eee;
                width: 20px;
                text-align: center;
                color: #333;
                font-size: 13px;
                font-family: "Trebuchet MS", Helvetica, sans-serif !important;
                line-height: 1.7;
                -webkit-transform: translateX(-100%);
                transform: translateX(-100%);
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                -o-user-select: none;
                user-select: none;
            }

            .quantity-button.quantity-up {
                position: absolute;
                height: 50%;
                top: 0;
                border-bottom: 1px solid #eee;
            }

            .quantity-button.quantity-down {
                position: absolute;
                bottom: -1px;
                height: 50%;
            }
            .searchByName{
                border-radius: 4px;
                background-color: #f4511e;
                text-align: center;
                font-size: 28px;
                width: 200px;
                transition: all 0.5s;
                cursor: pointer;
            }
            .myButtonClass{
                background-color: #4CAF50; 
                border: 3px;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                border-radius: 12px;
            }
            .inputTypeNumber{
                height: 30px;
                margin-top: 15px;
                display: inline-block;
                margin-bottom: 20px;
            }
        </style>

        <!-- Page Content -->
        <div class="container containerSpacing row justify-content-between ">
            <div class="col-md-3 containerSpacing">
                <div>
                    <c:forEach var="category" items="${categories}">    
                        <c:url var="category_selected" value="/UserHomeServlet">
                            <c:param name="category_id" value="${category.id}"/>
                        </c:url>
                        <a href="${category_selected}">
                            <div class="sidebar navbar-nav" >
                                <div class="nav-item active">
                                    <div class="nav-link">
                                        <h4 class="card-title">
                                            ${category.getName()}
                                        </h4>
                                    </div>

                                </div>

                            </div>
                        </a>    

                    </c:forEach>
                    <div>
                        <select id="product" class="custom-select" style="width:100%" onclick="select(this)">
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>

                        <input type="number" id="start_salary" class="inputTypeNumber"  min="0" max="84999" >
                        <input type="number"id="end_salary" 
                               class="inputTypeNumber" min="2241" value="85000">

                        <button type="button" class="myButtonClass" onc  data-type="Price" onclick="searchByPrice()" >
                            Search
                        </button>
                    </div>
                </div>
            </div>

            <div class=" col-md-8" style="display: inline-flex; flex-wrap: wrap; margin-top: 55px;">
                <c:forEach var="product" items="${products}">   
                    <c:url var="product_details" value="ProductDetails">
                        <c:param name="product_id" value="${product.id}"/>
                    </c:url>
                    <div class=" col-lg-4 col-md-6 mb-4"   >


                        <div class="asds card h-100" id="45" >
                            <img class="card-img-top" id="45" src="data:image/jpeg;base64,${product.stringImage}" alt=""></a>

                            <a href="${product_details}"> 

                                <div id="45" class="card-body">
                                    <h4 class="card-title">
                                        ${product.getName()}
                                    </h4>
                                    <h5>${" price "}
                                        ${product.getPrice()}
                                        ${" $"}</h5>
                                </div>
                            </a>

                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
        
        
        
        
        
        
        
        
        
        
        
        
        
        <div class="row">

            <div class="col-lg-9">


                <jsp:include page="../blocks/footer.jsp"/>

                <!-- Bootstrap core JavaScript -->
                <script> document.getElementById("home").classList.add("active");</script>
                <script src="resources/vendor/jquery/jquery.min.js"></script>
                <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
                <script>
                    function  searchByPrice()
                    {
                        if (document.getElementById("start_salary").value != '')
                        {
                        var e= document.getElementById("product")
                        var category = e.options[e.selectedIndex].value;
                            document.location.href = "/dokan/UserHomeServlet?start_salary="
                                    + document.getElementById("start_salary").value + "&end_salary="
                                    + document.getElementById("end_salary").value+"&category="+category;

                        } else {
                            alert("object is null")

                        }
                    }

                </script>

                </body>

                </html>
