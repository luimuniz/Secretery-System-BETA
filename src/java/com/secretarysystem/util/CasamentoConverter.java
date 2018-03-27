/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.util;

import com.secretarysystem.bean.FielBean;
import com.secretarysystem.bean.PedidoBatismoBean;
import com.secretarysystem.bean.PedidoCasamentoBean;
import com.secretarysystem.model.Batismo;
import com.secretarysystem.model.Casamento;
import com.secretarysystem.model.Cidade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Luiz
 */
@FacesConverter("casamento")
public class CasamentoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null){
            Long codigo = Long.valueOf(value);
            
            for(Casamento casamento : PedidoCasamentoBean.casamentos){
                if(codigo.equals(casamento.getId())){
                    return casamento;
                }
            }
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if(value != null && !value.equals("")){
           Casamento casamento = (Casamento)value;
           return String.valueOf(casamento.getId());
       }
       return null;
    }
    
}
