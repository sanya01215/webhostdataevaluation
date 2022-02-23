package service.split;

import exception.ParseInputParametersException;
import service.Service;

import java.util.Map;
import java.util.TreeMap;

import static appconst.StringConst.ARG_DELIMITER;

/**
 * The class for split input line onto parts(split by whitespace) and save it in map.
 * As a map key we use Enums of LineAttrOrderEnum (it's specifies order of attributes).
 * returns TreeMap.
 */
public class StringSplitter implements Service {
    public Map<LineAttrOrderEnum, String> splitAttrLine(String line) {
        Map<LineAttrOrderEnum, String> splitAttrMap = new TreeMap<>();
        String[] attributes = line.split(ARG_DELIMITER);
        LineAttrOrderEnum[] lineAttrOrderEnum = LineAttrOrderEnum.values();

        for (int i = 0;i < attributes.length; i++) {
            if(lineAttrOrderEnum.length<=i)throw new ParseInputParametersException("Input parameter amount more than "+lineAttrOrderEnum.length);
            splitAttrMap.put(lineAttrOrderEnum[i], attributes[i]);
        }
        return splitAttrMap;
    }
}
