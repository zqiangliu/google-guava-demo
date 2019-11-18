#### Installation
*maven*
```xml
<dependencies>
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>28.0-jre</version>
        </dependency>
</dependencies>
```
#### StringExample
```java
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
```
#### ObjectExample
```java
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
        System.out.printf("p1.equals(p3):%b\n", Objects.equals(p1, p3));

        System.out.printf("list contains p2:%b\n", list.contains(p2));
        System.out.printf("list contains p3:%b\n", list.contains(p3));
        list.add(p2);
        list.add(p3);
        System.out.println("before sort:");
        list.stream().forEach(n -> System.out.println(Objects.toString(n)));
        Collections.sort(list);
        System.out.println("after sort:");
        list.stream().forEach(n -> System.out.println(Objects.toString(n)));
    }
}
```

#### CacheExample
*create a loading cache*
```java
LoadingCache<Integer, Employee> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(Duration.ofMinutes(2))
                .build(new CacheLoader<Integer, Employee>() {
                    @Override
                    public Employee load(Integer key) throws Exception {
                        return getEmployeeFromDb(key);
                    }
                });
Employee getEmployeeFromDb(int id) {
        Employee emp1 = Employee.builder().id(1).name("Jorney").build();
        Employee emp2 = Employee.builder().id(2).name("Norval").build();
        Employee emp3 = Employee.builder().id(3).name("James").build();
        HashMap<Integer, Employee> db = new HashMap<>();
        Arrays.asList(emp1, emp2, emp3).forEach(e -> db.put(e.getId(), e));

        System.out.println("database query: id= " + id);
        return db.get(id);
    }
```
*use loading cache*
```java
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
```

#### PreconditionExample
*precondition is a way to check some logic before doing next work*
```java
public class PreconditionExample {
    public static void main(String[] args) {
        Integer a = null, b = 0;
        try {
            System.out.println(division(a, b));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static double division(Integer a, Integer b) throws IllegalArgumentException, NullPointerException{
        Preconditions.checkNotNull(a, "a=null is not allowed");
        Preconditions.checkNotNull(b, "b=null is not allowed");
        Preconditions.checkArgument(b != 0, "b=0 is not allowed");
        return a / b;
    }
}
```