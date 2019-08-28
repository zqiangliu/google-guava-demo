package org.demos;


import com.google.common.base.Optional;


/**
 * @Description
 * @Author liuzhiqiang
 * @Date 2019/8/27 16:50
 */
public class OptionalExample {
    static Integer sum(Integer a, Integer b){
        return a + b;
    }

    static Integer sum(Optional<Integer> a, Optional<Integer> b){
        System.out.printf("a isPresent: %b, b isPresent: %b\n", a.isPresent(), b.isPresent());
        return a.or(2) + b.get();
    }

    public static void main(String[] args) {
//        OptionalInt optionalInt = OptionalInt.of(1);
//        System.out.printf("isPresent:%b,value:%d\n", optionalInt.isPresent(), optionalInt.getAsInt());


        Integer a = null, b = 1;
//        Optional<Integer> optionalA = Optional.of(a); //check null
        Optional<Integer> optionalA = Optional.fromNullable(a); //not check null
        System.out.println("continue to set var 'b'");
        Optional<Integer> optionalB = Optional.of(b);
        System.out.println(sum(optionalA, optionalB));
    }
}
