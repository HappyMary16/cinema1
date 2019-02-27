<%@ page import="ua.com.cinema1.dao.HallDao" %>
<%@ page import="ua.com.cinema1.model.Hall" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 16.02.2019
  Time: 12:24
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

    table {
        left: 250px;
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
    <h3>Halls:</h3>

    <form action="/admin/halls/new">
        <button type="submit">New hall</button>
    </form>
    <table>
        <tr>
            <th class="title">№</th>
            <th class="title">Name</th>
            <th class="title">Places number</th>
        </tr>
        <%
            int i = 0;
            for (Hall hall :
                    HallDao.getInstance().getAll()) {
                i++;
        %>
        <tr>
            <td class="text"><a href="/admin/hall?id=<%= hall.getId() %>"><%= i%>
            </a>
            </td>
            <td class="text"><a href="/admin/hall?id=<%= hall.getId() %>"><%= hall.getName()%>
            </a>
            </td>
            <td class="text"><a href="/admin/hall?id=<%= hall.getId() %>"><%=hall.getPlaces().size()%>
            </a>
            </td>

            <td class="list"><a href="/admin/hall/delete?id=<%= hall.getId()%>">Delete</a></td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
