<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="pageTitle" value="Dokan | Shop"/>
<c:set var="currency" value="EGP"/>

<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>${pageTitle}</title>

        <!-- Bootstrap core CSS -->
        <link href="<%=request.getContextPath()%>/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="<%=request.getContextPath()%>/resources/css/shop-homepage.css" rel="stylesheet">

        <!-- Custom styles for search results page -->
        <link href="<%=request.getContextPath()%>/resources/css/search-results-page.css" rel="stylesheet">


    </head>

    <body>
        <!--Add Header Block-->
        <jsp:include page="../blocks/header.jsp"/>  

        <!-- Page Content -->
        <div class="justify-content-center container containerSpacing row" style="margin: 5em auto;">
            <!--Sidebar-->
            <div class="col-lg-3">
                <div class="list-group">
                    <h6>Categories</h6>
                    <c:forEach var="category" items="${categories}">    
                        <c:url var="category_selected" value="/shop">
                            <c:param name="category_id" value="${category.id}"/>
                        </c:url>
                        <a href="${category_selected}" class="list-group-item">${category.getName()}</a>
                    </c:forEach>
                </div>
                <h6><br>Advanced Search:</h6>
                <div class="list-group">
                    <a class="list-group-item">

                        <select id="product" class="myInput" style="width:100%" onclick="select(this)">
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.id}" <c:if test="${category.id == 0}">selected</c:if>>${category.name}</option>
                            </c:forEach>
                        </select>
                        <!--Categories End-->

                        <input type="number" id="start_salary" class="myInput"  min="0" max="84999" placeholder="Min Value" >
                        <input type="number"id="end_salary" class="myInput" min="1" placeholder="Max Value">

                        <button type="button" class="myButtonClass" onc  data-type="Price" onclick="searchByPrice()" >
                            Search
                        </button>
                    </a>
                </div>
            </div>


            <!--Sidebar End-->
            <!--Main Container-->
            <div class=" col-md-9" style="display: inline-flex; flex-wrap: wrap;margin-top: 1.6em;">
                <c:forEach var="product" items="${products}">   
                    <c:url var="product_details" value="ProductDetails">
                        <c:param name="product_id" value="${product.id}"/>
                    </c:url>
                    <div class=" col-lg-4 col-md-6 mb-4">
                        <div class="card h-100 myProductCardContainer">
                            <a href="${product_details}" class="productImageContainer">
                                <img class="productImage card-img-top center" src="data:image/jpeg;base64,${product.stringImage}">
                            </a>
                            <div class="card-body">
                                <h4 class="card-title">
                                    <a href="${product_details}" class="productName">${product.getName()}</a>
                                </h4>
                                <h5 class="productPriceFinal">${product.getPrice()} ${currency}</h5>
                                <p class="productPriceDiscounted text-muted">${product.getPrice() + product.getDiscount()} ${currency}</p>
                            </div>
                            <div class="card-footer">
                                <!-------------------------------------------------------->
                                <!-- Trigger/Open The Modal -->
                                <button onclick="addProduct(${product.id})" >ADD TO CART</button>
                                <!-- The Modal -->
                                <div id="myModal" class="modal">
                                    Modal content 
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h2>Item added to cart!</h2>
                                            <span class="close">&times;</span>
                                        </div>
                                    </div>
                                </div>
                                <!-------------------------------------------------------->

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!--Main Container-->
        </div>


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
                    var e = document.getElementById("product")
                    var category = e.options[e.selectedIndex].value;
                    document.location.href = "/dokan/shop?start_salary="
                            + document.getElementById("start_salary").value + "&end_salary="
                            + document.getElementById("end_salary").value + "&category=" + category;

                }
            }
            function addProduct(id) {
                document.location.href = "/dokan/shop?addProductId=" + id;

            }
        </script>

        <script>

            // Get the modal
            var modal = document.getElementById('myModal');

// Get the button that opens the modal
            var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
            btn.onclick = function () {
                modal.style.display = "block";
            }

// When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            }

// When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
            
            function addedTOCart(){
                modal.style.display = "block";
            }

        </script>
    </body>

</html>
