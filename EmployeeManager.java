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
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String fileContent = reader.readLine();
                String[] employeeList = fileContent.split(",");
                for (String employee : employeeList) {
                    System.out.println(employee);
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String fileContent = reader.readLine();
                System.out.println(fileContent);
                String[] employeeList = fileContent.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employeeList.length);
                System.out.println(employeeList[randomIndex]);
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true));
                String newEmployeeName = args[0].substring(1);
                writer.write(", " + newEmployeeName);
                writer.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String fileContent = reader.readLine();
                String[] employeeList = fileContent.split(",");
                boolean done = false;
                String searchName = args[0].substring(1);
                for (int index = 0; index < employeeList.length && !done; index++) {
                    if (employeeList[index].equals(searchName)) {
                        System.out.println("Employee found!");
                        done = true;
                    }
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String fileContent = reader.readLine();
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
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String fileContent = reader.readLine();
                String[] employeeList = fileContent.split(",");
                String targetName = args[0].substring(1);
                for (int index = 0; index < employeeList.length; index++) {
                    if (employeeList[index].equals(targetName)) {
                        employeeList[index] = "Updated";
                    }
                }
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt"));
                writer.write(String.join(",", employeeList));
                writer.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Updated.");
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String fileContent = reader.readLine();
                String[] employeeList = fileContent.split(",");
                String targetName = args[0].substring(1);
                List<String> updatedList = new ArrayList<>(Arrays.asList(employeeList));
                updatedList.remove(targetName);
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt"));
                writer.write(String.join(",", updatedList));
                writer.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Deleted.");
        }
    }
}
