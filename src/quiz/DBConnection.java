/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author dell
 */
public class DBConnection {
    Connection getDBConnection() throws ClassNotFoundException, SQLException
    {                
        String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/dbquiz?zeroDateTimeBehavior=convertToNull";
        Class.forName(myDriver);
        com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) DriverManager.getConnection(myUrl, "root", "");
        return conn;
    }
    void clearControls()
    {
        
    }
    BufferedImage imageBrowse()
    {
        JFileChooser ob = new JFileChooser();
        int j=ob.showOpenDialog(ob); 
        DBConnection conn = new DBConnection();      
        PreparedStatement pstmt;
        FileInputStream fs;
        File f;
        String filepath=null;
        if(j==JFileChooser.APPROVE_OPTION)
        {    
            f=ob.getSelectedFile();    
            filepath=f.getPath(); 
            try {
                fs=new FileInputStream(f);
                pstmt = conn.getDBConnection().prepareStatement("INSERT INTO imagetest VALUES(?)");
                pstmt.setBinaryStream(1,fs,(int)f.length());
                pstmt.executeUpdate();
                BufferedImage image = ImageIO.read(new File(filepath));
                //lblTest.setIcon(new ImageIcon(image));
                return image;
            } catch (ClassNotFoundException | SQLException | FileNotFoundException ex) {
                Logger.getLogger(PhotoTest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PhotoTest.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        return null;
    }
}
