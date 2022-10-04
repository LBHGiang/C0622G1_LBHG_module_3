<%--
  Created by IntelliJ IDEA.
  User: Hoang Gia
  Date: 9/27/2022
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Danh sách sản phẩm</title>
    <%--    bootstrap v5.0--%>
    <link rel="stylesheet" href="bootstrap520/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="datatables/css/dataTables.bootstrap5.min.css"/>
</head>
<body>
<c:if test="${message!=null}">
    <div aria-live="polite" aria-atomic="true" class="position-relative">
        <div class="toast fade show" style="margin: 20px auto" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <svg class="bd-placeholder-img rounded me-2" width="20" height="20" xmlns="http://www.w3.org/2000/svg"
                     aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <rect width="100%" height="100%" fill="#007aff"></rect>
                </svg>
                <strong class="me-auto"><span style="vertical-align: inherit;"><span style="vertical-align: inherit;">Thông báo:</span></span></strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Đóng"></button>
            </div>
            <div class="toast-body"><span style="vertical-align: inherit;"><span style="vertical-align: inherit;">
                   <strong> ${message}</strong> </span></span></div>
        </div>
    </div>
</c:if>
<h1 style="text-align: center; color: blue">Danh sách sản phẩm</h1>

<form action="/products?action=find" method="post">
    <a href="/products?action=create" class="btn btn-primary" role="button">Thêm mới sản phẩm</a>
    <input type="submit" value="Tìm kiếm" class="btn btn-primary">
    <input type="text" name="name" id="searchName" placeholder="Nhập thông tin cần tìm kiếm" size="30">
</form>

<table id="product_table" class="table table-striped table-bordered" style="width: 100%">
    <thead>
    <tr>
        <th>STT</th>
        <th>ID</th>
        <th>Tên</th>
        <th>Giá</th>
        <th>Mô tả</th>
        <th>Nhà sản xuất</th>
        <th>Chỉnh sửa</th>
        <th>Xóa</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${product.getId()}</td>
            <td><a href="/products?action=view&id=${product.id}">${product.getName()}</a></td>
            <td>${product.getPrice()}</td>
            <td>${product.getDescription()}</td>
            <td>${product.getProducer()}</td>

            <td><!-- Button trigger modal -->
                <button type="button" class="btn btn-warning" data-bs-toggle="modal"
                        data-bs-target="#update${product.getId()}" >
                    Chỉnh sửa
                </button>

                <!-- Modal -->
                <div class="modal fade" id="update${product.getId()}" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel-update">Chỉnh sửa sản phẩm</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Chỉnh sửa sản phẩm <strong>${product.getName()}</strong>:
                                <form action="/products?action=edit&id=${product.id}" method="post">
                                    <fieldset>
                                        <legend>Thông tin sản phẩm</legend>
                                        <table class="table table-striped table-bordered" style="width: 100%">
                                            <tr>
                                                <td>Tên:</td>
                                                <td><input type="text" name="name" id="name"
                                                           value="${product.getName()}"></td>
                                            </tr>
                                            <tr>
                                                <td>Giá:</td>
                                                <td><input type="number" name="price" id="price"
                                                           value="${product.getPrice()}"></td>
                                            </tr>
                                            <tr>
                                                <td>Mô tả:</td>
                                                <td><input type="text" name="description" id="description"
                                                           value="${product.getDescription()}"></td>
                                            </tr>
                                            <tr>
                                                <td>Nhà sản xuất:</td>
                                                <td><input type="text" name="producer" id="producer"
                                                           value="${product.getProducer()}"></td>
                                            </tr>
                                        </table>
                                    </fieldset>
                                    <div class="modal-footer">
                                        <a href="/products" class="btn btn-secondary" data-bs-dismiss="modal">Hủy bỏ</a>
                                        <input type="submit" value="Xác nhận" class="btn btn-warning">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </td>

            <td><!-- Button trigger modal -->
                <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                        data-bs-target="#delete${product.getId()}">
                    Xóa
                </button>

                <!-- Modal -->
                <div class="modal fade" id="delete${product.getId()}" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel-delete">Xóa Sản Phẩm</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Xóa sản phẩm <strong>${product.getName()}</strong>?
                            </div>
                            <div class="modal-footer">
                                <a href="/products" class="btn btn-secondary" data-bs-dismiss="modal">Hủy bỏ</a>
                                <form action="/products?action=delete&id=${product.id}" method="post">
                                    <input type="submit" value="Xác nhận" class="btn btn-danger">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script src="jquery/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
<script src="datatables/js/jquery.dataTables.min.js"></script>
<script src="datatables/js/dataTables.bootstrap5.min.js"></script>
<script>
    function doSth(){
        alert(document.getElementById("price").textContent);
    }
    $(document).ready(function () {
        $('#product_table').dataTable({
            "dom": 'lrtip',
            "lengthChange": false,
            "pageLength": 5
        });
    });
</script>
</body>
</html>
