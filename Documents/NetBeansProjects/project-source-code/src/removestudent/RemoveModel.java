

package removestudent;

import database.DataBase;
import java.sql.SQLException;


public class RemoveModel {
    DataBase db = new DataBase();
    public int deleteStudent(int idstudents){
        try{
            String query = "DELETE FROM `dorms-registration`.`students` WHERE `idstudents`="+idstudents+";";
            int deleted = db.modifyData(query);       
            return deleted;

        }catch(SQLException e){
            System.out.println("failed to get data");
            return 0;
        }
    }
}
