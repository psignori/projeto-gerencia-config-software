/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.conexaoBD;
import apoio.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import negocios.despesas;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author patri
 */
public class despesasDAO {
    private ResultSet resultado = null;


    public boolean salvar(despesas d, int id) {
        try {
            Date data = new Date();
            
            SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
            
            String dat = formatarDate.format(data);
            String mo = "Saída";
            
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "";
            if (d.getId_despesa()== 0){
                sql = "INSERT INTO public.despesas (id_despesa, nome, valor, descricao, id_pessoa, data, mov)"
                        + "VALUES ("
                        + "DEFAULT, "
                        +"'"+ d.getNome()+"'," +  d.getValor() +","+ "'"+ d.getDescricao()+"'," + id +", '"+ dat +"', '"+ mo +"')";
            }
            

            int resultado = st.executeUpdate(sql);
            
            return true;
        }
        catch (Exception e){
            System.out.println("Erro ao se cadastrar = " + e);
            return false;
        }
    }
        

    public boolean atualizar(despesas d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean excluir(int id) {
        boolean a = false;
        Statement st;
        
        try {
            st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "DELETE FROM public.despesas "
                    + "WHERE id_despesa = " +"" +id + "";


            // executa consulta
            st.executeQuery(sql);
            

        }
        catch (Exception e) {
            a = false;
        }
        return a;
    }
    

    public ArrayList<despesas> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<despesas> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public despesas consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int consultar_id (String a){
        int i = 0;
        Object[][] dadosTabela = null;
        int lin = 0; 
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT id_despesa "
                    + "FROM despesas "
                    + "WHERE "
                    + "nome ILIKE '%" + a + "%'");
            
            while (resultado.next()) {
                String g = (""+resultado.getString("id_despesa"));
                i = Integer.parseInt(g);
            }

            

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return i;
    }
    public despesas consultar (int a){
        despesas de = new despesas();
        int lin = 0; 
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * "
                    + "FROM despesas "
                    + "WHERE "
                    + "id_despesa = " + a + "";
             // executa consulta
            resultado = st.executeQuery(sql);

            // avanca ResultSet
            if (resultado.next()) {
                de.setNome(resultado.getString("nome"));
                de.setDescricao(resultado.getString("descricao"));
                de.setValor(resultado.getDouble("valor"));
            }
                        
        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return de;
    }
    public double soma_despesas (int id){
        despesas de = new despesas();
        double soma = 0; 
        double x = 0;
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT valor "
                    + "FROM despesas WHERE id_pessoa = "+ id;
             // executa consulta
            resultado = st.executeQuery(sql);

            // avanca ResultSet
            while (resultado.next()) {
                de.setValor(resultado.getDouble("valor"));
                x = de.getValor();
                soma = soma + x;
            }
                        
        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return soma;
    }
        
    public boolean atualizarCadastro(despesas d, int id) {
        boolean a = false;
        Statement st;
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE public.despesas "
                    + "SET nome  = '"+ d.getNome() + "', valor = "+ d.getValor() + ", descricao = '" + d.getDescricao() + "'"
                    + " WHERE id_despesa = " +"" +id + "";
             a = true;

            // executa consulta
            st.executeQuery(sql);
            
            
         } catch (Exception e) {
         }
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE public.despesas "
                    + "SET descricao = "+"'"+ d.getDescricao() + "'"
                    + " WHERE id_despesa = " +"" +d.getId_despesa() + "";

             a = true;
            // executa consulta
            st.executeQuery(sql);
            
            
         } catch (Exception e) {
         }
         try {
             st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "UPDATE public.despesas "
                    + "SET valor = "+""+ d.getValor() + ""
                    + " WHERE id_despesa = " +"" + d.getId_despesa() + "";

             a = true;
            // executa consulta
            st.executeQuery(sql);
            
            
         } catch (Exception e) {
         }
         
        return a;
    }
    
    
   public void popularTabela_tela_principal2(JTable tabela, int id) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Id";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Valor:";
        cabecalho[3] = "Descrição";


        // cria matriz de acordo com nÂº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM public.despesas "
                    + "WHERE  "
                    + "id_pessoa = " + id + "");

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][4];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        
        int lin = 0;
        
        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM public.despesas "
                    + "WHERE  "
                    + "id_pessoa = " + id + " " + " ORDER BY nome ");
            

            while (resultado.next()) {
                
                dadosTabela[lin][0] = resultado.getInt("id_despesa");
                dadosTabela[lin][1] = resultado.getString("nome");
                dadosTabela[lin][2] = resultado.getString("valor");
                dadosTabela[lin][3] = resultado.getString("descricao");
                


                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela despesas...");
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
                    column.setPreferredWidth(10);
                    break;
                case 1:
                    column.setPreferredWidth(50);
                    break;
                case 2:
                    column.setPreferredWidth(20);
                    break;
                case 3:
                    column.setPreferredWidth(200);
                    break;
            }
        }
    }
    
    public void popularTabela_tela_principal(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Nome";
        cabecalho[1] = "Valor:";
        cabecalho[2] = "Descrição";


        // cria matriz de acordo com nÂº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM public.despesas "
                    + "WHERE  "
                    + "nome ILIKE '%" + criterio + "%'");

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][4];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM public.despesas "
                    + "WHERE  "
                    + "nome ILIKE'%" + criterio + "%'" + " ORDER BY nome ");
            

            while (resultado.next()) {

                dadosTabela[lin][0] = resultado.getString("nome");
                dadosTabela[lin][1] = resultado.getString("valor");
                dadosTabela[lin][2] = resultado.getString("descricao");
                


                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela despesas...");
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
                    column.setPreferredWidth(50);
                    break;
                case 1:
                    column.setPreferredWidth(20);
                    break;
                case 2:
                    column.setPreferredWidth(200);
                    break;
            }
        }
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Nome";
        cabecalho[1] = "Valor";
        cabecalho[2] = "Descrição";


        // cria matriz de acordo com nÂº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM despesas "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%'");

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][3];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * "
                    + "FROM despesas "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%'" + "ORDER BY nome ");
            

            while (resultado.next()) {

                dadosTabela[lin][0] = resultado.getString("nome");
                dadosTabela[lin][1] = resultado.getString("valor");
                dadosTabela[lin][2] = resultado.getString("descricao");


                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela despesas...");
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
                    column.setPreferredWidth(50);
                    break;
                case 1:
                    column.setPreferredWidth(20);
                    break;
                case 2:
                    column.setPreferredWidth(200);
                    break;
            }
        }
    }
}
