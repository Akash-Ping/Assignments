import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EmployeeSorter {
    
    public static void main(String[] args) {
        try {
            // Read the CSV file and store employee data in a list
            List<Employee> employees = readCSV("Employees.csv");

            // Group employees by department    
            Map<String, List<Employee>> departmentMap = groupByDepartment(employees);

            // Sort employees within each department by salary
            sortEmployeesBySalary(departmentMap);

            // Print the sorted employees grouped by department
            printSortedEmployees(departmentMap);
        } catch (IOException e) {
            // Handle IOException by printing the error message
            e.printStackTrace();
        }
    }

    // // Method to read employee data from a CSV file : type 1
    // private static List<Employee> readCSV(String filePath) throws IOException {
    //     List<Employee> employees = new ArrayList<>();
    //     BufferedReader reader = new BufferedReader(new FileReader(filePath));
    //     String line;
    //     boolean headerSkipped = false;
    
    //     // Read each line of the CSV file
    //     while ((line = reader.readLine()) != null) {
    //         // Skip the header line
    //         if (!headerSkipped) {
    //             headerSkipped = true;
    //             continue;
    //         }
    //         // Split the line by comma to extract employee information
    //         String[] parts = line.split(",");
    //         // Check if all necessary fields are present
    //         if (parts.length < 5) {
    //             // If not enough fields, skip this line and print an error message
    //             System.err.println("Skipping line: " + line + " - Not enough fields");
    //             continue;
    //         }
    //         // Extract employee data from the line
    //         String firstName = parts[0].trim();
    //         String lastName = parts[1].trim();
    //         String department = parts[2].trim();
    //         String position = parts[3].trim();
    //         double salary = 0.0; // Default salary if not provided or parse error
    //         try {
    //             // Parse the salary from the line
    //             salary = Double.parseDouble(parts[4].trim());
    //         } catch (NumberFormatException e) {
    //             // Handle NumberFormatException if salary parsing fails
    //             System.err.println("Error parsing salary for line: " + line);
    //         }
    //         // Create an Employee object and add it to the list
    //         employees.add(new Employee(firstName, lastName, department, position, salary));
    //     }
    //     // Close the BufferedReader
    //     reader.close();
    //     return employees;
    // }
    

    // Method to read employee data from a CSV file : type 2
    private static List<Employee> readCSV(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        boolean headerSkipped = false;
    
        // Read each line of the CSV file
        while ((line = reader.readLine()) != null) {
            // Skip the header line
            if (!headerSkipped) {
                headerSkipped = true;
                continue;
            }
            // Split the line by comma to extract employee information
            String[] parts = line.split(",");
            // Check if all necessary fields are present
            if (parts.length < 5) {
                // If not enough fields, skip this line and print an error message
                System.err.println("Skipping line: " + line + " - Not enough fields");
                continue;
            }
            // Extract employee data from the line
            String firstName = parts[0].trim();
            String lastName = parts[1].trim();
            String department = parts[2].trim();
            String position = parts[3].trim();
            double salary = 0.0; // Default salary if not provided or parse error
            try {
                // Parse the salary from the line
                String[] salaryParts = parts[4].trim().split("-");
                if (salaryParts.length > 1) {
                    // If salary has a range, compare both values and pick the larger one
                    double firstSalary = Double.parseDouble(salaryParts[0]);
                    double secondSalary = Double.parseDouble(salaryParts[1]);
                    salary = Math.max(firstSalary, secondSalary);
                } else {
                    // If salary is a single value, parse it normally
                    salary = Double.parseDouble(parts[4].trim());
                }
            } catch (NumberFormatException e) {
                // Handle NumberFormatException if salary parsing fails
                System.err.println("Error parsing salary for line: " + line);
            }
            // Create an Employee object and add it to the list
            employees.add(new Employee(firstName, lastName, department, position, salary));
        }
        // Close the BufferedReader
        reader.close();
        return employees;
    }
    
    
    // Method to group employees by department
    // private static Map<String, List<Employee>> groupByDepartment(List<Employee> employees) {
    //     Map<String, List<Employee>> departmentMap = new HashMap<>();
    //     // Iterate over each employee
    //     for (Employee employee : employees) {
    //         // Compute the department map, creating a new list if department does not exist
    //         departmentMap.computeIfAbsent(employee.getDepartment(), k -> new ArrayList<>()).add(employee);
    //     }
    //     return departmentMap;
    // }

    // Method to group employees by department lexicographically
private static Map<String, List<Employee>> groupByDepartment(List<Employee> employees) {
    // Use TreeMap to automatically sort departments lexicographically
    Map<String, List<Employee>> departmentMap = new TreeMap<>();
    // Iterate over each employee
    for (Employee employee : employees) {
        // Compute the department map, creating a new list if department does not exist
        departmentMap.computeIfAbsent(employee.getDepartment(), k -> new ArrayList<>()).add(employee);
    }
    return departmentMap;
}

    // Method to sort employees within each department by salary
    private static void sortEmployeesBySalary(Map<String, List<Employee>> departmentMap) {
        // Iterate over each department
        for (List<Employee> employees : departmentMap.values()) {
            // Sort employees within the department by salary
            employees.sort(Comparator.comparingDouble(Employee::getSalary));
        }
    }

    // Method to print sorted employees grouped by department
    private static void printSortedEmployees(Map<String, List<Employee>> departmentMap) {
        // Iterate over each department
        for (Map.Entry<String, List<Employee>> entry : departmentMap.entrySet()) {
            System.out.println("Department: " + entry.getKey());
            // Iterate over each employee in the department
            for (Employee employee : entry.getValue()) {
                // Print employee details
                System.out.println(employee);
            }
            System.out.println(); // Print an empty line after each department
        }
    }

    // Employee class to represent employee data
    static class Employee {
        private String firstName;
        private String lastName;
        private String department;
        private String position;
        private double salary;

        // Constructor to initialize employee data
        public Employee(String firstName, String lastName, String department, String position, double salary) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.department = department;
            this.position = position;
            this.salary = salary;
        }

        // Getter methods to retrieve employee data
        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getDepartment() {
            return department;
        }

        public String getPosition() {
            return position;
        }

        public double getSalary() {
            return salary;
        }

        // Override toString method to print employee details
        @Override
        public String toString() {
            return "Employee{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", department='" + department + '\'' +
                    ", position='" + position + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}
