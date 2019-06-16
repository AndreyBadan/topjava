<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <h3>Edit List</h3>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request" />
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">
        DateTime: <br>
        <input type="datetime-local" value="${meal.dateTime}" name="dateTime"> <br>
        Description: <br>
        <input type="text" value="${meal.description}" name="description"> <br>
        Calories: <br>
        <input type="number" value="${meal.calories}" name="calories"> <br>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>

</body>
</html>
