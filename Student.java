class Student{
    private int id;
    private String name;
    private int age;
    private double marks;
    private String grade;
    

    Student(int id,String name,int age,double marks){
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
        calculateGrade();
    }
    private void calculateGrade(){
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

    //getters
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public double getMarks(){
        return marks;
    }
    public String getGrade(){
        return grade;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setMarks(double marks){
        this.marks = marks;
        calculateGrade(); 
    }



    public void display(){
        System.out.println("ID: "+id+" | Name: "+name+" | Age: "+age+" | Marks: "+marks+" | Grade: "+grade);
    }
}