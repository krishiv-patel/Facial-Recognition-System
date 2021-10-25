package searchpage;

import classesPackage.Leaf;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Search_controller implements Initializable {

    SearchPageModel model = new SearchPageModel();
    
    @FXML TableView students;
    //assign every column with the expected type
    @FXML TableColumn<Leaf,String> name;
    @FXML TableColumn<Leaf,Integer> id;
    @FXML TableColumn<Leaf,String> block;
    @FXML TableColumn<Leaf,String> room;
    @FXML TableColumn<Leaf,String> floor;
    @FXML TableColumn<Leaf,String> tel;
    @FXML TableColumn<Leaf,String> email;
    @FXML TableColumn<Leaf,String> bloodType;
    
    @FXML TextField searchId;
    //get id from user get student from database and set tableview content to the returned student
    public void search(KeyEvent event) throws SQLException{
        String studentName = searchId.getText().toLowerCase();
        ObservableList dbData = FXCollections.observableArrayList(model.searchStudent(studentName));
        students.setItems(dbData);
    }
    
    public void mainPageButton(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/mainpage/MainpageFXML.fxml"));
        Parent root=(Parent) loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Main page");
        stage.show();
    }
    public void Update_function(ActionEvent event) throws IOException{
          FXMLLoader loader=new FXMLLoader(getClass().getResource("/updateregistration/Update_fxml.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Remove student page");
            stage.show();
     }
     public void Remove_function(ActionEvent event) throws IOException{
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/removestudent/Remove_fxml.fxml"));
         Parent root=(Parent) loader.load();
         Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         stage.setScene(new Scene(root));
         stage.setTitle("Update registration page");
         stage.show();      
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set the variable name corresponding to evey column to look for in Leaf(student) objects
        ObservableList dbData = FXCollections.observableArrayList(model.getStudents());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        bloodType.setCellValueFactory(new PropertyValueFactory<>("bloodType"));
        room.setCellValueFactory(new PropertyValueFactory<>("room"));
        floor.setCellValueFactory(new PropertyValueFactory<>("floor"));
        block.setCellValueFactory(new PropertyValueFactory<>("block"));
        email.setCellValueFactory(new PropertyValueFactory <> ("email"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        students.setItems(dbData);
    }    
    
}
