import java.io.Serializable;
import java.util.*;
@SuppressWarnings("serial")
public class Specialty implements Serializable, Comparable<Specialty>{
	String name;
	Set <Subject> subjectsSet;
	
	public Specialty(String n) {
		name=n;
		subjectsSet = new TreeSet<Subject>();
	}
	
	public boolean addSubject(Subject sub) {
		return subjectsSet.add(sub);
	}
	
	public String toString() {
		String str=name;
		str=str.concat(" ");
		Iterator<Subject> it = subjectsSet.iterator();
		while(it.hasNext()) {
			Subject sub = (Subject)it.next();
			str+= sub.toString()+" - ";
		}
		
		return str+"\n";
	}

	public int compareTo(Specialty spe) {
		
		return name.compareToIgnoreCase(spe.name);
	}
	public String getName() {return this.name;}
}
