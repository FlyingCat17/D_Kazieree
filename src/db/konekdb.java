/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class konekdb {
    public static Connection koneksi;
    
    public static Connection GetConnection() throws SQLException {
        if (koneksi==null){
            new Driver();
            System.out.println("Connected to Database!");
        
        koneksi=DriverManager.getConnection("jdbc:mysql://localhost:3306/d'kazieree","root","");
//        koneksi=DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6452648","sql6452648","B1fMNnfMCj");
        }
        return koneksi;
    }
}
