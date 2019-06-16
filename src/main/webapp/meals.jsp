<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title>Meals</title>
    <style>
        .normal {color: green;}
        .excess {color: red;}
    </style>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <h3>Meal List</h3>
    <a href="meals?action=create">Add Meal</a>
    <hr>
    <table border="1" cellpadding="6" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach var="mealTo" items="${listMealTo}">
            <%--<jsp:useBean id="mealTo" scope="page" type="ru.javawebinar.topjava.model.MealTo" />--%>
            <fmt:parseDate value="${mealTo.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" var="parsedDate" />
            <tr class="${mealTo.excess ? 'excess' : 'normal'}">
                <td>${parsedDate}</td>
                <td>${mealTo.description}</td>
                <td>${mealTo.calories}</td>
                <td><a href="meals?action=update&id=${mealTo.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${mealTo.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
