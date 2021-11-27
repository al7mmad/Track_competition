import java.util.ArrayList;

public class Competition {
	String compName;
	String compURL;
	String CompDate;
	String sheet;
	int number;
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
		return number;
	}
	void setNumber(int number) {
		this.number= number;
	}
	@Override
	public String toString() {
		return "Competition [compName=" + compName + ", compURL=" + compURL + ", CompDate=" + CompDate
				+ ", compTypeStd=" + compTypeStd + ", stdArray=" + stdArray + "]";
	}

}
