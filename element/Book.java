package element;

import java.io.Serializable;

import element.Chapter;
import interfaces.Clearable;
import interfaces.IBook;
import element.Body;

public class Book implements IBook,Clearable,Serializable{
	private String author;
	private String name;
	private String isbn;
	private Body<Chapter> chapterBody = new Body<Chapter>();
	
	public Book(String author_name, String book_name , String isbn){
		this.author = author_name;
		this.name = book_name;
		this.isbn = isbn;
	}
		
	public void add(Chapter ch) {
		chapterBody.add(ch);
	}
	
	public Chapter getChapter(int i){
		return chapterBody.getElement(i);
	}
	
	public Body<Chapter> getAllChapters(){
		return chapterBody;
	}

	
	public void remove(int i) {
		chapterBody.remove(chapterBody.getElement(i));
	}

	
	public void renameAuthor(String newAuthorName) {
		author = newAuthorName;
	}

	
	public void renameBook(String newBookName) {
		name = newBookName;
	}

	
	public void changeISBN(String newISBN) {
		isbn = newISBN;
	}

	
	public int size() {
		return chapterBody.size();
	}
	
	public String getAuthor(){
		return author;
	}
	
	public String getName(){
		return name;
	}
	
	public String getISBN(){
		return isbn;
	}
	
	public void clear() {
		chapterBody.clear();
	}
	
}
