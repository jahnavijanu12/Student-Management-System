import java.io.*;
import java.util.*;
public class FileManager {

    //save student data
    public void saveToFile(ArrayList<Student> students) {
        try{
            FileWriter fw = new FileWriter("students.txt");
            for(Student s : students){
                fw.write(s.getId() + "," + s.getName() + "," + s.getAge() + "," + s.getMarks() + "\n");
            }
            fw.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public ArrayList<Student> loadFile(){
        ArrayList<Student> students = new ArrayList<Student>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("students.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] arr = line.split(",");
                if(arr.length != 4){
                    continue;
                }
                int id = Integer.parseInt(arr[0]);
                String name = arr[1];
                int age = Integer.parseInt(arr[2]);
                double marks = Double.parseDouble(arr[3]);
                students.add(new Student(id, name, age, marks));
            }
            br.close();
        }catch(Exception e){
            System.out.println("Error loading file: " + e);

        }
        return students;
    }
    
}
