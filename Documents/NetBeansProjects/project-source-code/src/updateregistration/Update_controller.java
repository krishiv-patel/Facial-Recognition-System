package updateregistration;



import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Update_controller implements Initializable {
    
    UpdateModel model = new UpdateModel();
    
    @FXML TextField name;
    @FXML
    TextField bloodType;
    @FXML
    TextField id;
    @FXML
    TextField tel;
    @FXML
    TextField email;
    @FXML TextField room;
    @FXML TextField floor;
    @FXML TextField block;
    @FXML VBox vbox;
    @FXML Button buton;
    
    
    public void mainPageButton(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/mainpage/MainpageFXML.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Main page");
            stage.show();
    }
    /*get student info on button click and set it to the corresponding textfield enable update button,
    ask user if he wants to search for student in case of a false id*/
    public void getInfo(ActionEvent event) throws SQLException, IOException{
        
            int intId = Integer.parseInt(id.getText());
            ResultSet rs = model.getStudent(intId);
            //to check if the inserted id is existent in the database (getstudetn returns null if the id doesn't exist)
            if(!rs.next()){
                id.setText("");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Wrong ID");
                alert.setContentText("The inserted ID doesn't belong to any student.\nWould you like to go to search page?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                     //ok button is pressed
                     FXMLLoader loader=new FXMLLoader(getClass().getResource("/searchpage/Search.fxml"));
                    Parent root=(Parent) loader.load();
                    Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Search page");
                    stage.show();
                }
                return;
            }
            
        //rs.next() in the if statement moves the pointer to the first row so we have to return the pointer to before first row 
            rs.beforeFirst();
            while(rs.next()){
                name.setText(rs.getString("fullName"));email.setText(rs.getString("email"));tel.setText(rs.getString("tel"));bloodType.setText(rs.getString("bloodType"));room.setText(rs.getString("room"));floor.setText(rs.getString("floorNb"));block.setText(rs.getString("blockName"));
            }
            vbox.setVisible(true);
            buton.setDisable(false);
        event.consume();
    }
    
    //get updated values and send to databse alert if update fails
    public void update(ActionEvent event) throws SQLException{
        int roomNum =Integer.parseInt(room.getText());
        int floorNum = Integer.parseInt(floor.getText());
        int updated;
        
        //if the room wasnt changed we do not need to check if the room is full
        if(model.roomNotChanged(roomNum, floorNum , block.getText().toUpperCase()))
            updated = model.updateStudent(block.getText().toUpperCase(), floorNum, Integer.parseInt(id.getText()), name.getText(), bloodType.getText(), roomNum, tel.getText(), email.getText());
        
        else{
    //verify if the chosen room is appropriate to the students gender (males are allowed in blocks F,G females:A->E) 
            if(!model.verifyInput(block.getText().toUpperCase() , floorNum ,roomNum  )){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Out of range");
                    alert.setContentText("Room number out of range");
                    alert.show();
                return;
            }
        
            if(model.roomIsFull(roomNum, floorNum , block.getText().toUpperCase())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Error");
                 alert.setHeaderText("Full room");
                 alert.setContentText("The inserted room is full");
                 alert.show();
                 return;
            }
            else
                updated = model.updateStudent(block.getText().toUpperCase(), floorNum, Integer.parseInt(id.getText()), name.getText(), bloodType.getText(), roomNum, tel.getText(), email.getText());
        }
        
        if(updated == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Update failed");
            alert.setContentText("Something went wrong while updating please check the input and try again");
            alert.show();
            event.consume();
        }
        
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Updated");
            alert.setHeaderText("Update successful");
            alert.setContentText("Student information updated successfully!");
            alert.show();
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
    
    public void toSearchPage(ActionEvent event) throws IOException{
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/searchpage/Search.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Search student page");
            stage.show();
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
