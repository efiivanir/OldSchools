import java.util.* ;
public class Machine {
  protected String machine_type;
  protected String machine_building;
  protected int    machine_code;
  
  public Machine(String type, String building, int code) {
    this.machine_type = type;
    this.machine_building = building;
    this.machine_code = code;
  }

  public String getMachineType(){
    return this.machine_type;
  }

  public String getMachineBuilding(){
    return this.machine_building;
  }

  public int getMachineCode(){
    return this.machine_code;
  }


  public void setMachineType(String type){
    this.machine_type = type;
  }

  public void setMachineBuilding(String build){
    this.machine_building = build;
  }

  public void setMachineCode(int code){
    this.machine_code = code;
  }

  public String toString() {
    return getMachineType() + ", " + getMachineBuilding()  + ", " + getMachineCode();
  }
  
  
}
