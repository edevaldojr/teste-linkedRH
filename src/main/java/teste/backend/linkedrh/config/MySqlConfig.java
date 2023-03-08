package teste.backend.linkedrh.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class MySqlConfig {
    
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.dbname}")
    private String dbName;

    @Bean
    public DataSource dataSource() {
        log.debug("Criando datasource");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/" + dbName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
    
    @Bean
    public Connection getConnection() throws SQLException {
        log.debug("Criando conexão");
        return dataSource().getConnection();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}