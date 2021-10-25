

package classesPackage;

import java.util.HashMap;

public class Blockfactory {
    private static final HashMap BlockMap = new HashMap();   
    
    public static Composite getBlock(String name){
       
            
        Composite block=(Composite) BlockMap.get(name);
         
        if(block == null){
            block = new Composite(name);
           BlockMap.put(name, block);
        }
         return block;
    
    }
}
