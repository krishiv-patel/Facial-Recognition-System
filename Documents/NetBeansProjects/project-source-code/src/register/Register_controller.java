package register;


import classesPackage.ComponentsFactory;
import classesPackage.Leaf;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Register_controller implements Initializable {
    
    RegisterModel model = new RegisterModel();
    
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField middleName;
    @FXML
    TextField id;
    @FXML
    TextField tel;
    @FXML
    TextField email;
    @FXML
    ComboBox comboBox;
    
    /*back to main page button*/
    public void mainPageButton(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/mainpage/MainpageFXML.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Main page");
            stage.show();
    }
    public void createStudent(ActionEvent event) throws IOException, SQLException{
        
        /*-----verifying input--------*/
        if(firstName.getText().isEmpty() || middleName.getText().isEmpty() || lastName.getText().isEmpty() || id.getText().isEmpty() || tel.getText().isEmpty() || email.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Missing info");
             alert.setContentText("Please fill all fields");
             alert.show();
             return;
        }
        /*-----verifying input--------*/
        int intId =Integer.parseInt(id.getText());
        String inEmail = email.getText().trim();
        String inTel = tel.getText().trim();
        if(!validInput(inEmail,inTel,intId)) return ;
        
        /*-----creating student instance--------*/
        Button button =(Button) event.getSource();
        Leaf student = ComponentsFactory.createStudent(firstName.getText().trim()+" "+middleName.getText().trim()+" "+lastName.getText().trim(), comboBox.getValue().toString(), button.getText(), intId, inEmail , inTel);
        /*-----creating student instance--------*/
        
        /*move to the page according to the students gender*/
        FXMLLoader loader;
        if(button.getText().equals("Female")){
            loader=new FXMLLoader(getClass().getResource("/register/femaleblocks/FemaleBlocks.fxml"));
        }
        else  loader=new FXMLLoader(getClass().getResource("/register/maleblocks/MaleBlocks.fxml"));
        Parent root=(Parent) loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setUserData(student); //sharing student instance with new scene
        stage.setScene(new Scene(root));
        stage.setTitle("Blocks");
        stage.show();
    }
    
    /*verifying that id and telephone inputs are numeric values*/
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
    
    public void alphabetInput(KeyEvent key){
        TextField input = (TextField) key.getSource();
        if(!input.getText().matches("[a-z]+")){
            input.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong input type");
            alert.setContentText(input.getId()+" can't contain numbers or symbols");
            alert.show();
            key.consume();
        }
    }
    
    public boolean validInput(String email,String tel,int id) throws SQLException{
        boolean flag = true;
        if(model.alreadyExists(id)){
            this.id.setText("");
            flag = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Duplicate ID");
             alert.setContentText("The inserted ID already belongs to a student");
             alert.show();
        }
        if(!model.emailVerification(email)){
            flag = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Invalid email");
             alert.setContentText("The inserted email is invalid");
             alert.show();
        }
        if(!model.telVerification(tel)){
            flag = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Invalid tel");
             alert.setContentText("The inserted tel is invalid");
             alert.show();
        }
        return flag;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBox.getItems().add("A+");
        comboBox.getItems().add("A-");
        comboBox.getItems().add("B+");
        comboBox.getItems().add("B-");
        comboBox.getItems().add("AB+");
        comboBox.getItems().add("AB-");
        comboBox.getItems().add("O+");
        comboBox.getItems().add("O-");
    }    
    
}
