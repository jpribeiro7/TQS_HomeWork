/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Pedro
 */
public class CurrencyList {
    private List<String> currencies;
    
    public CurrencyList( List<String> currencies){
        this.currencies = currencies;
    }
    
    public List<String> getCurrencyList(){
        return this.currencies;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.currencies);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CurrencyList other = (CurrencyList) obj;
        if (!Objects.equals(this.currencies, other.currencies)) {
            return false;
        }
        return true;
    }
    
}
