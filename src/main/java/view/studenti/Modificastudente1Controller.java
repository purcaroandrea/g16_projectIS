/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.studenti;

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
public class Modificastudente1Controller implements Initializable {

    @FXML
    private Button ConfermaModificaStudente;
    @FXML
    private Label modificastudente;
    @FXML
    private Label nomeModifica;
    @FXML
    private Label cognomeModifica;
    @FXML
    private Label matricolaModifica;
    @FXML
    private Label emailModifica;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void salvamodificastudente(ActionEvent event) {
    }
    
}
