package org.demos;

import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * @Description
 * @Author liuzhiqiang
 * @Date 2019/8/28 16:24
 */
public class IntsExample {
    public static void main(String[] args) {
        List<Integer> list = Ints.asList(1, 3, 5, 7, 9);

        int[] array = Ints.toArray(list);


        System.out.printf("array contains 5: %b\n", Ints.contains(array, 5));

        System.out.printf("array max element is: %d\n", Ints.max(array));

        System.out.println(Ints.join("|", array));

        System.out.println(Ints.tryParse("12"));

    }
}
