package kinder;

import java.sql.*;
import java.util.*;

public class StuffQueries {
  private Vector<Stuff> stuff_queries = new Vector<Stuff>(20);
  private Connection conn;
  private Statement stmt;
  private ResultSet rs;
  private String query;

  private String id;
  private String first_name;
  private String last_name;
  private String address;
  private String city;
  private String phone;
  private String birth_year;
  private String start_year;
  private String added_hours;

  public StuffQueries() {
    try {
      conn = SqlAct.getConn();
      System.out.println("Good");
      query = "SELECT * from Stuff";
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next()) {
        id = rs.getString("id");
        first_name = rs.getString("first_name");
        last_name = rs.getString("last_name");
        address = rs.getString("adress");
        city = rs.getString("city");
        phone = rs.getString("phone");
        birth_year = rs.getString("birth_year");
        start_year = rs.getString("start_year");
        added_hours = rs.getString("added_hours");

        Stuff s =
            new Stuff(
                id,
                first_name,
                last_name,
                address,
                city,
                phone,
                birth_year,
                start_year,
                added_hours);

        stuff_queries.addElement(s);
      }
      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void addStuff(
      String id,
      String first_name,
      String last_name,
      String address,
      String city,
      String phone,
      String birth_year,
      String start_year,
      String added_hours) {
    Stuff s =
        new Stuff(id, first_name, last_name, address, city, phone, birth_year, start_year, "0");
    stuff_queries.addElement(s);
    try {
      conn = SqlAct.getConn();
      System.out.println("Good");
      query =
          "INSERT INTO Stuff VALUES (\""
              + id
              + "\",\""
              + first_name
              + "\",\""
              + last_name
              + "\",\""
              + address
              + "\",\""
              + city
              + "\",\""
              + phone
              + "\",\""
              + birth_year
              + "\",\""
              + start_year
              + "\",\""
              + added_hours
              + "\");";
      System.out.println(query);
      PreparedStatement st = conn.prepareStatement(query);
      st.executeUpdate();

      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void removeStuff(String id) {

    int i;
    for (i = 0; i < stuff_queries.size(); i++) {

      if (stuff_queries.elementAt(i).getId().equals(id)) {
        stuff_queries.remove(i);

        try {
          conn = SqlAct.getConn();
          System.out.println("Good");
          query = "DELETE FROM Stuff WHERE id = " + id + ";";
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

  public Vector<Stuff> getAll() {
    return stuff_queries;
  }

  public void printAll() {
    int i;
    System.out.println("Num of recs:" + stuff_queries.size());
    for (i = 0; i < stuff_queries.size(); i++) {
      System.out.println(stuff_queries.elementAt(i));
    }
  }

  public Vector<String> WorksPerStuffId(String stuff_id, String job) {
    Vector<String> stuff_works = new Vector(20);
    if (job.equals("mng_area")) {
      stuff_works = getAreas(stuff_id);
    } else if (job.equals("mng_garden")) {
      stuff_works = getGardens(stuff_id, "manager_id");
    } else if (job.equals("ass_1")) {
      stuff_works = getGardens(stuff_id, "assist_1_id");
    } else if (job.equals("ass_2")) {
      stuff_works = getGardens(stuff_id, "assist_2_id");
    } else if (job.equals("ass_3")) {
      stuff_works = getGardens(stuff_id, "assist_3_id");
    }
    return stuff_works;
  }

  public Vector<String> getGardens(String id, String job) {
    Vector<String> places = new Vector(20);
    String name;
    try {
      conn = SqlAct.getConn();
      System.out.println("Good");
      query = "SELECT * from Garden  WHERE " + job + " = " + id + ";";
      System.out.println(query);

      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next()) {
        name = rs.getString("name");
        places.addElement(name);
      }
      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return places;
  }

  public Vector<String> getAreas(String id) {
    Vector<String> places = new Vector(20);
    String name;
    try {
      conn = SqlAct.getConn();
      System.out.println("Good");
      query = "SELECT * from Areas  WHERE id = " + id + ";";
      System.out.println(query);

      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next()) {
        name = rs.getString("name");
        places.addElement(name);
      }
      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return places;
  }
}
