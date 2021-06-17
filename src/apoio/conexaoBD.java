/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.sql.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author patri
 */
public class conexaoBD {
    private static conexaoBD instancia = null;
    private Connection conexao = null;

    public conexaoBD() {
        try {
            // Carrega informaÃ§Ãµes do arquivo de propriedades
            //Properties prop = new Properties();
            //prop.load(new FileInputStream("db.properties"));
            String dbdriver = "org.postgresql.Driver";
            String dburl = "jdbc:postgresql://localhost:5432/ProjQuarta";
            String dbuser = "postgres";
            String dbsenha = "postgres";

            // Carrega Driver do Banco de Dados
            Class.forName(dbdriver);

            if (dbuser.length() != 0) // conexÃ£o COM usuÃ¡rio e senha
            {
                conexao = DriverManager.getConnection(dburl, dbuser, dbsenha);
            } else // conexÃ£o SEM usuÃ¡rio e senha
            {
                conexao = DriverManager.getConnection(dburl);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // Retorna instÃ¢ncia
    public static conexaoBD getInstance() {
        if (instancia == null) {
            instancia = new conexaoBD();
        }
        return instancia;
    }

    // Retorna conexÃ£o
    public Connection getConnection() {
        if (conexao == null) {
            throw new RuntimeException("conexao==null");
        }
        return conexao;
    }

    // Efetua fechamento da conexÃ£o
    public void shutDown() {
        try {
            conexao.close();
            instancia = null;
            conexao = null;
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
