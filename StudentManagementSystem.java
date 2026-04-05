import java.io.*;
import java.util.*;

class Student implements Serializable {

    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void displayStudent() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Grade: " + grade);
        System.out.println("-----------------------");
    }
}

class StudentManagement {

    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.dat";

    public StudentManagement() {
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
        System.out.println("Student added successfully.");
    }

    public void removeStudent(int rollNumber) {

        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student s = iterator.next();

            if (s.getRollNumber() == rollNumber) {
                iterator.remove();
                saveToFile();
                System.out.println("Student removed successfully.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public void searchStudent(int rollNumber) {

        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                s.displayStudent();
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student s : students) {
            s.displayStudent();
        }
    }

    public void editStudent(int rollNumber, String newName, String newGrade) {

        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                s.setName(newName);
                s.setGrade(newGrade);
                saveToFile();
                System.out.println("Student updated successfully.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    private void saveToFile() {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(FILE_NAME));

            oos.writeObject(students);
            oos.close();

        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    private void loadFromFile() {

        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(FILE_NAME));

            students = (ArrayList<Student>) ois.readObject();
            ois.close();

        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }
}

public class StudentManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManagement system = new StudentManagement();

        int choice;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    if (name.isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        break;
                    }

                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();

                    system.addStudent(new Student(name, roll, grade));
                    break;

                case 2:
                    System.out.print("Enter Roll Number to Remove: ");
                    int removeRoll = sc.nextInt();
                    system.removeStudent(removeRoll);
                    break;

                case 3:
                    System.out.print("Enter Roll Number to Search: ");
                    int searchRoll = sc.nextInt();
                    system.searchStudent(searchRoll);
                    break;

                case 4:
                    system.displayAllStudents();
                    break;

                case 5:
                    System.out.print("Enter Roll Number to Edit: ");
                    int editRoll = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Grade: ");
                    String newGrade = sc.nextLine();

                    system.editStudent(editRoll, newName, newGrade);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 6);

        sc.close();
    }
}