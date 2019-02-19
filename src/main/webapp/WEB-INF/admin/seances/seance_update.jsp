<%@ page import="ua.com.cinema1.dao.FilmDao" %>
<%@ page import="ua.com.cinema1.dao.HallDao" %>
<%@ page import="ua.com.cinema1.dao.SeanceDao" %>
<%@ page import="ua.com.cinema1.model.Film" %>
<%@ page import="ua.com.cinema1.model.Hall" %>
<%@ page import="ua.com.cinema1.model.Seance" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 17.02.2019
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        function checkForm()
        {
            var p1 = document.getElementById('passwordField');
            var p2 = document.getElementById('confirmPasswordField');

            if(p1.value !== p2.value) // пароли не совпали
            {
                alert('Пароли не совпадают');
                return false;
            }
            return true;
        }
    </script>
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
    <form action="/admin/seance_update" method="post" onsubmit="return checkForm();">
        <%
            SeanceDao seanceService = SeanceDao.getInstance();
            Seance seance = seanceService.getById(Integer.parseInt(request.getParameter("id")));
        %>
        <p><label for="id">ID:</label><input type="number" name="id" id="id" value="<%=seance.getId()%>" readonly>
        </p>
        <label for="film">Film:</label><p><select name="film" id="film">
        <option value="<%=seance.getFilm().getId()%>"><%=seance.getFilm().getTitle()%>
        </option>
        <%
            for (Film film :
                    FilmDao.getInstance().getAll()) {
        %>
        <option value="<%=film.getId()%>"><%=film.getTitle()%>
        </option>
        <%
            }
        %>
    </select></p>
        <p><label for="date">Date:</label><input type="date" name="date" id="date"
                                                 value="<%=new SimpleDateFormat("yyyy-MM-dd").format((seance.getDateAdnTime()))%>" required></p>
        <p><label for="time">Time:</label><input type="time" name="time" id="time"
                                                 value="<%=new SimpleDateFormat("HH:mm").format((seance.getDateAdnTime()))%>" required></p>
        <label for="hall">Hall:</label><p><select name="hall" id="hall">
        <option value="<%=seance.getHall().getId()%>"><%=seance.getHall().getName()%>
        </option>
        <%
            for (Hall hall :
                    HallDao.getInstance().getAll()) {
        %>
        <option value="<%=hall.getId()%>"><%=hall.getName()%>
        </option>
        <%
            }
        %>
    </select></p>
        <p><label for="price">Price:</label><input type="number" name="price" id="price"
                                                   value="<%=seance.getPriceTicket()%>" required></p>

        <p><input type="submit" value="Save" class="submitField"></p>
    </form>
</div>

</body>
</html>