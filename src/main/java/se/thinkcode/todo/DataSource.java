package se.thinkcode.todo;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class DataSource {
    private org.apache.tomcat.jdbc.pool.DataSource datasource;

    public DataSource() {
        createDataSource();
    }

    private void createDataSource() {
        PoolProperties p = new PoolProperties();

        p.setUrl("jdbc:h2:mem:todo");
        p.setDriverClassName("org.h2.Driver");

        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(15);
        p.setMaxIdle(p.getMaxActive());
        p.setInitialSize(3);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(2);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        datasource = new org.apache.tomcat.jdbc.pool.DataSource();
        datasource.setPoolProperties(p);

        prepareDatabase();
    }

    private void prepareDatabase() {
        Flyway flyway = Flyway
                .configure()
                .dataSource(datasource)
                .load();

        flyway.migrate();
    }

    TaskDAO getTaskDAO() {
        Jdbi jdbi = Jdbi.create(datasource);
        jdbi.installPlugin(new SqlObjectPlugin());

        return jdbi.onDemand(TaskDAO.class);
    }
}
