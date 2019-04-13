package kinder;
import java.util.* ;
public class Garden {
  private String id;
  private String name;
  private String address;
  private String area_id;
  private String manager_id;
  private String assist_1_id;
  private String assist_2_id;
  private String assist_3_id;
  
  
  public Garden(String id, String name, String address, String area_id, String manager_id,
                String assist_1_id, String assist_2_id, String assist_3_id) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.area_id = area_id;
    this.manager_id = manager_id;
    this.assist_1_id = assist_1_id;
    this.assist_2_id = assist_2_id;
    this.assist_3_id = assist_3_id; 
  }

  public Garden() {
    
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }

  public void setAreaId(String area_id) {
    this.area_id = area_id;
  }

  public void setMngId(String manager_id) {
    this.manager_id = manager_id;
  }

  public void setAss1Id(String assist_1_id) {
    this.assist_1_id = assist_1_id;
  }

  public void setAss2Id(String assist_2_id) {
    this.assist_2_id = assist_2_id;
  }

  public void setAss3Id(String assist_3_id) {
    this.assist_3_id = assist_3_id;
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }
  
  public String getAddress() {
    return this.address;
  }

  public String getAreaId() {
    return this.area_id;
  }

  public String getMngId() {
    return this.manager_id;
  }

  public String getAss1Id() {
    return this.assist_1_id;
  }

  public String getAss2Id() {
    return this.assist_2_id;
  }

  public String getAss3Id() {
    return this.assist_3_id;
  }

  
  public String toString() {
    return(getId() + "," + getName() + "," + getAddress() + "," + getAreaId() + "," + getMngId() + "," +
           getAss1Id() + "," + getAss2Id() + "," + getAss3Id());
  }

}
