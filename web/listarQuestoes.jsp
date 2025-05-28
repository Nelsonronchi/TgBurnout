<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gerenciar Questões</title>
</head>
<body>

    <h2>Escolha um Questionário</h2>
    <form action="ListarQuestoesServlet" method="GET">
        <select name="questionarioId" onchange="this.form.submit()">
            <option value="">Selecione um questionário</option>
            <c:forEach var="q" items="${questionarios}">
                <option value="${q.id}" ${param.questionarioId == q.id ? 'selected' : ''}>
                    ${q.titulo}
                </option>
            </c:forEach>
        </select>
    </form>

    <c:if test="${not empty questoes}">
        <h3>Questões do Questionário</h3>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Texto da Questão</th>
                <th>Ação</th>
            </tr>
            <c:forEach var="questao" items="${questoes}">
                <tr>
                    <td>${questao.id}</td>
                    <td>${questao.texto}</td>
                    <td>
                        <form action="DeletarQuestaoServlet" method="POST"
                            onsubmit="return confirm('Tem certeza que deseja excluir este questionário? Todas as questões serão apagadas!');">
                            <input type="hidden" name="questaoId" value="${questao.id}">
                            <input type="submit" value="Excluir">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <h3>Excluir o Questionário</h3>
        <form action="DeletarQuestionarioServlet" method="POST" 
              onsubmit="return confirm('Tem certeza que deseja excluir este questionário? Todas as questões serão apagadas!');">
            <input type="hidden" name="questionarioId" value="${param.questionarioId}">
            <input type="submit" value="Excluir Questionário">
        </form>
    </c:if>

</body>
</html>
