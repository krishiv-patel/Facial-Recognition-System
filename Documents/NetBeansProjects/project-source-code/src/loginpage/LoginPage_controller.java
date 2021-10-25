package loginpage;



import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

   

public class LoginPage_controller implements Initializable{
    
    LoginPageModel model = new LoginPageModel();
    
     @FXML
     TextField userName;
     @FXML
     PasswordField password;
     
     
     public void verify(ActionEvent actionevent)throws IOException, SQLException{
         String username = userName.getText();
         String userpassword = password.getText();
         //alert if any field is empty
         if(username.isEmpty() || userpassword.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Missing info");
             alert.setContentText("Please fill both fields");
             alert.show();
             return;
         }
         //if password and username are correct move to main page
         if(model.verifyAdmin(username, userpassword)){
             FXMLLoader loader=new FXMLLoader(getClass().getResource("/mainpage/MainpageFXML.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)actionevent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Main page");
            stage.show();
         }
         //alert if username or password are invalid
         else{
             userName.setText("");
             password.setText("");
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Invalid info");
             alert.setContentText("Invalid username or password.");
             alert.show();
         }
     }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        
    }    
    
}
