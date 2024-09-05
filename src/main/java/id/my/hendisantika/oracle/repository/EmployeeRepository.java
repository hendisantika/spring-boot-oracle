package id.my.hendisantika.oracle.repository;

import id.my.hendisantika.oracle.entity.Employee;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-oracle
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/09/24
 * Time: 03.52
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByName(String name);

    @Modifying
    @Query("UPDATE employee SET name = :name WHERE id = :id")
    boolean updateByName(@Param("id") Long id, @Param("name") String name);
}
