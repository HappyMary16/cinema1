<%@ page import="ua.com.cinema1.model.Seance" %>
<%@ page import="ua.com.cinema1.service.SeanceService" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 16.02.2019
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    #navbar {
        margin: 0;
        padding: 0;
        list-style-type: none;
        width: 200px;
    }

    #navbar li {
        border-left: 10px solid #666;
        border-bottom: 1px solid #666;
    }

    #navbar a {
        background-color: #33ADFF;
        color: #fff;
        padding: 5px;
        text-decoration: none;
        font-weight: bold;
        display: block;
    }

    body {
        margin: 0;
    }

    #sidebar, #content {
        position: absolute;
    }

    #sidebar, #content {
        overflow: auto;
        padding: 10px;
    }
    #sidebar {
        width: 200px;
        background: #ffffff;
        border-right: 1px solid #231F20;
        top: 0px; /* Расстояние от верхнего края */
        bottom: 0; /* Расстояние снизу  */
    }
    #content {
        top: 0px; /* Расстояние от верхнего края */
        left: 220px; /* Расстояние от левого края */
        bottom: 0; right: 0;
    }

    label {
        width: 200px; /* Ширина */
        text-align: right; /* Выравниваем по правому краю */
        float: left; /* Выстраиваем элементы рядом */
        margin-right: 10px; /* Расстояние от текста до текстового поля */
        line-height: 30px; /* Выравниваем по высоте */
    }

    table {
        left: 220px;
        font-family: arial, sans-serif;
        border-collapse: collapse;
    }

    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #dddddd;
    }
</style>

<body>
<div id=sidebar>
    <ul id="navbar">
        <li><a href="/admin/users">Пользователи</a></li>
        <li><a href="/admin/admins">Администраторы</a></li>
        <li><a href="/admin/films">Фильмы</a></li>
        <li><a href="/admin/halls">Залы</a></li>
        <li><a href="/admin/seances">Сеансы</a></li>
        <li><a href="/">Просмотр кинотеатра</a></li>
    </ul>
</div>


<div id="content">
    <table>
        <th>
            <form action="/admin/seances/new">
                <button type="submit">New seance</button>
            </form>
        </th>
        <tr>
            <th class="title">№</th>
            <th class="title">Film</th>
            <th class="title">Date</th>
            <th class="title">Time</th>
            <th class="title">Hall</th>
            <th class="title">Price</th>
        </tr>
        <%
            int i = 0;
            for (Seance seance :
                    SeanceService.getInstance().getAll()) {
                i++;
        %>
        <tr>
            <td class="text"><a href="/admin/seance?id=<%= seance.getId() %>"><%= i%></a>
            </td>
            <td class="text"><a href="/admin/film?id=<%= seance.getFilm().getId() %>"><%= seance.getFilm().getTitle()%></a>
            </td>
            <td class="text"><a href="/admin/seance?id=<%= seance.getId() %>"><%=new SimpleDateFormat("yyyy-MM-dd").format((seance.getDateAdnTime()))%></a>
            </td>
            <td class="text"><a href="/admin/seance?id=<%= seance.getId() %>"><%=new SimpleDateFormat("HH:mm").format((seance.getDateAdnTime()))%></a>
            </td>
            <td class="text"><a href="/admin/hall?id=<%= seance.getHall().getId() %>"><%= seance.getHall().getName() %></a>
            </td>
            <td class="text"><a href="/admin/seance?id=<%= seance.getId() %>"><%= seance.getPriceTicket() %></a>
            </td>
            <td class="list"><a href="/admin/seance/delete?id=<%= seance.getId()%>">Delete</a></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>