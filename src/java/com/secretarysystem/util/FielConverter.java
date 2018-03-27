/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.util;

import com.secretarysystem.bean.BatismoBean;
import com.secretarysystem.model.Fiel;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Luiz
 */
@FacesConverter("fiel")
public class FielConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null){
            Long codigo = Long.valueOf(value);
            
            for(Fiel fiel : BatismoBean.fieis){
                if(codigo.equals(fiel.getId())){
                    return fiel;
                }
            }
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if(value != null && !value.equals("")){
           Fiel fiel = (Fiel)value;
           return String.valueOf(fiel.getId());
       }
       return null;
    }
    
}
