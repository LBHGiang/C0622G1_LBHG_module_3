<%--
  Created by IntelliJ IDEA.
  User: Hoang Gia
  Date: 10/5/2022
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="bootstrap520/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="datatables/css/dataTables.bootstrap5.min.css"/></head>
<body>
<div class="header-content">
    <div class="container">
        <div class="row">
            <div class="col-3 align-items-center bestel-logo">
                <a href="https://furamavietnam.com/vi/" class="header-logo"><img
                        src="https://furamavietnam.com/wp-content/uploads/2018/08/logo.png" alt="logo "
                        class="img-fluid" style="margin-top: 20px"></a>
            </div>
            <div class="col-7 align-items-center bestel-logo">
            </div>
            <div class="col-2 align-items-center bestel-logo">
                <div style="margin-top: 40px; color: #9933cc">Lê Bá Hoàng Giang</div>
            </div>
        </div>
    </div>
</div>

<div class="header-nav js-header-nav sticky bg-light">
    <div class="container">
        <nav class="navbar navbar-expand-lg">
            <div class="navbar-collapse">
                <ul id="menu-furama-vi" class="menu navbar-nav w-100 js-main-menu">
                    <li id="nav-menu-item-5006"
                        class="nav-item  menu-item-even menu-item-depth-0 menu-item menu-item-type-post_type menu-item-object-page">
                        <a href="https://furamavietnam.com/vi/the-resort/" class="nav-link main-menu-link">HOME</a></li>
                    <li id="nav-menu-item-5007"
                        class="nav-item  menu-item-even menu-item-depth-0 menu-item menu-item-type-post_type menu-item-object-page">
                        <a href="https://furamavietnam.com/vi/our-rooms/" class="nav-link main-menu-link">EMPLOYEE</a>
                    </li>
                    <li id="nav-menu-item-5008"
                        class="nav-item  menu-item-even menu-item-depth-0 menu-item menu-item-type-post_type menu-item-object-page">
                        <a href="https://furamavietnam.com/vi/culinary/" class="nav-link main-menu-link">CUSTOMER</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            SERVICE
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">VILLA</a></li>
                            <li><a class="dropdown-item" href="#">HOUSE</a></li>
                            <li><a class="dropdown-item" href="#">ROOM</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                        </ul>
                    </li>
                    <li id="nav-menu-item-5010"
                        class="nav-item  menu-item-even menu-item-depth-0 menu-item menu-item-type-post_type menu-item-object-page">
                        <a href="https://furamavietnam.com/vi/spa-and-fitness/"
                           class="nav-link main-menu-link">CONTRACT</a>
                    </li>
                </ul>
                <form class="d-flex" action="/products?action=find" method="post">
                    <input type="search" placeholder="Tìm kiếm" aria-label="Search" name="name" id="searchName">
                    <input type="submit" value="Tìm kiếm" class="btn btn-outline-success">
                </form>
            </div>
        </nav>
    </div>
</div>

<div class="container-fluid">
    <div class="row" style="width: 100%; padding: 0; margin: 0;">
        <div id="left" class="col-2">
            <ul id="ul_left" style="list-style-type: none; margin-left: -25px; margin-top: 50px">
                <li><a href="/products?action=create" class="btn btn-primary" role="button">Thêm mới sản phẩm</a></li>
                <li><a href="/products?action=create" class="btn btn-primary" role="button">Thêm mới sản phẩm</a></li>
                <li><a href="/products?action=create" class="btn btn-primary" role="button">Thêm mới sản phẩm</a></li>
                <li><i class="fa-regular fa-cart-shopping-fast"></i><a href="#">Item 4</a></li>
                <li><i class="fa-thin fa-balloons"></i><a href="#">Item 5</a></li>
            </ul>
        </div>
        <div id="content" class="col-10">

            <h1>Chỉnh sửa sản phẩm</h1>
            <p>
                <c:if test="${message != null}">
                    <span class="message">${message}</span>
                </c:if>
            </p>
            <p>
                <a href="/products">Quay lại Danh sách sản phẩm</a>
            </p>
            <form method="post">
                <fieldset>
                    <legend style="color: blue;">Chỉnh sửa dịch vụ</legend>
                    <table class="table table-striped">
                        <tr>
                            <td>Tên:</td>
                            <td><input type="text" name="name" id="name" value="${product.getName()}"></td>
                        </tr>
                        <tr>
                            <td>Giá:</td>
                            <td><input type="number" name="price" id="price" value="${product.getPrice()}"></td>
                        </tr>
                        <tr>
                            <td>Mô tả:</td>
                            <td><input type="text" name="description" id="description" value="${product.getDescription()}"></td>
                        </tr>
                        <tr>
                            <td>Nhà sản xuất:</td>
                            <td><input type="text" name="producer" id="producer" value="${product.getProducer()}"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Update product"></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div>
    </div>
</div>

<footer class="bd-footer py-1 mt-5 bg-light">
    <div class="container py-3">
        <div class="row">
            Đây là footer
        </div>
    </div>
</footer>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
</html>
