/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Neto
 */
package Controller;

import DAO.PessoaDAO;
import VO.Pessoa;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarPessoaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recebendo dados do formulário
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String dataNascimento = request.getParameter("dataNascimento");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String profissao = request.getParameter("profissao");
        String tipoUsuario = request.getParameter("tipoUsuario");
        int id = 0;
        

        // Criando um objeto Pessoa
        Pessoa pessoa = new Pessoa(id, nome, email, telefone, profissao);
        
        pessoa.setNome(nome);
        pessoa.setSenha(senha);
        pessoa.setEmail(email);
        pessoa.setCpf(cpf);
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setTelefone(telefone);
        pessoa.setSexo(sexo);
        pessoa.setProfissao(profissao);
        pessoa.setTipoUsuario(tipoUsuario);

        // Salvando no banco de dados
        PessoaDAO dao = new PessoaDAO();
        boolean sucesso = dao.cadastrarPessoa(pessoa);

        // Redirecionamento conforme o resultado
        if (sucesso) {
            response.sendRedirect("sucessoCadastrar.jsp");
        } else {
            response.sendRedirect("erro.jsp");
        }
    }
}
