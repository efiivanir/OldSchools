package kinder;

import java.sql.*;
import java.util.*;

public class SqlAct {

  public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  // public static final String DB_URL      = "jdbc:mysql://localhost:3306/cityGardens";
  public static final String DB_URL =
      "jdbc:mysql://localhost:3306/cityGardens?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";

  public static final String USER = "root";
  public static final String PASS = "nim16rod";
  public static Connection conn;

  public static Connection getConn() throws ClassNotFoundException {
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected...");
      return conn;
    } catch (SQLException e) {
      System.out.println(e);
      return null;
    }
  }
}
