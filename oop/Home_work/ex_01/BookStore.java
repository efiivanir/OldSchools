import java.util.Arrays; 
  
/**
 * BookStore
 * Class that implement bookstore, it gets:
 *   1. Store address
 *   2. educational and cook books empt objects.
 * It implement the methods:
 *   1. addBook for two books types
 *   2. findCheapCookBook
 *   3. printAll
 */
public class BookStore {
  protected String address;
  static protected int EducationalBookArraySize;
  static protected int CookBookArraySize;
  static protected int EducationalBookArrayCurrentIndex = 0;
  static protected int CookBookArrayCurrentIndex = 0;
  protected EducationalBook[] eduBooks;
  protected CookBook[] cookBooks;
  
  
  /**
   * Creates a new BookStore instance.
   *
   * @param address a String value
   * @param edu an EducationalBook value
   * @param cook a CookBook value
   */
  public BookStore(String address,EducationalBook[] edu,CookBook[] cook) {
    this.address = address;
    this.eduBooks = edu;
    this.cookBooks = cook;
    this.EducationalBookArraySize = edu.length;
    this.CookBookArraySize = cook.length;
  }
  
  public BookStore() {
    
  }
  
  /**
   * addBook
   *
   * @param book an EducationalBook value
   */
  public void addBook(EducationalBook book) {
    if(EducationalBookArrayCurrentIndex == EducationalBookArraySize) {
      this.EducationalBookArraySize += 3;
      EducationalBook[] copy = Arrays.copyOf(eduBooks, this.EducationalBookArraySize);
      this.eduBooks = copy;
      copy = null;
    }
    eduBooks[EducationalBookArrayCurrentIndex] = book;
    EducationalBookArrayCurrentIndex++;
  }
  
  /**
   * addBook
   *
   * @param book a CookBook value
   */
  public void addBook(CookBook book) {
    if(CookBookArrayCurrentIndex == CookBookArraySize) {
      this.CookBookArraySize += 3;
      CookBook[] copy = Arrays.copyOf(cookBooks, this.CookBookArraySize);
      this.cookBooks = copy;
      copy = null;
    }
    cookBooks[CookBookArrayCurrentIndex] = book;
    CookBookArrayCurrentIndex++;
  }
  
  
  /**
   * findCheapCookBook
   * Find cook books with price less the parameter price
   *
   * @param price an int value
   * @return a CookBook[] value
   */
  public CookBook[] findCheapCookBook(int price){
    int currentPrice;
    CookBook[]  CheapCookBooks;
    
    int i = 0;
    int j = 0;
    for(i = 0 ; i <= CookBookArrayCurrentIndex - 1; i++) {
      currentPrice = cookBooks[i].getPrice();
      if(currentPrice < price){
        j++;
      }
    }
    if(j == 0){
      return(null);
    }
    CheapCookBooks = new CookBook[j];
    j = 0;
    for(i = 0 ; i <= CookBookArrayCurrentIndex - 1; i++) {
      currentPrice = cookBooks[i].getPrice();
      if(currentPrice < price){
        CheapCookBooks[j] = cookBooks[i];
        j++;
      }
    }
    return(CheapCookBooks) ;
  }

  /**
   * printAll
   * print details of all books in store
   */
  public void printAll(){
    int i;
    for(i = 0;i < eduBooks.length;i++){
      if(eduBooks[i] != null){
        System.out.println("Education: " + eduBooks[i]);
      }
    }
    for(i = 0;i < cookBooks.length;i++){
      if(cookBooks[i] != null){
        System.out.println("Cook: " + cookBooks[i]);
      }
    }
  }
}
