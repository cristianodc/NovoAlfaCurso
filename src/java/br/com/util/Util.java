/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Cristiano
 */
public  class Util {
    
    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String host = "jdbc:mysql://localhost/alfacursos";
            String user = "root";
            String pass = "";
            con = DriverManager.getConnection(host, user, pass);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }
}
