package org.demos;

import com.google.common.collect.Collections2;
import com.google.common.collect.ComparisonChain;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.*;

/**
 * @Description
 * @Author liuzhiqiang
 * @Date 2019/8/27 16:57
 */
public class ObjectsExample {
    @Data
    @Builder
    static class Person implements Comparable<Person> {
        private String name;
        private int gender;
        private Date birth;

        @Override
        public int compareTo(Person o) {
            return ComparisonChain.start().compare(this.birth, o.getBirth()).result();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return com.google.common.base.Objects.equal(name, person.name);
        }

        @Override
        public int hashCode() {
            return com.google.common.base.Objects.hashCode(name);
        }
    }
    public static void main(String[] args) {
        Date d1 = new DateTime(1988, 8, 15, 0, 0, 0).toDate();
        Date d2 = new DateTime(1989, 2, 15, 0, 0, 0).toDate();
        Date d3 = new DateTime(1977, 10, 15, 0, 0, 0).toDate();
        Person p1 = Person.builder().name("david").gender(1).birth(d1).build();
        Person p2 = Person.builder().name("jane").gender(2).birth(d2).build();
        Person p3 = Person.builder().name("david").gender(2).birth(d3).build();

        List<Person> list = new ArrayList<>();
        list.add(p1);

        System.out.println(Objects.toString(p1));

        System.out.printf("p1.equals(p2):%b\n", Objects.equals(p1, p2));

        System.out.printf("list contains p2:%b\n", list.contains(p2));

        list.add(p2);
        list.add(p3);
        System.out.println("before sort:");
        list.stream().forEach(n -> System.out.println(Objects.toString(n)));
        Collections.sort(list);
        System.out.println("after sort:");
        list.stream().forEach(n -> System.out.println(Objects.toString(n)));
    }
}
