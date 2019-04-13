import java.util.* ;
enum FoodType {FOOD,DRINK}
public class Prod {
 
  protected int prod_code;
  protected String prod_type;
  protected int prod_price;
  
  public Prod(int code, FoodType type, int price) {
    this.prod_code = code;
    this.prod_type = type;
    this.prod_price = price;
  }

  // public int getProdCode(){
  //   return this.prod_code;
  // }

  // public String getProdType(){
  //   return this.prod_type;
  // }

  // public int getProdPrice(){
  //   return this.prod_price;
  // }


  // public void setProdCode(int code){
  //   this.prod_code = code;
  // }

  // public void setProdType(String type){
  //   this.prod_type = type;
  // }

  // public void setProdPrice(int price){
  //   this.prod_price = price;
  // }

  // public String toString() {
  //   return getProdCode() + ", " + getProdType()  + ", " + getProdPrice();
  // }
  
  public static void main(String[] args) {
    Prod a = new Prod(1,FOOD,4);
    
  }
}
