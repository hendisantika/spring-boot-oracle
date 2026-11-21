package id.my.hendisantika.oracle.repository;

import id.my.hendisantika.oracle.TestcontainersConfiguration;
import id.my.hendisantika.oracle.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-oracle
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 21/11/26
 * Time: 22.16
 */
@Import(TestcontainersConfiguration.class)
@SpringBootTest(properties = "spring.sql.init.mode=always")
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void shouldSaveFindAndRenameEmployee() {
        Employee saved = employeeRepository.save(new Employee(null, "JOHN", "CLERK", 7902, null));
        assertThat(saved.getId()).isNotNull();

        List<Employee> found = employeeRepository.findByName("JOHN");
        assertThat(found).extracting(Employee::getJob).contains("CLERK");

        assertThat(employeeRepository.updateByName(saved.getId(), "PETER")).isTrue();
        assertThat(employeeRepository.findById(saved.getId()))
                .get()
                .extracting(Employee::getName)
                .isEqualTo("PETER");
    }
}
