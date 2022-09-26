<%--
  Created by IntelliJ IDEA.
  User: Hoang Gia
  Date: 9/24/2022
  Time: 9:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Converter</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>Currency Converter</h1>
<form action="/convert" method="get">
    <label>Rate: </label><br/>
    <input type="text" name="rate" placeholder="RATE" value="22000"/><br/>
    <label>USD: </label><br/>
    <input type="text" name="usd" placeholder="USD" value="0"/><br/>
    <input type="submit" id="submit" value="Converter"/>
</form>

</body>
</html>
