<%--
  Created by IntelliJ IDEA.
  User: Hoang Gia
  Date: 9/29/2022
  Time: 2:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Simple Calculator</title>
</head>
<body>
<h1>Simple Calculator</h1>
<form action="/calculators" method="post">
    <fieldset>
        <legend>Calculator</legend>
        <table>
            <tr>
                <td>First operand</td>
                <td><input type="number" name="operand1" value="0"></td>
            </tr>
            <tr>
                <td>Operator</td>
                <td><select name="operator">
                    <option value="+">+</option>
                    <option value="-">-</option>
                    <option value="*">*</option>
                    <option value="/">/</option>
                </select></td>
            </tr>
            <tr>
                <td>Second operand</td>
                <td><input type="number" name="operand2" value="0"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Calculator"></td>
            </tr>
        </table>


    </fieldset>
</form>
<h2>${message}</h2>
</body>
</html>
