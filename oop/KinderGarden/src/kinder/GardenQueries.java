package kinder;

import java.sql.*;
import java.util.*;

public class GardenQueries {
  private Vector<Garden> garden_queries = new Vector<Garden>(20);
  private Connection conn;
  private Statement stmt;
  private ResultSet rs;
  private String query;

  private String id;
  private String name;
  private String address;
  private String area_id;
  private String manager_id;
  private String assist_1_id;
  private String assist_2_id;
  private String assist_3_id;

  public GardenQueries() {
    try {
      conn = SqlAct.getConn();
      System.out.println("Good");
      query = "SELECT * from Garden";
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next()) {
        id = rs.getString("id");
        name = rs.getString("name");
        address = rs.getString("adress");
        area_id = rs.getString("area_id");
        manager_id = rs.getString("manager_id");
        assist_1_id = rs.getString("assist_1_id");
        assist_2_id = rs.getString("assist_2_id");
        assist_3_id = rs.getString("assist_3_id");

        Garden g =
            new Garden(
                id, name, address, area_id, manager_id, assist_1_id, assist_2_id, assist_3_id);
        garden_queries.addElement(g);
      }
      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void addGarden(
      String id,
      String name,
      String address,
      String area_id,
      String manager_id,
      String assist_1_id,
      String assist_2_id,
      String assist_3_id) {

    Garden g =
        new Garden(id, name, address, area_id, manager_id, assist_1_id, assist_2_id, assist_3_id);
    garden_queries.addElement(g);
    try {
      conn = SqlAct.getConn();
      System.out.println("Good");
      String delimiter = "\",\"";
      String sqlPar =
          String.join(
              delimiter,
              id,
              name,
              address,
              area_id,
              manager_id,
              assist_1_id,
              assist_2_id,
              assist_3_id);

      System.out.println(sqlPar);
      query = "INSERT INTO Garden VALUES (\"" + sqlPar + "\");";

      System.out.println(query);
      PreparedStatement st = conn.prepareStatement(query);
      st.executeUpdate();

      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void removeGarden(String id) {
    int i;
    for (i = 0; i < garden_queries.size(); i++) {
      if (garden_queries.elementAt(i).getId() == id) {
        garden_queries.remove(i);

        try {
          conn = SqlAct.getConn();
          System.out.println("Good");
          query = "DELETE FROM Garden WHERE id = " + id + ";";
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

  public Vector<Garden> getAll() {
    return garden_queries;
  }

  public void printAll() {
    int i;
    for (i = 0; i < garden_queries.size(); i++) {
      System.out.println(garden_queries.elementAt(i));
    }
  }

  public Vector<String> ChildsPerGarden(String garden_id) {

    Vector<String> garden_childs = new Vector(20);
    String first_name;
    String last_name;
    String id;
    try {
      conn = SqlAct.getConn();
      System.out.println("Good");
      query = "SELECT * from Child WHERE garden_id = " + garden_id + ";";
      ;
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next()) {
        id = rs.getString("id");
        first_name = rs.getString("first_name");
        last_name = rs.getString("last_name");
        garden_childs.addElement(id + " " + first_name + " " + last_name);
      }
      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return garden_childs;
  }

  public HashMap<String, String> GardensPerArea() {
    String GardenId;
    String GardenName;
    String GardenAddress;
    String GardenArea;
    Vector<Garden> g = getAll();
    HashMap<String, String> h = new HashMap<>();
    for (int i = 0; i < g.size(); i++) {
      GardenId = g.elementAt(i).getId();
      GardenName = g.elementAt(i).getName();
      GardenAddress = g.elementAt(i).getAddress();
      GardenArea = getGardenArea(GardenId);
      if (h.containsKey(GardenArea)) {
        h.put(GardenArea, h.get(GardenArea) + GardenName + "," + GardenAddress + "\n");
      } else {
        h.put(GardenArea, GardenName + "," + GardenAddress + "\n");
      }
    }
    return h;
  }

  public String getGardenArea(String garden_id) {
    String name = "";
    try {
      conn = SqlAct.getConn();
      System.out.println("Good");
      query = "SELECT * from Areas WHERE id = " + garden_id + ";";
      System.out.println(query);
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next()) {
        name = rs.getString("name");
      }
      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return name;
  }
}
