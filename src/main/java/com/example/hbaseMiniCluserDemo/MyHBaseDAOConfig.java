package com.example.hbaseMiniCluserDemo;

import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyHBaseDAOConfig {
  
  @Bean
  public MyHBaseDAO myHBaseDAO() {
    return new MyHBaseDAO();
  }

  @Bean
  public Connection connection() throws IOException {
    org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
    config.set("hbase.zookeeper.quorum", "localhost");
    config.set("hbase.zookeeper.port", "1234");
    return ConnectionFactory.createConnection(config);
  }

}
