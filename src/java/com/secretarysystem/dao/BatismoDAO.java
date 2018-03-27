/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.dao;

import com.secretarysystem.factory.ConexaoFactory;
import com.secretarysystem.model.Batismo;
import com.secretarysystem.model.Casamento;
import com.secretarysystem.model.Cidade;
import com.secretarysystem.model.Fiel;
import com.secretarysystem.model.Usuario;
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
public class BatismoDAO {
    
    public void salvar(Batismo batismo) throws SQLException{
        StringBuilder sql = new StringBuilder();
        Date data = new Date((batismo.getDataBatismo()).getTime());
        sql.append("INSERT INTO batismo ");
        sql.append("(batizando, mae,pai, madrinha, padrinho, databatismo, status, datanascimento,localbatismo,localnascimento,paroco,valor) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)");
        //System.out.println("Data: " + fiel.getDataNascimento());
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        stmt.setLong(1, batismo.getFiel().getId());
        stmt.setString(2, batismo.getNomeMae());
        stmt.setString(3, batismo.getNomePai());
        stmt.setString(4, batismo.getNomeMadrinha());
        stmt.setString(5, batismo.getNomePadrinho());
        stmt.setDate(6, data);
        stmt.setString(7, batismo.getStatus());
        stmt.setDate(8, new Date((batismo.getDataNascimento()).getTime()));
        stmt.setString(9, batismo.getCidadeBatismo());
        stmt.setLong(10, batismo.getCidadeNascimento().getId());
        stmt.setLong(11, batismo.getParoco().getId());
        stmt.setFloat(12, batismo.getValor());
        stmt.executeUpdate();
    }
    
     public void editar(Batismo batismo) throws SQLException{
        StringBuilder sql = new StringBuilder();
        Date data = new Date((batismo.getDataBatismo()).getTime());
        sql.append("UPDATE batismo ");
        sql.append("SET batizando=?, mae=?, pai=?, madrinha=?, padrinho=?, databatismo=?, status=?, datanascimento=?,"
                + " localbatismo=?, localnascimento=?,  paroco=?, valor=? ");
        sql.append("WHERE id=?");
        //System.out.println("Data: " + fiel.getDataNascimento());
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        stmt.setLong(1, batismo.getFiel().getId());
        stmt.setString(2, batismo.getNomeMae());
        stmt.setString(3, batismo.getNomePai());
        stmt.setString(4, batismo.getNomeMadrinha());
        stmt.setString(5, batismo.getNomePadrinho());
        stmt.setDate(6, data);
        stmt.setString(7, batismo.getStatus());
        stmt.setDate(8, new Date((batismo.getDataNascimento()).getTime()));
        stmt.setString(9, batismo.getCidadeBatismo());
        stmt.setLong(10, batismo.getCidadeNascimento().getId());
        stmt.setLong(11, batismo.getParoco().getId());
        stmt.setFloat(12, batismo.getValor());
        stmt.setLong(13, batismo.getId());
        stmt.executeUpdate();
    }
   
    
    public ArrayList<Batismo> listaBatismos() throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b.id, b.batizando, b.pai, b.mae, b.padrinho, b.madrinha, ");
        sql.append("b.datanascimento, b.databatismo, b.localbatismo, b.status, b.paroco, b.valor, b.localnascimento, ");
        sql.append("f.nome, u.nome, c.nome, c.estado ");
        sql.append("FROM batismo b ");
        sql.append("INNER JOIN fiel f ON f.id=b.batizando ");
        sql.append("INNER JOIN cidade c ON c.id=b.localnascimento ");
        sql.append("INNER JOIN usuario u ON u.id=b.paroco ");
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Batismo> lista = new ArrayList<>();
        
        while(rs.next()){
            Batismo batismo = new Batismo();
            Fiel fiel = new Fiel();
            Cidade cidade = new Cidade();
            Usuario paroco = new Usuario();
            
            batismo.setId(rs.getLong(1));
            fiel.setId(rs.getLong(2));
            batismo.setNomePai(rs.getString(3));
            batismo.setNomeMae(rs.getString(4));
            batismo.setNomePadrinho(rs.getString(5));
            batismo.setNomeMadrinha(rs.getString(6));
            batismo.setDataNascimento(rs.getDate(7));
            batismo.setDataBatismo(rs.getDate(8));
            batismo.setCidadeBatismo(rs.getString(9));
            batismo.setStatus(rs.getString(10));
            paroco.setId(rs.getLong(11));
            batismo.setValor(rs.getFloat(12));
            cidade.setId(rs.getLong(13));
            fiel.setNome(rs.getString(14));
            paroco.setNome(rs.getString(15));
            cidade.setNome(rs.getString(16));
            cidade.setEstado(rs.getString(17));
            
            batismo.setFiel(fiel);
            batismo.setCidadeNascimento(cidade);
            batismo.setParoco(paroco);
            lista.add(batismo);
           
        }
        return lista;
    }
}
