<%--
  Created by IntelliJ IDEA.
  User: Mimino
  Date: 27.10.2017
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сеансы</title>
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
            background-color: #949494;
            color: #fff;
            padding: 5px;
            text-decoration: none;
            font-weight: bold;
            border-left: 5px solid #33ADFF;
            display: block;
        }
    </style>
</head>

<body>

<ul id="navbar">
    <li><a href="/admin/users">Пользователи</a></li>
    <li><a href="/admin/admins">Администраторы</a></li>
    <li><a href="/admin/films">Фильмы</a></li>
    <li><a href="/admin/halls">Залы</a></li>
    <li><a href="/admin/seances">Сеансы</a></li>
</ul>

</body>
</html>