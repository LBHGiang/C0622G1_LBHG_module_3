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
    <title>Danh sách phòng trọ</title>
    <%--    Link bootstrap 5.1 có navbar dropdown--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        body {
            background: url("../image/bg_img.jpg") no-repeat center/cover;
            height: 100vh;
            width: 100vw;
            margin-bottom: 0;
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
                <a href="" class="header-logo"><img
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
                <ul id="menu-room-vi" class="menu navbar-nav w-100 js-main-menu">
                    <li id="nav-menu-item-5006"
                        class="nav-item  menu-item-even menu-item-depth-0 menu-item menu-item-type-post_type menu-item-object-page">
                        <a href="/rooms?action=create" class="btn btn-primary" role="button">Thêm Mới</a></li>
                    <%--                    <li id="nav-menu-item-5007"--%>
                    <%--                        class="nav-item  menu-item-even menu-item-depth-0 menu-item menu-item-type-post_type menu-item-object-page">--%>
                    <%--                        <a href="/employees" class="nav-link main-menu-link">EMPLOYEE</a>--%>
                    <%--                    </li>--%>
                    <%--                    <li id="nav-menu-item-5008"--%>
                    <%--                        class="nav-item  menu-item-even menu-item-depth-0 menu-item menu-item-type-post_type menu-item-object-page">--%>
                    <%--                        <a href="/customers" class="nav-link main-menu-link">CUSTOMER</a>--%>
                    <%--                    </li>--%>
                    <%--                    <li class="nav-item dropdown">--%>
                    <%--                        <a class="nav-link dropdown-toggle" href="/facilities" id="navbarDropdown" role="button"--%>
                    <%--                           data-bs-toggle="dropdown" aria-expanded="false">--%>
                    <%--                            SERVICE--%>
                    <%--                        </a>--%>
                    <%--                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">--%>
                    <%--                            <li><a class="dropdown-item" href="/facilities">All Facilities</a></li>--%>
                    <%--                            <li>--%>
                    <%--                                <hr class="dropdown-divider">--%>
                    <%--                            </li>--%>
                    <%--                            <li><a class="dropdown-item" href="/facilities?action=villa">VILLA</a></li>--%>
                    <%--                            <li><a class="dropdown-item" href="/facilities?action=house">HOUSE</a></li>--%>
                    <%--                            <li><a class="dropdown-item" href="/facilities?action=room">ROOM</a></li>--%>
                    <%--                        </ul>--%>
                    <%--                    </li>--%>
                    <%--                    <li id="nav-menu-item-5010"--%>
                    <%--                        class="nav-item  menu-item-even menu-item-depth-0 menu-item menu-item-type-post_type menu-item-object-page">--%>
                    <%--                        <a href="/contracts"--%>
                    <%--                           class="nav-link main-menu-link">CONTRACT</a>--%>
                    <%--                    </li>--%>
                </ul>
                <form class="d-flex m-0 p-0 align-items-center justify-content-end" style="flex: 1"
                      action="/rooms?action=find" method="post">
                    <input type="search" placeholder="Tìm theo id" aria-label="Search" name="id">
                    <input type="search" placeholder="Tìm theo tên" aria-label="Search" name="name">
                    <input type="search" placeholder="Tìm theo sđt" aria-label="Search" name="phoneNumber">
                    <%--                    <select name="divisionId" class="mx-3">--%>
                    <%--                        <option value="0">Tất cả bộ phận</option>--%>
                    <%--                        <option value="1">Sale-Marketing</option>--%>
                    <%--                        <option value="2">Hành chính</option>--%>
                    <%--                        <option value="3">Phục vụ</option>--%>
                    <%--                        <option value="4">Quản lý</option>--%>
                    <%--                    </select>--%>
                    <input type="submit" value="Tìm kiếm" class="btn btn-outline-success">
                </form>
            </div>
        </nav>
    </div>
</div>

<div class="container-fluid">
    <div class="row" style="width: 100%; padding: 0; margin: 0;">
        <div id="content" class="col-12">
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

            <h1 style="text-align: center; color: blue">Danh sách Phòng trọ</h1>
            <table id="employee_table" class="table table-striped table-bordered" style="width: 100%">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Mã Phòng Trọ</th>
                    <th>Tên người thuê</th>
                    <%--                    <th>CMND</th>--%>
                    <%--                    <th>Lương</th>--%>
                    <th>SĐT</th>
                    <th>Ngày thuê</th>
                    <th>Hình thức thanh toán</th>
                    <th>Ghi chú</th>
                    <%--                    <th>Chỉnh sửa</th>--%>
                    <th>Xóa</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="room" items="${rooms}" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                            <%--                        <input type="hidden" name="id" value="${room.id}">--%>
                            <%--                        <td><a href="/rooms?action=view&id=${room.id}">${room.name}</a></td>--%>
                            <%--                        <td>--%>
                            <%--                            <button type="button"--%>
                            <%--                                    onclick="sendToViewModal(--%>
                            <%--                                            '${room.name}',--%>
                            <%--                                            '${room.birthday}',--%>
                            <%--                                            '${room.idCard}',--%>
                            <%--                                            '${room.salary}',--%>
                            <%--                                            '${room.phoneNumber}',--%>
                            <%--                                            '${room.email}',--%>
                            <%--                                            '${room.address}',--%>
                            <%--                                            '${position.get(room.positionId)}',--%>
                            <%--                                            '${education.get(room.educationDegreeId)}',--%>
                            <%--                                            '${division.get(room.divisionId)}')"--%>
                            <%--                                    class="" style="border: none; background: transparent; outline: none" data-bs-toggle="modal"--%>
                            <%--                                    data-bs-target="#viewModal">--%>
                            <%--                                <span class="text-decoration-underline">${room.name}</span>--%>
                            <%--                            </button>--%>
                            <%--                        </td>--%>

                        <td>${room.id}</td>
                            <%--                        <td>${room.idCard}</td>--%>
                        <td>${room.name}</td>
                        <td>${room.phoneNumber}</td>
                        <td>${room.rentDay}</td>
                        <td>${rentType.get(room.payMethod)}</td>
                        <td>${room.description}</td>

                            <%--                        <td>${division.get(room.divisionId)}</td>--%>
                            <%--                        <td><a href="/rooms?action=edit&id=${room.id}" class="btn btn-warning">Chỉnh sửa</a>--%>
                            <%--                        </td>--%>
                        <td>
