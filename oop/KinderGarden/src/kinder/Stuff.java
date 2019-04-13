package kinder;

import java.util.*;

public class Stuff extends Person {
  private String city;
  private String added_hours;

  public Stuff(
      String id,
      String first_name,
      String last_name,
      String address,
      String city,
      String phone,
      String birth_year,
      String start_year,
      String added_hours) {
    super(id, first_name, last_name, address, phone, birth_year, start_year);

    this.city = city;
    this.added_hours = added_hours;
  }

  public Stuff() {}

  public void setCity(String city) {
    this.city = city;
  }

  public void setAddedHours(String added_hours) {
    this.added_hours = added_hours;
  }

  public String getCity() {
    return this.city;
  }

  public String getAddedHours() {
    return this.added_hours;
  }

  public String toString() {
    return (super.toString() + "," + getCity() + "," + getAddedHours());
  }
}
