package tutu;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcHikariUtil {

    private static final DataSource dataSource = new HikariDataSource(new HikariConfig("src/main/resources/hikari.properties"));

    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Test
    public void hikariTest() throws Exception {
        final String configureFile = "src/main/resources/hikari.properties";
        HikariConfig configure = new HikariConfig(configureFile);
        HikariDataSource dataSource = new HikariDataSource(configure);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
