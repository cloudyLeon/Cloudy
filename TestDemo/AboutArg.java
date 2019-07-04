package TestDemo;

public class AboutArg {

    public static void main(String[] args) {
        Student s1=new Student();
        s1.num=new Integer(1);
        s1.age=new Integer(1);
        s1.student=new Student();
        System.out.println(s1);
        System.out.println(s1.student);
        t1(s1);
        System.out.println(s1);
        System.out.println(s1.student);
    }
    public static void t1(Student student){
        student.student=new Student();
        student.num=0;
        student.age=0;
    }
}
class Student{
    public Integer num;
    public Integer age;
    public Student student;
}
