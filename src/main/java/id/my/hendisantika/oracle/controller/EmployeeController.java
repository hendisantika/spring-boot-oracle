package id.my.hendisantika.oracle.controller;

import id.my.hendisantika.oracle.entity.Employee;
import id.my.hendisantika.oracle.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-oracle
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/09/24
 * Time: 03.53
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequiredArgsConstructor
class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public Iterable<Employee> all() {
        return employeeRepository.findAll();
    }

}
