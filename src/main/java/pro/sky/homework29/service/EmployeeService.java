package pro.sky.homework29.service;

import pro.sky.homework29.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee add(String firstName, String lastNme, Integer department, Double salary);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    List<Employee> findAll();
}
