/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

/**
 *
 * @author patri
 */
public class tratamentoIdade {
   public int calculaIdade(String dt_nasc) {

    // Data de hoje.
    GregorianCalendar hoje = new GregorianCalendar();
    int diah = hoje.get(Calendar.DAY_OF_MONTH);
    int mesh = hoje.get(Calendar.MONTH) + 1;
    int anoh = hoje.get(Calendar.YEAR);

    // Data do nascimento.
    int dian = Integer.valueOf(dt_nasc.substring(0,2));
    int mesn = Integer.valueOf(dt_nasc.substring(3,5));
    int anon = Integer.valueOf(dt_nasc.substring(6,10));

    // Idade.
    int idade;

    if (mesn < mesh || (mesn == mesh && dian <= diah))
    idade = anoh - anon;
    else
    idade = (anoh - anon)-1;
    
    return (idade);
    }
}
