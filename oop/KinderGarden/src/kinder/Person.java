package kinder;

import java.util.*;

abstract class Person {

  protected String id;
  protected String first_name;
  protected String last_name;
  protected String address;
  protected String phone;
  protected String birth_year;
  protected String start_year;

  public Person(
      String id,
      String first_name,
      String last_name,
      String address,
      String phone,
      String birth_year,
      String start_year) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.address = address;
    this.phone = phone;
    this.birth_year = birth_year;
    this.start_year = start_year;
  }

  public Person() {}

  // getters
  public String getId() {
    return this.id;
  }

  public String getFirstName() {
    return this.first_name;
  }

  public String getLastName() {
    return this.last_name;
  }

  public String getAddress() {
    return this.address;
  }

  public String getPhone() {
    return this.phone;
  }

  public String getBirthYear() {
    return this.birth_year;
  }

  public String getStartYear() {
    return this.start_year;
  }

  // setters
  public void setId(String id) {
    this.id = id;
  }

  public void setFirstName(String first_name) {
    this.first_name = first_name;
  }

  public void setLastName(String last_name) {
    this.last_name = last_name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setBirthYear(String birth_year) {
    this.birth_year = birth_year;
  }

  public void setStartYear(String start_year) {
    this.start_year = start_year;
  }

  // tostring
  public String toString() {
    return (getId()
        + ","
        + getFirstName()
        + ","
        + getLastName()
        + ","
        + getAddress()
        + ","
        + getPhone()
        + ","
        + getBirthYear()
        + ","
        + getStartYear());
  }
}
