<%@ page import="ua.com.cinema1.dao.HallDao" %>
<%@ page import="ua.com.cinema1.model.Hall" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Mimino
  Date: 04.11.2017
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administration</title>
</head>

<style>
    #navbar {
        margin: 0;
        padding: 0;
        list-style-type: none;
        width: 200px;
    }

    #navbar li {
        border-bottom: 1px solid #666;
        border-left: 10px solid #666;
    }

    #navbar a {
        background-color: #949494;
        color: #fff;
        padding: 5px;
        text-decoration: none;
        font-weight: bold;
        border-left: 5px solid #33ADFF;
        display: block;
    }

    #sidebar {
        position: absolute;
        overflow: auto;
        padding: 10px;
        width: 200px;
        background: #ECF5E4;
        border-right: 1px solid #231F20;
        top: 0; /* Расстояние от верхнего края */
        bottom: 0; /* Расстояние снизу  */
    }

    #content {
        position: absolute;
        overflow: auto;
        padding: 10px;
        top: 0px; /* Расстояние от верхнего края */
        left: 250px; /* Расстояние от левого края */
        bottom: 0;
        right: 0;
    }

    input {
        width: 300px; /* Ширина поля */
        box-shadow: inset 0 1px 5px rgba(0, 0, 0, 0.2); /* Тень внутри */
        border: 1px solid #ccc; /* Параметры рамки */
        color: black; /* Цвет текста */
        padding-left: 10px; /* Расстояние от левого края */
    }

    label {
        width: 200px; /* Ширина */
        text-align: right; /* Выравниваем по правому краю */
        float: left; /* Выстраиваем элементы рядом */
        margin-right: 10px; /* Расстояние от текста до текстового поля */
        line-height: 30px; /* Выравниваем по высоте */
    }

    table input {
        font-size: 12px;
        width: 20px; /* Ширина */
        height: 24px;
        line-height: 24px;
        text-align: center; /* Выравниваем по правому краю */
        color: #33ADFF;
        background-color: #33ADFF;
        float: left; /* Выстраиваем элементы рядом */
        margin-right: 10px; /* Расстояние от текста до текстового поля */
    }

</style>

<body>
<div id=sidebar>
    <ul id="navbar">
        <li><a href="/admin/users">Users</a></li>
        <li><a href="/admin/admins">Admins</a></li>
        <li><a href="/admin/films">Films</a></li>
        <ul>
            <li><a href="/admin/films/genres">Genres</a></li>
            <li><a href="/admin/films/studios">Studios</a></li>
            <li><a href="/admin/films/countries">Countries</a></li>
            <li><a href="/admin/films/actors">Actors</a></li>
            <li><a href="/admin/films/directors">Directors</a></li>
        </ul>
        <li><a href="/admin/halls">Halls</a></li>
        <li><a href="/admin/seances">Seances</a></li>
        <li><a href="/">To cinema</a></li>
    </ul>
</div>
<div id="content">
    <%
        HallDao hallDao = HallDao.getInstance();
        Hall hall = hallDao.getById(Integer.parseInt(request.getParameter("id")));
    %>
    <p><label for="name">Name:</label>
        <input type="text" name="name" id="name" value="<%=hall.getName()%>" readonly></p>
    <p><label for="width">Width:</label>
        <input type="number" name="width" id="width" value="<%=hall.getWidth()%>" readonly></p>
    <p><label for="height">Height:</label>
        <input type="number" name="height" id="height" value="<%=hall.getHeight()%>" readonly></p>

    <table>
        <%
            for (int i = 0; i < hall.getHeight(); i++) {
        %>
        <tr><%
            for (int j = 0; j < hall.getWidth(); j++) {
                if (hall.getPlacement()[i][j]) {
        %>
            <td><input type="button"></td>
            <%
            } else {
            %>
            <td></td>
            <%
                    }
                }
            %></tr>
        <%
            }
        %>
    </table>
    <form action="/admin/hall/update?id=<%=hall.getId()%>" method="post">
        <button type="submit">Update</button>
    </form>
</div>

</body>
</html>