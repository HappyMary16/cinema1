<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 16.02.2019
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Administration</title>
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

    </style>
</head>

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

</body>
</html>

