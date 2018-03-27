/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.dao;

import com.secretarysystem.factory.ConexaoFactory;
import com.secretarysystem.model.Batismo;
import com.secretarysystem.model.Cidade;
import com.secretarysystem.model.Fiel;
import com.secretarysystem.model.PedidoBatismo;
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
public class PedidoBatismoDAO {
    Batismo batismo = new Batismo();
    public void salvar(PedidoBatismo pedido) throws SQLException{
        StringBuilder sql = new StringBuilder();
        Date data = new Date((pedido.getDataPedido()).getTime());
        sql.append("INSERT INTO pedidobatismo ");
        sql.append("(fiel, certidao, status,data, valor) ");
        sql.append("VALUES (?, ?, ?, ?, ?)");
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        stmt.setLong(1, pedido.getFiel().getId());
        stmt.setLong(2, pedido.getCertidao().getId());
        stmt.setString(3, pedido.getStatus());
        stmt.setDate(4, data);
        stmt.setFloat(5, pedido.getValor());
        
        stmt.executeUpdate();
    }
    
    
    
     public void editar(PedidoBatismo pedido) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE pedidobatismo ");
        sql.append("SET fiel=?, certidao=?, status=?, valor=? ");
        sql.append("WHERE id=?");
        //System.out.println("Data: " + fiel.getDataNascimento());
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        stmt.setLong(1, pedido.getFiel().getId());
        stmt.setLong(2, pedido.getCertidao().getId());
        stmt.setString(3, pedido.getStatus());
        stmt.setFloat(4, pedido.getValor());
        stmt.setLong(5, pedido.getId());
        stmt.executeUpdate();
    }
   
    
    public ArrayList<PedidoBatismo> listaPedidosBatismos() throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.id, p.fiel, p.certidao, p.status, p.data, p.valor, ");
        sql.append("f.nome ");
        sql.append("FROM pedidobatismo p ");
        sql.append("INNER JOIN fiel f ON f.id=p.fiel ");
        
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<PedidoBatismo> lista = new ArrayList<>();
        
        while(rs.next()){
            PedidoBatismo pedido = new PedidoBatismo();
            Batismo batismo = new Batismo();
            Fiel fiel = new Fiel();
            
            pedido.setId(rs.getLong(1));
            fiel.setId(rs.getLong(2));
            fiel.setNome(rs.getString(7));
            batismo.setId(rs.getLong(3));
            pedido.setStatus(rs.getString(4));
            pedido.setDataPedido(rs.getDate(5));
            pedido.setValor(rs.getFloat(6));
            pedido.setFiel(fiel);
            pedido.setCertidao(batismo);
            lista.add(pedido);
           
        }
        return lista;
    }
    public Batismo listaBatismos(Long id) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b.id, b.batizando, b.pai, b.mae, b.padrinho, b.madrinha, ");
        sql.append("b.datanascimento, b.databatismo, b.localbatismo, b.status, b.paroco, b.valor, b.localnascimento, ");
        sql.append("f.nome, u.nome, c.nome, c.estado ");
        sql.append("FROM batismo b ");
        sql.append("INNER JOIN fiel f ON f.id=b.batizando ");
        sql.append("INNER JOIN cidade c ON c.id=b.localnascimento ");
        sql.append("INNER JOIN usuario u ON u.id=b.paroco ");
        sql.append("WHERE b.id = ");
        sql.append(String.valueOf(id));
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Batismo> lista = new ArrayList<>();
        
        while(rs.next()){
          
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
            
           
        }
        
        return batismo;
    }
    
}
