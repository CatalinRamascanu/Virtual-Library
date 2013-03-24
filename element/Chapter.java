package element;

import java.io.Serializable;

import element.Paragraph;
import interfaces.Clearable;
import interfaces.IChapter;
import element.Body;

public class Chapter implements IChapter,Clearable,Serializable{

	private String name;
	private Body<Paragraph> paragraphBody = new Body<Paragraph>();
	public Chapter(String name){
		this.name = name;
	}
	public Chapter(Chapter ch){
		name = ch.name;
		paragraphBody = ch.paragraphBody;
	}
	public void add(Paragraph p) {
		paragraphBody.add(p);
	}
	
	public void add(int i,Paragraph p){
		paragraphBody.add(i,p);
	}

	public void remove(int i) {
		paragraphBody.remove(getParagraph(i));
	}
	
	public String getName(){
		return name;
	}
	
	public Paragraph getParagraph(int i){
		return paragraphBody.getElement(i);
	}
	public void changeChapterName(String newChapterName) {
		name = newChapterName;
	}

	public int size() {
		return paragraphBody.size();
	}

	public void clear() {
		paragraphBody.clear();
	}


}
