import java.util.*;

public class allChild {
  static int CHILD_ARR_SIZE = 10;
  private Vector allChild_arr = new Vector(CHILD_ARR_SIZE);

  public allChild() {}

  public void addChild(Child st) {
    allChild_arr.addElement(st);
  }

  public Vector getAllChild() {
    return allChild_arr;
  }

  public static void main(String[] args) {
    allChild a = new allChild();
    Child s_0 =
        new Child(
            "123456789", "Moshe Juchmir", "Moshe Dayan 5 Bat-Yam", "04867899", 1970, 1990, 12);
    a.addChild(s_0);
    Vector ab = a.getAllChild();
    System.out.println(ab.size());
  }
}
