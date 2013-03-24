package element;

import java.io.Serializable;

import interfaces.Clearable;
import interfaces.ILibrary;
import element.Book;
import element.Body;;

public class Library implements ILibrary,Clearable,Serializable{
	private Body<Book> bookBody = new Body<Book>();
	
	public void add(Book book) {
		bookBody.add(book);
		
	}

	
	public void remove(Book book) {
		bookBody.remove(book);
	}
	
	public void addAtIndex(int i , Book book){
		bookBody.add(i,book);
	}
	
	public void removeAtIndex(int i){
		bookBody.remove(bookBody.getElement(i));
	}

	public Book getBook(int i){
		return bookBody.getElement(i);
	}
	
	public int size(){
		return bookBody.size();
	}
	
	public void clear() {
		bookBody.clear();
		
	}
	
}
