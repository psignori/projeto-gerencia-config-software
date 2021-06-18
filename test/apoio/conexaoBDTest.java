/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author patri
 */
public class conexaoBDTest {
    
    public conexaoBDTest() {
    }

    @Test
    public void testGetInstance() {
    }

    @Test
    public void testGetConnection() {
        System.out.println("teste de conexão");
        conexaoBD con = new conexaoBD ();
        assertNotEquals(con, con.getConnection());
    }

    @Test
    public void testShutDown() {
    }
    
}
