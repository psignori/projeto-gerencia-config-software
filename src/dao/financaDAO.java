/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;

/**
 *
 * @author patri
 */
public class financaDAO {
    public double financa(int id){
    
        despesasDAO dDAO = new despesasDAO();
        receitasDAO rDAO = new receitasDAO();
    
        double receita = rDAO.soma_receitas(id);
        double despesa = dDAO.soma_despesas(id);
    
        double fin = receita - despesa;
        return fin;
    }
}
