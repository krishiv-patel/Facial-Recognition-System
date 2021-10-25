

package register.endOfRegistration;

import database.DataBase;
import java.sql.SQLException;


public class eoRegistrationModel {
    DataBase db = new DataBase();
    public int insertStudent(String blockName, int floorNb,int idstudents,String fullName,String gender,String bloodtype,int roomNb,String tel,String email){
        try {
            String query = "INSERT INTO `dorms-registration`.`students`(`idstudents`,`fullName`,`gender`,`bloodtype`,`room`,`floorNb`,`blockName`,`email`,`tel`) VALUES("+idstudents+",'"+fullName+"','"+gender+"','"+bloodtype+"',"+roomNb+","+floorNb+",'"+blockName+"','"+email+"','"+tel+"');";
            int inserted = db.modifyData(query);
            return inserted;
        }
        catch (SQLException e) {
            System.out.println("failed to get data");
        }
        return 0;
    }
}
