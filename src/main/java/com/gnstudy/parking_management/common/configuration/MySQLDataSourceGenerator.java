package com.gnstudy.parking_management.common.configuration;

import javax.sql.DataSource;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.env.Environment;

@AllArgsConstructor
public class MySQLDataSourceGenerator {
  private Environment env;
  public DataSource generate(){
    DataSource dataSource = DataSourceBuilder.create()
        .driverClassName(env.getProperty("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver"))
        .url(env.getProperty("spring.datasource.url"))
        .username(env.getProperty("spring.datasource.username"))
        .password(env.getProperty("spring.datasource.password"))
        .build();
    return dataSource;
  }
  public boolean hasMySqlSetting(){
    String url = env.getProperty("spring.datasource.url");
    if (url == null || url.isEmpty()) return false;
    return true;
  }

}
