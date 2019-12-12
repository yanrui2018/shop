package cn.yanrui.shop.config;


import lombok.Data;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
//@Data
//@Configuration
//@ConfigurationProperties(prefix = "spring.datasource.database")
public class DataSourceConfig {
        private String url;
        private String username;
        private String password;

//        @Bean
        public DataSource getDataSource() {
            PooledDataSource dataSource = new PooledDataSource();
            dataSource.setUrl(url);
            dataSource.setUsername(username);// 用户名
            dataSource.setPassword(password);// 密码
            return dataSource;
        }

}
