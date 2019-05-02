/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anas
 */
public class EditController implements Initializable {

    @FXML
    TextField Eid;
    @FXML
    TextField Ename;
    @FXML
    ComboBox<String> Etype;
    @FXML
    TextField Ecost;

    public void onEdit(Event e) throws SQLException, IOException {
        int id = Integer.parseInt(Eid.getText());
        String name = Ename.getText();
        String type = Etype.getSelectionModel().getSelectedItem().toString();
        double cost = Double.valueOf(Ecost.getText());

        if (MainMenuController.EditTable.equals("_Edit Drinks")) {

            DB.Update("drinks", id, name, type, cost);
        } else {
            DB.Update("meals", id, name, type, cost);

        }

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
        stage2.setTitle("Menu");
        if (MainMenuController.EditTable.equals("_Edit Drinks")) {
            stage2.show();
            MainMenuController.m.setVisible(false);
            MainMenuController.d.setVisible(true);
        } else {
            stage2.show();
            MainMenuController.m.setVisible(true);
            MainMenuController.d.setVisible(false);

        }
        MainMenuController.close();

    }

    public void onCancel(Event e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        int id = MainMenuController.E_id_selection;

        Eid.setText(String.valueOf(id));
        Ename.setText(MainMenuController.E_name_selection);
        Etype.setValue(MainMenuController.E_type_selection);
        Ecost.setText(String.valueOf(MainMenuController.E_cost_selection));
        if (MainMenuController.EditTable.equals("_Edit Drinks")) {
            Etype.getItems().addAll("cola", "Hot-Drink", "coktel");
        } else {
            Etype.getItems().addAll("Burger", "Pizza", "Shawerma");
        }

    }

}
