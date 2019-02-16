<ul class="sidebar navbar-nav">
    <li class="nav-item active">
        <a class="nav-link" href="<%=request.getContextPath()%>/admin">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span>
        </a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="#">
            <i class="fas fa-fw fa-chart-area"></i>
            <span>Orders</span></a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/admin/users">
            <i class="fas fa-fw fa-table"></i>
            <span>Users</span></a>
    </li>

    <li class="nav-item dropdown">
        <a class="nav-link" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-fw fa-folder"></i>
            <span>Product</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="productDropdown">
            <h6 class="dropdown-header">Add:</h6>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/admin/addProduct">Add Product</a>
            <div class="dropdown-divider"></div>
            <h6 class="dropdown-header">Edit/ Delete:</h6>
            <a class="dropdown-item" href="#">Edit Product</a>
            <a class="dropdown-item" href="#">Delete Product</a>
        </div>
    </li>

    <li class="nav-item dropdown">
        <a class="nav-link" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-fw fa-folder"></i>
            <span>Category</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="productDropdown">
            <h6 class="dropdown-header">Add:</h6>
            <a class="dropdown-item" href="login.html">Add Category</a>
            <div class="dropdown-divider"></div>
            <h6 class="dropdown-header">Edit/ Delete:</h6>
            <a class="dropdown-item" href="404.html">Edit Category</a>
            <a class="dropdown-item" href="blank.html">Delete Category</a>
        </div>
    </li>
</ul>