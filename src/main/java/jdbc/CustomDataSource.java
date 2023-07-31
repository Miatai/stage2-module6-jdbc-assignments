package jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.sql.DataSource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomDataSource implements DataSource {
    private static volatile CustomDataSource instance;
    private final String driver;
    private final String url;
    private final String name;
    private final String password;
    private static final String DB_DRIVER = "postgres.driver";
    private static final String DB_URL = "postgres.url";
    private static final String DB_NAME = "postgres.name";
    private static final String DB_PASSWORD = "postgres.password";
    private static final String RESOURCE_NAME = "app";

    private CustomDataSource(String driver, String url, String password, String name) {
        this.driver = driver;
        this.url = url;
        this.name = name;
        this.password = password;
    }

    public static CustomDataSource getInstance() {
        if(instance != null){
            return instance;
        }
        ResourceBundle resource = ResourceBundle.getBundle(RESOURCE_NAME);
        String driver = resource.getString(DB_DRIVER);
        String url = resource.getString(DB_URL);
        String password = resource.getString(DB_PASSWORD);
        String name = resource.getString(DB_NAME);
        instance = new CustomDataSource(driver, url, password, name);
        return instance;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("Unimplemented method 'getParentLogger'");
    }

    @Override
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'isWrapperFor'");
    }

    @Override
    public <T> T unwrap(Class<T> arg0) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'unwrap'");
    }

    @Override
    public Connection getConnection() throws SQLException {
        return new CustomConnector().getConnection(url, name, password);
    }

    @Override
    public Connection getConnection(String arg0, String arg1) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'getConnection'");
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'getLogWriter'");
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'getLoginTimeout'");
    }

    @Override
    public void setLogWriter(PrintWriter arg0) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'setLogWriter'");
    }

    @Override
    public void setLoginTimeout(int arg0) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'setLoginTimeout'");
    }
    
}
