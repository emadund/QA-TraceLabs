package test.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ModulServices {

    public ModulServices() {
    }

    public static String getUsername(String type) throws Exception {
        String result = null;

        try {
            Connection connection = JDBC.getInstance().getConnection();
            String query = "SELECT username FROM testinputs WHERE type=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, type);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = rs.getString("username");
                if (result==null) result="";
            }
            rs.close();
            statement.close();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error with db");
            return result;
        }
    }

    public static String getPassword(String type) throws Exception {
        String result = null;

        try {
            Connection connection = JDBC.getInstance().getConnection();
            String query = "SELECT password FROM testinputs WHERE type=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, type);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = rs.getString("password");
                if (result==null) result="";
            }
            rs.close();
            statement.close();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error with db");
            return result;
        }
    }

    public static String getEmail(String type) throws Exception {
        String result = null;

        try {
            Connection connection = JDBC.getInstance().getConnection();
            String query = "SELECT email FROM testinputs WHERE type=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, type);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = rs.getString("email");
                if (result==null) result="";
            }
            rs.close();
            statement.close();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error with db");
            return result;
        }
    }
    public ArrayList<ModulJDBC> getAll () throws Exception {
        ArrayList<ModulJDBC> modules = new ArrayList<>();
        try {
            Connection connection = JDBC.getInstance().getConnection();
            String query = "SELECT * FROM testinputs";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ModulJDBC modul = new ModulJDBC();
                modul.setId(Integer.parseInt(rs.getString("id")));
                modul.setUsername(rs.getString("username"));
                modul.setPassword( rs.getString("password"));
                modul.setEmail(rs.getString("email"));
                modul.setType(rs.getString("type"));
                modules.add(modul);
            }
            rs.close();
            statement.close();
            return modules;
        }
        catch (Exception e)  {
            e.printStackTrace();
            System.out.println("Problem with db");
            return null;
        }
    }
}
