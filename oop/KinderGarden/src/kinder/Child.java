package kinder;

import java.util.*;

public class Child extends Person {
  private String area_id;
  private String garden_id;

  public Child(
      String id,
      String first_name,
      String last_name,
      String address,
      String area_id,
      String phone,
      String birth_year,
      String start_year,
      String garden_id) {

    super(id, first_name, last_name, address, phone, birth_year, start_year);

    this.area_id = area_id;
    this.garden_id = garden_id;
  }

  public Child() {}

  public void setAreaId(String area_id) {
    this.area_id = area_id;
  }

  public void setGardenId(String garden_id) {
    this.garden_id = garden_id;
  }

  public String getAreaId() {
    return this.area_id;
  }

  public String getGardenId() {
    return this.garden_id;
  }

  public String toString() {
    return (super.toString() + "," + getAreaId() + "," + getGardenId());
  }
}
