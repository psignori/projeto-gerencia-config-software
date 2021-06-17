/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author patri
 */
public class Conexao {
    /**
     * O atributo banco representa a url jdbc de conexÃ£o com o banco de dados
     * Para se trabalhar com outros bancos criados no postgresql 
     * muda-se somente o nome do banco
     */
    private static final String banco = 
            "jdbc:postgresql://localhost:5432/ProjQuarta";
    /**
     * O atributo driver representa a classe do Driver JDBC que serÃ¡ usada na 
     * conexÃ£o. Quando se utiliza outros bancos usa-se a classe apropriada a 
     * cada banco
     */
    private static final String driver = 
            "org.postgresql.Driver";
    /**
     * Os atributos usuario e senha representam usuario e senha do 
     * SGBD a ser usado na conexÃ£o
     */
    private static final String usuario = "postgres";
    private static final String senha = "postgres";  
    /**
     * O atributo con representa um objeto que 
     * contÃ©m a conexÃ£o com o banco de dados em si
     */
    private static Connection con = null;
    
    /**
     * Metodo que retorna uma conexÃ£o com o banco de dados
     * @return objeto java.sql.Connection
     */
    public static Connection getConexao(){
        // primeiro testo se o objeto con nÃ£o foi inicializado
        if (con == null){
            try {
                // defino a classe do driver a ser usado
                Class.forName(driver);
                // criaÃ§Ã£o da conexÃ£o com o BD
                con = 
                DriverManager.getConnection(
                        banco, usuario, senha);
            } catch (ClassNotFoundException ex) {
                System.out.println("NÃ£o encontrou o driver");
            } catch (SQLException ex) {
                System.out.println("Erro ao conectar: "+
                        ex.getMessage());
            }
        }
        return con;
    }
    /**
     * MÃ©todo que recebe um comando SQL para ser executado
     * @param sql
     * @return um objeto java.sql.PreparedStatement
     */
    public static PreparedStatement getPreparedStatement(String sql){
        // testo se a conexÃ£o jÃ¡ foi criada
        if (con == null){
            // cria a conexÃ£o
            con = getConexao();
        }
        try {
            // retorna um objeto java.sql.PreparedStatement
            return con.prepareStatement(sql);
        } catch (SQLException e){
            System.out.println("Erro de sql: "+
                    e.getMessage());
        }
        return null;
    }
}
