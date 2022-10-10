<%--
  Created by IntelliJ IDEA.
  User: Hoang Gia
  Date: 10/5/2022
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dịch vụ</title>
    <%--    Link bootstrap 5.1 có navbar dropdown--%>
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
<%--2 link phân trang--%>
<link rel="stylesheet" href="bootstrap520/css/bootstrap.min.css"/>
<link rel="stylesheet" href="datatables/css/dataTables.bootstrap5.min.css"/>

</head>
<body>
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
                <form class="d-flex m-0 p-0 align-items-center justify-content-end" style="flex: 1"
                      action="/customers?action=find" method="post">
                    <input type="search" placeholder="Tìm theo tên" aria-label="Search" name="name">
                    <select name="customerTypeId" class="mx-3">
                            <option value="0">Loại khách</option>
                            <option value="1">Diamond</option>
                            <option value="2">Platinium</option>
                            <option value="3">Gold</option>
                            <option value="4">Silver</option>
                            <option value="5">Member</option>
                    </select>
                    <input type="submit" value="Tìm kiếm" class="btn btn-outline-success">
                </form>
            </div>
        </nav>
    </div>
</div>

<%--Body: LeftSide and content--%>
<div class="container-fluid">
    <div class="row" style="width: 100%; padding: 0; margin: 0;">
        <div id="left" class="col-2">
            <ul id="ul_left" style="list-style-type: none; margin-left: -25px; margin-top: 50px">
                <li><a href="/customers?action=create" class="btn btn-primary" role="button">Thêm mới Khách hàng</a></li>
                <li style="margin: 20px"><button type="button"
                            class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#createModal">
                    Thêm mới Khách hàng (modal)
                </button></li>
                <%--                <li><a href="/faciliies?action=create" class="btn btn-primary" role="button">Thêm mới sản phẩm</a></li>--%>
                <%--                <li><a href="/faciliies?action=create" class="btn btn-primary" role="button">Thêm mới sản phẩm</a></li>--%>
            </ul>
        </div>
        <div id="content" class="col-10">
            <c:if test="${message!=null}">
                <div aria-live="polite" aria-atomic="true" class="position-relative">
                    <div class="toast fade show" style="margin: 20px auto" role="alert" aria-live="assertive"
                         aria-atomic="true">
                        <div class="toast-header">
                            <svg class="bd-placeholder-img rounded me-2" width="20" height="20"
                                 xmlns="http://www.w3.org/2000/svg"
                                 aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
                                <rect width="100%" height="100%" fill="#007aff"></rect>
                            </svg>
                            <strong class="me-auto"><span style="vertical-align: inherit;"><span
                                    style="vertical-align: inherit;">Thông báo:</span></span></strong>
                            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Đóng"></button>
                        </div>
                        <div class="toast-body"><span style="vertical-align: inherit;"><span
                                style="vertical-align: inherit;">
                   <strong> ${message}</strong> </span></span></div>
                    </div>
                </div>
            </c:if>
            <h1 style="text-align: center; color: blue">Danh sách KHÁCH HÀNG</h1>


            <table id="customer_table" class="table table-striped table-bordered" style="width: 100%">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên</th>
                    <th>CMND</th>
                    <th>Giới tính</th>
                    <th>SĐT</th>
                    <th>Email</th>
                    <th>Địa chỉ</th>
                    <th>Loại khách</th>
                    <th>Chỉnh sửa</th>
                    <th>Xóa</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="customer" items="${customers}" varStatus="status">
                    <tr>
                        <td>${status.count}<input type="hidden" name="id" value="${customer.id}"></td>
                            <%--                        <td><a href="/customers?action=view&id=${customer.id}">${customer.name}</a></td>--%>
                        <td>${customer.name}</td>
                        <td>${customer.idCard}</td>
                        <td>${gender.get(customer.gender)}</td>
                        <td>${customer.phoneNumber}</td>
                        <td>${customer.email}</td>
                        <td>${customer.address}</td>
                        <td>${customerType.get(customer.customerTypeId)}</td>
                        <td><!-- Button trigger modal -->
                            <button type="button"
                                    onclick="sendToEditModal(
                                            '${customer.id}',
                                            '${customer.name}',
                                            '${customer.birthday}',
                                            '${customer.gender}',
                                            '${customer.idCard}',
                                            '${customer.phoneNumber}',
                                            '${customer.email}',
                                            '${customer.address}',
                                            '${customer.customerTypeId}',
                                            '${gender.get(customer.gender)}',
                                            '${customerType.get(customer.customerTypeId)}',)"
                                    class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#updateModal">
                                Chỉnh sửa
                            </button>
                        </td>
                        <td>
                                <%--delete Modal--%>
                            <button type="button" onclick="sendToDeleteModal('${customer.id}','${customer.name}')"
                                    class="btn btn-danger" data-bs-toggle="modal"
                                    data-bs-target="#deleteModal">
                                Xóa
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- Create Modal -->
            <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel-create">Thêm mới khách hàng</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="/customers" method="post">
                                <input type="hidden" name="action" value="createByModal">
                                <fieldset>
                                    <legend>Nhập thông tin</legend>
                                    <table class="table table-striped table-bordered" style="width: 100%">
                                        <tr>
                                            <td>Tên</td>
                                            <td><input type="text" name="name"></td>
                                        </tr>
                                        <tr>
                                            <td>Ngày sinh</td>
                                            <td><input type="text" name="birthday"></td>
                                        </tr>
                                        <tr>
                                            <td>Giới tính</td>
                                            <td><select name="gender">
                                                <option value="0">Nam</option>
                                                <option value="1">Nữ</option>
                                            </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>CMND</td>
                                            <td><input type="text" name="idCard"></td>
                                        </tr>
                                        <tr>
                                            <td>SĐT</td>
                                            <td><input type="text" name="phoneNumber"></td>
                                        </tr>
                                        <tr>
                                            <td>Email</td>
                                            <td><input type="text" name="email"></td>
                                        </tr>
                                        <tr>
                                            <td>Địa chỉ</td>
                                            <td><input type="text" name="address"></td>
                                        </tr>
                                        <tr>
                                            <td>Loại khách</td>
                                            <td><select name="customerTypeId">
                                                <option value="1">Diamond</option>
                                                <option value="2">Platinium</option>
                                                <option value="3">Gold</option>
                                                <option value="4">Silver</option>
                                                <option value="5">Member</option>
                                            </select>
                                            </td>
                                        </tr>
                                    </table>
                                </fieldset>
                                <div class="modal-footer">
                                    <a href="/customers" class="btn btn-secondary" data-bs-dismiss="modal">Hủy bỏ</a>
                                    <input type="submit" value="Xác nhận" class="btn btn-primary">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Update Modal -->
            <div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel-update">Chỉnh sửa thông tin khách hàng</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="/customers" method="post">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="id" id="id">
                                <fieldset>
                                    <legend>Chỉnh sửa thông tin</legend>
                                    <table class="table table-striped table-bordered" style="width: 100%">
                                        <tr>
                                            <td>Tên</td>
                                            <td><input type="text" name="name" id="name"></td>
                                        </tr>
                                        <tr>
                                            <td>Ngày sinh</td>
                                            <td><input type="text" name="birthday" id="birthday"></td>
                                        </tr>
                                        <tr>
                                            <td>Giới tính</td>
                                            <td><select name="gender">
                                                <option id="gender"></option>
                                                <option value="0">Nam</option>
                                                <option value="1">Nữ</option>
                                            </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>CMND</td>
                                            <td><input type="text" name="idCard" id="idCard"></td>
                                        </tr>
                                        <tr>
                                            <td>SĐT</td>
                                            <td><input type="text" name="phoneNumber" id="phoneNumber"></td>
                                        </tr>
                                        <tr>
                                            <td>Email</td>
                                            <td><input type="text" name="email" id="email"></td>
                                        </tr>
                                        <tr>
                                            <td>Địa chỉ</td>
                                            <td><input type="text" name="address" id="address"></td>
                                        </tr>
                                        <tr>
                                            <td>Loại khách</td>
                                            <td><select name="customerTypeId">
                                                <option id="customerTypeId"></option>
                                                <option value="1">Diamond</option>
                                                <option value="2">Platinium</option>
                                                <option value="3">Gold</option>
                                                <option value="4">Silver</option>
                                                <option value="5">Member</option>
                                            </select>
                                            </td>
                                        </tr>
                                    </table>
                                </fieldset>
                                <div class="modal-footer">
                                    <a href="/customers" class="btn btn-secondary" data-bs-dismiss="modal">Hủy bỏ</a>
                                    <input type="submit" value="Xác nhận" class="btn btn-warning">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Delete Modal -->
            <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel-delete">Xóa Khách hàng</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Xóa khách hàng <strong id="nameDelete"></strong>?
                        </div>
                        <div class="modal-footer">
                            <a href="/customers" class="btn btn-secondary" data-bs-dismiss="modal">Hủy bỏ</a>
                            <form action="/customers" method="post">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" id="idDelete">
                                <input type="submit" value="Xác nhận" class="btn btn-danger">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--Footer--%>
<footer class="bd-footer py-1 mt-5 bg-light">
    <div class="container py-3">
        <div class="row">
            Cám ơn bạn đã sử dụng dịch vụ của chúng tôi
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
    $(document).ready(function () {
        $('#customer_table').dataTable({
            "dom": 'lrtip',
            "lengthChange": false,
            "pageLength": 5
        });
    });

    function sendToDeleteModal(id, name) {
        // document.getElementById("nameDelete").value = name;
        $("#nameDelete").text(name);
        // $("#idDelete").text(id);
        $("#idDelete").val(id);
        // document.getElementById("idDelete").value = id;
    }

    function sendToEditModal(id, name, birthday, gender, idCard, phoneNumber,
                             email, address, customerTypeId, genderName, customerTypeName) {
        $("#id").val(id);
        $("#name").val(name);
        $("#birthday").val(birthday);
        $("#gender").val(gender);
        $("#gender").text(genderName);
        $("#idCard").val(idCard);
        $("#phoneNumber").val(phoneNumber);
        $("#email").val(email);
        $("#address").val(address);
        $("#customerTypeId").val(customerTypeId);
        $("#customerTypeId").text(customerTypeName);
    }
</script>

</html>
