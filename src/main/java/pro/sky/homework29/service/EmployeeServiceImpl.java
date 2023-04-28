package pro.sky.homework29.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.homework29.exeption.EmployeeAlreadyAddedException;
import pro.sky.homework29.exeption.EmployeeNotFoundException;
import pro.sky.homework29.exeption.InvalidInputException;
import pro.sky.homework29.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
    public class EmployeeServiceImpl implements EmployeeService {
        private final Map<String, Employee> employees;

        public EmployeeServiceImpl() {
            this.employees = new HashMap<>();
        }
        @Override
        public Employee add(String firstName, String lastName, Integer department, Double salary) {
            if (!validateInput(firstName, lastName)) {
                throw new InvalidInputException();
            }
            Employee employee = new Employee(firstName, lastName, department, salary);
            if (employees.containsKey(employee.getFullName())) {
                throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в базе");
            }
            employees.put(employee.getFullName(), employee);
            return employee;
        }
        @Override
        public Employee remove(String firstName, String lastName) {
            if (!validateInput(firstName, lastName)) {
                throw new InvalidInputException();
            }
            Employee employee = new Employee(firstName, lastName);
            employees.remove(employee.getFullName());
            return employee;
        }
        @Override
        public Employee find(String firstName, String lastName) {
            if (!validateInput(firstName, lastName)) {
                throw new InvalidInputException();
            }
            Employee employee = new Employee(firstName, lastName);
            if (employees.containsKey(employee.getFullName())) {
                return employee;
            }
            throw new EmployeeNotFoundException("Сотрудник с такими данными не найден");
        }
        @Override
        public List<Employee> findAll() {
            return new ArrayList<Employee>(employees.values());
        }
        private boolean validateInput(String firstName, String lastName) {
            return isAlpha(firstName) && isAlpha(lastName);
        }
}
