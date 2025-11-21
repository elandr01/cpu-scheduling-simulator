import java.io.*;
import java.util.*;

public class CSVFile {
    public static final String COMMA_DELIMITER = ",";

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public static ArrayList<Process> fileToArray(){

        List<List<String>> processData = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("processes.csv"))){
            while (scanner.hasNextLine()){
                processData.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Process> processList = new ArrayList<>();
        for (int i = 1; i < processData.size(); i++){
            Process process = new Process(0, 0, 0); // initialize process object
            process.setPID(Integer.parseInt(processData.get(i).getFirst()));
            process.setArrivalTime(Integer.parseInt(processData.get(i).get(1)));
            process.setBurstTime(Integer.parseInt(processData.get(i).get(2)));
            processList.add(process);
        }

        return processList;
    }

    public static void createProcessFile(){
        String filePath = "processes.csv";
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"pid", "arrive", "burst"});
        data.add(new String[]{"1", "0", "5"});
        data.add(new String[]{"2", "1", "7"});
        data.add(new String[]{"3", "0", "2"});
        data.add(new String[]{"4", "2", "6"});

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
            System.out.println("CSV file created successfully at: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    public static void main(String[] args){
//        createProcessFile(); // DO NOT RUN THIS MORE THAN ONCE
    }

}
