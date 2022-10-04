<%--
  Created by IntelliJ IDEA.
  User: Hoang Gia
  Date: 9/27/2022
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thông tin sản phẩm</title>
    <link rel="stylesheet" href="bootstrap520/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="datatables/css/dataTables.bootstrap5.min.css"/>
</head>
<body>
<h1 style="text-align: center; color: blue">Thông tin chi tiết sản phẩm</h1>
<p>
   <a href="/products" class="btn btn-primary" style="margin-left: 25%; margin-top: 5%">Quay lại Danh sách sản phẩm</a>
</p>
<table class="table table-striped" style="width: 50%; margin-left: 25%">
    <tr>
        <td>Tên:</td>
        <td>${product.getName()}</td>
    </tr>
    <tr>
        <td>Giá:</td>
        <td>${product.getPrice()}</td>
    </tr>
    <tr>
        <td>Mô tả:</td>
        <td>${product.getDescription()}</td>
    </tr>
    <tr>
        <td>Nhà sản xuất:</td>
        <td>${product.getProducer()}</td>
    </tr>
</table>
</body>
</html>
