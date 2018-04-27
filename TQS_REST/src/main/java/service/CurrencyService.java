/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import model.Currency;
import model.CurrencyList;
import network.CurrencyQuotes;
import network.ListCurrency;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Pedro
 */
@Service
public class CurrencyService {
    private static final String BASIC_URL = "http://apilayer.net/api/";
    private static final String ACCESS_KEY = "420d7644f16611a56d7a34557eee56fa";

    //cache
    private Map<String,Currency> currencyConversion = new HashMap<>();
    //data e hora da ultima consulta feita à api
    private Date lastApiConsult = new Date();
    
    private List<String> currencyList;
    
    private ListCurrency listCurrency;
    private CurrencyQuotes currencyQuotes;
    
    public void setListCurrency(ListCurrency listCurrency){
        this.listCurrency = listCurrency;
    }
    
    public void setCurrencyQuotes(CurrencyQuotes currencyQuotes){
        this.currencyQuotes = currencyQuotes;
    }
    
    
    public CurrencyList getAllCurrencies(){
        if(!currencyList.isEmpty() && !timeToRefresh()){
            return new CurrencyList(currencyList);
        }
        RestTemplate restTemplate = new RestTemplate();
        listCurrency = restTemplate.getForObject(BASIC_URL+"list?access_key="+ACCESS_KEY, ListCurrency.class);
        List<String> currencies = new ArrayList<>();
        listCurrency.getList().forEach((k,v)-> currencies.add(k + " - "+v));
        currencyList = currencies;
        return new CurrencyList(currencyList);
    }

    public Currency getCurrency(String source) {
        if(currencyConversion.containsKey(source) && !timeToRefresh()){
            return currencyConversion.get("USD");
        }
        RestTemplate restTemplate = new RestTemplate();
        currencyQuotes = restTemplate.getForObject(BASIC_URL+"live?access_key="+ACCESS_KEY+"&source=", CurrencyQuotes.class);
        Map<String, Float> currencies = new HashMap<>();
       
        currencyQuotes.getQuotes().forEach((k,v)-> currencies.put(k,Float.parseFloat(v+"")));   
        Currency current = new Currency(source,currencies);
        currencyConversion.put(source,current);
        return current;
    }
    
    public String convert(String source, String to, float amount){
        Currency current = null;
        
        //atualiza a cache de dois em dois minutos
        //se estiver guardado em cache, usa esse valor, caso contrário consulta a api 
        if(currencyConversion.containsKey(source) && !timeToRefresh()){
            current = currencyConversion.get(source);
        }else{
            currencyConversion = new HashMap<>();
            current = getCurrency(source);
            lastApiConsult = new Date();
        }
        float rate = 1;
        boolean flag = false;
        String toReturn;
        for(Map.Entry<String,Float> entry : current.getConversionRate().entrySet()){
            if(entry.getKey().equals(source+to)){
                rate = entry.getValue();
                flag = true;
            }
        }
        if(flag){
            rate = rate*amount;
            toReturn = rate+"";
        }else{
            toReturn = null;
        }
        return toReturn;
    }
    
    public boolean timeToRefresh(){
        
        Date currentTime = new Date();
        
        //getTime devolve o tempo em milisegundos
        long time = currentTime.getTime()/(1000*60)- lastApiConsult.getTime()/((1000*60));
        
        return  time >= 2;
    }
    
    public void setLastApiConsult(Date date){
        this.lastApiConsult = date;
    }
    
    public void setCurrencyConversion (Map <String,Currency> currencyConversion){
        this.currencyConversion = currencyConversion;
    }
    
    public void setCurrencyList(List<String> currencyList){
        this.currencyList = currencyList;
    }
}
