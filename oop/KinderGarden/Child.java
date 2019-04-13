import java.util.*;

public class Child extends Person {

  protected int startYear;
  protected int GardenId;

  public Child(
      String id,
      String name,
      String address,
      String phone,
      int birthYear,
      int startYear,
      int GardenId) {
    super(id, name, address, phone, birthYear);
    this.startYear = startYear;
    this.GardenId = GardenId;
  }
}
