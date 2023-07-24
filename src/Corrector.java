import java.io.Serializable;

public class Corrector implements Serializable, Comparable<Corrector>{
	
		String first_name;
		String last_name;
		
		public Corrector(String fn, String ln) {
			first_name = fn;
			last_name = ln;
		}
		
		public String toString() {
			return first_name +" "+last_name+"-corrector";
		}

		public int compareTo(Corrector c) {

	        int result = first_name.compareTo(c.first_name);
	        result+= last_name.compareTo(c.last_name);
	        return result;
		}
		public String getName() {
			return first_name+" "+last_name;
		}
	}

