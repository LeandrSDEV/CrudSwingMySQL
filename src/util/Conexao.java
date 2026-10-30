package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static final String URL = env("DB_URL",
        "jdbc:mysql://localhost:3306/crud_java?useSSL=false&serverTimezone=UTC");
    private static final String USER = env("DB_USER", "root");
    private static final String PASS = env("DB_PASSWORD", "");
    
    private static String env(String chave, String padrao) {
        String valor = System.getenv(chave);
        return valor != null ? valor : padrao;
    }

    public static Connection conectar() {
        try{
            return DriverManager.getConnection(URL, USER, PASS);
        }catch (Exception e) {
            throw new RuntimeException("Erro de conexão: " + e.getMessage());
        }
    }
}
