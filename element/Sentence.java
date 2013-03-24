package element;


import java.io.Serializable;
import java.util.ArrayList;

public class Sentence extends ArrayList<String> implements Serializable{
	
	public Sentence(String sentence){
		super();
		int lengthSentence = sentence.split(" ").length;
		String []words = new String[ lengthSentence];
		words = sentence.split(" ");
		
		for(int i = 0; i < lengthSentence; i++)
			super.add(words[i]);
		
	}
	
	@Override
	public String toString(){
		String sentence = "";
		for( String text : this){
			sentence += text + " ";
		}
		return sentence;
	}
	
	public ArrayList<String> getSentenceWords(){
		return this;
	}
	
	@Override
	public int size(){
		return super.size();
	}
}
