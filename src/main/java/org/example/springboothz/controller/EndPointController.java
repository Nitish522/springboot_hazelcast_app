package org.example.springboothz.controller;

import org.example.springboothz.model.Employee;
import org.example.springboothz.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "employee",produces = "application/json")
public class EndPointController {
    EmployeeService employeeService;
    @Autowired
    EndPointController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployee(@RequestParam String id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @PostMapping (value = "/")
    @ResponseStatus(HttpStatus.OK)
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.addAnEmployee(employee);
    }

    @PutMapping (value = "/")
    @ResponseStatus(HttpStatus.OK)
    public Employee createEmployee(@RequestParam String id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id,employee);
    }

    @DeleteMapping (value = "/")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@RequestParam String id){
         employeeService.deleteById(id);
    }
}
