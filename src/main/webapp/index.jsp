<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cinema</title>
    <style>
        #topnav {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #33ADFF;
        }

        #topnav li {
            float: left;
            border-right: 1px solid #33ADFF;
            border-bottom: 2px solid #33ADFF;
        }
        #topnav li a {
            display: block;
            color: black;
            background-color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        /* Change the link color to #111 (black) on hover */
        #topnav li a:hover {
            background-color: #ECF5E4;
        }

    </style>
</head>
<body>
<!-- Load an icon library to show a hamburger menu (bars) on small screens -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<div class="topnav" id="topnav">
    <li><a class="active" href="/cinema" id="cinemaField">Cinema</a></li>
    <li><a href="/cinema/posters">Афіша</a></li>
    <li><a href="/cinema/seances">Сеанси</a></li>
    <li style="float: right"><a href="/cinema/user/tickets" id="ticketsField">Мої квитки</a></li>
    <li style="float: right"><a href="/admin" id="manege">Управління</a></li>
    <li style="float: right"><a href="/cinema/login" id="loginField">Увійти</a></li>

</div>

<h2>Hello World!</h2>
</body>
</html>
