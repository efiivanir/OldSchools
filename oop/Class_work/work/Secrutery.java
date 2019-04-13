public class Secrutery extends Worker {
  protected String job;
  protected int addSalary;
  protected int numOfAdditionHours;

  public String getJob() {
    return this.job;
  }
  public void setJobe(String job) {
    this.job = job;
  }
  
  public int getAddSalary() {
    return this.addSalary;
  }
  public void setAddSalary(int addSalary) {
    this.addSalary = addSalary;
  }
  
  public int getNumOfAdditionHours() {
    return this.numOfAdditionHours;
  }
  public void setNumOfAdditionHours(int numOfAdditionHours) {
    this.numOfAdditionHours = numOfAdditionHours;
  }
  public String toString() {
    return super.toString() + ","  + getJob() + ", " + getAddSalary() + ", " + getNumOfAdditionHours() + "," + getAddSalary();
  }
}

