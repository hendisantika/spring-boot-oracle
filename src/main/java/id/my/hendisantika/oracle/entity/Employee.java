package id.my.hendisantika.oracle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-oracle
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/09/24
 * Time: 03.50
 * To change this template use File | Settings | File Templates.
 */
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private @Id Long id;
    private String name;
    private String job;
    private Integer salary;
    private Integer commission;
}
