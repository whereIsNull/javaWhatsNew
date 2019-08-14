import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList;

public class StreamApi {

    public static void printPersonsOlderThan(List<Person> roster, int age) {
        // Java 7
        System.out.println("Print java 7");
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }

        // Java 8
        System.out.println("Print java 8");
        roster.stream().forEach(a -> {
            if(a.getAge() >= age) a.printPerson();
        });

        // Java 8 with filter
        System.out.println("Print java 8 with filter");
        roster.stream().filter(a -> a.getAge() >= age).forEach(Person::printPerson);
    }

    public static void main(String[] args) {
        System.out.println("Clase de practica para streams");
        List<Person> people = new ArrayList<>();
        for(int i=0; i<5; i++) {
            Person p = new Person("name " + i, i, Person.Sex.MALE, "mail@mail" + i);
            people.add(p);
        }
        printPersonsOlderThan(people, 2);

        System.out.println("create stream from builder");
        Stream.Builder<Person> builder = Stream.builder();
            builder
                .add(new Person("name " + 1, 1, Person.Sex.MALE, "mail@mail" + 1))
                .add(new Person("name " + 2, 2, Person.Sex.MALE, "mail@mail" + 2))
                .add(new Person("name " + 3, 3, Person.Sex.FEMALE, "mail@mail" + 3))
                .add(new Person("name " + 4, 4, Person.Sex.MALE, "mail@mail" + 4))
                .add(new Person("name " + 5, 5, Person.Sex.FEMALE, "mail@mail" + 5));
        builder.build().filter(a -> a.getAge() >= 2).forEach(Person::printPerson);
    }
}

class Person {

    public enum Sex {
        MALE, FEMALE
    }

    public Person(String name, int age, Sex gender, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.emailAddress = email;
    }

    String name;
    int age;
    Sex gender;
    String emailAddress;

    public int getAge() {
        return this.age;
    }

    public void printPerson() {
        System.out.println(name + ", " + gender.name() + ", " + emailAddress + ", " + this.age);
    }
}