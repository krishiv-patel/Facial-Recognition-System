/*
Bassim Mahfouz 95971
Hassan kamal al dine 94525
Hussein Fawaz 95272
Rami Mahfouz 95753
Rony Matar 95893
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/loginpage/LoginPage_fxml.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setMaximized(true);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("lulogo.jpg")));
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
