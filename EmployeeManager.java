// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

/**
 * Manages employee records stored in a text file.
 * Supports listing, random selection, adding, searching, counting, updating, and deleting employees.
 */
public class EmployeeManager {

    public static void main(String[] args) {
        // Validate that the user provided exactly one command-line argument
        if (args.length != 1) {
            System.out.println("Error: Wrong number of arguments. Please provide exactly one argument.");
            return;
        }

        // Operation 'l': List all employee names line by line
        if (args[0].equals("l")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String employeeDataLine = loadEmployeeDataFromFile();
            if (employeeDataLine != null) {
                for (String employeeName : employeeDataLine.split(Constants.DELIMITER)) {
                    System.out.println(employeeName);
                }
            }
            System.out.println(Constants.LOADED_MESSAGE);
        }
        // Operation 's': Display a randomly selected employee name
        else if (args[0].equals("s")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String employeeDataLine = loadEmployeeDataFromFile();
            if (employeeDataLine != null) {
                System.out.println(employeeDataLine);
                String[] employeeNames = employeeDataLine.split(Constants.DELIMITER);
                System.out.println(employeeNames[new Random().nextInt(employeeNames.length)]);
            }
            System.out.println(Constants.LOADED_MESSAGE);
        }
        // Operation '+': Append a new employee name to the data file
        else if (args[0].startsWith("+")) {
            System.out.println(Constants.LOADING_MESSAGE);
            saveEmployeeDataToFile(Constants.DELIMITER + args[0].substring(1), true);
            System.out.println(Constants.LOADED_MESSAGE);
        }
        // Operation '?': Search for a specific employee name in the data file
        else if (args[0].startsWith("?")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String employeeDataLine = loadEmployeeDataFromFile();
            if (employeeDataLine != null) {
                if (Arrays.asList(employeeDataLine.split(Constants.DELIMITER)).contains(args[0].substring(1))) {
                    System.out.println("Employee found!");
                } else {
                    System.out.println("Employee not found.");
                }
            }
            System.out.println(Constants.LOADED_MESSAGE);
        }
        // Operation 'c': Count the number of words and characters in the data file
        else if (args[0].equals("c")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String employeeDataLine = loadEmployeeDataFromFile();
            if (employeeDataLine != null) {
                System.out.println(employeeDataLine.trim().split("\\s+").length + " word(s) found " + employeeDataLine.length());
            }
            System.out.println(Constants.LOADED_MESSAGE);
        }
        // Operation 'u': Update a matching employee name to "Updated"
        else if (args[0].startsWith("u")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String employeeDataLine = loadEmployeeDataFromFile();
            if (employeeDataLine != null) {
                String[] employeeNames = employeeDataLine.split(Constants.DELIMITER);
                for (int index = 0; index < employeeNames.length; index++) {
                    if (employeeNames[index].equals(args[0].substring(1))) {
                        employeeNames[index] = "Updated";
                    }
                }
                saveEmployeeDataToFile(String.join(Constants.DELIMITER, employeeNames), false);
            }
            System.out.println(Constants.UPDATED_MESSAGE);
        }
        // Operation 'd': Delete a matching employee name from the data file
        else if (args[0].startsWith("d")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String employeeDataLine = loadEmployeeDataFromFile();
            if (employeeDataLine != null) {
                List<String> remainingEmployees = new ArrayList<>(Arrays.asList(employeeDataLine.split(Constants.DELIMITER)));
                remainingEmployees.remove(args[0].substring(1));
                saveEmployeeDataToFile(String.join(Constants.DELIMITER, remainingEmployees), false);
            }
            System.out.println(Constants.DELETED_MESSAGE);
        }
        // Handle any unrecognized command-line argument
        else {
            System.out.println("Error: Invalid argument provided.");
        }
    }

    /**
     * Reads and returns the contents of the employee data file.
     */
    private static String loadEmployeeDataFromFile() {
        try (BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.FILE_PATH)))) {
            return fileReader.readLine();
        } catch (Exception exception) {
            return null;
        }
    }

    /**
     * Writes content to the employee data file, with an option to append.
     */
    private static void saveEmployeeDataToFile(String dataToWrite, boolean shouldAppend) {
        try (BufferedWriter fileWriter = new BufferedWriter(
                new FileWriter(Constants.FILE_PATH, shouldAppend))) {
            fileWriter.write(dataToWrite);
        } catch (Exception exception) {
            // Ignore write exceptions to preserve original behavior
        }
    }
}
