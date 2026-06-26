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
        for(Student s : students){
            if(s.id == id){
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
        Collections.sort(students, (a, b) -> a.id - b.id);
        fm.saveToFile(students);
        System.out.println("Students sorted by ID");
    }

    void searchStudent(int id){
        for(Student s : students){
            if(s.id == id){
                s.display();
                return;
            }
        }
        System.out.println("Student not found");
    }

    void updateStudent(int id, String name, int age,double marks){
        for(Student s : students){
            if(s.id == id){
                s.name = name;
                s.age = age;
                s.marks = marks;
                s.calculateGrade();
                fm.saveToFile(students);
                System.out.println("Student updated successfully");
                return;
            }
        }
        System.out.println("Student not found");
    }

    void deleteStudent(int id){
        for(int i = 0; i < students.size();i++){
            if(students.get(i).id == id){
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
            if(s.name.equalsIgnoreCase(name)){
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
            if(s.grade.equalsIgnoreCase(grade)){
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
            if(s.age == age){
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
            totalAge += s.age;
        }
        return (double)totalAge / students.size();
    }

    double highestMarks(){
        if(students.isEmpty()){
            return 0;
        }
        double highest = students.get(0).marks;
        for(Student s : students){
            if(s.marks > highest){
                highest = s.marks;
            }
        }
        return highest;
    }

    double lowestMarks(){
        if(students.isEmpty()){
            return 0;
        }
        double lowest = students.get(0).marks;
        for(Student s : students){
            if(s.marks < lowest){
                lowest = s.marks;
            }
        }
        return lowest;
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
        try{
            FileWriter fw = new FileWriter("report.txt");
            fw.write("-----Student Report-----\n\n");
            fw.write("Total Students: " + totalStudents() + "\n");
            fw.write("Average Age: " + String.format("%.2f", averageAge()) + "\n");
            fw.write("Highest Marks: " + highestMarks() + "\n");
            fw.write("Lowest Marks: " + lowestMarks() + "\n");
            fw.close();
            System.out.println("Report generated successfully");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    

    
}