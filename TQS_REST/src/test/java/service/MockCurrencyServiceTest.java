/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Currency;
import model.CurrencyList;
import network.CurrencyQuotes;
import network.ListCurrency;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Pedro
 */
@RunWith(SpringRunner.class )
@SpringBootTest(classes = CurrencyService.class)
public class MockCurrencyServiceTest {
    
   @Autowired
    private CurrencyService currencyService;

    @MockBean
    private Map<String,Currency> currencyConversion;

    @MockBean
    private List<String> allCurrencies;
    @Test
    public void testGetRightCurrencies() {
        Map <String, Float> usd_conversion = new HashMap<>();
        usd_conversion.put("USDEUR",0.8f);
        Currency currency = new Currency("USD",usd_conversion );
        Map <String,Currency> simulatedCache = new HashMap<>();
        simulatedCache.put("USD", currency);
        
        
        Mockito.when(this.currencyConversion.get(currency)).thenReturn(currency);
        
        //assegurar que consulta a cache com que se quer comparar
        currencyService.setLastApiConsult(new Date());
        currencyService.setCurrencyConversion(simulatedCache);

        assertTrue(currency.equals(currencyService.getCurrency("USD")));
        
    }

    
    @Test
    public void testGetAllCurrencies() {
        List<String> currency_list = new ArrayList<>();
        currency_list.add("USD - United States Dollar");
        CurrencyList list = new CurrencyList(currency_list);
     
        Mockito.when(this.allCurrencies.get(0)).thenReturn("USD - United States Dollar");
        
        //assegurar que consulta a cache com que se quer comparar
        currencyService.setLastApiConsult(new Date());
        currencyService.setCurrencyList(currency_list);

        assertTrue(list.equals(currencyService.getAllCurrencies()));
        
    }
    
}
