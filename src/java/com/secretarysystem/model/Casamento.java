/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.model;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Luiz
 */
public class Casamento implements Serializable {
    private static final long serialVersionUID = 1l;
    
    private Long id;
    Fiel noivo;
    Fiel noiva;
    Date dataNascimentoNoivo;
    Cidade cidadeNascimentoNoivo;
    Date dataNascimentoNoiva;
    Cidade cidadeNascimentoNoiva;
    String testemunha1;
    String testemunha2;
    float valor;
    Date dataCasamento;
    String status;
    String cidade;
    Usuario paroco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fiel getNoivo() {
        return noivo;
    }

    public void setNoivo(Fiel noivo) {
        this.noivo = noivo;
    }

    public Fiel getNoiva() {
        return noiva;
    }

    public void setNoiva(Fiel noiva) {
        this.noiva = noiva;
    }

    public Date getDataNascimentoNoivo() {
        return dataNascimentoNoivo;
    }

    public void setDataNascimentoNoivo(Date dataNascimentoNoivo) {
        this.dataNascimentoNoivo = dataNascimentoNoivo;
    }

    public Cidade getCidadeNascimentoNoivo() {
        return cidadeNascimentoNoivo;
    }

    public void setCidadeNascimentoNoivo(Cidade cidadeNascimentoNoivo) {
        this.cidadeNascimentoNoivo = cidadeNascimentoNoivo;
    }

    public Date getDataNascimentoNoiva() {
        return dataNascimentoNoiva;
    }

    public void setDataNascimentoNoiva(Date dataNascimentoNoiva) {
        this.dataNascimentoNoiva = dataNascimentoNoiva;
    }

    public Cidade getCidadeNascimentoNoiva() {
        return cidadeNascimentoNoiva;
    }

    public void setCidadeNascimentoNoiva(Cidade cidadeNascimentoNoiva) {
        this.cidadeNascimentoNoiva = cidadeNascimentoNoiva;
    }
   
    public String getTestemunha1() {
        return testemunha1;
    }

    public void setTestemunha1(String testemunha1) {
        this.testemunha1 = testemunha1;
    }

    public String getTestemunha2() {
        return testemunha2;
    }

    public void setTestemunha2(String testemunha2) {
        this.testemunha2 = testemunha2;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getDataCasamento() {
        return dataCasamento;
    }

    public void setDataCasamento(Date dataCasamento) {
        this.dataCasamento = dataCasamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Usuario getParoco() {
        return paroco;
    }

    public void setParoco(Usuario paroco) {
        this.paroco = paroco;
    }
    
    
    
    
}
