
package register.floors;

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


public class FloorsController implements Initializable {

    FloorsModel model = new FloorsModel();
    
    @FXML Button f0;
    @FXML Button f1;
    @FXML Button f2;
    @FXML Button f3;
    
    ArrayList<Button> buttons = new ArrayList();
    
    public void backButton(ActionEvent event) throws IOException {
        SingltonChosenBlock currBlock = SingltonChosenBlock.getCurrentBlock();
        Composite block = currBlock.getBlock();
        FXMLLoader loader;
        if(block.getName().charAt(0) > 'E')loader=new FXMLLoader(getClass().getResource("/register/maleblocks/MaleBlocks.fxml"));
        else loader=new FXMLLoader(getClass().getResource("/register/femaleblocks/FemaleBlocks.fxml"));
        Parent root=(Parent) loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Blocks");
        stage.show();
    }
    //create a composite floor and add it in chosen block composite and go to rooms page
    public void floorSelected(ActionEvent event) throws IOException{
        SingltonChosenBlock currBlock = SingltonChosenBlock.getCurrentBlock();
        Composite block = currBlock.getBlock();
        Button button =(Button) event.getSource();
        Composite floor = ComponentsFactory.createComposite("floor",block.getName()+button.getText(),button.getText());
        block.add(floor);
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/register/rooms/Rooms.fxml"));
        Parent root=(Parent) loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Rooms");
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialize floor buttons based on the current block floors that are full are disabled with red bakcground
        SingltonChosenBlock currBlock = SingltonChosenBlock.getCurrentBlock();
        Composite block = currBlock.getBlock();
        try{
            System.out.println("initiallize");
        ResultSet rs = model.getFloors(block.getName());
        buttons.add(f0);buttons.add(f1);buttons.add(f2);buttons.add(f3);
        
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
