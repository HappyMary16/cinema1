<%@ page import="ua.com.cinema1.model.User" %>
<%@ page import="ua.com.cinema1.service.AdminService" %><%--
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
    <table>
        <th>
            <form action="/admin/admins/new">
                <button type="submit">New admin</button>
            </form>
        </th>
        <tr>
            <th class="title">№</th>
            <th class="title">First name</th>
            <th class="title">Last name</th>
            <th class="title">Login</th>
            <th class="title">Phone</th>
            <th class="title">Email</th>
        </tr>
        <%
            int i = 0;
            for (User user :
                    AdminService.getInstance().getAll()) {
                i++;
        %>
        <tr>
            <td class="text"><a href="/admin/admin?id=<%= user.getId() %>"><%= i%></a>
            </td>
            <td class="text"><a href="/admin/admin?id=<%= user.getId() %>"><%= user.getFirstName()%></a>
            </td>
            <td class="text"><a href="/admin/admin?id=<%= user.getId() %>"><%= user.getLastName() %></a>
            </td>
            <td class="text"><a href="/admin/admin?id=<%= user.getId() %>"><%= user.getLogin() %></a>
            </td>
            <td class="text"><a href="/admin/admin?id=<%= user.getId() %>"><%= user.getPhone() %></a>
            </td>
            <td class="text"><a href="/admin/admin?id=<%= user.getId() %>"><%= user.getEmail() %></a>
            </td>
            <td class="list"><a href="/admin/admins/delete?id=<%= user.getId()%>">Delete</a></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>
