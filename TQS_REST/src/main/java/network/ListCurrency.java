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
public class ListCurrency {
    public Map<String, String>  currencies = new HashMap<>();
 
    public Map<String, String> getList() {
        return currencies;
    }
 
    public void setList(Map<String, String> list) {
        this.currencies = list;
    }
}
