package loginpage;

import database.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;




public class LoginPageModel {
    DataBase db =new DataBase();
    ResultSet rs;
    String query = " SELECT * FROM `dorms-registration`.`admin`";
    
    public boolean verifyAdmin(String username,String password) throws SQLException{
        rs = db.getData(query);
            while (rs.next()){
                if(rs.getString(1).compareTo(username)==0 && rs.getString(2).compareTo(password)==0){return true;}
            }
            return false;
    }
}

