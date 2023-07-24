import java.io.Serializable;
import java.util.*;
public class Exercise implements Serializable,Comparable<Exercise>{
	int num;
	ArrayList<Question> questionsSet;
	Exam exam;
	
	public Exercise(int n,Exam exm) {
		num = n;
		questionsSet = new ArrayList<Question>();
		exam=exm;
	}
	
	public boolean addQuestion(Question q) {
		if(questionsSet.add(q)) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String s= this.exam.getRender()+" /number "+num+"\n";
		 Iterator value = questionsSet.iterator();
		 
		 while(value.hasNext()) {
			Question q = (Question)value.next();
			s+= q.toString();
			
		 }
		 return s;
	}

	public int compareTo(Exercise ex) {
		
		return num - ex.num;
	}
	
	public double noteExercise() {return 0;}
	
	public String getRender() {
		return "exercise-"+String.valueOf(this.num)+"-"+this.exam.modality+" "+this.exam.subject.name;
	}
	
	public Double calculExerciseScore() {
		Question question;
		Iterator <Question> it = questionsSet.iterator();
		while(it.hasNext()) {
			
		}
		
		return 0.0;
	}
	
}
