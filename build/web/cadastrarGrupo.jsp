<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Cadastrar Grupos</title>
</head>
<body>
  <h2>Cadastrar Novo Grupo</h2>

  <c:if test="${not empty error}">
    <div style="color:red;">${error}</div>
  </c:if>

  <form action="cadastrarGrupo" method="post">
    <label for="nome">Nome do Grupo:</label>
    <input type="text" name="nome" id="nome" required>
    <button type="submit">Cadastrar</button>
  </form>

  <hr>

  <h3>Grupos Cadastrados</h3>
  <table border="1">
    <tr><th>ID</th><th>Nome</th></tr>
    <c:forEach var="g" items="${grupos}">
      <tr>
        <td>${g.id}</td>
        <td>${g.nome}</td>
      </tr>
    </c:forEach>
  </table>

 
  <br><a href="associarGrupoQuestionario">Próximo: Associar Grupo ao questionario</a>
</body>
</html>
