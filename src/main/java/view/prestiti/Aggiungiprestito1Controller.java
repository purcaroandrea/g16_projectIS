/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.prestiti;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author laura
 */
public class Aggiungiprestito1Controller implements Initializable {

    @FXML
    private Label labelaggiungiprestito;
    @FXML
    private Label labelISBNprestito;
    @FXML
    private Label labelmatricolaprestito;
    @FXML
    private Label labeldataprestito;
    @FXML
    private Button bottoneConfermaPrestito;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confermaprestito(ActionEvent event) {
    }
    
}
