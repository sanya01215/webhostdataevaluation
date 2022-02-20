package service.handle;

import model.data.CustomerData;
import service.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
The class provides input/output data functionality (in txt files)
 */
public class InputOutputHandler implements Service {
    public void processAndWriteOutput(List<CustomerData> matchToQueryData, String outputFilePath) {
        Path path = Paths.get(outputFilePath);
        try {
            Files.writeString(path, processOutput(matchToQueryData)+System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String processOutput(List<CustomerData> matchToQueryData) {
        //if one object matches to query, then return reply minutes of it
        if (matchToQueryData.size() > 1) return outputAverageMinutes(matchToQueryData);
        //if two objects matches, then return average reply time of them
        else if(matchToQueryData.size() ==1) {
            String replyTime = String.valueOf(matchToQueryData.get(0).getReplyTimeInMinutes());
            return replyTime;
        }
        //if no matched to query object
        else return "-";
    }
    private String outputAverageMinutes(List<CustomerData> matchToQueryData) {
        int sumMinutes = 0;
        for (CustomerData customerData : matchToQueryData) {
            sumMinutes += customerData.getReplyTimeInMinutes();
        }
        return String.valueOf(sumMinutes / matchToQueryData.size());
    }

    public List<String> getListFromFile(String path) {
        List<String> result = null;
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            result = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
