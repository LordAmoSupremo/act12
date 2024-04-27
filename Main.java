import java.sql.*;

import static clases.Albums.*;
import static clases.Artist.*;
public class Main {

    public static Connection Conectar_bd(String bd) {
        Connection connection = null;
        String host = "jdbc:mysql://localhost:3306/" + bd;
        String user = "root";
        String password = "Idraptors#3%";
        System.out.println("Conectando...");
        try {
            connection = DriverManager.getConnection(host, user, password);
            System.out.println("Conexion exitosa!!!");
            Statement statement = connection.createStatement();
                } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
public static void Desconexion(Connection con) {
        try{
        con.close();
    System.out.println("Desconectado!!!");

}catch(SQLException e){
       System.out.println(e.getMessage());
       throw new  RuntimeException(e);
        }
}
    public static void insertartistalbum(Connection con, String artist_name, String album_name) {
        try {
            // Insertar un nuevo artista
            insertArtist(con, artist_name);

            // Obtener el ID del último artista insertado
            int artist_id = getLastInsertedArtistID(con);

            // Insertar un nuevo álbum asociado con el artista
            Insertalbums(con, album_name, artist_id);

            System.out.println("Se ha insertado el nuevo artista y su álbum asociado exitosamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el nuevo registro", e);
        }
    }

    private static void insertArtist(Connection con, String artist_name) throws SQLException {
        String sql = "INSERT INTO artists(artist_name) VALUES ('" + artist_name + "')";
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    private static int getLastInsertedArtistID(Connection con) throws SQLException {
        String sql = "SELECT LAST_INSERT_ID() AS last_id";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("last_id");
            } else {
                throw new SQLException("No se pudo obtener el ID del último artista insertado");
            }
        }
    }
    public static void main(String[] args) {
        Connection bd = Conectar_bd("music");
        System.out.println("Consultas terminadas!!!");
        //inicio de metodos para tabla albums.
            //deletealbums(bd,888);
            //Insertalbums(bd, "30", 208);
            //Consulta(bd);
            //updaterecord(bd,884,"Album_actualizado",203);
            //fin metodos para tabla albums.
        System.out.println("================================");
        //inicio de metodos para tabla artists.
            Consultaartists(bd);
            //insertartists(bd,"Adele");
            deleteartists(bd,208);
        //fin metodos para tabla artists.
        System.out.println("================================");
        //funcion para ingresar nuevo album y artista al mismo tiempo.
           // insertartistalbum(bd, "Artista complejo", "Álbum complejo");
        Desconexion(bd);
           }
}
