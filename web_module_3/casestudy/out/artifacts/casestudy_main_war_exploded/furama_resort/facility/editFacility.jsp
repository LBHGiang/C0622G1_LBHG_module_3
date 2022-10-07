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
    <%--    <link href="/bootstrap/css/bootstrap.min.css">--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body onload="openInput(${facility.facilityTypeId})">
<%--Header--%>
<div class="header-content">
    <div class="container">
        <div class="row">
            <div class="col-3 align-items-center bestel-logo">
                <a href="/furama_resort/home.jsp" class="header-logo"><img
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

<%--Navbar--%>
<div class="header-nav js-header-nav sticky bg-light">
    <div class="container">
        <nav class="navbar navbar-expand-lg">
            <div class="navbar-collapse">
                <ul id="menu-furama-vi" class="menu navbar-nav w-100 js-main-menu">
                    <li id="nav-menu-item-5006"
                        class="nav-item  menu-item-even menu-item-depth-0 menu-item menu-item-type-post_type menu-item-object-page">
                        <a href="/furama_resort/home.jsp" class="nav-link main-menu-link">HOME</a></li>
                    <li id="nav-menu-item-5007"
                        class="nav-item  menu-item-even menu-item-depth-0 menu-item menu-item-type-post_type menu-item-object-page">
                        <a href="https://furamavietnam.com/vi/our-rooms/" class="nav-link main-menu-link">EMPLOYEE</a>
                    </li>
                    <li id="nav-menu-item-5008"
                        class="nav-item  menu-item-even menu-item-depth-0 menu-item menu-item-type-post_type menu-item-object-page">
                        <a href="https://furamavietnam.com/vi/culinary/" class="nav-link main-menu-link">CUSTOMER</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="/facilities" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            SERVICE
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="/facilities">All Facilities</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="/facilities?action=villa">VILLA</a></li>
                            <li><a class="dropdown-item" href="/facilities?action=house">HOUSE</a></li>
                            <li><a class="dropdown-item" href="/facilities?action=room">ROOM</a></li>
                        </ul>
                    </li>
                    <li id="nav-menu-item-5010"
                        class="nav-item  menu-item-even menu-item-depth-0 menu-item menu-item-type-post_type menu-item-object-page">
                        <a href="https://furamavietnam.com/vi/spa-and-fitness/"
                           class="nav-link main-menu-link">CONTRACT</a>
                    </li>
                </ul>
                <form class="d-flex" action="/facilities?action=find" method="post">
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
                <li><a href="/facilities?" class="btn btn-primary" role="button">Quay lại Dịch vụ</a></li>
                <li><a href="furama_resort/home.jsp" class="btn btn-primary" role="button">Quay lại Trang chủ</a></li>
                <li><i class="fa-regular fa-cart-shopping-fast"></i><a href="#">Item 4</a></li>
                <li><i class="fa-thin fa-balloons"></i><a href="#">Item 5</a></li>
            </ul>
        </div>
        <div id="content" class="col-10">
            <h1 style="text-align: center; color: blue">Chỉnh sửa dịch vụ</h1>
            <form method="post">
                <fieldset>
                    <table>
                        <tr>
                            <td>Tên:</td>
                            <td><input type="text" name="name" id="name" value="${facility.name}"></td>
                        </tr>
                        <tr>
                            <td>Kiểu dịch vụ</td>
                            <td>${serviceType.get(facility.facilityTypeId)}</td>
<%--                            <td><select name="facilityTypeId" onchange="openInput(this.value)">--%>
<%--                                <option value="${facility.facilityTypeId}">${serviceType.get(facility.facilityTypeId)}</option>--%>
<%--                                <option value="1">Villa</option>--%>
<%--                                <option value="2">House</option>--%>
<%--                                <option value="3">Room</option>--%>
<%--                            </select>--%>
<%--                            </td>--%>
                        </tr>
                        <tr>
                            <td>Kiểu thuê</td>
                            <td><select name="rentType">
                                <option value="${facility.rentTypeId}">${rentType.get(facility.rentTypeId)}</option>
                                <option value="1">year</option>
                                <option value="2">month</option>
                                <option value="3">day</option>
                                <option value="4">hour</option>
                            </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Diện tích</td>
                            <td><input type="text" name="area" id="area" value="${facility.area}"></td>
                        </tr>
                        <tr>
                            <td>Giá</td>
                            <td><input type="text" name="cost" id="cost" value="${facility.cost}"></td>
                        </tr>
                        <tr>
                            <td>Số người tối đa</td>
                            <td><input type="text" name="maxPeople" id="maxPeople" value="${facility.maxPeople}"></td>
                        </tr>
                        <tr>
                            <td>Tiêu chuẩn phòng</td>
                            <td><input type="text" name="standard" id="standard" value="${facility.standard}"></td>
                        </tr>
                        <tr>
                            <td>Mô tả</td>
                            <td><input type="text" name="description" id="description"  value="${facility.description}"></td>
                        </tr>
                        <tr>
                            <td>Diện tích hồ bơi</td>
                            <td><input type="text" name="poolArea" id="poolArea" value="${facility.poolArea}"></td>
                        </tr>
                        <tr>
                            <td>Số tầng</td>
                            <td><input type="text" name="floors"  id="floors" value="${facility.floors}"></td>
                        </tr>
                        <tr>
                            <td>Dịch vụ miễn phí</td>
                            <td><input type="text" name="facilityFree"  id="freeService" value="${facility.facilityFree}"></td>
                        </tr>
                        <tr>
                            <td><a href="/facilities" class="btn btn-primary" role="button">Quay lại Dịch vụ</a></td>
                            <td><input type="submit" value="Chỉnh sửa"></td>
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
<%--2 script bootstrap 5.1--%>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
<%--3 script phân trang--%>
<script src="jquery/jquery-3.5.1.min.js"></script>
<script src="datatables/js/jquery.dataTables.min.js"></script>
<script src="datatables/js/dataTables.bootstrap5.min.js"></script>
<script>
    let inputId = ["standard", "description", "poolArea", "floors", "freeService"];
    let facility = ["villa-standard-description-poolArea-floors", "house-standard-description-floors", "room-freeService"];

    function openInput(value) {
        for (let element of inputId) {
            document.getElementById(element).disabled = !facility[parseInt(value)-1].includes(element);
        }
    }
</script>
</html>