import java.io.Serializable;

public class Subject implements Serializable, Comparable<Subject>{
	String name;
	
	public Subject(String n) {
		name = n;
	}
	
	public String toString() {
		return name;
	}
	
	public int compareTo(Subject sub) {
	
		return name.compareToIgnoreCase(sub.name);
	}
	
	
}
