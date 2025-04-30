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
@Profile("release")
public class ReleaseDataSourceConfig {
  @Autowired
  private Environment env;

  @Bean
  public DataSource dataSource() {
    MySQLDataSourceGenerator dataSourceGenerator = new MySQLDataSourceGenerator(env);
    if (!dataSourceGenerator.hasMySqlSetting()) {
      log.error("운영 모드에서는 MySQL 설정이 필수입니다.");
      throw new IllegalStateException("운영 모드에서는 MySQL 설정이 필수입니다.");
    }
    DataSource dataSource = dataSourceGenerator.generate();

    try (Connection conn = dataSource.getConnection()) {
      log.info("MySQL 데이터베이스에 성공적으로 연결되었습니다.");
    } catch (Exception e) {
      log.error("운영 모드에서 MySQL 연결에 실패했습니다. 에러: {}", e.getMessage());
      throw new IllegalStateException("운영 모드에서 MySQL 연결에 실패했습니다.", e);
    }
    return dataSource;
  }
}
