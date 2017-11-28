package org.data.stucture;

/**
 * Created by Administrator on 2017/11/5.
 */
public class Person implements Comparable{
    private String name;
    private int age;

    public Person() {
        this.name = "no name!";
        this.age = 0;
    }

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person that = (Person) obj;
            if (this.name.equals(that.getName()) && this.age == that.getAge()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Person) {
            Person that = (Person) o;
            return this.getAge() - that.getAge();
        }
        return -1;
    }
}
