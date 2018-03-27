/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.bean;

import com.secretarysystem.dao.CasamentoDAO;
import com.secretarysystem.dao.CidadeDAO;
import com.secretarysystem.dao.FielDAO;
import com.secretarysystem.dao.UsuarioDAO;
import com.secretarysystem.model.Casamento;
import com.secretarysystem.model.Cidade;
import com.secretarysystem.model.Fiel;
import com.secretarysystem.model.Usuario;
import com.secretarysystem.util.JSFUtil;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 *
 * @author Luiz
 */
@ManagedBean
@ViewScoped
public class CasamentoBean implements Serializable{
 private final static long serialVersionUID = 1l;
 
 
  public static  List<Fiel> fieis = new ArrayList<>();
  public static  List<Cidade> cidades = new ArrayList<>();
  private ArrayList<Casamento> casamentos;
  private Casamento casamento = new Casamento();
  public static  List<Usuario> parocos = new ArrayList<>(); 
    static{
       UsuarioDAO dao3 = new UsuarioDAO();
       FielDAO dao = new FielDAO();
        CidadeDAO dao2 = new CidadeDAO();
        try {
            parocos = dao3.listaParocos();
            fieis = dao.listaFieisSoleiros();
            cidades = dao2.listaCidades();
            System.out.println("Sucesso ao listar os fieis");
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os fieis");
            Logger.getLogger(FielBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
   
   
    
    public List<Fiel> sugerirFieisSolteiras(String consulta) {
		List<Fiel> fieisSugeridos = new ArrayList<>();
		
		for (Fiel fiel : this.fieis) {
			if (fiel.getNome().toLowerCase().startsWith(consulta.toLowerCase()) && "F".equals(fiel.getSexo())) {
				fieisSugeridos.add(fiel);
			}
		}
		
		return fieisSugeridos;
	}
    
    public List<Fiel> sugerirFieisSolteiros(String consulta) {
		List<Fiel> fieisSugeridos = new ArrayList<>();
		
		for (Fiel fiel : this.fieis) {
			if (fiel.getNome().toLowerCase().startsWith(consulta.toLowerCase()) && "M".equals(fiel.getSexo())) {
				fieisSugeridos.add(fiel);
			}
		}
		
		return fieisSugeridos;
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
    
    public List<Usuario> sugerirParocos(String consulta) {
		List<Usuario> parocosSugeridos = new ArrayList<>();
		
		for (Usuario user : this.parocos) {
			if (user.getNome().toLowerCase().startsWith(consulta.toLowerCase())) {
				parocosSugeridos.add(user);
			}
		}
		
		return parocosSugeridos;
	}




    
  public Date getDataMaxima(){
      GregorianCalendar gc = new GregorianCalendar();
        gc.add(GregorianCalendar.YEAR, -16 );
        Date data = gc.getTime();
        return  data;
  }  

    public static List<Fiel> getFieis() {
        return fieis;
    }

    public static void setFieis(List<Fiel> fieis) {
        CasamentoBean.fieis = fieis;
    }

    public Casamento getCasamento() {
        return casamento;
    }

    public void setCasamento(Casamento casamento) {
        this.casamento = casamento;
    }

    public ArrayList<Casamento> getCasamentos() {
        return casamentos;
    }
    
    
    
    @PostConstruct
    public void prepararPesquisa(){
        try{
        CasamentoDAO dao = new CasamentoDAO();
        casamentos = dao.listaCasamentos();
        } catch (SQLException ex){
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }

     public void novoCasamento(){
        try {
            System.out.println("Teste Casamento");
             CasamentoDAO dao = new CasamentoDAO();
             casamento.setCidade("Alvorada do Norte");
              dao.salvar(casamento);
            JSFUtil.adicionarMensagenSucesso("Casamento salvo com sucesso !");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }
     
      public void editarCasamento(){
        try {
            System.out.println("Teste");
          
           
            CasamentoDAO dao = new CasamentoDAO();
          
 
              dao.editar(casamento);
            JSFUtil.adicionarMensagenSucesso("Casamento editado com sucesso!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }
  
  
}
