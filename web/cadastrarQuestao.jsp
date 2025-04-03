<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Questão</title>
</head>
<body>

    <h2>Cadastrar Questão</h2>

    <form action="CadastrarQuestaoServlet" method="post">
        <label for="texto">Texto da Questão:</label>
        <input type="text" id="texto" name="texto" required>
        
        <label for="questionarioId">Questionário:</label>
        <select id="questionarioId" name="questionarioId" required>
            <c:forEach var="q" items="${questionarios}">
                <option value="${q.id}">${q.titulo}</option>
            </c:forEach>
        </select>

        <label for="autorId">Autor:</label>
        <select id="autorId" name="autorId" required>
            <c:forEach var="a" items="${autores}">
                <option value="${a.id}">${a.nome}</option>
            </c:forEach>
        </select>

        <button type="submit">Cadastrar</button>
    </form>

</body>
</html>
