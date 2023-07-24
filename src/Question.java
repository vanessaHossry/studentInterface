import java.io.Serializable;

public class Question implements Serializable,Comparable<Question>{
		int num;
		String content;
		double scale_note;
		public Question(int n,String cnt,double sn) {
			num = n;
			content = cnt;
			scale_note = sn;
		}
		
		public String toString() {
			return num+" "+content+" "+scale_note+"\n";
		}
		

		public int compareTo(Question q) {
			
			int result = num - q.num;
			result+=content.compareTo(q.content);
			result+=scale_note-q.scale_note;
			return result;
		}
		public int getNum() {return num;}
		public String getContent(){return content;}
		public double getScaleScore() {return scale_note;}
		
		public String getRender() {
			return String.valueOf(this.num);
		}
}
