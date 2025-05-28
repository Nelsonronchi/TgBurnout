<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><meta charset="UTF-8"><title>Associações Grupo–Questionário</title></head>
<body>
  <h2>Associações Grupo – Questionário</h2>

  <form action="listarGrupoQuestionario" method="get">
    <label>Selecione Grupo:</label>
    <select name="grupoId" onchange="this.form.submit()">
      <option value="">--</option>
      <c:forEach var="g" items="${grupos}">
        <option value="${g.id}" ${param.grupoId==g.id?'selected':''}>${g.nome}</option>
      </c:forEach>
    </select>
  </form>

  <c:if test="${not empty associacoes}">
    <table border="1">
      <tr><th>ID</th><th>Questionário</th><th>Ações</th></tr>
      <c:forEach var="a" items="${associacoes}">
        <tr>
          <td>${a.id}</td>
          <td>${a.questionario.titulo}</td>
          <td>
            <!-- botão excluir -->
            <form action="deletarGrupoQuestionario" method="post" style="display:inline">
              <input type="hidden" name="id" value="${a.id}"/>
              <input type="hidden" name="grupoId" value="${param.grupoId}"/>
              <button onclick="return confirm('Excluir?')">Excluir</button>
            </form>
            <!-- link para editar -->
            
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:if>

  <br><a href="associarGrupoQuestionario">Nova Associação</a>
</body>
</html>
