class Student{
    int id;
    String name;
    int age;

    double marks;
    String grade;

    Student(int id,String name,int age,double marks){
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
        calculateGrade();
    }
    void calculateGrade(){
        if(marks >= 90){
            grade = "A";
        }else if(marks >= 75){
            grade = "B";
        }else if(marks >= 50){
            grade = "C";
        }else{
            grade = "FAIL";
        }
    }
    void display(){
        System.out.println("ID: "+id+" | Name: "+name+" | Age: "+age+" | Marks: "+marks+" | Grade: "+grade);
    }
}