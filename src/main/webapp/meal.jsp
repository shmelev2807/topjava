<%--<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">--%>
    <meta charset="UTF-8">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title>Add/update meal</title>
</head>
<body>
<script>
    $(function() {
        $('input[name=meal]').datepicker();
    });
</script>

<form method="POST" action='meals' name="frmAddMeal">
    Meal ID : <input type="text" readonly="readonly" name="id"
        value="<c:out value="${meal.id}" />" /> <br />
    Date : <input type="text" name="dateTime"
        value="<c:out value="${meal.dateTime}" />" /> <br />
    Description : <input type="text" name="description"
        value="<c:out value="${meal.description}" />" /> <br />
    Calories : <input type="text" name="calories"
        value="<c:out value="${meal.calories}" />" /> <br />

    <input type="submit" value="Save" />
</form>
</body>
</html>