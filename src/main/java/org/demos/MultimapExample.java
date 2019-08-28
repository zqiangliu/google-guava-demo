package org.demos;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

/**
 * @Description
 * @Author liuzhiqiang
 * @Date 2019/8/28 16:36
 */
public class MultimapExample {
    public static void main(String[] args) {
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("companyA", "employee1");
        multimap.put("companyA", "employee2");
        multimap.put("companyA", "employee3");

        multimap.put("companyB", "employee4");
        multimap.put("companyB", "employee5");
        multimap.put("companyB", "employee6");

        Collection<String> employeesOfA = multimap.get("companyA");
        System.out.println(employeesOfA);

        //add new employee to companyA
        employeesOfA.add("employee10");
        System.out.println(multimap.get("companyA"));


        //java 8+ map api
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("companyC", Arrays.asList("employee1"));

        List<String> list = map.computeIfAbsent("companyA", k -> new ArrayList<>());
        list.add("employee12");

        List computedList = map.compute("companyD", (k, v) -> {
            v = new ArrayList<>();
            v.add("employee15");
            return v;
        });
        computedList.add("employee16");

        List<String> newList = map.putIfAbsent("companyB", new ArrayList<>());
        //newList.add("employee11");  //null pointer exception, cause putIfAbsent return null if key not exists
        System.out.println(map);

    }
}
