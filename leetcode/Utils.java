package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ Author :cloudy
 * @ Date   :Created in 10:44 2019/4/15
 * @ Description: 字段工具类
 */
public class Utils {
    public void comp(String s1,String s2){
        s1=s1.toLowerCase();
        s2=s2.toLowerCase();
        String[] arys1=s1.split(";");
        String[] arys2=s2.split(";");
        Set<String> set1=new HashSet(Arrays.asList(arys1));
        Set<String> set2=new HashSet();
        for(int i=0;i<arys2.length;i++){
            if(set1.contains(arys2[i])){
                set1.remove(arys2[i]);
            }else {
                set2.add(arys2[i]);
            }
        }
        System.out.print("s1字段：");
        for(String s:set1){
            System.out.print(s+",");
        }
        System.out.println();
        System.out.print("s2字段：");
        for(String s:set2){
            System.out.print(s+",");
        }
    }

    @Test
    public void fun(){
        String s1="ACC230;" +
                "AAC001;" +
                "ACC200;" +
                "ACA111;" +
                "ACA112;" +
                "ACB216;" +
                "ACC034;" +
                "ACC034_DSC;" +
                "ACC034_L;" +
                "ACC034_H;" +
                "YCB213;" +
                "YCB213_DSC;" +
                "ACB215;" +
                "ACB217;" +
                "ACC035;" +
                "ACC035_DSC;" +
                "ACB248;" +
                "ACB248_DSC;";
        String s2="ACC210;" +
                "AAC001;" +
                "ACC200;" +
                "ACA111;" +
                "ACA112;" +
                "ACB216;" +
                "AAE043;" +
                "AAE030;" +
                "AAE031;" +
                "YCB21Y;" +
                "YCC211;" +
                "ACC034;" +
                "ACC034_L;" +
                "ACC034_H;" +
                "YCB213;" +
                "ACB215;" +
                "ACB217;" +
                "YCB21C;" +
                "YCB21D;" +
                "ACC214;" +
                "YCC213;" +
                "YCB218;" +
                "YCB219;" +
                "YCB21A;" +
                "AAE013;" +
                "YCB2A0;" +
                "YCB20H;" +
                "YAE481;" +
                "YAE485;" +
                "YAE482;" +
                "YAE483;" +
                "AAE011;" +
                "YAE116;" +
                "AAE017;" +
                "AAE036;" +
                "AAE163;" +
                "YCC21A;" +
                "YCC21B;" +
                "YCB21F;" +
                "ACC217;" +
                "AAE100;" +
                "ACC034_DSC;" +
                "YCB213_DSC;" +
                "ACC035;" +
                "ACC035_DSC;" +
                "ACB248;" +
                "ACB248_DSC;";

        comp(s1,s2);
    }
}
