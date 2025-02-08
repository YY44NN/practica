import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            String url = "jdbc:firebirdsql://localhost:3050/C:\\Users\\IAN\\Downloads\\Ejemplos.fdb";
            String user = "SYSDBA";
            String password = "masterkey";
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se pudo cargar el driver de Firebird.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: No se pudo establecer la conexión a la base de datos.");
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
