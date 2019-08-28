package org.demos;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

/**
 * @Description
 * @Author liuzhiqiang
 * @Date 2019/8/28 15:14
 */
public class StringExample {
    public static void main(String[] args) {

        //joiner example
        String str = Joiner.on(",").skipNulls().join(Arrays.asList(1, 3, 5, 7, 9, null));
        System.out.println(str);

        Employee e1 = Employee.builder().id(1).name("david").build();
        Employee e2 = Employee.builder().id(2).name("james").build();
        Employee e3 = Employee.builder().id(2).name("oven").build();
        String str2 = Joiner.on("#").join(Arrays.asList(e1, e2, e3));
        System.out.println(str2);

        //splitter example
        String str3 = "google,  guava,beam,  ,java util  , framework";
        Iterable<String> segments = Splitter.on(",").trimResults().omitEmptyStrings().split(str3);
        System.out.println(segments);

        //char matcher
        System.out.println(CharMatcher.whitespace().trimAndCollapseFrom("java framework    guava, hello world    !   ", ' '));
        System.out.println(CharMatcher.inRange('a', 'f').matchesAllOf("abcf"));
        System.out.println(CharMatcher.digit().retainFrom("java123, guava456!"));
        System.out.println(CharMatcher.javaLetter().replaceFrom("java123, guava456!", "*"));

        //case format
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "user_account"));
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, "user-account"));
    }

    @Data
    @Builder
    static class Employee{
        private Integer id;
        private String name;
    }
}
