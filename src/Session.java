import java.io.Serializable;

public class Session implements Serializable, Comparable<Session>{
	String type;
	Year year;
	
	public Session(String t,Year y) {
		type=t;
		year = y;
	}
	
	public String toString() {
		return type+" "+year;
	}

	public int compareTo(Session s) {
		
		return type.compareTo(s.type);
	}
	
}
