

package classesPackage;

import java.util.HashMap;


public class RoomFactory {
     private static final HashMap RoomMap = new HashMap();   
    
    public static Composite getRoom(String key,String name){
       
            
        Composite room=(Composite) RoomMap.get(key);
         
        if(room == null){
            room = new Composite(name);
           RoomMap.put(key, room);
        }
         return room;
    
    }
}
