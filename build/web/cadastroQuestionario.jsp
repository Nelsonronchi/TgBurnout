<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Questionário</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Cadastrar Questionário</h1>
        
        <form action="CadastrarQuestionarioServlet" method="POST">
            <label for="titulo">Título do Questionário:</label>
            <input type="text" id="titulo" name="titulo" >
            
            <label for="autor">Autor:</label>
            <select name="autorId" id="autor" required>
    <option value="">Selecione um autor</option>
    <c:forEach var="autor" items="${autores}">
        <option value="${autor.id}">${autor.nome}</option>
    </c:forEach>
</select>
            
            <button type="submit">Cadastrar Questionário</button>
        </form>
    </div>
</body>
</html>
