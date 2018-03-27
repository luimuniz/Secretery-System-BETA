/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.dao;

import com.secretarysystem.factory.ConexaoFactory;
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
public class CasamentoDAO {
    public void salvar(Casamento casamento) throws SQLException{
        StringBuilder sql = new StringBuilder();
        Date data = new Date((casamento.getDataCasamento()).getTime());
        sql.append("INSERT INTO casamento ");
        sql.append("(noivo, noiva,localnascimentonoivo, localnascimentonoiva, datanascimentonoivo, datanascimentonoiva,testemunha1,"
                + " testemunha2, datacasamento, status, cidade,valor, paroco) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
        //System.out.println("Data: " + fiel.getDataNascimento());
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        stmt.setLong(1, casamento.getNoivo().getId());
        stmt.setLong(2, casamento.getNoiva().getId());
        stmt.setLong(3, casamento.getCidadeNascimentoNoivo().getId());
        stmt.setLong(4, casamento.getCidadeNascimentoNoiva().getId());
        stmt.setDate(5, new Date((casamento.getDataNascimentoNoivo()).getTime()));
         stmt.setDate(6, new Date((casamento.getDataNascimentoNoiva()).getTime()));
        stmt.setString(7, casamento.getTestemunha1());
        stmt.setString(8, casamento.getTestemunha2());
        stmt.setDate(9, data);
        stmt.setString(10, casamento.getStatus());
        stmt.setString(11, casamento.getCidade());
        stmt.setFloat(12, casamento.getValor());
        stmt.setLong(13, casamento.getParoco().getId());
        stmt.executeUpdate();
    }
    
     public void editar(Casamento casamento) throws SQLException{
        StringBuilder sql = new StringBuilder();
        Date data = new Date((casamento.getDataCasamento()).getTime());
        sql.append("UPDATE casamento ");
        sql.append("SET noivo=?, noiva=?,localnascimentonoivo=?, localnascimentonoiva=?, datanascimentonoivo=?, datanascimentonoiva=?,testemunha1=?,"
                + " testemunha2=?, datacasamento=?, status=?, cidade=?,valor=?, paroco=? ");
        sql.append("WHERE id=?");
        //System.out.println("Data: " + fiel.getDataNascimento());
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        stmt.setLong(1, casamento.getNoivo().getId());
        stmt.setLong(2, casamento.getNoiva().getId());
        stmt.setLong(3, casamento.getCidadeNascimentoNoivo().getId());
        stmt.setLong(4, casamento.getCidadeNascimentoNoiva().getId());
        stmt.setDate(5, new Date((casamento.getDataNascimentoNoivo()).getTime()));
         stmt.setDate(6, new Date((casamento.getDataNascimentoNoiva()).getTime()));
        stmt.setString(7, casamento.getTestemunha1());
        stmt.setString(8, casamento.getTestemunha2());
        stmt.setDate(9, data);
        stmt.setString(10, casamento.getStatus());
        stmt.setString(11, casamento.getCidade());
        stmt.setFloat(12, casamento.getValor());
        stmt.setLong(13, casamento.getParoco().getId());
        stmt.setLong(14, casamento.getId());
        stmt.executeUpdate();
    }
   
    
    public ArrayList<Casamento> listaCasamentos() throws SQLException{
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
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Casamento> lista = new ArrayList<>();
        
        while(rs.next()){
            Casamento casamento = new Casamento();
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
            
         lista.add(casamento);
           
        }
        return lista;
    }
}
