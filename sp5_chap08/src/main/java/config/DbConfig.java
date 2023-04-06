package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.tomcat.jdbc.pool.DataSource;

@Configuration
public class DbConfig {
    @Bean(destroyMethod = "close")
    // DataSource 타입의 dataSource선언 후 내부에 설정해준다.
    // 새로운 DataSource를 선언 sql서버명 url 유저이름 패스워드 최소사이즈, 최대활성 유휴커넥션검사 최소유휴시간 등을 설정
    public DataSource dataSource(){
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");;
        ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
        ds.setUsername("spring5");
        ds.setPassword("spring5");
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setTestWhileIdle(true);
        ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3);
        return ds;
    }

}
