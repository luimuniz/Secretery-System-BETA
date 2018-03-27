/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.bean;

import com.secretarysystem.dao.CasamentoDAO;
import com.secretarysystem.dao.FielDAO;
import com.secretarysystem.dao.PedidoBatismoDAO;
import com.secretarysystem.dao.PedidoCasamentoDAO;
import com.secretarysystem.model.Casamento;
import com.secretarysystem.model.Fiel;
import com.secretarysystem.model.PedidoCasamento;
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
public class PedidoCasamentoBean implements Serializable {
    
    PedidoCasamento pedido = new PedidoCasamento();
    
    public static  List<Fiel> fieis = new ArrayList<>();
    public static  List<Casamento> casamentos = new ArrayList<>();
    private ArrayList<PedidoCasamento> pedidos;
   
    static{
       FielDAO dao = new FielDAO();
       CasamentoDAO dao2 = new CasamentoDAO();
    
        try {
            fieis = dao.listaFieis();
            casamentos = dao2.listaCasamentos();
            System.out.println("Sucesso ao listar os fieis e casamentos");
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os fieis e casamentos");
            Logger.getLogger(FielBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
     public List<Casamento> sugerirCasamentos(String consulta) {
		List<Casamento> casamentosSugeridos = new ArrayList<>();
		
		for (Casamento casamento : this.casamentos) {
                    String casal = casamento.getNoivo().getNome() + " e " + casamento.getNoiva().getNome();
			if (casal.toLowerCase().startsWith(consulta.toLowerCase())) {
				casamentosSugeridos.add(casamento);
			}
		}
		
		return casamentosSugeridos;
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

    public PedidoCasamento getPedido() {
        return pedido;
    }

    public void setPedido(PedidoCasamento pedido) {
        this.pedido = pedido;
    }

    public static List<Fiel> getFieis() {
        return fieis;
    }

    public static void setFieis(List<Fiel> fieis) {
        PedidoBatismoBean.fieis = fieis;
    }

    public static List<Casamento> getCasamentos() {
        return casamentos;
    }

    public ArrayList<PedidoCasamento> getPedidos() {
        return pedidos;
    }
    
    
@PostConstruct
    public void prepararPesquisa(){
        try{
        PedidoCasamentoDAO dao = new PedidoCasamentoDAO();
           pedidos = dao.listaPedidosCasamentos();
        } catch (SQLException ex){
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }
   
    public void pegarCertidao(){
        System.out.println("Nome: " + pedido.getFiel().getNome());
        PedidoCasamentoDAO dao = new PedidoCasamentoDAO();
        try {
            Casamento casamento = dao.listaCasamentos(pedido.getCertidao().getId());
            pedido.setCertidao(casamento);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoBatismoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void novoPedidoCasamento(){
        try {
            System.out.println("Teste pedido casamento");
             PedidoCasamentoDAO dao = new PedidoCasamentoDAO();
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
          
           
            PedidoCasamentoDAO dao = new PedidoCasamentoDAO();
          
 
              dao.editar(pedido);
            JSFUtil.adicionarMensagenSucesso("Pedido editado com sucesso!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagenErro(ex.getMessage());
        }
    }
   
    
}
