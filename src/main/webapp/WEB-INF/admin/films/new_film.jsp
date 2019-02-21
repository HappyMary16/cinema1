<%@ page import="ua.com.cinema1.dao.HallDao" %>
<%@ page import="ua.com.cinema1.dao.LanguagesDao" %>
<%@ page import="ua.com.cinema1.model.Hall" %>
<%@ page import="ua.com.cinema1.model.Language" %><%--
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

        #film, #date, #time, #hall, #price #film {
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
<div id="addLanguage">

</div>
<div id="content">
    <h3>Add new film:</h3><br>
    <form action="/admin/add_film" method="post">
        <p><label for="title">Title:</label><input type="text" name="title" id="title" required></p>
        <p><label for="description">Description:</label><input type="text" name="description" id="description" required>
        </p>
        <p><label for="minAge">Min age:</label><input type="number" name="minAge" id="minAge" required></p>
        <p><label for="duration">Duration:</label><input type="number" name="duration" id="duration" required></p>
        <%--<p>--%>
            <%--<form action="/admin/add_language" method="post">--%>
        <%--<p><label for="languageAdd">Language:</label><input type="text" name="languageAdd" id="languageAdd" required>--%>
        <%--</p>--%>
        <%--<p><input type="submit" value="Add language" class="submitField"></p>--%>
    <%--</form>--%>
    <%--</p>--%>
    <p><label for="language">Language:</label>
        <select name="language" id="language">
            <%
                for (Language language :
                        LanguagesDao.getInstance().getAll()) {
            %>
            <option value="<%=language.getId()%>"><%=language.getLanguage()%>
            </option>
            <%
                }
            %>
        </select></p>
    <p><label for="firstSeance">First seance:</label><input type="date" name="firstSeance" id="firstSeance" required>
    </p>
    <p><label for="lastSeance">Last seance:</label><input type="date" name="lastSeance" id="lastSeance" required>
    </p>
    <p><label for="smallPoster">Small poster (link):</label><input type="text" name="smallPoster" id="smallPoster"
                                                                   required></p>
    <p><label for="bigPoster">Big poster (link):</label><input type="text" name="bigPoster" id="bigPoster" required></p>
    <p><label for="trailer">Trailer (link):</label><input type="text" name="trailer" id="trailer" required></p>
    <p><input type="submit" value="Save" class="submitField"></p>

    </form>
</div>
</body>
</html>
