
package register.endOfRegistration;

import classesPackage.Composite;
import classesPackage.Leaf;
import classesPackage.SingltonChosenBlock;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class EndOfRegistrationController implements Initializable {

    eoRegistrationModel model = new eoRegistrationModel();
    
    @FXML Label name;
    @FXML Label email;
    @FXML Label id;
    @FXML Label tel;
    @FXML Label bloodType;
    @FXML Label roomLabel;
    
    public void mainPage(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/mainpage/MainpageFXML.fxml"));
        Parent root=(Parent) loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Main page");
        stage.show();
    }
    //get encapsulated data and insert in students table alert if insertion doesn't work
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SingltonChosenBlock currBlock = SingltonChosenBlock.getCurrentBlock();
        Composite block = currBlock.getBlock();
        Composite floor=(Composite) block.getLastAdded();
        Composite room=(Composite) floor.getLastAdded();
        Leaf student = (Leaf) room.getLastAdded();
        name.setText(name.getText()+student.getName());
        id.setText(id.getText()+student.getId());
        email.setText(email.getText()+student.getEmail());
        tel.setText(tel.getText()+student.getTel());
        bloodType.setText(bloodType.getText()+student.getBloodType());
        roomLabel.setText(roomLabel.getText()+block.getName()+floor.getName()+room.getName());
        int inserted = model.insertStudent(block.getName(), Integer.parseInt(floor.getName()), student.getId(), student.getName(), student.getGender(), student.getBloodType(), Integer.parseInt(room.getName()), student.getTel(), student.getEmail());
        if (inserted ==0 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Registration failed");
             alert.setContentText("Something went wrong while adding student");
             alert.show();
        }
    }    
    
}
