
package searchpage;

import classesPackage.ComponentsFactory;
import classesPackage.Leaf;
import database.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SearchPageModel {
    DataBase db = new DataBase();
    String query = "SELECT * FROM `dorms-registration`.`students`";
    ResultSet rs;
    ArrayList<Leaf>students = new ArrayList();
    
    //get students from database and create an arraylist of student Leafs to be used in tableview 
    public ArrayList getStudents(){
        try {
            Leaf student;
            rs = db.getData(query);
            while(rs.next()){
                student = ComponentsFactory.createStudent( rs.getString("fullName") ,rs.getString("bloodtype"),rs.getString("gender"),rs.getInt("idstudents"),rs.getString("email"),rs.getString("tel") ) ;
                student.setBlock(rs.getString("blockName"));
                student.setFloor(rs.getString("floorNb"));
                student.setRoom(rs.getString("room"));
                students.add(student);
            }
            return students;
        }
        catch (SQLException e) {
            System.out.println("failed to get data");
        }
        return null;
    }
    //get student with the inserted id and return instance in arraylist to be used in tableview
    public ArrayList searchStudent(String name) throws SQLException{
        
        ArrayList<Leaf> searchedStudent =  new ArrayList();
        for(int i=0;i<students.size();i++){
            if(students.get(i).getName().toLowerCase().contains(name)) searchedStudent.add(students.get(i));
        }
        return searchedStudent;
    }
}
