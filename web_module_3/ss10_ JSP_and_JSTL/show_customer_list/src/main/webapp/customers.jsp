<%--
  Created by IntelliJ IDEA.
  User: Hoang Gia
  Date: 9/29/2022
  Time: 9:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        h1 {
            text-align: center
        }

        img {
            width: 50px;
            height: 70px
        }
    </style>
</head>
<body>
<h1>Customer List</h1>
<table class="table table-striped">
    <tr>
        <th>Tên</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <th>Ảnh</th>
    </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td>${customer.getName()}</td>
            <td>${customer.getBirthDay()}</td>
            <td>${customer.getAddress()}</td>
            <td><img src="${customer.getImgLink()}"/></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
