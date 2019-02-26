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
</head>

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
        color: #33ADFF;
        background-color: #33ADFF;
        float: left; /* Выстраиваем элементы рядом */
        margin-right: 10px; /* Расстояние от текста до текстового поля */
    }

    .submitField {
        margin-left: 425px; /* Сдвигаем вправо под поля */
    }
</style>

<body>

<div id="content">
    <h3>Add new hall:</h3>

    <form action="/admin/add_hall?name=<%=request.getParameter("name")%>&height=<%=request.getParameter("height")%>&width=<%=request.getParameter("width")%>"
          method="post">
        <table>
        <%
            for (int i = 0; i < Integer.valueOf(request.getParameter("height")); i++) {
        %>
        <tr><%
            for (int j = 0; j < Integer.valueOf(request.getParameter("width")); j++) {

        %>
            <td><input type="hidden" name="<%=i + "a" + j%>" id="<%=i + "a" + j%>" value="1" required/>
            <input type="button" name="<%=i + " " + j%>" id="<%=i + " " + j%>"
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