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
            display: block;
        }

        #sidebar {
            width: 200px;
            background: #ffffff;
            border-right: 1px solid #231F20;
            top: 0px; /* Расстояние от верхнего края */
            bottom: 0; /* Расстояние снизу  */
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

        #content {
            top: 0px; /* Расстояние от верхнего края */
            left: 220px; /* Расстояние от левого края */
            bottom: 0;
            right: 0;
        }

        #firstNameField, #lastNameField, #emailField, #loginField, #passwordField, #confirmPasswordField, #phoneField {
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

        .submitField {
            margin-left: 425px; /* Сдвигаем вправо под поля */
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
    <h3>Додавання нового адміністратора:</h3><br>
    <form action="/admin/add_admin" method="post" onsubmit="return checkForm();">
        <p><label for="firstNameField">Имя:</label><input type="text" name="firstName" id="firstNameField" required></p>
        <p><label for="lastNameField">Фамилия:</label><input type="text" name="lastName" id="lastNameField" required>
        </p>
        <p><label for="loginField">Логін:</label><input type="login" name="login" id="loginField" required></p>
        <p><label for="phoneField">Телефон:</label><input type="tel" name="phone" id="phoneField"></p>
        <p><label for="emailField">Email:</label><input type="email" name="email" id="emailField" required></p>
        <p><label for="passwordField">Пароль:</label><input type="password" name="password" id="passwordField" required>
        </p>
        <p><label for="confirmPasswordField">Подтверждение пароля:</label><input type="password" name="confirmPassword"
                                                                                 id="confirmPasswordField" required></p>
        <p><input type="submit" value="Сохранить" class="submitField"></p>

    </form>
</div>
</body>
</html>
