<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="VO.Pessoa" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Usuário</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Editar Usuário</h2>

    <%
        Pessoa pessoa = (Pessoa) request.getAttribute("pessoa");
        if (pessoa != null) {
    %>
    
    <form action="AtualizarUsuarioServlet" method="POST">
        <input type="hidden" name="id" value="<%= pessoa.getId() %>">
        
        <label>Nome:</label>
        <input type="text" name="nome" value="<%= pessoa.getNome() %>" required>

        <label>Email:</label>
        <input type="email" name="email" value="<%= pessoa.getEmail() %>" required>

        <label>Telefone:</label>
        <input type="text" name="telefone" value="<%= pessoa.getTelefone() %>" required>

        <label>Profissão:</label>
        <input type="text" name="profissao" value="<%= pessoa.getProfissao() %>" required>

        <button type="submit">Atualizar</button>
    </form>

    <%
        } else {
            out.println("<p>Usuário não encontrado.</p>");
        }
    %>
</body>
</html>
