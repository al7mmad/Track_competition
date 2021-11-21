import java.util.ArrayList;

public class Competition {
	String compName;
	String compURL;
	String CompDate;
	boolean compTypeStd;
	boolean notification;

	ArrayList<Student> stdArray = new ArrayList<Student>();

	Competition(String compName, String compURL, String CompDate, boolean compTypeStd) {
		this.compName = compName;
		this.compURL = compURL;
		this.CompDate = CompDate;
		this.compTypeStd = compTypeStd;

	}

	public boolean getNotification() {
		return notification;
	}

	public void getNotification(String compName) {
		this.notification = notification;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompURL() {
		return compURL;
	}

	public void setCompURL(String compURL) {
		this.compURL = compURL;
	}

	public String getCompDate() {
		return CompDate;
	}

	public void setCompDate(String compDate) {
		CompDate = compDate;
	}

	public boolean isCompTypeStd() {
		return compTypeStd;
	}

	public void setCompTypeStd(boolean compTypeStd) {
		this.compTypeStd = compTypeStd;
	}

	public ArrayList<Student> getStdArray() {
		return stdArray;
	}

	public void setStdArray(ArrayList<Student> stdArray) {
		this.stdArray = stdArray;
	}

	@Override
	public String toString() {
		return "Competition [compName=" + compName + ", compURL=" + compURL + ", CompDate=" + CompDate
				+ ", compTypeStd=" + compTypeStd + ", stdArray=" + stdArray + "]";
	}

}
