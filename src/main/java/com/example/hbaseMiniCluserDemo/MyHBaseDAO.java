package com.example.hbaseMiniCluserDemo;

import java.io.IOException;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.joda.time.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyHBaseDAO {
  
  private static final Logger LOG = LoggerFactory.getLogger(MyHBaseDAO.class);

  @Autowired
  private Connection connection;
  
  public void push(String valueStr) {

    TableName tableNameObj = TableName.valueOf("table");

    byte[] row = Bytes.toBytes(String.valueOf(DateTimeUtils.currentTimeMillis()));
    byte[] family = Bytes.toBytes("family");
    byte[] qualifier = Bytes.toBytes("qualifier");
    byte[] value = Bytes.toBytes(valueStr);

    try (Table hBaseTable = connection.getTable(tableNameObj)) {
      hBaseTable.put(new Put(row).addColumn(family, qualifier, value));
    } catch (IOException e) {
      LOG.error("Error writing to hbase", e);
    }

  }

}
