package TestDemo;

/**
 * @ Author :cloudy
 * @ Date   :Created in 14:36 2019/5/7
 * @ Description:
 */
public class INTConstant {
    public final static Integer[]NUMARRAY=new Integer[101];
    static{
        for(int i=0;i<=100;i++){
            NUMARRAY[i]=i;
        }
    }
}
