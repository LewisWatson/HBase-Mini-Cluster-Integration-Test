package com.example.hbaseMiniCluserDemo;

import static org.junit.Assert.*;

import java.io.IOException;

import static org.mockito.BDDMockito.given;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MyHBaseDAOIT {

  @MockBean
  private Connection connection;
  
  @Autowired
  private MyHBaseDAO dao;

  private static HBaseTestingUtility utility;

  @Before
  public void setup() throws Exception {
    utility = new HBaseTestingUtility();
    utility.startMiniCluster();
  }

  @Test
  public void test() throws IOException {

    Table table = utility.createTable(Bytes.toBytes("table"), Bytes.toBytes("family"));

    given(connection.getTable(TableName.valueOf("table"))).willReturn(table);
    
    dao.push("example");
    
    // TODO check that the push was successful

    assertTrue(true);
    
  }

}
