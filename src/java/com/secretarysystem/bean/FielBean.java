/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.bean;

import com.secretarysystem.dao.CidadeDAO;
import com.secretarysystem.dao.FielDAO;
import com.secretarysystem.model.Cidade;
import com.secretarysystem.model.Fiel;
import com.secretarysystem.util.JSFUtil;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Luiz
 */
@ManagedBean
@ViewScoped
public class FielBean implements Serializable{
    private static final long serialVersionUID = 1l;
    
    private Fiel fiel = new Fiel();
    private Cidade cidade;
    private Calendar calendar;
    private Boolean editar = true;
    private HtmlInputText inputNome;
    private ArrayList<Fiel> fieis;
    public static  List<Cidade> cidades = new ArrayList<>();
    private HtmlInputText inputCidade;
    
    static{
       CidadeDAO dao = new CidadeDAO();
        try {
            cidades = dao.listaCidades();
            System.out.println("Sucesso ao listar as cidades");
        } catch (SQLException ex) {
            System.out.println("Erro ao listar as cidades");
            Logger.getLogger(FielBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
   
    
    public List<Cidade> sugerirCidades(String consulta) {
		List<Cidade> cidadesSugeridas = new ArrayList<>();
		
		for (Cidade cidade : this.cidades) {
			if (cidade.getNome().toLowerCase().startsWith(consulta.toLowerCase())) {
				cidadesSugeridas.add(cidade);
			}
		}
		
		return cidadesSugeridas;
	}

    public Fiel getFiel() {
        return fiel;
    }

    public void setFiel(Fiel fiel) {
        this.fiel = fiel;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public HtmlInputText getInputCidade() {
        return inputCidade;
    }

    public void setInputCidade(HtmlInputText inputCidade) {
        this.inputCidade = inputCidade;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public HtmlInputText getInputNome() {
        return inputNome;
    }

    public void setInputNome(HtmlInputText inputNome) {
        this.inputNome = inputNome;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public ArrayList<Fiel> getFieis() {
        return fieis;
    }

    public void setFieis(ArrayList<Fiel> fieis) {
        this.fieis = fieis;
    }

    public Boolean getEditar() {
        return editar;
    }

    public void ativarEdicao() {
        this.editar = this.editar != true;
    }
    
    
    public void onRowSelect(SelectEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("cadfiel.xhtml");
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
    
    @PostConstruct
    public void prepararPesquisa(){
        try{
        FielDAO dao = new FielDAO();
        fieis = dao.listaFieis2();
        } catch (SQLException ex){
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }
     public void novoFiel(){
        try {
            System.out.println("Teste");
           
            fiel.setTelefone(fiel.getTelefone().replaceAll("[(,),-]", ""));
           
            FielDAO dao = new FielDAO();
              System.out.print("Nome Fiel: " + fiel.getNome());
 
              dao.salvar(fiel);
            JSFUtil.adicionarMensagenSucesso("Fiel salvo com sucesso!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }
    public void editarFiel(){
        try {
            System.out.println("Teste");
           
            fiel.setTelefone(fiel.getTelefone().replaceAll("[(,),-]", ""));
           
            FielDAO dao = new FielDAO();
              System.out.print("Nome Fiel: " + fiel.getNome());
 
              dao.editar(fiel);
            JSFUtil.adicionarMensagenSucesso("Fiel editado com sucesso!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }

}
    

