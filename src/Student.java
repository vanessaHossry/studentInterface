import java.io.Serializable;

public class Student implements Serializable,Comparable<Student>{
	String first_name;
	String last_name;
	
	public Student(String fn, String ln) {
		first_name = fn;
		last_name = ln;
	}
	
	public String toString() {
		return first_name +" "+last_name+"-student";
	}

	public int compareTo(Student s) {

        if (last_name.compareToIgnoreCase(s.last_name) == 0) {
            return first_name.compareToIgnoreCase(s.first_name);
        } else {
            return last_name.compareToIgnoreCase(s.last_name);
        }
	}
	public String getName() {
		return first_name+" "+last_name;
	}
}
