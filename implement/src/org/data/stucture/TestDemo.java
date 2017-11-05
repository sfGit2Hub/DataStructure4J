package org.data.stucture;

/**
 * Created by Administrator on 2017/11/5.
 */
public class TestDemo {
    public static void main(String[] args) {
        LinkListS<Person> persons = new LinkListS<>();
        for (int i=0; i<11; i++) {
            Person person = new Person("name"+i, i);
            persons.add(person);
        }

        for (int i=0; i<persons.size(); i++) {
            System.out.println(persons.get(i));
        }
    }
}
