import java.io.Serializable;

public class StudentAnswer implements Comparable<StudentAnswer>, Serializable{
		String answer;
		Student student;
		Question question;
		
		public StudentAnswer(String a,Student s,Question q) {
			answer = a;
			student = s;
			question = q;
		}
		
		public String toString() {
			return student.toString()+"\n"+question.toString()+"\n"+answer;
		}
		
		public int compareTo(StudentAnswer sta) {
			
			int result =student.compareTo(sta.student);
				result+= question.compareTo(sta.question);
				result+= answer.compareTo(sta.answer);
			return result;
		
		}
		public String getAnswer() {
			return this.answer;
		}
		
		
}
