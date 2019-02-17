<%@ page import="ua.com.cinema1.dao.UserDao" %>
<%@ page import="ua.com.cinema1.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Mimino
  Date: 04.11.2017
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Администратор</title>
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
            background: #ECF5E4;
            border-right: 1px solid #231F20;
            top: 0px; /* Расстояние от верхнего края */
            bottom: 0; /* Расстояние снизу  */
        }

        #content {
            top: 0px; /* Расстояние от верхнего края */
            left: 220px; /* Расстояние от левого края */
            bottom: 0;
            right: 0;
        }

        #firstNameField, #lastNameField, #loginField, #passwordField, #confirmPasswordField, #phoneField, #idField {
            width: 300px; /* Ширина поля */
            padding: 10px; /* Поля */
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

    </style>
</head>

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
    <%
        UserDao userService = UserDao.getInstance();
        User userDTO = userService.getById(Integer.parseInt(request.getParameter("id")));
    %>
    <p><label for="idField">ID:</label><input type="text" name="ID" id="idField" value="<%=userDTO.getId()%>" readonly>
    </p>
    <p><label for="firstNameField">Имя:</label><input type="text" name="firstName" id="firstNameField"
                                                      value="<%=userDTO.getFirstName()%>" readonly></p>
    <p><label for="lastNameField">Фамилия:</label><input type="text" name="lastName" id="lastNameField"
                                                         value="<%=userDTO.getLastName()%>" readonly></p>
    <p><label for="phoneField">Телефон:</label><input type="tel" name="phone" id="phoneField"
                                                      value="<%=userDTO.getPhone()%>" readonly></p>
    <p><label for="loginField">Email:</label><input type="email" name="email" id="loginField"
                                                    value="<%=userDTO.getEmail()%>" readonly></p>
    <p><label for="passwordField">Пароль:</label><input type="password" name="password" id="passwordField"
                                                        value="<%=userDTO.getPassword()%>" readonly></p>
</div>

</body>
</html>