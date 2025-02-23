/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

/**
 *
 * @author Neto
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // A URL com o parâmetro serverTimezone para evitar problemas com fusos horários
    private static final String URL = "jdbc:mysql://localhost:3306/tgburnout?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        // Registra o driver JDBC (necessário para versões mais antigas do Java)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Apenas se for necessário
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver não encontrado.");
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

