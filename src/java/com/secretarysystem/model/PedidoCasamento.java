/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Luiz
 */
public class PedidoCasamento implements Serializable {
    private static final long serialVersionUID = 1l;
    
    private Long id;
    private Fiel fiel;
    private Casamento certidao;
    private String status;
    private Date dataPedido;
    private float valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fiel getFiel() {
        return fiel;
    }

    public void setFiel(Fiel fiel) {
        this.fiel = fiel;
    }

    public Casamento getCertidao() {
        return certidao;
    }

    public void setCertidao(Casamento certidao) {
        this.certidao = certidao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
    
}
