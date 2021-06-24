/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patri
 */
public class conexaoBDTest {
    @Test
    public void testGetConnection() {
        System.out.println("teste de conexão");
        conexaoBD con = new conexaoBD ();
        assertNotEquals(con, con.getConnection());
    }    
}
