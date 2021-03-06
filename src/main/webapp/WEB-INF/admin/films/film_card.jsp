<%@ page import="ua.com.cinema1.model.*" %>
<%@ page import="ua.com.cinema1.service.FilmService" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 21.02.2019
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Film</title>
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

    input {
        width: 300px; /* Ширина поля */
        box-shadow: inset 0 1px 5px rgba(0, 0, 0, 0.2); /* Тень внутри */
        border: 1px solid #ccc; /* Параметры рамки */
        color: black; /* Цвет текста */
        padding-left: 30px; /* Расстояние от левого края */
    }

    label {
        width: 200px; /* Ширина */
        text-align: right; /* Выравниваем по правому краю */
        float: left; /* Выстраиваем элементы рядом */
        margin-right: 10px; /* Расстояние от текста до текстового поля */
        line-height: 30px; /* Выравниваем по высоте */
    }

    textarea {
        width: 200px;
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
    <%
        FilmService filmService = FilmService.getInstance();
        Film film = filmService.getById(Integer.parseInt(request.getParameter("id")));
    %>
    <p><label for="title"> Title: </label>
        <input type="text" name="title" value="<%=film.getTitle()%>" id="title" readonly></p>
    <p><label for="year"> Year: </label>
        <input type="number" name="year" id="year" value="<%=film.getYear()%>" readonly></p>
    <p><label for="description"> Description: </label>
        <input type="text" name="description" id="description" value="<%=film.getDescribe()%>" readonly></p>
    <p><label for="minAge"> Min age: </label>
        <input type="number" name="minAge" id="minAge" value="<%=film.getMinAge()%>" readonly></p>
    <p><label for="duration"> Duration: </label>
        <input type="number" name="duration" id="duration" value="<%=film.getDuration()%>" readonly></p>
    <p><label for="language"> Language: </label>
        <input type="text" name="language" id="language" value="<%=film.getLanguage().getLanguage()%>" readonly></p>
    <%
        StringBuilder genres = new StringBuilder();
        film.getGenres().forEach(e -> genres.append(e).append(", "));
        if (!genres.toString().isEmpty()) {
            genres.delete(genres.length() - 2, genres.length());
        }
    %>
    <p><label for="genres"> Genres: </label>
        <textarea name="genres" id="genres" readonly><%=genres.toString().toLowerCase()%> </textarea></p>
    <%
        StringBuilder studios = new StringBuilder();
        film.getStudios().forEach(e -> studios.append(e).append(", "));
        if (!studios.toString().isEmpty()) {
            studios.delete(studios.length() - 2, studios.length());
        }
    %>
    <p><label for="studios"> Studios: </label>
        <textarea name="studios" id="studios" readonly><%=studios.toString()%> </textarea></p>
    <%
        StringBuilder countries = new StringBuilder();
        film.getCountries().forEach(e -> countries.append(e).append(", "));
        if (!countries.toString().isEmpty()) {
            countries.delete(countries.length() - 2, countries.length());
        }
    %>
    <p><label for="countries"> Countries: </label>
        <textarea name="countries" id="countries" readonly><%=countries.toString()%> </textarea></p>

    <%
        StringBuilder directors = new StringBuilder();
        film.getDirectors().forEach(e -> directors.append(e).append(", "));
        if (!directors.toString().isEmpty()) {
            directors.delete(directors.length() - 2, directors.length());
        }
    %>
    <p><label for="directors"> Directors: </label>
        <textarea name="directors" id="directors" readonly><%=directors.toString()%> </textarea></p>
    <%
        StringBuilder actors = new StringBuilder();
        film.getActors().forEach(e -> actors.append(e).append(", "));
        if (!actors.toString().isEmpty()) {
            actors.delete(actors.length() - 2, actors.length());
        }
    %>
    <p><label for="actors"> Actors: </label>
        <textarea name="actors" id="actors" readonly><%=actors.toString()%> </textarea></p>
    <p><label for="firstSeance"> First seance: </label>
        <input type="date" name="firstSeance" id="firstSeance" value="<%=film.getFirstSeance()%>" readonly></p>
    <p><label for="lastSeance"> Last seance: </label>
        <input type="date" name="lastSeance" id="lastSeance" value="<%=film.getLastSeance()%>" readonly></p>
    <p><label for="smallPoster"> Small poster (link): </label>
        <input type="text" name="smallPoster" id="smallPoster" value="<%=film.getSmallPoster()%>" readonly></p>
    <p><label for="bigPoster"> Big poster (link): </label>
        <input type="text" name="bigPoster" id="bigPoster" value="<%=film.getBigPoster()%>" readonly></p>
    <p><label for="trailer"> Trailer (link): </label>
        <input type="text" name="trailer" id="trailer" value="<%=film.getTrailerLink()%>" readonly></p>

    <form action="/admin/film/update?id=<%=film.getId()%>" method="post">
        <button type="submit"> Update</button>
    </form>
</div>

</body>

</html>