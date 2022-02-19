package service.handle;

import model.data.HostData;
import model.data.QueryData;
import model.data.ReplyToCustomerData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class QueryHandler {
    public void handleQuery(QueryData queryData, List<ReplyToCustomerData> replyToCustomerDataList, String outputFilePath) {

    }
    
    private void saveToOutputFile(List<String> stringList,String outputFilePath ){
        Path path = Paths.get(outputFilePath);
        try {
            Files.write(path, stringList, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
