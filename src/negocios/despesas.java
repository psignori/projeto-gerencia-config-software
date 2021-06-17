/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

/**
 *
 * @author patri
 */
public class despesas {
    private int id_despesa;
    private String nome;
    private Double valor;
    private String descricao;
    private int id_pessoa;

    public int getId_pessoa() {
        return id_pessoa;
    }
    
    public int getId_despesa() {
        return id_despesa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
