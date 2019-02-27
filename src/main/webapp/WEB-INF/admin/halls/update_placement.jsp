<%@ page import="ua.com.cinema1.dao.HallDao" %>
<%@ page import="ua.com.cinema1.model.Hall" %><%--
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
</head>

<script> document.addEventListener('DOMContentLoaded', function () {
    for (let i = 0; i < document.getElementById("height").value; i++) {
        for (let j = 0; j < document.getElementById("width").value; j++) {

            const button = document.getElementById(i + " " + j);
            const hid = document.getElementById(i + "a" + j);
            if (hid.value === "0") {
                button.style.color = "black";
                button.style.backgroundColor = "black";
            } else {
                button.style.color = "#33ADFF";
                button.style.backgroundColor = "#33ADFF";
            }
        }
    }
}, false);

</script>
<script type="text/javascript">
    function changeButton(i, j) {
        const button = document.getElementById(i + " " + j);
        const hid = document.getElementById(i + "a" + j);
        if (hid.value === "1") {
            button.style.color = "black";
            button.style.backgroundColor = "black";
            hid.value = 0;
        } else {
            button.style.color = "#33ADFF";
            button.style.backgroundColor = "#33ADFF";
            hid.value = 1;
        }
    }
</script>

<style>

    #content {
        position: absolute;
        overflow: auto;
        padding: 10px;
        top: 0px; /* Расстояние от верхнего края */
        left: 250px; /* Расстояние от левого края */
        bottom: 0;
        right: 0;
    }

    #save {
        width: 300px; /* Ширина поля */
        box-shadow: inset 0 1px 5px rgba(0, 0, 0, 0.2); /* Тень внутри */
        border: 1px solid #ccc; /* Параметры рамки */
        color: black; /* Цвет текста */
        padding-left: 10px; /* Расстояние от левого края */
        background-color: #cccccc;
    }

    table input {
        font-size: 12px;
        width: 20px; /* Ширина */
        height: 24px;
        line-height: 24px;
        text-align: center; /* Выравниваем по правому краю */
        float: left; /* Выстраиваем элементы рядом */
        margin-right: 10px; /* Расстояние от текста до текстового поля */
    }

    .submitField {
        margin-left: 425px; /* Сдвигаем вправо под поля */
    }
</style>

<body>

<div id="content">
    <h3>Update placement:</h3>

    <%
        Hall hall = HallDao.getInstance().getById(Integer.valueOf(request.getParameter("id")));
    %>
    <input type="hidden" name="height" id="height" value="<%=hall.getHeight()%>"/>
    <input type="hidden" name="width" id="width" value="<%=hall.getWidth()%>"/>

    <form action="/admin/update_placement?id=<%=hall.getId()%>" method="post">
        <table>
            <%
                for (int i = 0; i < hall.getHeight(); i++) {
            %>
            <tr><%
                for (int j = 0; j < hall.getWidth(); j++) {

            %>
                <td><input type="hidden" name="<%=i + "a" + j%>" id="<%=i + "a" + j%>"
                           value="<%=hall.getPlacement()[i][j] ? 1 : 0%>" required/>
                    <input type="button" name="<%=hall.getPlacement()[i][j] ? 1 : 0%>" id="<%=i + " " + j%>"
                           onclick="return changeButton(<%=i%>, <%=j%>);"/></td>
                <%
                    }
                %></tr>
            <%
                }
            %>
        </table>
        <p><input type="submit" id="save" value="Save" class="submitField"></p>
    </form>
</div>
</body>
</html>