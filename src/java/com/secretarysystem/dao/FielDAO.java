/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.dao;

import com.secretarysystem.factory.ConexaoFactory;
import com.secretarysystem.model.Cidade;
import com.secretarysystem.model.Fiel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luiz
 */
public class FielDAO {
    public void salvar(Fiel fiel) throws SQLException{
        StringBuilder sql = new StringBuilder();
        Date data = new Date((fiel.getDataNascimento()).getTime());
        sql.append("INSERT INTO fiel ");
        sql.append("(nome, sexo, datanascimento,telefone, bairro, cep, cidade, estadocivil) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        System.out.println("Data: " + fiel.getDataNascimento());
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        stmt.setString(1, fiel.getNome());
        stmt.setString(2, fiel.getSexo());
        stmt.setDate(3, data);
        stmt.setString(4, fiel.getTelefone());
        stmt.setString(5, fiel.getBairro());
        stmt.setInt(6, fiel.getCep());
        stmt.setLong(7, fiel.getCidade().getId());
        stmt.setString(8, fiel.getEstadoCivil());
        stmt.executeUpdate();
    }
    
    public void editar(Fiel fiel) throws SQLException{
        StringBuilder sql = new StringBuilder();
        Date data = new Date((fiel.getDataNascimento()).getTime());
        sql.append("UPDATE  fiel ");
        sql.append("SET nome=?, sexo=?, datanascimento=?,telefone=?, bairro=?, cep=?, cidade=?, estadocivil=? ");
        sql.append("WHERE id=? ");
        System.out.println("Data: " + fiel.getDataNascimento());
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        stmt.setString(1, fiel.getNome());
        stmt.setString(2, fiel.getSexo());
        stmt.setDate(3, data);
        stmt.setString(4, fiel.getTelefone());
        stmt.setString(5, fiel.getBairro());
        stmt.setInt(6, fiel.getCep());
        stmt.setLong(7, fiel.getCidade().getId());
        stmt.setString(8, fiel.getEstadoCivil());
        stmt.setLong(9, fiel.getId());
        stmt.executeUpdate();
    }
    
    public ArrayList<Fiel> listaFieis() throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM fiel ");
        sql.append("ORDER BY id ASC");
        
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Fiel> lista = new ArrayList<>();
        
        while(rs.next()){
            
            Fiel fiel = new Fiel();
            fiel.setId(rs.getLong("id"));
            fiel.setNome(rs.getString("nome"));
            fiel.setSexo(rs.getString("sexo"));
            fiel.setDataNascimento(rs.getDate("datanascimento"));
            fiel.setTelefone(rs.getString("telefone"));
            fiel.setBairro(rs.getString("bairro"));
            fiel.setCep(rs.getInt("cep"));
            fiel.setEstadoCivil(rs.getString("estadocivil"));
            Long idCidade = rs.getLong("cidade");
            Cidade cidade = new Cidade();
            cidade.setId(idCidade);
            fiel.setCidade(cidade);
            
            fiel.setEstadoCivil("estadocivil");
            lista.add(fiel);
        }
        return lista;
    }
    
    public ArrayList<Fiel> listaFieis2() throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT f.id, f.nome, f.sexo, f.datanascimento, f.telefone,"
                + " f.bairro, f.cep, f.estadocivil, f.cidade, c.nome, c.estado ");
        sql.append("FROM fiel f, cidade c ");
        sql.append("where f.cidade = c.id");
        
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Fiel> lista = new ArrayList<>();
        
        while(rs.next()){
            
            Fiel fiel = new Fiel();
            fiel.setId(rs.getLong(1));
            fiel.setNome(rs.getString(2));
            fiel.setSexo(rs.getString(3));
            fiel.setDataNascimento(rs.getDate(4));
            fiel.setTelefone(rs.getString(5));
            fiel.setBairro(rs.getString(6));
            fiel.setCep(rs.getInt(7));
            fiel.setEstadoCivil(rs.getString(8));
            Long idCidade = rs.getLong(9);
            Cidade cidade = new Cidade();
            cidade.setId(idCidade);
            cidade.setNome(rs.getString(10));
            cidade.setEstado(rs.getString(11));
            fiel.setCidade(cidade);
            
         
            lista.add(fiel);
        }
        return lista;
    }
    
    public ArrayList<Fiel> listaFieisSoleiros() throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM fiel ");
        sql.append("where estadocivil = 'solteiro(a)'");
        
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Fiel> lista = new ArrayList<>();
        
        while(rs.next()){
            
            Fiel fiel = new Fiel();
            fiel.setId(rs.getLong("id"));
            fiel.setNome(rs.getString("nome"));
            fiel.setSexo(rs.getString("sexo"));
            fiel.setDataNascimento(rs.getDate("datanascimento"));
            fiel.setTelefone(rs.getString("telefone"));
            fiel.setBairro(rs.getString("bairro"));
            fiel.setCep(rs.getInt("cep"));
            
            Long idCidade = rs.getLong("cidade");
            Cidade cidade = new Cidade();
            cidade.setId(idCidade);
            fiel.setCidade(cidade);
            
            fiel.setEstadoCivil("estadocivil");
            lista.add(fiel);
        }
        return lista;
    }
}
