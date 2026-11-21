package id.my.hendisantika.oracle;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.oracle.OracleContainer;
import org.testcontainers.utility.DockerImageName;

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
@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    OracleContainer oracleFreeContainer() {
        return new OracleContainer(DockerImageName.parse("gvenzl/oracle-free:23-slim-faststart"));
    }
}
