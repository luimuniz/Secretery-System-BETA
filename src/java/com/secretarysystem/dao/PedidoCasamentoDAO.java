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
import com.secretarysystem.model.PedidoBatismo;
import com.secretarysystem.model.PedidoCasamento;
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
public class PedidoCasamentoDAO {
    
     public void salvar(PedidoCasamento pedido) throws SQLException{
        StringBuilder sql = new StringBuilder();
        Date data = new Date((pedido.getDataPedido()).getTime());
        sql.append("INSERT INTO pedidocasamento ");
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
     
     public void editar(PedidoCasamento pedido) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE pedidocasamento ");
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
     
     public ArrayList<PedidoCasamento> listaPedidosCasamentos() throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.id, p.fiel, p.certidao, p.status, p.data, p.valor, ");
        sql.append("f.nome ");
        sql.append("FROM pedidocasamento p ");
        sql.append("INNER JOIN fiel f ON f.id=p.fiel ");
        
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<PedidoCasamento> lista = new ArrayList<>();
        
        while(rs.next()){
            PedidoCasamento pedido = new PedidoCasamento();
            Casamento casamento = new Casamento();
            Fiel fiel = new Fiel();
            
            pedido.setId(rs.getLong(1));
            fiel.setId(rs.getLong(2));
            fiel.setNome(rs.getString(7));
            casamento.setId(rs.getLong(3));
            pedido.setStatus(rs.getString(4));
            pedido.setDataPedido(rs.getDate(5));
            pedido.setValor(rs.getFloat(6));
            pedido.setFiel(fiel);
            pedido.setCertidao(casamento);
            lista.add(pedido);
           
        }
        return lista;
    }
     
     public Casamento listaCasamentos(Long id) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT c.id, c.noivo, c.noiva, c.localnascimentonoivo, c.localnascimentonoiva, ");
        sql.append("c.datanascimentonoivo, c.datanascimentonoiva, c.testemunha1, c.testemunha2, c.datacasamento, ");
        sql.append("c.status, c.cidade, c.valor, c.paroco, ");
        sql.append("f.nome, l.nome, l.estado, f2.nome, l2.nome, l2.estado, u.nome ");
        sql.append("FROM casamento c ");
        sql.append("INNER JOIN fiel f ON f.id=c.noivo ");
        sql.append("INNER JOIN fiel f2 ON f2.id=c.noiva ");
        sql.append("INNER JOIN cidade l ON l.id=c.localnascimentonoivo ");
        sql.append("INNER JOIN cidade l2 ON l2.id=c.localnascimentonoiva ");
        sql.append("INNER JOIN usuario u ON u.id=c.paroco ");
        sql.append("WHERE c.id = ");
        sql.append(String.valueOf(id));
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        
        Casamento casamento = new Casamento();
        
        while(rs.next()){
            
            Fiel noivo = new Fiel();
            Fiel noiva = new Fiel();
            Cidade localNoivo = new Cidade();
            Cidade localNoiva = new Cidade();
            Cidade cidade = new Cidade();
            Usuario paroco = new Usuario();
            casamento.setId(rs.getLong(1));
           
            noivo.setId(rs.getLong(2));
            noivo.setNome(rs.getString(15));
            noiva.setId(rs.getLong(3));
            noiva.setNome(rs.getString(18));
            localNoivo.setId(rs.getLong(4));
            localNoivo.setNome(rs.getString(16));
            localNoivo.setEstado(rs.getString(17));
            localNoiva.setId(rs.getLong(5));
            localNoiva.setNome(rs.getString(19));
            localNoiva.setEstado(rs.getString(20));
            casamento.setDataNascimentoNoivo( rs.getDate(6));
            casamento.setDataNascimentoNoiva( rs.getDate(7));
            casamento.setTestemunha1(rs.getString(8));
            casamento.setTestemunha2(rs.getString(9));
            casamento.setDataCasamento(rs.getDate(10));
            casamento.setStatus(rs.getString(11));
            casamento.setCidade(rs.getString(12));
            casamento.setValor(rs.getFloat(13));
            paroco.setId(rs.getLong(14));
            paroco.setNome(rs.getString(21));
            casamento.setNoivo(noivo);
            casamento.setNoiva(noiva);
            casamento.setCidadeNascimentoNoivo(localNoivo);
            casamento.setCidadeNascimentoNoiva(localNoiva);
            casamento.setParoco(paroco);
                    
            System.out.println("Noiva: " + rs.getString(18));
            
        
           
        }
        return casamento;
    }
}
