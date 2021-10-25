

package classesPackage;


public class Leaf extends Component{
   
    
    private String bloodType;
    private int id;
    private String gender; 
    private String email;  
    private String tel;
    
    /*to be used in search page*/
    private String room;
    private String block;
    private String floor;
    
    public Leaf (String fullname,String bloodType,String gender, int id,String email,String tel){
        this.bloodType = bloodType;
        this.gender = gender;
        this.id =  id;
        this.name = fullname;
        this.email = email;
        this.tel = tel;
    }
    
    //getters and setters
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getTel() {return tel;}
    public void setTel(String tel) {this.tel = tel;}
    public String getGender(){return gender;}
    public String getBloodType(){return bloodType;}
    public int getId(){return id;}
    public void setName(String newName){ name = newName;}
    public void setGender(String newGender){ gender = newGender;}
    public void setBloodType(String newBloodType){ bloodType = newBloodType;}
    public void setId(int newId){ id = newId;}
    public String getRoom() {return room;}
    public void setRoom(String room) {this.room = room;}
    public String getBlock() {return block;}
    public void setBlock(String block) {this.block = block;}
    public String getFloor(){return floor;}
    public void setFloor(String floor) {this.floor = floor;}

}
