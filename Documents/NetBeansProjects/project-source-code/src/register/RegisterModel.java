

package register;

import database.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RegisterModel {
    DataBase db = new DataBase();
    String query = "SELECT `idstudents` FROM `dorms-registration`.`students`;";
    ResultSet rs;
    public ResultSet getIDs(){
        try {
            
            rs = db.getData(query);
            return rs;
        }
        catch (SQLException e) {
            System.out.println("failed to get data");
        }
        return null;
    }
    public boolean alreadyExists(int id) throws SQLException{
        ResultSet ids = this.getIDs();
        while(ids.next()){
            if(ids.getInt("idstudents") == id) return true;
        }
        return false;
    }
    public boolean emailVerification(String email){
        return email.matches("^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
    }
    public boolean telVerification(String tel){
        if(tel.length() != 8 )return false;
        else{
            String prefix = tel.substring(0, 2);
            if(prefix.equals("03") || prefix.equals("70") || prefix.equals("71") || prefix.equals("78") || prefix.equals("79") || prefix.equals("81"))
                return true;
        }
        return false;
    }
}
