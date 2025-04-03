<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Associar Usuários a Questionário</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Associar Usuários a um Questionário</h2>

    <form action="associarUsuarioQuestionario" method="post">
        <label for="questionarioId">Selecione o Questionário:</label>
        <select name="questionarioId" id="questionarioId" required>
            <c:forEach var="q" items="${questionarios}">
                <option value="${q.id}">${q.titulo}</option>
            </c:forEach>
        </select>

        <br><br>

        <label>Escolha o tipo de associação:</label><br>
        <input type="radio" name="tipo" value="individual" id="tipoIndividual" checked>
        <label for="tipoIndividual">Individual</label>

        <input type="radio" name="tipo" value="grupo" id="tipoGrupo">
        <label for="tipoGrupo">Grupo</label>

        <br><br>

        <div id="individualSection">
            <label for="usuarioId">Selecione o Usuário:</label>
            <select name="usuarioId" id="usuarioId">
                <c:forEach var="u" items="${usuarios}">
                    <option value="${u.id}">${u.nome}</option>
                </c:forEach>
            </select>
        </div>

        <div id="grupoSection" style="display: none;">
            <label for="grupoId">Selecione o Grupo:</label>
            <select name="grupoId" id="grupoId">
                <c:forEach var="g" items="${grupos}">
                    <option value="${g.id}">${g.nome}</option>
                </c:forEach>
            </select>
        </div>

        <br><br>
        <button type="submit">Associar</button>
    </form>

    <script>
        document.querySelectorAll('input[name="tipo"]').forEach(radio => {
            radio.addEventListener('change', function() {
                if (this.value === 'individual') {
                    document.getElementById('individualSection').style.display = 'block';
                    document.getElementById('grupoSection').style.display = 'none';
                } else {
                    document.getElementById('individualSection').style.display = 'none';
                    document.getElementById('grupoSection').style.display = 'block';
                }
            });
        });
    </script>
</body>
</html>
