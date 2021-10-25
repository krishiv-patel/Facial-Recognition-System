

package register.floors;

import database.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FloorsModel {
    DataBase db = new DataBase();
    public ResultSet getFloors(String blockName){
        try {
            String query = "SELECT * FROM `dorms-registration`.`floors` WHERE blockName='"+blockName+"'";
            ResultSet rs = db.getData(query);
            return rs;
        }
        catch (SQLException e) {
            System.out.println("failed to get data");
        }
        return null;
    }
}
