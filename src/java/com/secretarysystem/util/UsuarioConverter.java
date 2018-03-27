/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.util;

import com.secretarysystem.bean.BatismoBean;
import com.secretarysystem.bean.FielBean;
import com.secretarysystem.model.Cidade;
import com.secretarysystem.model.Usuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Luiz
 */
@FacesConverter("usuario")
public class UsuarioConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null){
            Long codigo = Long.valueOf(value);
            
            for(Usuario user : BatismoBean.parocos){
                if(codigo.equals(user.getId())){
                    return user;
                }
            }
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if(value != null && !value.equals("")){
           Usuario user = (Usuario)value;
           return String.valueOf(user.getId());
       }
       return null;
    }
    
}
