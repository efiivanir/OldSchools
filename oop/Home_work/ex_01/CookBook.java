/**
 * Describe class CookBook
 * Class that create cook book, it derived from Book class and inherit
 * title, author and price attributes.
 * 
 */
public class CookBook extends Book {
  protected boolean isVegeterian;
  
  /**
   * Creates a new CookBook instance.
   *
   * @param title a String value
   * @param author a String value
   * @param price an int value
   * @param isVegeterian a boolean value
   */
  public CookBook(String title, String author, int price, boolean isVegeterian) {
    super(title, author, price);
    this.isVegeterian = isVegeterian;
  }
  

  public CookBook() {
    
  }
  
  /**
   * getIsVegeterian
   *
   * @return a boolean value
   */
  public boolean getIsVegeterian()
  {
    return this.isVegeterian;
  }
  
  /**
   * setIsVegeterian
   *
   * @param isVegeterian a boolean value
   */
  public void setIsVegeterian(boolean isVegeterian) {
    this.isVegeterian = isVegeterian;
  }
  
  /**
   * toString
   *
   * @return a String value
   */
  public String toString() {
    return super.toString() + "," + getIsVegeterian();
  }
}


