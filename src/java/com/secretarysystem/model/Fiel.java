/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Luiz
 */
public class Fiel implements Serializable {
    private static final long serialVersionUID = 1l;
    
    private Long id;
    private String nome;
    private String sexo;
    private Date dataNascimento;
    private String telefone;
    private String bairro;
    private int cep;
    private Cidade cidade;
    private Cidade localNascimento;
    private String estadoCivil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    
    
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Cidade getLocalNascimento() {
        return localNascimento;
    }

    public void setLocalNascimento(Cidade localNascimento) {
        this.localNascimento = localNascimento;
    }
    
    

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    
    
}
