package clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Albums {
    public static void updaterecord (Connection con, int album_id, String album_name, int artist_id ){
        // Uodate albums set album name = 'album_name', artist id = 'artist_id' where album id = 'album_id'
        String sql = "update albums set album_name='"+album_name+"', artist_id='"+artist_id+"' where album_id= '"+album_id+"'";
        Statement stmt;
        int result;

        try {
            stmt= con.createStatement();
            result = stmt.executeUpdate(sql);
            if(result == 1){
                System.out.println("Se han actualizado los datos del album: "+album_name);
            }else{
                System.out.println(" el album con el id "+album_id+" no existe");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static void deletealbums(Connection con, int album_id){
        // Delete from albums where album_id = 'album_id'
        String sql = "DELETE FROM albums WHERE album_id = '"+album_id+"'";
        Statement stmt;
        int result;
        try {
            stmt = con.createStatement();
            result= stmt.executeUpdate(sql);
            // -> 1 -> el album se borro
            // diferente a 1 -> el album no se borro
            if(result == 1){
                System.out.println("Se borro el album con id: "+album_id);

            }else {
                System.out.println("No existe el album con id: "+album_id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void Insertalbums (Connection con, String album_name, int artista_id) {
        //Insert into album(album_name,artist_id) values('2nd law','10')
        String sql = "Insert into albums(album_name,artist_id) values('"+album_name+"','"+artista_id+"')";
        Statement stmt;
        int result;
        try {
            stmt= con.createStatement();
            result = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static void Consulta (Connection con){
        String sql = "select * from albums";
        Statement stmt;
        ResultSet rs;
        int album_id;
        String album_name;
        int artist_id;
        try {
            stmt= con.createStatement();
            rs=stmt.executeQuery(sql);

            while (rs.next()) {
                album_id=rs.getInt("album_id");
                album_name=rs.getString("album_name");
                artist_id=rs.getInt("artist_id");

                System.out.println("album ID: "+album_id+", album_name: " +album_name+", artist ID: "+artist_id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
