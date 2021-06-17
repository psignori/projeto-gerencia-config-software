/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto;

import apoio.conexaoBD;
import javax.swing.JOptionPane;
import negocios.pessoas;
import telas.TelaAlterarCadastro;
import telas.TelaLogin;
import telas.TelaCadastrarPessoas;

/**
 *
 * @author patri
 */
public class Main {
    conexaoBD conexao = new conexaoBD();
    
    
    public static void main (String [] args){

        if (conexaoBD.getInstance() != null) {
            pessoas p = new pessoas (); 
            TelaLogin tl = new TelaLogin ();
            tl.setVisible(true);
            System.out.println("Essa é a aplicação do pai Patrick");
        }else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no banco!");
        }
    }
    
    public boolean abrirTc (){
        TelaCadastrarPessoas tc = new TelaCadastrarPessoas ();
        tc.setVisible(true);
        return true;
    }

    public boolean abrirTl (){
        TelaLogin tl = new TelaLogin();
        tl.setVisible(true);
        return true;
    }
    /*public boolean abrirTp (pessoas p){
        TelaPrincipal1 tp = new TelaPrincipal1 (p);
        tp.setVisible(true);
        return true;
    }*/
}
