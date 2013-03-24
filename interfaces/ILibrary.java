package interfaces;

import element.Book;

public interface ILibrary {
    
    /* metoda pentru adaugarea unei carti in biblioteca */
    public void add(Book book);
    
    /* metoda pentru stergerea unei carti din biblioteca dupa ISBN */
    public void remove(Book book);
}