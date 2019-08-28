package org.demos;

import com.google.common.base.Throwables;

/**
 * @Description
 * @Author liuzhiqiang
 * @Date 2019/8/28 11:15
 */
public class ThrowableExample {
    public static void main(String[] args) {
        try {
            System.out.println(division(null, 0));
        } catch (InvalidInputException e) {
            //e.printStackTrace();

            System.out.println(Throwables.getRootCause(e));;
        } catch (NullPointerException e){
            System.out.println(Throwables.getStackTraceAsString(e));
        }
    }

    static double division(Integer a, Integer b) throws InvalidInputException{
        try{
            if(a == null || b == null){
                throw new NullPointerException();
            }
            if(b == 0){
                throw new InvalidInputException();
            }
            return a / b;
        }catch (Throwable e){
            //custom to throw exception
            Throwables.throwIfInstanceOf(e, InvalidInputException.class);
            Throwables.throwIfInstanceOf(e, NullPointerException.class);
        }
        return 0;
    }
}
