import java.util.*;
import java.io.*;
class StudentService{
    ArrayList<Student> students = new ArrayList<Student>();
    
    FileManager fm = new FileManager();
    StudentService(){
        students = fm.loadFile();
    }
    
    void addStudent(
        int id,
        String name,
        int age,
        double marks
    ) {
        if(age <= 0 || age > 120){
            throw new IllegalArgumentException("Invalid age. Please enter a valid age.");
        }
        if(marks < 0 || marks > 100){
            throw new IllegalArgumentException("Invalid marks. Please enter valid marks.");
        }
        for(Student s : students){
            if(s.getId() == id){
                System.out.println("Student with this ID already exists");
                return;
            }
        }
        students.add(new Student(id, name, age,marks));
        fm.saveToFile(students);
        System.out.println("Student added successfully");
        
    }

    void viewStudents() {
        if(students.isEmpty()) {
            System.out.println("No students to display");
            return;
        }
        
        for (Student s : students) {
            s.display();
        }
    }

    void sortStudents(){
        students.sort(Comparator.comparingInt(Student::getId));
        fm.saveToFile(students);
        System.out.println("Students sorted by ID");
    }

    void searchStudentById(int id){
        for(Student s : students){
            if(s.getId() == id){
                s.display();
                return;
            }
        }
        System.out.println("Student not found");
    }

    void updateStudent(int id, String name, int age,double marks){
        if(age <= 0 || age > 120){
            throw new IllegalArgumentException("Invalid age. Please enter a valid age.");
        }
        if(marks < 0 || marks > 100){
            throw new IllegalArgumentException("Invalid marks. Please enter valid marks.");
        }
        for(Student s : students){
            if(s.getId() == id){
                s.setName(name);
                s.setAge(age);
                s.setMarks(marks);
                fm.saveToFile(students);
                System.out.println("Student updated successfully");
                return;
            }
        }
        System.out.println("Student not found");
    }

    void deleteStudent(int id){
        for(int i = 0; i < students.size();i++){
            if(students.get(i).getId() == id){
                students.remove(i);
                fm.saveToFile(students);
                System.out.println("Student deleted successfully");
                return;
            }
        }
        System.out.println("Student not found");
    }

    //filter students by name, grade, age

    void searchByName(String name){
        boolean found = false;
        for(Student s : students){
            if(s.getName().equalsIgnoreCase(name)){
                s.display();
                found = true;
            }
        }
        if(!found){
            System.out.println("Student not found");
        }
    }

    void searchByGrade(String grade){
        boolean found = false;
        for(Student s : students){
            if(s.getGrade().equalsIgnoreCase(grade)){
                s.display();
                found = true;
            }
        }
        if(!found){
            System.out.println("Student not found");
        }
    }

    void searchByAge(int age){
        boolean found = false;
        for(Student s : students){
            if(s.getAge() == age){
                s.display();
                found = true;
            }
        }
        if(!found){
            System.out.println("Student not found");
        }
    }
    

    int totalStudents(){
        return students.size();
    } 

    double averageAge(){
        if(students.isEmpty()){
            return 0;
        }
        int totalAge = 0;
        for(Student s : students){
            totalAge += s.getAge();
        }
        return (double)totalAge / students.size();
    }

    double highestMarks(){
        if(students.isEmpty()){
            return 0;
        }
        double highest = students.get(0).getMarks();
        for(Student s : students){
            if(s.getMarks() > highest){
                highest = s.getMarks();
            }
        }
        return highest;
    }

    double lowestMarks(){
        if(students.isEmpty()){
            return 0;
        }
        double lowest = students.get(0).getMarks();
        for(Student s : students){
            if(s.getMarks() < lowest){
                lowest = s.getMarks();
            }
        }
        return lowest;
    }

    double passPercentage(){
        if(students.isEmpty()){
            return 0;
        }
        int passCount = 0;
        for(Student s : students){
            if(!s.getGrade().equals("FAIL")){
                passCount++;
            }
        }
        return (passCount * 100.0) / students.size();
    }

    void gradeDistribution() {
        int a = 0, b = 0, c = 0, fail = 0;
        for (Student s : students) {
        String g = s.getGrade();

        if (g.equals("A")) a++;
        else if (g.equals("B")) b++;
        else if (g.equals("C")) c++;
        else fail++;
    }
    System.out.println("Grade Distribution:");
    System.out.println("A: " + a);
    System.out.println("B: " + b);
    System.out.println("C: " + c);
    System.out.println("FAIL: " + fail);
}

    //pagination
    
    void viewStudentsByPage(int page,int size){
        if(students.isEmpty()){
            System.out.println("No students to display");
            return;
        }
        int start = (page -1)*size;
        int end = start + size;
        if(start >= students.size()){
            System.out.println("Page does not exist");
            return;
        }
        if(end > students.size()){
            end = students.size();
        }
        System.out.println("Page " + page);
        for(int i = start; i < end;i++){
            students.get(i).display();
        }
    }

    //to get this as report - saved file

    void generateReport(){
        if(students.isEmpty()){
            System.out.println("No students to generate report");
            return;
        }
        try(FileWriter fw = new FileWriter("report.txt")){
            
            fw.write("-----Student Report-----\n\n");
            fw.write("Total Students: " + totalStudents() + "\n");
            fw.write("Average Age: " + String.format("%.2f", averageAge()) + "\n");
            fw.write("Highest Marks: " + highestMarks() + "\n");
            fw.write("Lowest Marks: " + lowestMarks() + "\n");
            fw.write("Pass Percentage: " + String.format("%.2f", passPercentage()) + "%\n");
            fw.write("\nGrade Distribution:\n");
            int a = 0, b = 0, c = 0, fail = 0;
            for (Student s : students) {
                String g = s.getGrade();
                if (g.equals("A")) a++;
                else if (g.equals("B")) b++;
                else if (g.equals("C")) c++;
                else fail++;
            }
            fw.write("A: " + a + "\n");
            fw.write("B: " + b + "\n");
            fw.write("C: " + c + "\n");
            fw.write("FAIL: " + fail + "\n");

            System.out.println("Report generated successfully");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    //csv export
    void exportToCSV() {
    if (students.isEmpty()) {
        System.out.println("No students to export");
        return;
    }

    try (FileWriter fw = new FileWriter("students.csv")) {

        // 1. HEADER ROW (IMPORTANT FOR EXCEL)
        fw.write("ID,Name,Age,Marks,Grade\n");

        // 2. DATA ROWS
        for (Student s : students) {
            fw.write(
                s.getId() + "," +
                s.getName() + "," +
                s.getAge() + "," +
                s.getMarks() + "," +
                s.getGrade() + "\n"
            );
        }

        System.out.println("CSV file created successfully: students.csv");

    } catch (Exception e) {
        System.out.println("Error exporting CSV: " + e.getMessage());
    }
}
    
}