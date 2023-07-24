import java.io.Serializable;
import java.util.*;
public class Year implements Serializable, Comparable<Year>{

	String year;
	//Set<Session> sessionsSet;
	
	public Year(String y1, String y2) {
		this.year=y1+"/"+y2;
		//sessionsSet = new TreeSet<Session>();
	}
	
//	public boolean addSession(Session s) {
//		return sessionsSet.add(s);
//	}
	
	public String toString() {
		String str =year;
		//Iterator it = sessionsSet.iterator();
		
//		while(it.hasNext()) {
//			Session s = (Session)it.next();
//			str+= s.toString();
//		}
		return str;
	}

	public int compareTo(Year y) {
		return year.compareTo(y.year);
	}
}
