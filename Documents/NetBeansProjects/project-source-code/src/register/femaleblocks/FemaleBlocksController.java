
package register.femaleblocks;

import classesPackage.ComponentsFactory;
import classesPackage.Composite;
import classesPackage.SingltonChosenBlock;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class FemaleBlocksController implements Initializable {

    FemaleBlocksModel model = new FemaleBlocksModel();
    
    @FXML Button A;
    @FXML Button B;
    @FXML Button C;
    @FXML Button D;
    @FXML Button E;
    ArrayList<Button> buttons = new ArrayList();
    //to go back to information registration page
    public void backButton(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/register/Register_fxml.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Registration page");
            stage.show();
    }
   //get a block composite instance from factory and put the selected block in the chosen block singleton
    public void blockSelected(ActionEvent event) throws IOException{
        Button button =(Button) event.getSource();
        Composite block = ComponentsFactory.createComposite("block", button.getText(),button.getText());
        SingltonChosenBlock currBlock = SingltonChosenBlock.getCurrentBlock();
        currBlock.setBlock(block);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/register/floors/Floors.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Floors");
            stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            System.out.println("initiallize");
        ResultSet rs = model.getBlocks();
        buttons.add(A);buttons.add(B);buttons.add(C);buttons.add(D);buttons.add(E);
        
            while(rs.next()){
                for(int i=0;i<buttons.size();i++){
                    if(rs.getString(1).equals(buttons.get(i).getText())){
                        if(rs.getString(2).equals("yes")){buttons.get(i).setStyle("-fx-background-color: red");buttons.get(i).setDisable(true);break;}
                    }
                }
            }
        }
        catch(SQLException e){
            System.out.println("Failed to get data");
        }
    }    
    
}
