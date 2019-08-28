package org.demos;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

/**
 * @Description
 * @Author liuzhiqiang
 * @Date 2019/8/28 11:01
 */
public class RangeExample {
    public static void main(String[] args) {
        Range<Integer> range1 = Range.closed(0, 10);
        Range<Integer> range2 = Range.openClosed(8, 15);
        Range<Integer> range3 = Range.closedOpen(-1, 6);
        System.out.printf("range1 is connected with range2: %b\n", range1.isConnected(range2));
        System.out.printf("range1 is connected with range3: %b\n", range1.isConnected(range3));
        System.out.printf("range1 contains [2,3,4]: %b\n", range1.containsAll(Ints.asList(2,3,4)));
        //print elements in range
        for(int e : ContiguousSet.create(range3, DiscreteDomain.integers())){
            System.out.print(e + " ");
        }
    }
}
