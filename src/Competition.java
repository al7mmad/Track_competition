import java.util.ArrayList;

public class Competition {
	String compName;
	String compURL;
	String CompDate;
	String sheet;
	int numberOfStd;
	boolean compTypeStd;
	boolean notification;

	ArrayList<Student> stdArray = new ArrayList<Student>();

	Competition(String compName, String compURL, String CompDate, boolean compTypeStd, String sheet) {
		this.compName = compName;
		this.compURL = compURL;
		this.CompDate = CompDate;
		this.compTypeStd = compTypeStd;
		this.sheet= sheet;
	}

	int getNumber() {
		return numberOfStd;
	}
	void setNumber(int number) {
		this.numberOfStd = number;
	}

	@Override
	public String toString() {
		return "Competition [compName=" + compName + ", compURL=" + compURL + ", CompDate=" + CompDate
				+ ", compTypeStd=" + compTypeStd + ", stdArray=" + stdArray + "]";
	}

	public static Competition search(String s){

		for(Competition c : MySystem.compArray){
			if(c.compName.equals(s)) {
				return c;
			}
		}
		return null ;
	}

}

