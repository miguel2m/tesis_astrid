/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiopostgresql;

import com.ucab.dao.ConexionBaseDeDatos;
import imagenpostgresql.ImagenPostgresql;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguelangel
 */
public class AudioPostgresql {
    private static ConexionBaseDeDatos conn = new ConexionBaseDeDatos();

    public static void main(String[] args) {
        Connection conexion = conn.conexionBaseDeDatos();
        System.out.println("Hello World!");
        String directorio ="C:\\Users\\Miguelangel\\Pictures\\tesis\\Audio-Animales\\";
        File folder = new File(directorio);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("Archivo: "+listOfFiles[i].getName());
                FileInputStream fis = null;
                try {
                    File file = new File(directorio+listOfFiles[i].getName());
                    fis = new FileInputStream(file);
                    PreparedStatement ps = conexion.prepareStatement(
                            "INSERT INTO audio (au_nombre,au_byte,"
                            + "au_categoria, au_respuesta_correcta)"
                            + "VALUES "
                            + "(?,?,?,?)"
                    );
                    ps.setString(1, file.getName());
                    ps.setBinaryStream(2, fis, file.length());
                    ps.setString(3, "Animal");
                    String s=listOfFiles[i].getName();
                    if (s.contains("\\.") )  
                    {
                        String parts[] = s.split(".");
                        s = parts[0]; // i want to strip part after  +

                    }
                    ps.setString(4,s);

                    ps.executeUpdate();
                    ps.close();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ImagenPostgresql.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ImagenPostgresql.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ImagenPostgresql.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        
                        fis.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ImagenPostgresql.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImagenPostgresql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
