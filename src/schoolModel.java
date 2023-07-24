import java.io.Serializable;
import java.util.*;

@SuppressWarnings({ "serial", "deprecation" })
public class schoolModel extends Observable implements Serializable{
	     Set<Year> years;
	     Set<Specialty> specialties;
	     Set<Exam> exams;
	     List<Exercise> exercises;
	     List<Question> questions;
	     Set<Student> students;
	     Set<Correction> corrections;
	     Set<Corrector> correctors;
	     Set<Session> sessions;
	     Set<StudentAnswer> studentanswers;
	     Set<Subject> subjects;
	    	     
	     public schoolModel() {
	    	 years = new TreeSet<Year>();
	    	 specialties = new TreeSet<Specialty>();
	    	 exams = new TreeSet<Exam>();
	    	 exercises = new ArrayList<Exercise>();
	    	 questions = new ArrayList<Question>();
	    	 students = new TreeSet<Student>();
	    	 corrections = new TreeSet<Correction>();
	    	 correctors = new TreeSet<Corrector>();
	    	 sessions = new TreeSet<Session>();
	    	 studentanswers = new TreeSet<StudentAnswer>();
	    	 subjects = new TreeSet<Subject>();
	    	
	     }
	     
	     
	     public boolean addYear(Year o) {
	 		if(years.add(o)) {
	 			setChanged();
	 			notifyObservers(o);
	 			return true;
	 		}
	 		return false;
	 	}
	     
	     public boolean addSpeciality(Specialty o) {
		 		if(specialties.add(o)) {
		 			setChanged();
		 			notifyObservers(o);
		 			return true;
		 		}
		 		return false;
		 	}
	     
	     public boolean addExam(Exam o) {
		 		if(exams.add(o)) {
		 			setChanged();
		 			notifyObservers(o);
		 			return true;
		 		}
		 		return false;
		 	}
	     
	     public boolean addExercise(Exercise o) {
		 		if(exercises.add(o)) {
		 			setChanged();
		 			notifyObservers(o);
		 			return true;
		 		}
		 		return false;
		 	}
	     
	     public boolean addQuestion(Question o) {
		 		if(questions.add(o)) {
		 			setChanged();
		 			notifyObservers(o);
		 			return true;
		 		}
		 		return false;
		 	}
	     
	     public void setQuestions(ArrayList<Question> queSet) {
	 		questions = queSet;
	 		setChanged();
	 		notifyObservers(queSet);
	 	}	
	     
	     public boolean addCorrection(Correction o) {
		 		if(corrections.add(o)) {
		 			setChanged();
		 			notifyObservers(o);
		 			return true;
		 		}
		 		return false;
		 	}
	     
	     public boolean addStudent(Student o) {
		 		if(students.add(o)) {
		 			setChanged();
		 			notifyObservers(o);
		 			return true;
		 		}
		 		return false;
		 	}
	     
	     public boolean addCorrector(Corrector o) {
		 		if(correctors.add(o)) {
		 			setChanged();
		 			notifyObservers(o);
		 			return true;
		 		}
		 		return false;
		 	}
	     
	     public boolean addSession(Session o) {
		 		if(sessions.add(o)) {
		 			setChanged();
		 			notifyObservers(o);
		 			return true;
		 		}
		 		return false;
		 	}
	     
	     public boolean addStudentAnswer(StudentAnswer o) {
		 		if(studentanswers.add(o)) {
		 			setChanged();
		 			notifyObservers(o);
		 			return true;
		 		}
		 		return false;
		 	}
	     
	     public void setStudentAnswer(Set<StudentAnswer> ansSet) {
		 		studentanswers = ansSet;
		 		setChanged();
		 		notifyObservers(ansSet);
		 	}	
	     
	     public boolean addSubject(Subject o) {
		 		if(subjects.add(o)) {
		 			setChanged();
		 			notifyObservers(o);
		 			return true;
		 		}
		 		return false;
		 	}
}



