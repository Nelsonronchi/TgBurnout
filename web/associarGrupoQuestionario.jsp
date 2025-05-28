<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head><meta charset="UTF-8"><title>Associar Grupo-Questionário</title></head><body>
<h2>Associar Questionário a um Grupo</h2>
<form action="associarGrupoQuestionario" method="post">
    <label>Grupo:</label>
    <select name="grupoId" required>
        <c:forEach var="g" items="${grupos}">
            <option value="${g.id}">${g.nome}</option>
        </c:forEach>
    </select>
    <label>Questionário:</label>
    <select name="questionarioId" required>
        <c:forEach var="q" items="${questionarios}">
            <option value="${q.id}">${q.titulo}</option>
        </c:forEach>
    </select>
    <button type="submit">Associar</button>
</form>
<br><a href="associarUsuarioGrupo">Próximo: Associar Usuários ao Grupo</a>
</body></html>
