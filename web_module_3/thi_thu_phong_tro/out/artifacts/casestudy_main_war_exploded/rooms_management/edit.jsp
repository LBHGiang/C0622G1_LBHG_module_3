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
    <style>
        body {
            background: url("../image/bg_img.jpg") no-repeat center/cover;
            height: 100vh;
            width: 100vw;
        }
    </style>

</head>
<body onload="openInput(${facility.facilityTypeId})">
<%--Header--%>
<div class="header-content">
    <div class="container">
        <div class="row">
            <div class="col-3 align-items-center bestel-logo">
                <a href="/furama_resort/home.jsp" class="header-logo"><img
                        src="/image/logo_img.png" alt="logo "
                        class="img-fluid" style="margin: 20px"></a>
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
                        <a href="/employees" class="nav-link main-menu-link">EMPLOYEE</a>
                    </li>
                    <li id="nav-menu-item-5008"
                        class="nav-item  menu-item-even menu-item-depth-0 menu-item menu-item-type-post_type menu-item-object-page">
                        <a href="/customers" class="nav-link main-menu-link">CUSTOMER</a>
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
                        <a href="/contracts"
                           class="nav-link main-menu-link">CONTRACT</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>

<div class="container-fluid">
    <div class="row" style="width: 100%; padding: 0; margin: 0;">
        <div id="left" class="col-2">
            <ul id="ul_left" style="list-style-type: none; margin-left: -25px; margin-top: 50px">
                <li style="margin: 10px"><a href="/employees?" class="btn btn-primary" role="button">Quay lại Nhân
                    viên</a></li>
                <li style="margin: 10px"><a href="furama_resort/home.jsp" class="btn btn-primary" role="button">Quay lại
                    Trang chủ</a></li>
            </ul>
        </div>
        <div id="content" class="col-10">
            <h1 style="text-align: center; color: blue">Chỉnh sửa thông tin nhân viên</h1>
            <form method="post">
                <fieldset style="width: 50%; margin-left: 25%">
                    <legend>Sửa thông tin</legend>
                    <table class="table table-striped" style="text-align: left">
                        <tr>
                            <td>Tên:</td>
                            <td><input type="text" name="name" id="name" value="${employee.name}"></td>
                        </tr>
                        <tr>
                            <td>Ngày sinh:</td>
                            <td><input type="text" name="birthday" id="birthday" value="${employee.birthday}"></td>
                        </tr>
                        <tr>
                            <td>CMND:</td>
                            <td><input type="text" name="idCard" id="idCard" value="${employee.idCard}"></td>
                        </tr>
                        <tr>
                            <td>Salary:</td>
                            <td><input type="text" name="salary" id="salary" value="${employee.salary}"></td>
                        </tr>
                        <tr>
                            <td>SĐT:</td>
                            <td><input type="text" name="phoneNumber" id="phoneNumber" value="${employee.phoneNumber}">
                            </td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><input type="text" name="email" id="email" value="${employee.email}"></td>
                        </tr>
                        <tr>
                            <td>Địa chỉ:</td>
                            <td><input type="text" name="address" id="address" value="${employee.address}"></td>
                        </tr>
                        <tr>
                            <td>Chức vụ</td>
                            <td><select name="positionId">
                                <option value="${employee.positionId}">${position.get(employee.positionId)}</option>
                                <option value="1">Lễ tân</option>
                                <option value="2">Phục vụ</option>
                                <option value="3">Chuyên viên</option>
                                <option value="4">Giám sát</option>
                                <option value="5">Quản lý</option>
                                <option value="6">Giám đốc</option>
                            </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Trình độ</td>
                            <td><select name="educationDegreeId">
                                <option value="${employee.educationDegreeId}">${education.get(employee.educationDegreeId)}</option>
                                <option value="1">Trung cấp</option>
                                <option value="2">Cao đẳng</option>
                                <option value="3">Đại học</option>
                                <option value="4">Sau đại học</option>
                            </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Bộ phận</td>
                            <td><select name="divisionId">
                                <option value="${employee.divisionId}">${division.get(employee.divisionId)}</option>
                                <option value="1">Sale-Marketing</option>
                                <option value="2">Hành chính</option>
                                <option value="3">Phục vụ</option>
                                <option value="4">Quản lý</option>
                            </select>
                            </td>
                        </tr>
                        <tr>
                            <td><a href="/employees" class="btn btn-primary" role="button">Hủy bỏ</a></td>
                            <td><input type="submit" value="Chỉnh sửa"></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div>
    </div>
</div>

<footer class="bd-footer py-1 mt-5">
    <h3 style="text-align: center">Cám ơn bạn đã sử dụng dịch vụ của chúng tôi</h3>
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
</html>