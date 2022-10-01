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
</head>
<body>
<h1>Danh sách sản phẩm</h1>
<p>
    <a href="/products?action=create">Thêm mới sản phẩm</a>
    <a href="/products?action=find">Tìm kiếm sản phẩm</a>
</p>
<table border="1">
    <tr>
        <td>Tên</td>
        <td>Giá</td>
        <td>Mô tả</td>
        <td>Nhà sản xuất</td>
        <td>Chỉnh sửa</td>
        <td>Xóa</td>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td><a href="/products?action=view&id=${product.id}">${product.getName()}</a></td>
            <td>${product.getPrice()}</td>
            <td>${product.getDescription()}</td>
            <td>${product.getProducer()}</td>
            <td><a href="/products?action=edit&id=${product.getId()}">Chỉnh sửa</a></td>
            <td><a href="/products?action=delete&id=${product.getId()}">Xóa</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
