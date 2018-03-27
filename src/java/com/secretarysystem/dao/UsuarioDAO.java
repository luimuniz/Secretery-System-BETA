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
import com.secretarysystem.model.Nivel;
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
public class UsuarioDAO {
    
     public void salvar(Usuario usuario) throws SQLException{
        StringBuilder sql = new StringBuilder();
        
        sql.append("INSERT INTO usuario ");
        sql.append("(nome, login, senha, telefone, email, ativo,nivel) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");
        //System.out.println("Data: " + fiel.getDataNascimento());
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getLogin());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getTelefone());
        stmt.setString(5, usuario.getEmail());
        stmt.setString(6, usuario.getAtivo());
        stmt.setLong(7, usuario.getNivel().getId());
        stmt.executeUpdate();
    }
     
     public ArrayList<Usuario> listaUsuarios() throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u.id, u.nome, u.login, u.senha, u.telefone, u.email, u.ativo, u.nivel, n.descricao ");
        sql.append("FROM usuario u, nivel n ");
        sql.append("where u.nivel=n.id");
        
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Usuario> lista = new ArrayList<>();
        
        while(rs.next()){
            
            Usuario user = new Usuario();
            user.setId(rs.getLong(1));
            user.setNome(rs.getString(2));
            user.setLogin(rs.getString(3));
            user.setSenha(rs.getString(4));
            user.setTelefone(rs.getString(5));
            user.setEmail(rs.getString(6));
            user.setAtivo(rs.getString(7));
          
            Long idNivel = rs.getLong(8);
            Nivel nivel = new Nivel();
            nivel.setId(idNivel);
            nivel.setDescricao(rs.getString(9));
            user.setNivel(nivel);
            
         
            lista.add(user);
        }
        return lista;
    }
     
     public ArrayList<Usuario> listaParocos() throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u.id, u.nome, u.login, u.senha, u.telefone, u.email, u.ativo, u.nivel, n.descricao ");
        sql.append("FROM usuario u, nivel n ");
        sql.append("where u.nivel=n.id and u.nivel = 1");
        
        Connection con = ConexaoFactory.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        
        ArrayList<Usuario> lista = new ArrayList<>();
        
        while(rs.next()){
            
            Usuario user = new Usuario();
            user.setId(rs.getLong(1));
            user.setNome(rs.getString(2));
            user.setLogin(rs.getString(3));
            user.setSenha(rs.getString(4));
            user.setTelefone(rs.getString(5));
            user.setEmail(rs.getString(6));
            user.setAtivo(rs.getString(7));
          
            Long idNivel = rs.getLong(8);
            Nivel nivel = new Nivel();
            nivel.setId(idNivel);
            nivel.setDescricao(rs.getString(9));
            user.setNivel(nivel);
            
         
            lista.add(user);
        }
        return lista;
    }
    
}
