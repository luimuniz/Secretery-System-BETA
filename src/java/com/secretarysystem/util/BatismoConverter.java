/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.util;

import com.secretarysystem.bean.FielBean;
import com.secretarysystem.bean.PedidoBatismoBean;
import com.secretarysystem.model.Batismo;
import com.secretarysystem.model.Cidade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Luiz
 */
@FacesConverter("batismo")
public class BatismoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null){
            Long codigo = Long.valueOf(value);
            
            for(Batismo batismo : PedidoBatismoBean.batismos){
                if(codigo.equals(batismo.getId())){
                    return batismo;
                }
            }
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if(value != null && !value.equals("")){
           Batismo batismo = (Batismo)value;
           return String.valueOf(batismo.getId());
       }
       return null;
    }
    
}
