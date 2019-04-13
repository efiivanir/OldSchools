import java.util.*;

class Students {
  protected ArrayList<Integer> StudentsArr;
  
  
  public Students (ArrayList<Integer> arr) {
    this.StudentsArr = arr;
  }

  public void add(Integer id){
    StudentsArr.add(id); 
  }
}

// class Courses {
//   ArrayList CoursesArr[];
//   public Courses (ArrayList arr) {
//     this.CoursesArr = arr;
//   }

// }


public class StudensMain {
  public static void main(String args[]) {
   ArrayList<Integer> students = new ArrayList<Integer>();
   Students students_obj = new Students(students);
   Integer id = new Integer(1234567);
   students_obj.add(id);
  }
}
