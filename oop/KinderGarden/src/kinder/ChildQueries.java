package kinder;

import java.sql.*;
import java.util.*;

public class ChildQueries {
  private Vector<Child> child_queries = new Vector<Child>(20);
  private Connection conn;
  private Statement stmt;
  private ResultSet rs;
  private String query;

  private String id;
  private String first_name;
  private String last_name;
  private String address;
  private String area_id;
  private String phone;
  private String birth_year;
  private String start_year;

  private String garden_id;

  public ChildQueries() {
    try {
      conn = SqlAct.getConn();
      System.out.println("Good");
      query = "SELECT * from Child";
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next()) {
        id = rs.getString("id");
        first_name = rs.getString("first_name");
        last_name = rs.getString("last_name");
        address = rs.getString("adress");
        area_id = rs.getString("area_id");
        phone = rs.getString("phone");
        birth_year = rs.getString("birth_year");
        start_year = rs.getString("start_year");
        garden_id = rs.getString("garden_id");

        Child s =
            new Child(
                id,
                first_name,
                last_name,
                address,
                area_id,
                phone,
                birth_year,
                start_year,
                garden_id);

        child_queries.addElement(s);
      }
      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void addChild(
      String id,
      String first_name,
      String last_name,
      String address,
      String area_id,
      String phone,
      String birth_year,
      String start_year,
      String garden_id) {

    Child s =
        new Child(
            id, first_name, last_name, address, area_id, phone, birth_year, start_year, garden_id);

    child_queries.addElement(s);
    try {
      conn = SqlAct.getConn();
      System.out.println("Good");
      String delimiter = "\",\"";
      String sqlPar =
          String.join(
              delimiter,
              id,
              first_name,
              last_name,
              address,
              area_id,
              phone,
              birth_year,
              start_year,
              garden_id);

      System.out.println(sqlPar);
      query = "INSERT INTO Child VALUES (\"" + sqlPar + "\");";

      System.out.println(query);
      PreparedStatement st = conn.prepareStatement(query);
      st.executeUpdate();

      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void removeChild(String id) {
    int i;
    for (i = 0; i < child_queries.size(); i++) {
      if (child_queries.elementAt(i).getId().equals(id)) {
        child_queries.remove(i);

        try {
          conn = SqlAct.getConn();
          System.out.println("Good");
          query = "DELETE FROM Child WHERE id = " + id + ";";
          System.out.println(query);

          PreparedStatement st = conn.prepareStatement(query);
          st.executeUpdate();

          conn.close();
        } catch (Exception e) {
          System.out.println(e);
        }
      }
    }
  }

  public Vector<Child> getAll() {
    return child_queries;
  }

  public void printAll() {
    int i;
    System.out.println("Num of recs:" + child_queries.size());

    for (i = 0; i < child_queries.size(); i++) {
      System.out.println(child_queries.elementAt(i));
    }
  }

  public boolean isMaxLimit(String garden_id) {
    int i;
    int limit = 20;
    String key;
    HashMap<String, Integer> h = new HashMap<>();

    for (i = 0; i < child_queries.size(); i++) {
      key = child_queries.elementAt(i).getGardenId();
      if (h.containsKey(key)) {
        h.put(key, h.get(key) + 1);
      } else {
        h.put(key, 1);
      }
    }

    Set<String> keys = h.keySet();
    for (String k : keys) {
      System.out.println("Value of " + k + " is: " + h.get(k));
    }
    if (h.get(garden_id) < limit) {
      return true;
    }
    return false;
  }

  public boolean isChildIdNotExists(String child_id) {
    int i;

    String key;
    HashMap<String, Integer> h = new HashMap<>();

    for (i = 0; i < child_queries.size(); i++) {
      key = child_queries.elementAt(i).getId();
      if (h.containsKey(key)) {
        h.put(key, h.get(key) + 1);
      } else {
        h.put(key, 1);
      }
    }

    Set<String> keys = h.keySet();
    for (String k : keys) {
      System.out.println("Value of " + k + " is: " + h.get(k));
    }
    if (h.get(child_id) < 1) {
      return true;
    }
    return false;
  }
}
