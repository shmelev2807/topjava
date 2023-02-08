<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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
<h3>Add meal</h3>
<table border ="1">
    <tr>
        <th><b>Date</b></th>
        <th><b>Description</b></th>
        <th><b>Calories</b></th>
    </tr>
    <%
        ArrayList<MealTo> meals =
            (ArrayList<MealTo>)request.getAttribute("meals");
        for(MealTo m:meals){
            if(m.isExcess()) {
                request.setAttribute("color", "red");
            } else {
                request.setAttribute("color", "green");
            }
        %>
        <td><font color="${color}"><%=m.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))%></font></td>
        <td><font color="${color}"><%=m.getDescription()%></font></td>
        <td><font color="${color}"><%=m.getCalories()%></font></td>
    </tr>
    <%}%>
</table>
<hr>

</body>
</html>

