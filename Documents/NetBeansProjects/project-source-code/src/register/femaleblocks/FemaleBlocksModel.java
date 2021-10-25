

package register.femaleblocks;

import database.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FemaleBlocksModel {
    DataBase db = new DataBase();
    String query = "SELECT * FROM `dorms-registration`.`blocks` WHERE gender='f'";
    ResultSet rs;
    
    public ResultSet getBlocks(){
        try {
            rs = db.getData(query);
            return rs;
        }
        catch (SQLException e) {
            System.out.println("failed to get data");
        }
        return null;
    }
}
