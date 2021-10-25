/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesPackage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class Composite extends Component{
    private List <Component>list =new ArrayList();
    private int lastadded = -1;
 
    public Composite(String name){
     
        this.name = name;
    
    }
    public void add(Component c)
    {
        lastadded++;
        list.add(lastadded, c);
    }
    public void remove(Component c){
         list.remove(c);
     
    }
    public Component getLastAdded(){
        return list.get(lastadded);
    }
}
   