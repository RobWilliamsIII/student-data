import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Class to represent a student
class Student implements Comparable<Student> {
    private String name;
    private String address;
    private double GPA;

    // Student Constructor
    public Student(String name, String address, double GPA) {
        this.name = name;
        this.address = address;
        this.GPA = GPA;
    }

    // Getter
    public String getName() {
        return name;
    }


    // Compare students based on  name
    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.getName());
    }

    // String representation of a student
    @Override
    public String toString() {
        return "~~~~~~~~~~~\n" + "Student Name: " + name.substring(0,1).toUpperCase() + name.substring(1) + " \nAddress: " + address + " \nGPA: " + GPA + "\n";
    }
}

public class StudentData {
    public static void main(String[] args) {
        List<Student> students = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        // Prompt user for student data until 'exit' is entered
        while (true) {
            System.out.print("Enter student's name (or 'done' if no more students to add): ");
            String name = scanner.nextLine();
            if (name.equals("done")) {
                break;
            }

            System.out.print("Enter student's address: ");
            String address = scanner.nextLine();

            double GPA = 0;
            boolean validGPA = false;

            // Validate GPA input
            while (!validGPA) {
                System.out.print("Enter student's GPA: ");
                try {
                    GPA = Double.parseDouble(scanner.nextLine());
                    validGPA = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid GPA. GPA needs to have a decimal (like 3.2)");
                }
            }

            // Create a new student object and add it to the list
            students.add(new Student(name, address, GPA));
        }

        scanner.close();

        // Sort the list of students by name in ASC order
        Collections.sort(students);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {

            // Write the sorted student data to a file
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println("Students written to students.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}

