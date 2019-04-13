/**
 * Book
 * Basic class that Educational and Cook books are derived from.
 *
 */
public class Book {
  protected String title;
  protected String author;
  protected int price;
  
  /**
   * Creates a new Book instance.
   *
   * @param title a String value
   * @param author a String value
   * @param price an int value
   */
  public Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }
  
  public Book() {
  }
  
  /**
   * getTitle
   *
   * @return a String value
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * setTitle
   *
   * @param title a String value
   */
  public void setTitle(String title) {
    this.title = title;
  }
  
  /**
   * getAuthor
   *
   * @return a String value
   */
  public String getAuthor() {
    return this.author;
  }
  
  /**
   * setAutor
   *
   * @param author a String value
   */
  public void setAutor(String author) {
    this.author = author;
  }
  
  /**
   * getPrice
   *
   * @return an int value
   */
  public int getPrice() {
    return this.price;
  }
  
  /**
   * setPrice
   *
   * @param price an int value
   */
  public void setPrice(int price) {
    this.price = price;
  }
  

  /**
   * toString
   *
   * @return a String value
   */
  public String toString() {
    return getTitle() + ", " + getAuthor() + ", " + getPrice();
  }
}







