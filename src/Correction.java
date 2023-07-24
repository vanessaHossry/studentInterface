import java.io.Serializable;

public class Correction implements Serializable, Comparable<Correction>{
	double score1;
	double score2;
	StudentAnswer studentAnswer;
	Corrector corrector1;
	Corrector corrector2;
	
	// -- new correction
	public Correction(Corrector c, double sc, StudentAnswer sta) {
		corrector1 = c;
		score1 = sc;
		studentAnswer = sta;
	}
	
	// -- second correction
//	public Correction(Corrector c,double sc) {
//		corrector2 = c;
//		score2 = sc;
//	}
	public boolean addCorrector2(Corrector c,double sc) {
		this.corrector2=c;
		this.score2=sc;
		return true;
	}
	
	public String toString() {
		String s= studentAnswer.toString()+"\n";
		if(this.corrector1 != null)
				s+="Corrector 1"+corrector1.toString()+" score: "+score1+"\n";
		if(this.corrector2 != null)s+="Corrector 2"+corrector2.toString()+" score: "+score2+"\n \n";
		return s;
	}

	public int compareTo(Correction co) {
		
		int result = this.studentAnswer.compareTo(co.studentAnswer);
		if(this.corrector1 !=null) {
			result+=this.corrector1.compareTo(co.corrector1);}
		if(this.corrector2 != null)
			{result+=this.corrector2.compareTo(co.corrector2);}
		
		return result;
	}
	
	public double noteQuestion() {return (this.score1+this.score2)/2;}
	public double getScore1() {return score1;}
	public double getScore2() {return score2;}
}
