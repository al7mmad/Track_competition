

public class Student {
	int serial ;
	String teamNum;
	String teamName;
	String name;
	String id;
	String major;
	String rank;
	int number; // ?

	public Student(String name, String id, String major, String rank, String teamNum, String teamName) {

		this.teamNum = teamNum;
		this.teamName = teamName;
		this.name = name;
		this.id = id;
		this.major = major;
		this.rank = rank;
	}
		public Student(int serial, String name, String id, String major, String rank, String teamNum, String teamName) {
		this.serial = serial ;
		this.teamNum = teamNum;
		this.teamName = teamName;
		this.name = name;
		this.id = id;
		this.major = major;
		this.rank = rank;
	}

	public int getSerial() {
		return serial;
	}

	public String getTeamNum() {
		return teamNum;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getMajor() {
		return major;
	}

	public String getRank() {
		return rank;
	}

	public int getNumber() {
		return number;
	}



	@Override
	public String toString() {
		return "Student [teamNum=" + teamNum + ", teamName=" + teamName + ", name=" + name + ", id=" + id + ", major="
				+ major + ", rank=" + rank + "]";
	}

}
