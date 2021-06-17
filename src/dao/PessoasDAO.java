/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.Conexao;
import apoio.IDAOT;
import apoio.conexaoBD;
import java.awt.Color;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import negocios.Criptografia;
import negocios.pessoas;

/**
 *
 * @author patri
 */
public class PessoasDAO implements IDAOT <pessoas> {
    
     private ResultSet resultado = null;

    @Override
    public boolean salvar(pessoas o) {
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "";
            if (o.getId_pessoas()== 0){
                sql = "INSERT INTO public.pessoa (id_pessoa, nome, data_nascimento, email, senha)"
                        + "VALUES ("
                        + "DEFAULT, "
                        +"'"+ o.getNome() +"'," + "'"+ o.getIdade() +"'," + "'"+ o.getEmail() +"'," + "'"+ o.getSenha() +"')";
            }
            

            int resultado = st.executeUpdate(sql);
            
            return true;
        }
        catch (Exception e){
            System.out.println("Erro ao se cadastrar = " + e);
            return false;
        }
    }
    
    public boolean Atualizar(pessoas o, int id) {
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "";
            if (o.getId_pessoas()== 0){
                sql = "UPDATE public.pessoa"
                        + " SET  "
                        +"nome = '"+ o.getNome() +"'," + "data_nascimento = '"+ o.getIdade() +"'," + "email = '"+ o.getEmail() +"'," + "senha = '"+ o.getSenha() +"'"
                        + " WHERE id_pessoa = "+ id;
            }
                        

            int resultado = st.executeUpdate(sql);
            
            return true;
        }
        catch (Exception e){
            System.out.println("Erro ao se cadastrar = " + e);
            return false;
        }
    }
    
    public boolean salvarFoto(pessoas p) {
        boolean retorno = false;

        String sql = "UPDATE pessoa "
                    + "SET foto = ?"
                    + "WHERE id_pessoa = " +"" + p.getId_pessoas() + "";
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setBytes(1, p.getFoto());
             pst.executeUpdate();
             retorno = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return retorno;
    }
    
    public pessoas buscar(Integer id)
    {
        pessoas retorno = null;
        String sql = "SELECT foto from pessoa where id_pessoa =?";
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        
        try {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                retorno = new pessoas();
                retorno.setFoto(rs.getBytes("foto"));
            
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            retorno = null;
        }
        
        return retorno;
    
    }
    
    public boolean consultarLogin (pessoas o) {
        boolean a = false;
        Criptografia criptografia = new Criptografia ();
        pessoas pessoa = new pessoas ();
        
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "";
            int i = 1;
            String d = criptografia.criptografar(o.getSenha());
            resultado = st.executeQuery("SELECT * FROM pessoa WHERE email = '"+ o.getEmail() +"'");
            pessoa.setSenha(resultado.getString("senha"));
            
            if (pessoa.getSenha().equals(d)){
                a = true;
            }

        }
        catch (Exception e) {
            System.out.println("Erro ao fazer login = "+ e );
            a = false;
        }
        return a;
    }


    @Override
    public boolean atualizar(pessoas o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(int id) {
        boolean a = false;
        Statement st;
        
        try {
            st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE pessoa "
                    + "SET situacao = 'I'"
                    + "WHERE id_pessoa = " +"" +id + "";


            // executa consulta
            resultado = st.executeQuery(sql);
            

        }
        catch (Exception e) {
            a = false;
        }
        return a;
    }


    @Override
    public ArrayList<pessoas> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<pessoas> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean atualizarSituacao(String id, String situacao) {
        boolean a = false;
        Statement st;
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE pessoa "
                    + "SET situacao = '"+ situacao + "'"
                    + "WHERE id_pessoa = " +"" +id + "";


            // executa consulta
            st.executeQuery(sql);
            a = true;
         } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
         }
        return a;
    }
    public boolean atualizarEndereco(String id, String rua, String numero, String bairro, String cidade) {
        boolean a = false;
        Statement st;
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE pessoa "
                    + "SET rua = '"+ rua + "'"
                    + "WHERE id_pessoa = " +"" +id + "";


            // executa consulta
            st.executeQuery(sql);
            
            a = true;
         } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
         }
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE pessoa "
                    + "SET numero_casa = '"+ numero + "'"
                    + "WHERE id_pessoa = " +"" +id + "";


            // executa consulta
            st.executeQuery(sql);
            
            a = true;
         } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
         }
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE pessoa "
                    + "SET bairro = '"+ bairro + "'"
                    + "WHERE id_pessoa = " +"" +id + "";


            // executa consulta
            st.executeQuery(sql);
            
            a = true;
         } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
         }
         
        return a;
    }
    
    public boolean atualizarNomeUser(String id, String nomeUser) {
        boolean a = false;
        Statement st;
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE pessoa "
                    + "SET nome_user = '"+ nomeUser + "'"
                    + "WHERE id_pessoa = " +"'" +id + "'";


            // executa consulta
            st.executeQuery(sql);
            a = true;
         } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
         }
        return a;
    }
    public boolean atualizarSenha(String id, String senha) {
        boolean a = false;
        String x;
        Criptografia crip = new Criptografia ();
        x = crip.criptografar(senha);
        Statement st;
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             
             String sql = "UPDATE pessoa "
                    + "SET senha = '"+ x + "'"
                    + "WHERE id_pessoa = " +"'" +id + "'";


            // executa consulta
            st.executeQuery(sql);
            a = true;
         } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
         }
        return a;
    }
    @Override
    public pessoas consultarId(int id) {
        pessoas pessoa = new pessoas ();
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM pessoa "
                    + "WHERE id_pessoa = " + id;


            // executa consulta
            resultado = st.executeQuery(sql);

            // avanca ResultSet
            if (resultado.next()) {
                pessoa = new pessoas();

                // obtem dados do RS
                pessoa.setId_pessoas(resultado.getInt("id_pessoa"));
                pessoa.setNome(resultado.getString("nome"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
        }
        return pessoa;        
    }
    
    public String consultar_nome (int a){
        String i = null;
        Object[][] dadosTabela = null;
        int lin = 0; 
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT nome "
                    + "FROM pessoa "
                    + "WHERE "
                    + "id_pessoa = " + a + "");
            
            while (resultado.next()) {
                String g = (""+resultado.getString("nome"));
                i = g;
            }

            

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return i;
    }
    
    public String consultarEmail (String a){
        String i = " ";
        Object[][] dadosTabela = null;
        int lin = 0; 
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT nome "
                    + "FROM pessoa "
                    + "WHERE "
                    + "email = '" + a + "'");
            
            while (resultado.next()) {
                String g = (""+resultado.getString("nome"));
                i = g;
            }

            

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return i;
    }
    public int consultarNome2 (String a){
        Statement st;
        int g = 0;
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "SELECT id_pessoa "
                    + "FROM pessoa "
                    + "WHERE nome = " +"'" +a + "'";


            // executa consulta
            resultado = st.executeQuery(sql);
            
            if (resultado.next()) {

                // obtem dados do RS
                a = (""+resultado.getInt("id_pessoa"));
                g = Integer.parseInt(a);
            }
         } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
         }
        return g;
    }
    
    
     public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[11];
        cabecalho[0] = "CÃ³digo";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Data de Nascimento";
        cabecalho[3] = "Email";

        // cria matriz de acordo com nÂº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM pessoa "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%'");

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][11];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM pessoa "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%'" + "ORDER BY situacao,nome ");
            

                DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");

            while (resultado.next()) {
                
                
                dadosTabela[lin][0] = resultado.getInt("id_pessoa");
                dadosTabela[lin][1] = resultado.getString("nome");
                dadosTabela[lin][2] = formatDate.format(resultado.getDate("data_nasc"));
                dadosTabela[lin][3] = resultado.getString("email");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela pessoas...");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao Ã© editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
        });

        // permite seleÃ§Ã£o de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;
//                case 2:
//                    column.setPreferredWidth(14);
//                    break;
            }
        }
    }
}
