package org.demos;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Arrays;
import java.util.Set;

/**
 * @Description
 * @Author liuzhiqiang
 * @Date 2019/8/28 11:32
 */
public class MultisetExample {
    public static void main(String[] args) {
        Multiset<Integer> multiSet = HashMultiset.create(()->Arrays.asList(1, 2, 5, 7, 8, 2, 4, 1, 5, 7, 9).iterator());
        System.out.printf("total size: %d\n", multiSet.size());
        Set<Integer> elementSet = multiSet.elementSet();
        System.out.printf("distinct element set: ");
        elementSet.stream().forEach(e -> System.out.printf("%d ", e));
        System.out.println();
        System.out.printf("count of 5: %d\n", multiSet.count(5));

        Set<Multiset.Entry<Integer>> entries = multiSet.entrySet();
        entries.stream().forEach(en -> System.out.printf("element: %d, occur: %d\n", en.getElement(), en.getCount()));
    }
}
