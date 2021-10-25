

package register.rooms;

import database.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RoomsModel {
    DataBase db = new DataBase();
    public ResultSet getRooms(String blockName, int floorNb){
        try {
            String query = "SELECT * FROM (select * from `dorms-registration`.`rooms` WHERE blockName='"+blockName+"')rooms where floorNb ="+floorNb+";";
            ResultSet rs = db.getData(query);
            return rs;
        }
        catch (SQLException e) {
            System.out.println("failed to get data");
        }
        return null;
    }
}
