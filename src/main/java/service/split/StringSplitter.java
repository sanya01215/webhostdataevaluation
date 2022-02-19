package service.split;

import java.util.HashMap;
import java.util.Map;

public class StringSplitter {
    public Map<LineAttrOrder,String> splitAttrLine(String line){
        Map<LineAttrOrder,String> splitAttrMap = new HashMap<>();
        String[] attributes = line.split("\\s");
        LineAttrOrder[] lineAttrOrder = LineAttrOrder.values();

        for(int i = 0; i< attributes.length; i++){
            splitAttrMap.put(lineAttrOrder[i],attributes[i]);
        }
        return splitAttrMap;
    }
}
