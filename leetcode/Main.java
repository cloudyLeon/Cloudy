package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        char c='A';
        int num=10;
        switch (c){
            case 'B':
                num++;

                case 'A':
                num++;
            case 'Y':
                num++;
                break;

                default:
                    num--;
        }
        System.out.println(num);
    }
    @Test
    public void fun1(){
        int x=5,y=9,z=1,w=9;
        System.out.println((x>y)?(z>w)?x:z:w);
    }

    @Test
    public void fun2(){
        String s="abc,";
        s=s.substring(0,s.lastIndexOf(","));
        System.out.println(s);
    }

    @Test
    public double fun3(byte x,double y){
        return x/y*2;
    }

    @Test
    public void fun4(){
        double a=0.6332;
    }

    @Test
    public void fun5(){
       Object o=new Foo();
       Foo foo=(Foo)o;
        System.out.println(foo.i);
    }

    @Test
    public void fun6(){
        boolean flag;int i=0;
        do{
           flag=false;
            System.out.println(i++);
            flag=i<10;
            continue;
        }while ((flag)?true:false);
    }

    @Test
    public void fun7(){
       int a=1,b=2;
       int c=(a+b>3?a++:++b);
        System.out.println(c);
    }

    @Test
    public void fun8(){
        int []a={'a',23};
        System.out.println(a[0]);
    }

    @Test
    public void fun9(){
        char a=11;
        int b='a';
        System.out.println(a);
        System.out.println(b);
    }

}
class Foo{
    int i=5;

}


