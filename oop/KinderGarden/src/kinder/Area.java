package kinder;

import java.util.*;

/**
 * Class Area
 *
 * @param id
 * @param name
 * @param manager_id
 */
public class Area {
  private String id;
  private String name;
  private String manager_id;
  /**
   * Constructor for Area class
   *
   * @param id Area id
   * @param name Area Name
   * @param manager_id Area Manager id
   * @return
   */
  public Area(String id, String name, String manager_id) {
    this.id = id;
    this.name = name;
    this.manager_id = manager_id;
  }

  public Area() {}

  /** Sters & Geters for Area Class parameters */
  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMngId(String manager_id) {
    this.manager_id = manager_id;
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getMngId() {
    return this.manager_id;
  }

  public String toString() {
    return (getId() + "," + getName() + "," + getMngId());
  }
}
