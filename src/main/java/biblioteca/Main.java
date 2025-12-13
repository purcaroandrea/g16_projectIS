package biblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX Main
 */
public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/view/bibliotecainterfaccia1"), 640, 480);
        stage.setTitle("Biblioteca Universitaria");
        stage.setScene(scene);
        stage.show();

        // Debug: stampa il percorso del file FXML
        System.out.println(Main.class.getResource("/view/bibliotecainterfaccia1.fxml"));
    }

    public static void setRoot(String fxmlPath) throws IOException {
        scene.setRoot(loadFXML("/view/" + fxmlPath));
    }

    private static Parent loadFXML(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlPath + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
