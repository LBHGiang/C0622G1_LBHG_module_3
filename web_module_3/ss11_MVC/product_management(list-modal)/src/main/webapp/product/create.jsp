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
    <title>Tạo mới sản phẩm</title>
    <link rel="stylesheet" href="bootstrap520/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="datatables/css/dataTables.bootstrap5.min.css"/>
    <style>
        .message {
            color: green;
        }
    </style>
</head>
<body>
<h1 style="text-align: center; color: blue">Tạo mới sản phẩm</h1>
<p>
    <c:if test="${message != null}">
        <span class="message">${message}</span>
    </c:if>
</p>
<p>
    <a href="/products" class="btn btn-primary">Quay lại Danh sách sản phẩm</a>
</p>
<form method="post">
    <fieldset>
        <legend style="color: blue">Thông tin sản phẩm</legend>
        <table class="table table-striped">
            <tr>
                <td>Tên:</td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>Giá:</td>
                <td><input type="number" name="price" id="price"></td>
            </tr>
            <tr>
                <td>Mô tả:</td>
                <td><input type="text" name="description" id="description"></td>
            </tr>
            <tr>
                <td>Nhà sản xuất:</td>
                <td><input type="text" name="producer" id="producer"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create product" class="btn btn-success"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>