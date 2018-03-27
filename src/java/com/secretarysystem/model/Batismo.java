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
public class Batismo implements Serializable {
    private static final long serialVersionUID = 1l;
    
    private Long id;
    private Fiel fiel;
    private String nomePai;
    private String nomeMae;
    private String nomePadrinho;
    private String nomeMadrinha;
    private String status;
    private Date   dataBatismo;
    private Date   dataNascimento;
    private String cidadeBatismo;
    private float valor;
    private Cidade cidadeNascimento;
    private Usuario paroco;

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

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePadrinho() {
        return nomePadrinho;
    }

    public void setNomePadrinho(String nomePadrinho) {
        this.nomePadrinho = nomePadrinho;
    }

    public String getNomeMadrinha() {
        return nomeMadrinha;
    }

    public void setNomeMadrinha(String nomeMadrinha) {
        this.nomeMadrinha = nomeMadrinha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataBatismo() {
        return dataBatismo;
    }

    public void setDataBatismo(Date dataBatismo) {
        this.dataBatismo = dataBatismo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidadeBatismo() {
        return cidadeBatismo;
    }

    public void setCidadeBatismo(String cidadeBatismo) {
        this.cidadeBatismo = cidadeBatismo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Cidade getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(Cidade cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public Usuario getParoco() {
        return paroco;
    }

    public void setParoco(Usuario paroco) {
        this.paroco = paroco;
    }
    
    
}
