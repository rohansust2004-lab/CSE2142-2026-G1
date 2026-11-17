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
            System.out.println(Constants.LOADING_MESSAGE);
            String fileContent = readEmployeeData();
            if (fileContent != null) {
                for (String employee : fileContent.split(Constants.DELIMITER)) {
                    System.out.println(employee);
                }
            }
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].equals("s")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String fileContent = readEmployeeData();
            if (fileContent != null) {
                System.out.println(fileContent);
                String[] employeeList = fileContent.split(Constants.DELIMITER);
                System.out.println(employeeList[new Random().nextInt(employeeList.length)]);
            }
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].startsWith("+")) {
            System.out.println(Constants.LOADING_MESSAGE);
            writeEmployeeData(Constants.DELIMITER + args[0].substring(1), true);
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].startsWith("?")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String fileContent = readEmployeeData();
            if (fileContent != null) {
                if (Arrays.asList(fileContent.split(Constants.DELIMITER)).contains(args[0].substring(1))) {
                    System.out.println("Employee found!");
                } else {
                    System.out.println("Employee not found.");
                }
            }
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].equals("c")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String fileContent = readEmployeeData();
            if (fileContent != null) {
                System.out.println(fileContent.trim().split("\\s+").length + " word(s) found " + fileContent.length());
            }
            System.out.println(Constants.LOADED_MESSAGE);
        } else if (args[0].startsWith("u")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String fileContent = readEmployeeData();
            if (fileContent != null) {
                String[] employeeList = fileContent.split(Constants.DELIMITER);
                for (int index = 0; index < employeeList.length; index++) {
                    if (employeeList[index].equals(args[0].substring(1))) {
                        employeeList[index] = "Updated";
                    }
                }
                writeEmployeeData(String.join(Constants.DELIMITER, employeeList), false);
            }
            System.out.println(Constants.UPDATED_MESSAGE);
        } else if (args[0].startsWith("d")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String fileContent = readEmployeeData();
            if (fileContent != null) {
                List<String> updatedList = new ArrayList<>(Arrays.asList(fileContent.split(Constants.DELIMITER)));
                updatedList.remove(args[0].substring(1));
                writeEmployeeData(String.join(Constants.DELIMITER, updatedList), false);
            }
            System.out.println(Constants.DELETED_MESSAGE);
        } else {
            System.out.println("Error: Invalid argument provided.");
        }
    }

    private static String readEmployeeData() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.FILE_PATH)))) {
            return reader.readLine();
        } catch (Exception exception) {
            return null;
        }
    }

    private static void writeEmployeeData(String content, boolean shouldAppend) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.FILE_PATH, shouldAppend))) {
            writer.write(content);
        } catch (Exception exception) {
        }
    }
}
