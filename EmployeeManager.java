// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: Wrong number of arguments. Please provide exactly one argument.");
            return;
        }

        // Check arguments
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            String fileContent = readEmployeeData();
            if (fileContent != null) {
                String[] employeeList = fileContent.split(",");
                for (String employee : employeeList) {
                    System.out.println(employee);
                }
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            String fileContent = readEmployeeData();
            if (fileContent != null) {
                System.out.println(fileContent);
                String[] employeeList = fileContent.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employeeList.length);
                System.out.println(employeeList[randomIndex]);
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            String newEmployeeName = args[0].substring(1);
            writeEmployeeData("," + newEmployeeName, true);
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            String fileContent = readEmployeeData();
            if (fileContent != null) {
                String[] employeeList = fileContent.split(",");
                boolean done = false;
                String searchName = args[0].substring(1);
                for (int index = 0; index < employeeList.length && !done; index++) {
                    if (employeeList[index].equals(searchName)) {
                        System.out.println("Employee found!");
                        done = true;
                    }
                }
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            String fileContent = readEmployeeData();
            if (fileContent != null) {
                char[] characters = fileContent.toCharArray();
                boolean inWord = false;
                int wordCount = 0;
                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found " + characters.length);
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            String fileContent = readEmployeeData();
            if (fileContent != null) {
                String[] employeeList = fileContent.split(",");
                String targetName = args[0].substring(1);
                for (int index = 0; index < employeeList.length; index++) {
                    if (employeeList[index].equals(targetName)) {
                        employeeList[index] = "Updated";
                    }
                }
                writeEmployeeData(String.join(",", employeeList), false);
            }
            System.out.println("Data Updated.");
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            String fileContent = readEmployeeData();
            if (fileContent != null) {
                String[] employeeList = fileContent.split(",");
                String targetName = args[0].substring(1);
                List<String> updatedList = new ArrayList<>(Arrays.asList(employeeList));
                updatedList.remove(targetName);
                writeEmployeeData(String.join(",", updatedList), false);
            }
            System.out.println("Data Deleted.");
        }
    }

    private static String readEmployeeData() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("employees.txt")));
            String fileContent = reader.readLine();
            reader.close();
            return fileContent;
        } catch (Exception exception) {
            return null;
        }
    }

    private static void writeEmployeeData(String content, boolean shouldAppend) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt", shouldAppend));
            writer.write(content);
            writer.close();
        } catch (Exception exception) {
        }
    }
}
