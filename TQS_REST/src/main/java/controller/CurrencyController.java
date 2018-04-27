/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Pedro
 */
import model.CurrencyList;
import model.Currency;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.CurrencyService;

/**
 *
 * @author Pedro
 */
@RestController
@CrossOrigin(origins = "http://localhost:8383")
public class CurrencyController {
    
    @Autowired
    private CurrencyService currencyService;
    
    @RequestMapping(value = "/all",method = GET)
    public CurrencyList getAllCurrencies(){
        return currencyService.getAllCurrencies();
    }
    
    @RequestMapping(value="/currency",method = GET)
    public Currency getCurrency(@RequestParam(value="source", required = true)String source){
        return currencyService.getCurrency(source);
    }
    
    @RequestMapping(value="/conversion",method = GET)
    public String getConversion(@RequestParam(value="source", required = true)String source,@RequestParam(value="to", required = true)String to,@RequestParam(value="amount", defaultValue="1")Float amount){
        return currencyService.convert(source, to, amount);
    }
    
    
    public void setCurrencyService(){
        this.currencyService = new CurrencyService();
    }
}
