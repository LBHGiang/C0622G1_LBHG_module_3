<%--
  Created by IntelliJ IDEA.
  User: Hoang Gia
  Date: 9/25/2022
  Time: 1:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Discount Calculator</title>
</head>
<body>
<h1>Product Discount Calculator</h1>
<form action="/calculator" method="post">
    <label>Product Description: </label><br/>
    <input type="text" name="description" placeholder="Product Description"/><br/>
    <label>List Price: </label><br/>
    <input type="text" name="price" placeholder="USD" value="0"/>USD<br/>
    <label>Discount Percent: </label><br/>
    <input type="text" name="discount" placeholder="%" value="0"/>%<br/>
    <input type="submit" id="submit" value="Calculator"/>
</form>

</body>
</html>
