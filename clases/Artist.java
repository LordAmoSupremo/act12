package clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Artist {
    public static void deleteartists(Connection con, int artist_id){
        // Delete from artists where artist_id = 'artist_id'
        String sql = "DELETE FROM artists WHERE artist_id = '"+artist_id+"'";
        Statement stmt;
        int result;
        try {
            stmt = con.createStatement();
            result= stmt.executeUpdate(sql);
            // -> 1 -> el artista se borro
            // diferente a 1 -> el artista no se borro
            if(result == 1){
                System.out.println("Se borro el artista con id: "+artist_id);

            }else {
                System.out.println("No existe el artista con id: "+artist_id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void insertartists (Connection con, String artist_name){
        //insert into artist(artists_name) values('Adele')
        String sql = "Insert into artists(artist_name) values('"+artist_name+"')";
        Statement stmt;
        int result;
        try {
            stmt=con.createStatement();
            result=stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static void Consultaartists(Connection con) {
        String sql = "select * from artists";
        Statement stmt;
        ResultSet rs;
        int artist_id;
        String artist_name;
        try { stmt = con.createStatement();
              rs = stmt.executeQuery(sql);
            while (rs.next()) {
                artist_id = rs.getInt("artist_id");
                artist_name = rs.getString("artist_name");

                System.out.println("ID del artista: " + artist_id + ", Nombre del artista: " + artist_name);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar los artistas", e);
        }
    }
}
