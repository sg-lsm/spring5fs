package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.apache.tomcat.jdbc.pool.DataSource;

public class MemberConfigWithProfile {
    @Configuration
    @Profile("dev")
    public static class DsDevConfig {
        public DataSource dataSource(){
            DataSource ds = new DataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
            ds.setUsername("spring5");
            ds.setPassword("spring5");
            ds.setInitialSize(2);
            ds.setMaxActive(10);
            ds.setTestWhileIdle(true);
            ds.setMinEvictableIdleTimeMillis(60*1000*3);
            ds.setTimeBetweenEvictionRunsMillis(10*1000);
            return ds;
        }
    }

    public static class DsRealConfig{
        public DataSource dataSource(){
            DataSource ds = new DataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://realdb/spring5fs?characterEncoding=utf8");
            ds.setUsername("spring5");
            ds.setPassword("spring5");
            ds.setInitialSize(2);
            ds.setMaxActive(10);
            ds.setTestWhileIdle(true);
            ds.setMinEvictableIdleTimeMillis(60*1000*3);
            ds.setTimeBetweenEvictionRunsMillis(10*1000);
            return ds;
        }
    }
}
