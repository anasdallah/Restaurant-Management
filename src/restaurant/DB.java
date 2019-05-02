package restaurant;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DB {

    public static Alert a = new Alert(Alert.AlertType.INFORMATION, "Succesful Add", ButtonType.OK);
    public static Alert E = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);

    private static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Restaurant";
            String user = "root";
            String password = "";
            con = DriverManager.getConnection(url, user, password);
            //JOptionPane.showMessageDialog(null, "Done");
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Exception: " + ex.getMessage());
            E.show();
        }
        return con;
    }

    public static int count(String col, String Table) throws SQLException {
        Connection con = getConnection();
        Statement s = con.createStatement();
        String sqlSelect = "select count(" + col + ") from " + Table;
        ResultSet result = s.executeQuery(sqlSelect);
        if (result.next()) {
            return Integer.parseInt(result.getString(1));
        }

        return 0;
    }

    public static boolean insertMeals(String table, String name, String type, double cost) {

        try {
            Connection con = getConnection();
            Statement s = con.createStatement();
            String SqlInsert = "INSERT INTO Meals(Mname,Mtype,Mcost) " + "VALUES('" + name + "','" + type + "'," + cost + ")";

            s.execute(SqlInsert);
            return true;
        } catch (Exception e) {
            E.show();
            return false;
        }
    }

    public static boolean insertDrinks(String name, String type, double cost) {
        try {
            Connection con = getConnection();
            Statement s = con.createStatement();
            String SqlInsert = "INSERT INTO Drinks(Dname,Dtype,Dcost) " + "VALUES('" + name + "','" + type + "'," + cost + ")";
            s.execute(SqlInsert);
            return true;
        } catch (Exception e) {
            E.show();
            return false;
        }
    }

    public static ObservableList<Meals> getMeals() throws SQLException {
        Connection con = getConnection();
        Statement s = con.createStatement();
        String sqlSelect = "select * from meals";
        ResultSet result = s.executeQuery(sqlSelect);
        ObservableList<Meals> mealsList = FXCollections.observableArrayList();
        while (result.next()) {
            int id = result.getInt("Mid");
            String name = result.getString("Mname");
            String type = result.getString("Mtype");
            double cost = result.getDouble("Mcost");
            mealsList.add(new Meals(id, cost, type, name));
        }
        return mealsList;
    }

    public static boolean delMeals(int id) throws SQLException {
        Connection con = getConnection();
        Statement s = con.createStatement();
        String sqlDelete = "DELETE FROM meals WHERE Mid = " + id + " ";
        boolean execute = s.execute(sqlDelete);

        return execute;
    }

    public static boolean delDrinks(int id) throws SQLException {
        Connection con = getConnection();
        Statement s = con.createStatement();
        String sqlDelete = "DELETE FROM Drinks WHERE Did = " + id + " ";
        boolean execute = s.execute(sqlDelete);

        return execute;
    }

    public static ObservableList<Drinks> getDrinks() throws SQLException {
        Connection con = getConnection();
        Statement s = con.createStatement();
        String sqlSelect = "select * from drinks";
        ResultSet result = s.executeQuery(sqlSelect);
        ObservableList<Drinks> drinkList = FXCollections.observableArrayList();
        while (result.next()) {
            int id = result.getInt("Did");
            String name = result.getString("Dname");
            String type = result.getString("Dtype");
            double cost = result.getDouble("Dcost");
            drinkList.add(new Drinks(id, cost, type, name));
        }
        return drinkList;
    }

    public static boolean Update(String table, int id, String name, String type, double cost) throws SQLException {
        Connection con = getConnection();
        Statement s = con.createStatement();
        String sqlSelect;
        if (table.equals("drinks")) {
             sqlSelect = "UPDATE drinks SET Dname = '" + name + "' , Dtype = '" + type + "',Dcost = " + cost + " where Did="+id+"";
        } else {
             sqlSelect = "UPDATE meals SET Mname = '" + name + "' , Mtype = '" + type + "',Mcost = " + cost + " where Mid=" + id + "";

        }
         s.execute(sqlSelect);
        return true;
    }
   
}
