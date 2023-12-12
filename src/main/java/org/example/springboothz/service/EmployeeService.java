package org.example.springboothz.service;

import lombok.extern.slf4j.Slf4j;
import org.example.springboothz.model.Employee;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class EmployeeService {

    private static final Map<String, Employee> employees = new HashMap<>();

    static {
        employees.put("11111",new Employee("11111","Shubham","Samhita","e6data","Sd1"));
        employees.put("11112",new Employee("11112","Suman","Samhita","Oracle","Sd1"));
        employees.put("11113",new Employee("11113","Saurav","Samhita","TCS","Sd1"));
        employees.put("11114",new Employee("11114","Sumit","Samhita","Ciena","Sd1"));
        employees.put("11115",new Employee("11115","Raj","Samhita","ABC","Sd1"));
    }

    public Employee addAnEmployee(Employee employee){
        String empId = getRandomNumberString();
        while (employees.containsKey(empId)){
            empId = getRandomNumberString();
        }
        employee.setId(empId);
        employees.put(empId,employee);
        return employees.get(empId);
    }
    public List<Employee> getAllEmployee(){

       return employees.values().stream().toList();
    }
    @Cacheable(key = "#id",cacheNames = "employee")
    public Employee getEmployeeById(String id){
        return employees.get(id);
    }
    @CacheEvict(key = "#id",cacheNames = "employee")
    public void deleteById(String id){
        employees.remove(id);
    }
    @CachePut(key = "#id",cacheNames = "employee")
    public Employee updateEmployee(String id , Employee employee){

        return employees.replace(id,employee);
    }


    private static String getRandomNumberString(){

        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d",number);
    }
}
