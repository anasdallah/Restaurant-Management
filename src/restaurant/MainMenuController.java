package restaurant;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static restaurant.DB.E;
import static restaurant.DB.a;

/**
 * FXML Controller class
 *
 * @author anas
 */
public class MainMenuController implements Initializable {

    @FXML
    TextField numMeals;
    @FXML
    TextField numDrinks;
    @FXML
    Pane Meals;
    @FXML
    Pane Drinks;
    @FXML
    ComboBox typeD;
    @FXML
    ComboBox typeM;
    @FXML
    TextField numM;
    @FXML
    TextField nameM;
    @FXML
    Label lblnameM;
    @FXML
    TextField costM;
    @FXML
    Label lblcostM;
    @FXML
    Label lblTypeM;
    @FXML
    TextField numD;
    @FXML
    TextField nameD;
    @FXML
    Label lblnameD;
    @FXML
    Label lblTypeD;
    @FXML
    TextField costD;
    @FXML
    Label lblcostD;
    //tableview m:
    ObservableList<Meals> listM = null;
    @FXML
    TableView<Meals> tableM;
    @FXML
    TableColumn<Meals, Integer> numCM;
    @FXML
    TableColumn<Meals, String> nameCM;
    @FXML
    TableColumn<Meals, String> typeCM;
    @FXML
    TableColumn<Meals, Double> costCM;
    //tableview d:
    ObservableList<Drinks> listD = null;
    @FXML
    TableView<Drinks> tableD;
    @FXML
    TableColumn<Drinks, Integer> numCD;
    @FXML
    TableColumn<Drinks, String> nameCD;
    @FXML
    TableColumn<Drinks, String> typeCD;
    @FXML
    TableColumn<Drinks, Double> costCD;
    public static String EditTable = "";
    public static int E_id_selection;
    public static String E_name_selection;
    public static String E_type_selection;
    public static double E_cost_selection;

    public static Stage MainStage;
    public static Pane m;
    public static Pane d;

    public void entred(Event e) {
        ((Button) e.getSource()).setScaleX(1.1);
        ((Button) e.getSource()).setScaleY(1.1);
    }

    public void exited(Event e) {
        ((Button) e.getSource()).setScaleX(1);
        ((Button) e.getSource()).setScaleY(1);
    }

    public void Drinks() {
        Meals.setVisible(false);
        Drinks.setVisible(true);

    }

    public void Meals() {
        Meals.setVisible(true);
        Drinks.setVisible(false);

    }

    public void ClearMeals() {
        numM.clear();
        nameM.clear();
        typeM.getSelectionModel().clearSelection();
        costM.clear();
    }

    public void ClearDrinks() {
        numD.clear();
        nameD.clear();
        typeD.getSelectionModel().clearSelection();
        costD.clear();
    }

    public void onLogout(Event e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setResizable(false);

        stage.show();

    }

    public static void close() {
        MainStage.close();
    }

    public void addMeals() throws SQLException {

        String name = nameM.getText();
        String type = typeM.getSelectionModel().getSelectedItem().toString();
        double cost = Double.valueOf(costM.getText());

        if (DB.insertMeals("Meals", name, type, cost)) {

            a.show();
        }

        try {
            numMeals.setText(String.valueOf(DB.count("Mid", "meals")));
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int counter = Integer.parseInt(numMeals.getText());
        numM.setText(String.valueOf(counter));

        try {
            listM = DB.getMeals();
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableM.setItems(listM);
    }

    public void deleteMeals() throws SQLException {
        Meals id = tableM.getSelectionModel().getSelectedItem();

        DB.delMeals(id.getId());
        try {
            listM = DB.getMeals();
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableM.setItems(listM);
        try {
            numMeals.setText(String.valueOf(DB.count("Mid", "meals")));
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int counter = Integer.parseInt(numMeals.getText());
        numM.setText(String.valueOf(counter));
    }

    public void deleteDrinks() throws SQLException {
        Drinks id = tableD.getSelectionModel().getSelectedItem();

        DB.delDrinks(id.getId());
        try {
            listD = DB.getDrinks();
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableD.setItems(listD);
        try {
            numDrinks.setText(String.valueOf(DB.count("Did", "drinks")));
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int counter = Integer.parseInt(numDrinks.getText());
        numD.setText(String.valueOf(counter));
    }

    public void addDrinks() throws SQLException {
        String name = nameD.getText();
        String type = typeD.getSelectionModel().getSelectedItem().toString();
        double cost = Double.valueOf(costD.getText());

        if (DB.insertDrinks(name, type, cost)) {

            a.show();
        }
        try {
            numDrinks.setText(String.valueOf(DB.count("Did", "drinks")));
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            listD = DB.getDrinks();
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableD.setItems(listD);

    }

    public void update(Event e) throws IOException {
        Stage stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
        MainStage = stage1;
        EditTable = ((Button) e.getSource()).getText();
        if (EditTable.equals("_Edit Drinks")) {
            E_id_selection = tableD.getSelectionModel().getSelectedItem().getId();
            E_name_selection = tableD.getSelectionModel().getSelectedItem().getName();
            E_type_selection = tableD.getSelectionModel().getSelectedItem().getType();
            E_cost_selection=tableD.getSelectionModel().getSelectedItem().getCost();

        } else {
            E_id_selection = tableM.getSelectionModel().getSelectedItem().getId();
            E_name_selection = tableD.getSelectionModel().getSelectedItem().getName();
            E_type_selection = tableD.getSelectionModel().getSelectedItem().getType();
            E_cost_selection=tableD.getSelectionModel().getSelectedItem().getCost();

        }
        Parent root = FXMLLoader.load(getClass().getResource("Edit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit");
        stage.show();

    }

    public void loadData() throws SQLException {
        listM = DB.getMeals();
        tableM.setItems(listM);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        m = Meals;
        d = Drinks;

        //Meals: , Drinks:
        try {
            numMeals.setText(String.valueOf(DB.count("Mid", "meals")));
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            numDrinks.setText(String.valueOf(DB.count("Did", "drinks")));
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //cbo
        typeD.getItems().addAll("cola", "Hot-Drink", "coktel");
        typeM.getItems().addAll("Burger", "Pizza", "Shawerma");

        lblnameD.setLabelFor(nameD);
        lblTypeD.setLabelFor(typeD);
        lblcostD.setLabelFor(costD);

        lblnameM.setLabelFor(nameM);
        lblTypeM.setLabelFor(typeM);
        lblcostM.setLabelFor(costM);

        numCM.setCellValueFactory(new PropertyValueFactory<Meals, Integer>("id"));
        nameCM.setCellValueFactory(new PropertyValueFactory<Meals, String>("name"));
        typeCM.setCellValueFactory(new PropertyValueFactory<Meals, String>("type"));
        costCM.setCellValueFactory(new PropertyValueFactory<Meals, Double>("cost"));

        numCD.setCellValueFactory(new PropertyValueFactory<Drinks, Integer>("id"));
        nameCD.setCellValueFactory(new PropertyValueFactory<Drinks, String>("name"));
        typeCD.setCellValueFactory(new PropertyValueFactory<Drinks, String>("type"));
        costCD.setCellValueFactory(new PropertyValueFactory<Drinks, Double>("cost"));

        try {
            listM = DB.getMeals();
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableM.setItems(listM);

        try {
            listD = DB.getDrinks();
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableD.setItems(listD);

    }
}
