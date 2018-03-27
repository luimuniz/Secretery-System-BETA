/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.dao;

import com.secretarysystem.factory.ConexaoFactory;
import com.secretarysystem.model.Cidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luiz
 */
public class CidadeDAO {
    
    public void salvar(Cidade cidade) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO cidade ");
        sql.append("(nome, estado) ");
        sql.append("VALUES (?, ?)");
        
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        stmt.setString(1, cidade.getNome());
        stmt.setString(2, cidade.getEstado());
        stmt.executeUpdate();
    }
    
    public ArrayList<Cidade> listaCidades() throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, nome, estado ");
        sql.append("FROM cidade ");
        sql.append("ORDER BY id ASC");
        
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Cidade> lista = new ArrayList<>();
        
        while(rs.next()){
            Cidade cidade = new Cidade();
            cidade.setId(rs.getLong("id"));
            cidade.setNome(rs.getString("nome"));
            cidade.setEstado(rs.getString("estado"));
            lista.add(cidade);
        }
        return lista;
    }
    
}
