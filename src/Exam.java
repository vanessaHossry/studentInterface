import java.io.Serializable;
import java.util.*;
public class Exam implements Serializable, Comparable<Exam> {
	String date;
	String modality;  //ecrit-projet-devoir
	Set<Exercise> exercisesSet;
	int coefficient;
	Session session;
	Subject subject;
	
	public Exam(String d,String mod,int coef, Session s, Subject sub) {
		date=d;
		modality = mod;
		coefficient = coef;
		session =s ;
		subject =sub;
		exercisesSet= new TreeSet<Exercise>();
	}
	
	public boolean addExercise(Exercise ex) {
		return this.exercisesSet.add(ex);
	}
	
	public String toString() {
		String str= "";
		str+= session.toString()+" "+subject.toString()+" "+modality+" "+coefficient+"% on "+date;
		
		Iterator it = exercisesSet.iterator();
		while(it.hasNext()) {
			Exercise ex = (Exercise)it.next();
			str+=ex.toString()+"\n";
		}
		return str+"\n";
	}

	public int compareTo(Exam exam) {
		
		if(date.compareTo(exam.date)==0) {
			if(session.compareTo(exam.session)==0) {
				if(subject.compareTo(exam.subject)==0) {
					return modality.compareTo(exam.modality);
				}
				return subject.compareTo(exam.subject);
			}
			return session.compareTo(exam.session);
		}
		return date.compareTo(exam.date);
	}
	
	public String getRender() {
		return this.modality+" "+(this.session).year+"-"+(this.subject).name;
	}
	public double noteExam() {return 0;}
	
}
