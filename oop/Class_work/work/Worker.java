public class Worker {
  protected String name;
  protected String id;
  protected int incom;
  protected int seniority;
  
  public Worker(String name,String id,int incom,int seniority){
    this.name = name;
    this.id = id;
    this.incom = incom;
    this.seniority = seniority;
  }

  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getId() {
    return this.id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public int getIncom() {
    return this.incom;
  }
  public void setIncom(int incom) {
    this.incom = incom;
  }
  public int getSeniority() {
    return this.seniority;
  }
  public void setSeniority(int seniority) {
    this.seniority = seniority;
  }

  public String toString() {
    return getName() + ", " + getId() + ", " + getIncom() + "," + getSeniority();
  }
  
}

