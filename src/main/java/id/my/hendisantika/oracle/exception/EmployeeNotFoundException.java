package id.my.hendisantika.oracle.exception;

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
public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7768215094663842819L;

    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
