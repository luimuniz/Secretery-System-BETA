/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.bean;

import com.secretarysystem.dao.CidadeDAO;
import com.secretarysystem.model.Cidade;
import com.secretarysystem.util.JSFUtil;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;

/**
 *
 * @author Luiz
 */
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable{
    private static final long serialVersionUID = 1l;
    private Cidade cidade;
    private HtmlInputText nome;
    private HtmlInputText estado;

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public HtmlInputText getNome() {
        return nome;
    }

    public void setNome(HtmlInputText nome) {
        this.nome = nome;
    }

    public HtmlInputText getEstado() {
        return estado;
    }

    public void setEstado(HtmlInputText estado) {
        this.estado = estado;
    }
    
     public void prepararNovaCidade(){
        nome.setValue("");
        estado.setValue("");
        cidade = new Cidade();
        System.out.println("criado o objeto");
    }
    
    
   public void novaCidade(){
        try {
            System.out.println("Teste Cidade");
             CidadeDAO dao = new CidadeDAO();
              dao.salvar(cidade);
            JSFUtil.adicionarMensagenSucesso("Cidade salva com sucesso !");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }

}
