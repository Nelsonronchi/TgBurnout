<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="java.util.List" %>
<%@ page import="VO.UsuarioQuestionario" %>
<html>
<head>
    <title>Meus Questionários</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            border-collapse: collapse;
            width: 70%;
        }
        th, td {
            border: 1px solid #333;
            padding: 8px 12px;
            text-align: left;
        }
        th {
            background-color: #555;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #eee;
        }
        .no-data {
            color: #a00;
            font-style: italic;
        }
        .pagination {
            margin-top: 20px;
        }
        .pagination a, .pagination strong {
            margin: 0 5px;
            text-decoration: none;
        }
    </style>
</head>
<body>

<h2>Meus Questionários</h2>

<%
    // Pega a lista do request, que foi setada pelo servlet
    List<UsuarioQuestionario> lista = (List<UsuarioQuestionario>) request.getAttribute("meusQuestionarios");
    
    if (lista == null || lista.isEmpty()) {
%>
    <p class="no-data">Nenhum questionário encontrado.</p>
<%
    } else {
%>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (UsuarioQuestionario uq : lista) {
                int id = uq.getQuestionario().getId();
                String titulo = uq.getQuestionario().getTitulo();
        %>
            <tr>
                <td><%= id %></td>
                <td><%= titulo %></td>
                <td>
                    <form action="responderQuestionario" method="get">
            <input type="hidden" name="questionarioId" value="<%= id %>" />
            <button type="submit">Responder</button>
        </form>
        </form>
                </td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <!-- Paginação -->
    <%
        int noOfPages = (Integer) request.getAttribute("noOfPages");
        int currentPage = (Integer) request.getAttribute("currentPage");
        String baseUrl = "ListarMeusQuestionariosServlet";
    %>

    <div class="pagination">
        <%
            for (int i = 1; i <= noOfPages; i++) {
                if (i == currentPage) {
        %>
            
        <%
                } else {
        %>
            <a href="<%= baseUrl %>?page=<%= i %>"><%= i %></a>
        <%
                }
            }
        %>
    </div>

<%
    }
%>

</body>
</html>
