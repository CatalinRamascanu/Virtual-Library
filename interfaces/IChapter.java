
package interfaces;

import element.Paragraph;

public interface IChapter {
    
    /* metoda pentru adaugarea unui paragraf */
    public void add(Paragraph p);
    
    /* metoda pentru stergerea paragrafului cu index-ul i */
    public void remove(int i);
    
    /* metoda pentru redenumirea titlului capitolului */
    public void changeChapterName(String newChapterName);
    
    /* metoda ce returneaza numarul de paragrafe din capitol */
    public int size();
}
