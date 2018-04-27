/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Pedro
 */
@SpringBootTest(classes = CurrencyService.class)
public class UnitCurrencyServiceTest {

    @Test
    public void testConvertNoDataFound() {
        System.out.println("convert");
        String source = "AZN";
        String to = "EUR";
        float amount = 10.0F;
        CurrencyService instance = new CurrencyService();
        String expected = null;
        String result = instance.convert(source, to, amount);
        assertEquals(expected, result);
    }
    
    @Test
    public void testConvert() {
        System.out.println("convert");
        String source = "USD";
        String to = "EUR";
        float amount = 10.0F;
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        CurrencyService instance = new CurrencyService();
        Double expResult = 8.21505;

        String result = instance.convert(source, to, amount);
        assertEquals(df.format(expResult), df.format(Double.parseDouble(result)));
    }

    @Test
    public void testTimeToRefresh() {
        System.out.println("time to refresh");
        CurrencyService instance = new CurrencyService();

        //passaram menos de 2 minutos
        Date date = new Date();
        instance.setLastApiConsult(date);
        assertEquals(false, instance.timeToRefresh());

        //passaram 2 minutos
        date.setTime(date.getTime() / (1000 * 60) - 2);
        instance.setLastApiConsult(date);
        assertEquals(true, instance.timeToRefresh());
    }

}
