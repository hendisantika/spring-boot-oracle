package id.my.hendisantika.oracle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(properties = "spring.sql.init.mode=always")
class SpringBootOracleApplicationTests {

    @Test
    void contextLoads() {
    }

}
