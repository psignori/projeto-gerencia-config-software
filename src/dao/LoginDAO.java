/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.conexaoBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocios.pessoas;
/**
 *
 * @author patri
 */
public class LoginDAO {
    private ResultSet resultado = null;
    
    public boolean login (pessoas o){
        boolean a = false;
            try {
                Statement st = conexaoBD.getInstance().getConnection().createStatement();
                String sql = "select * from pessoa where email = '"+ o.getEmail() +"' and senha = '"+ o.getSenha() + "'";
                resultado = st.executeQuery(sql); 
                resultado.next();
                
                if(resultado.getString("email").equals(o.getEmail()) ){
                    if(resultado.getString("senha").equals(o.getSenha())){
                        a = true;
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao fazer login!");
                a = false;
            }
        return a;
    }
    
    public pessoas loginPassadoEmailESenha (pessoas o){
        try {
                Statement st = conexaoBD.getInstance().getConnection().createStatement();
                String sql = "select * from pessoa where email = '"+ o.getEmail() +"' and senha = '"+ o.getSenha() + "'";
                resultado = st.executeQuery(sql); 
                resultado.next();
                 
                o.setNomeUsuario(resultado.getString("nome_user"));
                o.setId_pessoas(resultado.getInt("id_pessoa"));
                o.setNome(resultado.getString("nome"));
                
                

            } catch (Exception e) {
                
                System.out.println("Erro ao fazer a consulta = " + e);
            }
        return o;
    }
    
    public pessoas loginPassandoID (pessoas o){
        try {
                Statement st = conexaoBD.getInstance().getConnection().createStatement();
                String sql = "select * from pessoa where id_pessoa = '"+ o.getId_pessoas() +"'";
                resultado = st.executeQuery(sql); 
                resultado.next();

                o.setEmail(resultado.getString("email"));
                o.setId_pessoas(resultado.getInt("id_pessoa"));
                o.setNome(resultado.getString("nome"));
                
                

            } catch (Exception e) {
                
                System.out.println("Erro ao fazer a consulta = " + e);
            }
        return o;
    }
}