<%--                            delete Modal--%>
                            <button type="button"
                                    onclick="sendToDeleteModal2('${room.id}','${room.name}')"
                                    class="btn btn-danger" data-bs-toggle="modal"
                                    data-bs-target="#deleteModal">
                                Xóa
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- Delete Modal -->
            <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel-delete">Xóa Phòng trọ</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Xóa Phòng trọ: Mã số <strong id="idDeleteShow"></strong>?
<%--                        <strong id="nameDelete"></strong>?--%>
                        </div>
                        <div class="modal-footer">
                            <a href="/rooms" class="btn btn-secondary" data-bs-dismiss="modal">Hủy bỏ</a>
                            <form action="/rooms" method="post">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" id="idDelete">
                                <input type="submit" value="Xác nhận" class="btn btn-danger">
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- View Modal -->
            <div class="modal fade" id="viewModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel-view">Thông tin chi tiết</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <fieldset>
                                <table class="table table-striped table-bordered" style="width: 100%">
                                    <tr>
                                        <td>Tên</td>
                                        <td><p id="nameView"></p></td>
                                    </tr>
                                    <tr>
                                        <td>Ngày sinh</td>
                                        <td><p id="birthdayView"></p></td>
                                    </tr>
                                    <tr>
                                        <td>CMND</td>
                                        <td><p id="idCardView"></p></td>
                                    </tr>
                                    <tr>
                                        <td>Lương</td>
                                        <td><p id="salaryView"></p></td>
                                    </tr>
                                    <tr>
                                        <td>SĐT</td>
                                        <td><p id="phoneNumberView"></p></td>
                                    </tr>
                                    <tr>
                                        <td>Email</td>
                                        <td><p id="emailView"></p></td>
                                    </tr>
                                    <tr>
                                        <td>Địa chỉ</td>
                                        <td><p id="addressView"></p></td>
                                    </tr>
                                    <tr>
                                        <td>Vị trí</td>
                                        <td><p id="positionView"></p></td>
                                    </tr>
                                    <tr>
                                        <td>Trình độ học vấn</td>
                                        <td><p id="educationView"></p></td>
                                    </tr>
                                    <tr>
                                        <td>Bộ phận</td>
                                        <td><p id="divisionView"></p></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <div class="modal-footer">
                                                <a href="/employees" class="btn btn-secondary" data-bs-dismiss="modal">Quay
                                                    lại</a>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--Footer--%>
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
<script>
    $(document).ready(function () {
        $('#employee_table').dataTable({
            "dom": 'lrtip',
            "lengthChange": false,
            "pageLength": 5
        });
    });

    function sendToDeleteModal(id, name) {
        $("#nameDelete").text(name);
        $("#idDelete").val(id);
    }

    function sendToDeleteModal2(id, name) {
        $("#idDeleteShow").text(id);
        $("#idDelete").val(id);
    }

    function sendToEditModal(name, price, description, producer) {
        document.getElementById("nameEdit").value = name;
        document.getElementById("idEdit").value = id;
        document.getElementById("priceEdit").value = price;
        document.getElementById("descriptionEdit").value = description;
        document.getElementById("producerEdit").value = producer;
    }

    function sendToViewModal(name, birthday, idCard, salary, phoneNumber, email, address, positionName, educationDegreeName, divisionName) {
        document.getElementById("nameView").innerHTML = name;
        document.getElementById("birthdayView").innerHTML = birthday;
        document.getElementById("idCardView").innerHTML = idCard;
        document.getElementById("salaryView").innerHTML = salary;
        document.getElementById("phoneNumberView").innerHTML = phoneNumber;
        document.getElementById("emailView").innerHTML = email;
        document.getElementById("addressView").innerHTML = address;
        document.getElementById("positionView").innerHTML = positionName;
        document.getElementById("educationView").innerHTML = educationDegreeName;
        document.getElementById("divisionView").innerHTML = divisionName;
    }
</script>

</html>
