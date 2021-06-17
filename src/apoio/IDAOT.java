/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.util.ArrayList;

/**
 *
 * @author patri
 */
public interface IDAOT <T>{
    public boolean salvar(T o);

    public boolean atualizar(T o);

    public boolean excluir(int id);

    public ArrayList<T> consultarTodos();

    public ArrayList<T> consultar(String criterio);

    public T consultarId(int id);    
}
