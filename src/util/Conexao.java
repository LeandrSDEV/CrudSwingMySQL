package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static final String URL =
        "jdbc:mysql://localhost:3306/crud_java?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "Js@153624";
    
    public static Connection conectar() {
        try{
            return DriverManager.getConnection(URL, USER, PASS);
        }catch (Exception e) {
            throw new RuntimeException("Erro de conexão: " + e.getMessage());
        }
    }
}
