<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Autor</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body class="Autores">
    <div class="container">
        <h2>Cadastro de Autor</h2>
<form action="CadastrarAutorServlet" method="post">
    <label for="nomeAutor">Nome do Autor:</label>
    <input type="text" id="nomeAutor" name="nomeAutor" required>

    <label for="descricaoAutor">Descrição:</label>
    <textarea id="descricaoAutor" name="descricaoAutor"></textarea>

    <button type="submit">Cadastrar</button>
</form>


    </div>
</body>
</html>
