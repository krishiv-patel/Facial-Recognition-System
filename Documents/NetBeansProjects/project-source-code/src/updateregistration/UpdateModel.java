

package updateregistration;

import database.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UpdateModel {
    DataBase db = new DataBase();
    ResultSet rs;
    private String gender;
    private String blockName;
    private int floorNb;
    private int roomNb;
    
    public int updateStudent(String blockName, int floorNb,int idstudents,String fullName,String bloodtype,int roomNb,String tel,String email){
        try {
            String query = "UPDATE `dorms-registration`.`students`SET `fullName` = '"+fullName+"',`bloodtype` ='"+bloodtype+"' ,`room` = "+roomNb+",`floorNb` ="+floorNb+" ,`blockName` ='"+blockName+"' ,`email` ='"+email+"'  ,`tel` ='"+tel+"' WHERE `idstudents` ="+idstudents+";";
            int inserted = db.modifyData(query);
            return inserted;
        }
        catch (SQLException e) {
            System.out.println("failed to get data");
        }
        return 0;
    }
    
    public ResultSet getStudent(int id){
        try {
            String query = "SELECT `gender`,`fullName`, `bloodtype` ,`room`, `floorNb`, `blockName`, `email`, `tel` FROM `dorms-registration`.`students` WHERE `idstudents` ="+id+";";
            rs = db.getData(query);
            if(rs.next()){
                this.gender = rs.getString("gender");
                this.blockName = rs.getString("blockName");
                this.floorNb =Integer.parseInt( rs.getString("floorNb"));
                this.roomNb =Integer.parseInt( rs.getString("room"));
                rs.beforeFirst();
            }
            return rs;
        }
        catch (SQLException e) {
            System.out.println("failed to get data");
        }
        return null;
    }
    public ResultSet getRoom(int roomNb, int floorNb, String blockName){
        try {
            String query = "SELECT `numberOfStudents` FROM `dorms-registration`.`rooms` WHERE `roomNb`="+roomNb+" and `floorNb`="+floorNb+" and `blockName`='"+blockName+"';";
            rs = db.getData(query);
            return rs;
        }
        catch (SQLException e) {
            System.out.println("failed to get data");
        }
        return null;
    }
    public boolean verifyInput(String block,int floor,int room){
        if(gender.equals("Female")){
            if(room > 36 || floor >3 || !block.matches("[A-E]")){
                return false;
            }
        }
        else if(gender.equals("Male")){
            if(room > 36 || floor >3 || !block.matches("[F-G]")){
                return false;
            }
        }
        return true;
    }
    public boolean roomIsFull(int roomNb, int floorNb, String blockName) throws SQLException{
        ResultSet selectedRoom = this.getRoom(roomNb,floorNb,blockName);
        while(selectedRoom.next()){
            if(selectedRoom.getInt("numberOfStudents") == 2) return true;
        }
        return false;
    }
    public boolean roomNotChanged(int room,int floor,String block){
        if(room == this.roomNb && floor == this.floorNb && block.equals(this.blockName))
            return true;
        return false;
    }
}
