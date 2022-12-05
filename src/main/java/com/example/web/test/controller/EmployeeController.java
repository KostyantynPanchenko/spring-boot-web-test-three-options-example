package com.example.web.test.controller;

import com.example.web.test.domain.Employee;
import com.example.web.test.exception.EmployeeNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EmployeeController {

  private final Map<Long, Employee> repository;

  EmployeeController() {
    repository = new HashMap<>();
    repository.put(23L, new Employee(23L, "MJ", "Shooting Guard"));
    repository.put(33L, new Employee(33L, "Pip", "Forward"));
  }

  @GetMapping("/employees")
  public Collection<Employee> getAll() {
    return repository.values();
  }

  @GetMapping("/employees/{id}")
  public Employee getOne(@PathVariable Long id) {
    Employee employee = repository.get(id);
    if (employee == null) {
      throw new EmployeeNotFoundException(id);
    }
    return employee;
  }

  @PostMapping("/employees")
  public ResponseEntity<Employee> newEmployee(@RequestBody Employee newEmployee) {
    newEmployee.setId(1L);
    repository.put(1L, newEmployee);
    return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
  }

  @PutMapping("/employees/{id}")
  public ResponseEntity<Employee> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    Employee employee = repository.get(id);
    if (employee != null) {
      employee.setName(newEmployee.getName());
      employee.setRole(newEmployee.getRole());
      return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    newEmployee.setId(id);
    repository.put(id, newEmployee);
    return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);

  }

  @DeleteMapping("/employees/{id}")
  ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
    Employee employee = repository.get(id);
    if (employee != null) {
      repository.remove(id, employee);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
