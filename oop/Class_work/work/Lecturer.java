public class Lecturer extends Worker {
  String title;
  String department;
  String courses[];
  int bonus;

  public String getTitle() {
    return this.title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  public String getDepartment() {
    return this.department;
  }
  public void setDepartment(String department) {
    this.department = department;
  }

  public String getCourses() {
    return this.courses;
  }
  public void setCourses(String courses) {
    this.courses = courses;
  }

  public int getBonus() {
    return this.bonus;
  }
  public void setBonus(int bonus) {
    this.bonus = bonus;
  }

  public String toString() {
    return super.toString() + ","  + getTitle() + ", " + getDepartment() + ", " + getCourses() + "," + getBonus();
  }
}

