<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<hr>
<h1>Meals</h1>
<h3><a href="meal.jsp">Add meal</a></h3>
<table border=1>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th> </th>
        <th> </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">

            <c:set var="excess" value="${meal.excess}" />
            <c:set var="color" value="green" />
            <c:if test="${excess==true}">
                    <c:set var="color" value="red" />
            </c:if>

            <td><font color="${color}"><c:out value="${meal.dateTime}" /></font ></td>
            <td><font color="${color}"><c:out value="${meal.description}" /></font ></td>
            <td><font color="${color}"><c:out value="${meal.calories}" /></font ></td>
            <td><a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<hr>
</body>
</html>

