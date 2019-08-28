package org.demos;

import com.google.common.collect.Ordering;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author liuzhiqiang
 * @Date 2019/8/28 10:36
 */
public class OrderingExample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 5, 9, 7, 2, 3, 11, 24, 6, 9, 0, null);
        System.out.println("before sort:");
        System.out.println(list);
        Ordering ordering = Ordering.natural().nullsFirst();
        Collections.sort(list, ordering);
        System.out.println("after sort:");
        System.out.println(list);

        System.out.print("max:");
        System.out.println(ordering.max(list));


    }
}
