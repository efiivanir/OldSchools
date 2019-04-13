package kinder;

import java.sql.*;
import java.util.*;

public class AreaQueries {
  private Vector<Area> area_queries = new Vector<Area>(20);
  private Connection conn;
  private Statement stmt;
  private ResultSet rs;
  private String query;

  private String id;
  private String name;
  private String manager_id;
  /**
   * AreaQueries constructor Manage the area Vecor
   *
   * <p>Use Area seters/Geters and SqlAct proc
   */
  public AreaQueries() {
    try {
      conn = SqlAct.getConn();
      System.out.println("Good");
      query = "SELECT * from Areas";
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next()) {
        id = rs.getString("id");
        name = rs.getString("name");
        manager_id = rs.getString("manager_id");
        Area a = new Area(id, name, manager_id);
        area_queries.addElement(a);
      }
      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  /**
   * addArea: Add a new area
   *
   * @param id
   * @param name
   * @param mng_id
   */
  public void addArea(String id, String name, String mng_id) {
    Area a = new Area(id, name, mng_id);
    area_queries.addElement(a);
    try {
      conn = SqlAct.getConn();
      System.out.println("Good");
      query = "INSERT INTO Areas VALUES (\"" + id + "\",\"" + name + "\",\"" + mng_id + "\");";

      System.out.println(query);
      PreparedStatement st = conn.prepareStatement(query);
      st.executeUpdate();

      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  /**
   * removeArea: Remove area from area Tables
   *
   * @param id
   */
  public void removeArea(String id) {
    int i;
    for (i = 0; i < area_queries.size(); i++) {

      if (area_queries.elementAt(i).getId() == id) {
        area_queries.remove(i);

        try {
          conn = SqlAct.getConn();
          System.out.println("Good");
          query = "DELETE FROM Areas WHERE id = " + id + ";";
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

  /**
   * getAll: return Vector od all Area Objects
   *
   * @return
   */
  public Vector<Area> getAll() {
    return area_queries;
  }

  /** printAll: Print all Area elements */
  public void printAll() {
    int i;
    for (i = 0; i < area_queries.size(); i++) {
      System.out.println(area_queries.elementAt(i));
    }
  }
}
