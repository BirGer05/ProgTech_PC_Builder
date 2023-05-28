package DBconnection;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBconnection {
    private static Logger logger = Logger.getLogger("DB connection test");
    private static JFrame frame = new JFrame();

    private Connection dbConnection;

    public DBconnection(String url, String username, String password) {
        Connection connection;
        try
        {
            connection = DriverManager.getConnection(url,username,password);
            logger.info("Sikeres csatlakozás");
        }
        catch (Exception e){
            connection = null;
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Adatbázis csatlakozási hiba!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        this.setDbConnection(connection);

    }
    public Connection getDbConnection() {

        return dbConnection;
    }

    public void setDbConnection(Connection dbConnection) {

        this.dbConnection = dbConnection;
    }
}
