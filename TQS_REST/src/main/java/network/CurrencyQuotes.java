/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pedro
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CurrencyQuotes {
    public Map<String, Double> quotes = new HashMap<>();
 
    public Map<String, Double> getQuotes() {
        return quotes;
    }
 
    public void setQuotes(Map<String, Double> conversions) {
        this.quotes = conversions;
    }
}
