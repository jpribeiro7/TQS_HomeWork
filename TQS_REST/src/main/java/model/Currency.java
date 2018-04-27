/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Pedro
 */
public class Currency {
    private String name;
    private Map<String, Float> conversionRate;
    
    public Currency(String name, Map<String, Float> conversionRate){
        this.name = name;
        this.conversionRate = conversionRate;
    }
    
    public String getName(){
        return name;
    }
    
    public Map<String, Float> getConversionRate(){
        return conversionRate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.conversionRate);
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
        final Currency other = (Currency) obj;
        if (!this.name.equals(other.name)) {
            return false;
        }
        for(Map.Entry<String,Float> entry: this.conversionRate.entrySet()){
            System.out.println(entry.getKey()+" - "+entry.getValue());
        }
        
        for(Map.Entry<String,Float> entry: other.conversionRate.entrySet()){
            System.out.println(entry.getKey()+" - "+entry.getValue());
        }
        if(!this.conversionRate.equals(other.conversionRate)){
            return false;
        }
        return true;
    }
    
    
}
