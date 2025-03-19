<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Pessoa</title>
    <!-- Linkando o CSS externo -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="body-cadastro">
    <form action="cadastrarPessoa" method="post">
        <h2>Cadastro de Pessoa</h2>
        <input type="text" name="nome" placeholder="Nome" required>
        <input type="password" name="senha" placeholder="Senha" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="text" name="cpf" placeholder="CPF" required>
        <input type="date" name="dataNascimento" required>
        <input type="tel" name="telefone" placeholder="Telefone" required>
        
        <select name="sexo" required>
            <option value="">Sexo</option>
            <option value="M">Masculino</option>
            <option value="F">Feminino</option>
            <option value="O">Outro</option>
        </select>
        
        <select name="profissao" required>
            <option value="">Profissão</option>
            <option value="Médico">Médico</option>
            <option value="Professor">Professor</option>
            <option value="Dentista">Dentista</option>
            <option value="Engenheiro">Engenheiro</option>
            <option value="Advogado">Advogado</option>
        </select>
        
        <select name="tipoUsuario" required>
            <option value="">Tipo de Usuário</option>
            <option value="Usuario">Usuário</option>
            <option value="Admin">Administrador</option>
        </select>
        
        <button type="submit">Cadastrar</button>
    </form>
</body>
</html>
