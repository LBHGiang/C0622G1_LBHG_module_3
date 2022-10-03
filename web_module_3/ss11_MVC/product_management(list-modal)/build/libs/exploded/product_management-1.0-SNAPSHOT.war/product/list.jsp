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
<h1 style="text-align: center; color: blue">Danh sách sản phẩm</h1>
<p>
    <a href="/products?action=create" class="btn btn-primary" role="button">Thêm mới sản phẩm</a>
    <a href="/products?action=find" class="btn btn-primary">Tìm kiếm sản phẩm</a>
</p>
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
            <td><a href="/products?action=edit&id=${product.getId()}" class="btn btn-warning">Chỉnh sửa</a></td>
            <td>
                <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                        data-bs-target="#deleteModal${product.getId()}">
                    Xóa
                </button>
                <!-- Modal -->
                <div class="modal fade" id="deleteModal${product.getId()}" tabindex="-1"
                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Xóa sản phẩm</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                ...
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy bỏ</button>
                                <button type="button" class="btn btn-danger">Xóa</button>
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
<script src="datatables/js/jquery.dataTables.min.js"></script>
<script src="datatables/js/dataTables.bootstrap5.min.js"></script>
<script>
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
