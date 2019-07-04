package TestDemo;

/**
 * @ Author :cloudy
 * @ Date   :Created in 10:03 2019/5/8
 * @ Description:
 */
public class Tstatic {
    static {
        print();
    }
    public  Tstatic(Boolean b){

    }
    public Tstatic(){
        System.out.println("a");
    }

    public static void print(){
        System.out.println("init...");
    }
}
class SON extends Tstatic {
    public SON() {
    }
}
