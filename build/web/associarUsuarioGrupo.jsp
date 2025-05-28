<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head><meta charset="UTF-8"><title>Associar Usuários a Grupo</title></head><body>
<h2>Associar Usuários a um Grupo</h2>
<form action="associarUsuarioGrupo" method="post">
    <label>Grupo:</label>
    <select name="grupoId" required>
        <c:forEach var="g" items="${grupos}">
            <option value="${g.id}">${g.nome}</option>
        </c:forEach>
    </select>
    <br><br>
    <label>Selecione Usuários:</label><br>
    <c:forEach var="u" items="${usuarios}">
        <input type="checkbox" name="usuarioId" value="${u.id}">${u.nome}<br>
    </c:forEach>
    <button type="submit">Associar Usuários</button>
</form>
<br><a href="listarUsuarioQuestionario">Ver Associações de Usuários-Questionários</a>
</body></html>
