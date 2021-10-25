

package classesPackage;

import java.util.HashMap;


public class FloorFactory {
    private static final HashMap FloorMap = new HashMap();   
    
    public static Composite getFloor(String key,String name){
       
            
        Composite floor=(Composite) FloorMap.get(key);
         
        if(floor == null){
            floor = new Composite(name);
           FloorMap.put(key, floor);
        }
         return floor;
    
    }
}
