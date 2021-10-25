

package classesPackage;


public class ComponentsFactory {
    public static Composite createComposite(String type,String key,String name){
        if(type.equals("block")){
            return Blockfactory.getBlock(name);
        }
        else if(type.equals("floor")){
            return FloorFactory.getFloor(key,name);
        }
        else if(type.equals("room")){
            return RoomFactory.getRoom(key,name);
        }
        return null;
    }
    
    public static Leaf createStudent(String fullname,String bloodType,String gender, int id,String email,String tel){
        return new Leaf( fullname, bloodType, gender, id, email, tel);
    }
}
