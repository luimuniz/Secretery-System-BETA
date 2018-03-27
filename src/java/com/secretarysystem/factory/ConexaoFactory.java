/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secretarysystem.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Luiz
 */
public class ConexaoFactory {
    
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    private static final String URL = "jdbc:mysql://localhost:3306/db_paroquia";
    
    public static Connection getConexao() throws SQLException{
        DriverManager.registerDriver( new com.mysql.jdbc.Driver());
        Connection con = DriverManager.getConnection(URL, USUARIO, SENHA);
        return con;
    }
    
    public static void main (String[] args){
        try{
            Connection conexao = ConexaoFactory.getConexao();
            System.out.println("CERTO");
        }catch(SQLException ex){
            System.out.println("ERRO");
        }
    }
}
