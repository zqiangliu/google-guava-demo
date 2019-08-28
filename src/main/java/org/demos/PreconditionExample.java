package org.demos;

import com.google.common.base.Preconditions;

/**
 * @Description
 * @Author liuzhiqiang
 * @Date 2019/8/27 17:54
 */
public class PreconditionExample {
    public static void main(String[] args) {
        Integer a = null, b = 0;
        try {
            System.out.println(division(a, b));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static double division(Integer a, Integer b) throws IllegalArgumentException, NullPointerException{
        Preconditions.checkNotNull(a, "a=null is not allowed");
        Preconditions.checkNotNull(b, "b=null is not allowed");
        Preconditions.checkArgument(b != 0, "b=0 is not allowed");
        return a / b;
    }
}
