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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import negocios.receitas;

/**
 *
 * @author patri
 */
public class receitasDAO {
    private ResultSet resultado = null;
    private int teste;

    public boolean salvar(receitas r, int id) {
        try {
            Date data = new Date();
            
            SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
            
            String dat = formatarDate.format(data);
            String mo = "Entrada";
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "";
            if (r.getId_receita()== 0){
                sql = "INSERT INTO public.receitas (id_receita, nome, valor, descricao, id_pessoa, data, mov)"
                        + "VALUES ("
                        + "DEFAULT, "
                        +"'"+ r.getNome()+"'," +  r.getValor() +","+ "'"+ r.getDescricao()+"'," + id +", '"+ dat +"', '"+ mo +"')";
            }
            

            int resultado = st.executeUpdate(sql);
            
            return true;
        }
        catch (Exception e){
            System.out.println("Erro ao se cadastrar = " + e);
            return false;
        }
    }
        

    public boolean atualizar(receitas r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean excluir(int id) {
        boolean a = false;
        Statement st;
        
        try {
            st = conexaoBD.getInstance().getConnection().createStatement();
             
             String sql = "DELETE FROM public.receitas "
                    + "WHERE id_receita = " +"" +id + "";


            // executa consulta
            st.executeQuery(sql);
            

        }
        catch (Exception e) {
            a = false;
        }
        return a;
    }
    

    public ArrayList<receitas> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<receitas> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public receitas consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int consultar_id (String a){
        int i = 0;
        Object[][] dadosTabela = null;
        int lin = 0; 
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT id_receita "
                    + "FROM receitas "
                    + "WHERE "
                    + "nome ILIKE '%" + a + "%'");
            
            while (resultado.next()) {
                String g = (""+resultado.getString("id_game"));
                i = Integer.parseInt(g);
            }

            

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return i;
    }
    public double soma_receitas (int id){
        receitas re = new receitas();
        double soma = 0; 
        double x = 0;
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT valor "
                    + "FROM receitas WHERE id_pessoa = " + id ;
             // executa consulta
            resultado = st.executeQuery(sql);

            // avanca ResultSet
            while (resultado.next()) {
                re.setValor(resultado.getDouble("valor"));
                x = re.getValor();
                soma = soma + x;
            }
                        
        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return soma;
    }
    public receitas consultar (int a){
        receitas re = new receitas();
        int lin = 0; 
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * "
                    + "FROM receitas "
                    + "WHERE "
                    + "id_receita = " + a + "";
             // executa consulta
            resultado = st.executeQuery(sql);

            // avanca ResultSet
            if (resultado.next()) {
                re.setNome(resultado.getString("nome"));
                re.setDescricao(resultado.getString("descricao"));
                re.setValor(resultado.getDouble("valor"));
            }
                        
        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        return re;
    }    
    
    public boolean atualizarCadastro(receitas r, int id) {
        boolean a = false;
        try {
            Statement st = conexaoBD.getInstance().getConnection().createStatement();
             
            String sql = "UPDATE public.receitas "
                    + "SET nome = '"+ r.getNome() + "', valor = "+ r.getValor() + ", descricao = '" + r.getDescricao() + "'"
                    + " WHERE id_receita = " +"" +id + "";
            a = true;

            // executa consulta
            int resultado = st.executeUpdate(sql);
            
            
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cadastro = " + e);
            return false;
        }
         
        return a;
    }
    
    public void popularTabela_tela_principal2(JTable tabela, int id) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[1] = "Id";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Valor";
        cabecalho[3] = "Descrição";


        // cria matriz de acordo com nÂº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM public.receitas "
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
                    + "FROM public.receitas "
                    + "WHERE  "
                    + "id_pessoa = " + id + " " + " ORDER BY nome ");
            

            while (resultado.next()) {
                
                dadosTabela[lin][0] = resultado.getInt("id_receita");
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
            System.out.println("problemas para popular tabela receitas...");
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
        cabecalho[2] = "DescriÃ§Ã£o";


        // cria matriz de acordo com nÂº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM public.receitas "
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
                    + "FROM public.receitas "
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
            System.out.println("problemas para popular tabela receitas...");
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
        cabecalho[2] = "DescriÃ§Ã£o";


        // cria matriz de acordo com nÂº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM receitas "
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
                    + "FROM receitas "
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
            System.out.println("problemas para popular tabela receitas...");
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
    public void popularTabela_movimentaçoes(JTable tabela, int id) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Mov";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Valor";
        cabecalho[3] = "Data";
        


        // cria matriz de acordo com nÂº de registros da tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + " select *"
                    + " from despesas"
                    + " WHERE id_pessoa = " + id 
                    + " UNION ALL"
                    + " select *"
                    + " from receitas"
                    + " where id_pessoa = " + id 
                    + " order by data desc");

            resultado.next();

            dadosTabela = new Object[resultado.getInt(1)][4];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        
        int lin = 0;
        
        // efetua consulta na tabela
        try {
            resultado = conexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + " select mov , nome, valor, data"
                    + " from despesas"
                    + " WHERE id_pessoa = " + id 
                    + " UNION ALL"
                    + " select mov ,  nome, valor, data"
                    + " from receitas"
                    + " where id_pessoa = " + id 
                    + " order by data desc");

            while (resultado.next()) {
                
                dadosTabela[lin][0] = resultado.getString("mov");
                dadosTabela[lin][1] = resultado.getString("nome");
                dadosTabela[lin][2] = resultado.getString("valor");
                dadosTabela[lin][3] = resultado.getDate("data");
                


                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela receitas...");
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

        
    }
}
