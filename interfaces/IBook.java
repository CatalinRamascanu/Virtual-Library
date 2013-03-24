package interfaces;

import element.Chapter;

public interface IBook {

    /* metoda pentru adaugarea unui capitol */
    public void add(Chapter ch);

    /* metoda pentru stegerea capitol cu numarul i*/
    public void remove(int i);

    /* metoda pentru schimbarea autorului */
    public void renameAuthor(String newAuthorName);

    /* metoda pentru schimbarea titlului cartii */
    public void renameBook(String newBookName);

    /* metoda pentru schimbarea ISBN-ului cartii */
    public void changeISBN(String newISBN);
    
    /* metoda ce returneaza numarul de capitole din carte */
    public int size();
}
