<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Usuários por Grupo e Questionário</title></head>
<body>
<h2>Usuários associados ao Grupo e Questionário</h2>

<!-- FORMULÁRIO CORRETO -->
<form method="get" action="listarUsuarioQuestionario">
  <label>Grupo:</label>
  <select name="grupoId">
    <option value="">-- Selecione --</option>
    <c:forEach var="g" items="${grupos}">
      <option value="${g.id}" <c:if test="${param.grupoId == g.id}">selected</c:if>>${g.nome}</option>
    </c:forEach>
  </select>

  <label>Questionário:</label>
  <select name="questionarioId">
    <option value="">-- Selecione --</option>
    <c:forEach var="q" items="${questionarios}">
      <option value="${q.id}" <c:if test="${param.questionarioId == q.id}">selected</c:if>>${q.titulo}</option>
    </c:forEach>
  </select>

  <button type="submit">Buscar</button>
</form>

<!-- DEBUG VISUAL -->
<p><strong>grupoId:</strong> ${param.grupoId}</p>
<p><strong>questionarioId:</strong> ${param.questionarioId}</p>

<!-- RESULTADO -->
<c:if test="${not empty associacoes}">
  <h3>Resultados encontrados: ${fn:length(associacoes)}</h3>
  <table border="1">
    <tr><th>ID</th><th>Nome</th><th>CPF</th></tr>
    <c:forEach var="uq" items="${associacoes}">
      <tr>
        <td>${uq.usuario.id}</td>
        <td>${uq.usuario.nome}</td>
        <td>${uq.usuario.cpf}</td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<c:if test="${empty associacoes && param.grupoId != null && param.questionarioId != null}">
  <p style="color: red;">Nenhum usuário encontrado para os critérios selecionados.</p>
</c:if>

</body>
</html>
