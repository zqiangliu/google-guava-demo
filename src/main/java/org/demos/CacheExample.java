package org.demos;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * @Description
 * @Author liuzhiqiang
 * @Date 2019/8/28 11:56
 */
public class CacheExample {
    @Data
    @Builder
    @ToString
    static class Employee{
        private Integer id;
        private String name;
    }

    public static void main(String[] args) {
        LoadingCache<Integer, Employee> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(Duration.ofMinutes(2))
                .build(new CacheLoader<Integer, Employee>() {
                    @Override
                    public Employee load(Integer key) throws Exception {
                        return getEmployeeFromDb(key);
                    }
                });

//        try {
//            System.out.println("# round 1");
//            loadingCache.get(1);
//            loadingCache.get(2);
//            loadingCache.get(3);
//            System.out.println("# round 2");
//            loadingCache.get(1);
//            loadingCache.get(2);
//            loadingCache.get(3);
//
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        Range<Integer> range1 = Range.closed(1, 6);
        for(int i : ContiguousSet.create(range1, DiscreteDomain.integers())){
            Thread t = new Thread(()->{
                try {

                    Employee emp = loadingCache.get(i<=3 ? i : i - 3);
                    System.out.printf("%s got %s\n", Thread.currentThread().getName(), emp);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }

    }

    static Employee getEmployeeFromDb(int id) {
        Employee emp1 = Employee.builder().id(1).name("Jorney").build();
        Employee emp2 = Employee.builder().id(2).name("Norval").build();
        Employee emp3 = Employee.builder().id(3).name("James").build();
        HashMap<Integer, Employee> db = new HashMap<>();
        Arrays.asList(emp1, emp2, emp3).forEach(e -> db.put(e.getId(), e));

        System.out.println("database query: id= " + id);
        return db.get(id);
    }
}
