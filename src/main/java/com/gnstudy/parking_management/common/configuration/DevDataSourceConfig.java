package com.gnstudy.parking_management.common.configuration;

import java.sql.Connection;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
@Slf4j
@Profile("!release")
public class DevDataSourceConfig {

  @Autowired
  private Environment env;

  @Bean
  public DataSource dataSource() {
    MySQLDataSourceGenerator dataSourceGenerator = new MySQLDataSourceGenerator(env);
    if (!dataSourceGenerator.hasMySqlSetting()) {
      log.info("MySQL 설정이 없으므로 H2 데이터베이스로 연결합니다.");
      return createH2DataSource();
    }

    try {
      DataSource dataSource = dataSourceGenerator.generate();
      try (Connection conn = dataSource.getConnection()) {
        log.info("MySQL 데이터베이스에 성공적으로 연결되었습니다.");
      }
      return dataSource;
    } catch (Exception e) {
      log.error("MySQL 연결에 실패하였습니다. H2 데이터베이스로 fallback 합니다. 에러: {}", e.getMessage());
      return createH2DataSource();
    }
  }
  private DataSource createH2DataSource() {
    return DataSourceBuilder.create()
        .driverClassName("org.h2.Driver")
        .url("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
        .username("sa")
        .password("")
        .build();
  }

}
