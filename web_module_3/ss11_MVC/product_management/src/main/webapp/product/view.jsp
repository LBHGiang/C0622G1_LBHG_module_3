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
</head>
<body>
<h1>Thông tin chi tiết sản phẩm</h1>
<p>
    <a href="/products">Quay lại Danh sách sản phẩm</a>
</p>
<table>
    <tr>
        <td>Tên:</td>
        <td>${requestScope["product"].getName()}</td>
    </tr>
    <tr>
        <td>Giá:</td>
        <td>${requestScope["product"].getPrice()}</td>
    </tr>
    <tr>
        <td>Mô tả:</td>
        <td>${requestScope["product"].getDescription()}</td>
    </tr>
    <tr>
        <td>Nhà sản xuất:</td>
        <td>${requestScope["product"].getProducer()}</td>
    </tr>
</table>
</body>
</html>
