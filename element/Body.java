package element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Body<T> implements Collection<T>,Serializable{
	private ArrayList<T> body = new ArrayList<T>();

	public boolean add(T e) {
		return body.add(e);
	}
	
	public void add(int i, T e){
		 body.add(i, e);
	}

	public boolean addAll(Collection<? extends T> c) {
		return body.addAll(c);
	}
	public T getElement(int i){
		return body.get(i);
	}
	public void clear() {
		body.clear();
	}

	public boolean contains(Object o) {
		return body.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return body.containsAll(c);
	}

	public boolean isEmpty() {
		return body.isEmpty();
	}

	public Iterator<T> iterator() {
		return body.iterator();
	}

	public boolean remove(Object o) {
		return body.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return body.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return body.retainAll(c);
	}

	public int size() {
		return body.size();
	}

	public Object[] toArray() {
		return body.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return body.toArray(a);
	}

}
