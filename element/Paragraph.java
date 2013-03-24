package element;

import java.io.Serializable;
import java.util.ArrayList;

public class Paragraph extends Body<Sentence> implements Serializable{
	public Paragraph(String paragraph){
		for( String sentence : paragraph.split("(?<=[a-z])\\.\\s+")){
			this.add(new Sentence(sentence));
		}
	}
	
	public String getText(){
		String paragraph = "";
		for( Sentence sentence : this){
			paragraph += sentence.toString() +".";
		}
		return paragraph;
	}
}
