/**
 * BookStoreMain
 * BookStoreMain is the test class which call the main method 
 *
 *
 */
public class BookStoreMain { 
  
  /**
   * main
   * main do the following:
   * 1. Init the object of education & cook books.
   * 2. Init the bookstore object
   * 3. Create some books of two kinds.
   * 4. Add the above books to relevant objects,educational or cook books.
   * 5. Run findCheapCookBook to list chip books
   * 6. Run printAll() to list all details of all books
   *
   * @param args a String value
   */
  public static void main(String[] args) {
      int i;
      EducationalBook[] edu = new EducationalBook[4];
      CookBook[] cook = new CookBook[4];
      BookStore localBookStore = new BookStore("ugugugr street",edu,cook);
      
      EducationalBook book1 = new EducationalBook("aaaaa","bbbbb",200,"ccccc");
      EducationalBook book2 = new EducationalBook("aaaa","bbbb",300,"cccc");
      EducationalBook book3 = new EducationalBook("aaa","bbb",400,"ccc");
      EducationalBook book4 = new EducationalBook("aa","bb",500,"cc");
      EducationalBook book5 = new EducationalBook("a","b",600,"c");
      EducationalBook book6 = new EducationalBook("arr","brr",800,"crr");

      CookBook book01 = new CookBook("aaaaa","bbbbb",200,true);
      CookBook book02 = new CookBook("aaaa","bbbb",300,true);
      CookBook book03 = new CookBook("aaa","bbb",400,false);
      CookBook book04 = new CookBook("aa","bb",500,false);
      CookBook book05 = new CookBook("a","b",600,false);

      localBookStore.addBook(book1);
      localBookStore.addBook(book2);
      localBookStore.addBook(book3);
      localBookStore.addBook(book4);
      localBookStore.addBook(book5);
      localBookStore.addBook(book6);
      
      
      localBookStore.addBook(book01);
      localBookStore.addBook(book02);
      localBookStore.addBook(book03);
      localBookStore.addBook(book04);
      localBookStore.addBook(book05);
      
      CookBook[] chip = localBookStore.findCheapCookBook(700);
      if(chip != null){
        for(i = 0 ; i < chip.length ; i++){
          System.out.println(chip[i].getTitle());
        }
      }

      localBookStore.printAll();
    }
}
