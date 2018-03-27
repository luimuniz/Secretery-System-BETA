/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.bean;

import static com.secretarysystem.bean.BatismoBean.cidades;
import static com.secretarysystem.bean.BatismoBean.fieis;
import static com.secretarysystem.bean.BatismoBean.parocos;
import com.secretarysystem.dao.BatismoDAO;
import com.secretarysystem.dao.CidadeDAO;
import com.secretarysystem.dao.FielDAO;
import com.secretarysystem.dao.PedidoBatismoDAO;
import com.secretarysystem.dao.UsuarioDAO;
import com.secretarysystem.model.Batismo;
import com.secretarysystem.model.Cidade;
import com.secretarysystem.model.Fiel;
import com.secretarysystem.model.PedidoBatismo;
import com.secretarysystem.util.JSFUtil;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Luiz
 */
@ManagedBean
@ViewScoped
public class PedidoBatismoBean implements Serializable {
       
    PedidoBatismo pedido = new PedidoBatismo();
    
    public static  List<Fiel> fieis = new ArrayList<>();
    public static  List<Batismo> batismos = new ArrayList<>();
    private  ArrayList<PedidoBatismo> pedidos ;
   
    static{
       FielDAO dao = new FielDAO();
       BatismoDAO dao2 = new BatismoDAO();
    
        try {
            fieis = dao.listaFieis();
            batismos = dao2.listaBatismos();
            System.out.println("Sucesso ao listar os fieis e batizados");
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os fieis e batizados");
            Logger.getLogger(FielBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
     public List<Batismo> sugerirBatismos(String consulta) {
		List<Batismo> batismosSugeridos = new ArrayList<>();
		
		for (Batismo batismo : this.batismos) {
     
			if (batismo.getFiel().getNome().toLowerCase().startsWith(consulta.toLowerCase())) {
				batismosSugeridos.add(batismo);
			}
		}
		
		return batismosSugeridos;
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

    public PedidoBatismo getPedido() {
        return pedido;
    }

    public void setPedido(PedidoBatismo pedido) {
        this.pedido = pedido;
    }

    public static List<Fiel> getFieis() {
        return fieis;
    }

    public static void setFieis(List<Fiel> fieis) {
        PedidoBatismoBean.fieis = fieis;
    }

    public static List<Batismo> getBatismos() {
        return batismos;
    }

    public static void setBatismos(List<Batismo> batismos) {
        PedidoBatismoBean.batismos = batismos;
    }

    public  ArrayList<PedidoBatismo> getPedidos() {
        return pedidos;
    }
    
    
    @PostConstruct
    public void prepararPesquisa(){
        try{
        PedidoBatismoDAO dao = new PedidoBatismoDAO();
           pedidos = dao.listaPedidosBatismos();
        } catch (SQLException ex){
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }
    
    public void pegarCertidao(){
        System.out.println("Nome: " + pedido.getFiel().getNome());
        PedidoBatismoDAO dao = new PedidoBatismoDAO();
        try {
            Batismo batismo = dao.listaBatismos(pedido.getCertidao().getId());
            pedido.setCertidao(batismo);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoBatismoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void novoPedidoBatismo(){
        try {
            System.out.println("Teste Batismo");
             PedidoBatismoDAO dao = new PedidoBatismoDAO();
             pedido.setDataPedido(new Date());
              dao.salvar(pedido);
            JSFUtil.adicionarMensagenSucesso("Pedido salvo com sucesso !");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }
    
    public void editarPedido(){
        try {
            System.out.println("Teste");
          
           
            PedidoBatismoDAO dao = new PedidoBatismoDAO();
          
 
              dao.editar(pedido);
            JSFUtil.adicionarMensagenSucesso("Pedido editado com sucesso!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }
 
   
}
