package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TriadeApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
         FXMLLoader loader = new FXMLLoader();

        // Carrega arquivo fxml do pacote view.fxml
        Parent root = (Parent) loader.load(getClass().getClassLoader().getResourceAsStream(
                "View/fxml/Principal.fxml"));

        Scene scene = new Scene(root);
        
         scene.getStylesheets().add(getClass().getClassLoader().getResource(
                "View/css/triade.css").toExternalForm());

        // Altera o título da janela para "DesafioFX", define a cena e a exibe na tela
        stage.setTitle("Criador de Vozes");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    

//    public static void main(String[] args) {
//        
//         javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                exibirTe();
//            }
//        });
//         
//        new triadesController().criador();
//
//    }
//    
//    public static void exibirTe() {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new visu().setVisible(true);
//            }
//        });
//    }

}
