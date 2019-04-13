/**
 * EducationalBook
 * Class that create educational book, it derived from Book class and inherit
 * title, author and price attributes.
 * 
 */
public class EducationalBook extends Book {
  protected String subject;
  
  /**
   * Creates a new EducationalBook  instance.
   *
   * @param title a String value
   * @param author a String value
   * @param price an int value
   * @param subject a String value
   */
  public EducationalBook(String title, String author, int price, String subject) {
    super(title,author,price);
    this.subject = subject;
  }
  
  /**
   * Creates a new enpty EducationalBook instance.
   *
   */
  public EducationalBook() {
    
  }
  

  /**
   * getSubject
   *
   * @return a String subject
   */
  public String getSubject()
  {
    return this.subject;
  }
  
  /**
   * setSubject method
   *
   * @param subject a String value
   */
  public void setSubject(String subject) {
    this.subject = subject;
  }
  
  /**
   * toString method
   *
   * @return a String value
   */
  public String toString() {
    return super.toString() + "," + getSubject();
  }
}
