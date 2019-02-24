<%@ page import="ua.com.cinema1.dao.LanguagesDao" %>
<%@ page import="ua.com.cinema1.model.Genre" %>
<%@ page import="ua.com.cinema1.model.Language" %>
<%@ page import="ua.com.cinema1.model.Country" %>
<%@ page import="ua.com.cinema1.model.Studio" %>
<%@ page import="ua.com.cinema1.model.Actor" %>
<%@ page import="ua.com.cinema1.model.Director" %>
<%@ page import="ua.com.cinema1.dao.StudioDao" %>
<%@ page import="ua.com.cinema1.dao.CountryDao" %>
<%@ page import="ua.com.cinema1.dao.DirectorDao" %>
<%@ page import="ua.com.cinema1.dao.ActorDao" %>
<%@ page import="ua.com.cinema1.model.Studio" %>
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
    <title>Add new film</title>
</head>

<script type="text/javascript">
    function addValue(inp, sel) {

        const input = document.getElementById(inp);
        const selVal = document.getElementById(sel).value;

        if (~input.value.indexOf(selVal)) {
            return;
        }

        if (input.value) {
            input.value += ", ";
        }

        input.value += selVal;
    }
</script>

<script type="text/javascript">
    function checkYear() {

        const year = document.getElementById('year').value;
        const d = new Date();
        const n = d.getFullYear();
        if (year > n) {
            alert("Max year is " + n);
            return false;
        }
        return true;
    }
</script>

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

    .submitField {
        margin-left: 425px; /* Сдвигаем вправо под поля */
    }
</style>

<body>
<div id=sidebar>
    <ul id="navbar"><li><a href="/admin/users">Users</a></li>
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
    <h3>Add new film:</h3><br>

    <form action="/admin/add_film" method="post" onsubmit="return checkYear()">
        <p><label for="title">Title:</label>
            <input type="text" name="title" id="title" required></p>
        <p><label for="description">Description:</label>
            <input type="text" name="description" id="description" required></p>
        <p><label for="year">Year:</label>
            <input type="number" name="year" id="year" min="1800" required></p>
        <p><label for="minAge">Min age:</label>
            <input type="number" name="minAge" id="minAge" required></p>
        <p><label for="duration">Duration:</label>
            <input type="number" name="duration" id="duration" required></p>
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
        <p><label for="genres">Genres:</label>
            <input type="text" name="genres" id="genres" pattern="(.+\(\d+\))+" required>
            <select id="selectGenre" onchange="addValue('genres', 'selectGenre')">
                <%
                    for (Genre genre :
                            Genre.values()) {
                %>
                <option value="<%=genre + "(" + genre.getId() + ")"%>"><%=genre%>
                </option>
                <%
                    }
                %>
            </select></p>
        <p><label for="studios">Studios:</label>
            <input type="text" name="studios" id="studios" pattern="(.+\(\d+\))+">
            <select id="selectStudios" onchange="addValue('studios', 'selectStudios')">
                <%
                    for (Studio studio :
                            StudioDao.getInstance().getAll()) {
                %>
                <option value="<%=studio + "(" + studio.getId() + ")"%>"><%=studio%>
                </option>
                <%
                    }
                %>
            </select></p>
        <p><label for="countries">Countries:</label>
            <input type="text" name="countries" id="countries" pattern="(.+\(\d+\))+">
            <select id="selectCountries" onchange="addValue('countries', 'selectCountries')">
                <%
                    for (Country country :
                            CountryDao.getInstance().getAll()) {
                %>
                <option value="<%=country + "(" + country.getId() + ")"%>"><%=country%>
                </option>
                <%
                    }
                %>
            </select></p>
        <p><label for="directors">Directors:</label>
            <input type="text" name="directors" id="directors" pattern="(.+\(\d+\))+">
            <select id="selectDirectors" onchange="addValue('directors', 'selectDirectors')">
                <%
                    for (Director director :
                            DirectorDao.getInstance().getAll()) {
                %>
                <option value="<%=director + "(" + director.getId() + ")"%>"><%=director%>
                </option>
                <%
                    }
                %>
            </select></p>
        <p><label for="actors">Actors:</label>
            <input type="text" name="actors" id="actors" pattern="(.+\(\d+\))+">
            <select id="selectActors" onchange="addValue('actors', 'selectActors')">
                <%
                    for (Actor actor :
                            ActorDao.getInstance().getAll()) {
                %>
                <option value="<%=actor + "(" + actor.getId() + ")"%>"><%=actor%>
                </option>
                <%
                    }
                %>
            </select></p>
        <p><label for="firstSeance">First seance:</label>
            <input type="date" name="firstSeance" id="firstSeance" required></p>
        <p><label for="lastSeance">Last seance:</label>
            <input type="date" name="lastSeance" id="lastSeance" required></p>
        <p><label for="smallPoster">Small poster (link):</label>
            <input type="text" name="smallPoster" id="smallPoster" required></p>
        <p><label for="bigPoster">Big poster (link):</label>
            <input type="text" name="bigPoster" id="bigPoster" required></p>
        <p><label for="trailer">Trailer (link):</label>
            <input type="text" name="trailer" id="trailer" required></p>

        <p><input type="submit" value="Save" class="submitField"></p>
    </form>

</div>
</body>
</html>
