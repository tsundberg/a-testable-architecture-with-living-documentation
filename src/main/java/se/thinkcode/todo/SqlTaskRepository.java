package se.thinkcode.todo;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;
import java.util.UUID;

public class SqlTaskRepository implements TaskRepository {
    private DataSource datasource;

    public SqlTaskRepository() {
        createDataSource();
    }

    @Override
    public void addTask(Owner owner, Task task) {
        TaskDAO dao = getTaskDAO();

        UUID uuid = UUID.randomUUID();
        String key = uuid.toString();

        String ownerName = owner.getName();
        String taskName = task.getDescription();

        dao.add(key, ownerName, taskName);
    }

    @Override
    public List<Task> getTasks(Owner owner) {
        TaskDAO dao = getTaskDAO();

        String ownerName = owner.getName();

        return dao.getTasks(ownerName);
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

        datasource = new DataSource();
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

    private TaskDAO getTaskDAO() {
        Jdbi jdbi = Jdbi.create(datasource);
        jdbi.installPlugin(new SqlObjectPlugin());

        return jdbi.onDemand(TaskDAO.class);
    }
}
