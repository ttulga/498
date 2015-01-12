package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {

  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private int count = 0;

  public static class AgeComparator implements Comparator<Person>
  {
    public int compare(Person p1, Person p2) {
      return p1.age - p2.age;
    }
  }

  @Override
  public int compareTo(Person other) {
    return (int)(other.salary - this.salary);
  }

  public Person() {
    this("", 0, 0.0d);
    count++;
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
  }

  public int getAge() {
    return age;
  }
  
  public void setAge(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Age " + value + " entered is not valid");
    }
    
    int old = age;
    age = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String value) {
    if (value == null) {
      throw new IllegalArgumentException("Name " + value + " entered is null");
    }

    String old = name;
    name = value;
  }
  
  public double getSalary() {
    return salary;
  }

  public void setSalary(double value) {
    double old = salary;
    salary = value;
  }
  
  public String getSSN() {
    return ssn;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Person) {
      Person p = (Person)other;
      return p.name == this.name && p.age == this.age;
    }
    return false;
  }

  @Override
  public String toString() {
    return "[Person name:" + name + " age:" + age +" salary:" + salary +"]";
  }

  public int count(){
    return count;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> family = new ArrayList<Person>();
    Person Ted = new Person("Ted", 41, 250000);
    Person Charlotte = new Person("Charlotte", 43, 150000);
    Person Michael = new Person("Michael", 22, 10000);
    Person Matthew = new Person("Matthew", 15, 0);

    family.add(Ted);
    family.add(Charlotte);
    family.add(Michael);
    family.add(Matthew);

    return family;
  }
}

