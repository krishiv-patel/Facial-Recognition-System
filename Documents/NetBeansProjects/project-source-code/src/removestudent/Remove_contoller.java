package removestudent;



import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Remove_contoller implements Initializable {

    RemoveModel model = new RemoveModel();
    @FXML TextField id;
    
    /*back to main page button*/
    public void mainPageButton(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/mainpage/MainpageFXML.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Main page");
            stage.show();
    }
    /*to search page button*/
    public void toSearchPage(ActionEvent event) throws IOException{
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/searchpage/Search.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Search student page");
            stage.show();
     }
    //get id from user remove student with the id inserted alert if deletion failed ,else ask if user wants to go to mainpage
    public void remove(ActionEvent event) throws IOException{
        int intid = Integer.parseInt(id.getText());
        int deleted = model.deleteStudent(intid);
        if(deleted == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Removing failed");
             alert.setContentText("Something went wrong while removing \n Check if the id inserted is correct");
             alert.show();
             event.consume();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Student removed");
            alert.setContentText("Student removed successfully would you like to return to the main page?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                 //ok button is pressed
                 FXMLLoader loader=new FXMLLoader(getClass().getResource("/mainpage/MainpageFXML.fxml"));
                Parent root=(Parent) loader.load();
                Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Main page");
                stage.show();
            }
            // cancel button is pressed
            else if(result.get() == ButtonType.CANCEL)
                event.consume();
        }
    }
    
    public void numericInput(KeyEvent key ){
        TextField input = (TextField) key.getSource();
        if(!input.getText().matches("\\d+")){
            input.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong input type");
            alert.setContentText(input.getId()+" must be a number(integer)");
            alert.show();
            key.consume();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
