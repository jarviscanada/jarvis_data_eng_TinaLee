package ca.jrvs.practice.dataStructure.list;

import java.util.Arrays;
import java.util.Comparator;

public class EmployeeSort {
  public class EmployeeAgeComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
      return Integer.compare(o1.getAge(), o2.getAge());
    }
  }

  public class EmployeeSalaryComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
      return Long.compare(o1.getSalary(), o2.getSalary());
    }
  }
}
