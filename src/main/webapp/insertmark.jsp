<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>Insert a new student</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div>Insert MARK</div>

<form action="insertservlet" method="POST">
    <table>
        <tbody>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" size="20"/></td>
        </tr>
        <tr>
            <td>Teacher:</td>

            <td>
                <c:forEach items="${teachers}" var="teacher">
                    teacher
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>Credits:</td>
            <td><input type="text" name="credits" size="4"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="submit" value="INSERT SUBJECT"/></td>
        </tr>
        </tbody>
    </table>
</form>

</body>
</html>
