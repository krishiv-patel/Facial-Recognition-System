
package register.maleblocks;

import classesPackage.ComponentsFactory;
import classesPackage.Composite;
import classesPackage.SingltonChosenBlock;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class MaleBlocksController implements Initializable {
    
    MaleBlocksModel model = new MaleBlocksModel();
    @FXML Button F;
    @FXML Button G;
    //to go back to information registration page
    public void mainPageButton(ActionEvent event) throws IOException {
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
        Composite block = ComponentsFactory.createComposite("block",button.getText(), button.getText());
        SingltonChosenBlock currBlock = SingltonChosenBlock.getCurrentBlock();
        currBlock.setBlock(block);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/register/floors/Floors.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Floors ");
            stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialize blocks if block is full the block button will be disabled with red background
        try{
            ResultSet rs = model.getBlocks();
            
            while(rs.next()){
                //getstring(1) returns block name getstring(2) returns the block's isFull attribute
                    if(rs.getString(1).equals(F.getText())){
                        if(rs.getString(2).equals("yes")){F.setStyle("-fx-background-color: red");F.setDisable(true);}
                    }
                    else if(rs.getString(1).equals(G.getText())){
                        if(rs.getString(2).equals("yes")){G.setStyle("-fx-background-color: red");G.setDisable(true);}
                    }
                
            }
        }
        catch(SQLException e){
            System.out.println("Failed to get data");
        }
    }    
    
}
