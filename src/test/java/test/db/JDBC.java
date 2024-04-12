package test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    private static JDBC instance;
    private final Connection connection;

    public JDBC() throws Exception {
        DBUtil util = new DBUtil();
        connection = DriverManager.getConnection(util.getURL(), util.getUser(), util.getPassword());
        System.out.println("Connection to DB via JDBC API successfull");
    }

    public static JDBC getInstance() throws Exception {
        if (instance==null) {
            instance=new JDBC();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }

    public void closeConnection() throws Exception {
        if (connection!=null)
            connection.close();
    }


}
