import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class task3 {
    public static void main(String[] args) throws IOException {

        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("task3/src/tests.json"));
        JsonTests tests = gson.fromJson(br, JsonTests.class);
        br = new BufferedReader(new FileReader("task3/src/values.json"));
        JsonValues values = gson.fromJson(br, JsonValues.class);

        generateReport(tests, values);

        String str = gson.toJson(tests);

        try(FileWriter writer = new FileWriter("task3/src/report.json", false)){
            writer.write(str);
        }
    }

    private static void generateReport (JsonTests tests, JsonValues values) {

        Map<Integer, String> testValues = new HashMap<>();
        for (Values value : values.getValues()) {
            Integer testId = value.Id;
            String val = value.getValue();
            testValues.put(testId, val);
        }

        for (Tests tests1: tests.getTests()){
            Integer idTest = tests1.getId();
            if (testValues.containsKey(idTest)){
                tests1.setValue(testValues.get(idTest));
            }
            if (tests1.getValues() != null){
                for (Values val: tests1.getValues()){
                    updateValues(val, testValues);
                }
            }
        }
    }

    private static void updateValues(Values values, Map<Integer, String> testValues) {
        Integer idValue = values.getId();
        if (values.getValue() != null) {
            if (testValues.containsKey(idValue)) {
                values.setValue(testValues.get(idValue));
            }
        }
        if (values.getValues() != null) {
            for (Values val : values.getValues()) {
                updateValues(val, testValues);
            }
        }
    }

    @Getter
    @Setter
    private static class JsonTests {
        @SerializedName("tests")
        private Tests[] tests;
    }
    @Getter
    @Setter
    private static class JsonReport {
        @SerializedName("tests")
        private Tests[] tests;
    }
    @Getter
    @Setter
    private static class JsonValues {
        @SerializedName("values")
        private Values[] values;
    }
    @Getter
    @Setter
    private static class Tests {
        @SerializedName("id")
        private Integer Id;

        @SerializedName("title")
        private String title;

        @SerializedName("value")
        private String value;

        @SerializedName("values")
        private Values[] values;
    }

    @Getter
    @Setter
    private static class Values {
        @SerializedName("id")
        private Integer Id;

        @SerializedName("title")
        private String title;

        @SerializedName("value")
        private String value;

        @SerializedName("values")
        private Values[] values;
    }
}
