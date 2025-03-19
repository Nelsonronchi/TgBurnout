<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="VO.Pessoa" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuários</title>
    <link rel="stylesheet" href="style.css">
    <script>
        function confirmarExclusao(id) {
            if (confirm("Tem certeza que deseja excluir este usuário?")) {
                window.location.href = "ExcluirUsuarioServlet?id=" + id;
            }
        }
    </script>
</head>
<body>
    <h2>Lista de Usuários</h2>

    <form action="ListarUsuariosServlet" method="GET">
        <input type="text" name="nomePesquisa" placeholder="Digite um nome..." required>
        <button type="submit">Pesquisar</button>
    </form>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>CPF</th>
                <th>Data de Nascimento</th>
                <th>Telefone</th>
                <th>Sexo</th>
                <th>Profissão</th>
                <th>Tipo</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Pessoa> listaUsuarios = (List<Pessoa>) request.getAttribute("usuarios");
                if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
                    for (Pessoa pessoa : listaUsuarios) {
            %>
                        <tr>
                            <td><%= pessoa.getId() %></td>
                            <td><%= pessoa.getNome() %></td>
                            <td><%= pessoa.getEmail() %></td>
                            <td><%= pessoa.getCpf() %></td>
                            <td><%= pessoa.getDataNascimento() %></td>
                            <td><%= pessoa.getTelefone() %></td>
                            <td><%= pessoa.getSexo() %></td>
                            <td><%= pessoa.getProfissao() %></td>
                            <td><%= pessoa.getTipoUsuario() %></td>
                            <td>
                                <a href="EditarUsuarioServlet?id=<%= pessoa.getId() %>">Editar</a> | 
                                <a href="#" onclick="confirmarExclusao(<%= pessoa.getId() %>)">Excluir</a>
                            </td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr><td colspan="10">Nenhum usuário encontrado.</td></tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
