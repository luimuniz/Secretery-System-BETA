/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.bean;

import com.secretarysystem.dao.BatismoDAO;
import com.secretarysystem.dao.CasamentoDAO;
import com.secretarysystem.dao.CidadeDAO;
import com.secretarysystem.dao.FielDAO;
import com.secretarysystem.dao.UsuarioDAO;
import com.secretarysystem.model.Batismo;
import com.secretarysystem.model.Cidade;
import com.secretarysystem.model.Fiel;
import com.secretarysystem.model.Usuario;
import com.secretarysystem.util.JSFUtil;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;

/**
 *
 * @author Luiz
 */
@ManagedBean
@ViewScoped
public class BatismoBean implements Serializable{
   private static final long serialVersionUID = 1l; 
   
   Batismo batismo = new Batismo();
   private Fiel fiel;
   public static  List<Fiel> fieis = new ArrayList<>();
   public static  List<Cidade> cidades = new ArrayList<>();
   public static  List<Usuario> parocos = new ArrayList<>();
   private ArrayList<Batismo> batismos;
    
    static{
       FielDAO dao = new FielDAO();
       CidadeDAO dao2 = new CidadeDAO();
       UsuarioDAO dao3 = new UsuarioDAO();
        try {
            parocos = dao3.listaParocos();
            fieis = dao.listaFieis();
            cidades = dao2.listaCidades();
            System.out.println("Sucesso ao listar os fieis,cidades,parocos");
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os fieis");
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

    public List<Fiel> sugerirFieis(String consulta) {
		List<Fiel> fieisSugeridos = new ArrayList<>();
		
		for (Fiel fiel : this.fieis) {
			if (fiel.getNome().toLowerCase().startsWith(consulta.toLowerCase())) {
				fieisSugeridos.add(fiel);
			}
		}
		
		return fieisSugeridos;
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


    public Batismo getBatismo() {
        return batismo;
    }

    public void setBatismo(Batismo batismo) {
        this.batismo = batismo;
    }

    public Fiel getFiel() {
        return fiel;
    }

    public void setFiel(Fiel fiel) {
        this.fiel = fiel;
    }

    public static List<Fiel> getFieis() {
        return fieis;
    }

    public static void setFieis(List<Fiel> fieis) {
        BatismoBean.fieis = fieis;
    }

    public static List<Cidade> getCidades() {
        return cidades;
    }

    public static void setCidades(List<Cidade> cidades) {
        BatismoBean.cidades = cidades;
    }

    public static List<Usuario> getParocos() {
        return parocos;
    }

    public static void setParocos(List<Usuario> parocos) {
        BatismoBean.parocos = parocos;
    }

    public ArrayList<Batismo> getBatismos() {
        return batismos;
    }
    
    
@PostConstruct
    public void prepararPesquisa(){
        try{
        BatismoDAO dao = new BatismoDAO();
           batismos = dao.listaBatismos();
        } catch (SQLException ex){
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }
  public void novoBatismo(){
        try {
            System.out.println("Teste Batismo");
             BatismoDAO dao = new BatismoDAO();
             batismo.setCidadeBatismo("Alvorada do Norte");
              dao.salvar(batismo);
            JSFUtil.adicionarMensagenSucesso("Batismo salvo com sucesso !");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }
  
   public void editarBatismo(){
        try {
            System.out.println("Teste");
          
           
            BatismoDAO dao = new BatismoDAO();
          
 
              dao.editar(batismo);
            JSFUtil.adicionarMensagenSucesso("Batismo editado com sucesso!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }
 
   
}
