<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Responder Questionário</title>
</head>
<body>
    <h2>Responder: ${questionario.titulo}</h2>

    <form action="salvarRespostas" method="post">
        <input type="hidden" name="questionarioId" value="${questionario.id}"/>

        <c:forEach var="pergunta" items="${perguntas}">
            <div style="margin-bottom: 20px;">
                <strong>${pergunta.texto}</strong><br/>
                <c:forEach var="i" begin="1" end="5">
                    <input type="radio" name="resposta_${pergunta.id}" value="${i}" required/>
                    ${i}
                </c:forEach>
            </div>
        </c:forEach>

        <button type="submit">Enviar Respostas</button>
    </form>

    <br>
    <a href="ListarMeusQuestionariosServlet">Voltar</a>
</body>
</html>

