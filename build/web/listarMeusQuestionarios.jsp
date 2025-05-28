<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Meus Questionários</title>
</head>
<body>
    <h2>Meus Questionários para Responder</h2>

    <c:if test="${empty questionarios}">
        <p>Você não tem nenhum questionário para responder no momento.</p>
    </c:if>

    <c:if test="${not empty questionarios}">
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Título do Questionário</th>
                <th>Ação</th>
            </tr>
            <c:forEach var="uq" items="${questionarios}">
                <tr>
                    <td>${uq.questionario.titulo}</td>
                    <td>
                        <form action="responderQuestionario" method="get">
                            <input type="hidden" name="questionarioId" value="${uq.questionario.id}">
                            <button type="submit">Responder</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <br>
    <a href="paginaUsuario.jsp">Voltar</a>
</body>
</html>
