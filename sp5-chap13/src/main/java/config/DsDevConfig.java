package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.apache.tomcat.jdbc.pool.DataSource;

@Configuration
// @Profile 어노테이션의 값을 dev로 주고 스프링 컨테이너 초기화시 "dev"프로필을 활성화하면 이것을 사용한다.
@Profile("dev")
public class DsDevConfig {
    @Bean(destroyMethod = "close")
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
