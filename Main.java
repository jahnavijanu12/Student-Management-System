import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Username: ");
        String username = sc.next();
        System.out.print("Enter Password: ");
        String password = sc.next();
        LoginService ls = new LoginService();
        if(!ls.login(username, password)){
            System.out.println("Invalid username or password");
            sc.close();
            return;
        }
        System.out.println("Login successful");

        StudentService ss = new StudentService();

        while(true){
            System.out.println("\nStudent Management System");

            System.out.println(" 1 Add Student");
            System.out.println(" 2 View Students");
            System.out.println(" 3 Search Student");
            System.out.println(" 4 Update Student");
            System.out.println(" 5 Delete Student");
            System.out.println(" 6 Sort Students by ID");
            System.out.println(" 7 View Students by Page");
            System.out.println(" 8 Search Student by Name");
            System.out.println(" 9 Search Student by Grade");
            System.out.println(" 10 Generate Report");
            System.out.println(" 11 Search Student by Age");
            System.out.println(" 12 Total Students");
            System.out.println(" 13 Average Age");
            System.out.println(" 14 Highest Marks");
            System.out.println(" 15 Lowest Marks");
            System.out.println(" 16 Exit");
            System.out.println("17 Export Students to CSV");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter id: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    try{
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    if(age <= 0 || age > 120){
                        System.out.println("Invalid age. Please enter a valid age.");
                        break;
                    }
                    System.out.print("Enter marks: ");
                    double marks = sc.nextDouble();
                    if(marks < 0 || marks > 100){
                        System.out.println("Invalid marks. Please enter valid marks.");
                        break;
                    }
                    ss.addStudent(id, name, age, marks);
                    }catch(Exception e){
                        System.out.println("Invalid input. Please enter valid age and marks.");
                        sc.nextLine();
                    }
                    break;  
                case 2:
                    ss.viewStudents();
                    break;
                case 3:
                    System.out.print("Enter id to search: ");
                    try{
                    int searchId = sc.nextInt();
                    ss.searchStudentById(searchId);
                    }catch(Exception e){
                        System.out.println("Invalid input. Please enter a valid id.");
                        sc.nextLine();
                    }
                    break;
                case 4:
                     System.out.print("Enter id to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new age: ");
                    int newAge = sc.nextInt();
                    if(newAge <= 0 || newAge > 120){
                        System.out.println("Invalid age. Please enter a valid age.");
                        break;
                    }
                    System.out.print("Enter new marks: ");
                    double newMarks = sc.nextDouble();
                    if(newMarks < 0 || newMarks > 100){
                        System.out.println("Invalid marks. Please enter valid marks.");
                        break;
                    }
                    ss.updateStudent(updateId, newName, newAge, newMarks);
                    break;
                case 5:
                    System.out.print("Enter id to delete: ");
                    try{
                    int deleteId = sc.nextInt();
                    ss.deleteStudent(deleteId); 
                    }catch(Exception e){
                        System.out.println("Invalid input. Please enter a valid id.");
                        sc.nextLine();
                    }
                    break;
                   
                case 6:
                    ss.sortStudents();
                    break;
                case 7:
                    System.out.print("Enter Page Number: ");
                    int page = sc.nextInt();
                    System.out.print("Enter Page Size: ");
                    int size = sc.nextInt();
                    ss.viewStudentsByPage(page, size);
                    break;
                case 8:
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    ss.searchByName(sc.nextLine());
                    break;
                case 9:
                    System.out.print("Enter Grade: ");
                    ss.searchByGrade(sc.next());
                    break;
                case 10:
                    ss.generateReport();
                    break;
                case 11:
                    System.out.print("Enter Age: ");
                    try{
                        int age = sc.nextInt();
                        ss.searchByAge(age);
                    }catch(Exception e){
                        System.out.println("Invalid input. Please enter a valid age.");
                        sc.nextLine();
                    }
                    break;
                case 12:
                    System.out.println("Total Students: " + ss.totalStudents());
                    break;
                case 13:
                    System.out.println("Average Age: " + ss.averageAge());
                    break;
                case 14:
                    System.out.println("Highest Marks: " + ss.highestMarks());
                    break;
                case 15:
                    System.out.println("Lowest Marks: " + ss.lowestMarks());
                    break; 
                case 16:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                case 17:
                    ss.exportToCSV();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }                    
        }
    }
}