/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.CurrencyController;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Pedro
 */

@RunWith(SpringRunner.class)
public class IntegrationTest {
	
	
    
    @Test
    public void whenAskForConversion_GetTheRightConversion(){
        String source = "USD";
        String to = "EUR";
        Float amount = 10f;
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        Double expResult = 8.3;
        
        CurrencyController currencyController = new CurrencyController();
        currencyController.setCurrencyService();
        String result = currencyController.getConversion(source, to, amount);
        
        assertEquals(df.format(expResult),df.format(Double.parseDouble(result)));
        
    }

    
    
    
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
