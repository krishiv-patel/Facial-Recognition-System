
package classesPackage;


public class SingltonChosenBlock {
    Composite block;
    
    private static SingltonChosenBlock currentBlock;
    
    public static SingltonChosenBlock getCurrentBlock(){
        if(currentBlock == null){
            currentBlock = new SingltonChosenBlock();
        }
        return currentBlock;
    }
    public void setBlock(Composite block){
        currentBlock.block = block;
    }
    public Composite getBlock(){
        return currentBlock.block;
    }
}
