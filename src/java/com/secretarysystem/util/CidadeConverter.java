/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.util;

import com.secretarysystem.bean.FielBean;
import com.secretarysystem.model.Cidade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Luiz
 */
@FacesConverter("cidade")
public class CidadeConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null){
            Long codigo = Long.valueOf(value);
            
            for(Cidade cidade : FielBean.cidades){
                if(codigo.equals(cidade.getId())){
                    return cidade;
                }
            }
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if(value != null && !value.equals("")){
           Cidade cidade = (Cidade)value;
           return String.valueOf(cidade.getId());
       }
       return null;
    }
    
}
